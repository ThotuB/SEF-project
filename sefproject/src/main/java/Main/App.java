package Main;

import Components.User;
import Controllers.LogRegController;
import Controllers.ProviderController;
import Databases.ProviderDTB;
import Databases.UserDTB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {
    private Stage stage;
    private User loggedUser;

    public static App instance;

    private final UserDTB userDTB;
    private ProviderDTB providerDTB;

    public App(){
        instance = this;
        userDTB = new UserDTB("src/main/resources/Databases/UserDTB.json");
    }

    // GETTERS
    public static App getInstance(){
        return instance;
    }


    public ProviderDTB getProviderDTB() {
        return providerDTB;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public UserDTB getUserDTB() {
        return userDTB;
    }

    // START
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        gotoLogin();
        primaryStage.show();
    }
    /// GOTOs
    public void gotoLogout(){
        loggedUser = null;
        gotoLogin();
    }

    public void gotoProfile(String username) {
        try {
            ProviderController controller = (ProviderController)replaceSceneContent("/Stages/provider_main.fxml");

            providerDTB = new ProviderDTB("src/main/resources/Databases/ProvidersDTB.json", username);
            controller.setup(username);
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

    public static void alert(String header, String content){
        Alert alert = new Alert(Alert.AlertType.NONE);

        alert.setTitle("Error");

        alert.setHeaderText(header);
        alert.setContentText(content);

        ButtonType okButton= new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(okButton);

        alert.initModality(Modality.APPLICATION_MODAL);

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }

}
