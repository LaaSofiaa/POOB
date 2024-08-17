

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; 


/**
 * The test class MissionaryTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MissionaryTest{
    private Missionary missionary;
    @Test
    public void testConstructor() {
        Missionary missionary = new Missionary();
        assertEquals("left", missionary.side);
        assertNotNull(missionary.body);
        assertNotNull(missionary.head);
        assertNotNull(missionary.eye1);
        assertFalse(missionary.body.isVisible);
        assertFalse(missionary.head.isVisible);
        assertFalse(missionary.eye1.isVisible);
    }
    
    @Test
    public void testMakeVisibleAndInvisible() {
        Missionary missionary = new Missionary();

        assertFalse(missionary.body.isVisible);
        assertFalse(missionary.head.isVisible);
        assertFalse(missionary.eye1.isVisible);
        
        missionary.makeVisible();
        
        assertTrue(missionary.body.isVisible);
        assertTrue(missionary.head.isVisible);
        assertTrue(missionary.eye1.isVisible);

        missionary.makeInvisible();

        assertFalse(missionary.body.isVisible);
        assertFalse(missionary.head.isVisible);
        assertFalse(missionary.eye1.isVisible);
    }
    
    @Test
    public void testChangeSize() {
        Missionary missionary = new Missionary();
        missionary.changeSize(40);
    }
    
    @Test
    public void testChangeColor() {
        Missionary missionary = new Missionary();
        missionary.changeColorEye("green");
        missionary.changeColorBody("red");
        missionary.changeColorHead("yellow");
    }
    
    @Test
    public void testMoveTo() {
        missionary = new Missionary();
        missionary.getMissionary();
        missionary.moveTo(30, -20);
        missionary.moveTo(-20, 10);    
    }
    
    @Test
    public void testRotate() {
        missionary = new Missionary();
        missionary.getMissionary();
        missionary.rotate();
    
        missionary.side = "right";
        missionary.rotate();
        
        missionary.side = "left";
        missionary.rotate();
    }
}