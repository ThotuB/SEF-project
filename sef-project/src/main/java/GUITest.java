import javax.swing.*;
import java.awt.*;

public class GUITest {

    public static void main(String[] args) {

        ProvFrame provFrame = new ProvFrame();

        JLabel name = new JLabel("Name");

        name.setVerticalAlignment(JLabel.TOP);

        name.setForeground(Color.WHITE);
        name.setFont(new Font("MV Boli", Font.PLAIN, 30));

        provFrame.add(name);

        //provFrame.setLayout(null);


    }

}
