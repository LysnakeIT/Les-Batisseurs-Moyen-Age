package model;
import java.util.Random;

/**
 * This class allows you to create a new Card object
 * @author L.DAMIEN
 */
public class AutoPlayer extends Player {

	private Difficulty difficulty;
	private Random random;
	private int tour;


	/**
	 * Constructor of AutoPlayer object
	 * @param name the name of the player
	 * @param board the board of the game
	 * @param coin the coin of the player
	 * @param nbActions the amount of actions of the player
	 * @param point the amount of victory point of player
	 * @param game the current game
	 */
	public AutoPlayer(String name, Board board, int coin, int nbActions, int point, Game game) {
		super(name, board, coin, nbActions, point, game);
		this.random = new Random();
		this.tour = 0;
	}

	/**
	 * Constructor of AutoPlayer object
	 * @param name the name of the player
	 * @param board the board of the game
	 * @param coin the coin of the player
	 * @param nbActions the amount of actions
	 * @param point the amount of point victory
	 * @param difficulty the diffculty
	 * @param game the current game
	 */
	public AutoPlayer(String name, Board board, int coin, int nbActions, int point, Difficulty difficulty, Game game) {
		super(name, board, coin, nbActions, point, game );
		this.random = new Random();
	}

	/**
	 * Get the difficulty of the AutoPlayer
	 * @return the difficulty of the AUtoPlayer
	 */
	public Difficulty getDifficulty() {
		return this.difficulty;
	}

	/**
	 * Method that plays
	 */
	public void play() {
		boolean ret = false;
		while(this.getNbActions() > 0 && ret == false ) {
			if (super.getBoard().getDeckWorker().size() > 1 && super.getBoard().getDeckWorker().size() > 1 && !super.getBoard().getDeckWorker().get(0).getName().equals("Stop") && !super.getBoard().getDeckBuilding().get(0).getName().equals("Stop")) {
				this.getBoard().displayCards();
				System.out.println(this.toString());
				int choice;
				if (this.getBuilding().size() > 0 && this.getWorker().size() > 0) {
					choice = 2;
				} else {
					choice = (int) (Math.random() *5);
				}
				if (choice == 0) {
					int idDeckBuild = random.nextInt(super.getBoard().getDeckWorker().size());
					this.startBuilding((IBuilding) super.getBoard().getDeckBuilding().get(idDeckBuild));
					super.getBoard().getDeckBuilding().remove(idDeckBuild);
					super.getBoard().createDeckBuilding();
					this.removeActions(1);
				} else if (choice == 1) {
					int idDeckWorker =  random.nextInt(super.getBoard().getDeckBuilding().size());
					this.recruitWorker((Worker) super.getBoard().getDeckWorker().get(idDeckWorker));
					super.getBoard().getDeckWorker().remove(idDeckWorker);
					super.getBoard().createDeckWorker();
					this.removeActions(1);
				} else if (choice == 2) {
					int round = -1;
					if (this.getBuilding().size() > 0 && this.getWorker().size() > 0) {
						int idWorker = random.nextInt(this.getWorker().size());
						if (round == this.tour) {
							this.work(this.getWorker().get(idWorker), this.getBuilding().get(0));
							this.removeActions(this.getBuilding().size());
						} else  {
							this.work(this.getWorker().get(idWorker), this.getBuilding().get(0));
							round = this.tour;
							this.removeActions(1);
						}
					}
				} else if (choice == 3) {
					int value = this.getNbActions() - 0 + 1;
					int amountBuy = (int) (Math.random() *value) + 1;
					if (this.getNbActions() > 0) {
						this.buyAction(amountBuy);
					}
				} else if (choice == 4) {
					int value = this.getNbActions() - 0 + 1;
					int amountSell = (int) (Math.random()*value) + 1;
					if (this.getNbActions() > 0) {
						this.sellAction(amountSell);
					}
				}
			} else {
				ret = true;
				super.getGame().end();
			}
		}
		this.tour++;
	}

	/**
     * Give a String of the AutoPlayer object
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