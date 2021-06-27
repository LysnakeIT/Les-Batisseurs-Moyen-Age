package view;
import javax.swing.*;
import control.*;
import java.awt.*;
import java.io.File;

/**
 * Class that display load game window
 * @author L.DAMIEN
 */
public class SaveList extends JPanel {
    
    private JList listSave;
    private JButton loadGame;
    private JButton quit;
    private SaveListListener control;

    /**
     * Constructor which initializes the graphical load game window
     * @param home the main frame of the application
     */
    public SaveList(Home home) {
        if (home != null) {
            control = new SaveListListener(home, this);
            this.initComponents();
        } else {
            System.out.println("SaveList : parameter is null");
        }
    }

    /**
     * Get the button load
     * @return the button load
     */
    public JButton getLoad() {return this.loadGame;}

    /**
     * Get the button back
     * @return the button back
     */
    public JButton getBack() {return this.quit;}

    /**
     * Get the list of load game file
     * @return the list of load game file
     */
    public JList getList() {return this.listSave;}

    /**
     * Method that initializes the "SaveList" window.
     */
    private void initComponents() {
        Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
        int longueur = tailleMoniteur.width * 2/3;
        int hauteur = tailleMoniteur.height * 2/3;
        setSize(longueur, hauteur);
        
        ImageIcon iconLoadGame = new ImageIcon("../data/images/LoadGame.png");
        ImageIcon iconBack = new ImageIcon("../data/images/back.png");
        JLabel background = new JLabel(new ImageIcon("../data/images/fond.png"));
        JLabel logo = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("../data/images/logo.png").getImage().getScaledInstance(1000, 150, Image.SCALE_SMOOTH));
        logo.setIcon(imageIcon);
        JPanel panel = new JPanel();
        logo.setBounds(150, 0 , 1200, 150);
        panel.setLayout(null);


        File dossier = new File("../data/saves/");
        File[] contenu = dossier.listFiles();
        listSave = new JList(contenu);
        listSave.setBounds(325, 170, 600, 300);
        listSave.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSave.setLayoutOrientation(JList.VERTICAL_WRAP);
        listSave.setVisibleRowCount(-1);

        loadGame = new JButton(iconLoadGame);
        loadGame.setBounds(860, 550, 250, 83);
        loadGame.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        loadGame.addActionListener(control);

        quit = new JButton(iconBack);
        quit.setBounds(150, 550, 250, 83);
        quit.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        quit.addActionListener(control);

        panel.add(logo);
        panel.add(listSave);
        panel.add(loadGame);
        panel.add(quit);
        panel.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        panel.add(background);
        add(panel);
        setVisible(true);
    }  
}
