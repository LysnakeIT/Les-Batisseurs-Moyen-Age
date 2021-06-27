package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import util.Backup;
import java.io.IOException;
import java.io.File;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioSystem;

/**
 * This class allows you to create a new HumanPlayer object
 * @author L.DAMIEN
 */
public class HumanPlayer extends Player {

	private int tour;
	/**
	 * Constructor of HumanPlayer object
	 * @param name the name of the player
	 * @param board the board of the game
	 * @param coin the coin of the player
	 * @param nbActions the amount of actions of the player
	 * @param point the amount of victory point of player
	 * @param game the current game
	 */
	public HumanPlayer(String name, Board board, int coin, int nbActions, int point, Game game) {
		super(name, board, coin, nbActions, point, game);
		this.tour = 0;
	}

	/**
	 * Method that reads the actions requested by the player
	 */
	public void play() {
		while(this.getNbActions() > 0) {
			Scanner scan = new Scanner(System.in);
			this.getBoard().displayCards();
			System.out.println(this.toString());
			System.out.println("Choose an action");
			System.out.println("1 - Open a construction");
			System.out.println("2 - Recruit a worker");
			System.out.println("3 - Send a worker to work");
			System.out.println("4 - Buy actions against coins (ecus)");
			System.out.println("5 - Sell actions against coins (ecus)");
			System.out.println("6 - Settings (music disable)");
			System.out.println("7 - Quit the game");

			int action = 0;
			try {
				action = scan.nextInt();
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
			if (action == 1) {
				int id = -1;
				System.out.println("Enter the id of the building card");
				try {
					id = scan.nextInt();
				} catch(Exception e){
					System.out.println(e.getMessage());
				}
				if (id >= 0 && id <= 5) {
					this.startBuilding((IBuilding) this.getBoard().getDeckBuilding().get(id));
					this.getBoard().getDeckBuilding().remove(id);
					this.getBoard().createDeckBuilding();
					this.removeActions(1);
				}
			} else if (action == 2) {
				int idW = -1;
				System.out.println("Enter the id of the worker card");
				try {
					idW = scan.nextInt();
				} catch(Exception e){
					System.out.println(e.getMessage());
				}
				if (idW >= 0 && idW <= 5) {
					this.recruitWorker((Worker) super.getBoard().getDeckWorker().get(idW));
					this.getBoard().getDeckWorker().remove(idW);
					this.getBoard().createDeckWorker();
					this.removeActions(1);
				}
			} else if (action == 3) {
				int round = -1;
				int idW = -1;
				int idC = -1;
				System.out.println("Enter the id of the worker card");
				try {
					idW = scan.nextInt();
					System.out.println("Enter the id of the building card");
					idC = scan.nextInt();
				} catch(Exception e){
					System.out.println(e.getMessage());
				}
				if (idW >= 0 && idW < super.getWorker().size() && idC >= 0 && idC < super.getBuilding().size()) {
					Worker worker = super.getWorker().get(idW);
					IBuilding building = super.getBuilding().get(idC);
					if (building.getWorkers().size() > 0 && round == this.tour) {
						this.work(worker, building);
						this.removeActions(building.getWorkers().size());
					} else  {
						this.work(worker, building);
						round = this.tour;
						this.removeActions(1);
					}
				}
			}  else if (action == 4) {
				int idB = 0;
				System.out.println("Enter the amount of actions to buy");
				try {
					idB = scan.nextInt();
				} catch(Exception e){
					System.out.println(e.getMessage());
				}
				if (idB > 0) {
					this.buyAction(idB);
				}
			} else if (action == 5) {
				int idS = 0;
				System.out.println("Enter the amount of actions to sell");
				try {
					idS = scan.nextInt();
				} catch(Exception e){
					System.out.println(e.getMessage());
				}
				if (idS > 0 && idS <= 3) {
					this.sellAction(idS);
				}
			} else if (action == 6) {
				String ret = null;
				System.out.println("If you want enable the music type start if you want to disable type stop");
				try {
					ret = scan.next();
				} catch(Exception e){
					System.out.println(e.getMessage());
				}
				try{
					if (ret.equals("start") || ret.equals("stop")) {
						Clip clip = AudioSystem.getClip();
						if (ret.equals("stop")) {
							clip.close();
						} else {
							clip.open(AudioSystem.getAudioInputStream(new File("data/music/music.wav")));
							clip.loop(Clip.LOOP_CONTINUOUSLY);
						}
					}
				} catch(LineUnavailableException e){
					e.printStackTrace();
				} catch(UnsupportedAudioFileException e){
					e.printStackTrace();
				} catch(IOException e){
					e.printStackTrace();
				}
			} else if (action == 7) {
				String rep = "no";
				System.out.println("Do you want to save the game (type yes)");
				try {
					rep = scan.next();
				} catch(Exception e){
					System.out.println(e.getMessage());
				}
				if (rep.equalsIgnoreCase("yes")) {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu_MM_dd-HH_mm_ss");
					LocalDateTime now = LocalDateTime.now();
					Backup.save(dtf.format(now).toString(), super.getGame());
					System.exit(0);
				} else {
					System.exit(0);
				}
			}
		}
		this.tour++;
	}

	/**
     * Give a String of the HumanPlayer object
     * @return a representation of the current player
     */
	public String toString() {
		System.out.println("---------- Your card ----------");
		String ret = this.getName() + "\t écus : "+ this.getCoin() + "\t victory point : " + this.getPoint() + "\t actions : " + this.getNbActions();
		for (int i = 0; i < this.getWorker().size(); i++) {
			ret = ret + "\n" + this.getWorker().get(i);
		}
		for (int i = 0; i < this.getBuilding().size(); i++) {
			ret = ret + "\n" + this.getBuilding().get(i);
		}
		return ret;
	}
}