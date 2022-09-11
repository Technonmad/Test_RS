package Database;

import java.sql.*;

public class Database {
    private static String url = "jdbc:firebirdsql://localhost:3050/c:/Databases/test_db.fdb";
    private static String user = "sysdba";
    private static String password = "masterkey";
    private Database(){

    }

    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection(url,user,password);
        return connection;
    }

}
