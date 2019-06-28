/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gadoy.camping.controlleurs;

import fr.gadoy.camping.controlleurs.components.ButtonMenu;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author Administrateur
 */
public class OptionClientController extends ButtonMenu implements Initializable{
    
    @FXML
    private TextField textFieldPrenom;
    
    @FXML
    private TextField textFieldNom;
    
    @FXML
    private TextField textFieldFix;
    
    @FXML
    private TextField textFieldPortable;
    
    @FXML
    private TextField textFieldPostal;
    
    @FXML
    private TextField textFieldRue;
    
    @FXML
    private TextField textFieldVille;
    
    @FXML
    private TextField textFieldMaill;
    
    @FXML
    private RadioButton radioMr;
    
    @FXML
    private RadioButton Mme;
    
    @FXML
    private ComboBox comboJour;
    
    @FXML
    private ComboBox comboMois;
    
    @FXML
    private ComboBox comboAnnee;
    
    @FXML
    private Button buttonEnregistrer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    public void buttonEnregistrer(){
        
    }
}
