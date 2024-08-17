package Presentation;
import Domain.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.event.*;

import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Toolkit;

import java.util.*;
import java.io.File;

/**
 ** La clase SquareGUI representa la interfaz gráfica de usuario para el juego Square.
 * Permite al usuario interactuar con el juego a través de elementos visuales como botones y menús.
 * También gestiona las acciones del usuario y la actualización de la interfaz según el estado del juego.
 *
 * @author Sofia Gil - Santiago Gualdron
 * @version 1.0
 */
public class SquareGUI extends JFrame{
    private static final int width = 700; //ancho de la vista
    private static final int height = 700; //largo de la vista
    private static final Dimension preferredDimention = new Dimension(width, height); //Dimension preestablecida de la vista
    private JPanel mainPanel; //matriz principal del juego
    private JPanel tableroPanel; //matriz de los botones del juego
    private int filas = 4; //filas de la matriz
    private int columnas = 4; //columna de la matriz
    private Square squareDom; //dominio del juego Square
    private int turned;
    
    /**
     * Constructor privado de la clase SquareGUI.
     * Se utiliza para inicializar la interfaz gráfica del juego.
     */
    private SquareGUI(){
        squareDom = new Square(4, 3);
        prepareElements();
        prepareActions();
    }

    /**
     * Método principal que inicia la aplicación creando una instancia de SquareGUI y haciéndola visible.
     * 
     * @param args Argumentos de la línea de comandos (no se utilizan en este caso)
     */
    public static void main(String[] args){
        SquareGUI gui = new SquareGUI();
        gui.setVisible(true);
    }
    
    /**
     * Prepara y configura los elementos visuales de la interfaz gráfica.
     */
    private void prepareElements(){
        setTitle("Square");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height / 2;
        setSize(width, height);
        setLocationRelativeTo(null);
        prepareElementsMenu();
        prepareElementsBoard();
        getContentPane().add(mainPanel);
    }
    
