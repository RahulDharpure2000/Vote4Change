
package evoting.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static Connection conn=null;
    static
    {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//RAHUL-PC:1521/XE", "evoting", "evoting");
            System.out.println("Connection sucessfully opened!");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static Connection getConnection()throws SQLException
    {
        return conn;
    }
    public static void closeConnection() throws SQLException
    {
        if(conn!=null)
        {
            conn.close();
        }
    }
}
