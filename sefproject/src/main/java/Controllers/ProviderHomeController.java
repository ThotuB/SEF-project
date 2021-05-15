package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ProviderHomeController {

    @FXML
    private Text provNameLabel;
    //public ListView<String> offerListListView;
    public GridPane gamesGridPane;
    public Button newOfferButton;

    public void setNameLabel(String name) {
        provNameLabel.setText(name);
    }

    public void setGamesGridPane(ArrayList<String> from) {

        int n = 0;
        int m = 0;


        for (String i: from) {

            Text a = new Text();
            a.setText("\t\t" + i);
            a.setFont(new Font("Verdana",20));
            a.setFill(Color.WHITESMOKE);

            gamesGridPane.add(a, m, n);
            gamesGridPane.setAlignment(Pos.CENTER_RIGHT);

            m++;
            if (m == 2) {
                n++;
                m = 0;
            }

        }
    }
}
