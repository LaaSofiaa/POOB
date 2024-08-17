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
public class GardenGUI extends JFrame implements Serializable{  
    public static final int SIDE=21;
    public static final int SIZE=Garden.LENGTH+1;

    private JButton buttonTicTac;
    private JPanel panelControl;
    private PhotoGarden photo;
    private Garden garden;
    private JFileChooser fileChooser;
    

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
        prepareElementsMenu();
        photo.repaint();
    }
    
    /**
     * Prepara y configura los elementos del menú de la interfaz.
     * Esto incluye opciones como "Abrir", "Guardar", importar, exportar, nuevo y salir
     */
    private void prepareElementsMenu(){
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);
        JMenu fileMenu = new JMenu("Menu");
        menu.add(fileMenu);
        JMenuItem newItem = new JMenuItem("Nuevo");
        JSeparator separator1 = new JSeparator();
        JMenuItem openItem = new JMenuItem("Abrir");
        JMenuItem saveItem = new JMenuItem("Guardar como");
        JSeparator separator2 = new JSeparator();
        JMenuItem importItem = new JMenuItem("Importar");
        JMenuItem exportItem = new JMenuItem("Exportar como");
        JSeparator separator3 = new JSeparator();
        JMenuItem exitItem = new JMenuItem("Salir");
        fileMenu.add(newItem);
        fileMenu.add(separator1);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(separator2);
        fileMenu.add(importItem);
        fileMenu.add(exportItem);
        fileMenu.add(separator3);
        fileMenu.add(exitItem);
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
        prepareActionsMenu();
        }
    
    private void prepareActionsMenu(){
        fileChooser = new JFileChooser();
        //new
        JMenuItem newMenuItem = getJMenuBar().getMenu(0).getItem(0);
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionNew();
            }

        });
        //Open
        JMenuItem loadMenuItem = getJMenuBar().getMenu(0).getItem(2);
        loadMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        //save as
        JMenuItem saveMenuItem = getJMenuBar().getMenu(0).getItem(3);
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionSave();
            }
        });
        //import
        JMenuItem importItem = getJMenuBar().getMenu(0).getItem(5);
        importItem.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                importFile();
            }
        });

        //export as
        JMenuItem exportItem = getJMenuBar().getMenu(0).getItem(6);
        exportItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportFile(); 
            }
        });
        //exit
        JMenuItem exitMenuItem = getJMenuBar().getMenu(0).getItem(8);
        exitMenuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                optionExit();
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
     * Reinicia el tablero del juego y muestra un mensaje indicando que la operación fue exitosa.
     */
    private void optionNew() {
        garden = new Garden();
        prepareElements();
        prepareActions();
        // Validar y repintar
        validate();
        repaint();
        JOptionPane.showMessageDialog(this, "Tablero reiniciado exitosamente.");
    }

    /**
     * Abre un cuadro de diálogo para seleccionar un archivo y muestra un mensaje
     * indicando que la funcionalidad está en construcción.
     */
    private void openFile() {
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectFile = fileChooser.getSelectedFile();
            if(selectFile != null){
                try{
                    Garden g = garden.open01Archivo(selectFile);
                    this.garden = g;
                    repaint();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(this, "Error: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     * Abre un cuadro de diálogo para seleccionar la ubicación de guardado y muestra un mensaje
     * indicando que la funcionalidad está en construcción.
     */
    private void optionSave() {
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectFile = fileChooser.getSelectedFile();
            if(selectFile != null){
                try{
                    garden.save01Archivo(selectFile);
                }catch (Exception e){
                    JOptionPane.showMessageDialog(this, "Error: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     * metodo privado que intenta seleccionar un archivo de importacion para Garden
     * @throws por si el archivo no se puede abrir
     */
    private void importFile(){
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectFile = fileChooser.getSelectedFile();
            if(selectFile != null){
                try{
                    Garden g = garden.import01Archivo(selectFile);
                    this.garden = g;
                    repaint();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(this, "Error: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     *  metodo privado que intenta seleccionar un archivo de exportacion para Garden
     * @throws por si el archivo no se puede salvar
     */
    private void exportFile(){
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectFile = fileChooser.getSelectedFile();
            if(selectFile != null){
                try{
                    garden.export01Archivo(selectFile);
                }catch (Exception e){
                    JOptionPane.showMessageDialog(this, "Error: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    } 
    
    /**
     * Muestra un cuadro de diálogo para confirmar la salida de la aplicación.
     * Cierra la aplicación si el usuario confirma la salida.
     */
    private void optionExit(){
        System.exit(0);
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
    public static void main() throws ClassNotFoundException, IOException{
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
class PhotoGarden extends JPanel implements Serializable{
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