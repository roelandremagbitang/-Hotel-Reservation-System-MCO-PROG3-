

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
    public Reservation(String guestName, int in_date, int in_month, int in_year, int out_date, int out_month, int out_year, Room chosenRoom){
        this.guestName = guestName;
        this.checkInDate.set(in_year, in_month, in_date);
        this.checkOutDate.set(out_year, out_month, out_date);
        this.chosenRoom = chosenRoom;
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
    public double getTotalPrice(){
        return this.chosenRoom.getBasePrice() * (this.checkOutDate.get(Calendar.DAY_OF_MONTH) - this.checkInDate.get(Calendar.DAY_OF_MONTH));
    }

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
     * Provides the total price of the reservation that has been broken up by amount of nights spent.
     *
     */
    public void breakdown(){
        int nights = this.checkOutDate.get(Calendar.DAY_OF_MONTH) - this.checkInDate.get(Calendar.DAY_OF_MONTH);
        System.out.println("Nights\tPrice per Night\n");
        for(int i = 1; i <= nights; i++){
            System.out.printf("Night %d\t%.2f PHP\n", i, this.chosenRoom.getBasePrice());
        }
        System.out.printf("TOTAL: %.2f PHP\n", nights * this.chosenRoom.getBasePrice());
    }
}
