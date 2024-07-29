

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * (Hotel Reservation System)
 * The HRS class provides management and database of the Reservations and/of the Hotels.
 * The HRS class adds/removes Hotels to its list while providing an option to view them.
 * The HRS class also manages details about a Hotel in order to keep itself updated with the information of a Hotel.
 * It is also where reservation bookings can be done and give the list of hotels and prints all the names in the hotelList.
 *
 */
public class HRS {
    private ArrayList<Hotel> hotelList;

    /**
     * Constructs an HRS object that initializes the hotel list.
     *
     */
    public HRS(){
        this.hotelList = new ArrayList<>();
    }

    /**
     * Adds a hotel into the list that specifies the room capacity and name of the Hotel.
     *
     * @param roomCount room capacity of a Hotel that should fall within 1-50
     * @param name name of a Hotel that observes the naming convention
     * @return true if the Hotel was added with the restrictions followed, false if the name did not follow the naming convention or the room capacity was not in the accepted range
     */
    public boolean addHotel(int roomCount, String name) {
        Hotel newHotel = new Hotel(roomCount, name);
        if(!newHotel.setHotelName(name))
            return false;
        else if(!newHotel.setNumberOfRooms(roomCount))
            return false;
        else {
            this.hotelList.add(newHotel);
            return true;
        }
    }

    /**
     * Adds a hotel into the list the specifies the name of the Hotel.
     * The room capacity of the Hotel is automatically set to 1.
     *
     * @param name name of a Hotel that observes the naming convention
     * @return true if the naming convention was followed, false if otherwise
     */
    public boolean addHotel(String name){
        Hotel newHotel = new Hotel(name);
        if(!newHotel.setHotelName(name))
            return false;
        else{
            this.hotelList.add(newHotel);
            return true;
        }
    }

    /**
     * Returns the hotelList of an HRS.
     *
     * @return hotelList
     */
    public ArrayList<Hotel> getHotelList(){
        return this.hotelList;
    }

    /**
     * Prints the names of the Hotels in the hotelList.
     *
     */
    public void getHotelNames(){
        for(int i = 0; i<this.hotelList.size(); i++)
            System.out.printf("%d. %s\n", i+1, this.hotelList.get(i).getHotelName());
    }

    /**
     * Books a reservation to the chosen Hotel.
     * Check-Out date must not coincide with another Reservation's Check-In Date with the same chosenRoom.
     * Check-In date cannot be inside another Reservation's Check-In and Check-Out Date with the same chosenRoom.
     * Two Check-In dates for different Reservations with the same chosenRoom cannot be possible.
     * Check-In date must not fall on the 31st day of the month and Check-Out date must not fall on the 1st day of the next month.
     * Inputs for the guest name must not be empty.
     *
     * @param index index used to get the specified Hotel that the Reservation will belong to
     * @param guestName name of the guest
     * @param in_date date of the Check-In date
     * @param in_month month of the Check-In date
     * @param in_year year of the Check-In date
     * @param out_date date of the Check-Out date
     * @param out_month month of the Check-Out date
     * @param out_year year of the Check-out date
     * @param chosenRoom room chosen from the Hotel
     */
    public void addReservation(int index, String guestName, int in_date, int in_month, int in_year, int out_date, int out_month, int out_year, Room chosenRoom) {
        Calendar inDate = Calendar.getInstance();
        Calendar outDate = Calendar.getInstance();
        inDate.set(in_year, in_month, in_date);
        outDate.set(out_year, out_month, out_date);
        DiscountTag discountTag = new DiscountTag(false, false, false);
        
        // Get the monthDateList from the hotel
        ArrayList<MonthDate> monthDateList = this.hotelList.get(index).getMonthDateList();
        boolean dateChecker = true;
        for(int i = 0; i < this.hotelList.get(index).getReservationList().size(); i++)
            if(this.hotelList.get(index).getReservationList().get(i).getChosenRoom().getRoomNumber() == chosenRoom.getRoomNumber()) {
                if(inDate.before(this.hotelList.get(index).getReservationList().get(i).getCheckInDate()))
                    if(outDate.after(this.hotelList.get(index).getReservationList().get(i).getCheckInDate())){
                        dateChecker = false;
                        break;
                    }
                if(inDate.after(this.hotelList.get(index).getReservationList().get(i).getCheckInDate()) && inDate.before(this.hotelList.get(index).getReservationList().get(i).getCheckOutDate())){
                    dateChecker = false;
                    break;
                }
                if(inDate.equals(this.hotelList.get(index).getReservationList().get(i).getCheckInDate())){
                    dateChecker = false;
                    break;
                }
            }
        if(in_date == 31)
            System.out.println("ERROR: Check-In Date must not be done at the 31st day of the previous month.");
        else if(out_date == 1)
            System.out.println("ERROR: Check-Out Date must not be done at the 1st day of the next month.");
        else if(in_date > out_date)
            System.out.println("ERROR: Check-In Date must not be later than the Check-Out Date.");
        else if(!dateChecker)
            System.out.println("ERROR: Date is already reserved.");
        else if(guestName.isEmpty())
            System.out.println("ERROR: Empty Guest Name Inputted.");
        else{
            Reservation reservation = new Reservation(guestName, in_date, in_month, in_year, out_date, out_month, out_year, chosenRoom, discountTag, monthDateList);
            this.hotelList.get(index).getReservationList().add(reservation);
            for(int j = 0; j <this.hotelList.get(index).getRoomList().size(); j++)
                if(chosenRoom.getRoomNumber() == this.hotelList.get(index).getRoomList().get(j).getRoomNumber())
                    this.hotelList.get(index).getRoomList().get(j).setAvailability(false);
            System.out.println("Reservation was successful.");
        }
    }

