package Domain;


/**
 * Write a description of class SquareException here.
 * 
 * @author Santiago Gualdron-Sofia Gil
 * @version 1.0
 */
public class SquareException extends Exception {
    public static final String SIZE_NOT_VALID = "La longitud de la matriz no es valida";
    public static final String SQUARE_NOT_VALID = "La cantidad de cuadrados no es valida";
    public static final String CARDINAL_NOT_VALID = "El punto cardinal no es valido";
    
    /**
     * Constructor de ProjectException
     */
    public SquareException(String message) {
        super(message);
    }
}
