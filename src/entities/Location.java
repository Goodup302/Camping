/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import utils.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Eiwan
 */
public class Location {
    
    final static String ID = "id";
    final static String NUMBER = "number";
    final static String TYPE_LOCATION_ID = "TypeLocation_id";
    
    private int id;
    private int numero;
    private String description;
    
    public Location(){}

    //GETTER - SETTERS
    public int getId() {return id;}
    public int getNumero() {return numero;}
    public String getDescription() {return description;}

    
    public Location setId(int id) {
        this.id = id;
        return this;
    }
    public Location setNumero(int numero) {
        this.numero = numero;
        return this;
    }
    public Location setDescription(String description) {
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
            ArrayList<Location> list = new ArrayList<>();
            //
            while(rs.next()){
                list.add((new Location()).setId(rs.getInt("id"))
                    .setNumero(rs.getInt(NUMBER))
                    .setDescription(rs.getString(TYPE_LOCATION_ID)));
            }
            //
            return list;
        } catch (Exception e) {e.printStackTrace();;}
        
        return null;
    }
    
    public static ArrayList getLocationsFreeBetweenDates(Date startDate, Date endDate){
        try {
            Connection database = ConnectionDB.get();
            String sql = "select * from Location"
                    + "inner join Booking on Locations.id = Booking.TypeLocation_id "
                    + "where '" + startDate + "' < Booking.endDate and '" + endDate + "' > Booking.startDate";
            ResultSet rs = database.createStatement().executeQuery(sql);
            ArrayList<Location> list = new ArrayList<>();
            //
            while(rs.next()){
                list.add((new Location()).setId(rs.getInt("id"))
                    .setNumero(rs.getInt(NUMBER))
                    .setDescription(rs.getString(TYPE_LOCATION_ID)));
            }
            //
            return list;
        } catch (Exception e) {e.printStackTrace();;}
        
        return null;
    }
    
}
