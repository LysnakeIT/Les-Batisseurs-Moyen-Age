package control;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

import model.*;
import view.*;
import util.*;

/**
 * Class that handles every button event in the Game window
 * @author L.DAMIEN
 */
public class GameWindowListener implements ActionListener, MouseListener {

	private GameWindow cw;
    private Settings s;
    private SettingsListener sl;
    private Home h;
    
    /**
     * Constructor that initialize the controller with parameter provide such as the main frame and the current frame
     * @param GameWindow the current frame
     */
    public GameWindowListener(GameWindow GameWindow) {
		this.cw = GameWindow;
        this.s = new Settings(h);
        this.sl = new SettingsListener(h,s);
    }

    /**
     * Detect when the mouse clicked in a JTextArea
     * @param e the event detected
     */
    public void mouseClicked(MouseEvent e){
        if(e.getSource() == cw.getSellAmount()) {
            cw.getSellAmount().setText("");
        } else if (e.getSource() == cw.getBuyAmount()) {
            cw.getBuyAmount().setText("");
        }
    }
    
    /**
     * Detect which button is pressed and perform the action linked to this button
     * @param e the event detected
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cw.getOpen()){
            int cardBuildingDeckId = (int) cw.getBoxDeckBuilder().getSelectedItem();
            cw.getGame().getCurrentPlayer().removeActions(1);
            cw.getGame().getCurrentPlayer().startBuilding(cw.getGame().getCurrentPlayer().getBoard().getDeckBuilding().get(cardBuildingDeckId));
            cw.getGame().getCurrentPlayer().getBoard().getDeckBuilding().remove(cardBuildingDeckId);
            cw.getGame().getCurrentPlayer().getBoard().createDeckBuilding();
            cw.refresh();
        } else if (e.getSource() == cw.getHire()) {
            int cardWorkerDeckId = (int) cw.getBoxDeckWorker().getSelectedItem();
            cw.getGame().getCurrentPlayer().removeActions(1);
            cw.getGame().getCurrentPlayer().recruitWorker((Worker)cw.getGame().getCurrentPlayer().getBoard().getDeckWorker().get(cardWorkerDeckId));
            cw.getGame().getCurrentPlayer().getBoard().getDeckWorker().remove(cardWorkerDeckId);
            cw.getGame().getCurrentPlayer().getBoard().createDeckWorker();
            cw.refresh();
        } else if (e.getSource() == cw.getWork()) {
            int cardBuildingId = (int) cw.getBoxBuilder().getSelectedItem();
            int cardWorkerId = (int) cw.getBoxWorker().getSelectedItem();
            if (cw.getGame().getCurrentPlayer().getBuilding().get(cardBuildingId).getWorkers().size() > 0) {
                cw.getGame().getCurrentPlayer().work(cw.getGame().getCurrentPlayer().getWorker().get(cardWorkerId), cw.getGame().getCurrentPlayer().getBuilding().get(cardBuildingId));
                cw.getGame().getCurrentPlayer().removeActions(1);
            } else  {
                cw.getGame().getCurrentPlayer().work(cw.getGame().getCurrentPlayer().getWorker().get(cardWorkerId), cw.getGame().getCurrentPlayer().getBuilding().get(cardBuildingId));
                cw.getGame().getCurrentPlayer().removeActions(1);
            }
            cw.refresh();
        } else if (e.getSource() == cw.getSell()) {
            cw.getGame().getCurrentPlayer().sellAction(Integer.parseInt(cw.getSellAmount().getText()));
            cw.getSellAmount().setText("Enter the amount of actions to sell here\nAnd after pressed the button Buy");
            cw.refresh();
        } else if (e.getSource() == cw.getBuy()) {
            cw.getGame().getCurrentPlayer().buyAction(Integer.valueOf(cw.getBuyAmount().getText()));
            cw.getBuyAmount().setText("Enter the amount of actions to buy here\nAnd after pressed the button Buy");
            cw.refresh();
        } else if (e.getSource() == cw.getSettings()) {
            Object[] options = {"Activate music","Desactivate musique!"};
            int n = JOptionPane.showOptionDialog(cw,"What do you want","Settings",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
            if (n == 0) {
                sl.inGame(0);
            } else {
                sl.inGame(1);
            }
        } else if (e.getSource() == cw.getBack()) {
            int n = JOptionPane.showConfirmDialog(cw,"Do you want to save?","Exit",JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu_MM_dd-HH_mm_ss");
                LocalDateTime now = LocalDateTime.now();
                Backup.save(dtf.format(now).toString(), cw.getGame());
                System.exit(0);
            } else {
                System.exit(0);
            }
        } else if (e.getSource() == cw.getRound()) {
            if (cw.getGame().getCurrentPlayer().getNbActions() == 0) {
                cw.getGame().getCurrentPlayer().addActions(3);
                cw.getGame().changeCurrent();
                if (cw.getGame().getCurrentPlayer() instanceof AutoPlayer) {
                    cw.getGame().getCurrentPlayer().play();
                    cw.getGame().getCurrentPlayer().addActions(3);
                    cw.getGame().changeCurrent();
                }
                cw.refresh();
            }
        }
    }

    /**
     * Search the winner of the game and display a pop-up with his name.
     */
    public void actionEnd() {
        String ret = "";
        for(int i = 0; i < cw.getGame().getPlayer().size(); i++) {
            int finalPoint = cw.getGame().getPlayer().get(i).getPoint() + (cw.getGame().getPlayer().get(i).getCoin() / 10 );
            cw.getGame().getPlayer().get(i).setPoint(finalPoint);
        }
        int k = 0;
        for(int j = 1; j < cw.getGame().getPlayer().size(); j++) {
            if (cw.getGame().getPlayer().get(j).getPoint() > cw.getGame().getPlayer().get(k).getPoint()) {
                k = j;
                ret = cw.getGame().getPlayer().get(j).getName();
            } else if (cw.getGame().getPlayer().get(j).getPoint() == cw.getGame().getPlayer().get(k).getPoint()) {
                if (cw.getGame().getPlayer().get(j).getCoin() > cw.getGame().getPlayer().get(k).getCoin()) {
                    k = j;
                    ret = cw.getGame().getPlayer().get(j).getName();
                } else {
                    ret = cw.getGame().getPlayer().get(k).getName();
                }
            } else {
                ret = cw.getGame().getPlayer().get(k).getName();
            }
        }
        JOptionPane.showMessageDialog(cw, ret + " win the game !");
        System.exit(0);
    }
    
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}