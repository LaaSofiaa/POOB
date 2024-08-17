package test;
import domain.Sand;
import domain.Garden;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The test class SandTest.
 *
 * @author  Sofia Gil, Santiago Gualdron
 * @version 1.0
 */
public class SandTest{
    @Test
    public void shouldChangeColor(){
        Sand sand = new Sand();
        assertEquals(Color.DARK_GRAY, sand.getColor());
        for (int i = 0; i < 33; i++){
            sand.act();
        }
        assertEquals(Color.GRAY, sand.getColor());
        for (int i = 0; i < 33; i++){
            sand.act();
        }
        assertEquals(Color.LIGHT_GRAY, sand.getColor());
        for (int i = 0; i < 34; i++){
            sand.act();
        }
        assertEquals(Color.WHITE, sand.getColor());
    }
    
    @Test
    public void souldDissapearGarden(){
        Garden garden = new Garden();
        Sand sand = new Sand();
        garden.setThing(5, 5, sand);
        for (int i = 0; i < 101; i++){
            garden.ticTac();
        }
        assertTrue(sand.isColor());
        assertNull(garden.getThing(5,5));
    }
    
    @Test
    public void souldNotDissapearGarden(){
        Garden garden = new Garden();
        Sand sand = new Sand();
        garden.setThing(5, 5, sand);
        for (int i = 0; i < 33; i++){
            garden.ticTac();
        }
        assertEquals(garden.getThing(5,5), sand);
        assertFalse(sand.isColor());
        for (int i = 0; i < 33; i++){
            garden.ticTac();
        }
        assertEquals(garden.getThing(5,5), sand);
        assertFalse(sand.isColor());
        for (int i = 0; i < 33; i++){
            garden.ticTac();
        }
        assertEquals(garden.getThing(5,5), sand);
        assertFalse(sand.isColor());
    }
}
