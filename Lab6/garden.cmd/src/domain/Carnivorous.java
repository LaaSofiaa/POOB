package domain;
import java.awt.Color;
import java.io.Serializable;

/**
 * Clase Carnivorous donde genera flores carnivoras en diferentes espacios del garden
 * 
 * @author Sofia Gil, Santiago Gualdron 
 * @version 1.0
 */
public class Carnivorous extends Flower implements Serializable{
    
    /**
     * Create a new carnivorous (row, column) in the garden.
     * Every new carnivorous is going to be alive in the following state.
     * @param garden donde se va a pintar la flor
     * @param row del garden
     * @param column del garden
     */
    public Carnivorous(Garden garden,int row, int column){
        super (garden, row, column);
        this.color = Color.blue;
    }
    
    /**
     * la planta carnivora se mueve hacia la direccion de la flor mas cercana que tenga
     * mientras esta flor no sea carnivora
     */
    @Override
    public void move(){
        int currentRow = getRow();
        int currentColumn = getColumn();
        int closestFlowerRow = 0;
        int closestFlowerColumn = 0;
        double minDistance = Double.MAX_VALUE;
        for (int r = 0; r < garden.getLength() - 1; r++) {
            for (int c = 0; c < garden.getLength() - 1; c++) {
                Thing thing = garden.getThing(r, c);
                if ( !(thing instanceof Carnivorous) && thing instanceof Flower) {
                    double distance = Math.sqrt(Math.pow(r - currentRow, 2) + Math.pow(c - currentColumn, 2));
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestFlowerRow = r;
                        closestFlowerColumn = c;
                    }
                }
            }
        }
        int temporalDistanceR = currentRow -closestFlowerRow;
        int temporalDistanceC = currentColumn -closestFlowerColumn;
        if (temporalDistanceR >0 && (row + 1 < 30)){
            row -=1;
        }else if (temporalDistanceR<0 && (row - 1 > 0)){
            row +=1;
        }
        if (temporalDistanceC >0 && (column + 1 < 30)){
            column -=1;
        }else if (temporalDistanceC<0 && (column - 1 > 0)){
            column +=1;
        }
        garden.setThing(currentRow,currentColumn,null);
        garden.setThing(row,column,this);
    }
    
    /**
     * indica como actua la flor carnivora cada vez que hay un Tic-tac
     */
    @Override
    public void act(){
        turn();
        int timeTic = getTime();
        if (timeTic == 3){
            nextState = Agent.DEAD;
            color = Color.green;
        } else if (timeTic == 5){
            nextState = Agent.ALIVE;
            color = Color.blue;
            reTime();  
        }
        this.move();
    }

}
