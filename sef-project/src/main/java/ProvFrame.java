import javax.swing.*;
import java.awt.*;

public class ProvFrame extends JFrame {

    ProvFrame(){


        this.setTitle("Raccoony");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1080,720);
        this.setVisible(true);

        ImageIcon image = new ImageIcon("Images/appIcon.jpg");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(54, 57, 63));




    }

}
