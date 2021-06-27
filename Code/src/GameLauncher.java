import model.*;
import view.*;
import java.util.Scanner;

public class GameLauncher {
    public static void main(String[] args) {
        Scanner ask = new Scanner(System.in);
        System.out.println("\t\t-------------------- Welcome to Les Batisseurs : Moyen-Age --------------------");
        System.out.println("If you want to play in graphical version type graphical, otherwise type console to play in text version.");
        String s = ask.next();
        if (s.equalsIgnoreCase("graphical")) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Home("Les bâtisseurs : Moyen - Age");
                }
            });
        } else if (s.equalsIgnoreCase("console"))  {
            GameSettings batisseurs = new GameSettings();
        }
    }
}
