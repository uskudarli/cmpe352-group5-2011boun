/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package applet_algorithm;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Nurettin Yilmaz
 * Serialized class because we handle serializable faster in object sending and receiving 
 * especially passing between applet and servlet via network
 */
public class Map implements Serializable{

    private Vector<Connection> connections;         //this fields will be initalized within the constructor 
    private Vector<MyPoint> points;             // when an instance of Map class is crated in Save button.
                                                //it will be used to create the string format of the data in the applet.

    private String XMLData;
    private String map_name;                    
    private boolean visible;
    private String map_owner;           // collected from probably session
                                        // we need this to relate maps to specific users
    private String[] keywords;
    private int Image_ID;               // it not necessary to set this field while saving map
                                        // it is crucial in loading/retrieving searched maps
                                        // btw it is set in mysql_UTIL, I thought that is necessary in loading maps
       
    /*
     * Constructor of Map
     * We are supposed to fill in the fields above.
     * In fact those are needed for database record.
     */
    public Map(String map_name, boolean visible,String[] keywords,Vector<MyPoint> ps, Vector<Connection> cs){
        this.map_name = map_name;
        this.visible = visible;
        this.keywords = keywords;
        this.points =ps;
        this.connections = cs;
    }
    
    public Map(){
        
    }
    // @ TODO bahtiyar veya nuretiin yapacak
    public String getXMLFormat(){
        
        return XMLData;
    }
    
    /*
     * Return array, itll be handled just before it is written to db
     */
    
    public String[] getKeywords(){
        return keywords;
    }
    public String getMapName(){
        return map_name;
    }
    public boolean getVisible(){
        return visible;
    }
    public String getMapOwner(){
        return map_owner;
    }
    public int getImage_ID(){
        return Image_ID;
    }
    /*
     * setter are used to initialize the map which is wanted to be loaded
     * in the applet
     * 
     * setters are used in mysql_UTIL.mysqlLoadMap class
     */
    
    
    public void setKeywords(String keywords){
        this.keywords = keywords.split(",");
        
    }
    public void setMapName(String mapName){
        map_name = mapName;
    }
    public void setVisible(boolean visible){
        this.visible = visible;
    }
    public void setMapOwner(String userName){
        map_owner = userName;
    }
    public void setImage_ID(int id){
        Image_ID = id;
    }

    public void setXMLData() {    //this function creates a string representation of the map in xml format.
        String XMLFormat="<Map>";
        for(int i=0;i<connections.size();i++){
            Connection c1=connections.get(0);
            MyPoint p1=c1.p1;
            MyPoint p2=c1.p2;
            String color=c1.c.toString();
            XMLFormat=XMLFormat.concat("<Edge>");
            XMLFormat=XMLFormat.concat("<Point1_X>"+p1.getX()+"</Point1_X>");
            XMLFormat=XMLFormat.concat("<Point1_Y>"+p1.getY()+"</Point1_Y>");
            XMLFormat=XMLFormat.concat("<Point2_X>"+p2.getX()+"</Point2_X>");
            XMLFormat=XMLFormat.concat("<Point2_Y>"+p2.getY()+"</Point2_Y>");
            XMLFormat=XMLFormat.concat("</Edge>");
        }
        for(int i=0;i<points.size();i++){
            MyPoint p = points.get(i);
            XMLFormat=XMLFormat.concat("<Point_X>");
            XMLFormat=XMLFormat.concat(Integer.toString(p.getX()));
            XMLFormat=XMLFormat.concat("</Point_X>");
            XMLFormat=XMLFormat.concat("<Point_Y>");
            XMLFormat=XMLFormat.concat(Integer.toString(p.getY()));
            XMLFormat=XMLFormat.concat("</Point_Y>");
            XMLFormat=XMLFormat.concat("<Description>");
            XMLFormat=XMLFormat.concat(p.description);
            XMLFormat=XMLFormat.concat("</Description>");
        }
        XMLFormat=XMLFormat.concat("<Map_Description>"+map_name+"</Map_Description>");
        XMLFormat=XMLFormat.concat("</Map>");
        XMLData = XMLFormat;
    }
    /*
     * parse XML here to load the points and connections of the map
     */


    public void setPointsAndConnections(String XMLData){
        this.XMLData = XMLData;
        
        
    }
}
