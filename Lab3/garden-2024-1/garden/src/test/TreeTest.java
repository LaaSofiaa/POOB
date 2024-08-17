package test;
import domain.Tree;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

/**
 * The test class TreeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TreeTest
{
     @Test
    public void shouldAct() {
        Tree tree = new Tree();
        Color initialColor = tree.getColor();
        tree.act(); 
        tree.act(); 
        tree.act(); 
        tree.act(); 
        tree.act(); 
        assertEquals(Color.ORANGE, tree.getColor());
        tree.act(); 
        tree.act(); 
        tree.act(); 
        tree.act(); 
        tree.act();
        assertEquals(Color.GREEN, tree.getColor());

    }
}
