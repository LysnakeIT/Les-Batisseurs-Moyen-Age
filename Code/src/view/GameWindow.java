package view;
import javax.swing.*;
import control.*;
import java.awt.*;

import model.*;

/**
 * Class that display game window
 * @author L.DAMIEN
 */
public class GameWindow extends JPanel {

    private Game gameplay;
    private JButton open;
    private JButton hire;
    private JButton work;
    private JButton sell;
    private JButton buy;
    private JButton settings;
    private JButton quit;
    private JButton round;
    private JLabel cw1;
    private JLabel cw2;
    private JLabel cw3;
    private JLabel cw4;
    private JLabel cw5;
    private JLabel cb1;
    private JLabel cb2;
    private JLabel cb3;
    private JLabel cb4;
    private JLabel cb5;
    private JLabel cp1;
    private JLabel cp2;
    private JLabel cp3;
    private JLabel cp4;
    private JLabel nameplayer;
    private JLabel ecuplayer;
    private JLabel pointplayer;
    private JLabel actionplayer;
    private JComboBox mainBuilder;
    private JComboBox mainWorker;
    private JComboBox boxDeckBuilder;
    private JComboBox boxDeckWorker;
    private JComboBox boxBuilder;
    private JComboBox boxWorker;
    private JTextArea sellField;
    private JTextArea buyField;
    private GameWindowListener control;

    /**
     * Constructor which initializes the graphical game window
     * @param home the main frame of the application
     * @param game the current game
     */
    public GameWindow(Home home, Game game) {
        if (home != null) {
            this.control = new GameWindowListener(this);
            this.gameplay = game;
            if (this.gameplay.getCurrentPlayer() instanceof AutoPlayer) {
                this.initComponents();
                this.gameplay.getCurrentPlayer().play();
                this.gameplay.getCurrentPlayer().addActions(3);
                this.gameplay.changeCurrent();
                this.refresh();
            } else {
                this.initComponents();
            }
        } else {
            System.out.println("GameWindow : parameter is null");
        }
    }

    /**
     * Get the button open
     * @return the button open
     */
    public Game getGame() {return this.gameplay;}

    /**
     * Get the button open
     * @return the button open
     */
    public JButton getOpen() {return this.open;}

    /**
     * Get the button hire
     * @return the button hire
     */
    public JButton getHire() {return this.hire;}

    /**
     * Get the button work
     * @return the button work
     */
    public JButton getWork() {return this.work;}

    /**
     * Get the button sell
     * @return the button sell
     */
    public JButton getSell() {return this.sell;}

    /**
     * Get the button buy
     * @return the button buy
     */
    public JButton getBuy() {return this.buy;}

    /**
     * Get the button back
     * @return the button back
     */
    public JButton getBack() {return this.quit;}

    /**
     * Get the button settings
     * @return the button settings
     */
    public JButton getSettings() {return this.settings;}

    /**
     * Get the button round
     * @return the button round
     */
    public JButton getRound() {return this.round;}

    /**
     * Get the idCard choose
     * @return the idCard choose
     */
    public JComboBox getBoxDeckBuilder() {return this.boxDeckBuilder;}

    /**
     * Get the idCard choose
     * @return the idCard choose
     */
    public JComboBox getBoxDeckWorker() {return this.boxDeckWorker;}

    /**
     * Get the idCard choose
     * @return the idCard choose
     */
    public JComboBox getBoxBuilder() {return this.boxBuilder;}

    /**
     * Get the idCard choose
     * @return the idCard choose
     */
    public JComboBox getBoxWorker() {return this.boxWorker;}

    /**
     * Get the value of actions to sell
     * @return value of actions to sell
     */
    public JTextArea getSellAmount() {return this.sellField;}

     /**
     * Get the value of actions to buy
     * @return value of actions to buy
     */
    public JTextArea getBuyAmount() {return this.buyField;}


