package applet_algorithm;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Hashtable;
import java.util.Vector;



/**
 *
 * @author Nurettin Yılmaz
 */
public class mysql_UTIL {

    private static String connectionURL="jdbc:mysql://85.153.22.90:3306/project5?"+"user=project5&password=662512";
    private static Connection connection;
    
    
    
    
    
    public mysql_UTIL(String host, String port, String user, String password, String schema) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //connectionURL = "jdbc:mysql://"+host+":"+port+"/"+schema+"?"+"user="+user+"&password="+password;
            connectionURL = "jdbc:mysql://85.153.22.90:3306/project5?"+"user=project5&password=662512";
           
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
    /*
     * we return null, if database error occurs
     * (assuming that we give this method "correct" params
     * 
     * hence, handle the null case in the applet
     */
    public static Map loadMap(String userName, int Image_ID){
        mysqlLoadMap a = new mysqlLoadMap(userName, Image_ID);
        a.run();
        return a.getMap();
        
    }
    public static Vector<Map> searchMaps(String user, String keyword){
        mysqlSearchMaps a = new mysqlSearchMaps(user, keyword);
        a.run();
        return a.getResultingMaps();
        
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
        String user;
        String pass;
        boolean result;

        public mysqlAddUser(String user, String pass) {
            
            this.user = user;
            this.pass = pass;
            
            query = "insert into USER_LOGIN (name, password) values (?,?);";
            result = false;
        }
        
        
        @Override
        public void run() {
            try {
               connection = (Connection)DriverManager.getConnection(connectionURL);
               PreparedStatement ps = connection.prepareStatement(query);
               ps.setString(1, user);
               ps.setString(2, pass);
               ps.execute();
               
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
        private String user;
        private String pass;
        private boolean isSuccessful;
        private Exception error;

        public mysqlCheckUser(String user, String pass) {
            query = "select * from USER_LOGIN where (name=?) and (password=?);";
            this.user = user;
            this.pass = pass;            
            isSuccessful = false;
            error = null;
        }
        
        @Override
        public void run(){
            try{
                
                connection = (Connection)DriverManager.getConnection(connectionURL);
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, user);
                ps.setString(2, pass);
                ResultSet result = ps.executeQuery();
                
                
                if(result.first()){
                    isSuccessful = true;
                }
                
                result.close();
                ps.close();
                connection.close();
                result = null;
                ps = null;
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
        private String user;
        private int result;

        public mysqlUserExists(String user) {
            query = "select count(*) from USER_LOGIN where name=?;";
            this.user = user;
            result = 0;
            
        }
        
        
        @Override
        public void run() {
            try {
                connection = (Connection) DriverManager.getConnection(connectionURL);
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, user);                
                ResultSet res = ps.executeQuery();
                
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
                ps.close();
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
        Map map;
        String keywords;
        int isVisible;
        boolean isSuccessful;
        

        public mysqlSaveMap(Map map) {
            this.map = map;
            isVisible = map.getVisible() ? 1 : 0;
            keywords = "";
            String[] _keywords = map.getKeywords();
            for(int i=0 ; i < _keywords.length-1 ; i++){
                keywords = keywords.concat(_keywords[i]+",");
            }
            keywords = keywords.concat(_keywords[_keywords.length-1]);
            
            
            query_to_map = "insert into MAPS (User_ID,Visible,Map_Name,MapXMLData,keywords) values ((select User_ID from USER_LOGIN where name=?"
                    +" ),?" 
                    + ",?" 
                    + ",?"
                    + ",? );";
            isSuccessful = false;
            
        }
        
        @Override
        public void run() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = (Connection)DriverManager.getConnection(connectionURL);
                PreparedStatement ps = connection.prepareStatement(query_to_map);
                ps.setString(1, map.getMapOwner());
                ps.setInt(2, isVisible);
                ps.setString(3, map.getMapName());
                ps.setString(4, map.getXMLFormat());
                ps.setString(5, keywords);
                ps.executeUpdate();
                
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
    private static class mysqlLoadMap implements Runnable{

        
        private String query;
        private String user;
        private int Image_ID;
        private Map map;

        public mysqlLoadMap(String user, int Image_ID) {
            query = "select * from MAPS right join USER_LOGIN on USER_LOGIN.User_ID=MAPS.User_ID "
                    + "where name=? and Image_ID=?";
            this.user = user;
            this.Image_ID = Image_ID;
            map = null;
        }
        
        @Override
        public void run() {
            try {
                map = new Map();
                connection = (Connection) DriverManager.getConnection(connectionURL);
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, user);
                ps.setInt(2, Image_ID);
                ResultSet result = ps.executeQuery(query);
                
                if(result.next()){
                    map.setMapOwner(result.getString("name"));
                    map.setKeywords(result.getString("keywords"));
                    map.setMapName(result.getString("Map_Name"));
                    map.setVisible(result.getBoolean("Visible"));
                    map.setPointsAndConnections(result.getString("MapXMLData"));
                    
                    
                }else{
                    map = null;
                }
                result.close();
                ps.close();
                connection.close();
                
            } catch (Exception e) {
                e.printStackTrace();
                connection = null;
                map = null;
            }
            connection = null;
        }
        public Map getMap(){ return map; }
        
    }
    private static class mysqlSearchMaps implements Runnable{

        
        private String query;
        private String user;
        private String keyword;
        private Vector<Map> resulting_maps;

        public mysqlSearchMaps(String user, String keyword) {
            query = "select Image_ID,keywords from MAPS join USER_LOGIN on MAPS.User_ID=USER_LOGIN.User_ID "
                    + "where Visible='1' or name = ? ;";
            this.user = user;
            resulting_maps = new Vector<Map>();
            this.keyword = keyword;
            
        }
        
        
        
        @Override
        public void run() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = (Connection) DriverManager.getConnection(connectionURL);
                PreparedStatement ps = connection.prepareStatement(query);
                
                ps.setString(1, user);
                ResultSet result = ps.executeQuery();
                
                
                Vector<Integer> ids = new Vector<Integer>();
                
                while(result.next()){
                    
                    int id =result.getInt("Image_ID");
                    String[] keywords = result.getString("keywords").split(",");
                    
                    /*
                     * we take image ids which contain the keyword, for later retrieving
                     * ( i.e. populating maps )
                     */
                    if(isKeywordFound(keywords, keyword)){
                        ids.add(id);
                    }                   
                    
                }
                
                if(ids.isEmpty()){
                    resulting_maps = null;
                    result.close();
                    ps.close();
                    connection.close();
                    connection = null;
                    return;
                }else{
                    result.close();
                    ps.close();
                    
                    /*
                     * Retrieve found maps & Prepare query string
                     */
                    String query2 = "select * from MAPS join USER_LOGIN on MAPS.User_ID=USER_LOGIN.User_ID where ";
                    for(int i=0;i<ids.size();i++){
                        query2 = query2.concat("Image_ID='"+ids.get(i)+"' ");
                        
                        //for last element we do not place "and"
                        //
                        if(i != ids.size()-1){
                            query2 = query2.concat("or ");
                        }else{
                            query2 = query2.concat(";");
                        }
                    }
                    
                    Statement stmt2 = connection.createStatement();
                    ResultSet result2 = stmt2.executeQuery(query2);
                    
                    while(result2.next()){
                        Map m = new Map();
                        m.setImage_ID(result2.getInt("Image_ID"));
                        m.setMapOwner(result2.getString("name"));
                        m.setKeywords(result2.getString("keywords"));
                        m.setMapName(result2.getString("Map_Name"));
                        m.setVisible(result2.getBoolean("Visible"));
                        m.setPointsAndConnections(result2.getString("MapXMLData"));
                        resulting_maps.add(m);
                    }
                    result2.close();
                    stmt2.close();
                    connection.close();
                    
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                resulting_maps = null;
                connection = null;
            }
            
            
        }
        
        private boolean isKeywordFound(String[] keywords, String keyword){
            
            for( int i=0 ; i < keywords.length ; i++ ){
                if(keywords[i].contains(keyword)){
                    return true;
                }
                    
            }
            return false;
        }
        public Vector<Map> getResultingMaps(){
            return resulting_maps;
        }
    }
    
    public static void main(String args[]) throws Exception{
        //
        // for testing purposes
        
        Class.forName("com.mysql.jdbc.Driver");
        connectionURL = "jdbc:mysql://titan.cmpe.boun.edu.tr:3306/database5?"+"user=project5&password=s8u4p";
        
        System.out.println(userExists("nurettin2"));
        
        
        
        
        
        
        
        
    }
}
