package domain;
import java.awt.Color;

/**
 * Clase Sand para dibujar cuadrados grises que se van aclarando hasta desaparecer
 * 
 * @author Sofia Gil, Santiago Gualdron 
 * @version 1.0
 */
public final class Sand implements Thing{
    private Color color;
    private int time;
    
    /**
     * Constructor for objects of class Sand
     */
    public Sand(){
        this.color = Color.DARK_GRAY;
        time = 0;
    }
    
    /**
     * @return Color el color que tiene actualmente la Sand
     */
    public Color getColor(){
        return color;
    }
    
    /**
     * indica como actua la arena cada vez que hay un Tic-tac
     */
    public void act(){
        time += 1;
        if (time == 33){
            color = Color.gray;
        } else if (time == 66){
            color = Color.LIGHT_GRAY;
        }else if (time == 100){
            color = Color.white;
        }
    }
    
    /**
     * indica si la arena ya se volvio blanca o no
     * @return boolean confirmando si es o no color blanco
     */
    public boolean isColor(){
        if (color != Color.WHITE){
            return false;
        }
        return true;
    }
}