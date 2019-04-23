package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ContestView {
    @FXML
    public ListView contestListView;
    @FXML
    public TextArea contestDetails;


    @FXML
    public void initialize(){
        initList();
    }

    private void initList(){
        contestListView.getItems().addAll("Contest ITMO 2019 superHackaton","Contest ITMO 2018 superHackaton");
        //TODO get users contests
    }

    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/start.fxml")));
        stage.setScene(scene);
    }

    @FXML
    public void clickOnContest(MouseEvent event) throws IOException {
        if(contestListView.getSelectionModel().getSelectedItem() != null) {
            if (event.getClickCount() == 2) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/ProblemView.fxml")));
                stage.setScene(scene);
            } else {
                //TODO init TextArea
                contestDetails.setText(contestListView.getSelectionModel().getSelectedItem().toString());
            }
        }
    }
}
