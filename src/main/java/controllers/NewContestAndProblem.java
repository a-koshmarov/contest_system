package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class NewContestAndProblem {

    final FileChooser fileChooser = new FileChooser();
    public TextField problemName;
    public TextArea problemDescription;


    public DatePicker contestStart;
    public DatePicker contestEnd;
    public TextArea contestDetails;
    public TextField contestName;


    @FXML
    public void addProblem(){
        //TODO add to base new problem
    }
    @FXML
    public void addContest(){
        //TODO add to base new contest
    }
    @FXML
    public void cancel(){

    }
    @FXML
    public void chooseFile(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        //TODO something with file
    }
}
