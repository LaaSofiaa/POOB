import java.util.ArrayList;
import java.util.List;
/**
* La clase Game representa el juego de misioneros y canibales a través de un río.
* El objetivo es llevar a todos los personajes de una orilla a otra sin violar ciertas restricciones.
* Esta clase gestiona la lógica del juego y las interacciones entre los personajes y el bote.
* 
* @author Sofia Gil, Santiago Gualdron
* @version 1.0
*/
public class Game{
    private tablero board;
    private int left;
    private int right;
    private int middle;
    
    /**
     * Constructor de la clase Game. Inicializa el tablero y los contadores de cada lado.
     */
    public Game(){
        board = new tablero();
        left = 0;
        right = 0;
        middle = 0;
    }
    
    /**
     * Configura el estado inicial del juego, colocando los personajes en sus posiciones iniciales.
     */
    public void estadoInicial(){
        //Dibujamos el tablero
        board.makeBoard();
        //posicion inicial
        board.missionary1.getMissionary();
        board.missionary2.getMissionary();
        board.missionary3.getMissionary();
        board.cannibal1.getCannibal();
        board.cannibal2.getCannibal();
        board.cannibal3.getCannibal();
        board.boat.getBoat();
        //posicion inical en la orilla 
        board.missionary1.moveTo(-10, 100);
        board.missionary2.moveTo(-25, 100);
        board.missionary3.moveTo(-40, 100);
        board.cannibal1.moveTo(-15, 100);
        board.cannibal2.moveTo(-35, 100);
        board.cannibal3.moveTo(-55, 100);
        board.boat.moveTo(10, 130);
    }
    
    /**
     * Mueve un personaje desde una orilla al bote.
     * @param player El personaje que se va a mover al bote.
     */
    public void subirPersonaje(String player){
        if (board.boat.number != 0){
            if (board.boat.side == "left"){
                switch (player){
                    case "missionary1":
                        board.missionary1.moveTo(50,-35);
                        board.missionary1.inside = true;
                        middle += 1;
                        left -= 1;
                        break;
                    case "missionary2":
                        board.missionary2.moveTo(65,-35);
                        board.missionary2.inside = true;
                        middle += 1;
                        left -= 1;
                        break;
                    case "missionary3":
                        board.missionary3.moveTo(75,-35);
                        board.missionary3.inside = true;
                        middle += 1;
                        left -= 1;
                        break;
                    case "cannibal1":
                        board.cannibal1.moveTo(80,8);
                        board.cannibal1.inside = true;
                        middle -= 1;
                        left += 1;
                        break;
                    case "cannibal2":
                        board.cannibal2.moveTo(100,8);
                        board.cannibal2.inside = true;
                        middle -= 1;
                        left += 1;
                        break;
                    case "cannibal3":
                        board.cannibal3.moveTo(120,8);
                        board.cannibal3.inside = true;
                        middle -= 1;
                        left += 1;
                        break;
                }
            } else{
                switch (player){
                    case "missionary1":
                        board.missionary1.moveTo(-60, -35);
                        board.missionary1.inside = true;
                        middle += 1;
                        right -= 1;
                        break;
                    case "missionary2":
                        board.missionary2.moveTo(-75, -35);
                        board.missionary2.inside = true;
                        middle += 1;
                        right -= 1;
                        break;
                    case "missionary3":
                        board.missionary3.moveTo(-85,-35);
                        board.missionary3.inside = true;
                        middle += 1;
                        right -= 1;
                        break;
                    case "cannibal1":
                        board.cannibal1.moveTo(-30, 5);
                        board.cannibal1.inside = true;
                        middle -= 1;
                        right += 1;
                        break;
                    case "cannibal2":
                        board.cannibal2.moveTo(-50, 5);
                        board.cannibal2.inside = true;
                        middle -= 1;
                        right += 1;
                        break;
                    case "cannibal3":
                        board.cannibal3.moveTo(-70, 5);
                        board.cannibal3.inside = true;
                        middle -= 1;
                        right += 1;
                        break;
                }
            }
            board.boat.number -= 1;
        } else{
            System.out.println("El bote ya esta lleno.");
        }
    }
    
