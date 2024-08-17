import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class DataMatrixCalculatorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DataMatrixCalculatorTest{
     @Test
    public void shouldInitializeVariables() {
        DataMatrixCalculator calculator = new DataMatrixCalculator();
        assertNotNull(calculator.variables);
        assertTrue(calculator.variables.isEmpty());
        assertFalse(calculator.ok);
    }

    @Test
    public void shouldNotInitializeVariables() {
        DataMatrixCalculator calculator = new DataMatrixCalculator();
        assertFalse(calculator.ok);
    }
        
    @Test
    public void shouldAssignMatrixToVariable() {
        DataMatrixCalculator calculator = new DataMatrixCalculator();
        String[][] values = {{"1", "2"}, {"3", "4"}};
        calculator.assign("A", values);
        assertTrue(calculator.ok());
        assertEquals(1, calculator.variables.size());
        assertTrue(calculator.variables.containsKey("A"));
    }
    
    @Test
    public void shouldVariables() {
        DataMatrixCalculator calculator = new DataMatrixCalculator();
        String[][] values = {{"1", "2"}, {"3", "4"}};
        calculator.assign("A", values);
        calculator.assign("B", new String[][]{{"5", "6"}});
        String[] variableNames = calculator.variables();
        assertArrayEquals(new String[]{"A", "B"}, variableNames);
    }
    
    @Test
    public void shouldNotVariables() {
        DataMatrixCalculator calculator = new DataMatrixCalculator();
        String[] variableNames = calculator.variables();
        assertArrayEquals(new String[]{}, variableNames); 
    }
    
    @Test
    public void shouldAssignBinary() {
        DataMatrixCalculator calculator = new DataMatrixCalculator();
        String[][] matrixA = {
                {"1", "2", "3"},
                {"4", "5", "6"}
        };
        String[][] matrixB = {
                {"7", "8", "9"},
                {"10", "11", "12"}
        };
        calculator.assign("A", matrixA);
        calculator.assign("B", matrixB);
        calculator.assign("C", "A", '+', "B");
        String result = calculator.toString("C");
    }
    
    @Test
    void shouldnotAssignBinary() {
        DataMatrixCalculator calculator = new DataMatrixCalculator();
        calculator.assign("A", new String[][]{{"1", "2"}, {"3", "4"}});
        // B no es asignado
        calculator.assign("C", "A", '+', "B");
        assertFalse(calculator.ok()); 
        assertEquals("Variable no encontrada", calculator.toString("C"));
    }
    
    @Test
    public void shouldAssignUnary() {
        DataMatrixCalculator calculator = new DataMatrixCalculator();
        DataMatrix matrixB = new DataMatrix(new String[][]{{"1", "2"}, {"3", "4"}});
        calculator.variables.put("b", matrixB);
        calculator.assign("a", 's', "b");
        assertTrue(calculator.ok); 
        assertTrue(calculator.variables.containsKey("a")); 

    }
    
     @Test
    public void shouldNotAssignUnary() {
        DataMatrixCalculator calculator = new DataMatrixCalculator();
        calculator.assign("a", 's', "invalid");
        assertFalse(calculator.ok); 
        assertFalse(calculator.variables.containsKey("a"));
    }
    
    @Test
    public void shouldGetUnaryDimension() {
        DataMatrixCalculator calculator = new DataMatrixCalculator();
        String[][] matrixData = {{"1", "2"}, {"3", "4"}};
        calculator.assign("matrixA", matrixData);

        int[] dimensions = calculator.getUnaryDimension("matrixA", "s");

        assertNotNull(dimensions);
        assertEquals(2, dimensions.length);
        assertEquals(2, dimensions[0]); // Verifica las filas
        assertEquals(2, dimensions[1]); // Verifica las columnas
    }
    
    @Test
    public void shouldGetMatrixTranspuesta(){
        DataMatrixCalculator calculator = new DataMatrixCalculator();
        String[][] matrixData = {{"1", "2"}, {"3", "4"}};
        calculator.assign("matrixA", matrixData);
        DataMatrix transp = calculator.transpuesta("matrixA");
        assertEquals("1.0 3.0 2.0 4.0", transp.toString());
    }
    
    @Test
    public void shouldAddTwoDataMatrix(){
        DataMatrixCalculator calculator = new DataMatrixCalculator();
        String[][] matrixDataA = {{"10", "10", "10"}, {"false", "True", "TRUE"}, {"d", "L", "J"}};
        String[][] matrixDataB = {{"15", "a", "False"}, {"0", "c", "true"}, {"10", "M", "TRue"}};
        calculator.assign("matrixA", matrixDataA);
        calculator.assign("matrixB", matrixDataB);
        calculator.suma("matrixC", "matrixA", "matrixB");
        assertEquals("25.0 107.0 TRUE FALSE c TRUE 110.0 M T", calculator.toString("matrixC"));
    }
    
    @Test
    public void shouldDSubTwoDataMatrix(){
        DataMatrixCalculator calculator = new DataMatrixCalculator();
        String[][] matrixDataA = {{"10", "10", "10"}, {"false", "True", "TRUE"}, {"d", "L", "J"}};
        String[][] matrixDataB = {{"15", "a", "False"}, {"0", "c", "true"}, {"10", "M", "TRue"}};
        calculator.assign("matrixA", matrixDataA);
        calculator.assign("matrixB", matrixDataB);
        calculator.resta("matrixC", "matrixA", "matrixB");
        assertEquals("-5.0 -87.0 FALSE FALSE T TRUE 90.0 L J", calculator.toString("matrixC"));
    }
}