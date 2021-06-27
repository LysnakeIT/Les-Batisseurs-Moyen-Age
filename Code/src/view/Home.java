package view;
import javax.swing.*;
import java.awt.*;
import control.*;
import model.Game;

/**
 * Class that display home window
 * @author L.DAMIEN
 */
public class Home extends JFrame {

    
    private JButton play;
    private JButton settings;
    private JButton rules;
    private JButton quit;
    private HomeListener control;
    private Container contentPane;

    /**
     * Constructor which initializes the graphical window with the controller
     * @param nom the name of the application
     */
    public Home(String nom) {
        if (nom != null) {
            control = new HomeListener(this);
            contentPane=getContentPane();
            this.initComponents(nom);
        } else {
            System.out.println("Home : parameter is null");
        }
    }

    /**
     * Get the button play
     * @return the button play
     */
    public JButton getPlay() {return this.play;}

    /**
     * Get the button settings
     * @return the button settings
     */
    public JButton getSettings() {return this.settings;}

    /**
     * Get the button rules
     * @return the button rules
     */
    public JButton getRules() {return this.rules;}
    
    /**
     * Get the button exit
     * @return the button exit
     */
    public JButton getQuit() {return this.quit;}

    /**
     * Method that initializes the "Home" window.
     * @param nom the name of the application
     */
    private void initComponents(String nom) {
        Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
        int longueur = tailleMoniteur.width * 2/3;
        int hauteur = tailleMoniteur.height * 2/3;
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setTitle(nom);
        setSize(longueur,  hauteur);
        
        ImageIcon iconPlay = new ImageIcon("../data/images/PlayButton.png");
        ImageIcon iconSettings = new ImageIcon("../data/images/SettingsButton.png");
        ImageIcon iconRules = new ImageIcon("../data/images/RulesButton.png");
        ImageIcon iconExit = new ImageIcon("../data/images/ExitButton.png");
        JLabel background = new JLabel(new ImageIcon("../data/images/fond.png"));

        JLabel logo = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("../data/images/logo.png").getImage().getScaledInstance(1000, 150, Image.SCALE_SMOOTH));
        logo.setIcon(imageIcon);

        JLabel worker = new JLabel();
        ImageIcon workerIcon = new ImageIcon(new ImageIcon("../data/images/worker.png").getImage().getScaledInstance(700, 700, Image.SCALE_SMOOTH));
        worker.setIcon(workerIcon);

        JPanel panel = new JPanel();
        logo.setBounds(150, -25 , 1200, 150);
        worker.setBounds(500, -15, 700, 700);
        panel.setLayout(null);
        
        play = new JButton(iconPlay);
        play.setBounds(150, 190, 250, 83);
        play.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        play.addActionListener(control);

        settings = new JButton(iconSettings);
        settings.setBounds(150, 310, 250, 83);
        settings.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        settings.addActionListener(control);

        rules = new JButton(iconRules);
        rules.setBounds(150, 430, 250, 83);
        rules.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        rules.addActionListener(control);

        quit = new JButton(iconExit);
        quit.setBounds(150, 550, 250, 83);
        quit.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        quit.addActionListener(control);

        panel.add(logo);
        panel.add(worker);
        panel.add(play);
        panel.add(settings);
        panel.add(rules);
        panel.add(quit);
        panel.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        panel.add(background);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel);
        setVisible(true);
    }

    /**
     * Method that changes the current window to the window "ChooseGameWindow"
     * Method used when pressing the play button on the home menu
     */
    public void goToPlay() {
        contentPane.removeAll();
        ChooseGameWindow cgw = new ChooseGameWindow(this);
        contentPane.add(cgw);
        setVisible(true);
    }

    /**
     * Method that changes the current window to the window "RulesWindow"
     * Method used when pressing the rules button on the home menu
     */
    public void goToRules() {
        contentPane.removeAll();
        RulesWindow rw = new RulesWindow(this);
        contentPane.add(rw);
        setVisible(true);
    }

    /**
     * Method that changes the current window to the window "Home"
     * Method used when pressing the back button on the chooseGameWindow menu to back at the home menu
     * @throws Exception avoid the exception "IllegalArgumentException" which says that we add a window to a container
     */
    public void goToBackAtHome() throws Exception {
        contentPane.removeAll();
        Home h = new Home("Les batisseurs");
        contentPane.add(h);
        setVisible(true);
    }

    /**
     * Method that changes the current window to the window "SaveList"
     * Method used when pressing the loadGame button on the chooseGameWindow menu
     */
    public void goToLoad() {
        contentPane.removeAll();
        SaveList sl = new SaveList(this);
        contentPane.add(sl);
        setVisible(true);
    }

    /**
     * Method that changes the current window to the window "ChooseGameWindow"
     * Method used when pressing the newGame button on the chooseGameWindow menu
     */
    public void goToNewGame() {
        contentPane.removeAll();
        GameModeWindow gmw = new GameModeWindow(this);
        contentPane.add(gmw);
        setVisible(true);
    }

    /**
     * Method that changes the current window to the window "Home"
     * Method used when pressing the back button on the gameModeWindow menu to back at the chooseGameWindow menu
     */
    public void goToBackChoose() {
        contentPane.removeAll();
        ChooseGameWindow cgw = new ChooseGameWindow(this);
        contentPane.add(cgw);
        setVisible(true);
    }

    /**
     * Method that changes the current window to the window "Settings"
     * Method used when pressing the settings button on the home menu
     */
    public void goToSettings() {
        contentPane.removeAll();
        Settings s = new Settings(this);
        contentPane.add(s);
        setVisible(true);
    }

    /**
     * Method that changes the current window to the window "Settings"
     * Method used when pressing the settings button on the home menu
     * @param game the current game
     */
    public void goToGame(Game game) {
        contentPane.removeAll();
        GameWindow gw = new GameWindow(this, game);
        contentPane.add(gw);
        setSize(1920, 1080);
        setVisible(true);
    }
}