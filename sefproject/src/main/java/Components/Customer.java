package Components;

import java.util.ArrayList;

public class Customer {

    private String name;
    private ArrayList<Game> library;
    private double money;

    public Customer(String name)  {
        this.name = name;
        library = new ArrayList<>();
    }

    // GETTERS

    public double getMoney() {
        return money;
    }

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

    public void setMoney(Double money) {
        this.money = money;
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
        return "Customer{" +
                "name='" + name + '\'' +
                ", library=" + library +
                '}';
    }
}