    /**
     * Prepara y configura los elementos del menú de la interfaz gráfica.
     */
    private void prepareElementsMenu(){
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);
        JMenu fileMenu = new JMenu("Menu");
        menu.add(fileMenu);
        JMenuItem newMenuItem = new JMenuItem("Nuevo");
        JMenuItem openMenuItem = new JMenuItem("Abrir");
        JMenuItem saveMenuItem = new JMenuItem("Guardar");
        JMenuItem reiniciarMenuItem = new JMenuItem("Reiniciar");
        JMenuItem exitMenuItem = new JMenuItem("Salir");
        JSeparator separator= new JSeparator();
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(reiniciarMenuItem);
        fileMenu.add(exitMenuItem);
        fileMenu.add(separator);
        JMenuItem boardDimensionsItem = new JMenuItem("Dimensiones");
        JMenuItem sheetColorItem = new JMenuItem("Color de las fichas");
        fileMenu.add(boardDimensionsItem);
        fileMenu.add(sheetColorItem);
        
    }
    
    /**
     * Prepara y configura los elementos relacionados con el tablero de juego.
     * Esto incluye la creación de botones para cada celda del tablero.
     */
    private void prepareElementsBoard() {
        mainPanel = new JPanel(new BorderLayout());
        tableroPanel = createTableroPanel();
        mainPanel.add(new Button("North"),BorderLayout.NORTH);
        mainPanel.add(new Button("South"),BorderLayout.SOUTH);
        mainPanel.add(new Button("east"),BorderLayout.EAST);
        mainPanel.add(new Button("west"),BorderLayout.WEST);
        mainPanel.add(tableroPanel,BorderLayout.CENTER);
        }
    
    /**
     * Crea el panel que contiene los botones del tablero y los configura según el estado del juego.
     * 
     * @return El panel del tablero con los botones configurados
     */
    private JPanel createTableroPanel(){
        Color blanco = new Color(255, 255, 255);
        JPanel tableroPanel = new JPanel(new GridLayout(filas,columnas, 3, 3));
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                char tipoSquare = squareDom.getSquarePosition(i,j); 
                JButton botonSquare = new JButton();
                botonSquare.setPreferredSize(new Dimension(30,20));
                Color colorBack;
                Color colorBorder;
                if (tipoSquare == 'R' || tipoSquare == 'G' ||tipoSquare == 'B'){
                    botonSquare.setBackground(blanco);
                    colorBorder = jewelTypeColor(tipoSquare);
                    botonSquare.setBorder(BorderFactory.createLineBorder(colorBorder,10));
                }else if (tipoSquare == 'r' || tipoSquare == 'g' ||tipoSquare == 'b'){
                    colorBack = jewelTypeColor(tipoSquare);
                    botonSquare.setBackground(colorBack);
                    botonSquare.setBorder(BorderFactory.createLineBorder(blanco,10));
                }else{
                    botonSquare.setBackground(blanco);
                    botonSquare.setBorder(BorderFactory.createLineBorder(blanco,10));
                }
                tableroPanel.add(botonSquare);
            }
        }
        tableroPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 5));
        return tableroPanel;
    }
    
    private Color jewelTypeColor(char type) {
        switch (type) {
            case 'R':
                return Color.RED;
            case 'G':
                return Color.GREEN;
            case 'B':
                return Color.BLUE;
            case 'r':
                return Color.RED;
            case 'g':
                return Color.GREEN;
            default:
                return Color.BLUE;
        }
    }
        
    /**
     * Prepara y configura las acciones de los elementos de la interfaz gráfica.
     * Esto incluye acciones para los elementos del menú y los botones del tablero.
     */
    private void prepareActions(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent event){
                exitOptions();
            }
  
        });
        
        prepareActionsMenu();
        prepareActionsCardinal();
    }
    
    /**
     * Prepara y configura las acciones para los elementos del menú de la interfaz gráfica.
     */
    private void prepareActionsMenu(){
        //Abrir 
        JMenuItem loadMenuItem = getJMenuBar().getMenu(0).getItem(1);
        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        
        // Guardar
        JMenuItem saveMenuItem = getJMenuBar().getMenu(0).getItem(2);
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });
        
        // Reiniciar
        JMenuItem restartItem = getJMenuBar().getMenu(0).getItem(3);
        restartItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });
        
        //salir menu
        JMenuItem exitMenuItem = getJMenuBar().getMenu(0).getItem(4);
        exitMenuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                exitOptions();
            }
  
        });
        
        // Color de las fichas
        JMenuItem cellColorItem = getJMenuBar().getMenu(0).getItem(7);
        cellColorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cellColor();
            }
        });
        
        //Cambiar tamaño
        JMenuItem tamanoItem = getJMenuBar().getMenu(0).getItem(6);
        tamanoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSize();
            }
        });
    }
    
    /**
     * Prepara y configura las acciones para los botones direccionales del tablero.
     */
    private void prepareActionsCardinal(){
        //North
        Button northButton = (Button) mainPanel.getComponent(0);
        northButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                squareDom.move('n');
                refresh();
            }
  
        });
        //South
        Button southButton = (Button) mainPanel.getComponent(1);
        southButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                squareDom.move('s');
                refresh();
            }
            
        });
        //East
        Button eastButton = (Button) mainPanel.getComponent(2);
        eastButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                squareDom.move('e');
                refresh();
            }
            
        });
        //West
        Button westButton = (Button) mainPanel.getComponent(3);
        westButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                squareDom.move('o');
                refresh();
            }
            
        });
    }
    
    /**
     * Muestra un cuadro de diálogo para confirmar la salida de la aplicación.
     * Cierra la aplicación si el usuario confirma la salida.
     */
    private void exitOptions(){
        int j = JOptionPane.showConfirmDialog(this,"Desea salir de la aplicacion", "Confirmar salida",JOptionPane.YES_NO_OPTION);
        if(j==0){
            System.exit(0);
        }
    }
    
    /**
     * Abre un cuadro de diálogo para seleccionar un archivo y muestra un mensaje
     * indicando que la funcionalidad está en construcción.
     */
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "En construcción. Archivo abierto: " + selectedFile.getName(), "Abrir", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Actualiza la interfaz gráfica del tablero después de cada movimiento.
     */
    private void refresh(){
        mainPanel.remove(tableroPanel);
        tableroPanel = createTableroPanel();
        mainPanel.add(tableroPanel,BorderLayout.CENTER);
        validate();
        repaint();
    }
    
    /**
     * Abre un cuadro de diálogo para seleccionar la ubicación de guardado y muestra un mensaje
     * indicando que la funcionalidad está en construcción.
     */
    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "En construcción. Archivo guardado:" + selectedFile.getName(), "Guardar", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Reinicia el tablero del juego y muestra un mensaje indicando que la operación fue exitosa.
     */
    private void resetBoard() {
        getContentPane().remove(mainPanel);
        turned = 0;
        squareDom = new Square(4, 3);
        prepareElements();
        prepareActions();
        // Validar y repintar
        validate();
        repaint();
        JOptionPane.showMessageDialog(this, "Tablero reiniciado exitosamente.");
    }
    
    /**
     * Abre un cuadro de diálogo para seleccionar un nuevo color para las fichas y establece ese color como borde de los botones del tablero.
     */
    private void cellColor(){
        Color newColor = JColorChooser.showDialog(this, "Seleccione un color para las fichas", Color.WHITE);
        if(newColor != null){
            Component[] components = tableroPanel.getComponents();
            for (int i = 0; i < components.length; i++) {
                if (components[i] instanceof JButton) {
                    JButton buttonSquare = (JButton) components[i];
                    //Calcular la posicion de donde esta el tablero
                    int fila = i/filas;
                    int columna = i % columnas;
                    char tipoSquare= squareDom.getSquarePosition(fila,columna);
                    if (tipoSquare != '\u0000' && (tipoSquare == 'r' || tipoSquare == 'g' || tipoSquare == 'b')) { // Verifica que la casilla no esté vacía
                    ArrayList<int[]> fichas = squareDom.getFichas();
                    ArrayList<int[]> huecos = squareDom.getHuecos();

                    // Verifica si la casilla es una ficha 
                    for (int[] ficha : fichas) {
                        if (ficha[1] == fila && ficha[2] == columna && squareDom.cambio(ficha[0]) == tipoSquare) {
                            buttonSquare.setBorder(BorderFactory.createLineBorder(newColor, 10));
                            buttonSquare.setBackground(newColor);
                        }
                    }

                    // Verifica si la casilla es un hueco
                    for (int[] hueco : huecos) {
                        if (hueco[1] == fila && hueco[2] == columna && squareDom.cambio(hueco[0]) == tipoSquare ) {
                            buttonSquare.setBorder(BorderFactory.createLineBorder(newColor, 10));
                            buttonSquare.setBackground(newColor);
                        }
                        }
                    }
                }
                    //buttonSquare.setBorder(BorderFactory.createLineBorder(newColor, 10));
                    //buttonSquare.setBackground(newColor);
                }
            }
            }
       
    /**
     * Abre un cuadro de diálogo para cambiar el tamaño del tablero.
     */
    private void changeSize(){
        
    }
}

