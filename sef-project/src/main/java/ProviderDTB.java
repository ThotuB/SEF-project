import com.google.gson.Gson;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProviderDTB {

    List<Provider> data = new ArrayList<>();

    public void addProvider(Provider adding){
        data.add(adding);
    }

    public void printProviders(String filename) throws IOException {

        Gson gson = new Gson();
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

    @Override
    public String toString() {
        return "ProviderDTB{" +
                "data=" + data +
                '}';
    }
}
