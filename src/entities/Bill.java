package entities;


public class Bill {
    public static String BOOKING_ID = "Booking_Id";
    public static String DATE_CREATION = "dateCreation";
    public static String DATE_PAID = "datePaid";
    
    private int bookingId;
    private int dateCreation;
    private boolean datePaid;
    
    public int getBookingId() {return bookingId;}

    public int getDateCreation() {return dateCreation;}
    
    public boolean getDatePaid() {return datePaid;}

    public Bill setBookingId(int bookingId){
        this.bookingId = bookingId;
        return this;
    }
    
    public Bill setDateCreation(int dateCreation){
        this.dateCreation = dateCreation;
        return this;
    }
    
    public Bill setDatePaid(boolean datePaid){
        this.datePaid = datePaid;
        return this;
    }
}
