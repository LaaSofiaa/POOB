package domain;
import java.awt.Color;
import java.io.Serializable;

/**
 * Clase Flower donde genera flores en diferentes espacios del garden
 * 
 * @author Sofia Gil, Santiago Gualdron 
 * @version 1.0
 */
public class Flower extends Agent implements Thing, Serializable{
    protected char nextState;
    protected Color color;
    protected Garden garden;
    protected int row,column;


    /**
     * Create a new flower (row, column) in the garden.
     * Every new flower is going to be alive in the following state.
     * @param garden donde se va a pintar la flor
     * @param row del garden
     * @param column del garden
     */
    public Flower(Garden garden,int row, int column){
        this.garden=garden;
        this.row=row;
        this.column=column;
        nextState=Agent.ALIVE;
        garden.setThing(row,column,(Thing)this);  
        color=Color.red;
    }

    /**
     * Returns the shape
     * @return 3, el numero para el shape FLOWER
     */
    public final int shape(){
        return Thing.FLOWER;
    }
    
    /**
     * Returns the row
     * @return int la fila de la flor
     */
    public final int getRow(){
        return row;
    }

    /**
     * Returns the row
     * @return int la columna de la flor
     */
    public final int getColumn(){
        return column;
    }
    
    /**
     * Returns the garden
     * @return Garden el jardin donde esta pintada la flor
     */
    public final Garden getGarden(){
        return this.garden;
    }
    
    /**
     * Returns the garden
     * @return Color el color que tiene la flor actualmente
     */
    public final Color getColor(){
        return color;
    }
    
    /**
     * mueve la flor
     */
    public void move(){
    }
    
    /**
     * indica como actua la flor cada vez que hay un Tic-tac
     */
    public void act(){
        turn();
        int timeTic = getTime();
        if (timeTic == 3){
            nextState = Agent.DEAD;
            color = Color.orange;
        } else if (timeTic == 5){
            nextState = Agent.ALIVE;
            color = Color.red;
            reTime();   
        }
    }
}
