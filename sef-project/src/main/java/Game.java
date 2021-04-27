import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Game {

    private String name;
    private double price;
    private String description;
    private boolean rent;

    private Date startDate = new Date();
    private Date endDate = new Date();


    //private picture??

    public Game(String name, double price, String description, String dateStart, String dateEnd, boolean rent) {

        this.name = name;
        this.price = price;
        this.description = description;
        this.rent = rent;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            startDate = dateFormat.parse(dateStart);
            endDate = dateFormat.parse(dateEnd);
        }
        catch (ParseException e) {
            System.out.println("Parse exception encountered while trying to format date");
        }

    }

    @Override
    public String toString() {
        return "Game{" +
                "name = '" + name + '\'' +
                ", price = " + price +
                ", description = '" + description + '\'' +
                ", startDate = " + startDate +
                ", endDate = " + endDate +
                '}';
    }
}
