import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


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

    public void printJson(String filename) throws IOException {

        JSONObject jobj = new JSONObject();
        JSONArray ja = new JSONArray();

        Map<String, java.io.Serializable> m = new LinkedHashMap<>(5);

        for (Game g: this.games) {
            m.put("name", g.getName());
            m.put("price", g.getPrice());
            m.put("description", g.getDescription());
            m.put("startDate", g.getStartDate());
            m.put("endDate", g.getEndDate());
        }

        ja.put(m);

        jobj.put("name", this.name);
        jobj.put("games", ja);

        Files.write(Paths.get(filename), jobj.toString().getBytes());

        PrintWriter pw = new PrintWriter("ceva.json");
        pw.write(jobj.toString());

        pw.flush();
        pw.close();

    }


}
