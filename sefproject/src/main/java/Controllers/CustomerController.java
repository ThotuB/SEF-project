package Controllers;

import Components.Customer;
import Components.Game;
import Components.Provider;
import Databases.CustomerDTB;
import Databases.ProviderDTB;
import Main.App;
import javafx.application.Platform;
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
import java.util.concurrent.TimeUnit;

public class CustomerController {
    CustomerDTB customerDTB;
    Customer currentCustomer;
    ProviderDTB providerDTB;

    @FXML
    public GridPane gamesGridPane;

    public Text customerNameLabel;
    public Text balanceText;

    public ListView<String> gameListListView;
    public ListView<String> gameListViewPrice;
    public ListView<String> gameListViewPeriod;
    public ListView<String> gameTransactionsListView;
    public ListView<String> gameTransactionsPriceListView;
    public ListView<String> gameTransactionsTimeLeftListView;

    public AnchorPane addMoneyAnchorPane;
    public AnchorPane libraryAnchorPane;
    public AnchorPane transactionsAnchorPane;
    public AnchorPane browseGamesAnchorPane;

    public Button chooseThisGameButton;

    public TextField amountTextField;
    public TextField creditCardTextField;
    public TextField ccvTextField;
    public TextField expirationDateTextField;

    public void setup(ProviderDTB providerDTB, CustomerDTB customerDTB){
        this.providerDTB = providerDTB;
        this.customerDTB = customerDTB;

        currentCustomer = customerDTB.getCurrentCustomer();

        chooseThisGameButton.disableProperty().bind(gameListListView.getSelectionModel().selectedItemProperty().isNull());

        loadData();
        updateGridAndList();

        setCVVTextFieldListener();
        setExpirationDateTextFieldListener();
        setCreditCardTextFieldListener();
        setGameTransactionsListView();

    }

    /// SETTERS
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

    public void setGameTransactionsListView() {

        ObservableList<String> observableListTransactions = FXCollections.observableArrayList();
        ObservableList<String> observableListTransactionsPrice = FXCollections.observableArrayList();
        ObservableList<String> observableListTransactionsTimeLeft = FXCollections.observableArrayList();

        long diffInMillis;
        long diff;

        for (Game i: currentCustomer.getLibrary()) {
            observableListTransactions.add(i.getName());
            observableListTransactionsPrice.add("$" + i.getPrice());

            if (i.getRent()) {
                diffInMillis = Math.abs(i.getEndDate().getTime() - i.getStartDate().getTime());
                diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
                observableListTransactionsTimeLeft.add(diff + " days");
            }
            else observableListTransactionsTimeLeft.add("" + '\u221e');
        }


        gameTransactionsListView.getItems().clear();
        gameTransactionsPriceListView.getItems().clear();
        gameTransactionsTimeLeftListView.getItems().clear();

        gameTransactionsListView.getItems().addAll(observableListTransactions);
        gameTransactionsPriceListView.getItems().addAll(observableListTransactionsPrice);
        gameTransactionsTimeLeftListView.getItems().addAll(observableListTransactionsTimeLeft);
    }

