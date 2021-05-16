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

    public Game getGame(String name) {
        for (Game i: this.games) {
            if (i.getName().equals(name))
                return i;
        }
        return null;
    }

    public void addGame (Game g) {
        games.add(g);
    }

    @Override
    public String toString() {
        return "Provider{" +
                "name='" + name + '\'' +
                ", games=" + games +
                '}';
    }

    public ArrayList<String> getStringGameArray(){
        ArrayList<String> gameNames = new ArrayList<>();

        for (Game i : this.games) {
            gameNames.add(i.getName());
        }
        return gameNames;
    }

    public String getName() {
        return this.name;
    }

    public void removeGame(String name) {

        this.games.removeIf(i -> i.getName().equals(name));

    }
}