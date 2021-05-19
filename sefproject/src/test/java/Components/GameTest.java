package Components;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private String name = "Game 1";
    private double price = 123.6;
    private String description = "Description of Game 1";
    private Date startDate;
    private Date endDate;

    private String newName = "Game 2";
    private double newPrice = 12.96;
    private String newDescription = "Description of Game 2";
    private Date newStartDate;
    private Date newEndDate;

    private Game game;

    @BeforeEach
    void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.JANUARY, 4);
        startDate = calendar.getTime();
        calendar.set(2004, Calendar.FEBRUARY, 12);
        endDate = calendar.getTime();
        calendar.set(2012, Calendar.MAY, 3);
        newStartDate = calendar.getTime();
        calendar.set(4354, Calendar.MARCH, 6);
        newEndDate = calendar.getTime();

        game = new Game(name, price,description , startDate, endDate, false);
    }

    @AfterEach
    void tearDown() {
        game = null;
    }

    @Test
    void getName() {
        assertEquals(name, game.getName());
    }

    @Test
    void getDescription() {
        assertEquals(description, game.getDescription());
    }

    @Test
    void getPrice() {
        assertEquals(price, game.getPrice());
    }

    @Test
    void getRent() {
        assertFalse(game.getRent());
    }

    @Test
    void getStartDate() {
        assertEquals(startDate, game.getStartDate());
    }

    @Test
    void getEndDate() {
        assertEquals(endDate, game.getEndDate());
    }

    @Test
    void getBought() {
        assertFalse(game.getBought());
    }

    @Test
    void setName() {
        game.setName(newName);
        assertEquals(newName, game.getName());
    }

    @Test
    void setPrice() {
        game.setPrice(newPrice);
        assertEquals(newPrice, game.getPrice());
    }

    @Test
    void setDescription() {
        game.setDescription(newDescription);
        assertEquals(newDescription, game.getDescription());
    }

    @Test
    void setRent() {
        game.setRent(true);
        assertTrue(game.getRent());
    }

    @Test
    void setStartDate() {
        game.setStartDate(newStartDate);
        assertEquals(newStartDate, game.getStartDate());
    }

    @Test
    void setEndDate() {
        game.setEndDate(newEndDate);
        assertEquals(newEndDate, game.getEndDate());
    }

    @Test
    void setBought() {
        game.setBought(true);
        assertTrue(game.getBought());
    }
}