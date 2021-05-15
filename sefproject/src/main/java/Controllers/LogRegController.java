package Controllers;

import Components.User;
import Databases.UserDTB;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogRegController {
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

    public void setUserDTB(UserDTB userDTB){
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
            System.out.println("login succ");
        }
        else {
            System.out.println("login not succ");
        }
    }

    public void registerButton(){
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = getPassword();

        User user = new User(username, email, password);

        if ( !userDTB.existsUsername(user.getUsername()) ){
            user.setSalt();
            user.setPasswordHashed();

            userDTB.add(user);
            userDTB.update();

            System.out.println("Registration Complete");
        }
        else {
            System.out.println("Username Already Taken");
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
