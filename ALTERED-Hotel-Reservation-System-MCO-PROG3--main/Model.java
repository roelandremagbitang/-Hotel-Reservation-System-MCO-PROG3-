import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Model {
    private ArrayList<Hotel> hotelList;

    public Model(){
        this.hotelList = new ArrayList<>();
    }
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
    public void addHotel(String name){
        Hotel newHotel = new Hotel(name);
        this.hotelList.add(newHotel);
    }

    public ArrayList<Hotel> getHotelList(){
        return this.hotelList;
    }
    //view's job
    public String getHotelNames(){
        String hotelNames = "";
        for(int i = 0; i<this.hotelList.size(); i++)
            hotelNames += i+1 + ". " + this.hotelList.get(i).getHotelName() + "\n";
        return hotelNames;
    }
    public int addReservation(int index, String guestName, int in_date, int in_month, int in_year, int out_date, int out_month, int out_year, Room chosenRoom) {
        Calendar inDate = Calendar.getInstance();
        Calendar outDate = Calendar.getInstance();
        inDate.set(in_year, in_month, in_date);
        outDate.set(out_year, out_month, out_date);
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
            return 1;
            //System.out.println("ERROR: Check-In Date must not be done at the 31st day of the previous month.");
        else if(out_date == 1)
            return 2;
            //System.out.println("ERROR: Check-Out Date must not be done at the 1st day of the next month.");
        else if(in_date > out_date)
            return 3;
            //System.out.println("ERROR: Check-In Date must not be later than the Check-Out Date.");
        else if(!dateChecker)
            return 4;
            //System.out.println("ERROR: Date is already reserved.");
        else if(guestName.isEmpty())
            return 5;
            //System.out.println("ERROR: Empty Guest Name Inputted.");
        else{
            Reservation reservation = new Reservation(guestName, in_date, in_month, in_year, out_date, out_month, out_year, chosenRoom, null, null);
            this.hotelList.get(index).getReservationList().add(reservation);
            for(int j = 0; j <this.hotelList.get(index).getRoomList().size(); j++)
                if(chosenRoom.getRoomNumber() == this.hotelList.get(index).getRoomList().get(j).getRoomNumber())
                    this.hotelList.get(index).getRoomList().get(j).setAvailability(false);
            System.out.println("Reservation was successful.");
            return 6;
        }
    }
    public String viewHotelMenu(){
        String VHMenu = "=========================================================\n";
        VHMenu += "View Hotel\n1. Hotel Information\n2. Room Information\n3. Reservation Information\n4. Availability of Rooms In a Selected Date\n5. Exit";
        return VHMenu;
    }
    public String manageHotelMenu(){
        String MHMenu = "=========================================================\n";
        MHMenu += "Manage Hotel\n1. Change Hotel Name\n2. Add a room\n3. Remove a room\n4. Update base price\n5. Remove reservation\n6. Remove hotel\n7. Change day rate\n8. Exit";
        return MHMenu;
    }
    public String getHotelInfo(int index){
        String hotelInfo = "Hotel Name: " + this.hotelList.get(index).getHotelName() + "\n";
        hotelInfo += "Number of Rooms: " + this.hotelList.get(index).getRoomList().size() + "\n";
        hotelInfo += "Estimated Monthly Earnings: " + this.hotelList.get(index).getMonthlyEarnings() + "\n";
        return hotelInfo;
    }
    public String getRoomNumbers(int index){
        String roomNumberList = "";
        for(int i=0; i<this.hotelList.get(index).getRoomList().size(); i++)//shows the list of rooms in the hotel
            roomNumberList += i+1 + ". " + this.hotelList.get(index).getRoomList().get(i).getRoomNumber() + "\n";
        return roomNumberList; //System.out.printf("%d. %d\n", i+1, );
    }
    public String getReservationRoomNumbers(int index){
        String reservationRoomNumbers = "";
        for(int i=0; i<this.hotelList.get(index).getRoomList().size(); i++)
            reservationRoomNumbers += i+1 + ". " + this.hotelList.get(index).getReservationList().get(i).getChosenRoom().getRoomNumber() + "\n";
        return reservationRoomNumbers;
    }
    public int roomChecker(int roomIndex, int index){
        if(roomIndex-1 < -1 || roomIndex-1 > this.hotelList.get(index).getRoomList().size())
                return 1;
        else if(this.hotelList.get(index).getRoomList().get(roomIndex-1) == null)
                return 2;
        else
                return 3;
    }
    public int reservationChecker(int resIndex, int index){
        if(resIndex-1 < -1 || resIndex-1 > this.hotelList.get(index).getReservationList().size())
            return 1;
        else if(this.hotelList.get(index).getRoomList().get(resIndex-1) == null)
            return 2;
        else
            return 3;
    }
    public int hotelIndexChecker(int index){
        if(index-1 < -1 || index-1 > this.getHotelList().size())
            return 1;
        else if(this.hotelList.get(index) == null)
            return 2;
        else
            return 3;

    }
    public String getBookedRoomsCount(int n_month, int n_date, int n_year, int index){
        String BRCount = "";
        Calendar cal = Calendar.getInstance();
        cal.set(n_year, n_month, n_date);
        ArrayList<Room> bookedRoomList = new ArrayList<>();
        for(int k = 0; k <this.hotelList.get(index).getReservationList().size(); k++)//checks if a reservation falls under the date
            if((!cal.after(this.hotelList.get(index).getReservationList().get(k).getCheckOutDate()) && !cal.before(this.hotelList.get(index).getReservationList().get(k).getCheckInDate())) || cal.equals(this.hotelList.get(index).getReservationList().get(k).getCheckInDate()))
                if(!bookedRoomList.contains(this.hotelList.get(index).getReservationList().get(k).getChosenRoom()))//checks if a room was already previously added
                    bookedRoomList.add(this.hotelList.get(index).getReservationList().get(k).getChosenRoom());
        BRCount += "Booked Rooms: " + bookedRoomList.size() + "\n";
        int availableRooms = this.hotelList.get(index).getRoomList().size() - bookedRoomList.size();
        BRCount += "Available Rooms: " + availableRooms + "\n";
        return BRCount;
    }
    public boolean isHotelNameAcceptable(String hotelName){
        if(hotelName.matches(".*\\s.*") || hotelName.isEmpty())//if the method returned false
            return false;
        else
            return true;
    }
    public int isRoomAcceptable(int roomNum, int index){
        Room newRoom = new Room(roomNum);
        if(newRoom.getRoomNumber() == 0)//if method returns false
            return 1;
        else
            for (int i = 0; i < this.hotelList.get(index).getRoomList().size(); i++)//checks if room number already exists
                if (newRoom.getRoomNumber() == this.hotelList.get(index).getRoomList().get(i).getRoomNumber())
                    return 2;
        return 3;
    }
    public String showMonthDays(int index){
        String dayRate = "";
        for(int i=0; i<31; i++){
            dayRate += "Day of the month: " + this.hotelList.get(index).getMonthDateList().get(i).getDate() + "\n";
            dayRate += "Rate for the day: " + this.hotelList.get(index).getMonthDateList().get(i).getRate() + "\n";
        }
        return dayRate;
    }
    //getRoomNumbers first, then for removal of room, use the roomChecker and then add an if statement on the case at which the number is acceptable and existing
    //editing of the price is strangely forward enough that you can include the whole thing in the controller
    //show the reservations first, do the reservation check, remove if it passes, check if there's still more then change availability accordingly
    //removal of the hotel is easy
    //we already have a place for the setting of the rate so controller should implement that, must input as if percentage is already considered, then divide by 100
    //exiting of the programs is the controllers job to implement in the view and the entire program
    //invalid inputs can easily be stored by the controller to the view
    /*public void viewHotel(int index){

         /* case 1: prints the hotel's information(name, current size, and monthly earnings)
         * case 2: grabs the information of a room chosen by the user
         * case 3: grabs the information of a reservation chosen by the user
         * case 4: prints the number of rooms that are either booked or available in a given date
         * case 5: exits the method
         * default: loops back
         *
        Scanner sc = new Scanner(System.in);
        int opt;
        do{
            //view
            System.out.println("====================================");
            System.out.println("View Hotel\n1. Hotel Information\n2. Room Information\n3. Reservation Information\n4. Availability of Rooms In a Selected Date\n5. Exit"); //v
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
                            this.hotelList.get(index).getReservationList().get(resIndex-1).breakdown();
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
    }*/
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
         *
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
                    //show a list of the days in the view
                    int date_opt = sc.nextInt();
                    //ask for the new rate for that date
                    double newRate = sc.nextDouble();
                    sc.nextLine();
                    this.monthDateList.get(date_opt-1).setRate(newRate);
                case 8:
                    System.out.println("Exiting program...");
                    sc.nextLine();
                    break;
                default:
                    System.out.println("ERROR: Invalid Input.");
            }
        }while(opt != 8 && opt != 6);*/
    }
}
