package model;

import java.util.ArrayList;

/**
 * This class allows you to create a new Buidling object
 * @author L.DAMIEN
 */
public class Building extends Card implements IBuilding {

    private int cost;
    private int point;
    private ArrayList<Worker> workers;

    /**
     * Constructor of Building object
     * @param id the id of the card
     * @param name the name of the card
     * @param cost the amount won at the end of the construction of the building
     * @param point the number of points won at the end of the constrcution of the building
     * @param stone the number of stone for the construction of the building
     * @param wood the number of wood for the construction of the building
     * @param knowledge the number of knowledge for the construction of the building
     * @param tiles the number of tile for the construction of the building
     */
    public Building(int id, String name, int cost, int point, int stone, int wood, int knowledge, int tiles) {
        super(id, name, stone, wood, knowledge, tiles);
        if (cost < 0 || point < 0) {
			throw new IllegalArgumentException("Building(): a parameter is negative");
		} else {
			this.cost = cost;
            this.point = point;
            this.workers = new ArrayList<Worker>();
		}
    }
    
    /**
     * Get the point of the Building card
     * @return the cost of the Building Card
     */
    public int getPoint() {
        return this.point;
    }

    /**
     * Get the cost of the Building card
     * @return the cost of the Building Card
     */
    public int getCost() {
        return this.cost;
    }

    /**
	 * Method that add a worker on this building
	 * @param worker the worker you want to add on this building
	 **/
	public void addWorker(Worker worker) {
        if (worker == null) {
			throw new IllegalArgumentException("addWorker(): a parameter is null");
		} else {
		    this.workers.add(worker);
        }
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
	 * Method that checks that the resources necessary for the construction are sufficient and if so then the build is finished
	 * @return true if the building is finished, else false
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
     * Give a String of the Building object
     * @return a representation of the current Building
     */
    public String toString() {
        String ret ="\n _________________________ ";
        if (this.getName().equals("La maison bourgeoise")) {
            ret = ret + "\n| " + this.getName() + "    |";
        } else {
            ret = ret + "\n| " + this.getName();
        }
        if (this.cost >= 10) {
            ret = ret + "\n" + "| cost : " + this.getCost() + "               |";
        } else {
            ret = ret + "\n" + "| cost : " + this.getCost() + "                |";
        }
        ret = ret + "\n" + "| point: " + this.getPoint() + "                |";
        ret = ret + "\n" + "| stone : " + this.getStone() + "               |";
        ret = ret + "\n" + "| wood : " + this.getWood() + "                |";
        ret = ret + "\n" + "| knowledge : " + this.getKnowledge() + "           |";
        ret = ret + "\n" + "| tiles : " + this.getTiles() + "               |";
        ret = ret + "\n _________________________ ";
        return ret;
    }
}
