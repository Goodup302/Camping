/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camping;

import com.mysql.jdbc.Driver;
import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;

public class ConnectionDB {

    final static String SERVER = "127.0.0.1:3306";
    final static String DB_NAME = "test";
    final static String UNERNAME = "root";
    final static String PASSWORD = "";
    
    public static Connection connection;
    
    public static Connection get() throws SQLException{
        if (ConnectionDB.connection == null) {
            ConnectionDB.connection = DriverManager.getConnection("jdbc:mysql://"+SERVER+"/"+DB_NAME, UNERNAME, PASSWORD);
        }
        return ConnectionDB.connection;
    }

    public static void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
}
