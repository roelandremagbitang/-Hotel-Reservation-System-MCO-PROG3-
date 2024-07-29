
import java.util.Scanner;
//the main file (driver) of the hotel reservation system
public class HRSMain {
    
    /** 
     * @param args
     */
    public static void main(String[] args){
        HRS hrs = new HRS();
        int opt;
        Scanner sc = new Scanner(System.in);
        /*
        * case 1: Create a Hotel
        * case 2: View a Hotel
        * case 3: Manage a Hotel
        * case 4: Simulate Booking
        * case 5: Exit
        * default: if another number was entered
        * */
        do{
            System.out.println("====================================");
            System.out.println("Welcome to the Hotel Reservation System!\nFunctionalities:");
            System.out.println("1. Create a Hotel\n2. View a Hotel\n3. Manage a Hotel\n4. Make a reservation\n5. Exit");
            opt = sc.nextInt();
            sc.nextLine();
            switch(opt){
                case 1:
                    int roomNum;
                    int inputOpt;
                    String newName;
                    boolean loopChecker = true;
                    while(loopChecker){
                        System.out.println("Choose to initialize the room size or not\n1. Yes\n2. No");
                        inputOpt = sc.nextInt();
                        sc.nextLine();
                        if(inputOpt == 1){//initialization of the hotel with the name and initial capacity
                            System.out.print("Enter the name: ");
                            newName = sc.nextLine();
                            System.out.print("Enter the initial size: ");
                            roomNum = sc.nextInt();
                            if(hrs.addHotel(roomNum, newName)){//if method was successful
                                System.out.println("Hotel Successfully Made.");
                                loopChecker = false;
                            }
                            else
                                System.out.println("ERROR: One of the inputs was incorrect.");
                        }
                        else if(inputOpt == 2){//initialization of the hotel with just the name
                            System.out.print("Enter a name for the hotel: ");
                            sc.nextLine();
                            newName = sc.nextLine();
                            sc.nextLine();
                            if(hrs.addHotel(newName)){//if method was successful
                                System.out.println("Hotel Successfully Made.");
                                loopChecker = false;
                            }
                            else{
                                System.out.println("ERROR: Name input was incorrect.");
                            }
                        }
                        else{
                            System.out.println("ERROR: Unrecognized Input.");
                        }
                        sc.nextLine();
                    }
                    break;
                case 2:
                    if(hrs.getHotelList().isEmpty())//if there are no hotels
                        System.out.println("There are currently no hotels to view.");
                    else{
                        int viewHotelIndex;
                        System.out.println("Choose a hotel:");
                        hrs.getHotelNames();
                        viewHotelIndex = sc.nextInt();
                        if(viewHotelIndex-1 > -1 && viewHotelIndex-1 < hrs.getHotelList().size()){//if index was valid
                            if(hrs.getHotelList().get(viewHotelIndex -1) != null)//if index does not produce a null
                                hrs.viewHotel(viewHotelIndex -1);
                            else
                                System.out.println("ERROR: Hotel does not exist.");
                        }
                        else
                            System.out.println("ERROR: Illegal Accessing.");
                    }
                    break;
                case 3:
                    if(hrs.getHotelList().isEmpty())//if there are no hotels
                        System.out.println("There are currently no hotels to manage.");
                    else{
                        int manageHotelIndex;
                        System.out.println("Choose a hotel:");
                        hrs.getHotelNames();
                        manageHotelIndex = sc.nextInt();
                        if(manageHotelIndex-1 > -1 && manageHotelIndex-1 < hrs.getHotelList().size()){//if index was valid
                            if(hrs.getHotelList().get(manageHotelIndex-1) != null)//if index does not produce a null
                                hrs.manageHotel(manageHotelIndex-1);
                            else
                                System.out.println("ERROR: Hotel does not exist");
                        }
                        else
                            System.out.println("ERROR: Illegal Accessing.");
                    }
                    break;
                case 4:
                    if(hrs.getHotelList().isEmpty())//if there are no hotels
                        System.out.println("There are currently no hotels to book a reservation to.");
                    else{
                        String guestName;
                        int bookHotelIndex, roomIndex, in_date, in_month, in_year, out_date, out_month, out_year;
                        System.out.println("Choose a hotel:");
                        hrs.getHotelNames();
                        bookHotelIndex = sc.nextInt();
                        sc.nextLine();
                        if(bookHotelIndex-1 > -1 && bookHotelIndex-1 < hrs.getHotelList().size())//if index was valid
                            if(hrs.getHotelList().get(bookHotelIndex-1) != null){//if index does not produce a null
                                if(hrs.getHotelList().get(bookHotelIndex-1).getRoomList().isEmpty())//if there are no rooms in the hotel
                                    System.out.println("This hotel has no rooms at the moment.");
                                else{
                                    System.out.println("Choose a Room:");
                                    for(int l = 0; l<hrs.getHotelList().get(bookHotelIndex-1).getRoomList().size(); l++)//shows all the rooms in the hotel
                                        System.out.printf("%d. %d\n", l+1, hrs.getHotelList().get(bookHotelIndex-1).getRoomList().get(l).getRoomNumber());
                                    roomIndex = sc.nextInt();
                                    sc.nextLine();
                                    if(roomIndex-1 > -1 && roomIndex < hrs.getHotelList().get(bookHotelIndex-1).getRoomList().size())//if index was valid
                                        if(hrs.getHotelList().get(bookHotelIndex-1).getRoomList().get(roomIndex-1) != null){//if index does not produce a null
                                            System.out.print("Enter your name: ");
                                            guestName = sc.nextLine();
                                            sc.nextLine();

                                            //check-in and check-out dates
                                            System.out.print("Enter Check-In date: ");
                                            in_month = sc.nextInt();
                                            in_date = sc.nextInt();
                                            in_year = sc.nextInt();
                                            System.out.print("Enter Check-Out date: ");
                                            out_month = sc.nextInt();
                                            out_date = sc.nextInt();
                                            out_year = sc.nextInt();

                                            hrs.addReservation(bookHotelIndex-1, guestName, in_date, in_month, in_year, out_date, out_month, out_year, hrs.getHotelList().get(bookHotelIndex-1).getRoomList().get(roomIndex-1));
                                        }
                                        else System.out.println("ERROR: Room does not exist.");
                                }
                            }
                            else System.out.println("ERROR: Illegal Accessing.");
                    }
                    break;
                case 5: 
                    System.out.println("Exiting program...");
                    sc.close();
                    break;
                default:
                    System.out.println("ERROR: Invalid Input.");
                    break;
            }
        }while(opt != 5);
    }
}
