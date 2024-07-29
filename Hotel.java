

import java.util.ArrayList;

/**
 * The Hotel class essentially captures a hotel that contains information about its name, room capacity, and the Rooms and Reservations themselves.
 * The Hotel class should be able to give their monthly earnings as it is something that is specific to the Hotel itself.
 * The Hotel class also has the responsibility to set its own pricing for every Room, its own name, and its capacity.
 */
public class Hotel {
    private String hotelName;
    private int numberOfRooms;
    private ArrayList<Room> roomList;
    private ArrayList<Reservation> reservationList;
    private ArrayList<MonthDate> monthDateList;

    /**
     * Constructs a Hotel object that includes the name of the Hotel and the initial capacity of the Hotel.
     *
     *
     * @param roomCount initial capacity of the Hotel. roomCount must be from 1-50 for it to be considered a valid input
     * @param hotelName name of the Hotel. hotelName must fall under the naming convention of no spaces to be considered valid.
     */
    public Hotel(int roomCount, String hotelName){
        //uses the set methods so that the conditions are also met upon instantiation
        setNumberOfRooms(roomCount);
        setHotelName(hotelName);
        this.roomList = new ArrayList<Room>(this.numberOfRooms);
        this.reservationList = new ArrayList<Reservation>();
        this.monthDateList = new ArrayList<>();
        for(int i=0; i<31; i++){
            MonthDate monthDate = new MonthDate(i+1);
            this.monthDateList.add(i, monthDate);
        }
    }

    /**
     * Constructs a Hotel Class that specifies the name of the Hotel. The room capacity is set to 1 by default.
     *
     * @param hotelName name of the Hotel. hotelName must fall under the naming convention of no spaces to be considered valid.
     */
    public Hotel(String hotelName){
        setNumberOfRooms(1);
        setHotelName(hotelName);
        this.roomList = new ArrayList<Room>(this.numberOfRooms);
        this.reservationList = new ArrayList<Reservation>();
    }

    /**
     * Gives the monthly earnings of the Hotel by going through price of all the Reservations that Hotel has for the month.
     *
     * @return the total computed monthly earnings of the Hotel
     */
    public double getMonthlyEarnings(){
        double sum = 0;
        for(int i=0; i<this.reservationList.size(); i++)
            sum+=this.reservationList.get(i).getTotalPrice();
        return sum;
    }

    /**
     * Gives the roomList of the Hotel.
     *
     * @return roomList of the Hotel
     */
    public ArrayList<Room> getRoomList(){
        return this.roomList;
    }

    /**
     * Gives the reservationList of the Hotel.
     *
     * @return reservationList of the Hotel
     */
    public ArrayList<Reservation> getReservationList(){
        return this.reservationList;
    }
    public ArrayList<MonthDate> getMonthDateList(){return this.monthDateList;}
    /**
     * Sets the room capacity of the Hotel.
     *
     * @param roomCount roomCount must be from 1-50 to be considered a valid input
     * @return true if roomCount is from 1-50, false if otherwise
     */
    public boolean setNumberOfRooms(int roomCount){
        if(roomCount >= 1 && roomCount <= 50){
            this.numberOfRooms = roomCount;
            return true;
        }
        return false;
    }

    /**
     * Sets a new price for all Rooms in the Hotel.
     *
     * @param newPrice newPrice must be greater than or equal to 100.00 to be considered a valid input
     * @return true if newPrice is >= 100.00, false if otherwise
     */
    public boolean setNewPrice(double newPrice){
        boolean availabilityChecker = true;
        if(newPrice < 100.0)//if price given was less than 100
            return false;
        for(int i=0; i<this.roomList.size(); i++)//checks if all rooms are available
            if(!this.roomList.get(i).getAvailability()){
                availabilityChecker = false;
                break;
            }
        if(availabilityChecker){
            for(int j=0; j<this.roomList.size(); j++)
                this.roomList.get(j).setBasePrice(newPrice);
            return true;
        }
        else return false;
    }

    /**
     * Gives the name of the Hotel.
     *
     * @return name of the Hotel
     */
    public String getHotelName(){
        return this.hotelName;
    }

    /**
     * Sets the name for the Hotel.
     *
     * @param newName newName must follow the naming convention of no spaces to be considered a valid input
     * @return true if newName followed the naming convention, false if otherwise
     */
    public boolean setHotelName(String newName){
        //returns false if newName has a space or is empty
        if(newName.matches(".*\\s.*") || newName.isEmpty())
            return false;
        this.hotelName = newName;
        return true;
    }
}
