/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schematizing_maps.server_side;

import java.sql.Connection;

/**
 *
 * @author px5x2
 */
public class mysql_UTIL {

    private String connectionURL;
    private Connection connection;
    
    
    
    
    
    public mysql_UTIL(String host, String port, String user, String password, String schema) {
        connectionURL = "jdbc:mysql://"+host+":"+port+"/"+schema+"?"+"user="+user+"&password="+password;
    }
    
    public boolean checkUser(String user, String pass){
        return false;
    }
    public boolean loadConfig(){
        return false;
    }
    public boolean updateConfig(){
        return false;
    }
    public boolean saveMap(){
        return false;
    }
    public boolean retrieveMaps(){
        return false;
    }
    
    
    private class mysqlCheckUser implements Runnable{

        @Override
        public void run() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
    private class mysqlLoadConfig implements Runnable{

        @Override
        public void run() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
    private class mysqlUpdateConfig implements Runnable{

        @Override
        public void run() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
    private class mysqlSaveMap implements Runnable{

        @Override
        public void run() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
    private class mysqlRetrieveMaps implements Runnable{

        @Override
        public void run() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
}
