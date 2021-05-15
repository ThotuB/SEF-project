package Databases;

import Components.Provider;
import Components.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProviderDTB {

    List<Provider> data = new ArrayList<>();
    private final String path = "src/main/resources/Databases/ProvidersDTB.json";

    public void addProvider(Provider adding){
        data.add(adding);
    }

    public List<Provider> getData() {
        return data;
    }

    public void editProviderDTB(Provider adding) {

    }

    public void printProviders() {

        try {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(this);

            Files.write(Paths.get(path), json.getBytes());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void readProviders(){

        try {
            Gson gson = new Gson();

            String content = Files.readString(Path.of(path), StandardCharsets.US_ASCII);

            ProviderDTB t = gson.fromJson(content, ProviderDTB.class);

            for (Provider i: t.data) {
                addProvider(i);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void editProvider(Provider add) {

        data.removeIf(i -> i.getName().equals(add.getName()));
        this.data.add(add);

    }

    @Override
    public String toString() {
        return "ProviderDTB{" +
                "data=" + data +
                '}';
    }
}