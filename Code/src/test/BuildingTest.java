package test;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import model.*;

public class BuildingTest{

    private Building buildingCard;

    @Before
    public void tearUp(){
        this.buildingCard = new Building(50, "La maison urbaine", 10, 2, 2, 0, 2, 1);
    }

    @After()
    public void tearDown(){
        this.buildingCard = null;
    }

    @Test()
    public void testGetPoint(){
        assertEquals(this.buildingCard.getPoint(), 2);
    }

    @Test()
    public void testGetCost(){
        assertEquals(this.buildingCard.getCost(), 10);
    }

    @Test()
    public void testAddWorker(){
        Worker workerCard = new Worker(1, "Apprenti", 2, 1, 0, 1, 0);
        this.buildingCard.addWorker(workerCard);
        ArrayList<Worker> workersList = this.buildingCard.getWorkers();
        assertEquals(workersList.get(0), workerCard);
        assertEquals(workersList.size(), 1);
    }
    
    @Test()
    public void testRemoveAllBuild(){
        this.buildingCard.removeAllWorkers();
        ArrayList<Worker> workersList = this.buildingCard.getWorkers();
        assertEquals(workersList.size(), 0);
    }

    @Test()
    public void testEndConstruct(){
        this.buildingCard = new Building(52, "La maison rurale", 10, 2, 1, 2, 1, 1);
        Worker workerCard = new Worker(1, "Apprenti", 2, 1, 0, 1, 0);
        this.buildingCard.addWorker(workerCard);
        assertEquals(this.buildingCard.endConstruct(), false);
        Worker workerBis = new Worker(25, "Compagnon", 4, 1, 2, 0, 1);
        this.buildingCard.addWorker(workerBis);
        assertEquals(this.buildingCard.endConstruct(), true);
    }
}