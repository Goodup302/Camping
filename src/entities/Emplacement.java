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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Eiwan
 */
public class Emplacement {
    
    final static String ID = "id";
    final static String NUMERO = "numero";
    final static String DESCRIPTION = "description";
    
    private int id;
    private int numero;
    private String description;
    
    public Emplacement(){}

    //GETTER - SETTERS
    public int getId() {return id;}
    public int getNumero() {return numero;}
    public String getDescription() {return description;}

    
    public Emplacement setId(int id) {
        this.id = id;
        return this;
    }
    public Emplacement setNumero(int numero) {
        this.numero = numero;
        return this;
    }
    public Emplacement setDescription(String description) {
        this.description = description;
        return this;
    }

    
    public boolean save() {
        try {
            Connection database = ConnectionDB.get();
            String sql = "INSERT INTO `emplacements` (`id`, `numero`, `description`) VALUES (?,?,?)";
            PreparedStatement ps = database.prepareStatement(sql);
            //
            ps.setInt(1, id);
            ps.setInt(2, numero);
            ps.setString(3, description);
            //
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {e.printStackTrace();;}
        
        return false;
    }
    
    public boolean update() {
        try {
            Connection database = ConnectionDB.get();
            String sql = "UPDATE `locations` SET `numero`=?, `description`=? WHERE id=?";
            PreparedStatement ps = database.prepareStatement(sql);
            //
            ps.setInt(1, numero);
            ps.setString(2, description);
            ps.setInt(3, id);
            //
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {e.printStackTrace();;}
        
        return false;
    }
    
    public static ArrayList getAll() {
        try {
            Connection database = ConnectionDB.get();
            String sql = "select * from emplacements";
            ResultSet rs = database.createStatement().executeQuery(sql);
            ArrayList<Emplacement> list = new ArrayList<>();
            //
            while(rs.next()){
                list.add((new Emplacement()).setId(rs.getInt("id"))
                    .setNumero(rs.getInt(NUMERO))
                    .setDescription(rs.getString(DESCRIPTION)));
            }
            //
            return list;
        } catch (Exception e) {e.printStackTrace();;}
        
        return null;
    }
}
