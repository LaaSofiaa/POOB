
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class BoatTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BoatTest{
    @Test
    public void testConstructor() {
        Boat boat = new Boat();
        assertNotNull(boat.boat);
        assertNotNull(boat.flag);
        
    }
    
    @Test
    public void testMakeVisibleAndInvisible() {
        Boat boat = new Boat();
        boat.makeVisible();
        boat.makeInvisible();
    }
    
     @Test
    public void testMoveTo() {
        Boat boat = new Boat();
        boat.getBoat();
        boat.moveTo(50, 30);
        boat.moveTo(-20,-13);
    }
    
    @Test
    public void testRotate() {
        Boat boat = new Boat();
        boat.getBoat();
        boat.rotate();
        boat.side = "right";
        boat.rotate();
    }
    
    @Test
    public void testChangeColor() {
        Boat boat = new Boat();
        boat.getBoat();
        boat.changeColor("blue");
        
    }
    
    @Test
    public void testChangeSize() {
        Boat boat = new Boat();
        boat.getBoat();
        boat.changeSize(50);
    
    }
}