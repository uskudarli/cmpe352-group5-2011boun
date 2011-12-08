/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applet_algorithm;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.File;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
/**
 *
 * @author Ervin Domazet & Mert Terzihan
 */
public class Algorithm {
    MyPoint rootPoint;
    String keywords;
    int userID;
    double mean_distance=0.0;
    boolean preserveEast_West;
    boolean preserveNorth_South;
    boolean preserve_distance;
    private double angleMultiple;
    double totalDistance=0;
    int totalEdges=0;
    
    Algorithm(MyPoint p, String k, int ID, Double _angleMultiple,boolean E_W,boolean N_S,boolean _distance) throws FileNotFoundException,IOException{
        rootPoint = p;
        keywords = k;
        userID = ID;
        preserveEast_West=E_W;
        preserveNorth_South=N_S;
        preserve_distance=_distance;
        angleMultiple = _angleMultiple;
        //parseConfigurationFromFile(ConfFile);
    }
    Algorithm(MyPoint p, String k, int ID) throws FileNotFoundException,IOException{
        rootPoint = p;
        keywords = k;
        userID = ID;
        preserveEast_West=false;
        preserveNorth_South=false;
        preserve_distance=false;
        angleMultiple =45.00;
        //parseConfigurationFromFile(ConfFile);
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
        angleMultiple =45.00;
    }
    MyPoint Schematize(){
        
        MyPoint startPoint=rootPoint.outgoingPoints.get(0);
        /*if(this.preserve_distance){
               this.totalDistance=totalDistance+distance(rootPoint,startPoint);
               this.totalEdges++;
        }*/
        //for(int i=0;i<startPoint.outgoingPoints.size();i++){
        //if (angleMultiple!=180.0){
                
            rootPoint.setY(rootPoint.outgoingPoints.get(0).getY());
            recursively_schematize(rootPoint, rootPoint.outgoingPoints.get(0));
            rootPoint.setY(0);
        //}
        //else
       // {
       // prepare_schematization(rootPoint,startPoint);
       // schematize_180(rootPoint.outgoingPoints.get(0));
       // }
        
        return rootPoint;    
    }
    void prepare_schematization(MyPoint p1,MyPoint p2){
        //if(! p1.outgoingPoints.isEmpty()){
         for(int i=0;i<p2.outgoingPoints.size();i++){
                MyPoint p3=p2.outgoingPoints.get(i);
                if(p3.point_id==p1.point_id){   
                    p2.outgoingPoints.remove(i);
                        i--; 
                }
                else{
                    prepare_schematization(p2,p3);
                }
       }
        
        
        
    }
    void schematize_180(MyPoint p1){
        if(!p1.outgoingPoints.isEmpty()){
            for(int i=0;i<p1.outgoingPoints.size();i++){
                    MyPoint p2=p1.outgoingPoints.get(i);
                    if(p2.point_id!=p1.point_id){
                        double distance_p1_p2=this.distance(p1, p2);
                        append_to_first(p1,p2,distance_p1_p2);
                        schematize_180(p2);
                    }
                    
           }
        }
    }
    void append_to_first(MyPoint p1,MyPoint p2,double distance)
    {
        p2.setY(p1.getY());
        p2.setX(p1.getX()+(int)distance);
    }
    void recursively_schematize(MyPoint p1,MyPoint p2){
        //if(! p2.outgoingPoints.isEmpty()){
            for(int i=0;i<p2.outgoingPoints.size();i++){
                MyPoint p3=p2.outgoingPoints.get(i);
                if(p3.point_id!=p1.point_id){
                    double angle=calculateAngle(p1,p2,p3);
                    double actual_angle=angle;
                    double remainder;
                    remainder=angle % angleMultiple;
                    int division = (int)(angle / angleMultiple);
                    if((remainder!=0.0) || (division != 0.0)){
                        if(remainder > (angleMultiple/2)){
                            //asagi dogru!
                            //angle=(int)((angle/angleMultiple) * angleMultiple )-remainder;
                            angle=angleMultiple-remainder;//aciyi ekleyerek tamamla
                            movePoint(p1,p2, p3, angle,actual_angle);
                        }
                        else{
                            //angle yukari dogru gidecek
                            angle=-remainder;
                            //angle=(int)(((angle/angleMultiple)-1) * angleMultiple )-remainder;
                            movePoint(p1,p2, p3,angle,actual_angle);
                        }
                    }
                    else if((remainder!= 0.0) && (division == 0.0)){
                        angle = angleMultiple-remainder;
                        movePoint(p1, p2, p3, angle, actual_angle);
                    }
                    
                    if(this.preserve_distance){
                        this.totalDistance=totalDistance+distance(p2,p3);
                        this.totalEdges++;
                    }
                    recursively_schematize(p2,p3);
                }
                else
                    {
                       p2.outgoingPoints.remove(i);
                       i--;
                    }
            }
       // }
        
    }
    double calculateAngle(MyPoint p1, MyPoint p2, MyPoint p3){ // cosine law
        double a = Math.sqrt(Math.pow((p1.getX()-p2.getX()), 2) + Math.pow((p1.getY()-p2.getY()), 2));
        double b = Math.sqrt(Math.pow((p3.getX()-p2.getX()), 2) + Math.pow((p3.getY()-p2.getY()), 2));
        double c = Math.sqrt(Math.pow((p3.getX()-p1.getX()), 2) + Math.pow((p3.getY()-p1.getY()), 2));
        double angle = Math.toDegrees(Math.acos((Math.pow(a,2)+Math.pow(b,2)- Math.pow(c,2))/(2.0*a*b)));
        return angle;
    }
    
