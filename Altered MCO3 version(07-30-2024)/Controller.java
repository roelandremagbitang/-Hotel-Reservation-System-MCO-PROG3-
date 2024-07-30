import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        view.setHotelListAreaText(model.getHotelNames());
        this.view.setBtn1Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.openMainC1();
                view.setMC1SubmitBtnListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String potentialName = view.getNameTfText();
                        String potentialSize = "";
                        Hotel newHotel;
                        if(model.isHotelNameAcceptable(potentialName)){
                            potentialSize = view.getSizeTfText();
                            if(Integer.parseInt(potentialSize) <= 50 && Integer.parseInt(potentialSize) > 0){
                                newHotel = new Hotel(Integer.parseInt(potentialSize), potentialName);
                                model.getHotelList().add(newHotel);
                                view.setFeedbackLblText("Successfully added.");
                            }
                            else
                                view.setFeedbackLblText("Invalid size.");
                        }
                        else{
                            view.setFeedbackLblText("Does not follow naming convention.");
                        }
                        view.clearTextFieldsMC1();
                        view.setHotelListAreaText(model.getHotelNames());
                    }
                });
            }
        });
        this.view.setBtn2Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.openMainC2();
            }
        });
        this.view.setBtnMC2C1Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hotelIndex;
                if(model.getHotelList().isEmpty())
                    view.setMainLblText("There are no hotels to view.");
                else{
                    hotelIndex = view.getHotelIndexTfText();
                    switch(model.hotelIndexChecker(hotelIndex)) {
                        case 1:
                            view.setMainLblText("Index is out of bounds.");
                            break;
                        case 2:
                            view.setMainLblText("Hotel does not exist.");
                            break;
                        case 3:
                            view.concatHnItself(model.getHotelList().get(hotelIndex-1).getHotelName());
                            view.concatRcItself(Integer.toString(model.getHotelList().get(hotelIndex-1).getRoomList().size()));
                            view.concatMeItself(Double.toString(model.getHotelList().get(hotelIndex-1).getMonthlyEarnings()));
                            view.openMainC2C1();
                            break;
                    }
                }
            }
        });
        this.view.setBtnMC2C2BtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.getHotelList().get(view.getHotelIndexTfText()-1).getRoomList().isEmpty())
                    view.setMainLblText("There are no rooms to view.");
                else
                {
                    view.setTextAreaText(model.getRoomNumbers(view.getHotelIndexTfText()-1));
                    view.openMainC2C2();
                }
            }
        });
        this.view.setMC2C2SubmitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int roomIndex = Integer.parseInt(view.getMc2c2TfText());
                switch(model.roomChecker(roomIndex, view.getHotelIndexTfText()-1)) {
                    case 1:
                        view.setMc2c2RemarkLbl("ERROR: Index is out of bounds.");
                        break;
                    case 2:
                        view.setMc2c2RemarkLbl("ERROR: Room does not exist anymore.");
                        break;
                    case 3:
                        view.setRoomInfoAreaText(model.getHotelList().get(view.getHotelIndexTfText()-1).getRoomList().get(roomIndex-1).getRoomInfo());
                        view.clearTextFieldsMC2C2();
                        break;
                }
            }
        });
        this.view.setBtnMC2C3Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.getHotelList().get(view.getHotelIndexTfText()-1).getReservationList().isEmpty())
                    view.setMainLblText("There are no reservations to view.");
                else
                {
                    view.setTextAreaText(model.getReservationRoomNumbers(view.getHotelIndexTfText()-1));
                    view.openMainC2C3();
                }
            }
        });
        this.view.setMC2C3SubmitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reservationIndex = Integer.parseInt(view.getMc2c3TfText());
                switch(model.reservationChecker(reservationIndex, view.getHotelIndexTfText()-1)) {
                    case 1:
                        view.setMc2c3RemarkLbl("ERROR: Index is out of bounds.");
                        break;
                    case 2:
                        view.setMc2c3RemarkLbl("ERROR: Reservation does not exist anymore.");
                        break;
                    case 3:
                        view.setResInfoAreaText(model.getHotelList().get(view.getHotelIndexTfText()-1).getReservationList().get(reservationIndex-1).getReservationInfo(model.getHotelList().get(view.getHotelIndexTfText()-1).getMonthDateList()));
                        view.clearTextFieldsMC2C3();
                        break;
                }
            }
        });
        this.view.setBtnMC2C4Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.getHotelList().get(view.getHotelIndexTfText()-1).getRoomList().isEmpty())
                    view.setMainLblText("There are no rooms to view.");
                else
                    view.openMainC2C4();
            }
        });
        this.view.setMC2C4SubmitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int date, month, year;
                date = view.getCheckInDate();
                month = view.getCheckInMonth();
                year = view.getCheckInYear();
                view.setBookedRoomsAreaText(model.getBookedRoomsCount(month, date, year, view.getHotelIndexTfText()-1));
                view.clearTextFieldsMC2C4();
            }
        });
        this.view.setBtn3Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.openMainC3();
            }
        });
        this.view.setBtnMC3C1Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.openMainC3C1();
            }
        });
        this.view.setMC3C1SubmitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.isHotelNameAcceptable(view.getHotelNameTfText())){
                    model.getHotelList().get(view.getHotelIndexTfText()-1).setHotelName(view.getHotelNameTfText());
                    view.setMc3c1RemarkLbl("Change was successful.");
                }
                else
                    view.setMainLblText("ERROR: No spaces/Empty inputs allowed.");
                view.clearTextFieldsMC3C1();
            }
        });
        this.view.setBtnMC3C2Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.getHotelList().get(view.getHotelIndexTfText()-1).getRoomList().size() >= 50)
                    view.setMainLblText("Hotel is already full of rooms.");
                else{
                    view.setRoomListAreaAddText(model.getRoomNumbers(view.getHotelIndexTfText()-1));
                    view.openMainC3C2();
                }
            }
        });
        this.view.setMC3C2SubmitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Room newRoom = new Room(view.getAddRoomTfText());
                switch(model.isRoomAcceptable(newRoom.getRoomNumber(), view.getHotelIndexTfText()-1)){
                    case 1:
                        view.setMc3c2RemarkLbl("ERROR: Unrecognized Naming convention.");
                        break;
                    case 2:
                        view.setMc3c2RemarkLbl("ERROR: Room Already Exists.");
                        break;
                    case 3:
                        if(view.getRoomTypeTfText().equals("Room")){
                            model.getHotelList().get(view.getHotelIndexTfText()-1).getRoomList().add(newRoom);
                            view.setMc3c2RemarkLbl("Room successfully added.");
                        }
                        else if(view.getRoomTypeTfText().equals("Deluxe Room")){
                            newRoom = new DeluxeRoom(view.getAddRoomTfText());
                            model.getHotelList().get(view.getHotelIndexTfText()-1).getRoomList().add(newRoom);
                            view.setMc3c2RemarkLbl("Room successfully added.");
                        }
                        else if(view.getRoomTypeTfText().equals("Executive Room")){
                            newRoom = new ExecutiveRoom(view.getAddRoomTfText());
                            model.getHotelList().get(view.getHotelIndexTfText()-1).getRoomList().add(newRoom);
                            view.setMc3c2RemarkLbl("Room successfully added.");
                        }
                        else
                            view.setMc3c2RemarkLbl("ERROR: Unrecognized room type.");
                        break;
                }
                view.setRoomListAreaAddText(model.getRoomNumbers(view.getHotelIndexTfText()-1));
                view.clearTextFieldsMC3C2();
            }
        });
        this.view.setBtnMC3C3Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.getHotelList().get(view.getHotelIndexTfText()-1).getRoomList().isEmpty())
                    view.setMainLblText("There are no rooms to remove.");
                else{
                    view.setResRemAreaText(model.getRoomNumbers(view.getHotelIndexTfText()-1));
                    view.openMainC3C3();
                }
            }
        });
        this.view.setMC3C3SubmitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(model.roomChecker(view.getRemoveRoomTfText(), view.getHotelIndexTfText()-1)){
                    case 1:
                        view.setMc3c3RemarkLbl("ERROR: Index is out of bounds.");
                        break;
                    case 2:
                        view.setMc3c3RemarkLbl("ERROR: Room does not exist anymore.");
                        break;
                    case 3:
                        if(model.getHotelList().get(view.getHotelIndexTfText()-1).getRoomList().get(view.getRemoveRoomTfText()-1).getAvailability())
                            view.setMc3c3RemarkLbl("Room currently has a reservation.");
                        else{
                            model.getHotelList().get(view.getHotelIndexTfText()-1).getRoomList().remove(view.getRemoveRoomTfText()-1);
                            view.setMc3c3RemarkLbl("Room successfully removed.");
                        }
                }
                view.setResRemAreaText(model.getRoomNumbers(view.getHotelIndexTfText()-1));
                view.clearTextFieldsMC3C3();
            }
        });
        this.view.setBtnMC3C4Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(model.getHotelList().get(view.getHotelIndexTfText()-1).getReservationList().isEmpty()){
                    view.openMainC3C4();
                }
                else
                    view.setMainLblText("There are still reservations in the hotel.");
            }
        });
        this.view.setMC3C4SubmitBtnListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                double newPrice = 0;
                newPrice = view.getPriceTfText();
                if(model.getHotelList().get(view.getHotelIndexTfText()-1).setNewPrice(newPrice))
                    view.setMc3c4RemarkLbl("New price set successfully.");
                else
                    view.setMc3c4RemarkLbl("ERROR: New price could not be set.");
                view.clearTextFieldsMC3C4();
            }
        });
        this.view.setBtnMC3C5Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.getHotelList().get(view.getHotelIndexTfText()-1).getReservationList().isEmpty())
                    view.setMainLblText("There are no reservations to remove.");
                else{
                    view.setRoomListAreaRemText(model.getRoomNumbers(view.getHotelIndexTfText()-1));
                    view.openMainC3C5();
                }
            }
        });
        this.view.setMC3C5SubmitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reservation roomCheck;
                int i;
                boolean roomAvIndex = true;
                switch (model.reservationChecker(view.removeResTfText(), view.getHotelIndexTfText() - 1)) {
                    case 1:
                        view.setMc3c5RemarkLbl("ERROR: Index is out of bounds.");
                        break;
                    case 2:
                        view.setMc3c5RemarkLbl("ERROR: Reservation does not exist anymore.");
                        break;
                    case 3:
                        roomCheck = model.getHotelList().get(view.getHotelIndexTfText()-1).getReservationList().remove(view.removeResTfText()-1);
                        view.setMc3c5RemarkLbl("Reservation successfully removed.");
                        for(i=0; i<model.getHotelList().get(view.getHotelIndexTfText()-1).getReservationList().size(); i++)
                            if(roomCheck.getChosenRoom().getRoomNumber() == model.getHotelList().get(view.getHotelIndexTfText()-1).getReservationList().get(i).getChosenRoom().getRoomNumber()){
                                roomAvIndex = false;
                                break;
                            }
                        if(!roomAvIndex)
                            model.getHotelList().get(view.getHotelIndexTfText()-1).getRoomList().get(i).setAvailability(true);
                        break;
                }
                view.setRoomListAreaRemText(model.getRoomNumbers(view.getHotelIndexTfText()-1));
                view.clearTextFieldsMC3C5();
            }
        });
        this.view.setBtnMC3C6Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hotelIndex = view.getHotelIndexTfText();
                view.closeHotel();
                model.getHotelList().remove(hotelIndex-1);
                view.setHotelListAreaText(model.getHotelNames());
            }
        });
        this.view.setBtnMC3C7Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.getHotelList().isEmpty())
                    view.setMainLblText("There are no hotels to view.");
                else{
                    view.setDayRateAreaText(model.showMonthDays(view.getHotelIndexTfText()-1));
                    view.openMainC3C7();
                }
            }
        });
        this.view.setMC3C7SubmitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int day = view.getDateTfText();
                double rate = (view.getRateTfText() * 1.0)/100;
                if(day < 1 || day > 31)
                    view.setMc3c7RemarkLbl("ERROR: This is not a day in a month.");
                else {
                    model.getHotelList().get(view.getHotelIndexTfText() - 1).getMonthDateList().get(day - 1).setRate(rate);
                    view.setMc3c7RemarkLbl("Change was successful.");
                    view.setDayRateAreaText(model.showMonthDays(view.getHotelIndexTfText()-1));
                    view.clearTextFieldsMC3C7();
                }
            }
        });
        this.view.setBtn4Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.openMainC4();
            }
        });
        this.view.setMC4SubmitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(view.getHotelIndexRB()-1 < -1 || view.getHotelIndexRB()-1 > model.getHotelList().size())
                    view.setMc4RemarkLbl("ERROR: Hotel Index is out of bounds.");
                else if(model.getHotelList().get(view.getHotelIndexRB()) == null)
                    view.setMc4RemarkLbl("ERROR: Hotel does not exist anymore.");
                else{
                    int in_date, in_month, in_year, out_month, out_date, out_year, hotelIndex, roomIndex;
                    String guestName;
                    switch(model.roomChecker(view.getRoomIndexRB(), view.getHotelIndexRB()-1)) {
                        case 1:
                            view.setMc4RemarkLbl("ERROR: Index is out of bounds.");
                            break;
                        case 2:
                            view.setMc4RemarkLbl("ERROR: Room does not exist anymore.");
                            break;
                        case 3:
                            hotelIndex = view.getHotelIndexRB();
                            roomIndex = view.getRoomIndexRB();
                            in_date = view.getCheckInDateRB();
                            in_month = view.getCheckInMonthRB();
                            in_year = view.getCheckInYearRB();
                            out_date = view.getCheckOutDateRB();
                            out_month = view.getCheckOutMonthRB();
                            out_year = view.getCheckOutYearRB();
                            guestName = view.getGuestNameTf();
                            switch(model.addReservation(hotelIndex-1, guestName, in_date, in_month, in_year, out_date, out_month, out_year, model.getHotelList().get(hotelIndex-1).getRoomList().get(roomIndex-1))){
                                case 1:
                                    view.setMc4RemarkLbl("ERROR: Check-In Date must not be done at the 31st day of the previous month.");
                                    break;
                                case 2:
                                    view.setMc4RemarkLbl("ERROR: Check-Out Date must not be done at the 1st day of the next month.");
                                    break;
                                case 3:
                                    view.setMc4RemarkLbl("ERROR: Check-In Date must not be later than the Check-Out Date.");
                                    break;
                                case 4:
                                    view.setMc4RemarkLbl("ERROR: Date is already reserved.");
                                    break;
                                case 5:
                                    view.setMc4RemarkLbl("ERROR: Empty Guest Name Inputted.");
                                    break;
                                case 6:
                                    boolean IWHCode, S4G1Code, PCode;
                                    DiscountTag discountTag;
                                    if(view.getEmployeeConTfText() == 1)
                                        IWHCode = true;
                                    else
                                        IWHCode = false;
                                    if(out_date - in_date == 5)
                                        S4G1Code = true;
                                    else
                                        S4G1Code = false;
                                    if(out_date == 31 || in_date == 15 || (in_date < 15 && out_date > 15))
                                        PCode = true;
                                    else
                                        PCode = false;
                                    discountTag = new DiscountTag(IWHCode, S4G1Code, PCode);
                                    Reservation reservation = new Reservation(guestName, in_date, in_month, in_year, out_date, out_month, out_year, model.getHotelList().get(hotelIndex-1).getRoomList().get(roomIndex-1), discountTag, model.getHotelList().get(hotelIndex-1).getMonthDateList());
                                    model.getHotelList().get(hotelIndex-1).getReservationList().add(reservation);
                                    for(int j = 0; j <model.getHotelList().get(hotelIndex-1).getRoomList().size(); j++)
                                        if(model.getHotelList().get(hotelIndex-1).getRoomList().get(roomIndex-1).getRoomNumber() == model.getHotelList().get(hotelIndex-1).getRoomList().get(j).getRoomNumber())
                                            model.getHotelList().get(hotelIndex-1).getRoomList().get(j).setAvailability(false);
                                    view.setMc4RemarkLbl("Reservation Successful!");
                                    view.setReservationBookingAreaText(reservation.getReservationInfo(model.getHotelList().get(hotelIndex-1).getMonthDateList()));
                                    view.clearTextFieldsMC4();
                                    break;
                            }
                            break;
                    }
                }
            }
        });
    }
}
