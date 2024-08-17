package test;
import domain.Multicolor;
import domain.Garden;
import domain.Tree;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The test class MulticolorTest.
 *
 * @author  Sofia Gil, Santiago Gualdron
 * @version 1.0
 */
public class MulticolorTest
{
    @Test
    public void shouldMove() {
        Garden garden = new Garden();
        Multicolor multi = new Multicolor(garden, 5, 5);
        garden.setThing(5, 5, multi);
        Tree tree = new Tree();
        garden.setThing(10, 10, tree);

        multi.move();

        int newRow = multi.getRow();
        int newColumn = multi.getColumn();

        assertTrue(newRow >= 0 && newRow < garden.getLength());
        assertTrue(newColumn >= 0 && newColumn < garden.getLength());
        assertNotEquals(10, newRow);
        assertNotEquals(10, newColumn);

        assertEquals(multi, garden.getThing(newRow, newColumn));
        assertNull(garden.getThing(5, 5));
        
        for (int i = 0; i < 4; i ++){
            multi.move();
        }
        
        newRow = multi.getRow();
        newColumn = multi.getColumn();
        
        assertEquals(multi, garden.getThing(newRow, newColumn));
        assertNull(garden.getThing(9, 9));
    }
    
    @Test
    public void shouldnotMove() {
        Garden garden = new Garden();
        Multicolor multi = new Multicolor(garden, 5, 5);
        garden.setThing(5, 5, multi);
        Tree tree = new Tree();
        garden.setThing(10, 10, tree);

        multi.move();

        int oldRow = 5;
        int oldColumn = 5;
        int newRow = multi.getRow();
        int newColumn = multi.getColumn();

        assertTrue(newRow >= 0 && newRow < garden.getLength());
        assertTrue(newColumn >= 0 && newColumn < garden.getLength());
        assertNotEquals(oldRow, newRow); // Verificar que las coordenadas nuevas no sean iguales a las anteriores
        assertNotEquals(oldColumn, newColumn);

        assertEquals(multi, garden.getThing(newRow, newColumn));
        assertNull(garden.getThing(oldRow, oldColumn));
    }
}
