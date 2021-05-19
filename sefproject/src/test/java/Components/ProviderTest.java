package Components;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProviderTest {
    private final String name = "Provider Bob";
    private Game game = new Game("Game 1", 123.6, "Description of Game 1", new Date(2001, Calendar.MARCH, 16), new Date(2005, Calendar.DECEMBER, 2), false);

    private Provider provider;

    @BeforeEach
    void setUp() {
        provider = new Provider(name);
    }

    @AfterEach
    void tearDown() {
        provider = null;
    }

    @Test
    void contains() {
        provider.addGame(game);
        assertEquals(game, provider.contains(game.getName()));
    }

    @Test
    void getName() {
        assertEquals(name, provider.getName());
    }

    @Test
    void getGames() {
        ArrayList<Game> games = new ArrayList<>();
        games.add(game);
        provider.addGame(game);
        assertEquals(games, provider.getGames());
    }

    @Test
    void getGame() {
        provider.addGame(game);
        assertEquals(game, provider.getGame(game.getName()));
    }

    @Test
    void setName() {
        String newName = "New name";
        provider.setName(newName);
        assertEquals(newName, provider.getName());
    }

    @Test
    void setGames() {
        ArrayList<Game> games = new ArrayList<>();
        games.add(game);
        provider.setGames(games);
        assertEquals(games, provider.getGames());
    }

    @Test
    void addGame() {
        provider.addGame(game);
        assertEquals(game, provider.getGame(game.getName()));
    }

    @Test
    void removeGame() {
        provider.addGame(game);
        provider.removeGame(game.getName());
        assertTrue(provider.getGames().isEmpty());
    }
}