/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import controlleurs.components.ButtonMenu;
import entities.Client;
import utils.ConnectionDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class ClientsController extends ButtonMenu implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button buttonEnregistrer;
    
    @FXML
    private Button buttonSelectionné;
    
    @FXML
    private TextField textFieldNom;
    
    @FXML
    private TextField textFieldAdresse;
    
    @FXML
    private TextField textFieldPrenom;
    
    @FXML
    private TableView<Client> tableViewClients;
    
    @FXML
    private TableColumn<Client, String> columnPrenom;
    
    @FXML
    private TableColumn<Client, String> columnNom;
    
    @FXML
    private TableColumn<Client, String> columnTel;
    
    @FXML
    private TableColumn<Client, String> columnFix;
    
    @FXML
    private TableColumn<Client, String> columnMail;
    
    @FXML
    private TableColumn<Client, String> columnAdresse;
    
    @FXML
    private TableColumn<Client, Integer> columnId;
    
    //@FXML
    //private TableColumn<Client, Date> columnNaissance;
    
    @FXML
    private Button buttonRechercher;
    
    @FXML
    private Label labelWarning;
    
    @FXML
    private TextField textFieldTel;
    
    @FXML
    private TextField textFieldFix;
    
    @FXML
    private TextField textFieldMail;
    
    @FXML
    private ComboBox comboJour;
    
    @FXML
    private ComboBox comboMois;
    
    @FXML
    private ComboBox comboAnnee;
    
    @FXML
    private RadioButton radioMme;
    
    @FXML
    private RadioButton radioMr;
    
    private ObservableList<ObservableList> data;
    
    public int idClient;
    
    String[] mois = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
                            "Juillet", "Aout", "Septembre", "Octobre", "Novembre",
                            "Décembre"};
    
    String[] numMois = {"01", "02", "03", "04", "05", "06", "07", 
                            "08", "09", "10", "11", "12"};
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        for(int i = 1920; i < 2030; i++){
            comboAnnee.getItems().addAll(i);
        }
        
        for(int i =0; i < 12; i++){
            comboMois.getItems().addAll(mois[i]);
        }
        
        for(int i = 0; i < 32; i++){
            comboJour.getItems().addAll(i);
        }
        
        columnNom.setCellValueFactory(new PropertyValueFactory<>(Client.LAST_NAME));
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>(Client.FIRST_NAME));
        columnTel.setCellValueFactory(new PropertyValueFactory<>(Client.MOBILE));
        columnFix.setCellValueFactory(new PropertyValueFactory<>(Client.PHONE));
        columnMail.setCellValueFactory(new PropertyValueFactory<>(Client.EMAIL));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));    
        columnAdresse.setCellValueFactory(new PropertyValueFactory<>(Client.POSTAL_CODE)); //?
        
        tableViewClients.setItems(FXCollections.observableArrayList(Client.getAll()));
    }   
    
    public void test(){
        if(tableViewClients.getSelectionModel().getSelectedItem() != null){
            buttonSelectionné.setVisible(true);
            Client client = tableViewClients.getSelectionModel().getSelectedItem();
            System.out.println(client.getLastName());
        }
         
    }
    
    @FXML
    public void buttonSelectionné() throws IOException{
        //
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/optionClient.fxml"));
        main.getChildren().setAll(pane);
    }
    
    public void buttonRechercher(){
        testNumber(textFieldTel.getText());
    }
    
    public boolean testNumber(String x){
        boolean test = false;
        if(x.length() != 10 || !x.matches("[0-9]*$")){
            test = true;
        }else{
            test = false;
        }

        return test;
    }
    
    public boolean checkFields(){
        if(textFieldNom.getText().compareTo("") == 0 || textFieldPrenom.getText().compareTo("") == 0
                || textFieldMail.getText().compareTo("") == 0 || textFieldTel.getText().compareTo("") == 0
                || textFieldFix.getText().compareTo("") == 0 || textFieldAdresse.getText().compareTo("") == 0){
            labelWarning.setText("Tout les champs doivent etre remplis");
            return false;
        }else{
            return true;
        }  
    }

    public void buttonEnregistrer(){
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String nom = textFieldNom.getText();
            String prenom = textFieldPrenom.getText();
            String tel = textFieldTel.getText();
            String fix = textFieldFix.getText();
            String mail = textFieldMail.getText();
            String adresse = textFieldAdresse.getText();
            String gender;
            String moiss = "";
            
            if(radioMr.isSelected()){
                gender = "Mr";
            }else{
                gender = "Mme";
            }
            
            for(int i = 0; i < 12; i++){
                if(comboMois.getValue().toString() == mois[i]){
                    moiss = numMois[i];
                }
            }
            String date = comboJour.getValue().toString() + "-" + moiss + "-" + comboAnnee.getValue().toString();
            
            try {
                Date datee = formatter.parse(date);
                java.sql.Date sqlDate = new java.sql.Date(datee.getTime());
                System.out.println(sqlDate);
                Connection database = ConnectionDB.get();
                String sql = "INSERT INTO clients (nom, prenom, numTel, numFix, mail, adresse, dateNaissance, Civilité) Values ('"+nom+"','"+prenom+"','"+tel+"','"+fix+"','"+mail+"','"+adresse+"','"+sqlDate+"', '" + gender + "')";
                Statement statement = database.createStatement();
                statement.executeUpdate(sql);
                Client.getAll();
            } catch (ParseException ex) {
                Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tableViewClients.getItems().clear();
            tableViewClients.setItems(FXCollections.observableArrayList(Client.getAll()));
    }
    //}
}
