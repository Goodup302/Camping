/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {

    public static String sqlServer;
    public static String sqlDbName;
    public static String sqlPassword;
    public static String sqlUser;
    
    public static void init() {
        try {
            Properties prop = Config.load("src/camping/config.properties");
            String test = prop.getProperty("sql.server");
            //Sql config
            Config.sqlServer = prop.getProperty("sql.server");
            Config.sqlDbName = prop.getProperty("sql.db_name");
            Config.sqlPassword = prop.getProperty("sql.user");
            Config.sqlUser = prop.getProperty("sql.password");
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Properties load(String filename) throws IOException, FileNotFoundException{
        Properties properties = new Properties();
        FileInputStream input = new FileInputStream(filename); 
        try{
           properties.load(input);
           return properties;
        } finally {
           input.close();
        }
   }
}
