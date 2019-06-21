/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camping;

import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;

public class ConnectionDB {
    public static Connection connection;
    
    public static Connection get() throws SQLException{
        if (ConnectionDB.connection == null) {
            ConnectionDB.connection = DriverManager.getConnection("jdbc:mysql://"+Config.sqlServer+"/"+Config.sqlDbName, Config.sqlUser, Config.sqlPassword);
        }
        return ConnectionDB.connection;
    }

    public static void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
}
