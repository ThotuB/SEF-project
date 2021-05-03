import com.google.gson.Gson;

import java.util.ArrayList;


public class Provider {

    private String name;
    private ArrayList<Game> games;

    public Provider (String name)  {
        this.name = name;
        games = new ArrayList<>();
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

    public String getName() {
        return this.name;
    }

    /*public void printJsonProvider(String filename) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);

        Files.write(Paths.get(filename), json.getBytes(), StandardOpenOption.APPEND);
    }*/

    public static Provider readJsonProvider(String json) {
        Gson gson = new Gson();

        return gson.fromJson(json, Provider.class);
    }
}