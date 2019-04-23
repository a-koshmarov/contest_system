package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ProblemView {
    public ListView problemList;
    public TextArea problemDescription;

    private final FileChooser fileChooser = new FileChooser();

    @FXML
    public void initialize(){
        initList();
    }

    private void initList(){
        problemList.getItems().addAll("Problem 1", "Problem 2");
        //TODO get contests problem
    }

    @FXML
    public void clickOnProblem(MouseEvent event) throws IOException {
        if(problemList.getSelectionModel().getSelectedItem() != null){
            problemDescription.setText(problemList.getSelectionModel().getSelectedItem().toString());
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
        File file = fileChooser.showOpenDialog(stage);
        //TODO something with file
    }
}
