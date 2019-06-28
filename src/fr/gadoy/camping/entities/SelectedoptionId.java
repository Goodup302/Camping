package fr.gadoy.camping.entities;
// Generated 27 juin 2019 16:28:57 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SelectedoptionId generated by hbm2java
 */
@Embeddable
public class SelectedoptionId  implements java.io.Serializable {


     private int optionId;
     private int bookingId;

    public SelectedoptionId() {
    }

    public SelectedoptionId(int optionId, int bookingId) {
       this.optionId = optionId;
       this.bookingId = bookingId;
    }
   


    @Column(name="Option_id", nullable=false)
    public int getOptionId() {
        return this.optionId;
    }
    
    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }


    @Column(name="Booking_id", nullable=false)
    public int getBookingId() {
        return this.bookingId;
    }
    
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SelectedoptionId) ) return false;
		 SelectedoptionId castOther = ( SelectedoptionId ) other; 
         
		 return (this.getOptionId()==castOther.getOptionId())
 && (this.getBookingId()==castOther.getBookingId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getOptionId();
         result = 37 * result + this.getBookingId();
         return result;
   }   


}

