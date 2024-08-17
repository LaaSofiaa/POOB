package domain;
import java.awt.Color;
import java.util.Random;

/**
 * Clase Multicolor donde genera flores multicolores en diferentes espacios del garden
 * 
 * @author Sofia Gil, Santiago Gualdron 
 * @version 1.0
 */
public class Multicolor extends Flower{
    
    /**
     * Create a new multicolor (row, column) in the garden.
     * Every new multicolor is going to be alive in the following state.
     * @param garden donde se va a pintar la flor
     * @param row del garden
     * @param column del garden
     */
    public Multicolor(Garden garden,int row, int column){
        super(garden,row,column);
        this.color= Color.magenta;
    }
    
    /**
     * indica como actua la flor multicolor cada vez que hay un Tic-tac
     */
    @Override
    public void act(){
        color = getRandomColor();
        this.move();
    }
    
    /**
     * la planta multicolor se mueve hacia la direccion del Tree mas cercano que tenga
     */
    @Override
    public void move(){
        int currentRow = getRow();
        int currentColumn = getColumn();
        int closestSandRow = 0;
        int closestSandColumn = 0;
        double minDistance = Double.MAX_VALUE;
        for (int r = 0; r < garden.getLength() - 1; r++) {
            for (int c = 0; c < garden.getLength() - 1; c++) {
                Thing thing = garden.getThing(r, c);
                if ( thing instanceof Tree) {
                    double distance = Math.sqrt(Math.pow(r - currentRow, 2) + Math.pow(c - currentColumn, 2));
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestSandRow = r;
                        closestSandColumn = c;
                    }
                }
            }
        }
        int temporalDistanceR = currentRow -closestSandRow;
        int temporalDistanceC = currentColumn -closestSandColumn;
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
     * Metodo para obtener un color aleatorio
     * @return Color un color aleatorio.
     */
    private Color getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256); // Valores entre 0 y 255
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }
    
    }

