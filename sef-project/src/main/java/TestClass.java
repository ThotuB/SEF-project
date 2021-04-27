import java.io.IOException;

public class TestClass {

    public static void main(String[] args) throws IOException {

        Game x = new Game("E", 12, "descr", "12-12-2012", "31-01-2929", true);
        Game y = new Game("E", 12, "descr", "12-12-2012", "31-01-2929", true);



        Provider e = new Provider("name1");
        e.addGame(x);
        e.addGame(y);

        System.out.println(e);

        e.printJson("dtb_resources\\test2.json");

    }


}