    /**
     * Allows to view information of a chosen Hotel.
     * Users can choose between viewing the information of the Hotel, information of a chosen Room, information of a chosen Reservation, and the number of Rooms that are available and booked in a given date.
     *
     * @param index index of the chosen Hotel in the hotelList
     */
    public void viewHotel(int index){
        /*
        * case 1: prints the hotel's information(name, current size, and monthly earnings)
        * case 2: grabs the information of a room chosen by the user
        * case 3: grabs the information of a reservation chosen by the user
        * case 4: prints the number of rooms that are either booked or available in a given date
        * case 5: exits the method
        * default: loops back
        * */
        Scanner sc = new Scanner(System.in);
        int opt;
        do{
            System.out.println("====================================");
            System.out.println("View Hotel\n1. Hotel Information\n2. Room Information\n3. Reservation Information\n4. Availability of Rooms In a Selected Date\n5. Exit");
            opt = sc.nextInt();
            sc.nextLine();
            switch(opt){
                case 1:
                    System.out.println("Hotel Name: " + this.hotelList.get(index).getHotelName());
                    System.out.println("Number of Rooms: " + this.hotelList.get(index).getRoomList().size());
                    System.out.println("Estimated Monthly Earnings: " + this.hotelList.get(index).getMonthlyEarnings());
                    break;
                case 2:
                    if(this.hotelList.get(index).getRoomList().isEmpty()) //if there are no rooms in the hotel
                        System.out.println("There are no rooms to choose from.");
                    else{
                        System.out.println("Choose a room:");
                        for(int i=0; i<this.hotelList.get(index).getRoomList().size(); i++)//shows the list of rooms in the hotel
                            System.out.printf("%d. %d\n", i+1, this.hotelList.get(index).getRoomList().get(i).getRoomNumber());
                        int roomIndex;
                        roomIndex = sc.nextInt();
                        //if index given was valid and did not produce a null
                        if(roomIndex-1 > -1 && roomIndex-1 < this.hotelList.get(index).getRoomList().size() && this.hotelList.get(index).getRoomList().get(roomIndex-1) != null)
                            this.hotelList.get(index).getRoomList().get(roomIndex-1).getRoomInfo();
                        else System.out.println("ERROR: Illegal Accessing.");
                    }
                    break;
                    case 3:
                    if(this.hotelList.get(index).getReservationList().isEmpty()) //if there are no reservations within the hotel
                        System.out.println("There are no reservations to choose from.");
                    else{
                        System.out.println("Choose a Reservation:");
                        for(int j = 0; j <this.hotelList.get(index).getReservationList().size(); j++)//shows the list of reservations in the hotel
                            System.out.printf("%d. %d\n", j+1, this.hotelList.get(index).getReservationList().get(j).getChosenRoom().getRoomNumber());
                        int resIndex;
                        resIndex = sc.nextInt();
                        //if the index given was valid and did not produce a null
                        if(resIndex-1 > -1 && resIndex-1 < this.hotelList.get(index).getReservationList().size() && this.hotelList.get(index).getReservationList().get(resIndex-1) != null) {
                            System.out.println("Guest Name: " + this.hotelList.get(index).getReservationList().get(resIndex-1).getGuestName());
                            System.out.println("Chosen Room Info: ");
                            this.hotelList.get(index).getReservationList().get(resIndex-1).getChosenRoom().getRoomInfo();
                            System.out.print("Check-In Date (month, day, and then year): ");
                            System.out.printf("%d-%d-%d\n", this.hotelList.get(index).getReservationList().get(resIndex-1).getCheckInDate().get(Calendar.MONTH), this.hotelList.get(index).getReservationList().get(resIndex-1).getCheckInDate().get(Calendar.DAY_OF_MONTH), this.hotelList.get(index).getReservationList().get(resIndex-1).getCheckInDate().get(Calendar.YEAR));
                            System.out.print("Check-Out Date (month, day, and then year): ");
                            System.out.printf("%d-%d-%d\n", this.hotelList.get(index).getReservationList().get(resIndex-1).getCheckOutDate().get(Calendar.MONTH), this.hotelList.get(index).getReservationList().get(resIndex-1).getCheckOutDate().get(Calendar.DAY_OF_MONTH), this.hotelList.get(index).getReservationList().get(resIndex-1).getCheckOutDate().get(Calendar.YEAR));
                            System.out.println("Total Price: " + this.hotelList.get(index).getReservationList().get(resIndex-1).getTotalPrice());
                            ArrayList<MonthDate> monthDateList = this.hotelList.get(index).getMonthDateList();
                            System.out.println(this.hotelList.get(index).getReservationList().get(resIndex-1).breakdown(monthDateList)); // Fix for breakdown
                        }
                        else System.out.println("ERROR: Illegal Accessing.");
                    }
                    break;
                case 4:
                    if(this.hotelList.get(index).getRoomList().isEmpty())//if there are no rooms in the hotel
                        System.out.println("There are no rooms to get data from.");
                    else{
                        System.out.print("Please enter a date (month, day, and then year): ");
                        int n_month, n_date, n_year;
                        n_month = sc.nextInt();
                        n_date = sc.nextInt();
                        n_year = sc.nextInt();
                        Calendar cal = Calendar.getInstance();
                        cal.set(n_year, n_month, n_date);
                        ArrayList<Room> bookedRoomList = new ArrayList<>();
                        for(int k = 0; k <this.hotelList.get(index).getReservationList().size(); k++)//checks if a reservation falls under the date
                            if((!cal.after(this.hotelList.get(index).getReservationList().get(k).getCheckOutDate()) && !cal.before(this.hotelList.get(index).getReservationList().get(k).getCheckInDate())) || cal.equals(this.hotelList.get(index).getReservationList().get(k).getCheckInDate()))
                                if(!bookedRoomList.contains(this.hotelList.get(index).getReservationList().get(k).getChosenRoom()))//checks if a room was already previously added
                                    bookedRoomList.add(this.hotelList.get(index).getReservationList().get(k).getChosenRoom());
                        System.out.println("Booked Rooms: " + bookedRoomList.size());
                        int availableRooms = this.hotelList.get(index).getRoomList().size() - bookedRoomList.size();
                        System.out.println("Available Rooms: " + availableRooms);
                    }
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    sc.nextLine();
                    break;
                default:
                    System.out.println("ERROR: Invalid Input.");
                    break;

            }
        }while(opt != 5);
    }

