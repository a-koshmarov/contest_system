package controllers;

import domain.managers.AdminManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.Context;

import java.io.File;
import java.io.IOException;

public class NewContestAndProblem {

    final FileChooser fileChooser = new FileChooser();
    public TextField problemName;
    public TextArea problemDescription;


    public DatePicker contestStart;
    public DatePicker contestEnd;
    public TextArea contestDetails;
    public TextField contestName;

    private AdminManager adminManager = new AdminManager();


    @FXML
    public void addProblem(ActionEvent event) throws IOException {
        if (problemName.getText() != null
                && problemDescription.getText() != null) {
            adminManager.getProblemManager().addProblem(Context.getCurrentContest(),
                    problemName.getText(),
                    problemDescription.getText());
        }
        problemCancel(event);
    }

    @FXML
    public void addContest(ActionEvent event) throws IOException {
        if (contestStart.getValue() != null
                && contestEnd.getValue() != null
                && contestDetails.getText() != null
                && contestName.getText() != null) {
            adminManager.getContestManager().addContest(
                    contestName.getText(),
                    contestDetails.getText(),
                    contestStart.getValue().toString(),
                    contestEnd.getValue().toString());
        }
        contestCancel(event);
    }

    @FXML
    public void contestCancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/AdminContestView.fxml")));
        stage.setTitle("Admin contest view");
        stage.setScene(scene);
    }

    public void problemCancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/AdminProblemView.fxml")));
        stage.setTitle("Admin problem view");
        stage.setScene(scene);
    }

    @FXML
    public void chooseFile(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        //TODO something with file
    }
}
