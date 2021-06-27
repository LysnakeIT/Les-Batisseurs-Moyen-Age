package model;
import java.io.Serializable;

/**
 * This class allows you to create a new Card object
 * @author L.DAMIEN
 */
public abstract class Card implements Serializable {

    protected String name;
    protected int stone;
    protected int wood;
    protected int knowledge;
    protected int tiles;
    protected int id;


    /**
     * Constructor of Card object
     * @param id the id of the card
     * @param name the name of the card
     * @param stone the amount of stone of the card
     * @param wood the amount of wood of the card
     * @param knowledge the amount of knowledge of the card
     * @param tiles the amount of tiles of the card
     */
    public Card(int id ,String name, int stone, int wood, int knowledge, int tiles) {
        if (id < 0) {
            throw new IllegalArgumentException("Card(): the id is negative");
        } else {
            this.id = id;
        }
        if (name == null) {
			throw new IllegalArgumentException("Card(): the name is null");
		} else {
			this.name = name;
		}
        if (stone < 0 || wood < 0 || knowledge < 0 || tiles < 0) {
			throw new IllegalArgumentException("Card(): a parameter is negative");
		} else {
			this.stone = stone;
            this.wood = wood;
            this.knowledge = knowledge;
            this.tiles = tiles;
		}
    }

    /**
     * Get the ID of the  card
     * @return the ID of the  Card
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Get the name of the card
     * @return the name of the card
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the stone of the card
     * @return the stone of the card
     */
    public int getStone() {
        return this.stone;
    }

    /**
     * Get the wood of the card
     * @return the wood of the card
     */
    public int getWood() {
        return this.wood;
    }

    /**
     * Get the knowledge of the card
     * @return the knowledge of the card
     */
    public int getKnowledge() {
        return this.knowledge;
    }

    /**
     * Get the tiles of the card
     * @return the tiles of the card
     */
    public int getTiles() {
        return this.tiles;
    }


    /**
     * Give a String of the Card object
     * @return a representation of the current card
     */
    public abstract String toString();
}