    /**
     * Allows the editing of information about a Hotel
     * Users can change the name of a Hotel, together with adding/removing a Room, updating the base price of all the Rooms, removing reservations, removing the Hotel itself and changing room numbers
     *
     * @param index index of the chosen Hotel in the hotelList
     */
    public void manageHotel(int index){
        /*
        * case 1: change the name of the hotel
        * case 2: add a room to the hotel
        * case 3: remove a room from the hotel
        * case 4: update the price of all the rooms in the hotel
        * case 5: remove a reservation from the hotel
        * case 6: remove the hotel itself
        * case 7: change the number of a room in the hotel
        * case 8: exit
        * default: loops back
        *
        * */
        Scanner sc = new Scanner(System.in);
        int opt;
        do{
            System.out.println("====================================");
            System.out.println("Manage Hotel\n1. Change Hotel Name\n2. Add a room\n3. Remove a room\n4. Update base price\n5. Remove reservation\n6. Remove hotel\n7. Change Room Number\n8. Exit");
            opt = sc.nextInt();
            sc.nextLine();
            switch(opt){
                case 1:
                    String hotelName;
                    sc.nextLine();
                    do{
                        System.out.print("New Hotel Name: ");
                        hotelName = sc.nextLine();
                        if(!this.hotelList.get(index).setHotelName(hotelName))//if the method returned false
                            System.out.println("ERROR: No spaces/Empty inputs allowed.");
                        else
                            System.out.println("New Name: " + this.hotelList.get(index).getHotelName());
                    }while(!this.hotelList.get(index).setHotelName(hotelName));//loops back until a valid input was given
                    break;
                case 2:
                    int roomNum;
                    Room newRoom;
                    boolean roomChecker = true;
                    if(this.hotelList.get(index).getRoomList().size() >= 50)//if hotel is already full of rooms
                        System.out.println("Hotel is already full of rooms.");
                    else{
                        do {
                            System.out.print("Enter the number for the new room: ");
                            roomNum = sc.nextInt();
                            newRoom = new Room(roomNum);
                            if(!newRoom.setRoomNumber(roomNum))//if method returns false
                                System.out.println("ERROR: Unrecognized naming convention.");
                            else
                            {
                                for(int i=0; i<this.hotelList.get(index).getRoomList().size(); i++)//checks if room number already exists
                                        if(newRoom.getRoomNumber() == this.hotelList.get(index).getRoomList().get(i).getRoomNumber()){
                                            roomChecker = false;
                                            break;
                                        }
                                if(!roomChecker)
                                    System.out.println("ERROR: Room Already Exists.");
                                else{
                                    this.hotelList.get(index).getRoomList().add(newRoom);
                                    System.out.println("Room successfully added.");
                                }
                            }
                        }while(newRoom.getRoomNumber() == 0 || !roomChecker);//loops until a valid one is given
                    }
                    break;
                case 3:
                    int roomIndex;
                    if(this.hotelList.get(index).getRoomList().isEmpty())//if there are no rooms in the hotel
                        System.out.println("ERROR: There are no rooms to remove.");
                    else{
                        System.out.println("Choose a Room to Remove");
                        for(int i =0; i<this.hotelList.get(index).getRoomList().size(); i++)//shows the rooms in the hotel
                            System.out.printf("%d. %d\n", i+1, this.hotelList.get(index).getRoomList().get(i).getRoomNumber());
                        roomIndex = sc.nextInt();
                        //checks if index is valid and does not produce a null
                        if(roomIndex-1 > -1 && roomIndex-1 < this.hotelList.get(index).getRoomList().size() && this.hotelList.get(index).getRoomList().get(roomIndex-1) != null){
                            if(!this.hotelList.get(index).getRoomList().get(roomIndex-1).getAvailability())
                                System.out.println("Cannot execute action. Room currently has a reservation.");
                            else{
                                System.out.println("Room has been deleted.");
                                this.hotelList.get(index).getRoomList().remove(roomIndex-1);
                            }
                        }
                        else
                            System.out.println("ERROR: Illegal Accessing.");
                    }
                    break;
                case 4:
                    double newPrice;
                    if(this.hotelList.get(index).getReservationList().isEmpty()){
                        sc.nextLine();//if there are no reservations in the hotel
                        System.out.print("Enter the new price: ");
                        newPrice = sc.nextDouble();
                        if(this.hotelList.get(index).setNewPrice(newPrice))//if method returns true
                            System.out.println("Price has been updated.");
                        else
                            System.out.println("ERROR: Invalid Price Input.");
                    }
                    else
                        System.out.println("ERROR: There are still reservations in the hotel.");
                    break;
                case 5:
                    int reservationIndex, k;
                    boolean roomAvIndex = true;
                    Reservation roomCheck;
                    if(this.hotelList.get(index).getReservationList().isEmpty())//if there are no reservations in the hotel
                        System.out.println("ERROR: There are no reservations to remove.");
                    else{
                        System.out.println("Select a reservation to remove");
                        for(int j=0; j<this.hotelList.get(index).getReservationList().size(); j++)//shows the reservations in the hotel
                            System.out.printf("%d. %d\n", j+1, this.hotelList.get(index).getReservationList().get(j).getChosenRoom().getRoomNumber());
                        reservationIndex = sc.nextInt();
                        //checks if index is valid and does not produce a null
                        if(reservationIndex-1 > -1 && reservationIndex-1 < this.hotelList.get(index).getReservationList().size() && this.hotelList.get(index).getReservationList().get(reservationIndex-1) != null){
                            roomCheck = this.hotelList.get(index).getReservationList().remove(reservationIndex-1);
                            for(k = 0; k<this.hotelList.get(index).getReservationList().size(); k++)
                                if(roomCheck.getChosenRoom().getRoomNumber() == this.hotelList.get(index).getReservationList().get(k).getChosenRoom().getRoomNumber()){
                                    roomAvIndex = false;
                                    break;
                                }
                            if(roomAvIndex)
                                this.hotelList.get(index).getRoomList().get(k).setAvailability(true);
                        }
                        else
                            System.out.println("ERROR: Illegal Accessing.");
                    }
                    break;
                case 6:
                    this.hotelList.remove(index);
                    System.out.println("Hotel Successfully Removed.");
                    break;
                case 7:
                    if(this.hotelList.get(index).getRoomList().isEmpty())//if there are no rooms in the hotel
                        System.out.println("There are no rooms to change the number of.");
                    else{
                        int roomChangeIndex, roomChangeNum;
                        boolean roomChecker2 = true;
                        System.out.println("Select the room you wish to edit the number of.");
                        for(int l=0; l<this.hotelList.get(index).getRoomList().size(); l++)//shows the rooms in the hotel
                            System.out.printf("%d. %d\n", l+1, this.hotelList.get(index).getRoomList().get(l).getRoomNumber());
                        roomChangeIndex = sc.nextInt();
                        System.out.print("Enter the new room number: ");
                        roomChangeNum = sc.nextInt();
                        //checks if index is valid and does not produce a null
                        if(roomChangeIndex-1 > -1 && roomChangeIndex-1 < this.hotelList.get(index).getRoomList().size() && this.hotelList.get(index).getRoomList().get(roomChangeIndex-1) != null){
                            for (int m = 0; m < this.hotelList.get(index).getRoomList().size(); m++)
                                if (roomChangeNum == this.hotelList.get(index).getRoomList().get(m).getRoomNumber()){//checks if room number already exists
                                    roomChecker2 = false;
                                    break;
                                }
                            if (!roomChecker2)
                                System.out.println("ERROR: Room number already exists.");
                            else{
                                if (!this.hotelList.get(index).getRoomList().get(roomChangeIndex - 1).setRoomNumber(roomChangeNum))
                                    System.out.println("ERROR: Unrecognized Naming Convention.");
                                else
                                    System.out.println("Change was successful.");
                            }
                        }
                        else
                            System.out.println("ERROR: Illegal Accessing.");
                    }
                    break;
                case 8:
                    System.out.println("Exiting program...");
                    sc.nextLine();
                    break;
                default:
                    System.out.println("ERROR: Invalid Input.");
            }
        }while(opt != 8 && opt != 6);
    }
}
