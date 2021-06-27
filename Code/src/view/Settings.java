package view;
import javax.swing.*;
import control.*;
import java.awt.*;

/**
 * Class that display settings window
 * @author L.DAMIEN
 */
public class Settings extends JPanel {
    
    private JCheckBox activate;
    private JCheckBox desactivate;
    private JButton quit;
    private SettingsListener control;

    /**
     * Constructor which initializes the graphical window with the controller, and the model
     * @param home the main frame of the application
     */
    public Settings(Home home)  {
        if (home != null) {
            control = new SettingsListener(home, this);
            this.initComponents();
        } else {
            System.out.println("Settings : parameter is null");
        }
    }

    /**
     * Get the button back
     * @return the button back
     */
    public JButton getBack() {return this.quit;}

    /**
     * Get the checkButton activate
     * @return the checkButton activate
     */
    public JCheckBox getActivate() {return this.activate;}

    /**
     * Get the checkButton desactivate
     * @return the checkButton desactivate
     */
    public JCheckBox getDesactivate() {return this.desactivate;}

    /**
     * Method that initializes the "Settings" window.
     */
    private void initComponents()  {
        Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
        int longueur = tailleMoniteur.width * 2/3;
        int hauteur = tailleMoniteur.height * 2/3;
        setSize(longueur, hauteur);
        
        ImageIcon iconBack = new ImageIcon("../data/images/back.png");
        JLabel background = new JLabel(new ImageIcon("../data/images/fond.png"));
        JLabel logo = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("../data/images/logo.png").getImage().getScaledInstance(1000, 150, Image.SCALE_SMOOTH));
        logo.setIcon(imageIcon);
        JPanel panel = new JPanel();
        logo.setBounds(150, 0 , 1200, 150);
        panel.setLayout(null);

        activate = new JCheckBox("Avec musique");
        activate.setBounds(250, 270, 250, 83);
        activate.addActionListener(control);

        desactivate = new JCheckBox("Sans musique");
        desactivate.setBounds(750, 270, 250, 83);
        desactivate.addActionListener(control);
        desactivate.setEnabled(false);

        quit = new JButton(iconBack);
        quit.setBounds(150, 550, 250, 83);
        quit.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        quit.addActionListener(control);

        panel.add(logo);
        panel.add(activate);
        panel.add(desactivate);
        panel.add(quit);
        panel.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        panel.add(background);
        add(panel);
        setVisible(true);

    }
}
