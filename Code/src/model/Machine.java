package model;
import java.util.ArrayList;

/**
 * This class allows you to create a new Machine object
 * @author L.DAMIEN
 */
public class Machine extends Card implements IBuilding {

    private int point;
    private int stoneProd;
    private int woodProd;
    private int knowledgeProd;
    private int tilesProd;
    private ArrayList<Worker> workers;

    /**
     * Constructor of Worker object
     * @param id the id of the card
     * @param name the name of the card
     * @param point the number of points won at the end of the constrcution of the machine
     * @param stone the number of stone for the construction of the machine
     * @param wood the number of wood for the construction of the machine
     * @param knowledge the number of knowledge for the construction of the machine
     * @param tiles the number of tiles for the construction of the machine
     * @param stoneProd the number of stone produced by the machine
     * @param woodProd the number of wood produced by the worker
     * @param knowledgeProd the number of knowledge produced by the worker
     * @param tilesProd the number of tiles produced by the worker
     */
    public Machine(int id, String name, int point, int stone, int wood, int knowledge, int tiles, int stoneProd, int woodProd, int knowledgeProd, int tilesProd) {
        super(id, name, stone, wood, knowledge, tiles);
        if (point < 0 || stoneProd < 0 || woodProd < 0 || knowledgeProd < 0 || tilesProd < 0) {
			throw new IllegalArgumentException("Machine(): a parameter is negative");
		} else {
			this.point = point;
            this.stoneProd = stoneProd;
            this.woodProd = woodProd; 
            this.knowledgeProd = knowledgeProd;
            this.tilesProd = tilesProd;
            this.workers = new ArrayList<Worker>();
		}
    }
    
    /**
     * Get the point of the Machine card
     * @return the cost of the Machine Card
     */
    public int getPoint() {
        return this.point;
    }

    /**
     * Get the stone produced of the card
     * @return the stone of the card
     */
    public int getStoneProd() {
        return this.stoneProd;
    }

    /**
     * Get the wood produced of the card
     * @return the wood of the card
     */
    public int getWoodprod() {
        return this.woodProd;
    }

    /**
     * Get the knowledge produced of the card
     * @return the knowledge of the card
     */
    public int getKnowledgeProd() {
        return this.knowledgeProd;
    }

    /**
     * Get the tiles produced of the card
     * @return the tiles of the card
     */
    public int getTilesProd() {
        return this.tilesProd;
    }

    /**
	 * Method that add a worker on this machine building
	 * @param worker the worker you want to add on this machine building
	 **/
	public void addWorker(Worker worker) {
		this.workers.add(worker);
	}

    /**
     * Method that use when the building is finished and so removed all workers who work on his
     */
    public void removeAllWorkers() {
        for (int i = 0; i < this.workers.size(); i++) {
            System.out.println(this.workers.get(i));
            this.workers.remove(i);
        }
    }

    /**
     * Get the list of workers who work on this building
     * @return the list of workers 
     */
    public ArrayList<Worker> getWorkers() {
        return this.workers;
    }

    /**
     * Get the cost of a machine when it becomes a worker
     * @return 0
     */
	public int getCost() {
        return 0;
    }

    /**
	 * Method that checks that the resources necessary for the construction are sufficient and if so then the machine buidling is finished
	 * @return true if the machine is finished, else false
	 **/
	public boolean endConstruct() {
		boolean ret = false;
        int stone = 0;
        int wood = 0;
        int knowledge = 0;
        int tiles = 0;
        for (Worker work : this.workers) {
            stone = stone + work.getStone();
            wood = wood + work.getWood();
            knowledge = knowledge + work.getKnowledge();
            tiles = tiles + work.getTiles();
        }
        if (this.getStone() - stone <= 0 && this.getWood() - wood <= 0 && this.getKnowledge() - knowledge <= 0 && this.tiles - tiles <= 0) {
            ret = true;
        }
		return ret;
	}

    /**
     * Give a String of the Worker object
     * @return a representation of the current worker
     */
    public String toString() {
        String ret ="\n _________________________ ";
        if (this.getName().equals("Une scie circulaire")) {
            ret = ret + "\n| " + this.getName() + "     |";
        } else if (this.getName().equals("Un four à tuiles")) {
            ret = ret + "\n| " + this.getName() + "        |";
        } else if (this.getName().equals("Un instrument de mesure")) {
            ret = ret + "\n| " + this.getName() + " |";
        } else {
            ret = ret + "\n| " + this.getName() + "                |";
        }
        ret = ret + "\n" + "| point: " + this.getPoint() + "                |";
        ret = ret + "\n" + "| stone : " + this.getStone() + "               |";
        ret = ret + "\n" + "| wood : " + this.getWood() + "                |";
        ret = ret + "\n" + "| knowledge : " + this.getKnowledge() + "           |";
        ret = ret + "\n" + "| tiles : " + this.getTiles() + "               |";
        ret = ret + "\n" + "| stone product : " + this.getStoneProd() + "       |";
        ret = ret + "\n" + "| wood product : " + this.getWoodprod() + "        |";
        ret = ret + "\n" + "| knowledge product : " + this.getKnowledgeProd() + "   |";
        ret = ret + "\n" + "| tiles product : " + this.getTilesProd() + "       |";
        ret = ret + "\n _________________________ ";
        return ret;
    }
}
