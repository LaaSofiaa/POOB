
/**
 * La clase Cannibal representa una representación gráfica simple de un personaje caníbal.
 * 
 * Sofia Gil, Santiago Gualdron
 * 1.0
 */
public class Cannibal{
    public String side;
    Rectangle body;
    Circle eye1;
    boolean inside;
    
    /**
     * Constructor for objects of class Cannibal
     */
    public Cannibal(){
        side = "left";
        inside = false;
        body = new Rectangle();
        eye1 = new Circle();
    }
    
    /**
     * Configura la representación visual del Cannibal con colores y posiciones predeterminadas.
     */
    public void getCannibal(){
        body.changeColor("black");
        eye1.changeColor("red");
        body.makeVisible();
        eye1.makeVisible();
        body.changeSize(25, 15);
        eye1.changeSize(7);
        eye1.moveHorizontal(58);
        eye1.moveVertical(5);
    }
    
    /**
     * Hace visible al Cannibal en la pantalla.
     */
    public void makeVisible(){
        body.makeVisible();
        eye1.makeVisible();
    }
    
    /**
     * Hace invisible al Cannibal en la pantalla.
     */
    public void makeInvisible(){
        body.makeInvisible();
        eye1.makeInvisible();
    }
    
    /**
     * Rota al Cannibal moviendo su ojo al lado opuesto.
     */
    public void rotate(){
        if (side == "right"){
            eye1.moveHorizontal(-7);
        } else{
            eye1.moveHorizontal(7);
        }
    }
    
    /**
     * Mueve al Cannibal a una posición específica en la pantalla.
     * 
     * @param f La posición horizontal a la que moverse.
     * @param c La posición vertical a la que moverse.
     */
    public void moveTo(int f, int c) {
        int pasosh = Math.abs(f) / 5; 
        for (int i = 0; i < pasosh; i++) {
            body.slowMoveHorizontal(f < 0 ? -5 : 5);  
            eye1.slowMoveHorizontal(f < 0 ? -5 : 5);
            }
        int pasosv = c/ 5;//negativo para arriba, positivo para abajo
        for (int i = 0; i < Math.abs(pasosv); i++) {
            body.slowMoveVertical(c < 0 ? -5 : 5);
            eye1.slowMoveVertical(c < 0 ? -5 : 5);
        }
    }
    
    /**
     * Cambia el tamaño del ojo y el cuerpo del Cannibal a un nuevo tamaño.
     * 
     * @param newSize El nuevo tamaño para el ojo y el cuerpo.
     */
    public void changeSize(int newSize){
        eye1.changeSize(newSize);
        body.changeSize(newSize, newSize);
    }
    
    /**
     * Cambia el color del ojo del Cannibal a un nuevo color.
     * 
     * @param newColor El nuevo color para el ojo.
     */
    public void changeColorEye(String newColor){
        eye1.changeColor(newColor);
    }
    
    /**
     * Cambia el color del cuerpo del Cannibal a un nuevo color.
     * 
     * @param newColor El nuevo color para el cuerpo.
     */
    public void changeColorBody(String newColor){
        body.changeColor(newColor);
    }
}
