package Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestLogin extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setTitle("Login/Register");
        stage.setScene(new Scene(root, 600, 800));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
