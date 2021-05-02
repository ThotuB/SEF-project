import java.io.IOException;

public class TestClass {

    public static void main(String[] args) throws IOException {

        Game x = new Game("E", 12, "descr", "12-12-2012", "31-01-2929", true);
        Game y = new Game("F", 1432, "dsfsfsfsefewfw", "12-12-2012", "31-01-2929", true);


        Provider e = new Provider("name1");
        e.addGame(x);
        e.addGame(y);

        Provider f = new Provider("name2");

        Provider g = new Provider("name2");

        g.addGame(x);
        g.addGame(x);
        g.addGame(y);

//        e.printJsonProvider("dtb_resources\\test2.json");
//        f.printJsonProvider("dtb_resources\\test2.json");
//        g.printJsonProvider("dtb_resources\\test2.json");

        ProviderDTB prov = new ProviderDTB();

        prov.addProvider(e);
        prov.addProvider(f);
        prov.addProvider(g);

        prov.printProviders("src\\main\\resources\\ProvidersDTB.json");

        ProviderDTB p2 = new ProviderDTB();

        p2.readProviders("src\\main\\resources\\ProvidersDTB.json");

        System.out.println("\nRead provider: \n" + p2);

    }
}
