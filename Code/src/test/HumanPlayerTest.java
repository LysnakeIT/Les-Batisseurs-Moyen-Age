package test;
import org.junit.*;
import static org.junit.Assert.*;
import model.*;

public class HumanPlayerTest {

    private HumanPlayer p;
    private Game game;
    private Board board;

    @Before
    public void tearUp(){
        this.board = new Board();
        this.game = new Game();
        this.p = new HumanPlayer("P1", board, 10, 3, 0, game);
    }

    @After()
    public void tearDown(){
        this.p = null;
    }

    @Test()
    public void testGetAction(){
        assertEquals(this.p.getNbActions(), 3);
    }

    @Test()
    public void testGetName(){
        assertEquals(this.p.getName(), "P1");
    }

    @Test()
    public void testGetBoard(){
        assertEquals(this.p.getBoard(), this.board);
    }

    @Test()
    public void testGetGame(){
        assertEquals(this.p.getGame(), this.game);
    }

    @Test()
    public void testGetCoin(){
        assertEquals(this.p.getCoin(), 10);
    }

    @Test()
    public void testGetPoint(){
        assertEquals(this.p.getPoint(), 0);
    }

    @Test()
    public void testSellAction(){
        this.p.sellAction(1);
        assertEquals(this.p.getNbActions(), 2);
        assertEquals(this.p.getCoin(), 11);
    }

    @Test()
    public void testBuyAction(){
        this.p.buyAction(1);
        assertEquals(this.p.getNbActions(), 4);
        assertEquals(this.p.getCoin(), 5);
    }

    @Test()
    public void testStartBuilding(){
        Building buildingCard = new Building(50, "La maison urbaine", 10, 2, 2, 0, 2, 1);
        this.p.startBuilding(buildingCard);
        assertEquals(this.p.getBuilding().get(0), buildingCard);
        assertEquals(this.p.getBuilding().size(), 1);
    }

    @Test()
    public void testRecruitWorker(){
        Worker workerCard = new Worker(1, "Apprenti", 2, 1, 0, 1, 0);
        this.p.recruitWorker(workerCard);
        assertEquals(this.p.getWorker().get(0), workerCard);
        assertEquals(this.p.getWorker().size(), 1);
    }

    @Test()
    public void testWork(){
        Worker workerCard = new Worker(1, "Apprenti", 2, 1, 0, 1, 0);
        Building buildingCard = new Building(50, "La maison urbaine", 10, 2, 2, 0, 2, 1);
        this.p.work(workerCard, buildingCard);
        assertEquals(this.p.getBuilding().size(), 0);
        assertEquals(this.p.getWorker().size(), 0);
    }
}