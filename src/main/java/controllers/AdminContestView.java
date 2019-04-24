package controllers;

import domain.entities.Attempt;
import domain.entities.Contest;
import domain.entities.Problem;
import domain.managers.AdminManager;
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
import javafx.stage.Stage;
import util.Context;
import util.Warnings;

import java.io.IOException;
import java.util.ArrayList;

public class AdminContestView {
    public TextArea contestDetails;
    public ListView<Contest> contestListView;
    public BorderPane borderPane;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private ToolBar newBar = new ToolBar();
    private String prevText;
    private AdminManager adminManager = new AdminManager();

    @SuppressWarnings("Duplicates")
    @FXML
    public void initialize() {
        save.setOnAction(event -> {
            contestDetails.setEditable(false);
            adminManager.getContestManager().editContestDescription(contestListView.getSelectionModel().getSelectedItem(), contestDetails.getText());
            borderPane.setTop(null);
        });

        cancel.setOnAction(event -> {
            contestDetails.setText(prevText);
            contestDetails.setEditable(false);
            borderPane.setTop(null);
        });
        newBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        newBar.getItems().addAll(save, cancel);
        initList();
    }

    private void initList() {
        contestListView.getItems().clear();
        contestListView.getItems().addAll(adminManager.getContestManager().getContests());
    }

    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/start.fxml")));
        stage.setScene(scene);
    }

    @FXML
    public void clickOnContest(MouseEvent event) throws IOException {
        if (contestListView.getSelectionModel().getSelectedItem() != null) {
            if (event.getClickCount() == 2) {
                Context.setCurrentContest(contestListView.getSelectionModel().getSelectedItem());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/AdminProblemView.fxml")));
                stage.setTitle("Problems");
                stage.setScene(scene);
            } else {
                contestDetails.setText(contestListView.getSelectionModel().getSelectedItem().getDescription());
            }
        }
    }

    @FXML
    public void handleDelete(ActionEvent event) {
        if (contestListView.getSelectionModel().getSelectedItem() != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            BorderPane pane = new BorderPane();
            pane.setPadding(new Insets(30, 50, 50, 50));
            Button yes = new Button("Yes");
            Button no = new Button("No");

            pane.setLeft(yes);
            pane.setRight(no);
            pane.setTop(new Text("Are you sure that you want to delete: " + contestListView.getSelectionModel().getSelectedItem().getName()));

            yes.setOnAction(newEvent -> {
                adminManager.getContestManager().cancelContest(contestListView.getSelectionModel().getSelectedItem());
                Stage newStage = (Stage) ((Node) newEvent.getSource()).getScene().getWindow();
                Scene scene = null;
                try {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/AdminContestView.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                newStage.setTitle("Admin contest view");
                newStage.setScene(scene);
            });

            no.setOnAction(newEvent -> {
                Stage newStage = (Stage) ((Node) newEvent.getSource()).getScene().getWindow();
                Scene scene = null;
                try {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/AdminContestView.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                newStage.setTitle("Admin contest view");
                newStage.setScene(scene);
            });

            Scene scene = new Scene(pane, 400, 400);
            stage.setScene(scene);

            stage.setTitle(contestListView.getSelectionModel().getSelectedItem().getName() + " removing");
            stage.show();
        } else {
            Warnings.selectTheContestWarning();
        }

    }

    @FXML
    public void handleAdd(ActionEvent event) throws IOException {
        Context.setCurrentContest(contestListView.getSelectionModel().getSelectedItem());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/newContest.fxml")));
        stage.setTitle("New contest");
        stage.setScene(scene);
    }

    private ArrayList<Attempt> getContestAttempts(Contest contest) {
        ArrayList<Attempt> attempts = new ArrayList<>();
        if (contestListView.getSelectionModel().getSelectedItem() != null) {
            for (Problem problem : adminManager.getAllProblems(contest)) {
                attempts.addAll(adminManager.getAttemptManager().getAllAttempts(problem));
            }
        }
        return attempts;
    }

    @FXML
    public void handleAllResult(ActionEvent event) {
        if (contestListView.getSelectionModel().getSelectedItem() != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            BorderPane pane = new BorderPane();
            Button back = new Button("Back");

            pane.setTop(back);
            ListView<Attempt> attemptListView = new ListView<>();

            back(back);
            attemptListView.getItems().addAll(getContestAttempts(contestListView.getSelectionModel().getSelectedItem()));
            pane.setCenter(attemptListView);

            Scene scene = new Scene(pane, 700, 400);
            stage.setScene(scene);

            stage.setTitle(contestListView.getSelectionModel().getSelectedItem().getName() + " results");
            stage.show();
        } else {
            Warnings.selectTheContestWarning();
        }

//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        BorderPane pane = new BorderPane();
//        Button back = new Button("Back");
//
//        pane.setTop(back);
//        ListView<Attempt> attemptListView = new ListView<>();
//
//        back(back);
//        for (Contest contest : adminManager.getContestManager().getContests()) {
//            attemptListView.getItems().addAll(getContestAttempts(contest));
//        }
//        pane.setCenter(attemptListView);
//
//        Scene scene = new Scene(pane, 700, 400);
//
//        stage.setTitle("All results");
//        stage.setScene(scene);


    }

    private void back(Button back) {
        back.setOnAction(event1 -> {
            Stage stage1 = (Stage) ((Node) event1.getSource()).getScene().getWindow();
            Scene scene = null;
            try {
                scene = new Scene(FXMLLoader.load(getClass().getResource("/AdminContestView.fxml")));
            } catch (IOException e) {
                System.out.println("error");
                e.printStackTrace();
            }
            stage1.setScene(scene);
        });
    }

    @FXML
    public void handleEdit() {
        contestDetails.setText(contestListView.getSelectionModel().getSelectedItem().getDescription());
        prevText = contestDetails.getText();
        borderPane.setTop(newBar);
        contestDetails.setEditable(true);
    }

}
