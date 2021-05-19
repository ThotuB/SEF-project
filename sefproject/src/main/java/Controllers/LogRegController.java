package Controllers;

import Components.User;
import Databases.UserDTB;
import Main.App;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LogRegController{
    UserDTB userDTB;
    private boolean providerFlag;

    @FXML
    public TextField usernameTextField;
    public TextField emailTextField;
    public PasswordField passwordPasswordField;
    public TextField passwordTextField;
    public TextArea errorTextArea;

    @FXML
    public CheckBox showPasswordCheckbox;
    public CheckBox registerAsProviderCheckbox;

    public void setup(UserDTB userDTB) {
        this.userDTB = userDTB;
    }

    public String getPassword(){
        if ( showPasswordCheckbox.isSelected() ){
            return passwordTextField.getText();
        }
        else {
            return passwordPasswordField.getText();
        }
    }

    public void loginAction(){
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = getPassword();

        User user = new User(username, email, password);

        if ( userDTB.existsUser(user) ){
            user = userDTB.getUser(user);

            App.alert("Login Successful", "Welcome, " + user.getUsername() + "!");
            System.out.println("login successful");

            if ( user.getProviderFlag() ){
                App.getInstance().gotoProvider(user.getUsername());
            }
            else {
                App.getInstance().gotoCustomer(user.getUsername());
            }
        }
        else {
            App.alert("Login Failed", "Invalid data given!");
            System.out.println("login failed");
        }
    }

    public void registerButton(){
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = getPassword();

        User user = new User(username, email, password);

        boolean valid = true;
        String errorStr = "";

        if ( !UserDTB.validUsername(user) ){
            errorStr += "Invalid username! (too short)\n";
            valid = false;

            System.out.println("registration failed: invalid username");
        }

        if ( !UserDTB.validEmail(user) ){
            errorStr += "Invalid email address!\n";
            valid = false;

            System.out.println("registration failed: invalid email");
        }

        if ( !UserDTB.validPassword(user) ){
            errorStr += """
                    Invalid password!

                    Password needs to:
                      - be between 5 and 20 characters long.
                      - have at least 1 uppercase character
                      - have at least 1 lowercase character
                      - have at least 1 digit
                      """;
            valid = false;

            System.out.println("registration failed: invalid password");
        }

        if ( !valid ){
            errorTextArea.setText(errorStr);
            errorTextArea.setVisible(true);
            return;
        }

        errorTextArea.setVisible(false);

        if ( !userDTB.existsUsernameOrEmail(user) ){
            user.setSalt();
            user.setPasswordHashed();
            user.setProviderFlag(providerFlag);

            userDTB.add(user);
            userDTB.updateDatabase();

            App.alert("Registration Complete", "Welcome, " + user.getUsername() + "!");
            System.out.println("registration successful");

            if ( user.getProviderFlag() ){
                App.getInstance().gotoProvider(user.getUsername());
            }
            else {
                App.getInstance().gotoCustomer(user.getUsername());
            }
        }
        else {
            App.alert("Registration Failed", "User already exists!");
            System.out.println("registration failed");
        }
    }

    public void registerAsProviderAction(){
        providerFlag = registerAsProviderCheckbox.isSelected();
    }

    public void showPasswordAction(){
        if ( showPasswordCheckbox.isSelected() ){
            passwordTextField.setText(passwordPasswordField.getText());
            passwordTextField.setVisible(true);
            passwordPasswordField.setVisible(false);
        }
        else {
            passwordPasswordField.setText(passwordTextField.getText());
            passwordPasswordField.setVisible(true);
            passwordTextField.setVisible(false);
        }
    }
}
