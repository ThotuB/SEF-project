package Components;

import java.util.ArrayList;

public class Customer {
    private String name;
    private ArrayList<Game> library;

    public Customer(String name)  {
        this.name = name;
        library = new ArrayList<>();
    }

    // GETTERS
    public String getName() {
        return this.name;
    }

    public ArrayList<Game> getLibrary() {
        return this.library;
    }

    public Game getGame(String name) {
        for (Game game: library) {
            if ( game.getName().equals(name) ) {
                return game;
            }
        }
        return null;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setLibrary(ArrayList<Game> library) {
        this.library = library;
    }

    // GAME OPTIONS
    public void addGame(Game game) {
        library.add(game);
    }

    public void removeGame(String name) {
        library.removeIf(i -> i.getName().equals(name));
    }

    public ArrayList<String> getStringGameArray(){
        ArrayList<String> gameNames = new ArrayList<>();

        for (Game game : library) {
            gameNames.add( game.getName() );
        }
        return gameNames;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "name='" + name + '\'' +
                ", library=" + library +
                '}';
    }
}
