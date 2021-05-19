package Databases;

import Components.Customer;
import Components.Game;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDTBTest {
    Customer customer1 = new Customer("Bob");
    Customer customer2 = new Customer("Alex");

    CustomerDTB customerDTB;

    @BeforeEach
    void setUp() {
        Date date;

        Calendar calendar = Calendar.getInstance();

        calendar.set(2004, Calendar.MAY, 12);
        date = calendar.getTime();

        Game game1 = new Game("Game 1", 12, "Description for game 1", date, date, true);
        Game game2 = new Game("Game 2", 43, "Description for game 2 ", date, date, false);
        Game game3 = new Game("Game 3", 21, "Description for game 3 ", date, date, false);

        customer1.addGame(game1);
        customer1.addGame(game2);

        customer2.addGame(game3);

        customerDTB = new CustomerDTB();
    }

    @AfterEach
    void tearDown() {
        customerDTB = null;
    }

    @Test
    void setCurrentCustomer() {
        customerDTB.add(customer1);
        customerDTB.setCurrentCustomer(customer1.getName());
        assertEquals(customer1, customerDTB.getCurrentCustomer());
    }

    @Test
    void getCustomer() {
        customerDTB.add(customer1);
        customerDTB.setCurrentCustomer(customer1.getName());
        assertNull(customerDTB.getCustomer(customer2.getName()));
    }

    @Test
    void add() {
        customerDTB.add(customer1);
        customerDTB.add(customer1);
        assertEquals(2, customerDTB.getData().size());
    }

    @Test
    void getCurrentCustomer() {
        customerDTB.add(customer1);
        customerDTB.setCurrentCustomer(customer1.getName());
        assertEquals(customer1, customerDTB.getCurrentCustomer());
    }
}