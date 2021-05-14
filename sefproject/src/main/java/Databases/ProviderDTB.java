package Databases;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

public class ProviderDTB {

    List<Provider> data = new ArrayList<>();

    public void addProvider(Provider adding){
        data.add(adding);
    }

    public void printProviders(String filename) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);

        Files.write(Paths.get(filename), json.getBytes());
    }

    public void readProviders(String filename) throws IOException{

        Gson gson = new Gson();

        String content = Files.readString(Path.of(filename), StandardCharsets.US_ASCII);

        ProviderDTB t = gson.fromJson(content, ProviderDTB.class);

        for (Provider i: t.data) {
            addProvider(i);
        }
    }

    public void editProvider(int providerNumber, Provider add, String filename) throws IOException {

        this.data.remove(providerNumber);
        this.data.add(add);

        printProviders(filename);

    }

    @Override
    public String toString() {
        return "ProviderDTB{" +
                "data=" + data +
                '}';
    }
}