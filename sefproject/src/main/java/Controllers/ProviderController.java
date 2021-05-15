package Controllers;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ProviderController {

    @FXML
    private Text provNameLabel;
    public GridPane gamesGridPane;
    public Button newOfferButton;

    public void setNameLabel(String name) {
        provNameLabel.setText(name);
    }

    public void newOfferClick() {

        System.out.println(provNameLabel.getText());

        String []args = {provNameLabel.getText()};
    }

    public void setGamesGridPane(ArrayList<String> from) {

        int n = 0;
        int m = 0;

        for (Node pane: gamesGridPane.getChildren()) {
            GridPane.setHalignment(pane, HPos.CENTER);
            GridPane.setValignment(pane, VPos.CENTER);
        }

        for (String i: from) {

            Text a = new Text();
            a.setText(i);
            a.setFont(new Font("Verdana",20));
            a.setFill(Color.WHITESMOKE);

            gamesGridPane.add(a, m, n);

            m++;
            if (m == 2) {
                n++;
                m = 0;
            }

        }
    }
}
