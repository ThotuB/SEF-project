package Components;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private final String name = "Customer Ales";
    private final double money = 34.5;
    private Game game = new Game("Game 1", 123.6, "Description of Game 1", new Date(2001, Calendar.MARCH, 16), new Date(2005, Calendar.DECEMBER, 2), false);

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(name);
        customer.setMoney(money);
    }

    @AfterEach
    void tearDown() {
        customer = null;
    }

    @Test
    void getMoney() {
        assertEquals(money, customer.getMoney());
    }

    @Test
    void getName() {
        assertEquals(name, customer.getName());
    }

    @Test
    void getLibrary() {
        ArrayList<Game> library = new ArrayList<>();
        library.add(game);
        customer.addGame(game);
        assertEquals(library, customer.getLibrary());
    }

    @Test
    void getGame() {
        customer.addGame(game);
        assertEquals(game, customer.getGame(game.getName()));
    }

    @Test
    void setName() {
        String newName = "Bonkus";
        customer.setName(newName);
        assertEquals(newName, customer.getName());
    }

    @Test
    void setLibrary() {
        ArrayList<Game> library = new ArrayList<>();
        library.add(game);
        customer.setLibrary(library);
        assertEquals(library, customer.getLibrary());
    }

    @Test
    void setMoney() {
        double newMoney = 34.2;
        customer.setMoney(newMoney);
        assertEquals(newMoney, customer.getMoney());
    }

    @Test
    void addGame() {
        customer.addGame(game);
        assertEquals(game, customer.getGame(game.getName()));
    }

    @Test
    void removeGame() {
        customer.addGame(game);
        customer.removeGame(game.getName());
        assertTrue(customer.getLibrary().isEmpty());
    }
}