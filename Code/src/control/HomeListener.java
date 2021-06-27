package control;
import java.awt.event.*;
import view.*;

/**
 * Class that handles every button event in the Home window
 * @author L.DAMIEN
 */
public class HomeListener implements ActionListener {

	private Home h;
    
    /**
     * Constructor that initialize the controller with parameter provide such as the main frame
     * @param home the Home of application
     */
    public HomeListener(Home home) {
		this.h = home;
    }
    
    /**
     * Detect which button is pressed and perform the action linked to this button
     * @param e the event detected
     */
    public void actionPerformed(ActionEvent e){  
        if(e.getSource() == h.getPlay()){
            h.goToPlay();
        } else if (e.getSource() == h.getSettings()) {
            h.goToSettings();
        } else if (e.getSource() == h.getRules()) {
            h.goToRules();
        } else if (e.getSource() == h.getQuit()) {
            System.exit(0);
        }
    }
}