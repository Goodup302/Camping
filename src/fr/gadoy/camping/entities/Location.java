package fr.gadoy.camping.entities;
// Generated 27 juin 2019 16:28:57 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Location generated by hbm2java
 */
@Entity
@Table(name="location"
    ,catalog="camping"
)
public class Location  implements java.io.Serializable {


     private Integer id;
     private Typelocation typelocation;
     private int number;
     private Integer roomAmount;
     private Set<Booking> bookings = new HashSet<Booking>(0);
     private Set<Option> options = new HashSet<Option>(0);

    public Location() {
    }

	
    public Location(Typelocation typelocation, int number) {
        this.typelocation = typelocation;
        this.number = number;
    }
    public Location(Typelocation typelocation, int number, Integer roomAmount, Set<Booking> bookings, Set<Option> options) {
       this.typelocation = typelocation;
       this.number = number;
       this.roomAmount = roomAmount;
       this.bookings = bookings;
       this.options = options;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TypeLocation_id", nullable=false)
    public Typelocation getTypelocation() {
        return this.typelocation;
    }
    
    public void setTypelocation(Typelocation typelocation) {
        this.typelocation = typelocation;
    }

    
    @Column(name="number", nullable=false)
    public int getNumber() {
        return this.number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }

    
    @Column(name="roomAmount")
    public Integer getRoomAmount() {
        return this.roomAmount;
    }
    
    public void setRoomAmount(Integer roomAmount) {
        this.roomAmount = roomAmount;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="location")
    public Set<Booking> getBookings() {
        return this.bookings;
    }
    
    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="location_has_option", catalog="camping", joinColumns = { 
        @JoinColumn(name="Location_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="Option_id", nullable=false, updatable=false) })
    public Set<Option> getOptions() {
        return this.options;
    }
    
    public void setOptions(Set<Option> options) {
        this.options = options;
    }


//    public static ArrayList<Location> getFreeBetweenDates(Date startDate, Date endDate, int locationType) {
//    try {
//        Connection database = ConnectionDB.get();
//        String sql = "select * from Location"
//                + "inner join Booking on Locations.id = Booking.TypeLocation_id"
//                + "where '?' < Booking."+Booking.END_DATE+" and '?' > Booking."+Booking.START_DATE
//                + "andWhere Location."+ Location.TYPE_LOCATION_ID +" = 1";
//        if (locationType > 0) {
//            sql += "inner join TypeLocation on TypeLocation.id = ?"
//                + "andWhere Location."+ Location.TYPE_LOCATION_ID +" = 1";
//        }
//        PreparedStatement ps = database.prepareStatement(sql);
//        //
//        ps.setDate(1, new java.sql.Date(startDate.getTime()));
//        ps.setDate(2, new java.sql.Date(endDate.getTime()));
//        ps.setInt(3, locationType);
//        //
//        ResultSet rs = ps.executeQuery();
//        ps.close();
//
//
//
//        ArrayList<Location> list = new ArrayList<>();
//        //
//        while(rs.next()){                                                               //? besoin du resultat inverse
//            list.add((new Location()).setId(rs.getInt("id"))
//                .setNumero(rs.getInt(NUMBER))
//                .setDescription(rs.getString(TYPE_LOCATION_ID)));
//        }
//        //
//        return list;
//    } catch (Exception e) {e.printStackTrace();;}
//
//    return null;
//}

}


