import java.awt.*;
import java.awt.geom.*;


/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle{

    public static final double PI=3.1416;
    
    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    public boolean isVisible;
    

    public Circle(){
        diameter = 30;
        xPosition = 20;
        yPosition = 15;
        color = "blue";
        isVisible = false;
    }
       
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    

    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }

    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * Move the circle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the circle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the circle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the circle vertically
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }

    /***
     * Calcular el área del circulo
     */
    public double areaCircle(){
        double radio = diameter/2.0;
        double area = PI* radio*radio ;
        return area; 
    }
    
    /***
     * Duplica el área del circulo
     */
    public void duplicateCircle(){
        double area = areaCircle();
        double diametro= 2 * Math.sqrt(area/PI);
        changeSize((int)diametro); 
    }
    
    /**
     * genera "times" cantidad de saltos con una altura random "height"
     * @param times indica la cantidad de saltos que hace el circulo
     * @param height indica el rango maximo de la altura aleatoria que saltara el circulo
     */
    public void bounce (int times, int height){
        if (height < 0 ){
            height = height * -1;
        }
        for (int i = 1; i <= times; i++){
            int salto = (int) (Math.random() * height) + 1;
            slowMoveVertical(salto);
            slowMoveVertical(-salto);
        }
    }
    
    /**
     * Crea 3 nuevos circulos, cada uno a una distancia "newHeight", formando una figura geometrica parecida
     * a un cuadrado, donde cada arista es 1 circulo.
     * @param newHeight es la distancia que hay entre cada circulo.
     */
    public void fourCircle (int newHeight){
        Circle circleOne = new Circle();
        Circle circleTwo = new Circle();
        Circle circleThree = new Circle();
        circleOne.xPosition = this.xPosition + newHeight;
        circleTwo.yPosition = this.yPosition + newHeight;
        circleThree.xPosition = this.xPosition + newHeight;
        circleThree.yPosition = this.yPosition + newHeight;
        circleOne.makeVisible();
        circleTwo.makeVisible();
        circleThree.makeVisible();
    }
    
    /**
     * Crea un nuevo objeto Circle de color negro con la posición especificada y propiedades predeterminadas.
     * @param xPosition La coordenada x del centro del Circle.
     * @param yPosition La coordenada y del centro del Circle.
     * @return Un nuevo objeto Circle que representa un círculo negro con la posición especificada.
     */
    public static Circle blackCircle (int xPosition, int yPosition) {
        Circle newCircle = new Circle();
        newCircle.xPosition = xPosition;
        newCircle.yPosition = yPosition;
        newCircle.diameter = 50;
        newCircle.color = "black";
        newCircle.isVisible = false;
        return newCircle;
    }
}


