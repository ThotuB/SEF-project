package Components;

import java.util.Date;

public class Game {

    private String name;
    private double price;
    private String description;
    private boolean rent;

    private Date startDate;
    private Date endDate;

    private boolean bought;

    public Game(String name, double price, String description, Date dateStart, Date dateEnd, boolean rent) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.rent = rent;
        this.startDate = dateStart;
        this.endDate = dateEnd;
        this.bought = false;
    }

    // GETTERS
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

    public boolean getBought(){
        return this.bought;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
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
                ", bought=" + bought +
                '}';
    }
}
