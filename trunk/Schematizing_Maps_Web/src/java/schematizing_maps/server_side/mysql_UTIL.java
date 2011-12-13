package schematizing_maps.server_side;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import applet_algorithm.Map;
import java.util.Hashtable;



/**
 *
 * @author Nurettin Yılmaz
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
    //
    // user related methods
    public static boolean addUser(String user, String pass){
        try{
            mysqlAddUser a = new mysqlAddUser(user, pass);
            a.run();
            if(a.getResult())
                return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
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
    public static boolean userExists(String user){
        mysqlUserExists a = new mysqlUserExists(user);
        a.run();
        int result = a.getResult();
        /*
         * -1 = a database error
         *  0 = user not found / duplicate users
         *  1 = user found!
         */
        if(result == 1)
            return true;
        
        
        return false;
    }
    //
    // configuration related methods
    public static boolean loadConfig(){
        return false;
    }
    public static boolean updateConfig(){
        return false;
    }
    //
    // map save / load / search
    public static boolean saveMap(Map map){
        mysqlSaveMap save = new mysqlSaveMap(map);
        save.run();
        return save.isSuccessful();
    }
    public static boolean retrieveMaps(){
        return false;
    }
    public static Hashtable<Integer, String> searchMaps(){
        Hashtable<Integer, String> maps = new Hashtable<Integer, String>();
        return maps;
    }
    
    //
    // Runnable derived classes, to do db operations in a seperate thread.
    //
    private static class mysqlAddUser implements Runnable{

        String query;
        boolean result;

        public mysqlAddUser(String user, String pass) {
            query = "insert into USER_LOGIN (name, password) values ('"+user+"','"+pass+"');";
            result = false;
        }
        
        
        @Override
        public void run() {
            try {
               connection = (Connection)DriverManager.getConnection(connectionURL);          
               Statement stmt = connection.createStatement();
               stmt.executeUpdate(query);
               result = true;
               connection.close();
               
               
            } catch (Exception e) {
                e.printStackTrace();
                result = false;
            }
            connection = null;
        }
        public boolean getResult(){
            return result;
        }
        
        
    }
    private static class mysqlCheckUser implements Runnable{

        private String query;
        private boolean isSuccessful;
        private Exception error;

        public mysqlCheckUser(String user, String pass) {
            query = "select * from USER_LOGIN where (name='"+user+"') and (password='"+pass+"');";
            isSuccessful = false;
            error = null;
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
                
                result.close();
                stmt.close();
                connection.close();
                result = null;
                stmt = null;
            }catch(Exception e){
                error = e;
            
            }finally{
                
                connection = null;
                
                
            }
            
            
            
            
        }
        public boolean getResult(){
            return isSuccessful;
        }
        public Exception getError(){
            return error;
        }
    }
    private static class mysqlUserExists implements Runnable{

        
        private String query;
        private int result;

        public mysqlUserExists(String user) {
            query = "select count(*) from USER_LOGIN where name='" + user + "';";
            result = 0;
            
        }
        
        
        @Override
        public void run() {
            try {
                connection = (Connection) DriverManager.getConnection(connectionURL);
                Statement stmt = connection.createStatement();
                ResultSet res = stmt.executeQuery(query);
                if(res.next()){
                    int count = res.getInt(1);
                    if(count != 1){
                        result = 0;
                    }else{
                        result = 1;
                    }
                }else{
                    result = -1;
                }
                
                res.close();
                stmt.close();
                connection.close();
                connection = null;
                
            } catch (Exception e) {
                e.printStackTrace();
                connection = null;
                result = -1;
                
            }
            
        }
        public int getResult(){
            return result;
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
        
        String query_to_map;
        boolean isSuccessful;
        

        public mysqlSaveMap(Map map) {
            int isVisible = map.getVisible() ? 1 : 0;
            String keywords = "";
            String[] _keywords = map.getKeywords();
            for(int i=0 ; i < _keywords.length-1 ; i++){
                keywords = keywords.concat(_keywords[i]+",");
            }
            keywords = keywords.concat(_keywords[_keywords.length-1]);
            
            
            query_to_map = "insert into MAPS (User_ID,Visible,Map_Name,MapXMLData,keywords) values ((select from USER_LOGIN where name='"
                    + map.getMapOwner() +"' ),'" 
                    + isVisible + "','" 
                    + map.getMapName() +"','"
                    + map.getXMLFormat() +"','"
                    + keywords + "');";
            isSuccessful = false;
            
        }
        
        @Override
        public void run() {
            try {
                connection = (Connection)DriverManager.getConnection(connectionURL);
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(query_to_map);
                connection.close();
                
                
            } catch (Exception e) {
                e.printStackTrace();
                connection = null;
                return;
                
            }
            isSuccessful = true;
            connection = null;
            
            
        }
        public boolean isSuccessful(){
            return isSuccessful;
        }
        
    }
    private static class mysqlRetrieveMaps implements Runnable{

        @Override
        public void run() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
    private static class mysqlSearchMaps implements Runnable{

        
        private String query;
        private Hashtable<Integer, String> results;

        public mysqlSearchMaps(String keyword) {
            
        }
        
        
        
        @Override
        public void run() {
            
        }
        
    }
    
    public static void main(String args[]) throws Exception{
        //
        // for testing purposes
        //connectionURL = "jdbc:mysql://titan.cmpe.boun.edu.tr:3306/database5?"+"user=project5&password=s8u4p";
        //System.out.println(userExists("nurettin"));
        
        
        
        
    }
}
