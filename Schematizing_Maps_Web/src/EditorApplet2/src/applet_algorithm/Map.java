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
    private Vector<MyPoint> points;
    private Vector<Connection> connections;
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
    public Map(){
        
    }
    
    
    // @ TODO bahtiyar veya nuretiin yapacak
    public String getXMLFormat(){
        
        return "";
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
    /*
     * parse XML here to load the points and connections of the map
     */
    public void setPointsAndConnections(String XMLData){
        
    }
}
