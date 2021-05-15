package Applications;

import Components.ProviderCollection;
import Controllers.ProviderController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class ProviderApplication extends Application {

    ProviderCollection providerCollection;
    Parameters parameters;
    List<String> parameterList;

    @Override
    public void init() throws Exception {
        super.init();
        parameters = getParameters();
        parameterList = parameters.getUnnamed();
        providerCollection = new ProviderCollection(parameterList.get(0));
    }

    @Override
    public void start(Stage welcomeStage) throws Exception {

        welcomeStage.setTitle("Welcome");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Stages/provider_main.fxml"));
        Parent root = loader.load();

        ProviderController providerController = loader.getController();

        Scene scene = new Scene(root);

        welcomeStage.setScene(scene);

        providerController.setNameLabel(providerCollection.getCurrentProvider().getName());
        providerController.setGamesGridPane(providerCollection.getCurrentProvider().getStringGameArray());

        //System.out.println(providerCollection.getCurrentProvider().getStringGameArray());
        welcomeStage.setResizable(false);
        welcomeStage.show();
    }

    public static void main(String[] args) {
        launch("Raul");
    }
}