    public void setGameListListView(ArrayList<Game> games) {
        ObservableList<String> observableListGameName = FXCollections.observableArrayList();
        ObservableList<String> observableListGamePrice = FXCollections.observableArrayList();
        ObservableList<String> observableListGamePeriod = FXCollections.observableArrayList();

        observableListGameName.removeAll();
        observableListGamePrice.removeAll();
        observableListGamePeriod.removeAll();

        for (Game game: games) {
            String name = game.getName();
            double price = game.getPrice();
            String date = game.getStartDate().toString().substring(0,10) + " -> ";

            if (game.getRent()) {
                date += game.getEndDate().toString().substring(0,10);
            }
            else {
                date += "\u221e";
            }

            observableListGameName.add(name);
            observableListGamePrice.add("$" + price);
            observableListGamePeriod.add( date );
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

    /// MOVE BETWEEN PANES
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
        setGameTransactionsListView();
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

    public void logOutAction(){
        App.getInstance().gotoLogout();
    }

    /// ACTIONS
    public void chooseThisGameButton() {
        Game currentGame = providerDTB.getAllGamesFromDTB().get(0); //TODO: ceva sa facem sa fie asta initializat

        for (Provider provider: providerDTB.getData()) {
            for (Game game: provider.getGames()) {
                if ( game.getName().equals(gameListListView.getSelectionModel().getSelectedItem()) ) {
                    currentGame = game;
                }
            }
        }

        if ( currentCustomer.getMoney() < currentGame.getPrice() )  {
            System.out.println("Not enough money");
            return;
        }

        currentGame.setBought(true);
        providerDTB.updateDatabase();
        addGameToLibrary(currentGame);
        updateGridAndList();

        String money = "" + (currentCustomer.getMoney() - currentGame.getPrice());
        money = money.substring(0, money.indexOf('.') + 2);

        currentCustomer.setMoney(Double.parseDouble(money));

        customerDTB.updateDatabase();

        balanceText.setText("$" + currentCustomer.getMoney());

        browseGamesClick();

        seeLibraryClick();
        //TODO: add to transactions

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
        balanceText.setText("$" + currentCustomer.getMoney());

        browseGamesClick();

        clearAddMoney();

    }


    public void setCVVTextFieldListener(){
        ccvTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if ( newValue.length() > 3 ){
                ccvTextField.setText(oldValue);
                ccvTextField.positionCaret(3);
            }
        });
    }

    // OTHER
    public void setExpirationDateTextFieldListener(){
        expirationDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if ( newValue.length() > 5 ){
                expirationDateTextField.setText(oldValue);
                expirationDateTextField.positionCaret(5);
                return;
            }

            int addFlag = newValue.length() - oldValue.length();

            if ( addFlag > 0 ){
                String text = "";
                for (int i = 0 ; i < newValue.length() ; i++){
                    if ( text.length() == 2 ){
                        text += "/";
                    }
                    if ( Character.isDigit( newValue.charAt(i) ) ){
                        text += newValue.charAt(i);
                    }
                }

                String finalText = text;
                Platform.runLater(() -> {
                    int pos = expirationDateTextField.getCaretPosition();
                    expirationDateTextField.setText(finalText);
                    if ( pos == 3 ) { pos++; }
                    expirationDateTextField.positionCaret(pos);
                });
            }
        });
    }

    public void setCreditCardTextFieldListener(){
        creditCardTextField.textProperty().addListener((observable, oldValue, newValue) ->{
            if ( newValue.length() > 19 ){
                creditCardTextField.setText(oldValue);
                creditCardTextField.positionCaret(19);
                return;
            }

            int addFlag = newValue.length() - oldValue.length();

            if ( addFlag > 0 ){
                String text = "";
                for (int i = 0 ; i < newValue.length() ; i++){
                    if ( text.length() == 4 || text.length() == 9 || text.length() == 14 ){
                        text += "-";
                    }
                    if ( Character.isDigit( newValue.charAt(i) ) ){
                        text += newValue.charAt(i);
                    }
                }

                String finalText = text;
                Platform.runLater(() -> {
                    int pos = creditCardTextField.getCaretPosition();
                    creditCardTextField.setText(finalText);
                    if ( pos == 5 || pos == 10 || pos == 15 ) { pos++; }
                    creditCardTextField.positionCaret(pos);
                });
            }
        });


    }

     private void updateGridAndList() {
            setGameListListView(providerDTB.getAvailableGamesFromDTB());
            setGamesGridPane(currentCustomer.getStringGameArray());
     }

    public void loadData() {
        customerNameLabel.setText(currentCustomer.getName());
        balanceText.setText("$" + currentCustomer.getMoney());
    }

    public void addGameToLibrary(Game currentGame) {
        this.currentCustomer.addGame(currentGame);
        customerDTB.updateDatabase();
        setGamesGridPane(currentCustomer.getStringGameArray());
    }

    public void clearAddMoney() {
        amountTextField.setText("");
        creditCardTextField.setText("");
        expirationDateTextField.setText("");
        ccvTextField.setText("");
    }



}
