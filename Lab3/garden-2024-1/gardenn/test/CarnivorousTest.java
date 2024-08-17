package test;
import domain.Carnivorous;
import domain.Garden;
import domain.Flower;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The test class CarnivorousTest.
 *
 * @author  Sofia Gil, Santiago Gualdron
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
    
    @Test
    public void shouldAct(){
        Garden garden = new Garden();
        Carnivorous carnivorous = new Carnivorous(garden, 10, 10);
        garden.setThing(10, 10, carnivorous);
        for (int i = 0; i < 3; i ++){
            carnivorous.act();
        }
        assertEquals(Color.green, carnivorous.getColor());
        for (int i = 0; i < 2; i ++){
            carnivorous.act();
        }
        assertEquals(Color.blue, carnivorous.getColor());
        assertEquals(0, carnivorous.getTime());
    }
    
    @Test
    public void shouldNotChangeColor(){
        Garden garden = new Garden();
        Carnivorous carnivorous = new Carnivorous(garden, 10, 10);
        garden.setThing(10, 10, carnivorous);
        for (int i = 0; i < 10; i ++){
            carnivorous.act();
        }
        assertEquals(Color.blue, carnivorous.getColor());
    }
}