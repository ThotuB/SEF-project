import java.io.IOException;

public class TestClass {

    public static void main(String[] args) throws IOException {

        Game x = new Game("Extra Small", 10, "This is a description for game number 1.", "12-12-2012", "31-01-2929", true);
        Game y = new Game("Extra Large", 142332, "This is a description for game number 2, which is longer than the description for game number one, using lots of characters because we have 16 gbs of ram and a lot of memory yes yes rucnalup.", "01-12-2021", "01-03-2023", true);


        Provider e = new Provider("Jador");
        e.addGame(x);
        e.addGame(y);

        Provider f = new Provider("thot");
        f.addGame(x);
        f.addGame(y);

        ProviderDTB alfa = new ProviderDTB();

        alfa.addProvider(e);
        alfa.addProvider(f);

        alfa.printProviders("src\\main\\resources\\databases\\ProvidersDTB.json");

    }
}