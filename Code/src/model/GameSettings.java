package model;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import util.Backup;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioSystem;

/**
 * This class allows you to create a new GameSettings object
 * @author L.DAMIEN
 */
public class GameSettings {

	private ArrayList<String> name;
     private Game gameplay;
     private Mode mode;
     private Clip clip;

    /**
     * Constructor of a GameSettings object, create the ArrayList of name's player and display the menu
     */
	public GameSettings() {
          this.name = new ArrayList<String>();
          this.menu();
	}
     
     /**
      * Asks players for the desired configuration for the game
      */
     private void configuration() {
          boolean ret = false;
          int numberOfPlayer = 0;
          System.out.println("\t\t-------------------- New Game --------------------");
          System.out.println("You must choose the number of players and the number of AIs among these players, \nFor this you must type H for a human and A for an AI, type the number of humans then the number of AI \nFor example HHA (2 human player & 1 auto player)");
          System.out.println("\nIf you want back at the choice of type of the game type back");
          Scanner scan = new Scanner(System.in);
          while (ret == false) {
               System.out.println("Your choice : ");
               String choice = scan.next();
               numberOfPlayer = choice.length();
               if (choice.length() == 4 && !choice.equalsIgnoreCase("back")) {
                    if (choice.equals(Mode.HHHH.toString())) {
                         this.mode = Mode.HHHH;
                         ret = true;
                    } else if (choice.equals(Mode.HHHA.toString())) {
                         this.mode = Mode.HHHA;
                         ret = true;
                    } else if (choice.equals(Mode.HHAA.toString())) {
                         this.mode = Mode.HHAA;
                         ret = true;
                    } else if (choice.equals(Mode.HAAA.toString())) {
                         this.mode = Mode.HAAA;
                         ret = true;
                    }
               } else if (choice.length() == 3) {
                    if (choice.equals(Mode.HHH.toString())) {
                         this.mode = Mode.HHH;
                         ret = true;
                    } else if (choice.equals(Mode.HHA.toString())) {
                         this.mode = Mode.HHA;
                         ret = true;
                    } else if (choice.equals(Mode.HAA.toString())) {
                         this.mode = Mode.HAA;
                         ret = true;
                    }
               } else if (choice.length() == 2) {
                    if (choice.equals(Mode.HH.toString())) {
                         this.mode = Mode.HH;
                         ret = true;
                    } else if (choice.equals(Mode.HA.toString())) {
                         this.mode = Mode.HA;
                         ret = true;
                    }
               } else if (choice.equalsIgnoreCase("back")) {
                    this.chooseGame();
                    ret = true;
               } else {
                    System.out.println("Please choose a valid number : ");
                    System.out.println();
               }
          }
          System.out.println();
          System.out.println("Now enter the nicknames of the players in the order of the configuration, i.e. humans then AIs: ");
          for (int i = 0; i < numberOfPlayer; i++) {
               this.name.add(scan.next());
          }
          System.out.println(this.printConfiguration());
          this.gameplay = new Game(this.name, this.mode);
          this.gameplay.start();
          scan.close();
     }

    /**
     * Asks players what type of game they want to play
     */
	private void chooseGame() {
          boolean ret = false;
          System.out.println(" _______________");
          System.out.println("| 1 - New game  |");
          System.out.println("| 2 - Load game |");
          System.out.println("| 3 - Back      |");
          System.out.println(" _______________");
          Scanner scan = new Scanner(System.in);
          while (ret == false) {
               System.out.println("Your choice : ");
               int choice = scan.nextInt();
               if (choice == 1) {
                    this.configuration();
                    ret = true;
               } else if ( choice == 2) {
                    Game load = new Game();
                    File dossier = new File("../data/saves/");
                    String[] contenu = dossier.list();
                    System.out.println("Type the name of the save");
                    for (String s : contenu) {
                         String save = String.format(s.substring(0,s.length()-4));
                         System.out.println(save);
                    }
                    String backup = scan.next();
                    load = Backup.load(backup);
                    load.start();
                    ret = true;
               } else if ( choice == 3) {
                    this.menu();
                    ret = true;
               } else {
                    System.out.println("Please choose a valid number : ");
               }
          }
          scan.close();
	}

