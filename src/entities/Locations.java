/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import utils.ConnectionDB;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Eiwan
 */
public class Locations {
    
    final static String ID_EMPLACEMENT = "idEmplacement";
    final static String DATE_DEBUT = "dateDebut";
    final static String DATE_FIN = "dateFin";
    final static String DISPONIBLE = "disponible";
    
    private int id;
    private Date dateDebut;
    private Date dateFin;
    private int idEmplacement;
    private boolean disponible;
    private int idClient;
    
    public Locations(){}

    //GETTER - SETTERS
    public int getId() {return id;}
    public Date getDateDebut() {return dateDebut;}
    public Date getDateFin() {return dateFin;}
    public int getIdEmplacement() {return idEmplacement;}
    public boolean getDisponible() {return disponible;}
    public int getIdClient() {return idClient;}
    
    public Locations setId(int id) {
        this.id = id;
        return this;
    }
    public Locations setDateDebut(Date date) {
        this.dateDebut = date;
        return this;
    }
    public Locations setDateFin(Date date) {
        this.dateFin = date;
        return this;
    }
    public Locations setIdEmplacement(int id) {
        this.idEmplacement = id;
        return this;
    }
    public Locations setDisponible(boolean disponible) {
        this.disponible = disponible;
        return this;
    }
      public Locations setIdClient(int id) {
        this.idClient = id;
        return this;
    }
    
    public boolean save() {
        try {
            Connection database = ConnectionDB.get();
            String sql = "INSERT INTO `locations` (`dateDebut`, `dateFin`, `idClient`, `idEmplacement`, `disponible`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = database.prepareStatement(sql);
            //
            ps.setDate(1, new java.sql.Date(dateDebut.getTime()));
            ps.setDate(2, new java.sql.Date(dateFin.getTime()));
            ps.setInt(3, idClient);
            ps.setInt(4, idEmplacement);
            ps.setBoolean(5, disponible);
            //
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {e.printStackTrace();;}
        
        return false;
    }
    
    public boolean update() {
        try {
            Connection database = ConnectionDB.get();
            String sql = "UPDATE `locations` SET `dateDebut`=?, `dateFin`=?, `idEmplacement`=?, `disponible`=?, `idClient`=? WHERE id=?";
            PreparedStatement ps = database.prepareStatement(sql);
            //
            ps.setDate(1, new java.sql.Date(dateDebut.getTime()));
            ps.setDate(2, new java.sql.Date(dateFin.getTime()));
            ps.setInt(3, idEmplacement);
            ps.setBoolean(4, disponible);
            ps.setInt(5, idClient);
            ps.setInt(6, id);
            //
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {e.printStackTrace();;}
        
        return false;
    }
    
    public static ArrayList getAll() {
        try {
            Connection database = ConnectionDB.get();
            String sql = "select * from locations";
            ResultSet rs = database.createStatement().executeQuery(sql);
            ArrayList<Locations> list = new ArrayList<>();
            //
            while(rs.next()){
                list.add((new Locations()).setId(rs.getInt("id"))
                    .setDateDebut(rs.getDate(DATE_DEBUT))
                    .setDateFin(rs.getDate(DATE_FIN))
                    .setIdEmplacement(rs.getInt(ID_EMPLACEMENT))
                    .setDisponible(rs.getBoolean(DISPONIBLE)));
            }
            //
            return list;
        } catch (Exception e) {e.printStackTrace();;}
        
        return null;
    }
}
