package control;
import java.awt.event.*;

import model.Game;
import util.Backup;
import view.*;

/**
 * Class that handles every button event in the SaveListListener window
 * @author L.DAMIEN
 */
public class SaveListListener implements ActionListener {

	private Home h;
    private SaveList sl;
    
    /**
     * Constructor that initialize the controller with parameter provide such as the main frame and the current frame
     * @param home the Home of application
     * @param saveList the current frame
     */
    public SaveListListener(Home home, SaveList saveList) {
		this.h = home;
        this.sl = saveList;
    }
    
    /**
     * Detect which button is pressed and perform the action linked to this button
     * @param e the event detected
     */
    public void actionPerformed(ActionEvent e){  
        if (e.getSource() == sl.getBack()){
            h.goToBackChoose();
        } else if (e.getSource() == sl.getLoad()) {
            String backup = sl.getList().getSelectedValue().toString();
            backup = backup.substring(13);
            Game loadBackup = new Game();
            loadBackup = Backup.load(backup);
            h.goToGame(loadBackup);
        }
    }
}