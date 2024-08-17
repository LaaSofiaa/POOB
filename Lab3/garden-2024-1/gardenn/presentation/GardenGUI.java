package presentation;
import domain.*;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Clase GardenGUI que genera todo el jardin para el usuario
 * 
 * @author Sofia Gil, Santiago Gualdron 
 * @version 1.0
 */
public class GardenGUI extends JFrame{  
    public static final int SIDE=21;
    public static final int SIZE=Garden.LENGTH+1;

    private JButton buttonTicTac;
    private JPanel panelControl;
    private PhotoGarden photo;
    private Garden garden;

    /**
     * Constructor for objects of class GardenGUI
     */
    private GardenGUI() {
        garden=new Garden();
        prepareElements();
        prepareActions();
    }
    
    /**
     * tablero para utilizar el Garden de forma visual
     */
    private void prepareElements() {
        setTitle("Garden");
        photo=new PhotoGarden(this);
        buttonTicTac=new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(photo,BorderLayout.NORTH);
        add(buttonTicTac,BorderLayout.SOUTH);
        setSize(new Dimension(SIDE*SIZE,SIDE*SIZE+50)); 
        setResizable(false);
        photo.repaint();
    }
    
    /**
     * Interaccion con el boto Tic-tac
     */
    private void prepareActions(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        buttonTicTac.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    buttonTicTacAction();
                }
            });

    }
    
    /**
     * llamado a las otras clases para el boton Tic-tac
     */
    private void buttonTicTacAction() {
        garden.ticTac();
        photo.repaint();
    }

    /**
     * @return el garden actual
     */
    public Garden getGarden(){
        return garden;
    }
    
    /**
     * funcion main que usa los otros metodos para crear Garden
     */
    public static void main() {
        GardenGUI cg=new GardenGUI();
        cg.setVisible(true);
    }  
}

/**
 * Clase PhotoGarden que genera todo el marco para GardenGUI
 * 
 * @author Sofia Gil, Santiago Gualdron 
 * @version 1.0
 */
class PhotoGarden extends JPanel{
    private GardenGUI gui;

    /**
     * Constructor for objects of class PhotoGarden
     */
    public PhotoGarden(GardenGUI gui) {
        this.gui=gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.SIDE*gui.SIZE, gui.SIDE*gui.SIZE));         
    }

    /**
     * medidas para hacer el garden
     */
    public void paintComponent(Graphics g){
        Garden garden=gui.getGarden();
        super.paintComponent(g);
        int[][] deltas={{gui.SIDE/3, 0},{0,gui.SIDE/6},{2*gui.SIDE/3,gui.SIDE/6},{0,3*gui.SIDE/6},{2*gui.SIDE/3,3*gui.SIDE/6},{gui.SIDE/3,2*gui.SIDE/3}} ;    
        for (int f=0;f<=garden.getLength();f++){
            g.drawLine(f*gui.SIDE,0,f*gui.SIDE,garden.getLength()*gui.SIDE);
        }
        for (int c=0;c<=garden.getLength();c++){
            g.drawLine(0,c*gui.SIDE,garden.getLength()*gui.SIDE,c*gui.SIDE);
        }       
        for (int f=0;f<garden.getLength();f++){
            for(int c=0;c<garden.getLength();c++){
                if (garden.getThing(f,c)!=null){
                    g.setColor(garden.getThing(f,c).getColor());
                    if (garden.getThing(f,c).shape()==Thing.FLOWER){
                        g.setColor(garden.getThing(f,c).getColor());
                        for (int i=0; i<deltas.length; i++){
                                g.drawOval(gui.SIDE*c+deltas[i][0],gui.SIDE*f+deltas[i][1],gui.SIDE/3-1,gui.SIDE/3-1);
                        }
                        g.setColor(Color.YELLOW);
                        g.drawOval(gui.SIDE*c+gui.SIDE/3,gui.SIDE*f+gui.SIDE/3,gui.SIDE/3,gui.SIDE/3);
                        if (garden.getThing(f,c).is()){
                            g.setColor(garden.getThing(f,c).getColor());
                            for (int i=0; i<deltas.length; i++){
                                g.fillOval(gui.SIDE*c+deltas[i][0],gui.SIDE*f+deltas[i][1],gui.SIDE/3-1,gui.SIDE/3-1);
                            }
                            g.setColor(Color.YELLOW);
                            g.fillOval(gui.SIDE*c+gui.SIDE/3,gui.SIDE*f+gui.SIDE/3,gui.SIDE/3,gui.SIDE/3);
                        }
                    }else if (garden.getThing(f,c).shape()==Thing.SQUARE){  
                        g.drawRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2); 
                        if (garden.getThing(f,c).is()){
                            g.fillRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2);
                        }
                    }else{
                        g.drawOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                        if (garden.getThing(f,c).is()){
                            g.fillOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                        } 
                    }
                }
            }
        }
    }
}