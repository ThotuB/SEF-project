import javax.swing.*;
import java.awt.*;

public class GeneralFrame extends JFrame {

    GeneralFrame() {

        ImageIcon image = new ImageIcon("src\\main\\resources\\images\\appIcon.jpg");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(40, 40, 40));

    }

}
