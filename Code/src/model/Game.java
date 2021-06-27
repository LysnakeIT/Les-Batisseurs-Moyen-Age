package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class allows you to create a new Game object
 * @author L.DAMIEN
 */
public class Game implements Serializable {
    
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Board board;
    private Mode mode;

    /**
     * Constructor of the class Game
     * @param names the names of the players
     * @param mode the mode of the current game
     */
    public Game(ArrayList<String> names, Mode mode) {
        if (names == null || mode == null) {
			throw new IllegalArgumentException("Game(): a parameter is null");
		} else {
            this.board = new Board();
            this.players = new ArrayList<Player>();
            this.mode = mode;
            this.createPlayers(names, this.mode);
            this.currentPlayer = this.getRandomPlayer();
            System.out.println(this.currentPlayer.getName());
        }
    }

    /**
     * Constructor use to load a game
     */
    public Game(){}

    /**
     * Method that creates 2 players automatically in relation to the number player and of the number of AI player
     * @param names the ArrayList of name
     * @param mode the mode of the current game
     */
    private void createPlayers(ArrayList<String> names, Mode mode) {
        if (names == null || mode == null) {
			throw new IllegalArgumentException("createPlayers(): a parameter is null");
		} else {
            String chaine = mode.toString();
            int nbHumanPlayer = this.nbOccurences("H", chaine);
            for (int i = 0; i < nbHumanPlayer; i++) {
                this.players.add(new HumanPlayer(names.get(i), this.board, 10, 3, 0, this));
            }
            for (int j = nbHumanPlayer ; j < names.size(); j++) {
                this.players.add(new AutoPlayer(names.get(j), this.board, 10, 3, 0, this));
            }
        }
    }

    /**
     * Calculate the number of Human Player (H) and AutoPlayer (A) in the mode settings
     * @param chaine the H or A to search
     * @param Text the mode string
     * @return the number of player of type H or A
     */
    private int nbOccurences(String chaine, String Text) {
        int nbOccurences = 0;
        int position = 0;
        while ((Text.contains(chaine)) && (!chaine.equals(""))) {
            position = Text.indexOf(chaine);
            Text = Text.substring(position + chaine.length(), Text.length());
            nbOccurences++;
        }
        return nbOccurences;
    }

    /**
     * Method which launches the game and which takes care of changing the player whose turn it is to play
     */
    public void start() {
		while(isWon() != true) {
			for(int i = 0; i < this.players.size(); i++) {
				System.out.println("It's " + this.currentPlayer.getName() + "'s turn to play");
				this.currentPlayer.play();
				changeCurrent();
			}
            for(int j = 0; j < this.players.size(); j++) {
                this.players.get(j).addActions(3);
			}
            
		} 
		end();
    }

    /**
     * Method announcing the end of the game and designates the name of the winner.
     */
    public void end() {
        String ret = "";
        for(int i = 0; i < this.players.size(); i++) {
            int finalPoint = this.players.get(i).getPoint() + (this.players.get(i).getCoin() / 10 );
            this.players.get(i).setPoint(finalPoint);
        }
        int k = 0;
        for(int j = 1; j < this.players.size(); j++) {
            if (this.players.get(j).getPoint() > this.players.get(k).getPoint()) {
                k = j;
                ret = this.players.get(j).getName();
            } else if (this.players.get(j).getPoint() == this.players.get(k).getPoint()) {
                if (this.players.get(j).getCoin() > this.players.get(k).getCoin()) {
                    k = j;
                    ret = this.players.get(j).getName();
                } else {
                    ret = this.players.get(k).getName();
                }
            } else {
                ret = this.players.get(k).getName();
            }
        }
        System.out.println("----------------------------------------------------------------------");
		System.out.println(ret +" win the game !");
        System.out.println("End of the game");
		System.out.println("----------------------------------------------------------------------");
        System.exit(0);
    }

    /**
     * Method that check if there is a winner
     * @return true if we are a winner, else false
     */
    public boolean isWon() {
        boolean ret = false;
        for(int i = 0; i < this.players.size(); i++) {
            if (this.players.get(i).getPoint() >= 17) {
                ret = true;
            }
        }
        return ret;
    }

    /**
     * Switches the current player to an another player
     */
    public void changeCurrent() {
        int i = 0;       
        boolean find = false;
        while (i < this.players.size() && find == false) {
            if (this.players.get(i) == this.currentPlayer && i != this.players.size()-1) {
                this.currentPlayer = this.players.get(i+1);
                find = true;
            } else if (this.players.get(i) == this.currentPlayer && i == this.players.size()-1) {
                this.currentPlayer = this.players.get(0);
                find = true;
            }
            i++;
        }
    }

    /**
     * Choose a player randomly between the players
     * @return the player choosen
     */
	private Player getRandomPlayer(){
        Random random = new Random();
		Player choosen = null;
        int ret = random.nextInt(this.players.size());
        choosen = this.players.get(ret);
		return choosen;
	}

    /**
     * Get the current player
     * @return the current player
     */
	public Player getCurrentPlayer(){
		return this.currentPlayer;
	}

    /**
     * Get the list of player
     * @return the list player
     */
	public ArrayList<Player> getPlayer(){
		return this.players;
	}

    /**
     * Get the current game
     * @return the current game 
     */
    public Game getGame() {
        return this;
    }
}
