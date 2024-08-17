package domain;

/**
 * Genera diferentes mensajes de errores que pueden ocurrir durante la ejecucion
 * @author Santiago Gualdron-Sofia Gil
 * @version 1.0
 */
public class ProjectException extends Exception {
    public static final String TIME_EMPTY = "El tiempo es nulo.";
    public static final String TIME_ERROR = "El tiempo es erroneo.";
    public static final String COMPOSED_EMPTY = "La actividad no tiene subactividades"; 
    public static final String IMPOSSIBLE = "No se puede calcular";
    public static final String UNKNOWN = "No existe";
    public static final String REPLICATE_NAME = "Nombre ya existente";
    public static final String COST_IS_NOT_NUMBER = "el costo o precio no es un numero valido";
    public static final String TIME_IS_NOT_NUMBER = "el tiempo no es un numero valido";
    public static final String TIMETYPE_IS_NOT_STRING = "el tipo de tiempo no es paralelo o secuencial";
    /**
     * Constructor de ProjectException
     */
    public ProjectException(String message) {
        super(message);
    }
}