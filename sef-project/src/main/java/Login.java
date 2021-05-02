import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.digest.DigestUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

class LoginFrame extends GeneralFrame implements ActionListener {
    Container container = getContentPane();
    Database dataBase;

    JLabel loginLabel = new JLabel("Login", SwingConstants.CENTER);
    JLabel usernameLabel = new JLabel("Username", SwingConstants.CENTER);
    JLabel passwordLabel = new JLabel("Password", SwingConstants.CENTER);
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
    JButton loginButton = new JButton("LOGIN");
    JButton registerButton = new JButton("CREATE ACCOUNT");

    LoginFrame(Database dataBase) {

        super();

        this.dataBase = dataBase;

        resetContainerLayout();
        setComponentProperties();
        setContainerComponents();
        setActionEvents();
    }
    
    public void resetContainerLayout(){
        container.setLayout(null);
    }

    public void setComponentProperties() {
        loginLabel.setBounds(0, 20, 400, 100);
        loginLabel.setFont(new Font("Verdana", Font.PLAIN, 50));


        usernameLabel.setBounds(0, 100, 400, 100);
        usernameLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        usernameField.setBounds(50, 170, 300, 50);

        passwordLabel.setBounds(0, 200, 400, 100);
        passwordLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        passwordField.setBounds(50, 270, 300, 50);

        showPasswordCheckBox.setBounds(50, 330, 200, 15);
        loginButton.setBounds(50, 400, 100,50);
        registerButton.setBounds(200, 400, 150, 50);
    }

    public void setContainerComponents() {
        container.add(loginLabel);
        container.add(usernameLabel);
        container.add(passwordLabel);
        container.add(usernameField);
        container.add(passwordField);
        container.add(loginButton);
        container.add(registerButton);
        container.add(showPasswordCheckBox);
    }

    public void setActionEvents() {
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        showPasswordCheckBox.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == showPasswordCheckBox ){
            if ( showPasswordCheckBox.isSelected() ){
                passwordField.setEchoChar((char) 0);
            }
            else {
                passwordField.setEchoChar('*');
            }
        }
        if ( e.getSource() == loginButton ){
            String usernameText;
            char[] passwordText;

            usernameText = usernameField.getText();
            passwordText = passwordField.getPassword();

            User user = new User();
            user.setUsername(usernameText);
            user.setPassword(new String(passwordText));

            if ( dataBase.existsUser(user) ){
                dispose();
                Home.main();
                JOptionPane.showMessageDialog(this, "Login Successful");
            }
            else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }
        if ( e.getSource() == registerButton ){
            String usernameText;
            char[] passwordText;

            usernameText = usernameField.getText();
            passwordText = passwordField.getPassword();

            User user = new User();
            user.setUsername(usernameText);

            if ( !dataBase.existsUsername(usernameText) ){
                user.setSalt();
                user.setPassword(new String(passwordText));
                user.setPasswordHashed();

                dataBase.add(user);
                dataBase.update();

                JOptionPane.showMessageDialog(this, "Registration Complete");
            }
            else {
                JOptionPane.showMessageDialog(this, "Username Already Taken");
            }
        }
    }
}

class User {
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
        this.passwordHashed = new DigestUtils("SHA3-256").digestAsHex(password + salt);
    }

    public String toString(){
        return "username: " + username + "\n"
                + "password: " + password + "\n"
                + "salt: " + salt + "\n"
                + "passwordHashed: " + passwordHashed + "\n";
    }
}

class Database {
    private ArrayList<User> userDB = new ArrayList<User>();
    private final String path;

    public Database(String path){
        this.path = new File(path).getAbsolutePath();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            userDB = new Gson().fromJson(reader, new TypeToken<ArrayList<User>>() {}.getType());
            reader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean existsUser(User user){
        if ( userDB != null ){
            for (User u : userDB) {
                if ( u.getUsername().equals(user.getUsername()) ){
                    user.setSalt(u.getSalt());
                    user.setPasswordHashed();

                    if ( u.getPasswordHashed().equals(user.getPasswordHashed()) ) {
                        return true;
                    }
                    else {
                        break;
                    }
                }
            }
        }

        return false;
    }

    public boolean existsUsername(String username){
        if ( userDB != null ){
            for (User u : userDB) {
                if ( u.getUsername().equals(username) ){
                    return true;
                }
            }
        }

        return false;
    }

    public void add(User user){
        userDB.add(user);
    }

    public void update(){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
            String json = gson.toJson(userDB);

            writer.write(json);
            writer.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void print(){
        if ( userDB == null ){
            return;
        }
        for (User u : userDB) {
            System.out.println(u);
        }
    }
}

public class Login {
    public static void main(String[] args){
        Database DB = new Database("src\\main\\resources\\databases\\database.json");
        LoginFrame frame = new LoginFrame(DB);

        frame.setTitle("Login");
        frame.setVisible(true);
        frame.setBounds(500, 400, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
