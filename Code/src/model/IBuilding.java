package model;
import java.util.ArrayList;

public interface IBuilding {
	public void addWorker(Worker worker);
	public void removeAllWorkers();
	public ArrayList<Worker> getWorkers();
	public boolean endConstruct();
	public int getPoint();
	public int getCost();
	public String getName();
	public int getId();
}