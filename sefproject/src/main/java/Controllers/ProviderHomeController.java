package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ProviderHomeController {

    @FXML
    private Text provNameLabel;

    public void setNameLabel(String name) {
        provNameLabel.setText(name);
    }

    public void click(ActionEvent actionEvent) {

    }

}
