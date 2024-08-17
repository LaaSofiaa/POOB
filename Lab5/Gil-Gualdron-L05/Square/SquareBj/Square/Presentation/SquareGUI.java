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
 * La clase SquareGUI representa la interfaz gráfica de usuario para el juego Square.
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
    private int filas; //filas de la matriz
    private int columnas; //columna de la matriz
    private Square squareDom; //dominio del juego Square
    private HashMap<Character, String> colores; //hashmap con los colores de las fichas
    private HashMap<Character, Color> colorCasillas; //dice que color es de cada uno de los characters
    private int turned;
    
    /**
     * Constructor privado de la clase SquareGUI.
     * Se utiliza para inicializar la interfaz gráfica del juego.
     */
    private SquareGUI(){
        try{
            squareDom = new Square(4, 3);
        } catch(Exception ignore){}
        filas = 4;
        columnas = 4;
        colores = new HashMap<>();
        colores.put('r', "Red");
        colores.put('g', "Green");
        colores.put('b', "Blue");
        colorCasillas = new HashMap<>();
        colorCasillas.put('R', Color.RED);
        colorCasillas.put('G', Color.GREEN);
        colorCasillas.put('B', Color.BLUE);
        colorCasillas.put('r', Color.RED);
        colorCasillas.put('g', Color.GREEN);
        colorCasillas.put('b', Color.BLUE);
        colorCasillas.put('d', Color.RED);
        colorCasillas.put('e', Color.GREEN);
        colorCasillas.put('f', Color.BLUE);
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
        JMenu fileOptions = new JMenu("Options");
        menu.add(fileMenu);
        menu.add(fileOptions);
        JMenuItem newMenuItem = new JMenuItem("Nuevo");
        JMenuItem openMenuItem = new JMenuItem("Abrir");
        JMenuItem saveMenuItem = new JMenuItem("Guardar");
        JMenuItem reiniciarMenuItem = new JMenuItem("Reiniciar");
        JMenuItem exitMenuItem = new JMenuItem("Salir");
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(reiniciarMenuItem);
        fileMenu.add(exitMenuItem);
        JMenuItem numberOfMoves = new JMenuItem("Movimientos actuales");
        JMenuItem winRate = new JMenuItem("Porcentade victoria");
        JMenuItem boardDimensionsItem = new JMenuItem("Dimensiones");
        JMenuItem sheetColorItem = new JMenuItem("Color de las fichas");
        fileOptions.add(numberOfMoves);
        fileOptions.add(winRate);
        fileOptions.add(sheetColorItem);
        fileOptions.add(boardDimensionsItem);
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
        mainPanel.add(new Button("west"),BorderLayout.EAST);
        mainPanel.add(new Button("east"),BorderLayout.WEST);
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
                Character tipoSquare = squareDom.getSquarePosition(i,j); 
                JButton botonSquare = new JButton();
                botonSquare.setPreferredSize(new Dimension(30,20));
                Color colorBack;
                Color colorBorder;
                if (tipoSquare == 'R' || tipoSquare == 'G' ||tipoSquare == 'B'){
                    botonSquare.setBackground(blanco);
                    colorBorder = colorCasillas.get(tipoSquare);
                    botonSquare.setBorder(BorderFactory.createLineBorder(colorBorder,10));
                }else if (tipoSquare == 'r' || tipoSquare == 'g' ||tipoSquare == 'b'){
                    colorBack = colorCasillas.get(tipoSquare);
                    botonSquare.setBackground(colorBack);
                    botonSquare.setBorder(BorderFactory.createLineBorder(blanco,10));
                } else if(tipoSquare == 'd' || tipoSquare == 'e' ||tipoSquare == 'f'){
                    colorBack = colorCasillas.get(tipoSquare);
                    botonSquare.setBackground(colorBack);
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
        prepareActionsOptions();
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
    }
    
    /**
     * Prepara y configura las acciones para los elementos del Options de la interfaz gráfica.
     */
    private void prepareActionsOptions(){
        // Cantidad de movimientos
        JMenuItem numberOfMoves = getJMenuBar().getMenu(1).getItem(0);
        numberOfMoves.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int m = squareDom.moves();
                cantMoves(m);
            }
        });
        
        // Porcentaje de ganar
        JMenuItem winRate = getJMenuBar().getMenu(1).getItem(1);
        winRate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int m = squareDom.percentageOK();
                winProbability(m);
            }
        });
        
        // Color de las fichas
        JMenuItem cellColorItem = getJMenuBar().getMenu(1).getItem(2);
        cellColorItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cellColor();
            }
        });
        
        //Cambiar tamaño
        JMenuItem tamanoItem = getJMenuBar().getMenu(1).getItem(3);
        tamanoItem.addActionListener(new ActionListener(){
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
                try{
                    Boolean confirm = squareDom.move('n');
                    confirmWin(confirm);
                } catch(Exception ignore){}
            }
            
        });
        //South
        Button southButton = (Button) mainPanel.getComponent(1);
        southButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                try{
                    Boolean confirm = squareDom.move('s');
                    confirmWin(confirm);
                } catch(Exception ignore){}
            }
            
        });
        //East
        Button eastButton = (Button) mainPanel.getComponent(2);
        eastButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                try{
                    Boolean confirm = squareDom.move('e');
                    confirmWin(confirm);
                } catch(Exception ignore){}
            }
            
        });
        //West
        Button westButton = (Button) mainPanel.getComponent(3);
        westButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                try{
                    Boolean confirm = squareDom.move('o');
                    confirmWin(confirm);
                } catch(Exception ignore){}
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
        try{
            squareDom = new Square(4, 3);
        } catch(Exception ignore){}
        prepareElements();
        prepareActions();
        // Validar y repintar
        validate();
        repaint();
        JOptionPane.showMessageDialog(this, "Tablero reiniciado exitosamente.");
    }
    
    /**
     * revisa que el jugador halla ganado, perdido o siga jugando.
     * @param c el booleano que confirma la desicion del jugador
     */
    private void confirmWin(Boolean c){
        refresh();  
        if (c == null){
            return ;
        }else if(c == false){
            JOptionPane.showMessageDialog(this, "Has Perdido...");
        }else if (c == true){
            JOptionPane.showMessageDialog(this, "Has Ganado!");
        }
        int r = JOptionPane.showConfirmDialog(null, "Deseas volver a jugar?","REINTENTAR?", JOptionPane.YES_NO_OPTION);
        if (r != 0){
            System.exit(0);
        }else{
            resetBoard();
        }
    }
    
    /**
     * Abre un cuadro de diálogo para seleccionar un nuevo color para las fichas y establece ese color como borde de los botones del tablero.
     */
    private void cellColor(){
        String select = JOptionPane.showInputDialog(this, "seleccione el color de alguna ficha actual para cambiar");
        Character comp = confirmColor(select);
        if (comp == null){
            JOptionPane.showMessageDialog(this, "El color seleccionado no coincide con ninguna ficha", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            Color newColor = JColorChooser.showDialog(this, "Seleccione un color para las fichas", Color.WHITE);
            if(newColor != null){
                Component[] components = tableroPanel.getComponents();
                for (int i = 0; i < components.length; i++) {
                    if (components[i] instanceof JButton) {
                        JButton buttonSquare = (JButton) components[i];
                        //Calcular la posicion de donde esta el tablero
                        int fila = i/filas;
                        int columna = i % columnas;
                        Character tipoSquare= squareDom.getSquarePosition(fila,columna);
                        if (tipoSquare == Character.toUpperCase(comp)){
                            buttonSquare.setBackground(Color.WHITE);
                            buttonSquare.setBorder(BorderFactory.createLineBorder(newColor,10));
                            colorCasillas.put(tipoSquare, newColor);
                        }
                        if (tipoSquare == comp){
                            buttonSquare.setBackground(newColor);
                            buttonSquare.setBorder(BorderFactory.createLineBorder(Color.WHITE,10));
                            colorCasillas.put(tipoSquare, newColor);
                        }
                    }
                }
            }
        }
    }
    
    private Character confirmColor(String c){
        for (Character clave : colores.keySet()){
            String valor = colores.get(clave);
            if (valor.equals(c)){
                return clave;
            }
        }
        return null;
    }
       
    /**
     * Abre un cuadro de diálogo para cambiar el tamaño del tablero.
     */
    private void changeSize(){
        String rows = JOptionPane.showInputDialog(this, "Numero de filas y columnas:");
        try{
            int newRows = Integer.parseInt(rows);
            if (newRows <= 4) {
                JOptionPane.showMessageDialog(this, "Ingrese valores válidos para filas y columnas", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            squareDom.changeSize(newRows);
            filas = newRows;
            columnas = newRows;
            refresh();
            JOptionPane.showMessageDialog(this, "Tamaño del tablero actualizado", "Exito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos para filas y columnas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
       
    /**
     *  muestra un mensaje indicando el porcentaje actual de ganancia que lleva el jugador
     */
    private void winProbability(int m){
        String m2 = String.valueOf(m);
        JOptionPane.showMessageDialog(this, "tienes "+m+"% de la victoria");
    }
       
    /**
     *  muestra un mensaje indicando la cantidad de movimientos que lleva el jugador
     */
    private void cantMoves(int m){
        String m2 = String.valueOf(m);
        JOptionPane.showMessageDialog(this, "llevas "+m+" movimientos hasta el momento");
    }
}