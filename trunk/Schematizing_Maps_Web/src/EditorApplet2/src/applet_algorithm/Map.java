/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package applet_algorithm;

import java.util.Vector;

/**
 *
 * @author px5x2
 */
public class Map {
    private Vector<MyPoint> points;
    private Vector<Connection> connections;
    private String map_name;                    
    private boolean visible;
    private String map_owner;           // collected from probably session
                                        // we need this to relate maps to specific users
    private String[] keywords;
    
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
    
    
}
