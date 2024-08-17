package test;    

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;
import java.util.logging.*;
/**
 * Clase para el registro de excepciones en un archivo de registro.
 */
public class Log{
    public static String name="Project";
    public static void record(Exception e){
        try{
            Logger logger=Logger.getLogger(name);
            logger.setUseParentHandlers(false);
            FileHandler file=new FileHandler(name +".log",true);
            System.out.println(file);
            file.setFormatter(new SimpleFormatter());
            logger.addHandler(file);
            logger.log(Level.SEVERE,e.toString(),e);
            file.close();
            System.out.println("Se ha generado un error.");//mensaje de alerta al usario
        }catch (Exception oe){
            System.err.println("Error al registrar la excepción en el archivo de registro: " + oe.getMessage());
            //oe.printStackTrace();
            //System.exit(0);
        }
    }
}
    
