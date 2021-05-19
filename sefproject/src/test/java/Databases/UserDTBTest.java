package Databases;

import Components.Customer;
import Components.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDTBTest {
    String username1 = "Bobus";
    String email1 = "bob@gmail.com";
    String password1 = "AbC123";

    String username2 = "Alexx";
    String email2 = "alex@yahoo.com";
    String password2 = "qwertY23";

    User user1 = new User(username1, email1, password1);
    User user2 = new User(username2, email2, password2);

    UserDTB userDTB;

    @BeforeEach
    void setUp() {
        userDTB = new UserDTB();
        userDTB.add(user1);
    }

    @AfterEach
    void tearDown() {
        userDTB = null;
    }

    @Test
    void getUser() {
        assertEquals(user1, userDTB.getUser(user1));
    }

    @Test
    void validUsername() {
        assertTrue(UserDTB.validUsername(user1));
    }

    @Test
    void validEmail() {
        assertTrue(UserDTB.validEmail(user1));
    }

    @Test
    void validPassword() {
        assertTrue(UserDTB.validPassword(user1));
    }

    @Test
    void existsUser() {
        assertFalse(userDTB.existsUser(user2));
    }

    @Test
    void existsUsernameOrEmail() {
        assertFalse(userDTB.existsUsernameOrEmail(user2));
    }

    @Test
    void remove() {
        userDTB.remove(username1);
        assertTrue(userDTB.getData().isEmpty());
    }

    @Test
    void add() {
        userDTB.add(user2);
        assertEquals(2, userDTB.getData().size());
    }
}