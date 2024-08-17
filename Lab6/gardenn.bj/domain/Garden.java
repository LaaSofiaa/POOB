package domain;
import java.util.*;
import java.io.*;

/**
 * Clase Garden que genera la matriz con las posiciones de los Things
 * 
 * @author Sofia Gil, Santiago Gualdron 
 * @version 1.0
 */
public class Garden implements Serializable{
    static public int LENGTH=30;
    private Thing[][] garden;
    
    /**
     * Constructor for objects of class Garden
     */
    public Garden() {
        MakeNullGarden();
        setThing(0,0,new Water());
        for (int i=1;i<5;i++){
            for (int j=1;j<5;j++){
                setThing(LENGTH-i,LENGTH-j,new Water());
            }
        }
        someThings();
    }
    
    private void MakeNullGarden(){
        garden=new Thing[LENGTH][LENGTH];
        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                garden[r][c]=null;
            }
        }
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
    
    public void createThings(String type,int newRow,int newColumn) throws GardenException {
        try{
            if(newRow >= 0 && newRow < garden.length && newColumn >= 0 && newColumn < garden.length){
                switch (type) {
                    case "Water":
                        setThing(newRow, newColumn, new Water());
                        break;
                    case "Sand":
                        setThing(newRow, newColumn, new Sand());
                        break;
                    case "Tree":
                        setThing(newRow, newColumn, new Tree());
                        break;
                    case "Flower":
                        setThing(newRow, newColumn, new Flower(this, newRow, newColumn));
                        break;
                    case "Carnivorous":
                        setThing(newRow, newColumn, new Carnivorous(this, newRow, newColumn));
                        break;
                    case "Multicolor":
                        setThing(newRow, newColumn, new Multicolor(this, newRow, newColumn));
                        break;
                    default:
                        throw new IllegalArgumentException("Tipo de entidad desconocido: " + type);
                }
            }else{
                throw new GardenException("Numeros fuera de rango");
            }
        }catch(NumberFormatException e1){
            throw new GardenException("Posiciones no encontradas");
        }
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
    
    /**
     * apertura de un nuevo archivo Garden
     * @param archivo, archivo que contiene todo el garden que se desea abrir
     * @throws GardenException, metodo actualmente en construccion.
     */
    public static Garden openArchivo (File archivo) throws GardenException{
        throw new GardenException("Opcion open en construccion. Archivo: "+archivo.getName());
    }
    
    /**
     * salvar el actual archivo Garden
     * @param archivo, archivo que va a contener todo el garden actual
     * @throws GardenException, metodo actualmente en construccion.
     */
    public void saveArchivo (File archivo) throws GardenException{
        throw new GardenException("Opcion save en construccion. Archivo: "+archivo.getName());
    }
    
    /**
     * importacion de un archivo Garden
     * @param archivo, archivo en el que se va a importar el garden
     * @throws GardenException, metodo actualmente en construccion.
     */
    public static Garden importArchivo (File archivo) throws GardenException{
        throw new GardenException("Opcion import en construccion. Archivo: "+archivo.getName());
    }

    /**
     * exportacion de el archivo Garden
     * @param archivo, archivo en el que se va a exportar el garden
     * @throws GardenException, metodo actualmente en construccion.
     */
    public void exportArchivo (File archivo) throws GardenException{
        throw new GardenException("Opcion export en construccion. Archivo: "+archivo.getName());
    }
    
    /**
     * apertura de un nuevo archivo Garden
     * @param archivo, archivo que contiene todo el garden que se desea abrir
     * @throws GardenException, metodo actualmente en construccion.
     */
    public static Garden open00Archivo(File archivo) throws GardenException{
        try{
            ObjectInputStream open = new ObjectInputStream(new FileInputStream(archivo));
            Garden g = (Garden) open.readObject();
            open.close();
            return g;
        } catch(Exception e){
            throw new GardenException("Opcion save no se guardo. Archivo: "+archivo.getName());
        }
    }
    
    /**
     * salvar el actual archivo Garden
     * @param archivo, archivo que va a contener todo el garden actual
     * @throws gardenException, metodo actualmente en construccion.
     */
    public void save00Archivo (File archivo) throws GardenException{
        try{
            ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(archivo));
            save.writeObject(this);
            save.close();
        } catch(Exception e){
            throw new GardenException("Opcion save no se guardo. Archivo: "+archivo.getName());
        }
    }
    
    /**
     * importacion de un archivo Garden
     * @param archivo, archivo en el que se va a importar el garden
     * @throws GardenException, metodo actualmente en construccion.
     */
    public static Garden import00Archivo (File archivo) throws GardenException{
        try{
            BufferedReader imp = new BufferedReader(new FileReader(archivo));
            String caracteristics = imp.readLine();
            Garden g = new Garden();
            g.MakeNullGarden();
            while(caracteristics != null){
                caracteristics.trim();
                String[] pos = caracteristics.split(" ");
                int row = Integer.parseInt(pos[1]);
                int column = Integer.parseInt(pos[1]);
                g.createThings(pos[0],row,column);
                caracteristics = imp.readLine();
            }
            imp.close();
            return g;
        } catch(Exception e){
            throw new GardenException("Opcion save no se guardo. Archivo: "+archivo.getName());
        }
    }

    /**
     * exportacion de el archivo Garden
     * @param archivo, archivo en el que se va a exportar el garden
     * @throws GardenException, metodo actualmente en construccion.
     */
    public void export00Archivo (File archivo) throws GardenException{
        try{
            PrintWriter exp = new PrintWriter(new FileOutputStream(archivo));
            for(int i = 0; i < garden.length; i++){
                for(int j = 0; j < garden.length; j++){
                    Thing thing = getThing(i, j);
                    if (thing != null) {
                        // Escribe la información de la entidad en una línea del archivo
                        exp.write(thing.getClass().getSimpleName() + " " + i + " " + j + "\n");
                    }
                }
            }
            exp.close();
        } catch(Exception e){
            throw new GardenException("Opcion save no se guardo. Archivo: "+archivo.getName());
        }
    }
    
    /**
     * apertura de un nuevo archivo Garden
     * @param archivo, archivo que contiene todo el garden que se desea abrir
     * @throws GardenException
     */
    public static Garden open01Archivo(File archivo) throws GardenException{
        if (archivo ==null){
            throw new GardenException(GardenException.ARCHIVE_NOT_NULL);
        }
        try{
            ObjectInputStream open = new ObjectInputStream(new FileInputStream(archivo));
            Garden g = (Garden) open.readObject();
            open.close();
            return g;
        } catch(FileNotFoundException e){
            throw new GardenException("Archivo no encontrado: " + archivo.getName());
        } catch(IOException e1){
            throw new GardenException("Error al abrir el archivo: " + archivo.getName());
        } catch(Exception e2){
            throw new GardenException("Opcion open no se abrio. Archivo: "+archivo.getName());
        }
    }
    
    /**
     * salvar el actual archivo Garden 
     * @param archivo, archivo que va a contener todo el garden actual
     * @throws gardenException
     */
    public void save01Archivo (File archivo) throws GardenException{
        if (archivo ==null){
            throw new GardenException(GardenException.ARCHIVE_NOT_NULL);
        }
        try{
            ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(archivo));
            save.writeObject(this);
            save.close();
        } catch(FileNotFoundException e){
            throw new GardenException("Archivo no guardado: " + archivo.getName());
        } catch(IOException e1){
            throw new GardenException("Error al salvar el archivo: " + archivo.getName());
        } catch(Exception e2){
            throw new GardenException("Opcion save no permite guardar. Archivo: "+archivo.getName());
        }
    }
    
    /**
     * importacion de un archivo Garden
     * @param archivo, archivo en el que se va a importar el garden
     * @throws GardenException, metodo actualmente en construccion.
     */
    public static Garden import01Archivo (File archivo) throws GardenException{
        if (archivo == null){
            throw new GardenException(GardenException.ARCHIVE_NOT_NULL);
        }
        try{
            BufferedReader imp = new BufferedReader(new FileReader(archivo));
            String caracteristics = imp.readLine();
            Garden g = new Garden();
            g.MakeNullGarden();
            while(caracteristics != null){
                caracteristics = caracteristics.trim();
                String[] pos = caracteristics.split(" ");
                if (pos.length != 3){ 
                    imp.close();
                    throw new GardenException(GardenException.ARCHIVE_NOT_CORRECT);
                }
                g.createThings(pos[0], Integer.parseInt(pos[1]), Integer.parseInt(pos[2]));
                caracteristics = imp.readLine();
            }
            imp.close();
            return g;
        }catch(GardenException e1){            
            throw new GardenException(e1.getMessage());
        }catch(Exception e1){
            throw new GardenException("Opcion save no se importo. Archivo: "+ archivo.getName());
        }
    }

    /**
     * exportacion de el archivo Garden
     * @param archivo, archivo en el que se va a exportar el garden
     * @throws GardenException, metodo actualmente en construccion.
     */
    public void export01Archivo (File archivo) throws GardenException{
        if (archivo == null){
            throw new GardenException(GardenException.ARCHIVE_NOT_NULL);
        }
        try{
            PrintWriter exp = new PrintWriter(new FileOutputStream(archivo));
            for(int i = 0; i < garden.length; i++){
                for(int j = 0; j < garden.length; j++){
                    Thing thing = getThing(i, j);
                    if (thing != null) {
                        // Escribe la información de la entidad en una línea del archivo
                        exp.write(thing.getClass().getSimpleName() + " " + i + " " + j + "\n");
                    }
                }
            }
            exp.close();
        } catch(Exception e){
            throw new GardenException("Opcion save no se exporto correctamente. Archivo: "+archivo.getName());
        }
    }
    
        /**
     * importacion de un archivo Garden
     * @param archivo, archivo en el que se va a importar el garden
     * @throws GardenException, metodo actualmente en construccion.
     */
    public static Garden import02Archivo (File archivo) throws GardenException{
        if (archivo == null){
            throw new GardenException(GardenException.ARCHIVE_NOT_NULL);
        }
        int lineNumber = 0;
        try{
            BufferedReader imp = new BufferedReader(new FileReader(archivo));
            String caracteristics = imp.readLine();
            Garden g = new Garden();
            g.MakeNullGarden();
            lineNumber += 1;
            while(caracteristics != null){
                caracteristics = caracteristics.trim();
                String[] pos = caracteristics.split(" ");
                int row;
                int column;
                if (pos.length == 3){
                    try{
                        row = Integer.parseInt(pos[1]);
                    }catch(NumberFormatException e4){
                        throw new GardenException("Error in line "+ lineNumber+ ": This is not a number " + pos[1]);
                    }
                    try{
                        column = Integer.parseInt(pos[2]);
                    }catch(NumberFormatException e5){
                        throw new GardenException("Error in line "+ lineNumber+ ": This is not a number " + pos[2]);
                    }
                    try{
                        g.createThings(pos[0], row, column);
                        caracteristics = imp.readLine();
                    }catch(IllegalArgumentException e6){
                        throw new GardenException("Error in line "+ lineNumber+ ": " + e6.getMessage());
                    }catch(GardenException e3){
                        throw new GardenException("Error in line "+ lineNumber+ ": " + e3.getMessage());
                    }
                }else{
                    imp.close();
                    throw new GardenException("Error in line "+ lineNumber+ ": " + GardenException.ARCHIVE_NOT_CORRECT);
                }
                lineNumber += 1;
            }
            imp.close();
            return g;
        }catch(GardenException e1){
            throw new GardenException(e1.getMessage());
        }catch(Exception e1){
            throw new GardenException("Opcion save no se importo. Archivo: "+ archivo.getName());
        }
    }
}
