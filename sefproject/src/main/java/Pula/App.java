package Pula;

import Components.User;
import Controllers.LogRegController;
import Databases.UserDTB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private Stage stage;
    private User loggedUser;

    public static App instance;

    private UserDTB userDTB;

    public App(){
        instance = this;
        userDTB = new UserDTB("src/main/resources/Databases/UserDTB.json");
    }

    public static App getInstance(){
        return instance;
    }

    @Override public void start(Stage primaryStage) {
        stage = primaryStage;
        gotoLogin();
        primaryStage.show();
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    /// GOTOs
    public void gotoLogout(){
        loggedUser = null;
        gotoLogin();
    }

    public void gotoProfile() {
        try {
            replaceSceneContent("/Stages/provider_main.fxml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void gotoLogin() {
        try {
            FXMLLoader loader = replaceSceneContent("/Stages/logreg.fxml");

            LogRegController controller = loader.getController();
            controller.setUserDTB(userDTB);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // CHANGE SCENE
    private FXMLLoader replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent page = loader.load();
        Scene scene = stage.getScene();

        if (scene == null) {
            scene = new Scene(page);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();

        return loader;
    }

    public static void main(String[] args) {
        launch();
    }
}
