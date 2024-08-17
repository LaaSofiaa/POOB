package domain;  
/**
 * Clase que representa una actividad simple en un proyecto.
 * Extiende la clase abstracta Activity.
 */
public class Simple extends Activity{
    private Integer time;
    /**
     * Constructor para crear una actividad simple con nombre, costo y tiempo.
     * @param name Nombre de la actividad.
     * @param cost Costo de la actividad.
     * @param time Tiempo de la actividad.
     */
    public Simple(String name, Integer cost, Integer time){
        super(name,cost);
        this.time=time;
    }    
    
    /**
     * Obtiene el tiempo de la actividad.
     * @return Tiempo de la actividad.
     * @throws ProjectException Si el tiempo está vacío o fuera de rango.
     */
    @Override
    public int time() throws ProjectException{
       if (time == null) throw new ProjectException(ProjectException.TIME_EMPTY);
       if (time < 1 || time>24) throw new ProjectException(ProjectException.TIME_ERROR);
       return time;
    }    
    
    /**
     * Obtiene el costo de la actividad simple.
     * @return Costo de la actividad (si es nulo o negativo, retorna 0).
     * @throws ProjectException No lanza excepción en este caso.
     */
    @Override
    public int cost() throws ProjectException{
       return (cost == null || cost<0) ? 0 : cost;
    }   
    
    /**
     * Obtiene una representación en cadena de la actividad simple.
     * @return Cadena con el nombre, costo y tiempo de la actividad simple.
     */
    @Override
    public String data(){
        return name+". Costo:" +cost+".Tiempo:"+time;
    }
}
