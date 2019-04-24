package util;

import javafx.scene.control.Alert;

public class Warnings {
    public static void selectCompilerWarning(){
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error alert");
        alert.setHeaderText("Can not submit.");
        alert.setContentText("Please choose a compiler!");

        alert.showAndWait();
    }
    public static void selectTheProblemWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");
        alert.setContentText("Select the problem.");

        alert.showAndWait();
    }

    public static void selectTheContestWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");
        alert.setContentText("Select the contest.");

        alert.showAndWait();
    }
}