    void movePoint(MyPoint p1,MyPoint p2, MyPoint p3, double _angle,double actual_angle){
        if(angleMultiple==(double)180){
            double distance_p2_p3=this.distance(p2, p3);
            append_to_first(p2,p3,distance_p2_p3);
        }
        else{
            double y3_prime;
            boolean y3_yukarda_kalir;
            if((p2.getX()-p1.getX())!=0){
                y3_prime=p2.getY() + (int)( ((p3.getX()-p2.getX())*(p2.getY()-p1.getY()))/ (p2.getX()-p1.getX())   );
            }
            else
            {
                if(p3.getX()>p2.getX()){
                    y3_prime=p3.getY()+1;
                }
                else
                    y3_prime=p3.getY()-1;
            }

            if (y3_prime >= p3.getY()){
                y3_yukarda_kalir=true;
            }
            else
                y3_yukarda_kalir=false;
            double dist_p2_p3 = Math.sqrt(Math.pow((p2.getX()-p3.getX()), 2) + Math.pow((p2.getY()-p3.getY()), 2));

            if(y3_yukarda_kalir && actual_angle >=90){
                MyPoint pnew=new MyPoint(p3.getX(),p2.getY());
                double angle=calculateAngle(pnew,p2,p3);
                double new_angle;
                if( pnew.getY()>p3.getY()){
                    new_angle=angle-_angle;
                }
                else
                    new_angle=_angle+angle;

                /*int tmp1=Math.abs((int)(dist_p2_p3*Math.cos(Math.toRadians(new_angle))));
                int tmp2=Math.abs((int)(dist_p2_p3*Math.sin(Math.toRadians(new_angle))));*/
                
                int tmp1=(int)(dist_p2_p3*Math.cos(Math.toRadians(new_angle)));
                int tmp2=(int)(dist_p2_p3*Math.sin(Math.toRadians(new_angle)));

                //p3.setX(p2.getX()+ tmp1 );
                if( pnew.getX()> p2.getX()){
                    p3.setX(p2.getX()+ tmp1 );
                }
                else{
                    p3.setX(p2.getX()- tmp1 );
                }
                if( pnew.getY()> p3.getY()){
                    p3.setY(p2.getY()- tmp2 );
                }
                else{
                    p3.setY(p2.getY()+ tmp2 );
                }
                /*if( pnew.getX()> p2.getX() && new_angle > 0.0){
                    p3.setX(p2.getX()+ tmp1 );
                }
                else if ( pnew.getX()> p2.getX() && new_angle < 0.0){
                    p3.setX(p2.getX()- tmp1 );
                }
                else if( pnew.getX()< p2.getX() && new_angle > 0.0){
                    p3.setX(p2.getX()- tmp1 );
                }
                else
                    p3.setX(p2.getX()+ tmp1 );
                
                if( pnew.getY()> p3.getY() && new_angle > 0.0){
                    p3.setY(p2.getY()- tmp2 );
                }
                else if ( pnew.getY()> p3.getY() && new_angle < 0.0){
                    p3.setY(p2.getY()+ tmp2 );
                }
                else if( pnew.getY()< p3.getY() && new_angle > 0.0){
                    p3.setY(p2.getY()+ tmp2 );
                }
                else
                    p3.setY(p2.getY()- tmp2 );*/
            }
            else if((y3_yukarda_kalir) && actual_angle <90){
                MyPoint pnew=new MyPoint(p2.getX(),p3.getY());
                double angle=calculateAngle(pnew,p2,p3);
                double new_angle;
                if(pnew.getX()>p3.getX()){
                    new_angle=angle-_angle;
                }
                else
                    new_angle=angle+_angle;

                /*int tmp1=Math.abs((int)(dist_p2_p3*Math.sin(Math.toRadians(new_angle))));
                int tmp2=Math.abs((int)(dist_p2_p3*Math.cos(Math.toRadians(new_angle))));*/
                
                int tmp1=(int)(dist_p2_p3*Math.sin(Math.toRadians(new_angle)));
                int tmp2=(int)(dist_p2_p3*Math.cos(Math.toRadians(new_angle)));
                //p3.setY(p2.getY()- tmp2 );
                if( pnew.getY()> p2.getY()){
                    p3.setY(p2.getY()+ tmp2 );
                }
                else{
                    p3.setY(p2.getY()- tmp2 );
                }
                /*if( pnew.getY()> p2.getY() && new_angle > 0.0){
                    p3.setY(p2.getY()+ tmp2 );
                }
                else if ( pnew.getY()> p2.getY() && new_angle < 0.0){
                    p3.setY(p2.getY()- tmp2 );
                }
                else if( pnew.getY()< p2.getY() && new_angle > 0.0){
                    p3.setY(p2.getY()- tmp2 );
                }
                else
                    p3.setY(p2.getY()+ tmp2 );*/
                
                if(pnew.getX()>p3.getX()){
                    p3.setX(p2.getX()- tmp1 );
                }
                else {
                    p3.setX(p2.getX()+ tmp1 );
                }
                /*if(pnew.getX()>p3.getX() && new_angle >0.0){
                    p3.setX(p2.getX()- tmp1 );
                }
                else if(pnew.getX()>p3.getX() && new_angle <0.0){
                    p3.setX(p2.getX()+ tmp1 );
                }
                else if(pnew.getX()<p3.getX() && new_angle >0.0){
                    p3.setX(p2.getX()+ tmp1 );
                }
                else 
                {
                    p3.setX(p2.getX()- tmp1 );
                }*/
            }
            else if(!(y3_yukarda_kalir) && actual_angle <90){
                MyPoint pnew=new MyPoint(p2.getX(),p3.getY());
                double angle=calculateAngle(pnew,p2,p3);
                double new_angle;
                if( pnew.getX()<p3.getX()){
                    new_angle=angle+_angle;
                }
                else
                    new_angle=angle-_angle;
                /*int tmp1=Math.abs((int)(dist_p2_p3*Math.sin(Math.toRadians(new_angle))));
                int tmp2=Math.abs((int)(dist_p2_p3*Math.cos(Math.toRadians(new_angle))));*/
                int tmp1=(int)(dist_p2_p3*Math.sin(Math.toRadians(new_angle)));
                int tmp2=(int)(dist_p2_p3*Math.cos(Math.toRadians(new_angle)));
                /*if(new_angle<=90.0){
                    p3.setY(p2.getY()+ tmp2 );
                }
                else
                    p3.setY(p2.getY()- tmp2 );
                */
                if( pnew.getY()> p2.getY()){
                    p3.setY(p2.getY()+ tmp2 );
                }
                else{
                    p3.setY(p2.getY()- tmp2 );
                }
                if(pnew.getX()>p3.getX()){
                    p3.setX(p2.getX()- tmp1 );
                }
                else if(pnew.getX()>p3.getX()){
                    p3.setX(p2.getX()+ tmp1 );
                }
                
                /*if( pnew.getY()> p2.getY() && new_angle > 0.0){
                    p3.setY(p2.getY()+ tmp2 );
                }
                else if ( pnew.getY()> p2.getY() && new_angle < 0.0){
                    p3.setY(p2.getY()- tmp2 );
                }
                else if( pnew.getY()< p2.getY() && new_angle > 0.0){
                    p3.setY(p2.getY()- tmp2 );
                }
                else
                    p3.setY(p2.getY()+ tmp2 );

                if(pnew.getX()>p3.getX() && new_angle >0.0){
                    p3.setX(p2.getX()- tmp1 );
                }
                else if(pnew.getX()>p3.getX() && new_angle <0.0){
                    p3.setX(p2.getX()+ tmp1 );
                }
                else if(pnew.getX()<p3.getX() && new_angle >0.0){
                    p3.setX(p2.getX()+ tmp1 );
                }
                else 
                {
                    p3.setX(p2.getX()- tmp1 );
                }*/       
            }
            else if(!(y3_yukarda_kalir) && actual_angle >=90)
            {
                MyPoint pnew=new MyPoint(p3.getX(),p2.getY());
                double angle=calculateAngle(pnew,p2,p3);
                double new_angle;
                if( pnew.getY()>p3.getY()){
                    new_angle=angle+_angle;
                }
                else
                    new_angle=angle-_angle;
                /*int tmp1=Math.abs((int)(dist_p2_p3*Math.cos(Math.toRadians(new_angle))));
                int tmp2=Math.abs((int)(dist_p2_p3*Math.sin(Math.toRadians(new_angle))));*/
                int tmp1=(int)(dist_p2_p3*Math.cos(Math.toRadians(new_angle)));
                int tmp2=(int)(dist_p2_p3*Math.sin(Math.toRadians(new_angle)));
                //p3.setX(p2.getX()+ tmp1 );
                if( pnew.getX()> p2.getX()){
                    p3.setX(p2.getX()+ tmp1 );
                }
                else {
                    p3.setX(p2.getX()- tmp1 );
                }
                if( pnew.getY()> p3.getY()){
                    p3.setY(p2.getY()- tmp2 );
                }
                else {
                    p3.setY(p2.getY()+ tmp2 );
                }
                /*
                if( pnew.getX()> p2.getX() && new_angle > 0.0){
                    p3.setX(p2.getX()+ tmp1 );
                }
                else if ( pnew.getX()> p2.getX() && new_angle < 0.0){
                    p3.setX(p2.getX()- tmp1 );
                }
                else if( pnew.getX()< p2.getX() && new_angle > 0.0){
                    p3.setX(p2.getX()- tmp1 );
                }
                else
                    p3.setX(p2.getX()+ tmp1 );
                
                if( pnew.getY()> p3.getY() && new_angle > 0.0){
                    p3.setY(p2.getY()- tmp2 );
                }
                else if ( pnew.getY()> p3.getY() && new_angle < 0.0){
                    p3.setY(p2.getY()+ tmp2 );
                }
                else if( pnew.getY()< p3.getY() && new_angle > 0.0){
                    p3.setY(p2.getY()+ tmp2 );
                }
                else
                    p3.setY(p2.getY()- tmp2 );*/
            }
        }
    }
    double distance(MyPoint p1, MyPoint p2){
        return Math.sqrt(Math.pow((p1.getX()-p2.getX()), 2) + Math.pow((p1.getY()-p2.getY()), 2));
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
                       // MyPoint p1=new MyPoint(Integer.parseInt(points_[0]),Integer.parseInt(points_[1]),description_);
                       // this.points.add(p1);               
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
 
        NodeList nList = doc.getElementsByTagName("Edge");
 
        for (int i = 0; i<nList.getLength(); i++) {
 
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
                Element element = (Element) nNode;
                int p1X = Integer.parseInt(getTagValue("Point1_X", element));
                int p1Y = Integer.parseInt(getTagValue("Point1_Y", element));
                int p2X = Integer.parseInt(getTagValue("Point2_X", element));
                int p2Y = Integer.parseInt(getTagValue("Point2_Y", element));
                String color = getTagValue("Color", element);
            }
        }
                
        nList = doc.getElementsByTagName("Point");
        for (int i = 0; i<nList.getLength(); i++) {
 
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
                Element element = (Element) nNode;
                int xCoor = Integer.parseInt(getTagValue("Point_X", element));
                int yCoor = Integer.parseInt(getTagValue("Point_Y", element));
                String desc = getTagValue("Description", element);
            }
        }
        
        nList = doc.getElementsByTagName("Map");
        for (int i = 0; i<nList.getLength(); i++) {
 
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
                Element element = (Element) nNode;
                String mapDesc = getTagValue("Map_Description", element);
            }
        }
    }
    
    private static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }   
}