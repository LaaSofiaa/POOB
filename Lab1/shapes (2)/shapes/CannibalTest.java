

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CannibalTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CannibalTest{
    private Cannibal cannibal;
    @Test
    public void testConstructor() {
        Cannibal cannibal = new Cannibal();
        assertEquals("left", cannibal.side);
        assertNotNull(cannibal.body);
        assertNotNull(cannibal.eye1);
        assertFalse(cannibal.body.isVisible);
        assertFalse(cannibal.eye1.isVisible);
    }

    @Test
    public void testMakeVisibleAndInvisible() {
        Cannibal cannibal = new Cannibal();

        assertFalse(cannibal.body.isVisible);
        assertFalse(cannibal.eye1.isVisible);
        
        cannibal.makeVisible();
        
        assertTrue(cannibal.body.isVisible);
        assertTrue(cannibal.eye1.isVisible);

        cannibal.makeInvisible();

        assertFalse(cannibal.body.isVisible);
        assertFalse(cannibal.eye1.isVisible);
    }
    
    @Test
    public void testChangeSize() {
        Cannibal cannibal = new Cannibal();
        cannibal.changeSize(65);
    }
    
    @Test
    public void testChangeColor() {
        Cannibal cannibal = new Cannibal();
        cannibal.changeColorEye("orange");
        cannibal.changeColorBody("blue");
    }
    
    @Test
    public void testMoveTo() {
        cannibal = new Cannibal();
        cannibal.getCannibal();
        cannibal.moveTo(50, -30);
        cannibal.moveTo(-55, 10);    
    }
    
    @Test
    public void testRotate() {
        cannibal = new Cannibal();
        cannibal.getCannibal();
        cannibal.rotate();
    
        cannibal.side = "right";
        cannibal.rotate();
        
        cannibal.side = "left";
        cannibal.rotate();
    }
}
