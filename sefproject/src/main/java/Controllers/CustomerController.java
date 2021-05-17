package Controllers;

import Components.Customer;
import Components.Game;
import Databases.CustomerDTB;
import Databases.ProviderDTB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
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
    public ListView<String> gameListListView;
    public ListView<Double> gameListViewPrice;
    public ListView<String> gameListViewPeriod;

    public AnchorPane addMoneyAnchorPane;
    public AnchorPane libraryAnchorPane;
    public AnchorPane transactionsAnchorPane;
    public AnchorPane browseGamesAnchorPane;


    public void setup(String username){

        customerDTB = new CustomerDTB("src/main/resources/Databases/CustomersDTB.json", username);
        //customerDTB = App.getInstance().getCustomerDTB();
        currentCustomer = customerDTB.getCurrentCustomer();


        loadData();
//        resetGameDataFields();
        updateGridAndList();
    }

    private void updateGridAndList() {
        ProviderDTB gameHolders = new ProviderDTB("src/main/resources/Databases/ProvidersDTB.json");

        //System.out.println(gameHolders);

        setGameListListView(gameHolders.getAllGamesFromDTB());
        setGamesGridPane(currentCustomer.getStringGameArray());
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


    public void setGameListListView(ArrayList<Game> gamesFromDTB) {

        ObservableList<String> observableListGameName = FXCollections.observableArrayList();
        ObservableList<Double> observableListGamePrice = FXCollections.observableArrayList();
        ObservableList<String> observableListGamePeriod = FXCollections.observableArrayList();

        //System.out.println(listViewStr);


        observableListGameName.removeAll();
        observableListGamePrice.removeAll();
        observableListGamePeriod.removeAll();

        for (Game i: gamesFromDTB) {

            observableListGameName.add(i.getName());
            observableListGamePrice.add(i.getPrice());
            if (i.getRent()) {
                observableListGamePeriod.add("" + i.getStartDate().toString().substring(0,10) + " -> " +
                        i.getEndDate().toString().substring(0,10));
            }
            else observableListGamePeriod.add("" + i.getStartDate().toString().substring(0,10) + " -> " +
                    "Forever");

        }
//
//        System.out.println(observableListGameName);
//        System.out.println(observableListGamePrice);
//        System.out.println(observableListGamePeriod);

        //observableList.addAll(listViewStr);

        gameListListView.getItems().clear();
        gameListViewPrice.getItems().clear();
        gameListViewPeriod.getItems().clear();

        gameListListView.getItems().addAll(observableListGameName);
        gameListViewPrice.getItems().addAll(observableListGamePrice);
        gameListViewPeriod.getItems().addAll(observableListGamePeriod);
        //gameListListView.getItems().addAll(observableList);
    }

    public void loadData() {
        customerNameLabel.setText(currentCustomer.getName());
    }


    public void seeLibraryClick() {
        libraryAnchorPane.setVisible(true);
        browseGamesAnchorPane.setVisible(false);
        transactionsAnchorPane.setVisible(false);
        addMoneyAnchorPane.setVisible(false);
    }

    public void browseGamesClick() {
        browseGamesAnchorPane.setVisible(true);
        transactionsAnchorPane.setVisible(false);
        libraryAnchorPane.setVisible(false);
        addMoneyAnchorPane.setVisible(false);
    }

    public void transactionsClick() {
        transactionsAnchorPane.setVisible(true);
        libraryAnchorPane.setVisible(false);
        browseGamesAnchorPane.setVisible(false);
        addMoneyAnchorPane.setVisible(false);
    }

    public void addMoneyClick() {
        addMoneyAnchorPane.setVisible(true);
        transactionsAnchorPane.setVisible(false);
        libraryAnchorPane.setVisible(false);
        browseGamesAnchorPane.setVisible(false);
    }

}
