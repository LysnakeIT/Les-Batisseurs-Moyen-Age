package control;
import java.awt.event.*;
import view.*;

/**
 * Class that handles every button event in the Rules window
 * @author L.DAMIEN
 */
public class RulesListener implements ActionListener {

	private Home h;
    private RulesWindow rw;
    
    /**
     * Constructor that initialize the controller with parameter provide such as the main frame and the current frame
     * @param home the Home of application
     * @param rulesWindow the current frame
     */
    public RulesListener(Home home, RulesWindow rulesWindow) {
		this.h = home;
        this.rw = rulesWindow;
    }
    
    /**
     * Detect which button is pressed and perform the action linked to this button
     * @param e the event detected
     */
    public void actionPerformed(ActionEvent e){  
        if(e.getSource() == rw.getBack()){
            try {
                h.goToBackAtHome();
            } catch (Exception exception) {
            }
        }
    }
}