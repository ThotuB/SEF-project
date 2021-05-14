package Components;

import com.google.gson.annotations.Expose;
import org.apache.commons.codec.digest.DigestUtils;

public class User {
    @Expose
    private String username;
    private String password;
    @Expose
    private String passwordHashed;
    @Expose
    private String salt;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setSalt() {
        StringBuilder saltGen = new StringBuilder();

        for (int i = 0 ; i < 5 ; i++){
            saltGen.append((char) Math.floor(Math.random() * 90 + 35));
        }

        this.salt = saltGen.toString();
    }

    public String getPasswordHashed() {
        return passwordHashed;
    }

    public void setPasswordHashed() {
        this.passwordHashed = DigestUtils.sha256Hex(password + salt);
    }

    public String toString(){
        return "username: " + username + "\n"
                + "password: " + password + "\n"
                + "salt: " + salt + "\n"
                + "passwordHashed: " + passwordHashed + "\n";
    }
}
