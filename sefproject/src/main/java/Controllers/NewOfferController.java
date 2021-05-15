package Controllers;

import Components.Game;
import Components.ProviderCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class NewOfferController implements Initializable {

    ProviderCollection providerCollection;

    ObservableList<String> list = FXCollections.observableArrayList();

    public TextField gameNameTextField;
    public TextField priceTextField;
    public TextField descriptionTextField;
    public DatePicker startDateDatePicker;
    public DatePicker endDateDatePicker;
    public ChoiceBox<String> choiceBoxo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    private void loadData() {
        list.removeAll();

        String a = "true";
        String b = "false";

        list.addAll(a,b);

        choiceBoxo.getItems().addAll(list);
    }

    public void newOfferButtonClick() {

        String gName;
        double price;
        Date startDate;
        Date endDate;
        boolean rent;
        String description;

        gName = gameNameTextField.getText();
        price = Double.parseDouble(priceTextField.getText());
        startDate = java.sql.Date.valueOf(startDateDatePicker.getValue());
        endDate = java.sql.Date.valueOf(endDateDatePicker.getValue());
        rent = Boolean.parseBoolean(choiceBoxo.getValue());
        description = descriptionTextField.getText();

        Game addedGame = new Game(gName, price, description, startDate, endDate, rent);
        System.out.println(addedGame);

    }

}
