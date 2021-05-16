package Pula;

import Components.User;
import Controllers.LogRegController;
import Databases.ProviderDTB;
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

    private final UserDTB userDTB;
    private final ProviderDTB providerDTB;

    public App(){
        instance = this;
        userDTB = new UserDTB("src/main/resources/Databases/UserDTB.json");
        providerDTB = new ProviderDTB();
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

    public UserDTB getUserDTB() {
        return userDTB;
    }

    /// GOTOs
    public void gotoLogout(){
        loggedUser = null;
        gotoLogin();
    }

    public void gotoProfile() {
        try {
            replaceSceneContent("/Stages/provider_main.fxml");

//            ProviderController controller = loader.getController();
//            controller.setUserDTB(userDTB);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void gotoLogin() {
        try {
            LogRegController controller = (LogRegController)replaceSceneContent("/Stages/logreg.fxml");

            controller.setDTB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // CHANGE SCENE
    private Object replaceSceneContent(String fxml) throws Exception {
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

        return loader.getController();
    }

    public static void main(String[] args) {
        launch();
    }
}
