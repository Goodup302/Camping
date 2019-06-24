/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camping;

import utils.Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Administrateur
 */
public class Camping extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Config.init();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeDimensions(){
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
