package view;
import javax.swing.*;
import control.*;
import java.awt.*;

/**
 * Class that display rules window
 * @author L.DAMIEN
 */
public class RulesWindow extends JPanel {

    private JTextArea rules;
    private JScrollPane areaScrollPane;
    private JLabel title;
    private JButton back;
    private RulesListener control;

    /**
     * Constructor which initializes the graphical rules window
     * @param home the main frame of the application
     */
    public RulesWindow(Home home) {
        if (home != null) {
            control = new RulesListener(home, this);
            this.initComponents();
        } else {
            System.out.println("RulesWindow : parameter is null");
        }
    }

    /**
     * Get the button back
     * @return the button back
     */
    public JButton getBack() {return this.back;}

    /**
     * Method that initializes the "Rules" window.
     */
    private void initComponents() {
        Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
        int longueur = tailleMoniteur.width * 2/3;
        int hauteur = tailleMoniteur.height * 2/3;
        setSize(longueur, hauteur);
        JLabel background = new JLabel(new ImageIcon("../data/images/fond.png"));
        title = new JLabel(new ImageIcon("../data/images/rulesTitle.png"));
        JPanel panel = new JPanel();
        title.setBounds(185, -100 , 500, 300);
        setLayout(new GridBagLayout());
        panel.setLayout(null);

        String rulesText = " ____________________________________________________________________________________ "
        + "\n"
        + "\t---------------------------------- Les Batisseurs : Moyen-Age ----------------------------------"
        + "\n\n   The winner is the first player to reach 17 points without counting the coins points"
        + "\n\n"
        + "   --- Rules :"
        + "\n"
        + "\n     ┌─ Open a site :"
        + "\n   └──────────╼ Opening a building site costs an action.the player takes a building from the deck and \n\t   opens the building site."
        + "\n\t   The player takes a building from the deck and opens the building site."
        + "\n\t   The player can open several construction sites."
        + "\n     ┌─ Recruit a  worker:"
        + "\n   └──────────╼ Recruiting a worker costs one action, the player takes a worker from the deck, he can  \n\t   take as many as he has actions to do so"
        + "\n     ┌─ Send a worker to work :"
        + "\n   └──────────╼ Send a worker to work at a variable cost."
        + "\n\t   Sending a worker during the same turn costs 1 action, a second during the same turn 2 \n\t   actions and so on."
        + "\n\t   The player pays the number of shields and as long as the site is not finished, the worker \n\t   cannot be moved."
        + "\n     ┌─ Sell actions against coins :"
        + "\n   └──────────╼ 1 action for 1 coin, 2 actions for 3 coins and 3 action for 6 coins"
        + "\n"
        + "‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾";

        rules = new JTextArea(5,10);
        rules.setFont(new Font("Serif", Font.ITALIC, 16));
        rules.setLineWrap(true);
        rules.setWrapStyleWord(true);
        rules.setEditable(false);
        rules.setText(rulesText);

        areaScrollPane = new JScrollPane(rules);
        areaScrollPane.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setBounds(185, 120, 750, 400);
        panel.add(areaScrollPane);
        
        ImageIcon iconQuit = new ImageIcon("../data/images/Back.png");
        back = new JButton(iconQuit);
        back.setBounds(185, 550, 250, 83);
        back.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        back.addActionListener(control);

        panel.add(title);
        panel.add(areaScrollPane);
        panel.add(back);
        panel.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        panel.add(background);
        add(panel);
        setVisible(true);
    }
}
