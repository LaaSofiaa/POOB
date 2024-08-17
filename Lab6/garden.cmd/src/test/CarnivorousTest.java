package test;
import domain.Carnivorous;
import domain.Garden;
import domain.Flower;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CarnivorousTest.
 *
 * @author  Santiago Gualdron, Sofia Gil
 * @version 1.0
 */
public class CarnivorousTest
{
    @Test
    public void shouldMove() {
        Garden garden = new Garden();
        Flower flower = new Flower(garden, 5, 5);
        garden.setThing(5, 5, flower);
        Carnivorous carnivorous = new Carnivorous(garden, 10, 10);
        garden.setThing(10, 10, carnivorous);

        carnivorous.move();

        int newRow = carnivorous.getRow();
        int newColumn = carnivorous.getColumn();

        assertTrue(newRow >= 0 && newRow < garden.getLength());
        assertTrue(newColumn >= 0 && newColumn < garden.getLength());
        assertNotEquals(10, newRow);
        assertNotEquals(10, newColumn);

        assertEquals(carnivorous, garden.getThing(newRow, newColumn));
        assertNull(garden.getThing(10, 10));
    }
    
    @Test
    public void shouldnotMove() {
        Garden garden = new Garden();
        Flower flower = new Flower(garden, 5, 5);
        garden.setThing(5, 5, flower);
        Carnivorous carnivorous = new Carnivorous(garden, 10, 10);
        garden.setThing(10, 10, carnivorous);

        carnivorous.move();

        int oldRow = 10;
        int oldColumn = 10;
        int newRow = carnivorous.getRow();
        int newColumn = carnivorous.getColumn();

        assertTrue(newRow >= 0 && newRow < garden.getLength());
        assertTrue(newColumn >= 0 && newColumn < garden.getLength());
        assertNotEquals(oldRow, newRow); // Verificar que las coordenadas nuevas no sean iguales a las anteriores
        assertNotEquals(oldColumn, newColumn);

        assertEquals(carnivorous, garden.getThing(newRow, newColumn));
        assertNull(garden.getThing(oldRow, oldColumn));
    }
}