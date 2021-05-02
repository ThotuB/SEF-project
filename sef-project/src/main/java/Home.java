import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class HomeFrame extends JFrame implements ActionListener {
    Container container = getContentPane();

    HomeFrame() {
        resetContainerLayout();
    }

    public void resetContainerLayout(){
        container.setLayout(null);
    }

    public void setComponentProperties() {

    }

    public void setContainerComponents() {

    }

    public void setActionEvents() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

public class Home {
    public static void main(){
        HomeFrame frame = new HomeFrame();

        frame.setTitle("Home");
        frame.setVisible(true);
        frame.setBounds(0, 0, 1080, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