     /**
      * Display the application menu
      */
     public void menu() {
          boolean ret = false;
          System.out.println("\t\t-------------------- Welcome to Les Batisseurs : Moyen-Age --------------------");
          System.out.println();
          System.out.println("1 - Play");
          System.out.println("2 - Settings (Music)");
          System.out.println("3 - Rules");
          System.out.println("4 - Quit");
          System.out.println();
          Scanner scan = new Scanner(System.in);
          while (ret == false) {
               System.out.println("Your choice : ");
               int choice = scan.nextInt();
               if (choice == 1) {
                    this.chooseGame();
                    ret = true;
               } else if (choice == 2) {
                    System.out.println("if you want enable the music type start if you want to disable type stop");
                    String ret2 = scan.next();
                    if (ret2.equals("start")) {
                         this.settings("start");
                    } else if (ret2.equals("stop")) {
                         this.settings("stop");
                    }
               } else if (choice == 3) {
                    this.description();
                    ret = true;
               } else if (choice == 4) {
                    System.exit(0);
               } else {
                    System.out.println("Please choose a valid number : ");
               }
          }
          scan.close();
     }

     private void settings(String process) {
          if (process == null) {
			throw new IllegalArgumentException("Settings(): a parameter is null");
		} else {
               if (process.equals("start")) {
                    try{
                         this.clip = AudioSystem.getClip();
                         this.clip.open(AudioSystem.getAudioInputStream(new File("../data/music/music.wav")));
                         this.clip.loop(Clip.LOOP_CONTINUOUSLY);
                    } catch(LineUnavailableException e){
                         e.printStackTrace();
                    } catch(UnsupportedAudioFileException e){
                         e.printStackTrace();
                    } catch(IOException e){
                         e.printStackTrace();
                    }
               } else if (process.equals("stop")) {
                    this.clip.stop();
               }
          }
          System.out.println();
          System.out.println("1 - Play");
          System.out.println("2 - Settings (Music)");
          System.out.println("3 - Rules");
          System.out.println("4 - Quit");
          System.out.println();
     }

     /**
      * Describing the rules of the game.
      */
     private void description(){
          System.out.println(" ________________________________________________________________________________________________________________________________________________");
          System.out.println("|                                                                                                                                                |");
          System.out.println("|     ---------------------------------- Les Batisseurs : Moyen-Age ----------------------------------                                           |");
          System.out.println("| The winner is the first player to reach 17 points without counting the coins points                                                            |");
          System.out.println("|                                                                                                                                                |");
          System.out.println("| --- Rules :                                                                                                                                    |");
          System.out.println("|                                                                                                                                                |");
          System.out.println("|    - Open a site :                                                                                                                             |");
          System.out.println("|   |---------- Opening a building site costs an action.the player takes a building from the deck and opens the building site.                  |");
          System.out.println("|                The player takes a building from the deck and opens the building site.                                                          |");
          System.out.println("|                The player can open several construction sites.                                                                                 |");
          System.out.println("|    - Recruit a  worker:                                                                                                                        |");
          System.out.println("|   |---------- Recruiting a worker costs one action, the player takes a worker from the deck, he can take as many as he has actions to do so   |");
          System.out.println("|    - Send a worker to work :                                                                                                                   |");
          System.out.println("|   |---------- Send a worker to work at a variable cost.                                                                                       |");
          System.out.println("|                Sending a worker during the same turn costs 1 action, a second during the same turn 2 actions and so on.                        |");
          System.out.println("|                The player pays the number of shields and as long as the site is not finished, the worker cannot be moved.                      |");
          System.out.println("|    - Sell actions against coins :                                                                                                              |");
          System.out.println("|   |---------- 1 action for 1 coin, 2 actions for 3 coins and 3 action for 6 coins                                                             |");
          System.out.println("|                                                                                                                                                |");
          System.out.println(" ________________________________________________________________________________________________________________________________________________");
          System.out.println("\n\n--- TYPE SOMETHING TO RETURN AT THE MENU ---");
          Scanner scan = new Scanner(System.in);
          if(scan.hasNext()){
               this.menu();
          }
          scan.close();
     }

     /**
      * Print the information of the configuration
      * @return the configuration informations
      */
     private String printConfiguration() {
          String ret = "___________________________ \n";
          ret = ret + "The mode : " + this.mode + "\nThe name of the players : \n";
          for (int i = 0; i < this.name.size(); i++) {
               ret = ret + this.name.get(i) + "\n";
          }
          return ret;
     }
}