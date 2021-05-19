package Components;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private final String username1 = "bob";
    private final String email1 = "bob@mail.com";
    private final String password1 = "password";
    private final String salt1 = "3h:/&";
    private final String passwordHashed1 = "c526bb5d9ca09cd7979ddf544cc902255bebf6dc20dc044f3033f08ce46e390b";

    private final String username2 = "alex";
    private final String email2 = "alex@mail.com";
    private final String password2 = "apaplata";
    private final String salt2 = "12345";
    private final String passwordHashed2 = "6ff94243990dbb2e51e66667ac607cc11df044766aba1856136c6f2f5db53195";

    private final String newUsername = "boberiu";
    private final String newEmail = "hmm@mail.com";
    private final String newPassword = "pass123";
    private final String newSalt = "fsda4";
    private final String newPasswordHashed = "0c1eeb5b358a8c790e613f1db11dfc1da703a47c06ddd9e7b0667d7c667d39ee";

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = new User(username1, email1, password1);
        user1.setSalt(salt1);
        user1.setPasswordHashed();

        user2 = new User(username2, email2, password2);
        user2.setSalt(salt2);
        user2.setPasswordHashed();
        user2.setProviderFlag(true);
    }

    @AfterEach
    void tearDown(){
        user1 = null;
        user2 = null;
    }

    @Test
    void getUsername() {
        assertAll(
                () -> assertEquals(username1, user1.getUsername()),
                () -> assertEquals(username2, user2.getUsername())
        );
    }

    @Test
    void getEmail() {
        assertAll(
            () -> assertEquals(email1, user1.getEmail()),
            () -> assertEquals(email2, user2.getEmail())
        );
    }

    @Test
    void getPassword() {
        assertAll(
                () -> assertEquals(password1, user1.getPassword()),
                () -> assertEquals(password2, user2.getPassword())
        );
    }

    @Test
    void getSalt() {
        assertAll(
                () -> assertEquals(salt1, user1.getSalt()),
                () -> assertEquals(salt2, user2.getSalt())
        );
    }

    @Test
    void getPasswordHashed() {
        assertAll(
                () -> assertEquals(passwordHashed1, user1.getPasswordHashed()),
                () -> assertEquals(passwordHashed2, user2.getPasswordHashed())
        );
    }

    @Test
    void getProviderFlag() {
        assertAll(
                () -> assertFalse(user1.getProviderFlag()),
                () -> assertTrue(user2.getProviderFlag())
        );
    }

    @Test
    void setUsername() {
        user1.setUsername(newUsername);
        assertEquals(newUsername, user1.getUsername());
    }

    @Test
    void setEmail() {
        user1.setEmail(newEmail);
        assertEquals(newEmail, user1.getEmail());
    }

    @Test
    void setPassword() {
        user1.setPassword(newPassword);
        assertEquals(newPassword, user1.getPassword());
    }

    @Test
    void setSalt() {
        user1.setSalt();
        assertEquals(5, user1.getSalt().length());
    }

    @Test
    void testSetSalt() {
        user1.setSalt(newSalt);
        assertEquals(newSalt, user1.getSalt());
    }

    @Test
    void setPasswordHashed() {
        user1.setPassword(newPassword);
        user1.setSalt(newSalt);
        user1.setPasswordHashed();
        assertEquals(newPasswordHashed, user1.getPasswordHashed());
    }

    @Test
    void setProviderFlag() {
        user1.setProviderFlag(true);
        assertTrue(user1.getProviderFlag());
    }

    @Test
    void testToString() {
        assertEquals("username: " + username1 + "\n"
                + "password: " + password1 + "\n"
                + "salt: " + salt1 + "\n"
                + "passwordHashed: " + passwordHashed1 + "\n", user1.toString());
    }
}