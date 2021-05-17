package Components;

import Components.Game;
import com.google.gson.Gson;

import java.util.ArrayList;


public class Provider {

    private String name;
    private ArrayList<Game> games;

    public Provider (String name)  {
        this.name = name;
        games = new ArrayList<>();
    }

    // GETTERS
    public String getName() {
        return this.name;
    }

    public ArrayList<Game> getGames() {
        return this.games;
    }

    public Game getGame(String name) {
        for (Game game: games) {
            if ( game.getName().equals(name) ) {
                return game;
            }
        }
        return null;
    }

    // SETTERS
    public void getName(String name) {
        this.name = name;
    }

    public void getGames(ArrayList<Game> games) {
        this.games = games;
    }

    // GAME OPTIONS
    public void addGame(Game game) {
        games.add(game);
    }

    public void removeGame(String name) {
        this.games.removeIf(i -> i.getName().equals(name));
    }

    public ArrayList<String> getStringGameArray(){
        ArrayList<String> gameNames = new ArrayList<>();

        for (Game game : games) {
            gameNames.add( game.getName() );
        }
        return gameNames;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "name='" + name + '\'' +
                ", games=" + games +
                '}';
    }
}