    /**
     * Method that initializes the "GameMode" window.
     */
    public void initComponents() {
        Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
        int longueur = tailleMoniteur.width;
        int hauteur = tailleMoniteur.height;
        setSize(longueur, hauteur);
        
        JLabel background = new JLabel(new ImageIcon("../data/images/fond.png"));
        JPanel panel = new JPanel();
        JLabel building = new JLabel("Building");
        JLabel worker = new JLabel("Worker");

        panel.setLayout(null);
        
        quit = new JButton(new ImageIcon("../data/images/back.png"));
        quit.setBounds(1700, 800, 250, 83);
        quit.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        quit.addActionListener(control);

        cw1 = new JLabel();
        cw1.setBounds(160, 7, 120, 170);
        cw2 = new JLabel();
        cw2.setBounds(520, 7, 120, 170);
        cw3 = new JLabel();
        cw3.setBounds(880, 7, 120, 170);
        cw4 = new JLabel();
        cw4.setBounds(1240, 7, 120, 170);
        cw5 = new JLabel();
        cw5.setBounds(1600, 7, 120, 170);

        int[] list = new int[5];
        for (int i = 0; i < 5; i++) {
            if(this.gameplay.getGame().getCurrentPlayer().getBoard().getDeckWorker() != null && this.gameplay.getGame().getCurrentPlayer().getBoard().getDeckWorker().size() > i && this.gameplay.getGame().getCurrentPlayer().getBoard().getDeckWorker().get(i) != null){
                list[i] = this.gameplay.getGame().getCurrentPlayer().getBoard().getDeckWorker().get(i).getId();
            } else {
                list[i] = 0;
            }
        }

        cw1.setIcon(new ImageIcon("../data/images/cartes/workers/" + list[0] + ".png"));
        cw2.setIcon(new ImageIcon("../data/images/cartes/workers/" + list[1] + ".png"));
        cw3.setIcon(new ImageIcon("../data/images/cartes/workers/" + list[2] + ".png"));
        cw4.setIcon(new ImageIcon("../data/images/cartes/workers/" + list[3] + ".png"));
        cw5.setIcon(new ImageIcon("../data/images/cartes/workers/" + list[4] + ".png"));

        cb1 = new JLabel();
        cb1.setBounds(160, 217, 170, 170);
        cb2 = new JLabel();
        cb2.setBounds(520, 217, 170, 170);
        cb3 = new JLabel();
        cb3.setBounds(880, 217, 170, 170);
        cb4 = new JLabel();
        cb4.setBounds(1240, 217, 170, 170);
        cb5 = new JLabel();
        cb5.setBounds(1600, 217, 170, 170);

        int[] listBis = new int[5];
        for (int i = 0; i < 5; i++) {
            if(this.gameplay.getGame().getCurrentPlayer().getBoard().getDeckBuilding() != null && this.gameplay.getGame().getCurrentPlayer().getBoard().getDeckBuilding().size() > i && this.gameplay.getGame().getCurrentPlayer().getBoard().getDeckBuilding().get(i) != null){
                listBis[i] = this.gameplay.getGame().getCurrentPlayer().getBoard().getDeckBuilding().get(i).getId();
            } else {
                listBis[i] = 0;
            }
        }

        cb1.setIcon(new ImageIcon("../data/images/cartes/building/" + listBis[0] + ".png"));
        cb2.setIcon(new ImageIcon("../data/images/cartes/building/" + listBis[1] + ".png"));
        cb3.setIcon(new ImageIcon("../data/images/cartes/building/" + listBis[2] + ".png"));
        cb4.setIcon(new ImageIcon("../data/images/cartes/building/" + listBis[3] + ".png"));
        cb5.setIcon(new ImageIcon("../data/images/cartes/building/" + listBis[4] + ".png"));


        cp1 = new JLabel();
        cp1.setBounds(300, 550, 170, 170);
        cp2 = new JLabel();
        cp2.setBounds(660, 550, 170, 170);
        cp3 = new JLabel();
        cp3.setBounds(1020, 550, 170, 170);
        cp4 = new JLabel();
        cp4.setBounds(1380, 550, 170, 170);

        int[] listThird = new int[2];
        for (int i = 0; i < 2; i++) {
            if(this.gameplay.getGame().getCurrentPlayer().getBuilding() != null && this.gameplay.getGame().getCurrentPlayer().getBuilding().size() > i && this.gameplay.getGame().getCurrentPlayer().getBuilding().get(i) != null){
                listThird[i] = this.gameplay.getGame().getCurrentPlayer().getBuilding().get(i).getId();
            } else {
                listThird[i] = 0;
            }
        }

        int[] listFourth = new int[2];
        for (int i = 0; i < 2; i++) {
            if(this.gameplay.getGame().getCurrentPlayer().getWorker() != null && this.gameplay.getGame().getCurrentPlayer().getWorker().size() > i && this.gameplay.getGame().getCurrentPlayer().getWorker().get(i) != null){
                listFourth[i] = this.gameplay.getGame().getCurrentPlayer().getWorker().get(i).getId();
            } else {
                listFourth[i] = 0;
            }
        }

        cp1.setIcon(new ImageIcon("../data/images/cartes/building/" + listThird[0] + ".png"));
        cp2.setIcon(new ImageIcon("../data/images/cartes/building/" + listThird[1] + ".png"));
        cp3.setIcon(new ImageIcon("../data/images/cartes/workers/" + listFourth[0] + ".png"));
        cp4.setIcon(new ImageIcon("../data/images/cartes/workers/" + listFourth[1] + ".png"));


        boxDeckBuilder = new JComboBox<Integer>();
        for (int i = 0; i < this.gameplay.getCurrentPlayer().getBoard().getDeckBuilding().size(); i++) {
            boxDeckBuilder.addItem(i);
        }
        boxDeckBuilder.setBounds(10, 900, 250, 50);
        boxDeckBuilder.addActionListener(control);

        boxDeckWorker = new JComboBox<Integer>();
        for (int i = 0; i < this.gameplay.getCurrentPlayer().getBoard().getDeckWorker().size(); i++) {
            boxDeckWorker.addItem(i);
        }
        boxDeckWorker.setBounds(300, 900, 250, 50);
        boxDeckWorker.addActionListener(control);

        boxBuilder = new JComboBox<Integer>();
        for (int i = 0; i < this.gameplay.getGame().getCurrentPlayer().getBuilding().size(); i++) {
            boxBuilder.addItem(i);
        }
        boxBuilder.setBounds(590, 900, 250, 50);
        boxBuilder.addActionListener(control);

        boxWorker = new JComboBox<Integer>();
        for (int i = 0; i < this.gameplay.getCurrentPlayer().getWorker().size(); i++) {
            boxWorker.addItem(i);
        }
        boxWorker.setBounds(590, 960, 250, 50);
        boxDeckWorker.addActionListener(control);


        ImageIcon separationIcon = new ImageIcon(new ImageIcon("../data/images/trait.png").getImage().getScaledInstance(2800, 100, Image.SCALE_SMOOTH));
        JLabel separation = new JLabel(separationIcon);
        separation.setBounds(-100,450,2800,5);

        round = new JButton(new ImageIcon("../data/images/Round.png"));
        round.setBounds(10, 470, 250, 83);
        round.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        round.addActionListener(control);

        open = new JButton(new ImageIcon("../data/images/OpenSite.png"));
        open.setBounds(10, 800, 250, 83);
        open.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        open.addActionListener(control);

        hire = new JButton(new ImageIcon("../data/images/HireWorker.png"));
        hire.setBounds(300, 800, 250, 83);
        hire.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        hire.addActionListener(control);

        work = new JButton(new ImageIcon("../data/images/Work.png"));
        work.setBounds(590, 800, 250, 83);
        work.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        work.addActionListener(control);

        sell = new JButton(new ImageIcon("../data/images/Sell.png"));
        sell.setBounds(880, 800, 250, 83);
        sell.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        sell.addActionListener(control);

        buy = new JButton(new ImageIcon("../data/images/Buy.png"));
        buy.setBounds(1150, 800, 250, 83);
        buy.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        buy.addActionListener(control);

        settings = new JButton(new ImageIcon("../data/images/SettingsButton.png"));
        settings.setBounds(1430, 800, 250, 83);
        settings.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        settings.addActionListener(control);

        nameplayer = new JLabel();
        ecuplayer = new JLabel();
        pointplayer = new JLabel();
        actionplayer = new JLabel();

        nameplayer.setText(this.gameplay.getCurrentPlayer().getName().toUpperCase());
        nameplayer.setBounds(10, 420, 200, 500);

        ecuplayer.setText("Ecus : "+ this.gameplay.getCurrentPlayer().getCoin());
        ecuplayer.setBounds(10, 450, 200, 500);

        pointplayer.setText("Victory point : " +this.gameplay.getCurrentPlayer().getPoint());
        pointplayer.setBounds(10, 480, 200, 500);

        actionplayer.setText("Actions : " + this.gameplay.getCurrentPlayer().getNbActions());
        actionplayer.setBounds(10, 510, 200, 500);

        sellField = new JTextArea("Enter the amount of actions to sell here\nAnd after pressed the button Sell");
        sellField.setBounds(880, 900, 250, 50);
        sellField.addMouseListener(control);

        buyField = new JTextArea("Enter the amount of actions to buy here\nAnd after pressed the button Buy");
        buyField.setBounds(1150, 900, 250, 50);
        buyField.addMouseListener(control);

        mainBuilder = new JComboBox<String>();
        building.setBounds(1550, 550, 100, 50);
        mainBuilder.setBounds(1600, 550, 250, 50);

        for (int i = 2; i < this.gameplay.getCurrentPlayer().getBuilding().size(); i++) {
            String ret = this.gameplay.getCurrentPlayer().getBuilding().get(i).getName();
            mainBuilder.addItem(ret);
        }

        mainWorker = new JComboBox<String>();
        mainWorker.setBounds(1600, 700, 250, 50);
        worker.setBounds(1550, 700, 100, 50);

        for (int i = 0; i < this.gameplay.getCurrentPlayer().getWorker().size(); i++) {
            String ret = this.gameplay.getCurrentPlayer().getWorker().get(i).getName();
            ret = ret + "/" + this.gameplay.getCurrentPlayer().getWorker().get(i).getStone() + "/" + this.gameplay.getCurrentPlayer().getWorker().get(i).getWood() + "/" + this.gameplay.getCurrentPlayer().getWorker().get(i).getKnowledge() + "/" + this.gameplay.getCurrentPlayer().getWorker().get(i).getTiles();
            mainWorker.addItem(ret);
        }
        mainWorker.addActionListener(control);

        panel.add(worker);
        panel.add(building);
        panel.add(cw1);
        panel.add(cw2);
        panel.add(cw3);
        panel.add(cw4);
        panel.add(cw5);
        panel.add(cb1);
        panel.add(cb2);
        panel.add(cb3);
        panel.add(cb4);
        panel.add(cb5);
        panel.add(cp1);
        panel.add(cp2);
        panel.add(cp3);
        panel.add(cp4);
        panel.add(separation);
        panel.add(round);
        panel.add(boxDeckBuilder);
        panel.add(boxDeckWorker);
        panel.add(boxBuilder);
        panel.add(boxWorker);
        panel.add(mainBuilder);
        panel.add(mainWorker);
        panel.add(open);
        panel.add(hire);
        panel.add(work);
        panel.add(sell);
        panel.add(buy);
        panel.add(settings);
        panel.add(quit);
        panel.add(sellField);
        panel.add(buyField);
        panel.add(nameplayer);
        panel.add(ecuplayer);
        panel.add(pointplayer);
        panel.add(actionplayer);
        panel.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        panel.add(background);
        add(panel);
        setVisible(true);
    }

