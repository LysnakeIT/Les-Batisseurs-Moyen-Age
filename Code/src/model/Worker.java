package model;

/**
 * This class allows you to create a new Worker object
 * @author L.DAMIEN
 */
public class Worker extends Card{

    private int cost;

    /**
     * Constructor of Worker object
     * @param id the id of the card
     * @param name the name of the card
     * @param cost the amount of player must pay for the worker
     * @param stone the number of stone produced by the worker
     * @param wood the number of wood produced by the worker
     * @param knowledge the number of knowledge produced by the worker
     * @param tiles the number of tiles produced by the worker
     */
    public Worker(int id, String name, int cost, int stone, int wood, int knowledge, int tiles) {
        super(id, name, stone, wood, knowledge, tiles);
        if (cost < 0) {
			throw new IllegalArgumentException("Worker(): the cost of the card is negative");
		} else {
			this.cost = cost;
		}
    }
    
    /**
     * Get the cost of the Worker card
     * @return the cost of the Worker Card
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Give a String of the Worker object
     * @return a representation of the current worker
     */
    public String toString() {
        String ret ="\n _________________ ";
        if (this.getName().length() == 9) {
            ret = ret + "\n| " + this.getName() + "       |";
        } else if (this.getName().length() == 6) {
            ret = ret + "\n| " + this.getName() + "          |";
        } else {
            ret = ret + "\n| " + this.getName() + "        |";
        }
        ret = ret + "\n" + "| cost : " + this.getCost() + "        |";
        ret = ret + "\n" + "| stone : " + this.getStone() + "       |";
        ret = ret + "\n" + "| wood : " + this.getWood() + "        |";
        ret = ret + "\n" + "| knowledge : " + this.getKnowledge() + "   |";
        ret = ret + "\n" + "| tiles : " + this.getTiles() + "       |";
        ret = ret + "\n _________________ ";
        return ret;
    }
}
