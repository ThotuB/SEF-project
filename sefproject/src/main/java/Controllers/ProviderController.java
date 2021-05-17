package Controllers;

import Components.Game;
import Components.Provider;
import Databases.ProviderDTB;
import Main.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class ProviderController {

    @FXML
    private Text provNameLabel;
    public GridPane gamesGridPane;
    public Button newOfferButton;

    public AnchorPane welcomeAnchorPane;
    public AnchorPane newOfferAnchorPane;
    public AnchorPane changeOfferAnchorPane;

    public TextField gameNameTextField;
    public TextField priceTextField;
    public TextField descriptionTextField;
    public DatePicker startDateDatePicker;
    public DatePicker endDateDatePicker;
    public ChoiceBox<String> choiceBoxo;

    public ListView<String> gameListListView;

    ProviderDTB providerDTB;
    Provider currentProvider;

    // CONSTRUCTOR-ISH
    public void setup(String username){
        providerDTB = App.getInstance().getProviderDTB();
        currentProvider = providerDTB.getCurrentProvider();

        provNameLabel.setText(username);
        loadData();
        resetGameDataFields();
        updateGridAndList();
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

    public void setGameListListView(ArrayList<String> listViewStr) {
        ObservableList<String> observableList = FXCollections.observableArrayList();

        observableList.removeAll();
        observableList.addAll(listViewStr);

        gameListListView.getItems().clear();
        gameListListView.getItems().addAll(observableList);
    }

    // ACTIONS
    public void newOfferClick() {
        newOfferAnchorPane.setVisible(true);
        welcomeAnchorPane.setVisible(false);
        changeOfferAnchorPane.setVisible(false);
    }

    public void seeGamesClick() {
        welcomeAnchorPane.setVisible(true);
        newOfferAnchorPane.setVisible(false);
        changeOfferAnchorPane.setVisible(false);
    }

    public void changeOfferClick() {
        changeOfferAnchorPane.setVisible(true);
        newOfferAnchorPane.setVisible(false);
        welcomeAnchorPane.setVisible(false);

    }

    public void changeThisOfferButtonClick() {
        Game game = currentProvider.getGame(gameListListView.getSelectionModel().getSelectedItem());
        
        gameNameTextField.setText( game.getName() );
        priceTextField.setText( String.valueOf(game.getPrice()) );
        descriptionTextField.setText( game.getDescription() );
        
        startDateDatePicker.setValue(convertToLocalDate( game.getStartDate() ));
        endDateDatePicker.setValue(convertToLocalDate( game.getEndDate() ));
        choiceBoxo.setValue( String.valueOf(game.getRent()) );

        currentProvider.removeGame(gameListListView.getSelectionModel().getSelectedItem());

        providerDTB.updateDatabase();

        updateGridAndList();

        newOfferClick();

    }

    public void makeNewOfferButtonClick() {
        String gName = gameNameTextField.getText();
        String priceStr = priceTextField.getText();
        double price;
        Date startDate = java.sql.Date.valueOf(startDateDatePicker.getValue());;
        Date endDate = java.sql.Date.valueOf(endDateDatePicker.getValue());
        boolean rent = Boolean.parseBoolean(choiceBoxo.getValue());
        String description = descriptionTextField.getText();

        Calendar cal = Calendar.getInstance();

        cal.setTime(startDate);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        startDate = cal.getTime();

        cal.setTime(endDate);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        endDate = cal.getTime();

        if ( !ProviderDTB.validGameName(gName) ){
            System.out.println("Invalid input: game name");
            return;
        }

        if ( !ProviderDTB.validGamePrice(priceStr) ) {
            System.out.println("Invalid input: price");
            return;
        }
        price = Double.parseDouble(priceStr);

        if ( !ProviderDTB.validGameDates(startDate, endDate) ) {
            System.out.println("Invalid input: dates");
            return;
        }

        if ( !ProviderDTB.validDescription(description) ){
            System.out.println("Invalid input: description");
            return;
        }

        Game game = new Game(gName, price, description, startDate, endDate, rent);

        System.out.println("Game " + gName + " successfully added!");

        currentProvider.addGame(game);

        providerDTB.updateDatabase();

        updateGridAndList();

        seeGamesClick();
        resetGameDataFields();
    }

    // HELPER
    public static LocalDate NOW_LOCAL_DATE (){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date , formatter);
    }

    public LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private void updateGridAndList() {
        setGamesGridPane(currentProvider.getStringGameArray());
        setGameListListView(currentProvider.getStringGameArray());
    }

    private void loadData() {
        choiceBoxo.getItems().removeAll(choiceBoxo.getItems());
        choiceBoxo.getItems().addAll("true", "false");
    }

    public void resetGameDataFields() {
        gameNameTextField.setText("");
        priceTextField.setText("");
        descriptionTextField.setText("");
        startDateDatePicker.setValue(NOW_LOCAL_DATE());
        endDateDatePicker.setValue(NOW_LOCAL_DATE());
        choiceBoxo.setValue("true");
    }

}
