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
            /*Reservation reservation = new Reservation(guestName, in_date, in_month, in_year, out_date, out_month, out_year, chosenRoom);
            //this.hotelList.get(index).getReservationList().add(reservation);
            for(int j = 0; j <this.hotelList.get(index).getRoomList().size(); j++)
                if(chosenRoom.getRoomNumber() == this.hotelList.get(index).getRoomList().get(j).getRoomNumber())
                    this.hotelList.get(index).getRoomList().get(j).setAvailability(false);
            System.out.println("Reservation was successful.");*/
            return 6;
        }
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
}
