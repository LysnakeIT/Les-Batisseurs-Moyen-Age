package model;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Random;

/**
 * This class allows you to create a new Board object
 * @author L.DAMIEN
 */
public class Board implements Serializable {

	private ArrayList<Card> worker;
	private ArrayList<Card> building;
	private ArrayList<Card> machine;
	private Random random;
	private ArrayList<Card> deckWorker;
	private ArrayList<IBuilding> deckBuilding;

	public Board() {
		this.random = new Random();
		this.worker = new ArrayList<Card>();
		this.building = new ArrayList<Card>();
		this.machine = new ArrayList<Card>();
		this.deckWorker = new ArrayList<Card>();
		this.deckBuilding = new ArrayList<IBuilding>();
		this.createCards("../data/ouvriers.txt", "../data/batiments.txt", "../data/machines.txt");
		this.createDeckWorker();
		this.createDeckBuilding();
	}

	/**
	 * Create the game cards
	 * @param fileWorker the source file contains the worker
	 * @param fileBuilder the source file contains the building
	 * @param fileMachine the source file contains the machine
	 */
	public void createCards(String fileWorker, String fileBuilder, String fileMachine) {
		int id = 0;
		try{
			FileReader file = new FileReader (fileWorker);
			Scanner in = new Scanner(file);
			while(in.hasNext()) {
				id++;
				String line = in.nextLine();
				line.trim();
				Scanner scan = new Scanner(line).useDelimiter(";");
				String name = scan.next();
				int cost = scan.nextInt();
				int stone = scan.nextInt();
				int wood = scan.nextInt();
				int knowledge = scan.nextInt();
				int tiles = scan.nextInt();
				this.worker.add(new Worker(id, name, cost, stone, wood, knowledge, tiles));
			}
			in.close();
		}
		catch(FileNotFoundException e){
			System.out.println(fileWorker + " not found");
		}

		try{
			FileReader file = new FileReader (fileBuilder);
			Scanner in = new Scanner(file);
			while (in.hasNext()) {
				id++;
				String line = in.nextLine();
				line.trim();
				Scanner scan = new Scanner(line).useDelimiter(";");
				String name = scan.next();
				int cost = scan.nextInt();
				int point = scan.nextInt();
				int stone = scan.nextInt();
				int wood = scan.nextInt();
				int knowledge = scan.nextInt();
				int tiles = scan.nextInt();
				this.building.add(new Building(id, name, cost, point, stone, wood, knowledge, tiles));
			}
			in.close();
		}
		catch(FileNotFoundException e){
			System.out.println(fileBuilder + " not found");
		}

		try{
			FileReader file = new FileReader (fileMachine);
			Scanner in = new Scanner(file);
			while (in.hasNextLine()) {
				id++;
				String line = in.nextLine();
				line.trim();
				Scanner scan = new Scanner(line).useDelimiter(";");
				String name = scan.next().trim();
				int stoneProd = scan.nextInt();
				int woodProd = scan.nextInt();
				int knowledgeProd = scan.nextInt();
				int tileProd = scan.nextInt();
				int point = scan.nextInt();
				int stone = scan.nextInt();
				int wood = scan.nextInt();
				int knowledge = scan.nextInt();
				int tiles = scan.nextInt();
				this.machine.add(new Machine(id, name, point, stone, wood, knowledge, tiles, stoneProd, woodProd, knowledgeProd, tileProd));
			}
			in.close();
		}
		catch(FileNotFoundException e){
			System.out.println(fileMachine + " not found");
		}
	}

	/**
	 * Pick a card on the pioche
	 * @param pioche the list of Card
	 * @return the arraylist containing the deck cards
	 */
	public Card pickOnThePioche(ArrayList<Card> pioche) {
		Card card = null;
		if (pioche == null) {
			throw new IllegalArgumentException("pickOnThePioche(): the parameter is null");
		} else {
			if (pioche.size() > 0) {
				int i = random.nextInt(pioche.size());
				card = pioche.get(i);
			} else {
				if (pioche.equals(this.worker)) {
					card = new Worker(150, "Stop", 0, 0, 0, 0, 0);
				} else if (pioche.equals(this.building)) {
					card = new Building(150, "Stop", 0, 0, 0, 0, 0, 0);
				} else if (pioche.equals(this.machine)) {
					card = new Machine(150, "Stop", 0, 0, 0, 0, 0, 0, 0, 0, 0);
				}
			}
		}
		return card;
	}

	/**
	 * Create the deck of worker (5 cards)
	 */
	public void createDeckWorker() {
		boolean stop = false;
		while (this.deckWorker.size() < 5 && stop == false) {
			Card cardWorker = pickOnThePioche(this.worker);
			if (cardWorker.getName().equals("Stop")) {
				this.deckWorker.add(cardWorker);
				stop = true;
			} else {
				this.deckWorker.add(cardWorker);
				this.worker.remove(cardWorker);
			}
		}
	}

	/**
	 * Create the deck of Building (5 cards)
	 */
	public void createDeckBuilding() {
		boolean stop = false;
		while (this.deckBuilding.size() < 5 && stop == false) {
			Card cardBuilding;
			int number = random.nextInt(this.building.size() + this.machine.size());
			if (number < this.building.size()) {
				cardBuilding = pickOnThePioche(this.building);
				if (cardBuilding.getName().equals("Stop")) {
					this.deckBuilding.add((IBuilding) cardBuilding);
					stop = true;
				} else {
					this.building.remove(cardBuilding);
					this.deckBuilding.add((IBuilding) cardBuilding);
				}
			} else {
				cardBuilding = pickOnThePioche(this.machine);
				if (cardBuilding.getName().equals("Stop")) {
					this.deckBuilding.add((IBuilding) cardBuilding);
					stop = true;
				} else {
					this.building.remove(cardBuilding);
					this.deckBuilding.add((IBuilding) cardBuilding);
				}
			}
		}
	}

	/**
     * Get the list of card of the Worker
     * @return the list of card Worker
     */
	public ArrayList<Card> getWorkerCards() {
		return this.worker;
	}

	/**
     * Get the list of card of the Building
     * @return the list of card Building
     */
	public ArrayList<Card> getBuildingCards() {
		return this.building;
	}

	/**
     * Get the list of card of the Machine
     * @return the list of card Machine
     */
	public ArrayList<Card> getMachineCards() {
		return this.machine;
	}

	/**
     * Get the Deck of Worker
     * @return the DeckWorker
     */
	public ArrayList<Card> getDeckWorker() {
		return this.deckWorker;
    }

	/**
     * Get the Deck of Building
     * @return the DeckBuilding
     */
	public ArrayList<IBuilding> getDeckBuilding() {
		return this.deckBuilding;
    }

	/**
     * Display the 2 decks of cards
     */
	public void displayCards() {
		System.out.println("\n---------- Deck Worker ----------");
		for(Card worker : this.deckWorker) {
			System.out.print(worker.toString());
		}
		System.out.println("\n\n---------- Deck Building and Machine ----------");
		for(IBuilding building : this.deckBuilding) {
			System.out.print(building.toString());
		}
		System.out.println();
	}
}