package view;
import javax.swing.*;
import control.*;
import java.awt.*;
import model.*;

/**
 * Class that display game mode window
 * @author L.DAMIEN
 */
public class GameModeWindow extends JPanel {

    private JButton play;
    private JButton quit;
    private JComboBox <Mode> boxMode;
    private JTextField player1;
    private JTextField player2;
    private JTextField player3;
    private JTextField player4;
    private GameModeWindowListener control;

    /**
     * Constructor which initializes the graphical game mode window
     * @param home the main frame of the application
     */
    public GameModeWindow(Home home) {
        if (home != null) {
            control = new GameModeWindowListener(this, home);
            this.initComponents();
        } else {
            System.out.println("GameModeWindow : parameter is null");
        }
    }

    /**
     * Get the button play
     * @return the button play
     */
    public JButton getPlay() {return this.play;}

    /**
     * Get the mode choose
     * @return the mode choose
     */
    public JComboBox <Mode> getMode() {return this.boxMode;}

    /**
     * Get the playerName1
     * @return the playerName1
     */
    public JTextField getPlayer1() {return this.player1;}

    /**
     * Get the playerName2
     * @return the playerName2
     */
    public JTextField getPlayer2() {return this.player2;}

    /**
     * Get the playerName3
     * @return the playerName3
     */
    public JTextField getPlayer3() {return this.player3;}

    /**
     * Get the playerName4
     * @return the playerName4
     */
    public JTextField getPlayer4() {return this.player4;}

    /**
     * Get the button back
     * @return the button back
     */
    public JButton getBack() {return this.quit;}

    /**
     * Method that initializes the "GameMode" window.
     */
    private void initComponents() {
        Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
        int longueur = tailleMoniteur.width * 2/3;
        int hauteur = tailleMoniteur.height * 2/3;
        setSize(longueur, hauteur);
        
        ImageIcon iconPlay = new ImageIcon("../data/images/PlayButton.png");
        ImageIcon iconBack = new ImageIcon("../data/images/back.png");
        JLabel background = new JLabel(new ImageIcon("../data/images/fond.png"));
        JLabel logo = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("../data/images/logo.png").getImage().getScaledInstance(1000, 150, Image.SCALE_SMOOTH));
        logo.setIcon(imageIcon);
        JPanel panel = new JPanel();
        logo.setBounds(150, -25 , 1200, 150);
        panel.setLayout(null);
        
        play = new JButton(iconPlay);
        play.setBounds(860, 550, 250, 83);
        play.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        play.addActionListener(control);

        boxMode = new JComboBox<Mode>(Mode.values());
        boxMode.setBounds(507, 270, 250, 83);
        boxMode.addActionListener(control);

        player1 = new JTextField();
        player1.setBounds(150, 150, 250, 83);
        player1.setText("Name of player 1");
        player1.addMouseListener(control);

        player2 = new JTextField();
        player2.setBounds(860, 150, 250, 83);
        player2.setText("Name of player 2");
        player2.addMouseListener(control);

        player3 = new JTextField();
        player3.setBounds(150, 390, 250, 83);
        player3.setText("Name of player 3");
        player3.setEditable(false);
        player3.addMouseListener(control);

        player4 = new JTextField();
        player4.setBounds(860, 390, 250, 83);
        player4.setText("Name of player 4");
        player4.setEditable(false);
        player4.addMouseListener(control);

        quit = new JButton(iconBack);
        quit.setBounds(150, 550, 250, 83);
        quit.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        quit.addActionListener(control);

        panel.add(logo);
        panel.add(play);
        panel.add(player1);
        panel.add(player2);
        panel.add(player3);
        panel.add(player4);
        panel.add(boxMode);
        panel.add(quit);
        panel.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        panel.add(background);
        add(panel);
        setVisible(true);
    }
}
