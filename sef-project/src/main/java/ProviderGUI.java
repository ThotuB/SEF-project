import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProviderGUI extends GeneralFrame implements ActionListener {
    Provider provider;

    Container container = getContentPane();

    JLabel homeLabel = new JLabel("", SwingConstants.LEFT);
    JButton makeOfferButton = new GeneralButton("Make a new offer");
    JButton checkCurrentOffersButton = new GeneralButton("Check current offers");
    JButton statisticsButton = new GeneralButton("See statistics");

    BufferedImage myPicture = ImageIO.read(new File("src\\main\\resources\\images\\raccoon.png"));
    JLabel raccoon = new JLabel(new ImageIcon(myPicture));


    ProviderGUI(String username) throws IOException {

        super();

        provider = getProvider(username);
        resetContainerLayout();
        setComponentProperties();
        setContainerComponents();
        setActionEvents();
    }

    public void resetContainerLayout(){
        container.setLayout(null);
    }

    public void setComponentProperties() {

        homeLabel.setBounds(-7, -7, 1200, 100);
        homeLabel.setFont(new Font("Verdana", Font.PLAIN, 50));
        homeLabel.setText("  Hello, " + provider.getName() + "!");
        homeLabel.setForeground(Color.WHITE);
        Border border = BorderFactory.createLineBorder(Color.GRAY, 3);
        homeLabel.setBorder(border);

        makeOfferButton.setBounds(50, 200, 200,50);

        checkCurrentOffersButton.setBounds(50, 300, 200, 50);

        statisticsButton.setBounds(50, 400, 200, 50);

        //raccoon.setBounds(400, 250, 400, 400);
        //raccoon.

    }

    public void setContainerComponents() {
        container.add(homeLabel);
        container.add(makeOfferButton);
        container.add(checkCurrentOffersButton);
        container.add(statisticsButton);
        container.add(raccoon);
    }

    public void setActionEvents() {
        makeOfferButton.addActionListener(this);
        checkCurrentOffersButton.addActionListener(this);
        statisticsButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == makeOfferButton) {

        }

        if (e.getSource() == checkCurrentOffersButton) {

        }

        if (e.getSource() == statisticsButton) {

        }
    }

    public Provider getProvider(String username) throws IOException {

        ProviderDTB dtb = new ProviderDTB();

        dtb.readProviders("src\\main\\resources\\databases\\ProvidersDTB.json");

        for (Provider i: dtb.data) {
            if (i.getName().equals(username)) {
                return i;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {

        ProviderGUI home = new ProviderGUI("Jador");

        home.setTitle("Welcome");
        home.setVisible(true);
        home.setBounds(0, 0, 1080, 720);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setResizable(false);
    }


}
