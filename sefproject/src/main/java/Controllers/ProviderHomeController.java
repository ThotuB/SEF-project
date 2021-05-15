package Controllers;

import Components.Provider;
import Databases.ProviderDTB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ProviderHomeController {

    private ProviderDTB providerDTB = new ProviderDTB();
    private Provider currentProvider;

    public ProviderHomeController(String username) {

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

    @FXML
    private Text provNameLabel;

    public void setNameLabel(String name) {
        provNameLabel.setText(name);
    }

    public void click(ActionEvent actionEvent) {

    }

}
