package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
/**
 * This class allows you to create a new Player object
 * @author L.DAMIEN
 */
public abstract class Player implements Serializable {

	private int coin;
	private int point;
	private int nbActions;
	private String name;
	private Board board;
	private ArrayList<Worker> worker;
	private ArrayList<IBuilding> building;
	private ArrayList<IBuilding> finishedBuilding;
	private Game game;

	/**
	 * Constructor of Player object
	 * @param name the name of the player
	 * @param board the board of the game
	 * @param coin the coin of the player
	 * @param nbActions the amount of actions of the player
	 * @param point the amount of victory point of player
	 * @param game the current game
	 */
	public Player(String name, Board board, int coin, int nbActions, int point, Game game) {
		if (name == null) {
			throw new IllegalArgumentException("Player(): the name's player cis null");
		} else {
			this.name = name;
		}
		if (board == null) {
			throw new IllegalArgumentException("Player(): the board cannot be null");
		} else {
			this.board = board;
		}
		if (coin < 0) {
			throw new IllegalArgumentException("Player(): the coins is negative");
		} else {
			this.coin = coin;
		}
		if (nbActions < 0) {
			throw new IllegalArgumentException("Player(): the number of action is negative");
		} else {
			this.nbActions = nbActions;
		}
		if (point < 0) {
			throw new IllegalArgumentException("Player(): the number of victory point is negative");
		} else {
			this.point = point;
		}
		this.worker = new ArrayList<Worker>();
		this.building = new ArrayList<IBuilding>();
		this.finishedBuilding = new ArrayList<IBuilding>();
		this.game = game;
		Random random = new Random();
		int id = random.nextInt(5);
		this.worker.add((Worker)this.getBoard().getWorkerCards().get(id));
		this.getBoard().getWorkerCards().remove(id);
	}

	/**
	 * Method that represents the actions of the players
	 */
	public abstract void play();

	/**
     * Exchange action against Ecu
	 * @param action the number of action to sell
     */
    public void sellAction(int action) {
		if (action < 0) {
			throw new IllegalArgumentException("sellAction(): the parameter is negative");
		} else {
			if (this.nbActions - action >= 0) {
				if (action == 1) {
					this.coin = this.coin + 1;
				} else if (action == 2) {
					this.coin = this.coin + 3;
				} else if (action == 3) {
					this.coin = this.coin + 6;
				}
				this.removeActions(action);
			}
		}
    }

	/**
     * Exchange ecu against action
	 * @param action the number of action to buy
     */
    public void buyAction(int action) {
		if (action < 0) {
			throw new IllegalArgumentException("buyAction(): the parameter is negative");
		} else {
			if (this.coin >= (action*5) && action != 0) {
				this.addActions(action);
				this.coin = this.coin - (action*5);
			}
		}
    }

	/**
     * Begins construction of a building
	 * @param build the construction to build
     */
    public void startBuilding(IBuilding build) {
		if (build == null) {
			throw new IllegalArgumentException("startBuilding(): the parameter is null");
		} else {
			this.building.add(build);
		}
    }

	/**
     * Recruit a worker
	 * @param worker the hired worker
     */
    public void recruitWorker(Worker worker) {
		if (worker == null) {
			throw new IllegalArgumentException("recruitWorker(): the parameter is null");
		} else {
			if (this.coin - worker.getCost() >= 0) {
				this.worker.add(worker);
				this.coin = this.coin - worker.getCost();
			}
		}
    }

	/**
     * Sends a worker to work on a construction
	 * @param worker the worker send to build
	 * @param build the construction to build
     */
	public void work(Worker worker, IBuilding build) {
		if (worker == null || build == null) {
			throw new IllegalArgumentException("work(): the parameter is null");
		} else {
			if (this.worker.contains(worker) && this.building.contains(build)) {
				build.addWorker(worker);
				this.worker.remove(worker);
				if (build.endConstruct()) {
					for (Worker workers : build.getWorkers()) {
						this.worker.add(workers);
					}
					build.removeAllWorkers();
					this.finishedConstruct(build);
					if (build instanceof Machine) {
						this.machineFinished(build);
					}
				}
			}
		}
    }

