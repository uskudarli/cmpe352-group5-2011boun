/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schematizing_maps.schematize;

/**
 *
 * @author Mert Terzihan
 */
public class Point {
    private double x;
    private double y;
    private String description;
    
    Point(double xCoor, double yCoor, String desc){
        x = xCoor;
        y = yCoor;
        description = desc;
    }
    
    void setX(double xCoor){
        x = xCoor;
    }
    double getX(){
        return x;
    }
    
    void setY(double yCoor){
        y = yCoor;
    }
    double getY(){
        return y;
    }
    
    void setDescription(String desc){
        description = desc;
    }
    String getDescription(){
        return description;
    }
}
