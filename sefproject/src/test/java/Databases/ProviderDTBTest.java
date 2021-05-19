package Databases;

import Components.Game;
import Components.Provider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProviderDTBTest {
    Provider provider1 = new Provider("Provider 1");
    Provider provider2 = new Provider("Provider 2");

    Game game1;
    Game game2;
    Game game3;

    ProviderDTB providerDTB;

    @BeforeEach
    void setUp() {
        providerDTB = new ProviderDTB();
        Date date;

        Calendar calendar = Calendar.getInstance();

        calendar.set(2004, Calendar.MAY, 12);
        date = calendar.getTime();

        game1 = new Game("Game 1", 12, "Description for game 1", date, date, true);
        game1.setBought(true);
        game2 = new Game("Game 2", 43, "Description for game 2 ", date, date, false);
        game3 = new Game("Game 3", 21, "Description for game 3 ", date, date, false);

        provider1.addGame(game1);

        provider2.addGame(game2);
        provider2.addGame(game3);
    }

    @AfterEach
    void tearDown() {
        providerDTB = null;
    }

    @Test
    void getProvider() {
        providerDTB.add(provider1);
        assertNull(providerDTB.getProvider(provider2.getName()));
    }

    @Test
    void getAllGamesFromDTB() {
        providerDTB.add(provider1);
        providerDTB.add(provider2);
        ArrayList<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);
        games.add(game3);
        assertEquals(games, providerDTB.getAllGamesFromDTB());
    }

    @Test
    void getAvailableGamesFromDTB() {
        providerDTB.add(provider1);
        providerDTB.add(provider2);
        ArrayList<Game> games = new ArrayList<>();
        games.add(game2);
        games.add(game3);
        assertEquals(games, providerDTB.getAvailableGamesFromDTB());
    }

    @Test
    void getCurrentProvider() {
        providerDTB.add(provider1);
        providerDTB.setCurrentProvider(provider1.getName());
        assertEquals(provider1, providerDTB.getCurrentProvider());
    }

    @Test
    void setCurrentProvider() {
        providerDTB.add(provider1);
        providerDTB.setCurrentProvider(provider1.getName());
        assertEquals(provider1, providerDTB.getCurrentProvider());
    }

    @Test
    void validGameName() {
        assertTrue(ProviderDTB.validGameName(game1.getName()));
    }

    @Test
    void validDoubleValueInput() {
        assertTrue(ProviderDTB.validDoubleValueInput("" + game1.getPrice()));
    }

    @Test
    void validGameDates() {
        assertFalse(ProviderDTB.validGameDates(game1.getStartDate(), game1.getEndDate()));
    }

    @Test
    void validDescription() {
        assertFalse(ProviderDTB.validDoubleValueInput( game1.getDescription()) );
    }

    @Test
    void validCreditCard() {
        String creditCard = "1111-2222-3333-4444";
        assertTrue(ProviderDTB.validCreditCard(creditCard));
    }

    @Test
    void validExpirationDate() {
        String expirationDate = "11/23";
        assertTrue(ProviderDTB.validExpirationDate(expirationDate));
    }

    @Test
    void validCCV() {
        String cvv = "234";
        assertTrue(ProviderDTB.validCCV(cvv));
    }

    @Test
    void add() {
        providerDTB.add(provider1);
        assertEquals(1, providerDTB.getData().size());
    }

    @Test
    void remove() {
        providerDTB.add(provider1);
        providerDTB.remove(provider1);
        assertTrue(providerDTB.getData().isEmpty());
    }
}