package Controllers;

import Components.Customer;
import Databases.CustomerDTB;
import Main.App;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class CustomerController {
    CustomerDTB customerDTB;
    Customer currentCustomer;

    @FXML
    public GridPane gamesGridPane;
    public Text customerNameLabel;

    public void setup(String username){

        customerDTB = new CustomerDTB("src/main/resources/Databases/CustomersDTB.json", username);
        //customerDTB = App.getInstance().getCustomerDTB();
        currentCustomer = customerDTB.getCurrentCustomer();


        loadData();
//        resetGameDataFields();
        updateGridAndList();
    }

    public void loadData() {

        customerNameLabel.setText(currentCustomer.getName());

    }

    private void updateGridAndList() {
        setGamesGridPane(currentCustomer.getStringGameArray());
        //setGameListListView(currentProvider.getStringGameArray());
    }

    // SETTERS
    public void setGamesGridPane(ArrayList<String> gridPaneStr) {
        int col = 0;
        int row = 0;

        for (Node pane: gamesGridPane.getChildren()) {
            GridPane.setHalignment(pane, HPos.CENTER);
            GridPane.setValignment(pane, VPos.CENTER);
        }

        gamesGridPane.getChildren().clear();

        for (String str: gridPaneStr) {

            Text text = new Text();
            text.setText(str);
            text.setFont(new Font("Verdana",20));
            text.setFill(Color.WHITESMOKE);

            gamesGridPane.add(text, row, col);

            row++;
            if ( row == 2 ) {
                col++;
                row = 0;
            }

        }
    }

    public void browseGamesClick() {

    }

    public void seeLibraryClick() {

    }

    public void transactionsClick() {

    }

    public void addMoneyClick() {

    }

}
