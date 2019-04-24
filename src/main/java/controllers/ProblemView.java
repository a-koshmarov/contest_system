package controllers;

import domain.entities.Attempt;
import domain.entities.Problem;
import domain.managers.ContestantManager;
import domain.testing.JavaCompiler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.Context;
import util.Warnings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ProblemView {
    public ListView<Problem> problemList;
    public TextArea problemDescription;

    private final FileChooser fileChooser = new FileChooser();
    private File file;
    public ChoiceBox<domain.testing.Compiler> choiceBox;
    private ContestantManager contestantManager = new ContestantManager();

    @FXML
    public void initialize() {
        initList();
    }

    private void initList() {
        choiceBox.getItems().addAll(new JavaCompiler());
        problemList.getItems().clear();
        problemList.getItems().addAll(contestantManager.getAttemptManager().getAllProblems(Context.getCurrentContest()));
    }

    @FXML
    public void clickOnProblem(MouseEvent event) throws IOException {
        if (problemList.getSelectionModel().getSelectedItem() != null) {
            Context.setCurrentProblem(problemList.getSelectionModel().getSelectedItem());
            problemDescription.setText(problemList.getSelectionModel().getSelectedItem().getDescription());
        }
    }

    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/ContestView.fxml")));
        stage.setScene(scene);
    }

    public void chooseFile(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        //TODO something with file
    }

    public void showResults(ActionEvent event) {
        if (problemList.getSelectionModel().getSelectedItem() != null) {

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            BorderPane pane = new BorderPane();
            Button back = new Button("Back");

            pane.setTop(back);
            ListView<Attempt> attemptListView = new ListView<>();
            back.setOnAction(event1 -> {
                Stage stage1 = (Stage) ((Node) event1.getSource()).getScene().getWindow();
                Scene scene = null;
                try {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/ProblemView.fxml")));
                } catch (IOException e) {
                    System.out.println("error");
                    e.printStackTrace();
                }
                stage1.setScene(scene);
            });

            attemptListView.getItems().addAll(contestantManager.getAttemptManager().getAllUserAttempts(problemList.getSelectionModel().getSelectedItem(), Context.getCurrentContestant()));

            pane.setCenter(attemptListView);
            Scene scene = new Scene(pane, 700, 400);

            stage.setTitle(problemList.getSelectionModel().getSelectedItem().getName() + " all results");
            stage.setScene(scene);

        } else {
            Warnings.selectTheProblemWarning();
        }
    }

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
                contestantManager.getAttemptManager().submitAttempt(Context.getCurrentProblem(), attemptText, choiceBox.getValue().toString(), Context.getCurrentContestant());
                showResults(event);
            }
        } else {
            Warnings.selectTheProblemWarning();
        }
    }
}
