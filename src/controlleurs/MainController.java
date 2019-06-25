/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurs;

import controlleurs.components.ButtonMenu;
import javafx.scene.control.Label;
import utils.ConnectionDB;
import static java.awt.MouseInfo.getPointerInfo;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */

public class MainController extends ButtonMenu implements Initializable {
    
    @FXML
    private ImageView imageHome;
    
    @FXML
    private DatePicker datePickerDebut;
    
    @FXML
    private DatePicker datePickerFin;
    
    @FXML
    private Label labelWarning;
    
    @FXML
    private AnchorPane main;
    
    @FXML
    private ImageView imageDisconnect;
    
    int [][] coor = new int[][]{
        {684, 155},{740, 152},{ 800, 148},{854, 145},
        {674, 194},{735,191},{764,189},{796,189},{826,188},{856, 185},
        {679,227},{703,227},{730,228},{755,228},{780,228},{806,228},{833,228},{861,228},
        {692,284},{714,284},{736,285},{757,284},{779,284},{801,284},{823,284},{845,284},
        {668,349},{692,347},{716,345},{741,342},{766,339},{790,337},{822,333},{910,299},{900,334},
        {675,405},{699,402},{723,400},{747,397},{772,393},{801,388},
        {708,433},{729,433},{753,433},{785,433},
        {664,471},{690,473},{716,476},{743,478},{768,481},
        {893, 144},{933,141},{970,137},
        {904,201},{934,200},{962,200},
        //56 places camping
        {698,514},{718,516},{738,516},{759,518},{779,519},{796,520},
        {793,540},{770,538},{748,538},{724,538},{700,534},
        //11 places gite ruraux
        {863,439},{879,399},
        //2 places mobilhome
        {702,192},
        //1 place chalet
        {397,442},{403,562},{377,631},
        //3 place gite collectifs
        {184,507},{196,539},{207,572},{203,506},{213,530},{225,560},
    };
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> client = clientDevis();
        if(client != null) {
            labelWarning.setText("Le(s) clients : \n\n");
            for(int i = 0; i < client.size(); i++){
                labelWarning.setText(labelWarning.getText() + " - " + client.get(i) + "\n");
            }
            labelWarning.setText(labelWarning.getText() + "\n n'ont pas confirmés leur devis.");
        }
    }
    
    public void testDate(){
        if(datePickerDebut.getValue() != null && datePickerFin.getValue() != null){
            ArrayList<Integer> idEmplacements = afficherPoints();
            
            for(int i = 0; i < 79; i++){
                if(idEmplacements.contains(i+1)){
                    System.out.println("Marche pas : " + i);
                    paint(coor[i][0], coor[i][1], Color.RED);
                }else{
                    paint(coor[i][0], coor[i][1], Color.LIGHTGREEN);
                }
            }
        }
    }
    
    public ArrayList afficherPoints(){
        try {
            java.sql.Date dateDebut = java.sql.Date.valueOf(datePickerDebut.getValue());
            java.sql.Date dateFin = java.sql.Date.valueOf(datePickerFin.getValue());
            
            if(dateFin.compareTo(dateDebut) < 0){
                labelWarning.setText("Date de fin est avant date de début");
            }else{
                labelWarning.setText("");
            }
            
            Connection database = ConnectionDB.get();
            
            String sql = "select emplacements.id from emplacements inner join locations on emplacements.id = locations.idEmplacement and "
                    +"( '" + dateDebut + "' < locations.dateFin and '" + dateFin + "' > locations.dateDebut )";

            ResultSet rs = database.createStatement().executeQuery(sql);
            ArrayList<Integer> idEmplacements = new ArrayList<>();
            
            while(rs.next()){
                int id = rs.getInt("id");
                idEmplacements.add(id);
            }
            
            
            return idEmplacements;
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;    

    }
    
    public void paint(int x, int y, Color color){
        Circle circle = new Circle(x, y, 9, color);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);
        circle.setOpacity(1);
        main.getChildren().add(circle);
    }
    
    public void ok(){
        System.out.println(getPointerInfo().getLocation());
    }
    
    public ArrayList clientDevis(){
        try {
            LocalDate twoDaysAgo = LocalDate.now().minusDays(2);
            System.out.println(twoDaysAgo);
            Connection database = ConnectionDB.get();
            String sql = "SELECT clients.nom, clients.prenom from clients "
                    + "inner join bill on clients.id = bill.idClient "
                    + "where bill.dateCree < '" + twoDaysAgo +"' and bill.payé = 0";
            Statement stmt = database.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> client = new ArrayList<>();
            while(rs.next()){
                String temp;
                temp = rs.getString("prenom") + " " + rs.getString("nom");
                client.add(temp);
            }
            return client;
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}