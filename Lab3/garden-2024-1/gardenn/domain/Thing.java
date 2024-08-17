package domain;
import java.awt.Color;

/**
 * Interfaz Thing que indica el shape, color y estado.
 * 
 * @author Sofia Gil, Santiago Gualdron 
 * @version 1.0
 */
public interface Thing{
    public static final int ROUND = 1;
    public static final int SQUARE = 2;
    public static final int FLOWER = 3;

    /**
     * como actuan los objetos en el garden
     */
    public void act();
    
    /**
     * el tipo de shape que es cada Thing
     * @return entero que indica si es round, square o flower
     */
    default public int shape(){
        return SQUARE;
    }
    
    /**
     * @return el color del Thing
     */
    default public Color getColor(){
        return Color.blue;
    }
    
    /**
     * verificador de que las acciones si se hicieron correctamente
     * @return boolean true
     */
    default public boolean is(){
        return true;
    }
}
