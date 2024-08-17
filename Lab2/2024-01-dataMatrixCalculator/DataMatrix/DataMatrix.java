/**
 * @author ECI, 2024
 *
 */
public class DataMatrix{
    public Data [][] data;
    public int rows;
    public int columns;
    
    /**
     * Creates a matrix with the defined size and value
     */
    public DataMatrix(String [][] data) {
        this.rows = data.length;
        this.columns = data[0].length;
        this.data = new Data[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.data[i][j] = new Data(data[i][j]);
            }
        }
        
    }
    
    /**
     *Devuelve una matriz con la forma de la matriz principal
     *@return la forma de la matriz
     */
    public DataMatrix shape(){
        DataMatrix shapeMatrix = new DataMatrix(new String[1][2]);
        shapeMatrix.data[0][0] = new Data(String.valueOf(rows));
        shapeMatrix.data[0][1] = new Data(String.valueOf(columns));
        return shapeMatrix;
    }
    
    /**
     * convierte a string el valor que tiene la matriz
     * @param row fila del valor de la matriz.
     * @column columna del valor de la matriz.
     * @return el valor en string
     */
    public String toString(int row, int column){
        return data[row][column].toString();
    }
    
    /**
     * genera una matriz con la transpuesta de la matriz principal
     * @param rows cantidad de filas de la matriz principal
     * @param columns cantidad de columnas de la matriz principal
     * @return la matriz transpuesta
     */
    public DataMatrix transpuesta(int rows, int columns){
        DataMatrix transpuestaMa = new DataMatrix(new String[columns][rows]);
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                transpuestaMa.data[i][j] = data[j][i];
            }
        }
        return transpuestaMa;
    }
    
    /**
     * suma de 2 matrices que tienen las mismas dimensiones
     * @param t segunda matriz para sumarla con la primera
     * @return una nueva matriz con la suma de las 2 anteriores
     */
    public DataMatrix add(DataMatrix t){
        if (rows != t.rows || columns != t.columns) {
            throw new IllegalArgumentException("Matrices must have the same dimensions for addition.");
        }
        DataMatrix resultMatrix = new DataMatrix(new String[rows][columns]);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                resultMatrix.data[i][j] = this.data[i][j].add(t.data[i][j]);
            }
        }
        return resultMatrix;
    }
    
    /**
     * resta de 2 matrices que tienen las mismas dimensiones
     * @param t segunda matriz para restarla con la primera
     * @return una nueva matriz con la resta de las 2 anteriores
     */
    public DataMatrix sub(DataMatrix t) {
        if (rows != t.rows || columns != t.columns) {
            throw new IllegalArgumentException("Matrices must have the same dimensions for subtraction.");
        }
        DataMatrix resultMatrix = new DataMatrix(new String[rows][columns]);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                resultMatrix.data[i][j] = this.data[i][j].sub(t.data[i][j]);
            }
        }
        return resultMatrix;
    }
    
    /**
     * revisa que las 2 matrices tengan las mismas dimensiones y los mismos valores Data
     * @param other segunda matriz para comparar con la primera
     */
    public boolean equals(DataMatrix other) {
            if (other == null || rows != other.rows || columns != other.columns) {
            return false;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!this.data[i][j].equals(other.data[i][j])) {
                    return false;
                }
            }
        }
        return true;
        }
    
    /**
     * revisa que las 2 matrices tengan las mismas dimensiones y los mismos valores Data
     * @param other segunda matriz para comparar con la primera
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) 
            return true;
        if (other == null || getClass() != other.getClass()) 
            return false;
        return equals((DataMatrix) other);
    }
    
    /**
     * crea un String con todos los elementos de la matriz seguidos 
     * (si hay mas de 1 fila los pone consecuentes)
     * @return String la cadena con todos los valores de la matriz
     */
    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.append(data[i][j].toString()).append(" ");
            }
        }
        return result.toString().trim();
    }
}