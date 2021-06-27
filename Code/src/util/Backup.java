package util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.Game;

/**
 * This class allows you to save a game or load a game
 * @author L.DAMIEN
 */
public class Backup {

    /**
     * Method use to save object, checks the parameter provide
     * @param fileName the name of the file
     * @param game the instance of the game to save
     */
    public static void save(String fileName, Game game) {
        if (fileName == null || game == null) {
			throw new IllegalArgumentException("save(): error on a parameter");
		} else {
            try {
                FileOutputStream fos = new FileOutputStream("../data/saves/" + fileName);
                ObjectOutputStream file = new ObjectOutputStream(fos);
                file.writeObject(game);
                file.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Method use to load object, checks the parameter provide
     * @param fileName the name of the file
     * @return the game loading
     */
    public static Game load(String fileName) {
        Game game = new Game();
        if (fileName == null) {
			throw new IllegalArgumentException("load(): error on a parameter");
		} else {
            try {
                FileInputStream fis = new FileInputStream("../data/saves/" + fileName);
                ObjectInputStream file = new ObjectInputStream(fis);

                game = (Game) file.readObject();

                file.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }  catch (IOException e) {
                e.printStackTrace();
            }  catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return game;
    }
}