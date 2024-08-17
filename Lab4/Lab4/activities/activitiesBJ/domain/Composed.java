
package domain;  
 
import java.util.ArrayList;

/**
 * Clase que representa una actividad compuesta en un proyecto.
 * Extiende la clase Activity.
 */
public class Composed extends Activity{
   
    private boolean parallel;
    private ArrayList<Activity> activities;
    
    /**
     * Constructor para crear una actividad compuesta.
     * @param name Nombre de la actividad compuesta.
     * @param cost Costo de la actividad compuesta.
     * @param parallel Indica si la actividad compuesta es paralela (true) o secuencial (false).
     */
    public Composed(String name, Integer cost, boolean parallel){
        super(name,cost);
        this.parallel=parallel;
        activities= new ArrayList<Activity>();
    }

     /**
     * Agrega una nueva actividad a la actividad compuesta.
     * @param a Actividad a agregar.
     */   
    public void add(Activity a){
        activities.add(a);
    }
        
    @Override
    public int cost(){
        return 0;
    }
    
    @Override
     /**
     * Método que calcula el tiempo total de las actividades que componen la actividad compuesta.
     * @return Tiempo total de las actividades.
     * @throws ProjectException Si hay algún error al calcular el tiempo.
     */
    public int time() throws ProjectException{
        if (activities.isEmpty()){
            throw new ProjectException(ProjectException.COMPOSED_EMPTY);
        }
        int totalTime = 0;
        for (Activity activity: activities) {
            if (parallel){
                totalTime = Math.max(totalTime, activity.time());
            } else{
                totalTime += activity.time();
            }
        }
        return totalTime;
    }
    
     /**
     * Calcula un tiempo estimado utilizando valores por defecto cuando sea necesario.
     * @param dUnknow Tiempo estimado para actividades desconocidas.
     * @param dError Tiempo estimado para actividades con errores.
     * @param dEmpty Tiempo estimado cuando la actividad compuesta no tiene actividades.
     * @return Tiempo estimado de las actividades.
     */
    public int time(int dUnknow, int dError, int dEmpty){
        if (activities.isEmpty()){
            return dEmpty;
        }
        int totalTime = 0;
        for (Activity activity: activities) {
            try{
                totalTime += activity.time();
            }catch(ProjectException e){
                if(ProjectException.TIME_EMPTY==e.getMessage()){
                    totalTime += dUnknow;
                }else if (ProjectException.TIME_ERROR==e.getMessage()){
                    totalTime += dError;
                }else{
                    totalTime += dEmpty;
                }
            }
        }
        return totalTime; 
    }

     /**
     * Calcula un tiempo estimado considerando la modalidad (promedio o máximo) de las actividades conocidas.
     * @param modality 'A' para promedio, 'M' para máximo.
     * @return Tiempo estimado de las actividades.
     * @throws ProjectException Si no se puede calcular el tiempo.
     */
    public int time(char modality) throws ProjectException{
        if(activities.isEmpty()){
            throw new ProjectException(ProjectException.COMPOSED_EMPTY);
        }
        int totalTime = 0;
        int actividades = 0;//actividades conocidas
        boolean actividadesUoE = false;//activities to estimate unknown ones or those with error.
        //tengo que mirar todas las actividades
        for(Activity activity : activities){
            try{
                int activityTime = activity.time();//miramos el tiempo
                totalTime += activityTime;//suma el tiempo 
                actividades++;
            }catch(ProjectException e){
                continue;
            }
        }
        
        if(modality == 'A' && actividades > 0){
            //promedio
            int x = totalTime/actividades; 
            return x;
        }else if (modality == 'M' && actividades >0){
            //maximo
            int y = Integer.MIN_VALUE;
            for (Activity activity : activities){
                try{
                    int activityTime = activity.time();
                    y = Math.max(y,activityTime);
                }catch(ProjectException ignored ){
                    continue;
                }
            }
            return y;
        }
        throw new ProjectException(ProjectException.IMPOSSIBLE);
    }
    
     /**
     * Calculates an time of a subactivity
     * @return time
     * @throws ProjectException UNKNOWN, if it doesn't exist. IMPOSSIBLE, if it can't be calculated
     */
    public int time(String activity) throws ProjectException{
        //voy a buscar la subactividad
        for(Activity actividad: activities){
            //miro el nombre de la actividad con la subactividad
            if (actividad.name().equals(activity)){
                try {
                    //tiempo de la subactividad 
                    int totalTime = actividad.time();
                    return totalTime;
                }catch(ProjectException e){
                    //excepciones de las subactiviades 
                    if (ProjectException.UNKNOWN.equals(e.getMessage())){
                        //no existe
                        throw new ProjectException(ProjectException.UNKNOWN);
                    }else{
                        //no se puede calcular el tiempo
                        throw new ProjectException(ProjectException.IMPOSSIBLE);
                    }
                }
            }
        }
        throw new ProjectException(ProjectException.UNKNOWN);
    }   
    
    /**
     * Devuelve los datos de la actividad, incluyendo su nombre, tipo (paralela o secuencial) y los datos de sus subactividades.
     * @return Una cadena de texto con los datos de la actividad y sus subactividades.
     * @throws ProjectException Si hay algún error al obtener los datos de las subactividades.
     */
    @Override
    public String data() throws ProjectException{
        StringBuffer answer=new StringBuffer();
        answer.append(name+". Tipo "+ (parallel ? "Paralela": "Secuencial")+". ");
        for(Activity b: activities) {
            answer.append("\n\t"+b.data());
        }
        return answer.toString();
    } 
    
    public ArrayList<Activity> getActivities(){
        return activities;
    }
}
