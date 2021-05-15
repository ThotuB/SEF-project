package MainScenesControllers;

import Controllers.ProviderHomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProviderWelcomeController extends Application {

    @Override
    public void start(Stage welcomeStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Stages/provider_main.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root);

        ProviderHomeController providerHomeController = loader.getController();


        welcomeStage.setScene(scene);
        welcomeStage.setTitle("Login/Register");

        providerHomeController.setNameLabel("John");

        welcomeStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
