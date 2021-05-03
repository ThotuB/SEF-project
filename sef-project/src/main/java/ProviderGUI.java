import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ProviderGUI extends GeneralFrame implements ActionListener {
    Provider provider;

    Container container = getContentPane();

    JLabel homeLabel = new JLabel("", SwingConstants.LEFT);
    JButton makeOfferButton = new GeneralButton("Make a new offer");
    JButton checkCurrentOffersButton = new GeneralButton("Check current offers");
    JButton statisticsButton = new GeneralButton("See statistics");
    JList <String> gameList = new JList<>();
    DefaultListModel<String> model = new DefaultListModel<>();

    ProviderGUI(String username) throws IOException {

        super();

        provider = getProvider(username);

        for (String l : getGameNames(provider.getGameArray())) {
            model.addElement(l);
        }

        gameList.setModel(model);
        resetContainerLayout();
        setComponentProperties();
        setContainerComponents();
        setActionEvents();
    }

    public ArrayList<String> getGameNames(ArrayList <Game> games) {

        ArrayList<String> gameNames = new ArrayList<>();

        for (Game g : games) {
            gameNames.add(g.getName());
        }

        return gameNames;
    }

    public void resetContainerLayout(){
        container.setLayout(null);
    }

    public void setComponentProperties() {

        homeLabel.setBounds(-7, -7, 1200, 100); //intentionat mai > decat 1080
        homeLabel.setFont(new Font("Verdana", Font.PLAIN, 50));
        homeLabel.setText("  Hello, " + provider.getName() + "!");
        homeLabel.setForeground(Color.WHITE);
        Border border = BorderFactory.createLineBorder(Color.GRAY, 3);
        homeLabel.setBorder(border);

        makeOfferButton.setBounds((1080/2 - 200/2), 200, 200,50);

        checkCurrentOffersButton.setBounds(440, 350, 200, 50);

        statisticsButton.setBounds(440, 500, 200, 50);

        gameList.setBounds(30,110,250,550);
        gameList.setBackground(new Color(60,63,65));
        gameList.setFont(new Font("Verdana", Font.PLAIN, 17));
        gameList.setForeground(Color.WHITE);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer)gameList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        gameList.setBorder(border);


    }

    public void setContainerComponents() {
        container.add(homeLabel);
        container.add(makeOfferButton);
        container.add(checkCurrentOffersButton);
        container.add(statisticsButton);
        container.add(gameList);
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
