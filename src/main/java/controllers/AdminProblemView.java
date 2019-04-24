package controllers;

import domain.entities.*;
import domain.managers.AdminManager;
import domain.testing.JavaCompiler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.Context;
import util.Warnings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AdminProblemView {
    public ListView<Problem> problemList;
    public TextArea problemDescription;
    public BorderPane borderPane;
    public ChoiceBox<domain.testing.Compiler> choiceBox;


    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private ToolBar newBar = new ToolBar();
    private String prevText;
    private final FileChooser fileChooser = new FileChooser();

    private AdminManager adminManager = new AdminManager();
    private File file;

    @SuppressWarnings("Duplicates")
    @FXML
    public void initialize() {
        save.setOnAction(event -> {
            problemDescription.setEditable(false);
            adminManager.getProblemManager().editProblemDescription(problemList.getSelectionModel().getSelectedItem(), problemDescription.getText());
            borderPane.setTop(null);
        });
        cancel.setOnAction(event -> {
            problemDescription.setText(prevText);
            problemDescription.setEditable(false);
            borderPane.setTop(null);
        });
        domain.testing.Compiler compiler = new JavaCompiler();
        choiceBox.getItems().add(compiler);

        newBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        newBar.getItems().addAll(save, cancel);
        initList();
    }

    private void initList() {
        problemList.getItems().addAll(adminManager.getProblemManager().getAllProblems(Context.getCurrentContest()));
    }

    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/AdminContestView.fxml")));
        stage.setScene(scene);
    }

    @FXML
    public void clickOnProblem(MouseEvent event) throws IOException {
        if (problemList.getSelectionModel().getSelectedItem() != null) {
            Context.setCurrentProblem(problemList.getSelectionModel().getSelectedItem());
            problemDescription.setText(problemList.getSelectionModel().getSelectedItem().getDescription());
        }
    }

    @FXML
    public void handleEdit() {
        prevText = problemDescription.getText();
        borderPane.setTop(newBar);
        problemDescription.setEditable(true);
    }

    @FXML
    public void handleAdd(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/newProblem.fxml")));
        stage.setTitle("New problem");
        stage.setScene(scene);
    }

    @FXML
    public void handleDelete(ActionEvent event) {
        if (problemList.getSelectionModel().getSelectedItem() != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            BorderPane pane = new BorderPane();
            pane.setPadding(new Insets(30, 50, 50, 50));
            Button yes = new Button("Yes");
            Button no = new Button("No");

            pane.setLeft(yes);
            pane.setRight(no);
            pane.setTop(new Text("Are you sure that you want to delete: " + problemList.getSelectionModel().getSelectedItem().getName()));

            yes.setOnAction(newEvent -> {
                adminManager.getProblemManager().removeProblem(Context.getCurrentContest(), problemList.getSelectionModel().getSelectedItem());
                Stage newStage = (Stage) ((Node) newEvent.getSource()).getScene().getWindow();
                Scene scene = null;
                try {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/AdminProblemView.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                newStage.setTitle("Admin problem view");
                newStage.setScene(scene);
            });

            no.setOnAction(newEvent -> {
                Stage newStage = (Stage) ((Node) newEvent.getSource()).getScene().getWindow();
                Scene scene = null;
                try {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/AdminProblemView.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                newStage.setTitle("Admin problem view");
                newStage.setScene(scene);
            });

            Scene scene = new Scene(pane, 400, 400);
            stage.setScene(scene);

            stage.setTitle(problemList.getSelectionModel().getSelectedItem().getName() + " removing");
            stage.show();
        } else {
            Warnings.selectTheProblemWarning();
        }
    }

    @FXML
    public void chooseFile(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        //TODO something with file
    }

    @FXML
    public void allResults(ActionEvent event) {
        if (problemList.getSelectionModel().getSelectedItem() != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            BorderPane pane = new BorderPane();
            Button back = new Button("Back");

            pane.setTop(back);
            ListView<Attempt> attemptListView = new ListView<>();

            back(back);
            attemptListView.getItems().addAll(adminManager.getAttemptManager().getAllAttempts(problemList.getSelectionModel().getSelectedItem()));
            pane.setCenter(attemptListView);

            Scene scene = new Scene(pane, 700, 400);


            stage.setTitle(problemList.getSelectionModel().getSelectedItem().getName() + " all results");
            stage.setScene(scene);

        } else {
            Warnings.selectTheProblemWarning();
        }
    }

    @FXML
    public void results(ActionEvent event) {
        if (problemList.getSelectionModel().getSelectedItem() != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            BorderPane pane = new BorderPane();
            Button back = new Button("Back");

            pane.setTop(back);
            ListView<Attempt> attemptListView = new ListView<>();

            back(back);
//            ContestantManager contestantManager = new ContestantManager();
            attemptListView.getItems().addAll(adminManager.getAttemptManager().getUserAttempts(problemList.getSelectionModel().getSelectedItem(), Context.getCurrentContestant()));
            pane.setCenter(attemptListView);

            Scene scene = new Scene(pane, 700, 400);


            stage.setTitle(problemList.getSelectionModel().getSelectedItem().getName() + " results");
            stage.setScene(scene);

        } else {
            Warnings.selectTheProblemWarning();
        }
    }

    private void back(Button back) {
        back.setOnAction(event1 -> {
            Stage stage1 = (Stage) ((Node) event1.getSource()).getScene().getWindow();
            Scene scene = null;
            try {
                scene = new Scene(FXMLLoader.load(getClass().getResource("/AdminProblemView.fxml")));
            } catch (IOException e) {
                System.out.println("error");
                e.printStackTrace();
            }
            stage1.setScene(scene);
        });
    }

    @FXML
    public void submit(ActionEvent event) throws FileNotFoundException {
        if (problemList.getSelectionModel().getSelectedItem() != null) {
            String attemptText = "{attempt}";
            if (file != null) {
                Scanner scanner = new Scanner(file);
                if (scanner.hasNext()) attemptText = scanner.nextLine();
            }
            if (choiceBox.getSelectionModel().isEmpty()) {
                Warnings.selectCompilerWarning();
            } else {
                adminManager.getAttemptManager().submitAttempt(Context.getCurrentProblem(), attemptText, choiceBox.getValue().toString(), Context.getCurrentContestant());
                results(event);
            }
        } else {
            Warnings.selectTheProblemWarning();
        }
    }
}
