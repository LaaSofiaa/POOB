package test;
import domain.Tree;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.Color;

/**
 * The test class TreeTest.
 *
 * @author  Sofia Gil, Santiago Gualdron
 * @version 1.0
 */
public class TreeTest
{
     @Test
    public void shouldAct() {
        Tree tree = new Tree();
        Color initialColor = tree.getColor();
        for (int i = 0; i <5; i++){
            tree.act();
        }
        assertEquals(Color.ORANGE, tree.getColor());
        for (int i = 0; i <5; i++){
            tree.act();
        }
        assertEquals(Color.GREEN, tree.getColor());
    }
}