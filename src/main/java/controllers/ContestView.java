package controllers;

import domain.entities.Contest;
import domain.managers.ContestantManager;
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
    public ListView<Contest> contestListView;
    @FXML
    public TextArea contestDetails;

    private ContestantManager contestantManager = new ContestantManager();


    @FXML
    public void initialize(){
        initList();
    }

    private void initList(){
        contestListView.getItems().clear();
        contestListView.getItems().addAll(contestantManager.getAttemptManager().getUserContest(Context.getCurrentContestant()));
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
                Context.setCurrentContest(contestListView.getSelectionModel().getSelectedItem());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/ProblemView.fxml")));
                stage.setScene(scene);
            } else {
                contestDetails.setText(contestListView.getSelectionModel().getSelectedItem().getDescription());
            }
        }
    }
}
