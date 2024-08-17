package domain;
import java.io.Serializable;

/**
 * Clase de excepciones 
 * 
 * @author Santiago Gualdron-Sofia Gil 
 * @version 1.0
 */
   public class GardenException extends Exception implements Serializable{
       public static final String ARCHIVE_NOT_NULL = "Archivo no econtrado";
       public static final String ARCHIVE_NOT_CORRECT = "Archivo no valido";
       
        public GardenException(String message) {
        super(message);
    }
}
