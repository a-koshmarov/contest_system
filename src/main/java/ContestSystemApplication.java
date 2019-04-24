import domain.managers.AdminManager;
import domain.managers.ContestantManager;
import util.Context;
import domain.managers.AuthManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.HibernateSessionFactory;

public class ContestSystemApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AuthManager authManager = new AuthManager();
        Context.setCurrentContestant(authManager.getContestant("joja","1234d" ));
//        Context.setCurrentContestant(authManager.getContestant("bobDylan","12345" ));
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        primaryStage.setTitle("M3205 contest system");
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnHidden(event -> HibernateSessionFactory.shutdown());
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
