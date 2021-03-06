package Databases;

import Components.Game;
import Components.Provider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

public class ProviderDTB {
    private ArrayList<Provider> data = new ArrayList<>();
    private Provider currentProvider;
    private final String path;

    public ProviderDTB(){
        path = "";
    }

    public ProviderDTB(String path){
        this.path = new File(path).getAbsolutePath();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            data = new Gson().fromJson(reader, new TypeToken<ArrayList<Provider>>() {}.getType());
            reader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // GETTERS
    public ArrayList<Provider> getData() {
        return data;
    }

    public Provider getProvider(String username){
        if ( data == null ){
            return null;
        }
        for (Provider provider: data){
            if ( provider.getName().equals(username) ){
                return provider;
            }
        }

        return null;
    }

    public ArrayList<Game> getAllGamesFromDTB() {
        ArrayList<Game> allGames = new ArrayList<>();

        for (Provider i : data) {
            allGames.addAll(i.getGames());
        }

        return allGames;
    }

    public ArrayList<Game> getAvailableGamesFromDTB() {
        ArrayList<Game> availableGames = new ArrayList<>();

        for (Provider provider : data) {
            for (Game game : provider.getGames()){
                if ( !game.getBought() ){
                    availableGames.add(game);
                }
            }
        }

        return availableGames;
    }

    public Provider getCurrentProvider() {
        return currentProvider;
    }

    // SETTER
    public void setCurrentProvider(String username) {
        currentProvider = getProvider(username);

        if ( currentProvider == null ){
            currentProvider = new Provider(username);

            add(currentProvider);
            updateDatabase();
        }
    }

    // VALIDATION
    public static boolean validGameName(String name){
        return !name.isBlank();
    }

    public static boolean validDoubleValueInput(String price){
        return (price.matches("[0-9.]+") && price.length() > 0);
    }

    public static boolean validGameDates(Date startDate, Date endDate){
        return startDate.before(endDate);
    }

    public static boolean validDescription(String description){
        return description.length() >= 10;
    }

    public static boolean validCreditCard (String creditCard) {
        return (creditCard.matches("[0-9-]+") &&  creditCard.length() == 19);
    }

    public static boolean validExpirationDate (String expDate) {
        return expDate.matches("[0-1][0-9]/[0-9][0-9]");
    }

    public static boolean validCCV (String ccv) {
        return ccv.matches("[0-9]+") && ccv.length() == 3;
    }

    // DATABASE OPTIONS
    public void add(Provider provider){
        data.add(provider);
    }

    public void remove(Provider provider) {
        data.removeIf(prov -> prov.getName().equals(provider.getName()));
    }

    public void updateDatabase() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
            String json = gson.toJson(data);

            writer.write(json);
            writer.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "ProviderDTB{" +
                "data=" + data +
                '}';
    }
}