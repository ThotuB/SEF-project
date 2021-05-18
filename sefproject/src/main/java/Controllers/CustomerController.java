package Controllers;

import Components.Customer;
import Components.Game;
import Components.Provider;
import Databases.CustomerDTB;
import Databases.ProviderDTB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class CustomerController {
    CustomerDTB customerDTB;
    Customer currentCustomer;
    ProviderDTB providerDTB;

    @FXML
    public GridPane gamesGridPane;

    public Text customerNameLabel;
    public Text balanceText;

    public ListView<String> gameListListView;
    public ListView<Double> gameListViewPrice;
    public ListView<String> gameListViewPeriod;

    public AnchorPane addMoneyAnchorPane;
    public AnchorPane libraryAnchorPane;
    public AnchorPane transactionsAnchorPane;
    public AnchorPane browseGamesAnchorPane;

    public Button chooseThisGameButton;

    public TextField amountTextField;
    public TextField creditCardTextField;
    public TextField ccvTextField;
    public TextField expirationDateTextField;

    public void setup(String username){
        providerDTB = new ProviderDTB("src/main/resources/Databases/ProvidersDTB.json");
        customerDTB = new CustomerDTB("src/main/resources/Databases/CustomersDTB.json", username);
        //customerDTB = App.getInstance().getCustomerDTB();
        currentCustomer = customerDTB.getCurrentCustomer();

        chooseThisGameButton.disableProperty().bind(gameListListView.getSelectionModel().selectedItemProperty().isNull());

        loadData();
//        resetGameDataFields();
        updateGridAndList();
    }

    private void updateGridAndList() {


        //System.out.println(gameHolders);

        setGameListListView(providerDTB.getAllGamesFromDTB());
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
        balanceText.setText("" + currentCustomer.getMoney());

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

    public void chooseThisGameButton() {

        Game currentGame = providerDTB.getAllGamesFromDTB().get(0); //TODO: ceva sa facem sa fie asta initializat

        for (Provider i: providerDTB.getData()) {
            for (Game j: i.getGames()) {
                if (j.getName().equals(gameListListView.getSelectionModel().getSelectedItem()))
                    currentGame = j;
            }
        }

        if (currentCustomer.getMoney() < currentGame.getPrice())  {
            System.out.println("Not enough money");
            return;
        }

        addGameToLibrary(currentGame);


        String money = "" + (currentCustomer.getMoney() - currentGame.getPrice());
        money = money.substring(0, money.indexOf('.') + 2);

        currentCustomer.setMoney(Double.parseDouble(money));

        customerDTB.updateDatabase();

        balanceText.setText("" + currentCustomer.getMoney());

        browseGamesClick();

        seeLibraryClick();
        //TODO: add to transactions

    }

    public void addGameToLibrary(Game currentGame) {
        this.currentCustomer.addGame(currentGame);
        customerDTB.updateDatabase();
        setGamesGridPane(currentCustomer.getStringGameArray());
    }

    public void addMoneyToAccount() {

        String amount = amountTextField.getText();
        String creditCard = creditCardTextField.getText();
        String expirDate = expirationDateTextField.getText();
        String ccv = ccvTextField.getText();

        if ( !ProviderDTB.validDoubleValueInput(amount) ) {
            System.out.println("Invalid input value for the amount of money");
            return;
        }

        if ( !ProviderDTB.validCreditCard(creditCard) ) {
            System.out.println("Invalid credit card");
            return;
        }

        if ( !ProviderDTB.validExpirationDate(expirDate) ) {
            System.out.println("Invalid expiration date: [xy/ab]");
            return;
        }

        if ( !ProviderDTB.validCCV(ccv) ) {
            System.out.println("Invalid CCV");
            return;
        }

        System.out.println("Your balance has incremented by: " + amount);

        currentCustomer.setMoney(currentCustomer.getMoney() + Double.parseDouble(amount));

        customerDTB.updateDatabase();
        balanceText.setText("" + currentCustomer.getMoney());

        browseGamesClick();

        clearAddMoney();

    }

    public void clearAddMoney() {
        amountTextField.setText("");
        creditCardTextField.setText("");
        expirationDateTextField.setText("");
        ccvTextField.setText("");
    }

}
