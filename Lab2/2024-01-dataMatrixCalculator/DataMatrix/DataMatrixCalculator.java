import java.util.HashMap;

/** Data Matrix Calculator
 * 
 * @author CIS 2024-01
 */
    
public class DataMatrixCalculator{
    
    public HashMap<String, DataMatrix> variables;
    public boolean ok;
    
    /**
     * Constructor para DataMatrixCalculator.
     * Inicializa las variables y establece 'ok' en falso.
     */
    public DataMatrixCalculator(){
        variables = new HashMap<>();
        ok = false;
    }

    /**
     * Asigna una matriz a una variable.
     * @param name   El nombre de la variable.
     * @param values Matriz bidimensional que representa los valores de la matriz.
     */
    public void assign(String name, String values[][] ){
        DataMatrix matrix = new DataMatrix(values);
        variables.put(name, matrix);
        ok = true;
    }    
    
    /**
     * Consulta los nombres de las variables en la calculadora.
     * @return Un array de nombres de variables.
     */
    public String[] variables(){
        return variables.keySet().toArray(new String[0]);
    }    
       
    /**
     * Asigna el valor de una operación unaria a una variable.
     * @param a      El nombre de la variable donde se almacenará el resultado.
     * @param unary  Operador unario ('s' para forma, 'r' para transpuesta).
     * @param b      El nombre de la variable sobre la cual se realizará la operación unaria.
     */
    //Assigns the value of an operation to a variable (unary operations)
    // a := unary b
    //The unary operators are: s (hape), r (transpuesta)
    // shape returns a (1x2) matrix with the shape (rows, columns)
    // In transpuesta b is a (1x2) matrix with the shape (rows, columns)
    //If this is not possible, it returns the (1x1) matrix with a false value
    public void assign(String a, char unary, String b){
        if (variables.containsKey(b)) {
            DataMatrix matrixB = variables.get(b);
            DataMatrix resultMatrix;
            switch (unary) {
                case 's':
                    // devuelve una matriz con la forma
                    resultMatrix = matrixB.shape();
                    break;
                case 'r':
                    //devuelve la transpuesta de la matriz
                    int oRows = matrixB.rows;
                    int oColumns = matrixB.columns;
                    resultMatrix = matrixB.transpuesta(oRows, oColumns);
                    break;
                default:
                    // Maneja los operadores binarios no soportados.
                    resultMatrix = new DataMatrix(new String[][]{{"FALSE"}});
                    ok = false;
                    return;
                }
            //Almacena
            variables.put(a, resultMatrix);
            ok = true;
        } else {
        ok = false;
        }
    }
    
    /**
     * Realiza la asignación de una operación binaria entre dos matrices y almacena
     * el resultado en una nueva variable.
     * @param a        Nombre de la variable donde se almacenará el resultado.
     * @param b        Nombre de la primera matriz en la operación.
     * @param binary   Operador binario ('+' para suma, '-' para resta).
     * @param c        Nombre de la segunda matriz en la operación.
     */
    //Assigns the value of an operation to a variable (simple binary operations)
    // a := b simple c
    //The simple operators are: +, -
     //If this is not possible, it returns the (1x1) matrix with a false value
    public void assign(String a, String b, char binary, String c){
        if (variables.containsKey(b) && variables.containsKey(c)) {
        DataMatrix matrixB = variables.get(b);
        DataMatrix matrixC = variables.get(c);
        DataMatrix resultMatrix;
        if (binary == '+') {
            resultMatrix = matrixB.add(matrixC);
        } else if (binary == '-') {
            resultMatrix = matrixB.sub(matrixC);
        } else {
            // Maneja los operadores binarios no soportados.
            resultMatrix = new DataMatrix(new String[][]{{"FALSE"}});
            ok = false;
        }
        //Almacena
        variables.put(a, resultMatrix);
        ok = true;
        } else {
        ok = false;
        }
        }   
    
