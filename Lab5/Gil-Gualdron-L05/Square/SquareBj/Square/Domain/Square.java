package Domain;

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * La clase Square modela un juego de tablero con fichas móviles.
 * Las fichas pueden moverse en cuatro direcciones: norte, sur, este u oeste.
 * Cada ficha tiene un color asociado.
 * @author Sofia Gil - Santiago Gualdron
 * @version 1.0
 */
public class Square{
    public char[][] tablero; //matriz del tablero
    private final char[] w = {'n','s','e','o'}; //posiciones a las que puede ir el tablero
    private int moves; //cantidad de movimientos que ha hecho el usuario
    private int cantFichas; //dice cuantas fichas existen en total dentro del tablero
    private ArrayList<int[]> huecos; //cantidad de huecos que lleva el tablero
    private ArrayList<int[]> fichas; //cantidad de fichas que lleva el tablero
    private ArrayList<int[]> completos; //casillas que ya estan completas

    /**
     * Constructor de la clase Square.
     * 
     * @param n Dimensiones del tablero (n x n)
     * @param m Cantidad de fichas en el juego
     */
    public Square(int n, int m) throws SquareException{
        if (n <= 1){
            throw new SquareException(SquareException.SIZE_NOT_VALID);
        }else if (m <= 0 || m >= n*n){
            throw new SquareException(SquareException.SQUARE_NOT_VALID);
        }
        tablero = new char[n][n];
        moves = 0;
        cantFichas = m;
        fichas = new ArrayList<>();
        huecos = new ArrayList<>();
        completos = new ArrayList<>();
        createBoard();
    }
    
    /**
     * Mueve todas las fichas en la dirección especificada.
     * 
     * @param side Dirección de movimiento ('n', 's', 'e', 'o')
     * @return boolean, el cual indica si el usuario gano o perdio o continua jugando
     */
    public Boolean move(char side) throws SquareException{
        if (elementNotInW(side)){
            throw new SquareException(SquareException.CARDINAL_NOT_VALID);
        }
        ArrayList<int[]> resp = new ArrayList<>();
        switch(side){
            case 'n':
                for (int i = 0; i < fichas.size(); i++){
                    int[] positions = fichas.get(i);
                    int posPosition = positions[1] - 1;
                    if (posPosition == -1 || compCasilla(posPosition, positions[2]) == false){
                        posPosition = positions[1];
                    }
                    tablero[positions[1]][positions[2]] = '\u0000';
                    tablero[posPosition][positions[2]] = cambio(positions[0]);
                    resp.add(new int[] {positions[0], posPosition, positions[2]});
                }
                break;
            case 's':
                for (int i = 0; i < fichas.size(); i++){
                    int[] positions = fichas.get(i);
                    int posPosition = positions[1] + 1;
                    if (posPosition == tablero.length || compCasilla(posPosition, positions[2]) == false){
                        posPosition = positions[1];
                    }
                    tablero[positions[1]][positions[2]] = '\u0000';
                    tablero[posPosition][positions[2]] = cambio(positions[0]);
                    resp.add(new int[] {positions[0], posPosition, positions[2]});
                }
                break;
            case 'e':
                for (int i = 0; i < fichas.size(); i++){
                    int[] positions = fichas.get(i);
                    int posPosition = positions[2] + 1;
                    if (posPosition == tablero.length || compCasilla(positions[1], posPosition) == false){
                        posPosition = positions[2];
                    }
                    tablero[positions[1]][positions[2]] = '\u0000';
                    tablero[positions[1]][posPosition] = cambio(positions[0]);
                    resp.add(new int[] {positions[0], positions[1], posPosition});
                }
                break;
            case 'o':
                for (int i = 0; i < fichas.size(); i++){
                    int[] positions = fichas.get(i);
                    int posPosition = positions[2] - 1;
                    if (posPosition == -1 || compCasilla(positions[1], posPosition) == false){
                        posPosition = positions[2];
                    }
                    tablero[positions[1]][positions[2]] = '\u0000';
                    tablero[positions[1]][posPosition] = cambio(positions[0]);
                    resp.add(new int[] {positions[0], positions[1], posPosition});
                }
                break;
        }
        fichas= resp;
        moves +=1;
        return rellenar();
    }
    
    /**
     * Verifica si alguna ficha ha completado una casilla vacía, eliminando la ficha y la casilla vacía si se cumple la condición.
     * También verifica si todas las casillas ya están completas y muestra un mensaje de victoria en ese caso.
     * @return retorna si el jugador gano (true), perdio (false) o le toca seguir jugando (null).
     */
    private Boolean rellenar(){
        if (completos.size() != cantFichas){
            for (int i = 0; i < fichas.size(); i++){
                for (int j = 0; j < huecos.size(); j++){
                    int[] posFichas = fichas.get(i);
                    int[] posHuecos = huecos.get(j);
                    if (posFichas[1] == posHuecos[1] && posFichas[2] == posHuecos[2]){
                        if (posFichas[0] == posHuecos[0] + 3){
                            fichas.remove(i);
                            huecos.remove(j);
                            tablero[posFichas[1]][posFichas[2]] = cambio(posHuecos[0] + 6);
                            completos.add(new int[] {posHuecos[0]+6, posHuecos[1], posHuecos[2]});
                        }else if (posFichas[0] != posHuecos[0] + 3){
                            return false;
                        }
                    }
                }
            }
        }else{
            return true;
        }
        return null;
    }
    
