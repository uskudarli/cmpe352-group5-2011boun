/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schematizing_maps.schematize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

/**
 *
 * @author Mert Terzihan
 */
public class Map {
    Vector<Point> points;
    Vector<Edge> edges;
    String keywords;
    int userID;
    private Properties configuration;
    private String preservationCriteria;
    private double[] angles;
    
    Map(){
        points = null;
        edges = null;
        keywords = "";
        userID = 0;
        configuration = null;
        preservationCriteria = "";
        angles = null;
    }
    
    Map(Vector<Point> p, Vector<Edge> e, String k, int ID, Properties c){
        points = p;
        edges = e;
        keywords = k;
        userID = ID;
        configuration = c;
        preservationCriteria = "";
        angles = null;
    }
    
    Properties getConfiguration(){
        return configuration;
    }
    void setConfiguration(Properties c){
        configuration = c;
    }
    
    String getPreservationCriteria(){
        return preservationCriteria;
    }
    void setPreservationCriteria(String p){
        preservationCriteria = p;
    }
    
    double[] getAngles(){
        return angles;
    }
    void setAngles(double[] a){
        angles = a;
    }
    
    void parseConfigurationFromFile(String path) throws FileNotFoundException, IOException{
        if(path != null){
            configuration = new Properties();
            FileInputStream InputStream = new FileInputStream(path);
            configuration.load(InputStream);
            InputStream.close(); 
        }
        String a = configuration.getProperty("angles");
        String[] aString = a.split(",");
        for(int i=0; i<aString.length; i++){
            angles[i] = new Double(aString[i]);
        }
        preservationCriteria = configuration.getProperty("preservationCriteria");
    }
    
    void Schematize(){
        Iterator<Point> oldPoints = points.iterator();
        Point twoBefore = oldPoints.next();
        Point oneBefore = oldPoints.next();
        double dist1 = Math.sqrt(Math.pow((twoBefore.getX()-oneBefore.getX()), 2) + Math.pow((twoBefore.getY()-oneBefore.getY()), 2)); // distance between twoBefore and oneBefore
        double dist2 = Math.abs(twoBefore.getY() - oneBefore.getY());
        double angle = Math.asin(dist2/dist1);
        if(angle > (Math.PI/4)){
            oneBefore.setX(twoBefore.getX());
        }
        else{
            oneBefore.setY(twoBefore.getY());
        }
        while(oldPoints.hasNext()){
            Point p = oldPoints.next();
            adjustDistance(oneBefore, p, dist1);
            angle = calculateAngle(twoBefore, oneBefore, p);
            for(int i=0; i<angles.length; i++){
                if(angle < angles[i]){
                    movePoint(oneBefore, p, angle);
                }
            }
            twoBefore = oneBefore;
            oneBefore = p;
        }
    }
    
    double calculateAngle(Point p1, Point p2, Point p3){ // cosine law
        double a = Math.pow((p1.getX()-p2.getX()), 2) + Math.pow((p1.getY()-p2.getY()), 2);
        double b = Math.pow((p3.getX()-p2.getX()), 2) + Math.pow((p3.getY()-p2.getY()), 2);
        double c = Math.pow((p3.getX()-p1.getX()), 2) + Math.pow((p3.getY()-p1.getY()), 2);
        double angle = Math.acos((a+b-c)/(2*a*b));
        return angle;
    }
    
    void movePoint(Point oneBefore, Point p, double angle){
        
    }
    
    void adjustDistance(Point oneBefore, Point p, double distance){
        
    }
}
