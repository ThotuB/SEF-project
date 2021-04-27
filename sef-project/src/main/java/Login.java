import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginFrame extends JFrame implements ActionListener {
    Container container = getContentPane();

    JLabel loginLabel = new JLabel("Login", SwingConstants.CENTER);
    JLabel usernameLabel = new JLabel("Username", SwingConstants.CENTER);
    JLabel passwordLabel = new JLabel("Password", SwingConstants.CENTER);
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");

    LoginFrame() {
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
        loginButton.setBounds(100, 400, 200,50);
    }

    public void setContainerComponents() {
        container.add(loginLabel);
        container.add(usernameLabel);
        container.add(passwordLabel);
        container.add(usernameField);
        container.add(passwordField);
        container.add(loginButton);
        container.add(showPasswordCheckBox);
    }

    public void setActionEvents() {
        loginButton.addActionListener(this);
        showPasswordCheckBox.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == showPasswordCheckBox ){
            if ( showPasswordCheckBox.isSelected() ){
                //showPasswordCheckBox.setEchoChar((char) 0);
            }
            else {

            }
        }
        if ( e.getSource() == loginButton ){

        }
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
