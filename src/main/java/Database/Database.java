package Database;

import java.sql.*;

public class Database {

    private static Database database;
    public String url;
    public String user;
    public String password;
    public Database(){

    }

    public static synchronized Database getInstance(){
        if (database == null){
            database = new Database();
        }
        return database;
    }
    public void setUrl(String url){
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection(url,user,password);
        return connection;
    }
}
