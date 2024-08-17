
/**
 * Write a description of class tablero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tablero
{
    // instance variables - replace the example below with your own
    Cannibal cannibal1;
    Cannibal cannibal2;
    Cannibal cannibal3;
    Boat boat;
    Missionary missionary1;
    Missionary missionary2;
    Missionary missionary3;
    Rectangle grass1;
    Rectangle grass2;
    Rectangle river;

    /**
     * Constructor for objects of class tablero
     */
    public tablero()
    {
        // initialise instance variables
        cannibal1 = new Cannibal();
        cannibal2 = new Cannibal();
        cannibal3 = new Cannibal();
        boat = new Boat();
        missionary1 = new Missionary();
        missionary2 = new Missionary();
        missionary3 = new Missionary();
        grass1 = new Rectangle();
        grass2 = new Rectangle();
        river = new Rectangle();
    }
    
    /**
     * Método que crea un tablero estandar del juego
     */
    public void makeBoard(){
        grass1.changeColor("green");
        grass2.changeColor("green");
        river.changeColor("blue");
        grass1.changeSize(350, 120);
        grass2.changeSize(350, 120);
        river.changeSize(350, 200);
        river.moveVertical(-50);
        river.makeVisible();
        grass1.moveVertical(-50);
        grass1.moveHorizontal(-110);
        grass2.moveVertical(-50);
        grass2.moveHorizontal(145);
        grass1.makeVisible();
        grass2.makeVisible();
    }
}
