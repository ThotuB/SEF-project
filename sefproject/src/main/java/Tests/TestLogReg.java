package Tests;

import Controllers.LogRegController;
import Controllers.ProviderHomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestLogReg extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Stages/logreg.fxml"));

        Parent root = loader.load();

        LogRegController controller = loader.getController();
        controller.setUserDTB();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Login/Register");

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
