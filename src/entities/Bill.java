package entities;

import java.util.ArrayList;
import java.util.Date;


public class Bill {
    public static String BOOKING_ID = "Booking_Id";
    public static String DATE_CREATION = "dateCreation";
    public static String DATE_PAID = "datePaid";
    
    private int bookingId;
    private Date dateCreation;
    private boolean datePaid;

    public int getBookingId() {return bookingId;}

    public Date getDateCreation() {return dateCreation;}

    public boolean isDatePaid() {return datePaid;}

    public Bill(int bookingId, Date dateCreation, boolean datePaid) {
        this.bookingId = bookingId;
        this.dateCreation = dateCreation;
        this.datePaid = datePaid;
    }
    


    public Bill setBookingId(int bookingId){
        this.bookingId = bookingId;
        return this;
    }
    
    public Bill setDateCreation(Date dateCreation){
        this.dateCreation = dateCreation;
        return this;
    }
    
    public Bill setDatePaid(boolean datePaid){
        this.datePaid = datePaid;
        return this;
    }
}
