

/**
 * The Room class contains information about a Room in a Hotel.
 * A Room contains information about its price per night, its room number, and its availability.
 * A Room can set its own pricing, availability and number with the correct convention, alongside giving its complete information.
 */
public class Room {
    private double basePrice;
    private int number = 0;
    private boolean availability;

    /**
     * Constructs a Room object that asks for the number that will be assigned to the object.
     *
     * @param number number given must be within the range of 100-510 while only being up to 9 in the ones digit.
     *               (ex. 100-109 must be followed and the next number would jump immediately to 200)
     */
    public Room(int number){
        this.basePrice = 1299.00;
        setRoomNumber(number);
        this.availability = true;
    }

    /**
     * Gives the number of a Room.
     *
     * @return number of a Room
     */
    public int getRoomNumber(){
        return this.number;
    }

    /**
     * Sets the number of a Room.
     *
     * @param number number must follow the naming convention
     * @return true if convention was followed, false if otherwise
     */
    public boolean setRoomNumber(int number){
        if(number >= 100 && number < 510)//checks if number is from 100-510
            if((number/10%100) == 10 || (number/10%100) == 20 || (number/10%100) == 30 || (number/10%100) == 40 || (number/10%100) == 50){//checks if the number's ones digit is 0-9 with the tens digit being 0
                this.number = number;
                return true;
            }
        return false;
    }

    /**
     * Gives the price of a Room per night.
     *
     * @return basePrice of a Room
     */
    public double getBasePrice(){
        return this.basePrice;
    }

    /**
     * Gives the availability of a Room.
     *
     * @return availability of a Room
     */
    public boolean getAvailability(){
        return this.availability;
    }

    /**
     * Prints a screen output of all the information about a Room.
     *
     */
    public String getRoomInfo() {
        String info = "";
        info += "Room Number: " + this.getRoomNumber() + "\n";
        info += "Price per night: " + this.getBasePrice() + "\n";
        info += "Availability: " + this.getAvailability() + "\n";
        return info;
    }
    /**
     * Sets the new price for a Room.
     *
     * @param newPrice the new price of a Room
     */
    public void setBasePrice(double newPrice){
        this.basePrice = newPrice;
    }

    /**
     * Sets the availability of a Room.
     *
     * @param availability availability of a Room
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
