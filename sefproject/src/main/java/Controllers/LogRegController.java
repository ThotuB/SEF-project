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

    public void loginButton(){
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = getPassword();

        User user = new User(username, email, password);
    }

    public void registerButton(){
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = getPassword();


    }

    public void showPasswordCheck(){
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
