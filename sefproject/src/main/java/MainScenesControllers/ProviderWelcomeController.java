package MainScenesControllers;

import Components.Provider;
import Controllers.ProviderHomeController;
import Databases.ProviderDTB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class ProviderWelcomeController extends Application {

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

        ProviderHomeController providerHomeController = loader.getController();

        Scene scene = new Scene(root);

        welcomeStage.setScene(scene);

        providerHomeController.setNameLabel(providerCollection.getCurrentProvider().getName());
        providerHomeController.setGamesGridPane(providerCollection.getCurrentProvider().getStringGameArray());

        System.out.println(providerCollection.getCurrentProvider().getStringGameArray());

        welcomeStage.show();
    }

    public static void main(String[] args) {
        launch("Raul");
    }
}

class ProviderCollection {

    private ProviderDTB providerDTB = new ProviderDTB();
    private Provider currentProvider;

    public ProviderCollection(String username) {

        providerDTB.readProviders();
        currentProvider = getProvider(username);

    }

    public Provider getCurrentProvider() {
        return currentProvider;
    }

    public Provider getProvider(String username){

        for (Provider i: providerDTB.getData()) {
            if (i.getName().equals(username)) {
                return i;
            }
        }
        return null;
    }

}