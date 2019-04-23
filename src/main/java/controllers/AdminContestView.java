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
import javafx.stage.Stage;

import java.io.IOException;

public class AdminContestView {
    public TextArea contestDetails;
    public ListView contestListView;
    public BorderPane borderPane;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private ToolBar newBar = new ToolBar();
    private String prevText;

    @SuppressWarnings("Duplicates")
    @FXML
    public void initialize() {
        save.setOnAction(event -> {
            contestDetails.setEditable(false);
            //TODO запись в базу
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
        contestListView.getItems().addAll("Contest ITMO 2019 superHackaton", "Contest ITMO 2018 superHackaton");
        //TODO getAllContests
    }

    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/start.fxml")));
        stage.setScene(scene);
    }

    @FXML
    public void clickOnContest(MouseEvent event) throws IOException {
        if (contestListView.getSelectionModel().getSelectedItem() != null && event.getClickCount() == 2) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/AdminProblemView.fxml")));
            stage.setTitle("Problems");
            stage.setScene(scene);
        } else {
            //TODO init TextArea with data from contest
            contestDetails.setText(contestListView.getSelectionModel().getSelectedItem().toString());
        }
    }

    @FXML
    public void handeDelete() {
        //TODO delete selected item from base
    }

    @FXML
    public void handeAdd() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/newContest.fxml"));
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
    public void handeResult() {
        //TODO get contest result
    }

    @FXML
    public void handeAllResult() {
        //TODO get all contest result
    }

    @FXML
    public void handleEdit() {
        prevText = contestDetails.getText();
        borderPane.setTop(newBar);
        contestDetails.setEditable(true);
    }
}
