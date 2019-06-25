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
public class Booking {
    
    final static String START_DATE = "startDate";
    final static String END_DATE = "endDate";
    final static String LOCATION_ID = "Location_id1";  //? might be a typo in field's name
    final static String NIGHT_AMOUNT = "nightAmount";
    final static String SEASON_TYPE_ID = "SeasonTyppe_id";
    
    private int id;
    private Date startDate;
    private Date endDate;
    private int locationId;
    private int nightAmount;
    private int seasonTypeId;
    
    public Booking(){}

    public int getId() {return id;}

    public Date getStartDate() {return startDate;}

    public Date getEndDate() {return endDate;}

    public int getLocation_id() {return locationId;}

    public int getNightAmount() {return nightAmount;}

    public int getSeasonTypeId() {return seasonTypeId;}

    
    
    public Booking setId(int id) {
        this.id = id;
        return this;
    }

    public Booking setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Booking setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Booking setLocationId(int locationId) {
        this.locationId = locationId;
        return this;
    }

    public Booking setNightAmount(int nightAmount) {
        this.nightAmount = nightAmount;
        return this;
    }

    public Booking setSeasonTypeId(int seasonTypeId) {
        this.seasonTypeId = seasonTypeId;
        return this;
    }
    
    public boolean save() {
        try {
            Connection database = ConnectionDB.get();
            String sql = "INSERT INTO `Booking` (`"+START_DATE+"`, `"+END_DATE+"`, `"+LOCATION_ID+"`, `"+NIGHT_AMOUNT+"`, `"+SEASON_TYPE_ID+"`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = database.prepareStatement(sql);
            //
            ps.setDate(1, new java.sql.Date(startDate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));
            ps.setInt(3, locationId);
            ps.setInt(4, nightAmount);
            ps.setInt(5, seasonTypeId);
            //
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {e.printStackTrace();;}
        
        return false;
    }

    public static ArrayList getAll() {
        try {
            Connection database = ConnectionDB.get();
            String sql = "select * from Booking";
            ResultSet rs = database.createStatement().executeQuery(sql);
            ArrayList<Booking> list = new ArrayList<>();
            //
            while(rs.next()){
                list.add((new Booking()).setId(rs.getInt("id"))
                    .setStartDate(rs.getDate(START_DATE))
                    .setEndDate(rs.getDate(END_DATE))
                    .setLocationId(rs.getInt(LOCATION_ID))
                    .setSeasonTypeId(rs.getInt(SEASON_TYPE_ID))
                    .setNightAmount(rs.getInt(NIGHT_AMOUNT)));
            }
            //
            return list;
        } catch (Exception e) {e.printStackTrace();;}
        
        return null;
    }
    
}
