package test;

import domain.*;
import presentation.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;


/**
 * The test class OpenSaveTest.
 *
 * @author  Santiago Gualdron-Sofia Gil
 * @version 1.0
 */
public class OpenSaveTest
{
     @Test
    public void shouldOpen00Archivo() throws GardenException {
        Garden garden = new Garden();
        garden.setThing(0, 0, new Water());
        garden.setThing(1, 1, new Flower(garden, 1, 1));

        File file = new File("garden.dat");
        garden.save00Archivo(file);

        Garden abrir = Garden.open00Archivo(file);
        //Abre el archivo y verificamos lo que guardamos
        assertNotNull(abrir);
        assertEquals(garden.getLength(), abrir.getLength());
        assertEquals(garden.getThing(0, 0).getClass(), abrir.getThing(0, 0).getClass());
        assertEquals(garden.getThing(1, 1).getClass(), abrir.getThing(1, 1).getClass());
    }
    
     @Test
    public void should1Open00Archivo() throws GardenException {
        Garden garden = new Garden();
        garden.setThing(1, 0, new Water());
        garden.setThing(1, 3, new Flower(garden, 2, 1));

        File file = new File("garden.dat");
        garden.save00Archivo(file);

        Garden abrir = Garden.open00Archivo(file);
        //Abre el archivo y verificamos lo que guardamos
        assertNotNull(abrir);
        assertEquals(garden.getLength(), abrir.getLength());
        assertEquals(garden.getThing(1, 0).getClass(), abrir.getThing(1, 0).getClass());
        assertEquals(garden.getThing(1, 3).getClass(), abrir.getThing(1, 3).getClass());
    }
    
    @Test
    public void shouldSave00Archivo() throws GardenException {
        Garden garden = new Garden();
        garden.setThing(0, 0, new Water());
        garden.setThing(1, 1, new Flower(garden, 1, 1));

        File file = new File("garden.dat");
        garden.save00Archivo(file);
        // Verifica que exista y no este vacio el archivo
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
    
    @Test
    public void should1Save00Archivo() throws GardenException {
        Garden garden = new Garden();
        garden.setThing(1, 0, new Water());
        garden.setThing(1, 3, new Flower(garden, 2, 1));

        File file = new File("garden.dat");
        garden.save00Archivo(file);
        // Verifica que exista y no este vacio el archivo
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
    
    @Test
    public void shouldSave01Archivo() throws GardenException {
        Garden garden = new Garden();
        File archivo = new File("garden.dat");
        garden.save01Archivo(archivo);
        assertTrue(archivo.exists());
    }
    
    @Test
    public void shoul2Save01Archivo() {
        try {
            File archivo = new File("garden.dat");
            Garden garden = new Garden();
            garden.save01Archivo(archivo);
            assertTrue(archivo.exists());
            assertTrue(archivo.length() > 0);
        } catch (Exception e) {
            fail("Excepcion inesperada: " + e.getMessage());
        }
    }
    
    @Test
    public void should1Save01Archivo() throws Exception {
        Garden garden = new Garden();
        File archivo = new File("errorgarden.txt");
        garden.save01Archivo(archivo);
    }
        
    @Test
    public void shouldOpen01Archivo() throws Exception {
        File archivo = new File("errorgarden.txt");
        Garden.open01Archivo(archivo);
    }
    
    @Test
    public void should1Open01Archivo() {
        try {
            File archivo = new File("garden.dat");
            Garden garden = new Garden();
            garden.save01Archivo(archivo);
            Garden archi = Garden.open01Archivo(archivo);
            assertNotNull(archi);
        } catch (Exception e) {
            fail("Excepcion inesperada: " + e.getMessage());
        }
    }
    
    @Test
    public void should2Open01Archivo() {
        try {
            //no existe le archivo
            File archivo = new File("garden01.dat");
            Garden gardenNoExistente = Garden.open01Archivo(archivo);
        } catch (GardenException e) {
            assertEquals("Archivo no encontrado: garden01.dat", e.getMessage());
        } catch (Exception e) {
            fail("Se esperaba una GardenException, se lanzo: " + e.getClass().getSimpleName());
        }
    }
    
    
    //Debrrian arrojar error ya que prueban la funcionalidad de los archivos saquen las excepciones
    /**@Test
    public void shouldnotSave01Archivo() throws GardenException {
        Garden garden = new Garden();
        garden.save01Archivo(null);
    }

    @Test
    public void shouldnot1Save01Archivo() throws GardenException {
        Garden garden = new Garden();
        File archivo = new File("garden/file.txt");
        garden.save01Archivo(archivo);
    }
    */
}