    /**
     * Mueve el bote de una orilla a otra, llevando consigo a los personajes que estén dentro.
     */
    public void moverBote() {
        // Mueve la barca a la otra orilla
        board.boat.rotate();
        if (board.boat.side == "left"){
            board.boat.moveTo(55, 0);
            if (board.missionary1.inside){
                board.missionary1.moveTo(55, 0);
            }
            if (board.missionary2.inside){
                board.missionary2.moveTo(55, 0);
            }
            if (board.missionary3.inside){
                board.missionary3.moveTo(55, 0);
            }
            if (board.cannibal1.inside){
                board.cannibal1.moveTo(55, 0);
            }
            if (board.cannibal2.inside){
                board.cannibal2.moveTo(55, 0);
            }
            if (board.cannibal3.inside){
                board.cannibal3.moveTo(55, 0);
            }
            board.boat.side = "right";
        } else{
            board.boat.moveTo(-55, 0);
            if (board.missionary1.inside){
                board.missionary1.moveTo(-55, 0);
            }
            if (board.missionary2.inside){
                board.missionary2.moveTo(-55, 0);
            }
            if (board.missionary3.inside){
                board.missionary3.moveTo(-55, 0);
            }
            if (board.cannibal1.inside){
                board.cannibal1.moveTo(-55, 0);
            }
            if (board.cannibal2.inside){
                board.cannibal2.moveTo(-55, 0);
            }
            if (board.cannibal3.inside){
                board.cannibal3.moveTo(-55, 0);
            }
            board.boat.side = "left";
        }
        perder();
    }
    
    /**
     * Baja un personaje desde el bote a la orilla correspondiente.
     * @param player El personaje que se va a bajar desde el bote.
     */
    public void bajarPersonaje(String player){
        if (board.boat.number <= 3){
            if (board.boat.side == "right"){
                switch (player){
                    case "missionary1":
                        board.missionary1.moveTo(60, 35);
                        board.missionary1.inside = false;
                        middle -= 1;
                        right += 1;
                        break;
                    case "missionary2":
                        board.missionary2.moveTo(75, 35);
                        board.missionary2.inside = false;
                        middle -= 1;
                        right += 1;
                        break;
                    case "missionary3":
                        board.missionary3.moveTo(95, 35);
                        board.missionary3.inside = false;
                        middle -= 1;
                        right += 1;
                        break;
                    case "cannibal1":
                        board.cannibal1.moveTo(30, -5);
                        board.cannibal1.inside = false;
                        middle += 1;
                        right -= 1;
                        break;
                    case "cannibal2":
                        board.cannibal2.moveTo(50, -5);
                        board.cannibal2.inside = false;
                        middle += 1;
                        right -= 1;
                        break;
                    case "cannibal3":
                        board.cannibal3.moveTo(70, -5);
                        board.cannibal3.inside = false;
                        middle += 1;
                        right -= 1;
                        break;
                }
            } else{
                switch (player){
                    case "missionary1":
                        board.missionary1.moveTo(-50,35);
                        board.missionary1.inside = false;
                        middle -= 1;
                        left += 1;
                        break;
                    case "missionary2":
                        board.missionary2.moveTo(-65, 35);
                        board.missionary2.inside = false;
                        middle -= 1;
                        left += 1;
                        break;
                    case "missionary3":
                        board.missionary3.moveTo(-75, 35);
                        board.missionary3.inside = false;
                        middle -= 1;
                        left += 1;
                        break;
                    case "cannibal1":
                        board.cannibal1.moveTo(-80,-8);
                        board.cannibal1.inside = false;
                        middle += 1;
                        left -= 1;
                        break;
                    case "cannibal2":
                        board.cannibal2.moveTo(-100,-8);
                        board.cannibal2.inside = false;
                        middle += 1;
                        left -= 1;
                        break;
                    case "cannibal3":
                        board.cannibal3.moveTo(-120,-8);
                        board.cannibal3.inside = false;
                        middle += 1;
                        left -= 1;
                        break;
                }
            }
            board.boat.number += 1;
        } else{
            System.out.println("El bote ya esta vacio.");
        }
    }
    
    /**
     * Verifica si se ha perdido el juego.
     */
    public void perder(){
        if (left == -1 || right == -1){
            if (this.right + this.middle == 1){
                System.out.println("has perdido!");
            }
        } else if (left == -2 || right == -2){
            if (this.right + this.middle == 2){
                System.out.println("has perdido!");
            }
        }
    }
}