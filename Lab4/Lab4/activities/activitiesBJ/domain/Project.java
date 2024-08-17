
package domain; 
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase que representa un proyecto con actividades.
 * Permite agregar, consultar y seleccionar actividades, así como gestionar su información.
 */
public class Project{
    private HashMap<String,Activity> activities;

    /**
     * Constructor para crear un nuevo proyecto.
     * Inicializa el mapa de actividades y agrega algunas actividades predeterminadas.
     */
    public Project(){
        activities= new HashMap<String,Activity>();
        addSome();
    }
    
    /**
     * Método privado para agregar actividades predeterminadas al proyecto
     */
    private void addSome(){
        String [][] activities= {{"Buscar datos","50","50", "" },
                                 {"Evaluar datos","80","80",""},
                                 {"Limpiar datos","100","100",""},
                                 {"Preparar datos", "50", "Secuencial", "Buscar datos\nEvaluar datos\nLimpiar datos"}};
        for (String [] c: activities){
            try{
                add(c[0],c[1],c[2],c[3]);
            }catch (ProjectException e){
                continue;
            }
        }
        String [][] activities2={
                                {"Buscar datos","50","10000", "" },
                                {"Ver datos","50","50", "" },
                                {"Escribir datos","50","50", "" },
                                {"Corregir datos", "100", "Paralela", "Buscar datos\nVer datos\nEscribir datos"}};
        for (String [] c: activities2){
            try{
                add(c[0],c[1],c[2],c[3]);
            }catch (ProjectException e){
                continue;
            }
        }
    }

    /**
     * Consulta una actividad en el proyecto.
     * @param name Nombre de la actividad a consultar.
     * @return La actividad correspondiente al nombre especificado.
     */
    public Activity consult(String name){
        return activities.get(name.toUpperCase());
    }

    /**
     * Agrega una nueva actividad al proyecto.
     * @param name Nombre de la actividad.
     * @param cost Costo de la actividad.
     * @param timeType Tipo de tiempo de la actividad (Secuencial o Paralela).
     * @param theActivities Actividades que componen esta actividad compuesta (opcional).
     * @throws ProjectException Si ocurre un error al agregar la actividad.
     */
    public void add(String name, String cost, String timeType, String theActivities) throws ProjectException{
        try{
            comprobate(cost, timeType, theActivities);
            noRepetidos(name);
            Activity na;
            if (theActivities.equals("")){
               na=new Simple(name,cost.equals("") ? null : Integer.parseInt(cost),timeType.equals("") ? null : Integer.parseInt(timeType));
            }else{ 
                na = new Composed(name,cost.equals("") ? null : Integer.parseInt(cost), timeType.equals("") ? true : timeType.toUpperCase().charAt(0)=='P');
                String [] aSimples= theActivities.split("\n");
                for (String b : aSimples){
                    ((Composed)na).add(activities.get(b.toUpperCase()));
                }
            }
            activities.put(name.toUpperCase(),na);
        } catch (ProjectException e){
            throw e;
        }
    }
    
    /**
     * Selecciona actividades que empiecen con un prefijo dado.
     * @param prefix Prefijo para seleccionar actividades.
     * @return Lista enlazada de actividades seleccionadas.
     * @throws IllegalArgumentException Si el prefijo es nulo o vacío.
     */
    public LinkedList<Activity> select(String prefix){
        if(prefix ==null || prefix.isEmpty()){
            throw new IllegalArgumentException("El prefijo no puede ser nulo o vacio.");
        }
        LinkedList <Activity> answers= new LinkedList<>();
        prefix=prefix.toUpperCase();
        for(Activity activity : activities.values()){
            if(activity.name().toUpperCase().startsWith(prefix)){
                answers.add(activity);
            }   
        }
        return answers;
    }


    /**
     * Obtiene los datos de las actividades seleccionadas.
     * @param selected Lista de actividades seleccionadas.
     * @return Cadena con los datos de las actividades.
     */
    public String data(LinkedList<Activity> selected){
        StringBuffer answer=new StringBuffer();
        answer.append(activities.size()+ " actividades\n");
        for(Activity p : selected) {
            try{
                answer.append('>' + p.data());
                answer.append("\n");
            }catch(ProjectException e){
                answer.append("**** "+e.getMessage());
            }
        }    
        return answer.toString();
    }
    
    
    /**
     * Busca actividades que empiecen con un prefijo dado y devuelve sus datos.
     * @param prefix Prefijo para buscar actividades.
     * @return Cadena con los datos de las actividades encontradas.
     */ 
    public String search(String prefix){
        return data(select(prefix));
    }
    
    
    /**
     * Obtiene los datos de todas las actividades del proyecto.
     * @return Cadena con los datos de todas las actividades.
     */    
    public String toString(){
        return data(new LinkedList<>(activities.values()));
        
    }
    
    /**
     * Obtiene el número total de actividades en el proyecto.
     * @return Número de actividades.
     */
    public int numberActivitys(){
        return activities.size();
    }

    /**
     * verifica que el nombre no este ya incluido en alguna de las actividades
     * @param name nombre que se va a verificar
     */
    private void noRepetidos(String name) throws ProjectException{
        for(String names : activities.keySet()){
            if (name.toUpperCase().equals(names)){
                throw new ProjectException(ProjectException.REPLICATE_NAME);
            }
        }
    }

    /**
     * comprueba que el costo no sea un string sino numero
     * revisa que e timetype sea el correcto para simple y compuesto
     * @param cost el costo que se va a revisar
     * @param timeType el tiempo o tipo para revisar en simple o compuesto
     * @param theActivities las actividades para compuesto las cuales indicaran si es simple o compuesto.
     */
    private void comprobate(String cost, String timeType, String theActivities) throws ProjectException{
        //condicional para saber que costo no es vacio
        if (cost != ""){
            //intenta pasar a entero a costo
            try {
                int numero = Integer.parseInt(cost);
            } catch (NumberFormatException e) {
                throw new ProjectException(ProjectException.COST_IS_NOT_NUMBER);
            }
        }
        //condicional para saber si TheActivities esta vacio
        if (theActivities.equals("")){
            //condicional para saber que tiempo no es vacio
            if (timeType != ""){
                //intenta pasar a entero al tiempo
                try {
                    int numero = Integer.parseInt(timeType);
                } catch (NumberFormatException e) {
                    throw new ProjectException(ProjectException.TIME_IS_NOT_NUMBER);
                }
            }
            //si TheActivities no esta vacio
        }else{
            //condicional para saber que timeType no esta vacio
            if (timeType != ""){
                //intenta pasar a entero a timeType
                try {
                    int numero = Integer.parseInt(timeType);
                    throw new ProjectException(ProjectException.TIMETYPE_IS_NOT_STRING);
                } catch (NumberFormatException ignore) {}
            }
        }
    }
}
