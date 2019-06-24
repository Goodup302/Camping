package controlleurs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import camping.ButtonMenu;
import camping.ConnectionDB;
import camping.Pdf;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

public class ReservationController extends ButtonMenu implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private DatePicker datePickerDebut;
    
    @FXML
    private TextField textFieldClient;
    
    @FXML
    private Button buttonCreerReservation;
    
    @FXML
    private DatePicker datePickerFin;
    
    @FXML
    private ToggleButton toggleAnimaux;
    
    @FXML
    private ToggleButton toggleKitBébé;
    
    @FXML
    private ToggleButton toggleLaveLinge;
    
    @FXML
    private ToggleButton toggleSecheLinge;
    
    @FXML
    private ToggleButton toggleDraps140x190;
    
    @FXML
    private ToggleButton toggleDraps90x190;
    
    @FXML
    private ToggleButton toggleServiettes;
    
    @FXML
    private Pane paneOptions;
    
    @FXML
    private ComboBox comboFormule;
    
    @FXML
    private ComboBox comboBoxType;
    
    @FXML
    private ComboBox comboBoxNumero;
    
    @FXML
    private Label labelNumero;
    
    @FXML
    private Label labelPrix;
    
    @FXML
    private Label labelAttention;
    
    @FXML
    private Label labelPersonne;
    
    @FXML
    private Label labelPlus13Ans;
    
    @FXML
    private Label labelMoins13Ans;
    
    @FXML
    private Label labelNombreAdultes;
    
    @FXML
    private Label labelNombreMoins12Ans;
    
    @FXML
    private Slider sliderPlus13Ans;
    
    @FXML
    private Slider sliderNombreAdulte;
    
    @FXML
    private Slider sliderMoins13Ans;
    
    @FXML
    private Pane paneGite;
    
    @FXML
    private ToggleButton toggleDraps;
    
    @FXML
    private Pane paneCamping;
    
    @FXML
    private Pane paneGiteCollectif;
    
    @FXML
    private Slider sliderPersonne;
    
    @FXML
    private Slider sliderNombreMoins12Ans;
    
    @FXML
    private Label labelClientWarning;
    
    @FXML
    private ToggleButton toggleHandicape;
    
    @FXML
    private Label labelWarning;
    
    @FXML
    private Button buttonCreerFacture;
    /*
    @FXML
    private Button buttonCreerReservation;
    */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            comboFormule.getItems().setAll("Classique", "Confort", "Grand Confort");
            
            
            Connection database = ConnectionDB.get();
            String sql = "select distinct description from emplacements ";
            ResultSet rs = database.createStatement().executeQuery(sql);
                
            ArrayList<String> desc = new ArrayList<>();
            
            while(rs.next()){
                comboBoxType.getItems().addAll(rs.getString("description"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    public void buttonCreerFacture() throws DocumentException, BadElementException, IOException{
        Pdf test = new Pdf();
        test.create();
    }
    
    public void sliderNombreMoins12Ans(){
        labelNombreMoins12Ans.setText(""+ Math.round(sliderNombreMoins12Ans.getValue()));
    }
    
    public void sliderNombreAdulte(){
        labelNombreAdultes.setText(""+ Math.round(sliderNombreAdulte.getValue()));
    }
    
    public void buttonCreerReservation(){
        try {
            String client = textFieldClient.getText();
            Connection database = ConnectionDB.get();
            String sql = "SELECT nom from clients where nom = '" + client + "'";
            ResultSet rs = database.createStatement().executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int nbColumn = rsmd.getColumnCount();
            ArrayList<String> sqlResult = new ArrayList<>(nbColumn);
            while(rs.next()){
                int i = 1;
                while(i <= nbColumn){
                    sqlResult.add(rs.getString(i++));
                    
                }
            }
            
            if(sqlResult.isEmpty()){
                labelClientWarning.setText("Client non-trouvé. Veuillez le créer");
            }else{
                labelClientWarning.setText("");
                java.sql.Date sqlDateDebut = java.sql.Date.valueOf(datePickerDebut.getValue());
                java.sql.Date sqlDateFin = java.sql.Date.valueOf(datePickerFin.getValue());
                int idEmplacement = (int) comboBoxNumero.getValue();
                int idClient = 2;
                String sqlClient = "select id from clients where nom = '" + textFieldClient.getText() + "'";
                ResultSet rs1 = database.createStatement().executeQuery(sqlClient);
                while(rs1.next()){
                    idClient = rs1.getInt("id");
                }
                String sql1 = "INSERT INTO locations (dateDebut, dateFin, idClient, idEmplacement) "
                        + "Values ('"+sqlDateDebut+"','"+sqlDateFin+"','"+idClient+"','"+idEmplacement+"')";
                Statement statement = database.createStatement();
                statement.executeUpdate(sql1);
            }
            
            System.out.println(sqlResult); 
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sliderPlus13Ans(){
        labelPlus13Ans.setText(""+ Math.round(sliderPlus13Ans.getValue()));
    }
    
    public void sliderMoins13Ans(){
        labelMoins13Ans.setText(""+ Math.round(sliderMoins13Ans.getValue()));
    }
    
    public void sliderPersonne(){
        labelPersonne.setText(""+Math.round(sliderPersonne.getValue()));
    }
    
    public void devis(){
        String type = comboBoxType.getValue().toString();
        int duree = (int) getDaysBetweenDates(); 
        //Devis devis = new Devis();
        
    }
    
    public boolean testDatePresente(){
        if(datePickerFin.getValue() != null && datePickerDebut.getValue() != null){
            return true;
        }else{
            return false;
        }
    }
    
    public long getDaysBetweenDates(){
            return DAYS.between(datePickerDebut.getValue(), datePickerFin.getValue());
    }

    public void getNoms(){
        try {
            java.sql.Date dateDebut = java.sql.Date.valueOf(datePickerDebut.getValue());
            java.sql.Date dateFin = java.sql.Date.valueOf(datePickerFin.getValue());
            
            Connection database = ConnectionDB.get();
            
            String sql = "select DISTINCT "
                    + "emplacements.description "
                    + "from emplacements "
                    + "left outer join locations "
                    + "on emplacements.id=locations.idEmplacement " 
                    + "where locations.idEmplacement is null ";
            //+ "and (('" + dateDebut + "' < locations.dateDebut and '" + dateFin + "' < locations.dateDebut) "
            // + "or('" + dateDebut + "' > locations.dateFin and '" + dateFin + "' > locations.dateDebut))";

            ResultSet rs = database.createStatement().executeQuery(sql);
            
            comboBoxType.getItems().clear();
            while(rs.next()){
                comboBoxType.getItems().add(rs.getString("description"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    }

    public ArrayList getIdPlusPrecis(){
        try {
            System.out.println("fonction getIdPlusPrecis");
            java.sql.Date dateDebut = java.sql.Date.valueOf(datePickerDebut.getValue());
            java.sql.Date dateFin = java.sql.Date.valueOf(datePickerFin.getValue());
            
            Connection database = ConnectionDB.get();
            String value = (String) comboBoxType.getValue();
            
            String sql = "select emplacements.id from emplacements inner join locations on emplacements.id = locations.idEmplacement and "
                    +"( '" + dateDebut + "' < locations.dateFin and '" + dateFin + "' > locations.dateDebut )  and emplacements.description = 'camping' ";

            ResultSet rs = database.createStatement().executeQuery(sql);
            ArrayList<Integer> idEmplacements = new ArrayList<>();
            
            while(rs.next()){
                int id = rs.getInt("id");
                System.out.println("id recupéré : " + id);
                idEmplacements.add(id);
            }
            
            return idEmplacements;
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;

    }
    
    public ArrayList getId(){
        try {
            java.sql.Date dateDebut = java.sql.Date.valueOf(datePickerDebut.getValue());
            java.sql.Date dateFin = java.sql.Date.valueOf(datePickerFin.getValue());
            
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
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;

    }
    
    public int count(String col, String word){
        try {
            Connection database = ConnectionDB.get();
            String sql = "SELECT COUNT(" + col + ") FROM emplacements  where description = '" + word + "'";
            ResultSet rs = database.createStatement().executeQuery(sql);
            int result = 0;
            if(rs.next()){
                result = rs.getInt(1);
                return  result;
            }
            return  result;
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public void testDate(){
        String value = (String) comboBoxType.getValue();
        
        if(testDatePresente() && value != null && value != null && (value.equals("appartement F2") || value.equals("appartement F3") || value.equals("gite rural"))){
            long daysBetween = getDaysBetweenDates();
            if((value.equals("appartement F2")|| value.equals("appartement F3"))&& daysBetween < 31){
                    datePickerDebut.setValue(null);
                    datePickerFin.setValue(null);
                    comboBoxNumero.setValue(null);
                    labelAttention.setText("Attention, en choisissant 'Appartement', \n la durée minimal de reservation \n est de un mois");
            }else if(testDatePresente()){
                String PremiereDate = datePickerDebut.getValue().toString();
                String mois = PremiereDate.substring(5,7);
                if((value.equals("gite rural"))&& (mois.equals("07") || mois.equals("08")) && daysBetween < 7){
                    System.out.println("Dans le test");
                    datePickerDebut.setValue(null);
                    datePickerFin.setValue(null);
                    comboBoxNumero.setValue(null);
                    labelAttention.setText("Attention, en choisissant 'Gite', \n la durée minimal de reservation \n en Haute période est de une semaine");
                }
            }      
        }
        
        
        
        if(testDatePresente() && value == null){
            getNoms();
            ArrayList<Integer> idEmplacements = getId();
            comboBoxNumero.getItems().clear();

            for(int i = 1; i <= 79; i++){
                if(idEmplacements.contains(i)){
                    System.out.println("Exclu : " + i);
                }else{
                    comboBoxNumero.getItems().addAll(i);
                }
            } 
        }
        
        if(testDatePresente() && value != null){
            ArrayList<Integer> idEmplacements = getIdPlusPrecis();
            comboBoxNumero.getItems().clear();
            String word = comboBoxType.getValue().toString();
            for(int i = 1; i <= count("description", word); i++){
                if(idEmplacements.contains(i)){
                    System.out.println("Exclu : " + i);
                }else{
                    comboBoxNumero.getItems().addAll(i);
                }
            }             
        }
        
    }
    
    public void comboChanged(ActionEvent event){
        testDate();
        String value = (String) comboBoxType.getValue();
        System.out.println("."+value+".");
        comboBoxNumero.setLayoutY(353);
        labelNumero.setLayoutY(332);
        paneGite.setVisible(false);
        paneGite.setLayoutY(380);
        paneGiteCollectif.setVisible(false);
        paneCamping.setVisible(false);
        labelPrix.setVisible(false);
        labelPrix.setLayoutX(580);
        labelPrix.setLayoutY(500);
        toggleHandicape.setVisible(false);
        paneOptions.setVisible(true);
        toggleDraps.setVisible(false);
        if(value != null){
            labelPrix.setVisible(true);
            if(value.equals("appartement F2") || value.equals("appartement F3")){
                labelPrix.setLayoutY(480);
                labelPrix.setLayoutX(214);
                paneOptions.setVisible(false);
            }else if(value.equals("camping")){
                paneCamping.setVisible(true);
            }else if(value.equals("chalet") || value.equals("mobilhome") || value.equals("gite rural")){
                paneGite.setVisible(true);
                if(value.equals("gite rural")){ 
                    comboBoxNumero.setLayoutY(399);
                    paneGite.setLayoutY(470);
                    labelNumero.setLayoutY(375);
                    toggleHandicape.setVisible(true);
                }   
            }else if(value.equals("gite collectif")){
                toggleDraps.setVisible(true);
                paneGiteCollectif.setVisible(true);
            }
        }
    }
    
}