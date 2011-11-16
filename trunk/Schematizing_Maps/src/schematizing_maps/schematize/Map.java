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
    double mean_distance=0.0;
    boolean preserveEast_West;
    boolean preserveNorth_South;
    boolean preserve_distance;
    private double angleMultiple;
    
    Map()throws FileNotFoundException,IOException{
        //Default values
        
        points = null;
        edges = null;
        keywords = "";
        userID = 0;
        defaultConfiguration();
        
    }
    
    Map(Vector<Point> p, Vector<Edge> e, String k, int ID, Properties c, String ConfFile) throws FileNotFoundException,IOException{
        points = p;
        edges = e;
        keywords = k;
        userID = ID;
        parseConfigurationFromFile(ConfFile);
    }

 
    double getAngleMultiple(){
        return angleMultiple;
    }
    void setAngleMultiple(double a){
        angleMultiple = a;
    }
    
    boolean get_preserveEast_West(){
        return preserveEast_West;
    }
    boolean set_preserveEast_West(){
        return preserveEast_West;
    }
    boolean get_preserveNorth_South(){
        return preserveNorth_South;
    }
    boolean set_preserveNorth_South(){
        return preserveNorth_South;
    }
     boolean get_preserve_distance(){
        return preserve_distance;
    }
    boolean set_preserve_distance(){
        return preserve_distance;
    }
    void parseConfigurationFromFile(String path) throws FileNotFoundException, IOException{
               /*preserveEast_West=false;
        preserveNorth_South=false;
        distance=false;*/
        Properties configuration;
        if(path != null){
            configuration = new Properties();
            FileInputStream InputStream = new FileInputStream(path);
            configuration.load(InputStream);
            InputStream.close();
            angleMultiple =Double.parseDouble(configuration.getProperty("angleMultiple"));
            preserveEast_West=Boolean.parseBoolean(configuration.getProperty("preserveEast_West"));
            preserveNorth_South=Boolean.parseBoolean(configuration.getProperty("preserveNorth_South"));
            preserve_distance=Boolean.parseBoolean(configuration.getProperty("preserve_distance"));
        }
        
       
    }
    void defaultConfiguration()throws FileNotFoundException,IOException{
        preserveEast_West=false;
        preserveNorth_South=false;
        preserve_distance=false;
        angleMultiple = 22.5;
    }
    void Schematize(){
        Iterator<Point> oldPoints = points.iterator();
        Point twoBefore = oldPoints.next();
        Point oneBefore = oldPoints.next();
        double angle;
        double remainder;
        while(oldPoints.hasNext()){
            Point p = oldPoints.next();
            angle = calculateAngle(twoBefore, oneBefore, p);
            remainder=angle % angleMultiple;
            if (remainder > (angleMultiple/2)){
                angle=(angle-remainder)+angleMultiple;//aciyi ekleyerek tamamla
                movePoint(oneBefore, p, angle);
            }
            else
            {
                angle=angle-remainder;
                movePoint(oneBefore, p, angle);
            }
            twoBefore = oneBefore;
            oneBefore = p;
        }
        if(this.preserve_distance){
            this.adjustDistance();
        }
    }
    
    double calculateAngle(Point p1, Point p2, Point p3){ // cosine law
        double a = Math.pow((p1.getX()-p2.getX()), 2) + Math.pow((p1.getY()-p2.getY()), 2);
        double b = Math.pow((p3.getX()-p2.getX()), 2) + Math.pow((p3.getY()-p2.getY()), 2);
        double c = Math.pow((p3.getX()-p1.getX()), 2) + Math.pow((p3.getY()-p1.getY()), 2);
        double angle = Math.acos((a+b-c)/(2*a*b));
        return angle;
    }
    
    void movePoint(Point p1, Point p2, double angle){
        double a = Math.pow((p1.getX()-p2.getX()), 2) + Math.pow((p1.getY()-p2.getY()), 2);
        double _angle=180-angle;
        double temp_x=a * Math.cos(_angle);
        double temp_y=a * Math.sin(_angle);
        p2.setX(p1.getX()+temp_x);
        p2.setY(p1.getY()+temp_y);
    }
    
    void adjustDistance(){
        int N=0;
        double distance=0.0;
        double currentDistance;
        Iterator<Point> Points = points.iterator();
        Point p1 = Points.next();
        while(Points.hasNext()){
            Point p2 = Points.next();
            distance=distance+distance(p1,p2);
            N++;
            p1=p2;
        }
        mean_distance=distance/N;
        
    }
    double distance(Point p1, Point p2){
        return Math.pow((p1.getX()-p2.getX()), 2) + Math.pow((p1.getY()-p2.getY()), 2);
    }
}
