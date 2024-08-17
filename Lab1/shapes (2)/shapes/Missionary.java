
/**
 * La clase Missionary representa un objeto gráfico que simboliza a un misionero.
 * Incluye un cuerpo rectangular, una cabeza triangular y un ojo circular, todos con
 * tamaño y color personalizables. El misionero puede moverse, rotarse y su visibilidad
 * puede ser modificada.
 * 
 * @author Santiago Gualdron y Sofia Gil  
 * @version 1.0
 */
public class Missionary{
    public String side;
    Rectangle body;
    Triangle head;
    Circle eye1;
    boolean inside;
    
    /**
     * Constructor for objects of class Missionary
     */
    public Missionary(){
        side = "left";
        body = new Rectangle();
        head = new Triangle();
        eye1 = new Circle();
        inside = false;
    }
    
    /**
     * Configura la representación visual del misionero con colores y posiciones predeterminadas.
     */
    public void getMissionary(){
        
        body.changeColor("orange");
        head.changeColor("magenta");
        eye1.changeColor("blue");
        body.makeVisible();
        eye1.makeVisible();
        head.makeVisible();
        body.changeSize(15, 10);
        head.changeSize(15, 10);
        eye1.changeSize(7);
        eye1.moveHorizontal(55);
        head.moveHorizontal(-65);
        body.moveVertical(50);
        head.moveVertical(35);
        eye1.moveVertical(50);
    }
    
    /**
     * Hace visible al misionero en la pantalla.
     */
    public void makeVisible(){
        body.makeVisible();
        head.makeVisible();
        eye1.makeVisible();
    }
    
    /**
     * Hace invisible al misionero en la pantalla.
     */
    public void makeInvisible(){
        body.makeInvisible();
        head.makeInvisible();
        eye1.makeInvisible();
    }
    
    /**
     * Rota el ojo del misionero hacia un lado específico.
     */
    public void rotate(){
        if (side == "right"){
            eye1.moveHorizontal(-7);
        } else{
            eye1.moveHorizontal(7);
        }
    }
    
    /**
     * Mueve al misionero a una posición específica en la pantalla.
     * 
     * @param f La posición horizontal a la que moverse.
     * @param c La posición vertical a la que moverse.
     */
    public void moveTo(int f, int c) {
        int pasosh = Math.abs(f) / 5; 
        for (int i = 0; i < pasosh; i++) {
            body.slowMoveHorizontal(f < 0 ? -5 : 5); 
            head.slowMoveHorizontal(f < 0 ? -5 : 5);
            eye1.slowMoveHorizontal(f < 0 ? -5 : 5);
            }
        int pasosv = c/ 5;//negativo para arriba, positivo para abajo
        for (int i = 0; i < Math.abs(pasosv); i++) {
            body.slowMoveVertical(c < 0 ? -5 : 5);
            head.slowMoveVertical(c < 0 ? -5 : 5);
            eye1.slowMoveVertical(c < 0 ? -5 : 5);
        }
    }

    /**
     * Cambia el tamaño del cuerpo, cabeza y ojo del misionero a un nuevo tamaño.
     * 
     * @param newSize El nuevo tamaño para el cuerpo, cabeza y ojo.
     */
    public void changeSize(int newSize){
        body.changeSize(newSize, newSize);
        head.changeSize(newSize, newSize);
        eye1.changeSize(newSize);
    }
    
    /**
     * Cambia el color del ojo del misionero a un nuevo color.
     * 
     * @param newColor El nuevo color para el ojo.
     */
    public void changeColorEye(String newColor){
        eye1.changeColor(newColor);
    }
    
    /**
     * Cambia el color del cuerpo del misionero a un nuevo color.
     * 
     * @param newColor El nuevo color para el cuerpo.
     */
    public void changeColorBody(String newColor){
        body.changeColor(newColor);
    }
    
    /**
     * Cambia el color de la cabeza del misionero a un nuevo color.
     * 
     * @param newColor El nuevo color para la cabeza.
     */
    public void changeColorHead(String newColor){
        head.changeColor(newColor);
    }
    
    /**
     * Obtiene el lado en el que se encuentra el misionero (izquierda o derecha).
     * 
     * @return El lado actual del misionero.
     */
    public String getSide() {
        return side;
    }
}
