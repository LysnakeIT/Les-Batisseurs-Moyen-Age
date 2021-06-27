package control;
import java.awt.event.*;
import java.util.ArrayList;
import model.Game;
import model.Mode;
import model.Player;
import view.*;

/**
 * Class that handles every button event and every mouse event in the GameMode window
 * @author L.DAMIEN
 */
public class GameModeWindowListener implements ActionListener, MouseListener {

	private GameModeWindow gmw;
    private Home h;
    
    /**
     * Constructor that initialize the controller with parameter provide such as the main frame and the current frame
     * @param gameModeWindow the current frame
     * @param h the Home of application
     */
    public GameModeWindowListener(GameModeWindow gameModeWindow , Home h) {
		this.gmw = gameModeWindow;
        this.h = h;
    }

    /**
     * Detect when the mouse clicked in a JTextField
     * @param e the event detected
     */
    public void mouseClicked(MouseEvent e){
        if(e.getSource() == gmw.getPlayer1()) {
            gmw.getPlayer1().setText("");
        } else if (e.getSource() == gmw.getPlayer2()) {
            gmw.getPlayer2().setText("");
        } else if (e.getSource() == gmw.getPlayer3()) {
            gmw.getPlayer3().setText("");
        } else if (e.getSource() == gmw.getPlayer4()) {
            gmw.getPlayer4().setText("");
        }
    }
    
    /**
     * Detect which button is pressed and perform the action linked to this button
     * @param e the event detected
     */
    public void actionPerformed(ActionEvent e){  
        String mode = gmw.getMode().getSelectedItem().toString();
        if (e.getSource() == gmw.getMode()) {
            mode = gmw.getMode().getSelectedItem().toString();
            if(mode.length() == 3) {
                gmw.getPlayer1().setEditable(true);
                gmw.getPlayer2().setEditable(true);
                gmw.getPlayer3().setEditable(true);
                gmw.getPlayer4().setEditable(false);
            } else if (mode.length() == 2) {
                gmw.getPlayer1().setEditable(true);
                gmw.getPlayer2().setEditable(true);
                gmw.getPlayer3().setEditable(false);
                gmw.getPlayer4().setEditable(false);
            } else if (mode.length() == 4) {
                gmw.getPlayer1().setEditable(true);
                gmw.getPlayer2().setEditable(true);
                gmw.getPlayer3().setEditable(true);
                gmw.getPlayer4().setEditable(true);
            } 
        } else if (e.getSource() == gmw.getPlay()) {
            ArrayList<String> names = new ArrayList<String>();
            if (mode.length() == 2) {
                names.add(gmw.getPlayer1().getText());
                names.add(gmw.getPlayer2().getText());
            } else if (mode.length() == 3) {
                names.add(gmw.getPlayer1().getText());
                names.add(gmw.getPlayer2().getText());
                names.add(gmw.getPlayer3().getText());
            } else if (mode.length() == 4) {
                names.add(gmw.getPlayer1().getText());
                names.add(gmw.getPlayer2().getText());
                names.add(gmw.getPlayer3().getText());
                names.add(gmw.getPlayer4().getText());
            }
            Game game = new Game(names, Mode.valueOf(gmw.getMode().getSelectedItem().toString()));
            h.goToGame(game);
        } else if (e.getSource() == gmw.getBack()) {
            h.goToBackChoose();
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}