    /**
     * Verifica si una casilla está vacía o no.
     * 
     * @param i Fila de la casilla
     * @param j Columna de la casilla
     * @return true si la casilla está vacía, false de lo contrario
     */
    private boolean compCasilla(int i, int j){
        if(tablero[i][j] != 'r' && tablero[i][j] != 'g' && tablero[i][j] != 'b' && tablero[i][j] != 'z'){
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene el tablero de juego.
     * 
     * @return La matriz que representa el tablero
     */
    public char[][] board(){
        return tablero;
    }
    
    /**
     * Obtiene la cantidad de movimientos realizados por el usuario.
     * 
     * @return Cantidad de movimientos
     */
    public int moves(){
        return moves;
    }
    
     /**
     * Calcula el porcentaje de casillas completas en el tablero.
     * 
     * @return Porcentaje de casillas completas
     */
    public int percentageOK(){
        return (completos.size() * 100) / cantFichas;
    }

    /**
     * Obtiene el contenido de una posición específica en el tablero.
     * 
     * @param i Fila de la posición
     * @param j Columna de la posición
     * @return Contenido de la posición (ficha o casilla vacía)
     */
    public char getSquarePosition(int i,int j){
        return tablero[i][j];
    }
    
    /**
     * Crea el tablero inicial con las fichas y casillas vacías.
     */
    private void createBoard(){
        int clasif = 0;
        for(int i = 0; i < cantFichas*2; i++){
            int[] casilla = randomPositions();
            char f = cambio(clasif);
            tablero[casilla[0]][casilla[1]] = f;
            if (clasif == 0 || clasif == 1 || clasif == 2){
                huecos.add(new int[] {clasif, casilla[0], casilla[1]});
            }else{
                fichas.add(new int[] {clasif, casilla[0], casilla[1]});
            }
            clasif += 1;
        }
    }
    
    /**
     * Genera posiciones aleatorias válidas en el tablero.
     * 
     * @return Un array de dos elementos que representa una posición válida en el tablero (fila y columna)
     */
    private int[] randomPositions(){
        Random random = new Random();
        int row = random.nextInt(tablero.length);
        int column = random.nextInt(tablero.length);
        while (tablero[row][column] != '\u0000'){
            row = random.nextInt(tablero.length);
            column = random.nextInt(tablero.length);
        }
        return new int[] {row, column};
    }
    
    /**
     * Asigna el carácter correspondiente a cada tipo de ficha.
     * 
     * @param number Número que representa el tipo de ficha
     * @return Carácter asociado a la ficha
     */
    public char cambio(int number){
        switch (number){
            case 0:
                return 'R';
            case 1:
                return 'G';
            case 2:
                return 'B';
            case 3:
                return 'r';
            case 4:
                return 'g';
            case 5:
                return 'b';
            case 6:
                return 'd';
            case 7:
                return 'e';
            case 8:
                return 'f';
            default:
                return 'z';
        }
    }
    
    /**
     * Obtiene la lista de posiciones ocupadas por las fichas en el tablero.
     * 
     * @return Lista de posiciones de las fichas
     */
    public ArrayList<int[]> getFichas() {
        return fichas;
    }
    
    /**
     * Obtiene la lista de posiciones vacías en el tablero.
     * 
     * @return Lista de posiciones vacías
     */
    public ArrayList<int[]> getHuecos() {
        return huecos;
    }
    
    /**
     * Establece el número de filas del tablero.
     * 
     * @param filas Nuevo número de filas
     * @throws IllegalArgumentException Si el número de filas es menor o igual a 0
     */
    public void changeSize(int filas) {
        if (filas > 0) {
            char[][] nuevoTablero = new char[filas][filas];
            // Itera sobre las filas hasta llegar al mínimo entre las filas actuales y las filas especificadas
            for (int i = 0; i <tablero.length; i++) {
                for (int j = 0; j < tablero.length; j++) {
                    //  posicion de la matriz actual a la nueva matriz
                    nuevoTablero[i][j] = tablero[i][j];
                }
            }
            this.tablero = nuevoTablero;
        } else {
            throw new IllegalArgumentException("El número de filas debe ser mayor que 0.");
        }
    }
    
    /**
     * revisa que el caracter ingresado si sea alguna de las 4 posiciones.
     * @param e, el elemento a ser comparado
     * @return false si el elemento esta dentro de la lista y true si no.
     */
    private boolean elementNotInW(char e){
        for (char c : w) {
            if (c == e) {
                return false;
            }
        }
        return true;
    }
}