    public void refresh() {
        if (!this.gameplay.isWon()) {
            nameplayer.setText(this.gameplay.getCurrentPlayer().getName().toUpperCase());
            ecuplayer.setText("Ecus : "+ this.gameplay.getCurrentPlayer().getCoin());
            pointplayer.setText("Victory point : " +this.gameplay.getCurrentPlayer().getPoint());
            actionplayer.setText("Actions : " + this.gameplay.getCurrentPlayer().getNbActions());
            boxDeckBuilder.removeAllItems();
            boxDeckWorker.removeAllItems();
            boxWorker.removeAllItems();
            boxBuilder.removeAllItems();
            mainBuilder.removeAllItems();
            mainWorker.removeAllItems();
            for (int i = 0; i < this.gameplay.getCurrentPlayer().getBoard().getDeckBuilding().size(); i++) {
                boxDeckBuilder.addItem(i);
            }
            for (int i = 0; i < this.gameplay.getCurrentPlayer().getBoard().getDeckWorker().size(); i++) {
                boxDeckWorker.addItem(i);
            }
            for (int i = 0; i < this.gameplay.getGame().getCurrentPlayer().getBuilding().size(); i++) {
                boxBuilder.addItem(i);
            }
            for (int i = 0; i < this.gameplay.getCurrentPlayer().getWorker().size(); i++) {
                boxWorker.addItem(i);
            }
            for (int i = 2; i < this.gameplay.getCurrentPlayer().getWorker().size(); i++) {
                String ret = this.gameplay.getCurrentPlayer().getWorker().get(i).getName();
                ret = ret + "/" + this.gameplay.getCurrentPlayer().getWorker().get(i).getStone() + "/" + this.gameplay.getCurrentPlayer().getWorker().get(i).getWood() + "/" + this.gameplay.getCurrentPlayer().getWorker().get(i).getKnowledge() + "/" + this.gameplay.getCurrentPlayer().getWorker().get(i).getTiles();
                mainWorker.addItem(ret);
            }
            for (int i = 2; i < this.gameplay.getCurrentPlayer().getBuilding().size(); i++) {
                String ret = this.gameplay.getCurrentPlayer().getBuilding().get(i).getName();
                mainBuilder.addItem(ret);
            }

            int[] list = new int[5];
            for (int i = 0; i < 5; i++) {
                if(this.gameplay.getGame().getCurrentPlayer().getBoard().getDeckWorker() != null && this.gameplay.getGame().getCurrentPlayer().getBoard().getDeckWorker().size() > i && this.gameplay.getGame().getCurrentPlayer().getBoard().getDeckWorker().get(i) != null){
                    list[i] = this.gameplay.getGame().getCurrentPlayer().getBoard().getDeckWorker().get(i).getId();
                } else {
                    list[i] = 0;
                }
            }

            cw1.setIcon(new ImageIcon("../data/images/cartes/workers/" + list[0] + ".png"));
            cw2.setIcon(new ImageIcon("../data/images/cartes/workers/" + list[1] + ".png"));
            cw3.setIcon(new ImageIcon("../data/images/cartes/workers/" + list[2] + ".png"));
            cw4.setIcon(new ImageIcon("../data/images/cartes/workers/" + list[3] + ".png"));
            cw5.setIcon(new ImageIcon("../data/images/cartes/workers/" + list[4] + ".png"));

            int[] listBis = new int[5];
            for (int i = 0; i < 5; i++) {
                if(this.gameplay.getGame().getCurrentPlayer().getBoard().getDeckBuilding() != null && this.gameplay.getGame().getCurrentPlayer().getBoard().getDeckBuilding().size() > i && this.gameplay.getGame().getCurrentPlayer().getBoard().getDeckBuilding().get(i) != null){
                    listBis[i] = this.gameplay.getGame().getCurrentPlayer().getBoard().getDeckBuilding().get(i).getId();
                } else {
                    listBis[i] = 0;
                }
            }

            cb1.setIcon(new ImageIcon("../data/images/cartes/building/" + listBis[0] + ".png"));
            cb2.setIcon(new ImageIcon("../data/images/cartes/building/" + listBis[1] + ".png"));
            cb3.setIcon(new ImageIcon("../data/images/cartes/building/" + listBis[2] + ".png"));
            cb4.setIcon(new ImageIcon("../data/images/cartes/building/" + listBis[3] + ".png"));
            cb5.setIcon(new ImageIcon("../data/images/cartes/building/" + listBis[4] + ".png"));

            int[] listThird = new int[2];
            for (int i = 0; i < 2; i++) {
                if(this.gameplay.getGame().getCurrentPlayer().getBuilding() != null && this.gameplay.getGame().getCurrentPlayer().getBuilding().size() > i && this.gameplay.getGame().getCurrentPlayer().getBuilding().get(i) != null){
                    listThird[i] = this.gameplay.getGame().getCurrentPlayer().getBuilding().get(i).getId();
                } else {
                    listThird[i] = 0;
                }
            }
    
            int[] listFourth = new int[2];
            for (int i = 0; i < 2; i++) {
                if(this.gameplay.getGame().getCurrentPlayer().getWorker() != null && this.gameplay.getGame().getCurrentPlayer().getWorker().size() > i && this.gameplay.getGame().getCurrentPlayer().getWorker().get(i) != null){
                    listFourth[i] = this.gameplay.getGame().getCurrentPlayer().getWorker().get(i).getId();
                } else {
                    listFourth[i] = 0;
                }
            }
    
            cp1.setIcon(new ImageIcon("../data/images/cartes/building/" + listThird[0] + ".png"));
            cp2.setIcon(new ImageIcon("../data/images/cartes/building/" + listThird[1] + ".png"));
            cp3.setIcon(new ImageIcon("../data/images/cartes/workers/" + listFourth[0] + ".png"));
            cp4.setIcon(new ImageIcon("../data/images/cartes/workers/" + listFourth[1] + ".png"));

        } else {
            this.control.actionEnd();
        }
    }
}
