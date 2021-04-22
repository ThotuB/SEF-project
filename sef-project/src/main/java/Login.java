import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginFrame extends JFrame implements ActionListener {
    Container container = getContentPane();

    JLabel loginLabel = new JLabel("Login", SwingConstants.CENTER);
    JLabel usernameLabel = new JLabel("Username: ");
    JLabel passwordLabel = new JLabel("Password: ");
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");

    LoginFrame(){
        resetContainerLayout();
        setComponentProperties();
        setContainerComponents();
    }
    
    public void resetContainerLayout(){
        container.setLayout(null);
    }

    public void setComponentProperties(){
        loginLabel.setBounds(0, 20, 400, 100);
        loginLabel.setFont(new Font("Verdana", Font.PLAIN, 50));
//        usernameLabel.setBounds();
//        passwordLabel.setBounds();
//        usernameField.setBounds();
//        passwordField.setBounds();
//        loginButton.setBounds();
//        showPasswordCheckBox.setBounds();
    }

    public void setContainerComponents(){
        container.add(loginLabel);
        container.add(usernameLabel);
        container.add(passwordLabel);
        container.add(usernameField);
        container.add(passwordField);
        container.add(loginButton);
        container.add(showPasswordCheckBox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

public class Login {
    public static void main(String[] args){
        LoginFrame frame = new LoginFrame();

        frame.setTitle("Login");
        frame.setVisible(true);
        frame.setBounds(0, 0, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
