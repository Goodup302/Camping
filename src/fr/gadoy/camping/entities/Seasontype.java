package fr.gadoy.camping.entities;
// Generated 27 juin 2019 16:28:57 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Seasontype generated by hbm2java
 */
@Entity
@Table(name="seasontype"
    ,catalog="camping"
)
public class Seasontype  implements java.io.Serializable {


     private int id;
     private String name;
     private Set<Booking> bookings = new HashSet<Booking>(0);

    public Seasontype() {
    }

	
    public Seasontype(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Seasontype(int id, String name, Set<Booking> bookings) {
       this.id = id;
       this.name = name;
       this.bookings = bookings;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="name", nullable=false)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="seasontype")
    public Set<Booking> getBookings() {
        return this.bookings;
    }
    
    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }




}

