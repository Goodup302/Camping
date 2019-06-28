/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gadoy.camping;

import fr.gadoy.camping.utils.Hibernate;
import fr.gadoy.camping.entities.Location;
import fr.gadoy.camping.utils.ConsoleColors;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 * @author Administrateur
 */
public class Main extends Application {
    
    static Main Instance;
    static final String ROOT_FOLDER = "/fr/gadoy/camping/";
    
    public static URL getRoot(String file) {
        return Main.Instance.getClass().getResource(file);
    }
    
    public static void main(String[] args) {
        //TEST HIBERNATE
//        SessionFactory sessionFactory = Hibernate.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        try {
//            Location loc = (Location) session.load(Location.class, new Integer(2));
//            System.out.println("\u001B[44m"+loc.getTypelocation().getName());
//        } finally {
//            session.close();
//        }

        
        launch(args);
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Main.Instance = this;
        System.out.println(ConsoleColors.GREEN + Main.getRoot("views/main.fxml") + ConsoleColors.RESET);
        Scene scene = new Scene(FXMLLoader.load(Main.getRoot("views/main.fxml")));
        stage.setScene(scene);
        stage.show();
    }
}
