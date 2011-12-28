package applet_algorithm;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
    public Map(String map_name, boolean visible,Vector<MyPoint> ps, Vector<Connection> cs){
        if(map_name.isEmpty())
            this.map_name = "Default";
        else
            this.map_name = map_name;
        
        this.visible = visible;
        keywords = new String[ps.size()];
        this.points =ps;
        this.connections = cs;
    }
    
    public Map(){
        connections = new Vector<Connection>();
        points = new Vector<MyPoint>();
    }
    
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
    public Vector<Connection> getConnections(){
        return connections;
    }
    public Vector<MyPoint> getPoints(){
        return points;
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
    /*
     * This overloaded function used in save process, it extracts point description
     * and set those to keywords
     */
    public void setKeywords(){
        
        for(int i=0;i<points.size();i++){
            keywords[i] = points.get(i).getDescription();
        }
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
            Connection c1=connections.get(i);
            MyPoint p1=c1.p1;
            MyPoint p2=c1.p2;
            String color=Integer.toString(c1.c.getRGB());
            XMLFormat=XMLFormat.concat("<Edge>");
            XMLFormat=XMLFormat.concat("<Point1_X>"+p1.getX()+"</Point1_X>");
            XMLFormat=XMLFormat.concat("<Point1_Y>"+p1.getY()+"</Point1_Y>");
            XMLFormat=XMLFormat.concat("<Point2_X>"+p2.getX()+"</Point2_X>");
            XMLFormat=XMLFormat.concat("<Point2_Y>"+p2.getY()+"</Point2_Y>");
            XMLFormat = XMLFormat.concat("<Color>"+color+"</Color>");
            XMLFormat=XMLFormat.concat("</Edge>");
        }
        for(int i=0;i<points.size();i++){
            MyPoint p = points.get(i);
            XMLFormat = XMLFormat.concat("<Point>");
            XMLFormat=XMLFormat.concat("<Point_X>");
            XMLFormat=XMLFormat.concat(Integer.toString(p.getX()));
            XMLFormat=XMLFormat.concat("</Point_X>");
            XMLFormat=XMLFormat.concat("<Point_Y>");
            XMLFormat=XMLFormat.concat(Integer.toString(p.getY()));
            XMLFormat=XMLFormat.concat("</Point_Y>");
            XMLFormat=XMLFormat.concat("<Description>");
            XMLFormat=XMLFormat.concat(p.description);
            XMLFormat=XMLFormat.concat("</Description>");
            XMLFormat = XMLFormat.concat("</Point>");
        }
        XMLFormat=XMLFormat.concat("<Map_Description>"+map_name+"</Map_Description>");
        XMLFormat=XMLFormat.concat("</Map>");
        XMLData = XMLFormat;
    }
    

    /*
     * parse XML here to load the points and connections of the map
     * !!call this function when user clicks "load" button!!
     * !! It is not called when maps taken from db (not to slow down mysql_UTIL class)
     */
    public void setPointsAndConnections(String XMLData){
        this.XMLData = XMLData;
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            
            InputStream is = new ByteArrayInputStream(XMLData.getBytes());
            org.w3c.dom.Document doc = db.parse(is);
            
            
            ///////////////////////////////
            //////////Edge Parsing////////
            ///////////////////////////////
            NodeList edgeNodes = doc.getElementsByTagName("Edge");
            if(edgeNodes != null){
                /*
                 * Traverse all "Edge" nodes
                 */
                for(int i=0;i<edgeNodes.getLength();i++){                    
                    
                    Element elem = (Element)edgeNodes.item(i);
                    NodeList nodes = elem.getChildNodes();
                    
                    Connection conn = new Connection();                    
                    
                    if(nodes != null){
                        /*
                         * Get "Edge" Properties
                         * item(0) - Point1_X
                         * item(1) - Point1_Y
                         * item(2) - Point2_X
                         * item(3) - Point2_Y
                         * item(4) - Color (RGB)
                         */                        
                            
                        conn.setP1(new MyPoint(Integer.parseInt(nodes.item(2).getFirstChild().getNodeValue()),
                            Integer.parseInt(nodes.item(3).getFirstChild().getNodeValue()) ));

                        conn.setP2(new MyPoint(Integer.parseInt(nodes.item(0).getFirstChild().getNodeValue()),
                            Integer.parseInt(nodes.item(1).getFirstChild().getNodeValue()) ));                        

                        conn.setColor(new Color(Integer.parseInt(nodes.item(4).getFirstChild().getNodeValue())));
                        
                        connections.add(conn);
                        
                    }    
                }         
                    
            }
            
            ///////////////////////////////
            //////////Point Parsing////////
            ///////////////////////////////
            NodeList pointNodes = doc.getElementsByTagName("Point");                

            if(pointNodes != null){
                /*
                 * Traverse all "Point" nodes
                 */
                for(int i =0;i<pointNodes.getLength();i++){

                    Element elem = (Element)pointNodes.item(i);
                    NodeList nodes = elem.getChildNodes();

                    if(nodes != null){
                        /*
                         * Get "Point" Properties
                         * item(0) - Point_X
                         * item(1) - Point_Y
                         * item(2) - Description
                         */

                        MyPoint point = new MyPoint(Integer.parseInt(nodes.item(0).getFirstChild().getNodeValue()),
                                 Integer.parseInt(nodes.item(1).getFirstChild().getNodeValue()));

                        point.setDescription(nodes.item(2).getFirstChild().getNodeValue());

                        points.add(point);
                    }                        

                }                                

            }
            
            
            /////////////////////////////////////
            /////Finally we parse Map Name///////
            /////////////////////////////////////
            NodeList mapNameNode = doc.getElementsByTagName("Map_Description");
            if(mapNameNode != null){
                /*
                 * in fact we can take map_name also from corresponding database column
                 * 
                 */
                map_name = mapNameNode.item(0).getFirstChild().getNodeValue();
            }
            
        for(int i=0;i<connections.size();i++){
            connections.get(i).p1.outgoingPoints.add(connections.get(i).p2);
            connections.get(i).p2.outgoingPoints.add(connections.get(i).p1);
            connections.get(i).startingPoints = connections.get(i).findStartingPoints();
        }  
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    /*
     * for testing purposes
     */
    public static void main(String args[]){
        //String XMLData = "<Map><Edge><Point1_X>62</Point1_X><Point1_Y>109</Point1_Y><Point2_X>190</Point2_X><Point2_Y>230</Point2_Y><Color>-65536</Color></Edge><Edge><Point1_X>190</Point1_X><Point1_Y>230</Point1_Y><Point2_X>311</Point2_X><Point2_Y>107</Point2_Y><Color>-65536</Color></Edge><Edge><Point1_X>311</Point1_X><Point1_Y>107</Point1_Y><Point2_X>452</Point2_X><Point2_Y>231</Point2_Y><Color>-65536</Color></Edge><Point><Point_X>62</Point_X><Point_Y>109</Point_Y><Description>No description</Description></Point><Point><Point_X>190</Point_X><Point_Y>230</Point_Y><Description>No description</Description></Point><Point><Point_X>311</Point_X><Point_Y>107</Point_Y><Description>No description</Description></Point><Point><Point_X>452</Point_X><Point_Y>231</Point_Y><Description>No description</Description></Point><Map_Description>deneme2</Map_Description></Map>";
        //setPointsAndConnections(XMLData);
        }
}
