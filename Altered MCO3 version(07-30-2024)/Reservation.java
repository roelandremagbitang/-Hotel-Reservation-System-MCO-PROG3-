

import java.util.ArrayList;
import java.util.Calendar;

/**
 * The Reservation is a class representation of a reservation and the important information that it carries.
 * It carries information like the name of the guest attributed to the reservation, the check-in and out dates, and the chosen Room.
 * It can also provide a breakdown of the price per night given the duration of a guest's stay.
 */
public class Reservation {
    private String guestName;
    private Calendar checkInDate = Calendar.getInstance();
    private Calendar checkOutDate = Calendar.getInstance();
    private Room chosenRoom;
    private DiscountTag discountTag;
    private double totalPrice;

    /**
     * Creates a Reservation object, asking for the full details of the guest's name, check-in and out dates, and the chosen Room.
     *
     * @param guestName name of the guest
     * @param in_date date of the check-in date
     * @param in_month month of the check-in date
     * @param in_year year of the check-in date
     * @param out_date date of the check-out date
     * @param out_month month of the check-out date
     * @param out_year year of the check-out date
     * @param chosenRoom the chosen Room of the guest
     */
    public Reservation(String guestName, int in_date, int in_month, int in_year, int out_date, int out_month, int out_year, Room chosenRoom, DiscountTag discountTag, ArrayList<MonthDate>monthDateList){
        this.guestName = guestName;
        this.checkInDate.set(in_year, in_month, in_date);
        this.checkOutDate.set(out_year, out_month, out_date);
        this.chosenRoom = chosenRoom;
        this.discountTag = discountTag;
        breakdown(monthDateList);
    }

    /**
     * Returns the name of the guest.
     *
     * @return guestName
     */
    public String getGuestName(){
        return this.guestName;
    }

    /**
     * Returns the totalPrice of a Reservation.
     *
     * @return totalPrice
     */

    /**
     * Returns the check-in date of a Reservation.
     *
     * @return checkInDate
     */
    public Calendar getCheckInDate(){
        return this.checkInDate;
    }

    /**
     * Returns the check-out date of a Reservation.
     *
     * @return checkOutDate
     */
    public Calendar getCheckOutDate(){
        return this.checkOutDate;
    }

    /**
     * Returns the chosenRoom of a Reservation
     *
     * @return chosenRoom
     */
    public Room getChosenRoom(){
        return this.chosenRoom;
    }

    /**
     * Returns the discountTag of a Reservation
     *
     * @return discountTag
     */
    public DiscountTag getDiscountTag(){
        return this.discountTag;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Returns a String representation of the Reservation.
     *
     * @return reservationInfo
     */
    public String getReservationInfo(ArrayList<MonthDate> monthDateList){
        String reservationInfo = "";
        reservationInfo += "Guest Name: " + this.getGuestName() + "\n";
        reservationInfo += "Chosen room info:\n" + this.getChosenRoom().getRoomInfo();
        reservationInfo += "Check-in Date (month, day, and then year): " + this.getCheckInDate().get(Calendar.MONTH) + "-" + this.getCheckInDate().get(Calendar.DAY_OF_MONTH) + "-" + this.getCheckInDate().get(Calendar.YEAR) + "\n";
        reservationInfo += "Check-out Date (month, day, and then year): " + this.getCheckOutDate().get(Calendar.MONTH) + "-" + this.getCheckOutDate().get(Calendar.DAY_OF_MONTH) + "-" + this.getCheckOutDate().get(Calendar.YEAR) + "\n";
        reservationInfo += this.breakdown(monthDateList);
        return reservationInfo;
    }
    /**
     * Provides the total price of the reservation that has been broken up by amount of nights spent.
     *
     */
    public String breakdown(ArrayList<MonthDate> monthDateList){
        String breakdown = "";
        double price = 0;
        double total = 0;
        for(int i=this.getCheckInDate().get(Calendar.DAY_OF_MONTH); i<this.getCheckOutDate().get(Calendar.DAY_OF_MONTH); i++){
            if(i==this.getCheckInDate().get(Calendar.DAY_OF_MONTH)){
                if (this.getDiscountTag().getStay4Get1Code())
                    breakdown += "First night is free." + "\tPrice: " + price + "\n";
                else
                    price = this.getChosenRoom().getBasePrice() * monthDateList.get(i-1).getRate();
                total += price;
                breakdown += this.getCheckInDate().get(Calendar.MONTH) + "/" + monthDateList.get(i - 1).getDate() + ":\tPrice: " + price + "\n";
                i++;
            }
            price = this.getChosenRoom().getBasePrice() * monthDateList.get(i-1).getRate();
            total += price;
            breakdown += this.getCheckInDate().get(Calendar.MONTH) + "/" + monthDateList.get(i-1).getDate() + ":\tPrice: " + price + "\n";
        }
        if(this.getDiscountTag().getIWorkHereCode())
            total = total - (total * 0.10);
        if(this.getDiscountTag().getPaydayCode())
            total = total - (total * 0.07);
        if(this.getDiscountTag().getIWorkHereCode() || this.getDiscountTag().getPaydayCode() || this.getDiscountTag().getStay4Get1Code())
            breakdown += "Discounts have been applied.\n";
        breakdown += "Total Price: " + total + "\n";
        this.totalPrice = total;
        return breakdown;
    }
}
