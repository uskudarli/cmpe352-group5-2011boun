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
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author Mert Terzihan
 */
public class Maps {
    static Vector<Point> points;
    Vector<Edge> edges;
    String keywords;
    int userID;
    double mean_distance=0.0;
    boolean preserveEast_West;
    boolean preserveNorth_South;
    boolean preserve_distance;
    private double angleMultiple;
    
    Maps()throws FileNotFoundException,IOException{
        //Default values
        
        points = new Vector<Point>();
        edges = null;
        keywords = "";
        userID = 0;
        defaultConfiguration();
        
    }
    
    Maps(Vector<Point> p, Vector<Edge> e, String k, int ID, Properties c, String ConfFile) throws FileNotFoundException,IOException{
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
        /*Note: When drawing the output, the distances between 
         * points will be set as "main_distance" */
    }
    double distance(Point p1, Point p2){
        return Math.pow((p1.getX()-p2.getX()), 2) + Math.pow((p1.getY()-p2.getY()), 2);
    }  
    private void ParseAndStorePoints_from_KMLFile(String KML_File){
           try{
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                Document doc = docBuilder.parse (new File(KML_File));
                NodeList listOfPlacemarks = doc.getElementsByTagName("Placemark");
                int totalPlacemarks = listOfPlacemarks.getLength();
                System.out.println("Total no of Placemark : " + totalPlacemarks);
                for(int s=0; s<listOfPlacemarks.getLength() ; s++){
                    Node firstPlacemarkNode = listOfPlacemarks.item(s);               
                    if(firstPlacemarkNode.getNodeType() == Node.ELEMENT_NODE){
                        Element element = (Element)firstPlacemarkNode;
                        String name_ = getTagValue("name", element);
                        String description_ = getTagValue("description", element);
                        String [] points_= getTagValue("coordinates", element).split(",");
                        Point p1=new Point(Double.parseDouble(points_[0]),Double.parseDouble(points_[1]),description_);
                        this.points.add(p1);               
                    }

                }      
            }catch (SAXParseException err) {
            System.out.println ("** Parsing error" + ", line " 
                 + err.getLineNumber () + ", uri " + err.getSystemId ());
            System.out.println(" " + err.getMessage ());

            }catch (SAXException e) {
            Exception x = e.getException ();
            ((x == null) ? e : x).printStackTrace ();

            }catch (Throwable t) {
            t.printStackTrace ();
            }
    }
    private void parseTextFile(String path) throws ParserConfigurationException, IOException, SAXException{
        File XmlFile = new File(path);
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(XmlFile);
	doc.getDocumentElement().normalize();
 
	NodeList nList = doc.getElementsByTagName("point");
 
	for (int i = 0; i<nList.getLength(); i++) {
 
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
                Element element = (Element) nNode;
                double x_coor = Double.parseDouble(getTagValue("x_coordinate", element));
                double y_coor = Double.parseDouble(getTagValue("y_coordinate", element));
                String description = getTagValue("description", element);
                Point p = new Point(x_coor, y_coor, description);
                this.points.add(p);
            }
        }
                
        nList = doc.getElementsByTagName("keyword");
        for (int i = 0; i<nList.getLength(); i++) {
 
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
                Node nValue = (Node) nList.item(0);
                if(this.keywords.length() != 0){
                    this.keywords += "," + nValue.getNodeValue();
                }
                else{
                    this.keywords = nValue.getNodeValue();  
                }
            }
        }
    }
    
    private static String getTagValue(String sTag, Element eElement) {
	NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
	return nValue.getNodeValue();
    }
}
