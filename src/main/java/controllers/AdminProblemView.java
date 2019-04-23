package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AdminProblemView {
    public ListView problemList;
    public TextArea problemDescription;
    public BorderPane borderPane;


    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private ToolBar newBar = new ToolBar();
    private String prevText;
    private final FileChooser fileChooser = new FileChooser();

    @SuppressWarnings("Duplicates")
    @FXML
    public void initialize() {
        save.setOnAction(event -> {
            problemDescription.setEditable(false);
            //TODO запись в базу
            borderPane.setTop(null);
        });
        cancel.setOnAction(event -> {
            problemDescription.setText(prevText);
            problemDescription.setEditable(false);
            borderPane.setTop(null);
        });
        newBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        newBar.getItems().addAll(save, cancel);
        initList();
    }

    private void initList() {
        problemList.getItems().addAll("Problem 1", "Problem 2");
        //TODO getAll contest problems
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
            //todo init list with problem description
            problemDescription.setText(problemList.getSelectionModel().getSelectedItem().toString());
        }
    }

    @FXML
    public void handleEdit() {
        prevText = problemDescription.getText();
        borderPane.setTop(newBar);
        problemDescription.setEditable(true);
    }

    @FXML
    public void handleAdd() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/newProblem.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setOnHidden((event) -> {
            initList();
        });
        stage.setTitle("New Contest");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handeDelete() {
        //TODO delete selected item from base
    }

    @FXML
    public void chooseFile(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        //TODO something with file
    }
}
