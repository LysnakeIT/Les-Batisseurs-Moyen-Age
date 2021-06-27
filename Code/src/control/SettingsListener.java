package control;
import java.awt.event.*;
import view.*;
import java.io.IOException;
import java.io.File;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioSystem;


/**
 * Class that handles every button event in the SaveListListener window
 * @author L.DAMIEN
 */
public class SettingsListener implements ActionListener {

	private Home h;
    private Settings s;
    private Clip clip;
    
    /**
     * Constructor that initialize the controller with parameter provide such as the main frame and the current frame
     * @param home the Home of application
     * @param settings the current frame
     */
    public SettingsListener(Home home, Settings settings) {
		this.h = home;
        this.s = settings;
    }
    
    /**
     * Detect which button is pressed and perform the action linked to this button
     * @param e the event detected
     */
    public void actionPerformed(ActionEvent e){  
        if (e.getSource() == s.getBack()){
            try {
                h.goToBackAtHome();
            } catch (Exception e1) {
            }
        } else if (e.getSource() == s.getActivate()) {
            s.getActivate().setEnabled(false);
            s.getActivate().setSelected(true);
            s.getDesactivate().setSelected(false);
            s.getDesactivate().setEnabled(true);
            try{
                this.clip = AudioSystem.getClip();
                this.clip.open(AudioSystem.getAudioInputStream(new File("../data/music/music.wav")));
                this.clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch(LineUnavailableException eL){
                eL.printStackTrace();
            } catch(UnsupportedAudioFileException eU){
                eU.printStackTrace();
            } catch(IOException eI){
                eI.printStackTrace();
            }
        } else if (e.getSource() == s.getDesactivate()) {
            this.clip.stop();
            s.getActivate().setEnabled(true);
            s.getActivate().setSelected(false);
        }
    }

    /**
     * Activate or desactivate the music in game
     * @param choice the choice of the settings
     */
    public void inGame(int choice) {
        if (choice == 0) {
            try{
                this.clip = AudioSystem.getClip();
                this.clip.open(AudioSystem.getAudioInputStream(new File("../data/music/music.wav")));
                this.clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch(LineUnavailableException eL){
                eL.printStackTrace();
            } catch(UnsupportedAudioFileException eU){
                eU.printStackTrace();
            } catch(IOException eI){
                eI.printStackTrace();
            }
        } else if (choice == 1) {
            System.out.println("ok1");
            this.clip.stop();
        }
    }
}