

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class GameTest.
 *
 * @author  Santiago Gualdron- Sofia Gil
 * @version 1.0
 */
public class GameTest{
    
    @Test
    public void testEstadoInicial(){
        Game game= new Game();
        game.estadoInicial();
    }
    
    @Test
    public void testSubirPeronaje(){
        Game game= new Game();
        game.estadoInicial();
        game.subirPersonaje("missionary1");
        game.subirPersonaje("cannibal1");
    }
    
    @Test
    public void testmoverBote(){
        Game game= new Game();
        game.estadoInicial();
        game.subirPersonaje("missionary1");
        game.subirPersonaje("cannibal1");
        game.moverBote();
    }
    
    @Test
    public void testBajarPersonaje(){
        Game game= new Game();
        game.estadoInicial();
        game.subirPersonaje("missionary1");
        game.subirPersonaje("cannibal1");
        game.moverBote();
        game.bajarPersonaje("missionary1");
        game.bajarPersonaje("cannibal1");
        
    }
    
    @Test
    public void testPerder(){
        Game game= new Game();
        game.estadoInicial();
        game.subirPersonaje("missionary1");
        game.moverBote();
    }
    
    @Test
    public void testGanar(){
        Game game= new Game();
        game.estadoInicial();
        game.subirPersonaje("cannibal1");
        game.subirPersonaje("cannibal2");
        game.moverBote();
        game.bajarPersonaje("cannibal1");
        game.moverBote();
        game.subirPersonaje("cannibal3");
        game.moverBote();
        game.bajarPersonaje("cannibal2");
        game.moverBote();
        game.bajarPersonaje("cannibal3");
        game.subirPersonaje("missionary1");
        game.subirPersonaje("missionary2");
        game.moverBote();
        game.bajarPersonaje("missionary2");
        game.subirPersonaje("cannibal1");
        game.moverBote();
        game.bajarPersonaje("cannibal1");
        game.subirPersonaje("missionary3");
        game.moverBote();
        game.bajarPersonaje("missionary1");
        game.bajarPersonaje("missionary3");
        game.subirPersonaje("cannibal2");
        game.moverBote();
        game.subirPersonaje("cannibal1");
        game.moverBote();
        game.bajarPersonaje("cannibal1");
        game.moverBote();
        game.subirPersonaje("cannibal3");
        game.moverBote();
        game.bajarPersonaje("cannibal2");
        game.bajarPersonaje("cannibal3");
        
    }
    
}