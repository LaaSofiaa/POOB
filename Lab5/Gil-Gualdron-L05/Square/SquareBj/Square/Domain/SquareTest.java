package Domain;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The test class SquareTest.
 *
 * @author  Santiago Gualdron-Sofia Gil
 * @version 1.0
 */
public class SquareTest
{
    @Test
    public void shouldConstructor() throws SquareException {
        Square square = new Square(5, 8);
        assertNotNull(square);
    }
    
    @Test
    public void shouldMoves() throws SquareException {
        Square square = new Square(3, 2);
        square.move('n');
        square.move('e');
        assertEquals(2, square.moves());
    }
    
    @Test
    public void shouldMove() throws SquareException {
        Square square = new Square(4, 3);
        char [][] w= square.board();
        HashMap<Character,int[]> resp = new HashMap<>();
        for (int i = 0; i < w.length;i++){
            for(int j = 0; j < w.length;j++){
                Character c = w[i][j];
                if(c != '\u0000' ){
                    resp.put(c,new int[] {i,j});
                }
            }
        }
        square.move('n');
        for (int i = 0; i < w.length;i++){
            for(int j = 0; j < w.length;j++){
                Character c = w[i][j];
                if(c != '\u0000' ){
                    int[] r= resp.get(c);
                    int q=r[0]-1;
                    if(q==-1){
                        q=0;
                    }else if(w[q][r[1]]!= '\u0000' && w[q][r[1]]!='R' && w[q][r[1]]!='G' && w[q][r[1]]!='B'&& w[q][r[1]]!= c){
                        q=r[0];
                    }
                    if(c=='r' || c=='g'|| c=='b'){
                        assertEquals(i,q);
                        assertEquals(j,r[1]);
                    }
                }
            }
        }
    }
    
    
    @Test
    public void shouldBoard() throws SquareException {
        Square square = new Square(4, 3);
        char[][] tablero = new char[][] {
            {'\u0000', '\u0000', 'R', '\u0000'},
            {'\u0000', '\u0000', '\u0000', 'G'},
            {'\u0000', 'B', '\u0000', '\u0000'},
            {'\u0000', '\u0000', '\u0000', '\u0000'}
        };
        square.tablero = tablero;
        char[][] tablerito = square.board();
        assertArrayEquals(tablero, tablerito);
    }
    }
    


