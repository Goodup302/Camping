/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gadoy.camping.controlleurs.components;

import fr.gadoy.camping.Main;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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
        AnchorPane pane = FXMLLoader.load(Main.getRoot("views/main.fxml"));
        main.getChildren().setAll(pane);
    }
    
    @FXML
    private void disconnect() throws IOException {
        AnchorPane pane = FXMLLoader.load(Main.getRoot("views/login.fxml"));
        main.getChildren().setAll(pane);
    }
    
    @FXML
    private void clients(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(Main.getRoot("views/clients.fxml"));
        main.getChildren().setAll(pane);
    }
    
    @FXML
    private void factures(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(Main.getRoot("views/factures_.fxml"));
        main.getChildren().setAll(pane);
    }
    
    @FXML
    private void devis(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(Main.getRoot("views/devis_.fxml"));
        main.getChildren().setAll(pane);
    }
    
    @FXML
    private void reservation(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(Main.getRoot("views/reservations_.fxml"));
        main.getChildren().setAll(pane);
    }
    
}