	/**
     * When a machine is finished she became a worker
	 * @param build the machine
     */
    public void finishedConstruct(IBuilding build) {
		if (build == null) {
			throw new IllegalArgumentException("finishedConstruct(): the parameter is null");
		} else {
			if (this.building.contains(build)) {
				System.out.println("The constructions of  " + ((Card)(build)).getName() + " is finish");
				this.finishedBuilding.add(build);
				this.building.remove(build);
				this.coin = this.coin + build.getCost();
				this.addPoint(build.getPoint());
			}
		}
    }

	/**
     * When a machine is finished she became a worker
	 * @param build the machine
     */
    public void machineFinished(IBuilding build) {
		if (build == null) {
			throw new IllegalArgumentException("machineFinished(): the parameter is null");
		} else {
			if (this.finishedBuilding.contains(build)) {
				if(build instanceof Machine) {
					this.finishedBuilding.remove(build);
					Worker w = new Worker(((Machine)(build)).getId(), ((Machine)(build)).getName(), 0, ((Machine) (build)).getStoneProd(), ((Machine) (build)).getWoodprod(), ((Machine) (build)).getKnowledgeProd(), ((Machine) (build)).getTilesProd());
					this.worker.add(w);
				}
			}
		}
    }

	/**
     * Get the coin of the Player
     * @return the coin of the Player
     */
	public int getCoin() {
		return this.coin;
	}

	/**
	 * Set the coin of the Player
	 * @param coin the new coin of the player
	 */
	public void setCoin(int coin) {
		this.coin = coin;
	}

	/**
     * Get the name of the Player
     * @return the name of the Player
     */
    public String getName() {
        return this.name;
    }

	/**
     * Get the point of the Player
     * @return the point of the Player
     */
    public int getPoint() {
        return this.point;
    }

	/**
     * Set the point of the Player
     * @param point the new point of the player
     */
    public void setPoint(int point) {
        this.point = point;
    }

	/**
	 * Add the point to the Player
	 * @param point the number of point
	 */
    public void addPoint(int point) {
		if (point < 0 ) {
			throw new IllegalArgumentException("addPoint(): the parameter is negative");
		} else {
			this.point = this.point + point;
		}
    }

	/**
     * Get the number of actions of the Player
     * @return the number of actions of the Player
     */
    public int getNbActions() {
        return this.nbActions;
    }

	/**
     * Get the current board
     * @return the current board
     */
    public Board getBoard() {
        return this.board;
    }

	 /**
	  * Remove actions to the player
	  * @param nbAmount the amount of the actions to remove
	  */
	public void removeActions(int nbAmount) {
		if (nbAmount < 0) {
			throw new IllegalArgumentException("removeActions(): the parameter is negative");
		} else {
			if (nbAmount <= this.nbActions) {
				this.nbActions = this.nbActions - nbAmount;
			}
		}
	}

	 /**
	  * Add actions to the player
	  * @param nbAmount the amount of the actions to add
	  */
	  public void addActions(int nbAmount) {
		if (nbAmount < 0) {
			throw new IllegalArgumentException("addActions(): the parameter is negative");
		} else {
			this.nbActions = this.nbActions + nbAmount;
		}
	}

	/**
     * Get the list of card of the Worker
     * @return the list of card Worker
     */
    public ArrayList<Worker> getWorker() {
        return this.worker;
    }

	/**
     * Get the list of card of the Building
     * @return the list of card Building
     */
    public ArrayList<IBuilding> getBuilding() {
        return this.building;
    }

	/**
     * Get the current game
     * @return the current game 
     */
	public Game getGame() {
		return this.game;
	}

	/**
     * Give a String of the Player object
     * @return a representation of the current player
     */
    public abstract String toString();
}