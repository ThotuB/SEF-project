import javax.swing.*;
import java.awt.*;

public class GeneralButton extends JButton {

    GeneralButton(String buttonName) {

        super(buttonName);

        this.setBackground(new Color(220, 221, 222));
        this.setFont(new Font("Cambria", Font.BOLD, 15));

    }
}