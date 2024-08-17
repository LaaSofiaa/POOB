    package test;
import domain.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;


public class ActivityTest{
   
    
    public ActivityTest(){
    }


    @Before
    public void setUp(){    
    }

    @After
    public void tearDown(){
    }
    
 
    @Test
    public void shouldCalculateTheTimeOfAComposedSecuencialActivity(){
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        try {
           assertEquals(45,c.time());
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }    
    
    @Test
    public void shouldCalculateTheTimeOfAComposedParallelActivity(){
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        try {
           assertEquals(20,c.time());
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }  
    
    
    @Test
    public void shouldThrowExceptionIfComposedIsEmpty(){
        //prueba para time()
        Composed c = new Composed("IS-BASICA", 100 , true);
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.COMPOSED_EMPTY,e.getMessage());
        }
        
        //prueba para time(modality)
        Composed d = new Composed("IS-ALREADY-BASICA", 101 , true);
        try { 
           int time=d.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.COMPOSED_EMPTY,e.getMessage());
        }    
    }    
    
    
    @Test
    public void shouldThrowExceptionIfThereIsErrorInTime(){
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, -20));
        c.add(new Simple("POOB", 10, 11));
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_ERROR,e.getMessage());
        }    
    }     
    
    @Test
    public void shouldThrowExceptionIfTimeIsNotKnown(){
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, null));
        c.add(new Simple("POOB", 10, 11));
        try { 
           int time=c.time();
           fail("El tiempo es nulo.");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_EMPTY,e.getMessage());
        }    
    }  
    
    @Test
    public void shouldCalculateUnknowComposed(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        c.add(new Simple("AYED", 10, null));
        c.add(new Simple("MBDA", 10, 14));
        c.add(new Simple("POOB", 10, 20));
        int time = c.time(5,0,0);
        assertEquals(time, 39);
    }
    
    @Test
    public void shouldCalculateErrorComposed(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 30));
        c.add(new Simple("POOB", 10, 20));
        int time = c.time(0,18,0);
        assertEquals(time, 48);
    }
    
    @Test
    public void shouldCalculateEmptyComposed(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        int time=c.time(0,0,23);
        assertEquals(time, 23);
    }
    
    @Test
    public void shouldCalculateTheAverageModality(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        c.add(new Simple("AYED", 10, 20));
        c.add(new Simple("MBDA", 10, -1));
        c.add(new Simple("POOB", 10, 23));
        c.add(new Simple("AYPR", 10, 15));
        try {
           assertEquals(19,c.time('A')); // 20 + 23 + 15 = 58 / 3 = 19 entero
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }
    
    @Test
    public void shouldCalculateTheMaximumModality(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        c.add(new Simple("AYED", 10, 20));
        c.add(new Simple("MBDA", 10, -1));
        c.add(new Simple("POOB", 10, 23));
        c.add(new Simple("AYPR", 10, 15));
        try {
           assertEquals(23,c.time('M')); //maximo entre los 3 que son validos
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }
    
    @Test
    public void shouldThrowExceptionImpossibleModality(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        c.add(new Simple("AYED", 10, null));
        c.add(new Simple("MBDA", 10, -1));
        //si todos los tiempos son diferentes a un numero valido
        try {
            int totalTime = c.time('M');
            fail("Threw a exception");
        } catch (ProjectException e){
            assertEquals(ProjectException.IMPOSSIBLE,e.getMessage());
        }
        //si se inserta algo diferente a "A" o "M"
        try {
            int totalTime = c.time('R');
            fail("Threw a exception");
        } catch (ProjectException e){
            assertEquals(ProjectException.IMPOSSIBLE,e.getMessage());
        }    
    }
    
    @Test
    public void shouldCalculateTheSpecificActivity(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        c.add(new Simple("MBDA", 10, -1));
        c.add(new Simple("POOB", 10, 23));
        c.add(new Simple("AYED", 10, 13));
        c.add(new Simple("AYPR", 10, 19));
        try {
           assertEquals(13,c.time("AYED"));
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }
    
    @Test
    public void shouldThrowExceptionUnknowSpecificActivity(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        c.add(new Simple("MBDA", 10, 19));
        c.add(new Simple("POOB", 10, -1));
        c.add(new Simple("AYED", 10, 13));
        try {
            int TotalTime = c.time("CNYT");
            fail("Threw a exception");
        } catch (ProjectException e){
            assertEquals(ProjectException.UNKNOWN,e.getMessage());
        }
    }
    
    @Test
    public void shouldThrowExceptionImpossibleSpecificActivity(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        c.add(new Simple("MBDA", 10, -1));
        c.add(new Simple("POOB", 10, 23));
        c.add(new Simple("AYED", 10, 13));
        c.add(new Simple("AYPR", 10, null));
        try {
           assertEquals(13,c.time("AYPR"));
            fail("Threw a exception");
        } catch (ProjectException e){
            assertEquals(ProjectException.IMPOSSIBLE,e.getMessage());
        }    
    }
    
    @Test
    public void shouldAddSimple() {
        Project project = new Project();
        Simple simple = new Simple("RECO",120,20);
        try{
            project.add("RECO","120","20", "");
            assertEquals(project.consult("RECO").time(), simple.time());
            assertEquals(project.consult("RECO").cost(), simple.cost());
        } catch (ProjectException e){
            fail("Excepción inesperada: " + e.getMessage());
        }
    }
    
    @Test
    public void shouldAddComposed(){
        Project project = new Project();
        //compuesto
        Composed c = new Composed("Ciclos", 100 , true);
        c.add(new Simple("MBDA", 10, 12));
        c.add(new Simple("POOB", 10, 23));
        ArrayList<Activity> activities = c.getActivities();
        try {
            //anade al projecto
            project.add("MBDA", "10", "12", "");
            project.add("POOB", "10", "23", "");
            project.add("Ciclos", "158" , "Paralela", "MBDA\nPOOB");
            assertEquals(2, activities.size());
            assertEquals(project.consult("Ciclos").time(), c.time());
            assertEquals(project.consult("Ciclos").cost(), c.cost());
        } catch (ProjectException e){
            fail("Excepción inesperada: " + e.getMessage());
        }
    }

        @Test
    public void shouldNotAddDuplicateName() {
        Project project = new Project();
        try {
            project.add("MBDA", "10", "12", "");
            project.add("MBDA", "150", "13", "");
        } catch (ProjectException e) {
            assertEquals(ProjectException.REPLICATE_NAME, e.getMessage());
        }
    }
    
    @Test
    public void shouldNotAddStringTime() {
        Project project = new Project();
        try {
            project.add("MBDA", "10", "tiempo", "");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_IS_NOT_NUMBER, e.getMessage());
        }
    }
    
    @Test
    public void shouldNotAddStringCost() {
        Project project = new Project();
        try {
            project.add("MBDA", "Coste", "12", "");
        } catch (ProjectException e) {
            assertEquals(ProjectException.COST_IS_NOT_NUMBER, e.getMessage());
        }
    }
    
    @Test
    public void shouldNotAddTimeTypeNumber() {
        Project project = new Project();
        try {
            project.add("MBDA", "10", "12", "88");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIMETYPE_IS_NOT_STRING, e.getMessage());
        }
    }
    
    @Test
    public void shouldToString(){
        Project project = new Project();
        String salida = "7 actividades\n" +
                        ">Corregir datos. Tipo Paralela. \n" +
                        "	Buscar datos. Costo:50.Tiempo:50\n" +
                        "	Ver datos. Costo:50.Tiempo:50\n" +
                        "	Escribir datos. Costo:50.Tiempo:50\n" +
                        ">Buscar datos. Costo:50.Tiempo:50\n" +
                        ">Limpiar datos. Costo:100.Tiempo:100\n" +
                        ">Ver datos. Costo:50.Tiempo:50\n" +
                        ">Evaluar datos. Costo:80.Tiempo:80\n" +
                        ">Escribir datos. Costo:50.Tiempo:50"+"\n" +
                        ">Preparar datos. Tipo Secuencial. \n" +
                        "	Buscar datos. Costo:50.Tiempo:50\n" +
                        "	Evaluar datos. Costo:80.Tiempo:80\n" +
                        "	Limpiar datos. Costo:100.Tiempo:100\n";
        assertEquals(salida, project.toString());
    }
    
        
    @Test
    public void shouldAddComposedLog(){
        Project project = new Project();
        //compuesto
        Composed c = new Composed("Ciclos", 100 , true);
        c.add(new Simple("MBDA", 10, 12));
        c.add(new Simple("POOB", 10, 23));
        ArrayList<Activity> activities = c.getActivities();
        try {
            //anade al projecto
            project.add("MBDA", "10", "12", "");
            project.add("POOB", "10", "23", "");
            project.add("Ciclos", "158" , "Paralela", "MBDA\nPOOB");
            assertEquals(2, activities.size());
            assertEquals(project.consult("Ciclos").time(), c.time());
            assertEquals(project.consult("Ciclos").cost(), c.cost());
        } catch (ProjectException e){
            Log.record(e);
            fail("Excepción inesperada: " + e.getMessage());
        }
    }   
           
    @Test 
    public void shouldNotFindActivity() throws ProjectException{
        Project project =new Project();
        try{
            project.search("Buscar datos");
        }catch(Exception e){
            fail("Hay un fallo");
        }
        }
    
    @Test
    public void shouldSearch(){
        Composed c = new Composed("Ciclos", 100 , true);
        c.add(new Simple("MBDA", 10, 12));
        c.add(new Simple("POOB", 10, 23));
        try{
            String data= c.data();
            assertTrue(data.contains("Ciclos"));
            assertTrue(data.contains("MBDA"));
            assertTrue(data.contains("POOB"));
        }catch(ProjectException e){
            fail("No deberia arrojar excepcion");
        }
    }
    }

