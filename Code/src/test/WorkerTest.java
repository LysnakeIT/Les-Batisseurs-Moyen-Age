package test;
import org.junit.*;
import static org.junit.Assert.*;
import model.*;
public class WorkerTest {

    private Worker workerCard;

    @Before
    public void tearUp(){
        this.workerCard = new Worker(1, "Apprenti", 2, 1, 0, 1, 0);
    }

    @After()
    public void tearDown(){
        this.workerCard = null;
    }

    @Test()
    public void testGetId(){
        assertEquals(this.workerCard.getId(), 1);
    }

    @Test()
    public void testGetName(){
        assertEquals(this.workerCard.getName(), "Apprenti");
    }

    @Test()
    public void testGetCost(){
        assertEquals(this.workerCard.getCost(), 2);
    }

    @Test()
    public void testGetStone(){
        assertEquals(this.workerCard.getStone(), 1);
    }

    @Test()
    public void testGetWood(){
        assertEquals(this.workerCard.getWood(), 0);
    }

    @Test()
    public void testGetKnowledge(){
        assertEquals(this.workerCard.getKnowledge(), 1);
    }

    @Test()
    public void testGetTiles(){
        assertEquals(this.workerCard.getTiles(), 0);
    }
}