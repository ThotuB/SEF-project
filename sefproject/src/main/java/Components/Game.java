package Components;

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

    public String getName() {
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public double getPrice() {
        return price;
    }

    public boolean getRent() {
        return rent;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    //private picture??

    public Game(String name, double price, String description, Date dateStart, Date dateEnd, boolean rent) {

        this.name = name;
        this.price = price;
        this.description = description;
        this.rent = rent;
        this.startDate = dateStart;
        this.endDate = dateEnd;

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        try {
//            startDate = dateFormat.parse(dateStart);
//            endDate = dateFormat.parse(dateEnd);
//        }
//        catch (ParseException e) {
//            System.out.println("Parse exception encountered while trying to format date");
//        }

    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", rent=" + rent +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
