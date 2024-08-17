package domain;
/**
 * Clase abstracta que representa una actividad en un proyecto.
 * Define métodos abstractos para obtener información sobre la actividad.
 */
public abstract class Activity{
    protected String name;
    protected Integer cost;

    /**
     * Constructor para crear una actividad con nombre y costo.
     * @param name Nombre de la actividad.
     * @param cost Costo de la actividad.
     */
    public Activity(String name, Integer cost){
        this.name=name;
        this.cost=cost;
    }
    
    /**
     * Obtiene el nombre de la actividad.
     * @return Nombre de la actividad.
     */
    public String name(){
        return name;
    }

    /**
     * Obtiene el tiempo de la actividad (método abstracto).
     * @return Tiempo de la actividad.
     * @throws ProjectException Si el tiempo no está disponible o hay un error.
     */
    public abstract int time() throws ProjectException;
    
    /**
     * Obtiene el costo de la actividad (método abstracto).
     * @return Costo de la actividad.
     * @throws ProjectException Si el costo no está disponible o hay un error.
     */
    public abstract int cost() throws ProjectException;
    
    /**
     * Obtiene una representación en cadena de la actividad (método abstracto).
     * @return Representación en cadena de la actividad.
     * @throws ProjectException Si los datos de la actividad no están completos.
     */   
    public abstract String data() throws ProjectException;

}
