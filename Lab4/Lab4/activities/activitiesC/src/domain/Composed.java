package domain;  
 
import java.util.ArrayList;

public class Composed extends Activity{
   
    private boolean parallel;
    private ArrayList<Activity> activities;
    
    /**
     * Constructs a new composed activity
     * @param name 
     * @param cost
     * @param parallel
     */
    public Composed(String name, Integer cost, boolean parallel){
        super(name,cost);
        this.parallel=parallel;
        activities= new ArrayList<Activity>();
    }

     /**
     * Add a new activity
     * @param a
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
     * Metodo que obtiene el tiempo total de las actividades
     * @return totalTime
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
     * Calculates an estimated time using default values when necessary
     * @param dUnknown
     * @param dError
     * @param dEmpty
     * @return 
     */
    public int time(int dUnknow, int dError, int dEmpty){
        if (activities.isEmpty()){
            return dEmpty;
        }
        int totalTime = 0;
        for (Activity activity: activities) {
            try{
                totalTime= activity.time();
            }catch(ProjectException e){
                if(ProjectException.TIME_EMPTY==e.getMessage()){
                    totalTime = dUnknow;
                }else if (ProjectException.TIME_ERROR==e.getMessage()){
                    totalTime = dError;
                }else{
                    totalTime =dEmpty;
                }
            }
        }
        return totalTime; 
    }

     /**
     * Calculate an estimated time considering the modality, if is possible.
     * @param modality ['A'(verage), 'M' (ax)] Use the average or maximum time of known activities to estimate unknown ones or those with error.
     * @return 
     * @throws ProjectException  IMPOSSIBLE, if it can't be calculated
     */
    public int time(char modality) throws ProjectException{
        if(activities.isEmpty()){
            throw new ProjectException(ProjectException.COMPOSED_EMPTY);
        }
        int totalTime = 0;
        int actividades = 0;//actividades conocidad
        boolean actividadesUoE = false;//activities to estimate unknown ones or those with error.
        //tengo que mirar todas las actividades
        for(Activity activity : activities){
            try{
                int activityTime = activity.time();//miramos el tiempo
                totalTime += activityTime;//suma el tiempo 
                actividades++;
            }catch(ProjectException e){
                actividadesUoE = true;
            }
        }
        
        if (actividadesUoE){
            //calcular el tiempo mirando la modalidad
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
                        y =Math.max(y,activityTime);
                    }catch(ProjectException ignored ){}
                }
                return y;
            }else{
                //si no se pudo calcular el tiempo
                throw new ProjectException(ProjectException.IMPOSSIBLE);
            }
        }else{
            return totalTime;
        }
        }
        

     /**
     * Calculates an time of a subactivity
     * @return time
     * @throws ProjectException UNKNOWN, if it doesn't exist. IMPOSSIBLE, if it can't be calculated
     */
    public int time(String activity) throws ProjectException{
        //voy a bsicar la subactividad 
        for(Activity actividad: activities){
            //miro el nombre de la actividad con la subactividad
            if (actividad.name().equals(activity)){
                try {
                    //tiempo de la subactividad 
                    return actividad.time();
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
}
