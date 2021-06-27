package test;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import model.*;

public class MachineTest{

    private Machine machineCard;

    @Before
    public void tearUp(){
        this.machineCard = new Machine(78, "Une scie circulaire", 1, 1, 0, 1, 0, 0, 2, 0, 0);
    }

    @After()
    public void tearDown(){
        this.machineCard = null;
    }

    @Test()
    public void testGetPoint(){
        assertEquals(this.machineCard.getPoint(), 1);
    }

    @Test()
    public void testGetStoneProd(){
        assertEquals(this.machineCard.getStoneProd(), 0);
    }

    @Test()
    public void testGetWoodProd(){
        assertEquals(this.machineCard.getWoodprod(), 2);
    }

    @Test()
    public void testGetKnowledgeProd(){
        assertEquals(this.machineCard.getKnowledgeProd(), 0);
    }

    @Test()
    public void testGetTilesProd(){
        assertEquals(this.machineCard.getKnowledgeProd(), 0);
    }

    @Test()
    public void testGetCost(){
        assertEquals(this.machineCard.getCost(), 0);
    }

    @Test()
    public void testAddWorker(){
        Worker workerCard = new Worker(1, "Apprenti", 2, 1, 0, 1, 0);
        this.machineCard.addWorker(workerCard);
        ArrayList<Worker> workersList = this.machineCard.getWorkers();
        assertEquals(workersList.get(0), workerCard);
        assertEquals(workersList.size(), 1);
    }
    
    @Test()
    public void testRemoveAllBuild(){
        this.machineCard.removeAllWorkers();
        ArrayList<Worker> workersList = this.machineCard.getWorkers();
        assertEquals(workersList.size(), 0);
    }

    @Test()
    public void testEndConstruct(){
        this.machineCard = new Machine(79, "Un instrument de mesure", 1, 1, 1, 0, 0, 0, 0, 2, 0);
        Worker workerCard = new Worker(1, "Apprenti", 2, 1, 0, 1, 0);
        this.machineCard.addWorker(workerCard);
        assertEquals(this.machineCard.endConstruct(), false);
        Worker workerBis = new Worker(25, "Compagnon", 4, 1, 2, 0, 1);
        this.machineCard.addWorker(workerBis);
        assertEquals(this.machineCard.endConstruct(), true);
    }
}