    /**
     * Devuelve la representación en cadena de una matriz asociada a una variable.
     * @param variable El nombre de la variable.
     * @return La representación en cadena de la matriz o un mensaje de error si la variable no se encuentra.
     */
    public String toString(String variable){
        if (variables.containsKey(variable)) {
            return variables.get(variable).toString();
        } else {
            return "Variable no encontrada";
        }
    }
    
    /**
     * Verifica si la última operación fue exitosa.
     * @return Verdadero si la última operación fue exitosa, falso en caso contrario.
     */
    public boolean ok(){
        return ok;
    }
    
    //-------ciclo 2-------------------
        
    /**
     * Consulta la dimensión de una operación unaria.
     * @param unario El nombre de la variable que se utilizará en la operación unaria.
     * @param sample dice si la operacion va a ser "s" o "r"
     * @return Un array de dos elementos que representa las dimensiones resultantes (filas y columnas).
     */
    public int[] getUnaryDimension(String name, String unary) {
        if (variables.containsKey(name)) {
            DataMatrix matrixB = variables.get(name);
            int[] dimension = new int[2];
            switch (unary) {
                case "s":
                    dimension[0] = matrixB.rows;
                    dimension[1] = matrixB.columns;
                    break;
                case "r":
                    dimension[0] = matrixB.columns;
                    dimension[1] = matrixB.rows;
                    break;
                default:
                    // Maneja los operadores unarios no soportados.
                    dimension[0] = -1;
                    dimension[1] = -1;
                    ok = false;
                    break;
                }
            return dimension;
        } else {
            ok = false;
            return null;
        }
    }
    
    /**
     * Hace la transpuesta de una matriz
     * @param name busca en variables el nombre relacionado con los valores de la matriz
     * @return devuelve la matriz transpuesta
     */
    public DataMatrix transpuesta(String name){
        if (variables.containsKey(name)){
            DataMatrix matrixA = variables.get(name);
            DataMatrix resultMatrix = matrixA.transpuesta(matrixA.rows, matrixA.columns);
            return resultMatrix;
        } else {
            ok = false;
            return null;
        }
    }
    
    //-------CICLO3-----------------
    
    /**
    * Realiza la operación de suma entre dos matrices y almacena el resultado en una nueva variable.
    * @param nameResult Nombre de la variable donde se almacenará el resultado.
    * @param nameA Nombre de la primera matriz.
    * @param nameB Nombre de la segunda matriz.
    */
    public void suma(String nameResult, String nameA, String nameB) {
        if (variables.containsKey(nameA) && variables.containsKey(nameB)) {
            DataMatrix matrixA = variables.get(nameA);
            DataMatrix matrixB = variables.get(nameB);
            // Verifica que las matrices tengan las mismas dimensiones para la suma
            if (matrixA.rows == matrixB.rows && matrixA.columns == matrixB.columns) {
                DataMatrix resultado = matrixA.add(matrixB);
                variables.put(nameResult, resultado);
                ok = true;
            } else{
                ok = false;
            }
        } else{
            ok = false;
        }
    }
    
    /**
    * Realiza la operación de resta entre dos matrices y almacena el resultado en una nueva variable.
    * @param nameResult Nombre de la variable donde se almacenará el resultado.
    * @param nameA Nombre de la primera matriz.
    * @param nameB Nombre de la segunda matriz.
    */
    public void resta(String nameResult, String nameA, String nameB) {
       if (variables.containsKey(nameA) && variables.containsKey(nameB)) {
           DataMatrix matrixA = variables.get(nameA);
           DataMatrix matrixB = variables.get(nameB);
           // Verifica que las matrices tengan las mismas dimensiones para la resta
           if (matrixA.rows == matrixB.rows && matrixA.columns == matrixB.columns) {
               DataMatrix resultado = matrixA.sub(matrixB);
              variables.put(nameResult, resultado);
               ok = true;
           } else {
               ok = false;
           }
       } else {
           ok = false;
       }
    }
}