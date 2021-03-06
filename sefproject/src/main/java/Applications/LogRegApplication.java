package Applications;

import Controllers.LogRegController;
import Databases.UserDTB;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogRegApplication extends Application {
    UserDTB userDTB;

    @Override
    public void init() throws Exception {
        super.init();
        userDTB = new UserDTB("src/main/resources/Databases/UserDTB.json");
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Stages/logreg.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        LogRegController controller = loader.getController();
        controller.setup(userDTB);

        stage.setScene(scene);
        stage.setTitle("Login/Register");

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
