package Applications;

import Controllers.ProviderController;
import Databases.ProviderDTB;
import Databases.UserDTB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class ProviderApplication extends Application {
    ProviderDTB providerDTB;

    @Override
    public void init() throws Exception {
        super.init();
        providerDTB = new ProviderDTB("src/main/resources/Databases/ProvidersDTB.json");
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Stages/provider_main.fxml"));
        Parent page = loader.load();
        Scene scene = new Scene(page);

        ProviderController controller = loader.getController();
        controller.setup(providerDTB);

        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

