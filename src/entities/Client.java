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
 *
 * @author Administrateur
 */
public class Client {
    final public static String FIRST_NAME = "firstName";
    final public static String LAST_NAME = "lastName";
    final public static String PHONE = "phone";
    final public static String MOBILE = "mobile";
    final public static String BIRTHDATE = "birthDate";
    final public static String EMAIL = "email";
    final public static String STREET = "street";
    final public static String POSTAL_CODE = "postalCode";
    final public static String CITY = "city";
    final public static String CIVILITY = "civility";
    final public static String NUMBER_PLATE = "numberPlate";
    
    
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String mobile;
    private Date birthday;
    private String email;
    private String street;
    private String postalCode;
    private String city;
    private String civility;
    private String numberPlate;
    private String address = street + " " + postalCode + " " + city;
    
    
    public Client(){}

    //GETTER - SETTERS
    
    public int getId() {return id;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getPhone() {return phone;}
    public String getMobile() {return mobile;}
    public Date getBirthday() {return birthday;}
    public String getEmail() {return email;}
    public String getPostalCode() {return postalCode;}
    public String getCity() {return city;}
    public String getCivility() {return civility;}
    public String getNumberPlate() {return numberPlate;}
    public String getAddress() {return address;}
    
    
    public Client(String firstName, String lastName, String phone, String mobile,
                    Date birthday, String email, String postalCode, String city,
                    String civility, String numberPlate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.mobile = mobile;
        this.birthday = birthday;
        this.email = email;
        this.postalCode = postalCode;
        this.city = city;
        this.civility = civility;
        this.numberPlate = numberPlate;
        
    }

    public Client setId(int id) {
        this.id = id;
        return this;
    }

    public Client setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Client setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Client setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Client setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public Client setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public Client setEmail(String email) {
        this.email = email;
        return this;
    }

    public Client setStreet(String street) {
        this.street = street;
        return this;
    }

    public Client setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public Client setCity(String city) {
        this.city = city;
        return this;
    }

    public Client setCivility(String civility) {
        this.civility = civility;
        return this;
    }

    public Client setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
        return this;
    }
    
        public boolean save() {
        try {
            Connection database = ConnectionDB.get();
            String sql = "INSERT INTO `clients` (`"+FIRST_NAME+"`,`"+LAST_NAME+"`, `"+PHONE+"`, `"+MOBILE+"`, `"+BIRTHDATE+"`, `"+EMAIL+"`, "
                    + "`"+STREET+"`, `"+POSTAL_CODE+"`, `"+CITY+"`, `"+CIVILITY+"`, `"+NUMBER_PLATE+"`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = database.prepareStatement(sql);
            //
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, phone);
            ps.setString(4, mobile);
            ps.setDate(5, new java.sql.Date(birthday.getTime()));
            ps.setString(6, email);
            ps.setString(7, street);
            ps.setString(8, postalCode);
            ps.setString(9, city);
            ps.setString(10, civility);
            ps.setString(11, numberPlate);
            //
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {e.printStackTrace();;}
        
        return false;
    }
    
    public boolean update() {
        try {
            Connection database = ConnectionDB.get();
            String sql = "UPDATE `locations` SET `"+FIRST_NAME+"`=?, `"+LAST_NAME+"`=?, `"+PHONE+"`=?, "
                    + "`"+MOBILE+"`=?, `"+BIRTHDATE+"`=?, `"+EMAIL+"`=?, `"+STREET+"`=?, `"+POSTAL_CODE+"`=? "
                    + ", `"+CITY+"`=? , `"+CIVILITY+"`=? , `"+NUMBER_PLATE+"`=? WHERE id=?";
            PreparedStatement ps = database.prepareStatement(sql);
            //
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, phone);
            ps.setString(4, mobile);
            ps.setDate(5, new java.sql.Date(birthday.getTime()));
            ps.setString(6, email);
            ps.setString(7, street);
            ps.setString(8, postalCode);
            ps.setString(9, city);
            ps.setString(10, civility);
            ps.setString(11, numberPlate);
            ps.setInt(12, id);
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
                    .setBirthday(rs.getDate(BIRTHDATE))
                    .setEmail(rs.getString(EMAIL))
                    .setFirstName(rs.getString(FIRST_NAME))
                    .setLastName(rs.getString(LAST_NAME))
                    .setPhone(rs.getString(PHONE))
                    .setMobile(rs.getString(MOBILE))
                    .setStreet(rs.getString(STREET))
                    .setCity(rs.getString(CITY))
                    .setCivility(rs.getString(CIVILITY))
                    .setNumberPlate(rs.getString(NUMBER_PLATE))
                    .setPostalCode(rs.getString(POSTAL_CODE)));
            }
            //
            return list;
        } catch (Exception e) {e.printStackTrace();;}
        
        return null;
    }
    
}
