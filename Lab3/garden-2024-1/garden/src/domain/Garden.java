package domain;
import java.util.*;

/**
 * Clase Garden que genera la matriz con las posiciones de los Things
 * 
 * @author Sofia Gil, Santiago Gualdron 
 * @version 1.0
 */
public class Garden{
    static public int LENGTH=30;
    private Thing[][] garden;
    
    /**
     * Constructor for objects of class Garden
     */
    public Garden() {
        garden=new Thing[LENGTH][LENGTH];
        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                garden[r][c]=null;
            }
        }
        setThing(0,0,new Water());
        for (int i=1;i<5;i++){
            for (int j=1;j<5;j++){
                setThing(LENGTH-i,LENGTH-j,new Water());
            }
        }
        someThings();
    }
    
    /**
     * @return int la longitud que tiene el jardin
     */
    public int  getLength(){
        return LENGTH;
    }
    
    /**
     * @param r, la fila que se desea buscar
     * @param c, la columna que se desea buscar
     * @return Thing el objeto que esta guardadon en la posicion r, c
     */
    public Thing getThing(int r,int c){
        return garden[r][c];
    }

    /**
     * agrega el objeto "e" a la posicion r, c
     * @param r, la fila que se desea buscar
     * @param c, la columna que se desea buscar
     * @param e, el objeto Thing que se desea guardar
     */
    public void setThing(int r, int c, Thing e){
        garden[r][c]=e;
    }

    /**
     * objetos que se agregan basicos para el tablero garden
     */
    public void someThings(){
        Flower rose = new Flower(this, 10, 10);
        Flower violet = new Flower(this, 15, 15);
        setThing(10,10,rose);
        setThing(15,15,violet);
        
        Carnivorous venus = new Carnivorous(this, 8,14);
        Carnivorous sundeuos = new Carnivorous(this, 22,13);
        setThing(8,14,venus);
        setThing(22,13,sundeuos);
        
        Sand tatacoa = new Sand();
        Sand sahara = new Sand();
        setThing(1,28,tatacoa);
        setThing(1,29,sahara);
        
        Multicolor santiago = new Multicolor(this,20,20);
        Multicolor sofia = new Multicolor(this,21,21);
        setThing(20,20,santiago);
        setThing(21,21,sofia);
        
        Tree gualdron = new Tree();
        Tree gil = new Tree();
        setThing(29,0,gualdron);
        setThing(18,2,gil);
    }
    
    /**
     * indica como actua cada objeto Thing en el garden
     * cada vez que hay un Tic-tac
     */
    public void ticTac(){
        List<Carnivorous> lista = new ArrayList<>();
        List<Multicolor>listaa = new ArrayList<>();
        for (int i = 0; i < LENGTH; i++){
            for (int j = 0; j < LENGTH; j++){
                Thing thing = getThing(i, j);
                if (thing instanceof Carnivorous){
                    Carnivorous carnivorous = (Carnivorous) thing;
                    lista.add(carnivorous);
                }else if(thing instanceof Multicolor){
                    Multicolor multicolor = (Multicolor) thing;
                    listaa.add(multicolor);
                }else if (thing instanceof Flower){
                    Flower flower = (Flower) thing;
                    flower.act();
                } else if (thing instanceof Sand){
                    Sand sand = (Sand) thing;
                    sand.act();
                    boolean confirm = sand.isColor();
                    if (confirm){
                        garden[i][j] = null;
                    }
                }else if (thing instanceof Tree){
                    Tree tree =(Tree) thing;
                    tree.act();
                }
            }
        }
        for (Carnivorous car : lista){
            car.act();
        }
        for (Multicolor mo : listaa){
            mo.act();
        }
    }
}
