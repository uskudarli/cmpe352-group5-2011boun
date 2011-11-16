/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schematizing_maps.server_side;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author px5x2
 */
public class mysql_UTIL {

    private static String connectionURL;
    private static Connection connection;
    
    
    
    
    
    public mysql_UTIL(String host, String port, String user, String password, String schema) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connectionURL = "jdbc:mysql://"+host+":"+port+"/"+schema+"?"+"user="+user+"&password="+password;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
    }
    
    public static boolean checkUser(String user, String pass){
        
        try {
            mysqlCheckUser c = new mysqlCheckUser(user, pass);
            c.run();
            if(c.getResult()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean loadConfig(){
        return false;
    }
    public static boolean updateConfig(){
        return false;
    }
    public static boolean saveMap(){
        return false;
    }
    public static boolean retrieveMaps(){
        return false;
    }
    
    
    private static class mysqlCheckUser implements Runnable{

        private String query;
        private boolean isSuccessful;

        public mysqlCheckUser(String user, String pass) {
            query = "select * from USER_LOGIN where (name='"+user+"') and (password='"+pass+"');";
            isSuccessful = false;
        }
        
        @Override
        public void run(){
            try{
                
                connection = (Connection)DriverManager.getConnection(connectionURL);
                Statement stmt = connection.createStatement();
                ResultSet result = stmt.executeQuery(query);
                
                if(result.first()){
                    isSuccessful = true;
                }
                connection.close();
                connection = null;
                return;
  
            }catch(Exception e){
                e.printStackTrace();
            }
            
            
        }
        public boolean getResult(){
            return isSuccessful;
        }
    }
    private static class mysqlLoadConfig implements Runnable{

        @Override
        public void run() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
    private static class mysqlUpdateConfig implements Runnable{

        @Override
        public void run() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
    private static class mysqlSaveMap implements Runnable{

        @Override
        public void run() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
    private static class mysqlRetrieveMaps implements Runnable{

        @Override
        public void run() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
    
    public static void main(String args[]) throws Exception{
        //
        // for testing purposes
        
        
        
    }
}
