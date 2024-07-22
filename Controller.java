import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;
    private String greeting;
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        this.greeting = "=========================================================\n";
        this.greeting += "Welcome to the Hotel Reservation System!\nFunctionalities:\n";
        this.greeting += "1. Create a Hotel\n2. View a Hotel\n3. Manage a Hotel\n4. Make a reservation\n";
        view.setTextAreaText(this.greeting);
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
                    switch(model.hotelIndexChecker(hotelIndex-1)) {
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
                view.openMainC2C2();
            }
        });
        /*this.view.setMC2C2SubmitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/
        this.view.setBtnMC2C3Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.openMainC2C3();
            }
        });
        this.view.setMC2C3SubmitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.view.setBtnMC2C4Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.openMainC2C4();
            }
        });
        this.view.setMC2C4SubmitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
        this.view.setBtnMC3C2Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.openMainC3C2();
            }
        });
        this.view.setBtnMC3C3Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.openMainC3C3();
            }
        });
        this.view.setBtnMC3C4Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.openMainC3C4();
            }
        });
        this.view.setBtnMC3C5Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.openMainC3C5();
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
                view.openMainC3C7();
            }
        });
        this.view.setBtn4Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.openMainC4();
            }
        });
    }
}
