/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Administrateur
 */
public class Client {
    
    final public static String DATE_NAISSANCE = "dateNaissance"; 
    final public static String MAIL = "mail";
    final public static String NOM = "nom";
    final public static String PRENOM = "prenom";
    final public static String NUM_TEL = "numTel";
    final public static String NUM_FIX = "numFix";
    final public static String ADRESSE = "adresse";
    final public static String CIVILITE = "Civilité";
    
    private int id;
    private String nom;
    private String prenom;
    private String numTel;
    private String numFix;
    private String mail;
    private String adresse;
    private Date dateNaissance;
    private String civilite;
    
    public Client(){}

    //GETTER - SETTERS
    
    public int getId() {return id;}
    public String getNom() {return nom;}
    public String getPrenom() {return prenom;}
    public String getNumTel() {return numTel;}
    public String getNumFix() {return numFix;}
    public String getMail() {return mail;}
    public String getAdresse() {return adresse;}
    public Date getNaissance() {return dateNaissance;}
    public String getCivilite() {return civilite;}
    
    
    public Client(String cnom, String cprenom, String cnumTel, String cnumFix,
                    String cmail, String cadresse, Date cdateNaissance){
        this.nom = cnom;
        this.prenom = cprenom;
        this.numTel = cnumTel;
        this.numFix = cnumFix;
        this.mail = cmail;
        this.adresse = cadresse;
        this.dateNaissance = cdateNaissance;
    }

    public Client setId(int id) {
        this.id = id;
        return this;
    }
    
    public Client setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public Client setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public Client setNumTel(String numTel) {
        this.numTel = numTel;
        return this;
    }

    public Client setNumFix(String numFix) {
        this.numFix = numFix;
        return this;
    }

    public Client setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public Client setAdresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public Client setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
        return this;
    }

    public Client setCivilite(String civilite) {
        this.civilite = civilite;
        return this;
    }
    
        public boolean save() {
        try {
            Connection database = ConnectionDB.get();
            String sql = "INSERT INTO `clients` (`dateNaissance`, `mail`, `nom`, `prenom`, `numTel`, `numFix`, `adresse`, `Civilité`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = database.prepareStatement(sql);
            //
            ps.setDate(1, new java.sql.Date(dateNaissance.getTime()));
            ps.setString(2, mail);
            ps.setString(3, nom);
            ps.setString(4, prenom);
            ps.setString(5, numTel);
            ps.setString(6, numFix);
            ps.setString(7, adresse);
            ps.setString(8, civilite);
            //
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {e.printStackTrace();;}
        
        return false;
    }
    
    public boolean update() {
        try {
            Connection database = ConnectionDB.get();
            String sql = "UPDATE `locations` SET `dateNaissance`=?, `mail`=?, `nom`=?, `prenom`=?, `numTel`=?, `numFix`=?, `adresse`=?, `Civilité`=? WHERE id=?";
            PreparedStatement ps = database.prepareStatement(sql);
            //
            ps.setDate(1, new java.sql.Date(dateNaissance.getTime()));
            ps.setString(2, mail);
            ps.setString(3, nom);
            ps.setString(4, prenom);
            ps.setString(5, numTel);
            ps.setString(6, numFix);
            ps.setString(7, adresse);
            ps.setString(8, civilite);
            ps.setInt(9, id);
            //
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {e.printStackTrace();;}
        
        return false;
    }
    
    public static ArrayList getAll() {
        try {
            String sql = "select * from clients";
            ResultSet rs = ConnectionDB.get().createStatement().executeQuery(sql);
            ArrayList<Client> list = new ArrayList<>();
            //
            while(rs.next()){
                list.add((new Client()).setId(rs.getInt("id"))
                    .setDateNaissance(rs.getDate(DATE_NAISSANCE))
                    .setMail(rs.getString(MAIL))
                    .setNom(rs.getString(NOM))
                    .setPrenom(rs.getString(PRENOM))
                    .setNumTel(rs.getString(NUM_TEL))
                    .setAdresse(rs.getString(ADRESSE))
                    .setCivilite(rs.getString(CIVILITE))
                    .setNumFix(rs.getString(NUM_FIX)));
            }
            //
            return list;
        } catch (Exception e) {e.printStackTrace();;}
        
        return null;
    }
    
}
