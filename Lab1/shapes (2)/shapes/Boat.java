
/**
 * La clase Boat representa un objeto gráfico que simboliza un barco.
 * Incluye un cuerpo rectangular y una bandera triangular, ambos con tamaño
 * y color personalizables. El barco puede moverse, rotarse y su visibilidad
 * puede ser modificada.
 * @author Santiago Gualdron y Sofia Gil 
 * @version 1.0
 */
public class Boat{
    public String side;
    Rectangle boat;
    Triangle flag;
    int number;

    /**
     * Constructor for objects of class Boat
     */
    public Boat(){
        side = "left";
        boat = new Rectangle();
        flag = new Triangle();
        number = 2;
    }
    
    /**
     * Configura la representación visual del barco con colores y posiciones predeterminadas.
     */
    public void getBoat(){
        boat.makeVisible();
        flag.makeVisible();
        boat.changeColor("brown");
        flag.changeColor("brown");
        boat.changeSize(20, 80);
        flag.changeSize(15, 10);
        flag.moveHorizontal(-60);
        flag.moveVertical(-16);
    }
    
    /**
     * Hace visible al barco en la pantalla.
     */
    public void makeVisible(){
        boat.makeVisible();
        flag.makeVisible();
    }
    
    /**
     * Hace invisible al barco en la pantalla.
     */
    public void makeInvisible(){
        boat.makeInvisible();
        flag.makeInvisible();
    }
    
    /**
     * Mueve el barco a una posición específica en la pantalla.
     * 
     * @param f La posición horizontal a la que moverse.
     * @param c La posición vertical a la que moverse.
     */
    public void moveTo(int f, int c) {
        int pasosh = Math.abs(f) / 5; 
        for (int i = 0; i < pasosh; i++) {
            boat.slowMoveHorizontal(f < 0 ? -5 : 5);
            flag.slowMoveHorizontal(f < 0 ? -5 : 5);
                    }
        int pasosv = c/ 5;//negativo para arriba, positivo para abajo
        for (int i = 0; i < Math.abs(pasosv); i++) {
            boat.slowMoveVertical(c < 0 ? -5 : 5);
            flag.slowMoveVertical(c < 0 ? -5 : 5);
        }
    }
    
    /**
     * Rota la bandera del barco hacia un lado específico.
     */
    public void rotate(){
        if (side == "right"){
            flag.moveHorizontal(-60);
        } else{
            flag.moveHorizontal(60);
        }
    }
    
    /**
     * Cambia el tamaño del barco y de la bandera a un nuevo tamaño.
     * 
     * @param newSize El nuevo tamaño para el barco y la bandera.
     */
    public void changeSize(int newSize){
        boat.changeSize(0, newSize);
        flag.changeSize(15, newSize/2);
    }
    
    /**
     * Cambia el color del barco y de la bandera a un nuevo color.
     * 
     * @param newColor El nuevo color para el barco y la bandera.
     */
    public void changeColor(String newColor){
        boat.changeColor(newColor);
        flag.changeColor(newColor);
    }
}
