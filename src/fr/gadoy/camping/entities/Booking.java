package fr.gadoy.camping.entities;
// Generated 27 juin 2019 16:28:57 by Hibernate Tools 4.3.1


import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Booking generated by hbm2java
 */
@Entity
@Table(name="booking"
    ,catalog="camping"
)
public class Booking  implements java.io.Serializable {


     private Integer id;
     private Bill bill;
     private Location location;
     private Seasontype seasontype;
     private Date startDate;
     private Date endDate;
     private int nightAmount;
     private Set<User> users = new HashSet<User>(0);
     private Set<Meal> meals = new HashSet<Meal>(0);
     private Set<Selectedoption> selectedoptions = new HashSet<Selectedoption>(0);

    public Booking() {
    }

	
    public Booking(Bill bill, Location location, Seasontype seasontype, Date startDate, Date endDate, int nightAmount) {
        this.bill = bill;
        this.location = location;
        this.seasontype = seasontype;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nightAmount = nightAmount;
    }
    public Booking(Bill bill, Location location, Seasontype seasontype, Date startDate, Date endDate, int nightAmount, Set<User> users, Set<Meal> meals, Set<Selectedoption> selectedoptions) {
       this.bill = bill;
       this.location = location;
       this.seasontype = seasontype;
       this.startDate = startDate;
       this.endDate = endDate;
       this.nightAmount = nightAmount;
       this.users = users;
       this.meals = meals;
       this.selectedoptions = selectedoptions;
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
    @JoinColumn(name="Bill_id", nullable=false)
    public Bill getBill() {
        return this.bill;
    }
    
    public void setBill(Bill bill) {
        this.bill = bill;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Location_id1", nullable=false)
    public Location getLocation() {
        return this.location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SeasonType_id", nullable=false)
    public Seasontype getSeasontype() {
        return this.seasontype;
    }
    
    public void setSeasontype(Seasontype seasontype) {
        this.seasontype = seasontype;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="startDate", nullable=false, length=10)
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="endDate", nullable=false, length=10)
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    @Column(name="nightAmount", nullable=false)
    public int getNightAmount() {
        return this.nightAmount;
    }
    
    public void setNightAmount(int nightAmount) {
        this.nightAmount = nightAmount;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="user_has_booking", catalog="camping", joinColumns = { 
        @JoinColumn(name="Booking_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="User_id", nullable=false, updatable=false) })
    public Set<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="booking")
    public Set<Meal> getMeals() {
        return this.meals;
    }
    
    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="booking")
    public Set<Selectedoption> getSelectedoptions() {
        return this.selectedoptions;
    }
    
    public void setSelectedoptions(Set<Selectedoption> selectedoptions) {
        this.selectedoptions = selectedoptions;
    }




}


