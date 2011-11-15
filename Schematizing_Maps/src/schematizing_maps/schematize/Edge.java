/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schematizing_maps.schematize;

/**
 *
 * @author Mert Terzihan
 */
public class Edge {
    private Point p1;
    private Point p2;
    private double distance;
    
    Edge(Point point1, Point point2){
        p1 = point1;
        p2 = point2;
        distance = calculateDistance();
    }
    
    private double calculateDistance(){
        return Math.sqrt(Math.pow((p1.getX()-p2.getX()), 2) + Math.pow((p1.getY()-p2.getY()), 2));
    }
    
    Point getP1(){
        return p1;
    }
    void setP1(Point point){
        p1 = point;
        distance = calculateDistance();
    }
    
    Point getP2(){
        return p2;
    }
    void setP2(Point point){
        p2 = point;
        distance = calculateDistance();
    }
    
    double getDistance(){
        return distance;
    }
}
