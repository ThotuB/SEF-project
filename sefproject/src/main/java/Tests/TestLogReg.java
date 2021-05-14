package Tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class TestLogReg extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Stages/logreg.fxml")));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Login/Register");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
