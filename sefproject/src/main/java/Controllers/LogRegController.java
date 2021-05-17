package Controllers;

import Components.User;
import Databases.UserDTB;
import Main.App;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogRegController{
    UserDTB userDTB;

    @FXML
    TextField usernameTextField;
    @FXML
    TextField emailTextField;
    @FXML
    PasswordField passwordPasswordField;
    @FXML
    TextField passwordTextField;

    @FXML
    CheckBox showPasswordCheckbox;

    public void setDTB() {
        userDTB = App.getInstance().getUserDTB();
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
            App.alert("Login Successful", "Welcome, " + user.getUsername() + "!");
            System.out.println("login successful");

            App.getInstance().gotoProfile(user.getUsername());
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

        if ( !UserDTB.validUsername(user) ){
            System.out.println("registration failed: invalid username");
            return;
        }

        if ( !UserDTB.validEmail(user) ){
            System.out.println("registration failed: invalid email");
            return;
        }

        if ( !UserDTB.validPassword(user) ){
            System.out.println("registration failed: invalid password");
            return;
        }

        if ( !userDTB.existsUsernameOrEmail(user) ){
            user.setSalt();
            user.setPasswordHashed();

            userDTB.add(user);
            userDTB.updateDatabase();

            App.alert("Registration Complete", "Welcome, " + user.getUsername() + "!");
            System.out.println("registration successful");

            App.getInstance().gotoProfile(user.getUsername());
        }
        else {
            App.alert("Registration Failed", "User already exists!");
            System.out.println("registration failed");
        }
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
