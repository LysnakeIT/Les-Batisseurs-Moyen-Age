package view;
import javax.swing.*;
import control.*;
import java.awt.*;

/**
 * Class that display choose game window
 * @author L.DAMIEN
 */
public class ChooseGameWindow extends JPanel {

    private JButton newGame;
    private JButton loadGame;
    private JButton quit;
    private ChooseGameWindowListener control;


    /**
     * Constructor which initializes the graphical choose game window
     * @param home the main frame of the application
     */
    public ChooseGameWindow(Home home) {
        if (home != null) {
            control = new ChooseGameWindowListener(this, home);
            this.initComponents();
        } else {
            System.out.println("ChooseGameWindow: parameter is null");
        }
    }

    /**
     * Get the button newGame
     * @return the button newGame
     */
    public JButton getNewGame() {return this.newGame;}

    /**
     * Get the button loadGame
     * @return the button loadGame
     */
    public JButton getLoadGame() {return this.loadGame;}

    /**
     * Get the button back
     * @return the button back
     */
    public JButton getBack() {return this.quit;}

    /**
     * Method that initializes the "ChooseGame" window.
     */
    private void initComponents()  {
        Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
        int longueur = tailleMoniteur.width * 2/3;
        int hauteur = tailleMoniteur.height * 2/3;
        setSize(longueur, hauteur);
        
        ImageIcon iconNewGame = new ImageIcon("../data/images/NewGame.png");
        ImageIcon iconLoadGame = new ImageIcon("../data/images/LoadGame.png");
        ImageIcon iconBack = new ImageIcon("../data/images/back.png");
        JLabel background = new JLabel(new ImageIcon("../data/images/fond.png"));
        JLabel logo = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("../data/images/logo.png").getImage().getScaledInstance(1000, 150, Image.SCALE_SMOOTH));
        logo.setIcon(imageIcon);
        JPanel panel = new JPanel();
        logo.setBounds(150, 0 , 1200, 150);
        panel.setLayout(null);
        
        newGame = new JButton(iconNewGame);
        newGame.setBounds(250, 270, 250, 83);
        newGame.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        newGame.addActionListener(control);

        loadGame = new JButton(iconLoadGame);
        loadGame.setBounds(750, 270, 250, 83);
        loadGame.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        loadGame.addActionListener(control);

        quit = new JButton(iconBack);
        quit.setBounds(150, 550, 250, 83);
        quit.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        quit.addActionListener(control);

        panel.add(logo);
        panel.add(newGame);
        panel.add(loadGame);
        panel.add(quit);
        panel.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        panel.add(background);
        add(panel);
        setVisible(true);
    }
}
