import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
       /*Room room1 = new Room(101);
        room1  = new DeluxeRoom(201);
        //System.out.println(room1.getBasePrice());
        ArrayList<MonthDate> monthDateList = new ArrayList<>();
        for(int i=0; i<31; i++){
            MonthDate monthDate = new MonthDate(i+1);
            monthDateList.add(i, monthDate);
        }
        double price = 0;
        Reservation reservation = new Reservation("Matt", 15, 3, 2002, 18, 3, 2002, room1, new DiscountTag(true, false, true), monthDateList);
        monthDateList.get(14).setRate(90.00/100);

        System.out.println(reservation.breakdown(monthDateList));
        System.out.println(monthDateList.get(15).getRate());
        System.out.println(monthDateList.get(15).getDate());
        if(monthDateList.get(14).getRate() > 100)
            price = room1.getBasePrice() + room1.getBasePrice() * monthDateList.get(14).getRate();
        if(monthDateList.get(14).getRate() < 1)
            price = room1.getBasePrice() - room1.getBasePrice() * monthDateList.get(14).getRate();
        System.out.println(90.00/100);
        System.out.println(room1.getBasePrice());*/
    }
}