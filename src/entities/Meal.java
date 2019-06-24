/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Logane
 */
public class Meal {
    public static String BOOKING_ID = "Booking_Id";
    public static String USER_ID = "User_Id";
    public static String MEAL_TYPE_ID = "MealType_Id";
    
    private int bookingId;
    private int userId;
    private int mealTypeId;

    public Meal(){};
    
    public Meal(int bookingId, int userId, int mealTypeId){
        this.bookingId = bookingId;
        this.userId = userId;
        this.mealTypeId = mealTypeId;
        
    }
    
    public Meal setBookingd(int bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public Meal setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public Meal setMealTypeId(int mealTypeId) {
        this.mealTypeId = mealTypeId;
        return this;
    }

    public int getBookingId() { return bookingId;}

    public int getUserId() {return userId;}

    public int getMealTypeId() {return mealTypeId;}
    
}
