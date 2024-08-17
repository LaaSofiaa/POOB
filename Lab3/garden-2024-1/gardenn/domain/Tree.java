package domain;
import java.awt.Color;


/**
 * Clase Tree para dibujar cuadrados verdes que se van marchitando y
 * regenerando cada cierto tiempo
 * 
 * @author Sofia Gil, Santiago Gualdron 
 * @version 1.0
 */
public class Tree implements Thing{
    private Color color;
    private int time;
    
    /**
     * Constructor for objects of class Tree
     */
    public Tree(){
        this.color = Color.GREEN;
        time = 0;
    }
    
    /**
     * @return Color el color que tiene actualmente el Tree
     */
    public Color getColor(){
        return color;
    }
    
    /**
     * indica como actua el arbol cada vez que hay un Tic-tac
     */
    public void act(){
        time += 1;
        if (time == 5){
            color = Color.ORANGE;
        } else if (time == 10){
            color = Color.GREEN;
            time =0;
        } 
        
    }
}
