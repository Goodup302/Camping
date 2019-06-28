package fr.gadoy.camping.utils;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Eiwan
 */
public class IconCircle {
    
    @FXML
    private AnchorPane main;
    
    private String nom;
    private int id;
    private int x;
    private int y;
    private Color color;
    
    
    public IconCircle(int id, String nom){
        this.id = id;
        this.nom = nom;
    }
    
    public void paint(int x, int y, Color color){
        Circle circle = new Circle(x, y, 9, color);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);
        circle.setOpacity(1);
        main.getChildren().add(circle);
    }
    
    public String getNom(){
        return this.nom;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void setColor(Color c){
        this.color = c;
    }
}
