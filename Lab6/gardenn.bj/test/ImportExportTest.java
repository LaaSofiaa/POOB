package test;
import domain.*;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * The test class ImportExportTest.
 *
 * @author  Santiago Gualdron- Sofia Gil
 * @version 1.0
 */
public class ImportExportTest {

    @Test
    public void ShouldThrowNullImport01ArchiveException(){
        File archivo = null;
        assertThrows(GardenException.class, () -> Garden.import01Archivo(archivo));
    }

    @Test
    public void ShouldThrowNotCorrectImport01ArchiveException(){
        File archivo = new File("test.txt");
        try{
            PrintWriter exp = new PrintWriter(new FileOutputStream(archivo));
            String inputData = "Flower 1 \n";
            exp.write(inputData);
            exp.close();
        } catch(Exception e){}
        assertThrows(GardenException.class, () -> Garden.import01Archivo(archivo));
    }

    @Test
    public void ShouldThrowNotValidImport01ArchiveException() {
        File archivo = new File("");
        assertThrows(GardenException.class, () -> Garden.import01Archivo(archivo));
    }

    @Test
    public void ShouldCorrectImport01Archive() {
        File archivo = new File("test.txt");
        Garden g = new Garden();
        try{
            PrintWriter exp = new PrintWriter(new FileOutputStream(archivo));
            String inputData = "Flower 1 1\nSand 5 5\nCarnivorous 10 10\n";
            exp.write(inputData);
            exp.close();
            g = Garden.import01Archivo(archivo);
        } catch(Exception e){}
        assertNotNull(g);
        assertEquals("Flower", g.getThing(1,1).getClass().getSimpleName());
        assertEquals("Sand", g.getThing(5,5).getClass().getSimpleName());
        assertEquals("Carnivorous", g.getThing(10,10).getClass().getSimpleName());
    }

    @Test
    public void ShouldThrowNullExport01ArchiveException() {
        Garden g = new Garden();
        File archivo = null;
        assertThrows(GardenException.class, () -> g.export01Archivo(archivo));
    }

    @Test
    public void ShouldThrowNotValidExport01ArchiveException() {
        Garden g = new Garden();
        File archivo = new File("");
        assertThrows(GardenException.class, () -> g.export01Archivo(archivo));
    }

    @Test
    void ShouldThrowFirstNumberErrorImport02ArchiveException() {
        File archivo = new File("test.txt");
        try{
            PrintWriter exp = new PrintWriter(new FileOutputStream(archivo));
            String inputData = "Flower 1 sa\n";
            exp.write(inputData);
            exp.close();
        } catch(Exception e){}
        assertThrows(GardenException.class, () -> Garden.import02Archivo(archivo));
    }

    @Test
    void ShouldThrowSecondNumberErrorImport02ArchiveException() {
        File archivo = new File("test.txt");
        try{
            PrintWriter exp = new PrintWriter(new FileOutputStream(archivo));
            String inputData = "Flower sa 1\n";
            exp.write(inputData);
            exp.close();
        } catch(Exception e){}
        assertThrows(GardenException.class, () -> Garden.import02Archivo(archivo));
    }

    @Test
    void ShouldThrowTypeErrorImport02ArchiveException() {
        File archivo = new File("test.txt");
        try{
            PrintWriter exp = new PrintWriter(new FileOutputStream(archivo));
            String inputData = "1 1 1\n";
            exp.write(inputData);
            exp.close();
        } catch(Exception e){}
        assertThrows(GardenException.class, () -> Garden.import02Archivo(archivo));
    }

    @Test
    void ShouldCorrectImport02Archive() {
        File archivo = new File("test.txt");
        Garden g = new Garden();
        try{
            PrintWriter exp = new PrintWriter(new FileOutputStream(archivo));
            String inputData = "Flower 1 1\nSand 5 5\nCarnivorous 10 10\n";
            exp.write(inputData);
            exp.close();
            g = Garden.import02Archivo(archivo);
        } catch(Exception e){}
        assertNotNull(g);
        assertEquals("Flower", g.getThing(1,1).getClass().getSimpleName());
        assertEquals("Sand", g.getThing(5,5).getClass().getSimpleName());
        assertEquals("Carnivorous", g.getThing(10,10).getClass().getSimpleName());
    }
    
    @Test
    public void shouldImport00() throws GardenException{
        try {
            File archivo = new File("C:/Users/laura/Downloads/Gil-Gualdronlab06/Gil-Gualdronlab06/garden-2024-1/archivos/onegarden.txt");
            Garden garden = Garden.import00Archivo(archivo);
            assertNotNull(garden);
            assertEquals(30, garden.getLength());
        } catch (GardenException e) {
            System.err.println("Error al importar el archivo: " + e.getMessage());
        }
    }
    
    @Test
    public void should1Import00() throws GardenException{
        try {
            File archivo = new File("C:/Users/laura/Downloads/Gil-Gualdronlab06/Gil-Gualdronlab06/garden-2024-1/archivos/othergarden.txt");
            Garden garden = Garden.import00Archivo(archivo);
            assertNotNull(garden);
            assertEquals(30, garden.getLength());
        } catch (GardenException e) {
            System.err.println("Error al importar el archivo: " + e.getMessage());
        }
    }
    
    @Test
    public void shouldExport00() throws GardenException{
        Garden garden = new Garden();
        File archivo = new File("garden.txt");
        try {
            garden.export00Archivo(archivo);
            assertNotNull(garden);
            assertTrue(archivo.exists());
        } catch (GardenException e) {
            fail("Se produjo una excepcion: " + e.getMessage());
        }
    }
    
    @Test
    public void should1Export00() throws Exception, GardenException{
        Garden garden = new Garden();
        File archivo = new File("garden.txt");
        try {
            garden.export00Archivo(archivo);
            assertNotNull(garden);
            assertTrue(archivo.exists());
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String line;
            int entitiesCount = 0;
            while ((line = reader.readLine()) != null) {
                String[] entityInfo = line.split(" ");
                assertEquals(3, entityInfo.length);
                entitiesCount++;
            }
            reader.close();
            assertEquals(27, entitiesCount);
        } catch (GardenException e) {
            fail("Se produjo una excepcion: " + e.getMessage());
        }
    }
}


