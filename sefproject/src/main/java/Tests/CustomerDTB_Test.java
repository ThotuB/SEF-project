package Tests;

import Components.Customer;
import Components.Game;
import Databases.CustomerDTB;

import java.util.Calendar;
import java.util.Date;

public class CustomerDTB_Test {

    public static void main(String[] args) {

        Date date = new Date();

        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        date = cal.getTime();


        Game are = new Game("Alfa", 10, "A t", date, date, true);
        Game alinare = new Game("beta", 10, "A t ", date, date, true);

        Customer test = new Customer("Alin");

        test.addGame(are);
        test.addGame(alinare);
        test.setMoney(2000.2);

        CustomerDTB customerDTB = new CustomerDTB("src/main/resources/Databases/CustomersDTB.json");
        customerDTB.setCurrentCustomer(test.getName());

        customerDTB.setCurrentCustomer(test.getName());

        customerDTB.getCurrentCustomer().addGame(are);
        customerDTB.getCurrentCustomer().addGame(alinare);
        customerDTB.getCurrentCustomer().setMoney(200.0);
        customerDTB.updateDatabase();

        customerDTB.setCurrentCustomer("Oare apar aici");
        customerDTB.getCurrentCustomer().addGame(are);
        customerDTB.getCurrentCustomer().setMoney(10000.0);
        customerDTB.updateDatabase();

        System.out.println(customerDTB.getCurrentCustomer());
    }
}
