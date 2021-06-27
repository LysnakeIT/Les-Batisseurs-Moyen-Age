package control;
import java.awt.event.*;
import view.*;

/**
 * Class that handles every button event in the ChooseGame window
 * @author L.DAMIEN
 */
public class ChooseGameWindowListener implements ActionListener {

	private ChooseGameWindow cgw;
    private Home h;
    
    /**
     * Constructor that initialize the controller with parameter provide such as the main frame and the current frame
     * @param chooseGameWindow the current frame
     * @param h the Home of application
     */
    public ChooseGameWindowListener(ChooseGameWindow chooseGameWindow, Home h) {
		this.cgw = chooseGameWindow;
        this.h = h;
    }
    
    /**
     * Detect which button is pressed and perform the action linked to this button
     * @param e the event detected
     */
    public void actionPerformed(ActionEvent e) {  
        if(e.getSource() == cgw.getNewGame()){
            h.goToNewGame();
        } else if (e.getSource() == cgw.getLoadGame()) {
            h.goToLoad();
        } else if (e.getSource() == cgw.getBack()) {
            try {
                h.goToBackAtHome();
            } catch (Exception exception) {

            }
        }
    }
}