/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camping;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Administrateur
 */
public class ButtonMenu {
    
    @FXML
    public AnchorPane main;
    
    @FXML
    private ImageView imageHome;
    
    @FXML
    private ImageView imageDisconnect;
    
    @FXML
    private Button buttonClients;
    
    @FXML
    private Button buttonFactures;
    
    @FXML
    private Button buttonDevis;
    
    @FXML
    private Button buttonReservations;
    
    @FXML
    private void home() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        main.getChildren().setAll(pane);
    }
    
    @FXML
    private void disconnect() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        main.getChildren().setAll(pane);
    }
    
    @FXML
    private void clients(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/clients.fxml"));
        main.getChildren().setAll(pane);
    }
    
    @FXML
    private void factures(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/factures_.fxml"));
        main.getChildren().setAll(pane);
    }
    
    @FXML
    private void devis(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/devis_.fxml"));
        main.getChildren().setAll(pane);
    }
    
    @FXML
    private void reservation(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/reservations_.fxml"));
        main.getChildren().setAll(pane);
    }
    
}
