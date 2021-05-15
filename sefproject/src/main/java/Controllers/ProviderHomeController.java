package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ProviderHomeController {

    @FXML
    private Text provNameLabel;
    private ListView<String> offerListListView = new ListView<>();

    public void setNameLabel(String name) {
        provNameLabel.setText(name);
    }

    public void setOfferListListView(ArrayList<String> from) {

        //System.out.println(from);

        offerListListView.getItems().addAll(from);

        offerListListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    public void click(ActionEvent actionEvent) {

    }

}
