import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class View {
    private JFrame mainFrame;
    private JFrame mainC1, mainC2, mainC3, mainC4;
    private JFrame C2C1, C2C2, C2C3, C2C4;
    private JFrame C3C1, C3C2, C3C3, C3C4, C3C5, C3C7;
    private JLabel meItself, hnItself, rcItself;
    private JLabel mainLbl, mc2c2Lbl, mc2c3Lbl, mc2c4CIDMonthLbl, mc2c4CIDDateLbl, mc2c4CIDYearLbl;
    private JLabel mc3c1Lbl, mc3c2Lbl, mc3c3Lbl, mc3c4Lbl, mc3c5Lbl, dateLbl, rateLbl, mc32RoomTypeLbl;
    private JLabel mc2c2RemarkLbl, mc2c3RemarkLbl, mc3c1RemarkLbl, mc3c2RemarkLbl, mc3c3RemarkLbl, mc3c4RemarkLbl, mc3c5RemarkLbl, mc3c7RemarkLbl, mc4RemarkLbl;
    private JLabel inputLbl, welcomeLbl, remarkLblMc1, nameLbl, case1Lbl, case2Lbl, case3Lbl, case4Lbl;
    private JLabel hotelLbl, resRoomLbl, resGuestLbl, cidMonthLbl, cidDateLbl, cidYearLbl, codMonthLbl, codDateLbl, codYearLbl, employeeLbl;
    private JTextField nameTf, sizeTf, roomNumTf1, hotelNameTf, roomNumTf2, roomNumTf3, resTf, resRemoveTf, priceTf, hotelIndexTf, dateTf, rateTf, roomTypeTf;
    private JTextField mc2c4CiMTf, mc2c4CiDTf, mc2c4CiYTf;
    private JTextField hotelTf, resRoomTf, resGuestTf, cidMonthTf, cidDateTf, cidYearTf, codMonthTf, codDateTf, codYearTf, employeeTf;
    private JButton btn1, btn2, btn3, btn4;
    private JButton btnMC2C1, btnMC2C2, btnMC2C3, btnMC2C4;
    private JButton btnMC3C1, btnMC3C2, btnMC3C3, btnMC3C4, btnMC3C5, btnMC3C6, btnMC3C7;
    private JButton subMC1, subMC2C2, subMC2C3, subMC2C4, subMC3C1, subMC3C2, subMC3C3, subMC3C4, subMC3C5, subMC3C7, subMC4;
    private JTextArea TextArea, hotelListArea, mc2c2Area, mc2c3Area, mc2c4Area, mc3c2Area, mc3c3Area, mc3c5Area, mc3c7Area, reservationArea;
    public View() {
        this.mainFrame = new JFrame("MCO2");

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setSize(1080, 720);

        this.mainLbl = new JLabel();
        this.inputLbl = new JLabel("Hotel(by number): ");
        this.hotelIndexTf = new JTextField();
        this.hotelIndexTf.setColumns(10);
        //this.feedbackLbl = new JLabel();
        //this.feedbackLbl.setPreferredSize(new Dimension(220, 30));

        this.TextArea = new JTextArea(" ");
        this.TextArea.setPreferredSize(new Dimension(300, 200));
        this.TextArea.setEditable(false);
        //this.employeeListTextArea.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.hotelListArea = new JTextArea("");
        this.hotelListArea.setPreferredSize(new Dimension(540, 200));
        this.hotelListArea.setEditable(false);

        this.remarkLblMc1 = new JLabel();
        //main buttons
        this.btn1 = new JButton("1");
        this.btn2 = new JButton("2");
        this.btn3 = new JButton("3");
        this.btn4 = new JButton("4");
        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.add(this.btn1);
        panel1.add(this.btn2);
        panel1.add(this.btn3);
        panel1.add(this.btn4);
        panel1.add(this.inputLbl);
        panel1.add(this.hotelIndexTf);
        panel1.add(this.mainLbl);
        panel1.setPreferredSize(new Dimension(250, 100));
        panel1.setBackground(Color.LIGHT_GRAY);



        this.mainFrame.add(panel1, BorderLayout.WEST);
        this.welcomeLbl = new JLabel("Hotel Reservation System");
        this.welcomeLbl.setFont(new Font("MS UI Gothic", Font.BOLD, 30));
        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(110, 81, 145));
        panel2.setPreferredSize(new Dimension(720, 300));
        panel2.add(this.welcomeLbl);
        this.mainFrame.add(panel2, BorderLayout.NORTH);
        //this.mainFrame.add(submitBtn);
        //this.mainFrame.add(feedbackLbl);
        this.mainFrame.add(this.TextArea, BorderLayout.EAST);
        this.mainFrame.add(this.hotelListArea, BorderLayout.SOUTH);

        //frame component structuring
        this.case1Lbl = new JLabel("Create a Hotel");
        this.case1Lbl.setFont(new Font("MS UI Gothic", Font.BOLD, 30));
        this.nameLbl = new JLabel("Name: ");
        JLabel sizeLbl = new JLabel("Size: ");
        JPanel mC1 = new JPanel();
        JPanel mCT1 = new JPanel();
        mCT1.setBackground(new Color(110, 81, 145));
        mCT1.setPreferredSize(new Dimension(720, 300));
        this.subMC1 = new JButton("Submit");
        this.nameTf = new JTextField();
        this.nameTf.setColumns(20);
        this.sizeTf = new JTextField();
        this.sizeTf.setColumns(20);
        mC1.setBackground(Color.LIGHT_GRAY);
        mC1.add(this.nameLbl);
        mC1.add(this.nameTf);
        mC1.add(sizeLbl);
        mC1.add(this.sizeTf);
        mC1.add(this.subMC1);
        mC1.add(this.remarkLblMc1);
        mCT1.add(this.case1Lbl);
        //frame structuring
        this.mainC1 = new JFrame("Main Case 1");
        this.mainC1.setLayout(new BorderLayout());
        this.mainC1.setSize(1080, 720);
        this.mainC1.add(mC1, BorderLayout.WEST);
        this.mainC1.add(mCT1, BorderLayout.NORTH);
        this.mainC1.setVisible(false);

        //mc2
        this.btnMC2C1 = new JButton("Hotel Info");
        this.btnMC2C2 = new JButton("Room Info");
        this.btnMC2C3 = new JButton("Reservation Info");
        this.btnMC2C4 = new JButton("Booked Rooms Count");
        this.case2Lbl = new JLabel("View a Hotel");
        this.case2Lbl.setFont(new Font("MS UI Gothic", Font.BOLD, 30));

        JPanel mCT2 = new JPanel();
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        mCT2.setBackground(new Color(110, 81, 145));
        mCT2.setPreferredSize(new Dimension(720, 300));
        mCT2.add(this.case2Lbl);
        buttons.setBackground(Color.LIGHT_GRAY);
        buttons.setSize(new Dimension(200, 200));
        buttons.add(this.btnMC2C1);
        buttons.add(this.btnMC2C2);
        buttons.add(this.btnMC2C3);
        buttons.add(this.btnMC2C4);
        this.mainC2 = new JFrame("View Hotel");
        this.mainC2.setLayout(new BorderLayout());
        this.mainC2.setSize(1080, 720);
        this.mainC2.add(mCT2, BorderLayout.NORTH);
        this.mainC2.add(buttons, BorderLayout.CENTER);
        this.mainC2.setVisible(false);

        //mc2c1
        this.C2C1 = new JFrame("Hotel Info");
        this.hnItself = new JLabel("Hotel Name: ");
        this.rcItself = new JLabel("Room Count: ");
        this.meItself = new JLabel("Monthly Earnings: ");
        JPanel c2c1InfoPanel = new JPanel();
        c2c1InfoPanel.setLayout(new BorderLayout());
        c2c1InfoPanel.setBackground(Color.LIGHT_GRAY);
        c2c1InfoPanel.setPreferredSize(new Dimension(720, 300));
        c2c1InfoPanel.add(this.hnItself, BorderLayout.NORTH);
        c2c1InfoPanel.add(this.rcItself, BorderLayout.WEST);
        c2c1InfoPanel.add(this.meItself, BorderLayout.EAST);
        this.hnItself.setHorizontalAlignment(SwingConstants.CENTER);
        this.C2C1.setLayout(new FlowLayout());
        this.C2C1.setSize(1080, 720);
        this.C2C1.add(c2c1InfoPanel);
        this.C2C1.setVisible(false);

        //mc2c2
        this.C2C2 = new JFrame("Room Info");
        this.mc2c2Lbl = new JLabel("Room Number: ");
        this.mc2c2RemarkLbl = new JLabel();
        this.subMC2C2 = new JButton("Submit");
        this.roomNumTf1 = new JTextField();
        this.mc2c2Area = new JTextArea();
        JPanel mc2_2 = new JPanel();

        this.roomNumTf1.setColumns(10);
        this.mc2c2Area.setPreferredSize(new Dimension(300, 300));
        mc2_2.setPreferredSize(new Dimension(250, 100));
        mc2_2.setBackground(Color.LIGHT_GRAY);
        mc2_2.add(this.mc2c2Lbl);
        mc2_2.add(this.roomNumTf1);
        mc2_2.add(this.subMC2C2);
        mc2_2.add(this.mc2c2RemarkLbl);

        this.C2C2.setLayout(new BorderLayout());
        this.C2C2.setSize(new Dimension(1080, 720));
        this.C2C2.add(mc2_2, BorderLayout.WEST);
        this.C2C2.add(this.mc2c2Area, BorderLayout.EAST);
        this.C2C2.setVisible(false);

        //mc2c3
        this.mc2c3Lbl = new JLabel("Reservation Index: ");
        this.subMC2C3 = new JButton("Submit");
        this.mc2c3Area = new JTextArea();
        this.resTf = new JTextField();
        this.mc2c3RemarkLbl = new JLabel();
        JPanel mc2_3 = new JPanel();


        this.resTf.setColumns(10);
        this.mc2c3Area.setPreferredSize(new Dimension(300, 300));
        mc2_3.setPreferredSize(new Dimension(250, 100));
        mc2_2.setBackground(Color.LIGHT_GRAY);
        this.mc2c3Area.setEditable(false);
        mc2_3.add(this.mc2c3Lbl);
        mc2_3.add(this.resTf);
        mc2_3.add(this.subMC2C3);
        mc2_3.add(this.mc2c3RemarkLbl);

        this.C2C3 = new JFrame("Reservation Info");
        this.C2C3.setLayout(new BorderLayout());
        this.C2C3.setSize(new Dimension(1080, 720));
        this.C2C3.add(mc2_3, BorderLayout.WEST);
        this.C2C3.add(this.mc2c2Area, BorderLayout.EAST);
        this.C2C3.setVisible(false);

        //mc2c4
        this.mc2c4CIDMonthLbl = new JLabel("Check-In Month: ");
        this.mc2c4CIDDateLbl = new JLabel("Check-In Date: ");
        this.mc2c4CIDYearLbl = new JLabel("Check-In Year: ");
        this.mc2c4CiMTf = new JTextField();
        this.mc2c4CiDTf = new JTextField();
        this.mc2c4CiYTf = new JTextField();
        this.subMC2C4 = new JButton("Submit");
        this.mc2c4Area = new JTextArea();
        JPanel mc2_4 = new JPanel();

        this.mc2c4CiMTf.setColumns(10);
        this.mc2c4CiDTf.setColumns(10);
        this.mc2c4CiYTf.setColumns(10);
        mc2_4.setPreferredSize(new Dimension(250, 250));
        this.mc2c4Area.setPreferredSize(new Dimension(300, 300));
        mc2_4.setBackground(Color.LIGHT_GRAY);
        this.mc2c4Area.setEditable(false);
        mc2_4.add(this.mc2c4CIDMonthLbl);
        mc2_4.add(this.mc2c4CiMTf);
        mc2_4.add(this.mc2c4CIDDateLbl);
        mc2_4.add(this.mc2c4CiDTf);
        mc2_4.add(this.mc2c4CIDYearLbl);
        mc2_4.add(this.mc2c4CiYTf);
        mc2_4.add(this.subMC2C4);

        this.C2C4 = new JFrame("Room Availability");
        this.C2C4.setLayout(new BorderLayout());
        this.C2C4.setSize(new Dimension(1080, 720));
        this.C2C4.add(mc2_4, BorderLayout.WEST);
        this.C2C4.add(this.mc2c4Area, BorderLayout.EAST);
        this.C2C4.setVisible(false);

        //mc3
        this.btnMC3C1 = new JButton("Change Hotel Name");
        this.btnMC3C2 = new JButton("Add a room");
        this.btnMC3C3 = new JButton("Remove a room");
        this.btnMC3C4 = new JButton("Change room price");
        this.btnMC3C5 = new JButton("Remove a reservation");
        this.btnMC3C6 = new JButton("Remove a hotel");
        this.btnMC3C7 = new JButton("Change rate of day");
        this.case3Lbl = new JLabel("Manage a Hotel");
        JPanel buttons2 = new JPanel();
        JPanel mcT3 = new JPanel();

        this.case3Lbl.setFont((new Font("MS UI Gothic", Font.BOLD, 30)));
        buttons2.setLayout(new FlowLayout());
        buttons2.setBackground(Color.LIGHT_GRAY);
        buttons2.setSize(new Dimension(200, 200));
        mcT3.setBackground(new Color(110, 81, 145));
        mcT3.setPreferredSize(new Dimension(720, 300));

        buttons2.add(this.btnMC3C1);
        buttons2.add(this.btnMC3C2);
        buttons2.add(this.btnMC3C3);
        buttons2.add(this.btnMC3C4);
        buttons2.add(this.btnMC3C5);
        buttons2.add(this.btnMC3C6);
        buttons2.add(this.btnMC3C7);
        mcT3.add(this.case3Lbl);
        this.mainC3 = new JFrame("Manage a Hotel");
        this.mainC3.setLayout(new BorderLayout());
        this.mainC3.setSize(new Dimension(1080, 720));
        this.mainC3.add(buttons2, BorderLayout.CENTER);
        this.mainC3.add(mcT3, BorderLayout.NORTH);
        this.mainC3.setVisible(false);

        //mc3c1
        this.hotelNameTf = new JTextField();
        this.mc3c1Lbl = new JLabel("New Hotel Name: ");
        this.subMC3C1 = new JButton("Submit");
        this.mc3c1RemarkLbl = new JLabel();
        JPanel mc3_1 = new JPanel();

        this.hotelNameTf.setColumns(20);
        mc3_1.setBackground(Color.LIGHT_GRAY);
        mc3_1.setPreferredSize(new Dimension(250, 100));
        mc3_1.add(this.mc3c1Lbl);
        mc3_1.add(this.hotelNameTf);
        mc3_1.add(this.subMC3C1);
        mc3_1.add(this.mc3c1RemarkLbl);

        this.C3C1 = new JFrame("Hotel Name Change");
        this.C3C1.setLayout(new BorderLayout());
        this.C3C1.setSize(new Dimension (1080, 720));
        this.C3C1.add(mc3_1, BorderLayout.WEST);
        this.C3C1.setVisible(false);

        //mc3c2
        this.roomNumTf2 = new JTextField();
        this.mc3c2Lbl = new JLabel("Room Number: ");
        this.mc3c2RemarkLbl = new JLabel();
        this.subMC3C2 = new JButton("Submit");
        this.mc3c2Area = new JTextArea();
        this.mc32RoomTypeLbl = new JLabel("Room Type: ");
        this.roomTypeTf = new JTextField();
        JPanel mc3_2 = new JPanel();

        this.roomNumTf2.setColumns(10);
        this.roomTypeTf.setColumns(20);
        this.mc3c2Area.setPreferredSize(new Dimension(300, 300));
        this.mc3c2Area.setEditable(false);
        mc3_2.setPreferredSize(new Dimension(250, 100));
        mc3_2.setBackground(Color.LIGHT_GRAY);
        mc3_2.add(this.mc32RoomTypeLbl);
        mc3_2.add(this.roomTypeTf);
        mc3_2.add(this.mc3c2Lbl);
        mc3_2.add(this.roomNumTf2);
        mc3_2.add(this.subMC3C2);
        mc3_2.add(this.mc3c2RemarkLbl);

        this.C3C2 = new JFrame("Add a room");
        this.C3C2.setLayout(new BorderLayout());
        this.C3C2.setSize(new Dimension(1080, 720));
        this.C3C2.add(mc3_2, BorderLayout.WEST);
        this.C3C2.add(this.mc3c2Area, BorderLayout.EAST);
        this.C3C2.setVisible(false);

        //mc3c3
        this.roomNumTf3 = new JTextField();
        this.mc3c3Lbl = new JLabel("Room Index: ");
        this.mc3c3RemarkLbl = new JLabel();
        this.subMC3C3 = new JButton("Submit");
        this.mc3c3Area = new JTextArea();
        JPanel mc3_3 = new JPanel();

        this.roomNumTf3.setColumns(10);
        this.mc3c3Area.setPreferredSize(new Dimension(300, 300));
        this.mc3c3Area.setEditable(false);
        mc3_3.setBackground(Color.LIGHT_GRAY);
        mc3_3.setPreferredSize(new Dimension(250, 100));
        mc3_3.add(this.mc3c3Lbl);
        mc3_3.add(this.roomNumTf3);
        mc3_3.add(this.subMC3C3);
        mc3_3.add(this.mc3c3RemarkLbl);

        this.C3C3 = new JFrame("Remove a room");
        this.C3C3.setLayout(new BorderLayout());
        this.C3C3.setSize(new Dimension(1080, 720));
        this.C3C3.add(mc3_3, BorderLayout.WEST);
        this.C3C3.add(this.mc3c3Area, BorderLayout.EAST);
        this.C3C3.setVisible(false);

        //mc3c4
        this.mc3c4Lbl = new JLabel("Enter new price: ");
        this.mc3c4RemarkLbl = new JLabel();
        this.subMC3C4 = new JButton("Submit");
        this.priceTf = new JTextField();
        JPanel mc3_4 = new JPanel();

        this.priceTf.setColumns(10);
        mc3_4.setPreferredSize(new Dimension(250, 100));
        mc3_4.setBackground(Color.LIGHT_GRAY);
        mc3_4.add(this.mc3c4Lbl);
        mc3_4.add(this.priceTf);
        mc3_4.add(this.subMC3C4);
        mc3_4.add(this.mc3c4RemarkLbl);

        this.C3C4 = new JFrame("Price Change");
        this.C3C4.setLayout(new BorderLayout());
        this.C3C4.setSize(new Dimension(1080, 720));
        this.C3C4.add(mc3_4, BorderLayout.WEST);
        this.C3C4.setVisible(false);

        //mc3c5
        this.mc3c5Area = new JTextArea();
        this.mc3c5Lbl = new JLabel("Reservation Index: ");
        this.mc3c5RemarkLbl = new JLabel();
        this.subMC3C5 = new JButton("Submit");
        this.resRemoveTf = new JTextField();
        JPanel mc3_5 = new JPanel();

        this.resRemoveTf.setColumns(10);
        this.mc3c5Area.setPreferredSize(new Dimension(300, 300));
        this.mc3c5Area.setEditable(false);
        mc3_5.setBackground(Color.LIGHT_GRAY);
        mc3_5.setPreferredSize(new Dimension(250, 100));
        mc3_5.add(this.mc3c5Lbl);
        mc3_5.add(this.resRemoveTf);
        mc3_5.add(this.subMC3C5);
        mc3_5.add(this.mc3c5RemarkLbl);

        this.C3C5 = new JFrame("Remove a reservation");
        this.C3C5.setLayout(new BorderLayout());
        this.C3C5.setSize(new Dimension(1080, 720));
        this.C3C5.add(mc3_5, BorderLayout.WEST);
        this.C3C5.add(this.mc3c5Area, BorderLayout.EAST);
        this.C3C5.setVisible(false);

        //mc3c7
        this.mc3c7Area = new JTextArea();
        this.dateLbl = new JLabel("Day of Month: ");
        this.rateLbl = new JLabel("New Rate for the Day(up to 100%): ");
        this.mc3c7RemarkLbl = new JLabel();
        this.dateTf = new JTextField();
        this.rateTf = new JTextField();
        this.subMC3C7 = new JButton();
        JPanel mc3_7 = new JPanel();

        this.dateTf.setColumns(10);
        this.rateTf.setColumns(10);
        this.mc3c7Area.setPreferredSize(new Dimension(300, 300));
        this.mc3c7Area.setEditable(false);
        mc3_7.setPreferredSize(new Dimension(250, 100));
        mc3_7.setBackground(Color.LIGHT_GRAY);
        mc3_7.add(this.dateLbl);
        mc3_7.add(this.dateTf);
        mc3_7.add(this.rateLbl);
        mc3_7.add(this.rateTf);
        mc3_7.add(this.subMC3C7);
        mc3_7.add(this.mc3c7RemarkLbl);

        this.C3C7 = new JFrame("Change the Rate of Day");
        this.C3C7.setLayout(new BorderLayout());
        this.C3C7.setSize(new Dimension(1080, 720));
        this.C3C7.add(mc3_7, BorderLayout.WEST);
        this.C3C7.add(this.mc3c7Area);
        this.C3C7.setVisible(false);

        //mc4
        this.codDateLbl = new JLabel("Check-Out Date: ");
        this.codDateTf = new JTextField();
        this.codMonthLbl = new JLabel("Check-Out Month: ");
        this.codMonthTf = new JTextField();
        this.codYearLbl = new JLabel("Check-Out Year: ");
        this.codYearTf = new JTextField();
        this.cidYearTf = new JTextField();
        this.cidYearLbl = new JLabel("Check-In Year: ");
        this.cidDateLbl = new JLabel("Check-In Date: ");
        this.cidDateTf = new JTextField();
        this.cidMonthLbl = new JLabel("Check-In Month: ");
        this.cidMonthTf = new JTextField();
        this.resGuestLbl = new JLabel("Guest name: ");
        this.resGuestTf = new JTextField();
        this.hotelTf = new JTextField();
        this.resRoomTf = new JTextField();
        this.resRoomLbl = new JLabel("Room Index: ");
        this.hotelLbl = new JLabel("Hotel Index: ");
        this.employeeLbl = new JLabel("Are you an employee? 1 - yes, 2 - no: ");
        this.employeeTf = new JTextField();
        this.case4Lbl = new JLabel("Reservation Booking");
        this.reservationArea = new JTextArea();
        this.subMC4 = new JButton("Submit");
        this.mc4RemarkLbl = new JLabel();
        JPanel mcT4 = new JPanel();
        JPanel mc4 = new JPanel();

        this.employeeTf.setColumns(10);
        this.hotelTf.setColumns(10);
        this.resRoomTf.setColumns(10);
        this.cidMonthTf.setColumns(10);
        this.cidDateTf.setColumns(10);
        this.cidYearTf.setColumns(10);
        this.codMonthTf.setColumns(10);
        this.codDateTf.setColumns(10);
        this.codYearTf.setColumns(10);
        this.resGuestTf.setColumns(20);
        this.case4Lbl.setFont(new Font("MS UI Gothic", Font.BOLD, 30));
        mcT4.setBackground(new Color(110, 81, 145));
        mcT4.setPreferredSize(new Dimension(720, 300));
        mc4.setBackground(Color.LIGHT_GRAY);
        mc4.setPreferredSize(new Dimension(250, 100));
        this.reservationArea.setPreferredSize(new Dimension(300, 300));
        this.reservationArea.setEditable(false);
        mcT4.add(this.case4Lbl);
        mc4.add(this.hotelLbl);
        mc4.add(this.hotelTf);
        mc4.add(this.resRoomLbl);
        mc4.add(this.resRoomTf);
        mc4.add(this.resGuestLbl);
        mc4.add(this.resGuestTf);
        mc4.add(this.cidMonthLbl);
        mc4.add(this.cidMonthTf);
        mc4.add(this.cidDateLbl);
        mc4.add(this.cidDateTf);
        mc4.add(this.cidYearLbl);
        mc4.add(this.cidYearTf);
        mc4.add(this.codMonthLbl);
        mc4.add(this.codMonthTf);
        mc4.add(this.codDateLbl);
        mc4.add(this.codDateTf);
        mc4.add(this.codYearLbl);
        mc4.add(this.codYearTf);
        mc4.add(this.employeeLbl);
        mc4.add((this.employeeTf));
        mc4.add(this.subMC4);
        mc4.add(this.mc4RemarkLbl);

        this.mainC4 = new JFrame("Booking");
        this.mainC4.setLayout(new BorderLayout());
        this.mainC4.setSize(new Dimension(1080, 720));
        this.mainC4.add(mcT4, BorderLayout.NORTH);
        this.mainC4.add(mc4, BorderLayout.WEST);
        this.mainC4.add(this.reservationArea, BorderLayout.EAST);
        this.mainC4.setVisible(false);

        //ender
        this.mainFrame.setVisible(true);
    }

    public void concatHnItself(String text){
        this.hnItself.setText("Hotel Name: " + text);
    }
    public void concatRcItself(String text){
        this.rcItself.setText("Room Count: " + text);
    }
    public void concatMeItself(String text){
        this.meItself.setText("Monthly Earnings: " + text);
    }
    public void setBtn1Listener(ActionListener actionListener) {
            this.btn1.addActionListener(actionListener);
    }
    public void setBtn2Listener(ActionListener actionListener) {
        this.btn2.addActionListener(actionListener);
    }
    public void setBtn3Listener(ActionListener actionListener) {
        this.btn3.addActionListener(actionListener);
    }
    public void setBtn4Listener(ActionListener actionListener) {
        this.btn4.addActionListener(actionListener);
    }
    public void setMC1SubmitBtnListener(ActionListener actionListener){
        this.subMC1.addActionListener(actionListener);
    }
    public void setMC2C2SubmitBtnListener(ActionListener actionListener){
        this.subMC2C2.addActionListener(actionListener);
    }
    public void setMC2C3SubmitBtnListener(ActionListener actionListener){
        this.subMC2C3.addActionListener(actionListener);
    }
    public void setMC2C4SubmitBtnListener(ActionListener actionListener){
        this.subMC2C4.addActionListener(actionListener);
    }
    public void setMC3C1SubmitBtnListener(ActionListener actionListener){
        this.subMC3C1.addActionListener(actionListener);
    }
    public void setMC3C2SubmitBtnListener(ActionListener actionListener){
        this.subMC3C2.addActionListener(actionListener);
    }
    public void setMC3C3SubmitBtnListener(ActionListener actionListener){
        this.subMC3C3.addActionListener(actionListener);
    }
    public void setMC3C4SubmitBtnListener(ActionListener actionListener){
        this.subMC3C4.addActionListener(actionListener);
    }
    public void setMC3C5SubmitBtnListener(ActionListener actionListener){
        this.subMC3C5.addActionListener(actionListener);
    }
    public void setMC3C7SubmitBtnListener(ActionListener actionListener){
        this.subMC3C7.addActionListener(actionListener);
    }
    public void setMC4SubmitBtnListener(ActionListener actionListener){
        this.subMC4.addActionListener(actionListener);
    }
    public void setBtnMC2C1Listener(ActionListener actionListener){
        this.btnMC2C1.addActionListener(actionListener);
    }

    public void setBtnMC2C2BtnListener(ActionListener actionListener) {
        this.btnMC2C2.addActionListener(actionListener);
    }

    public void setBtnMC2C3Listener(ActionListener actionListener) {
        this.btnMC2C3.addActionListener(actionListener);
    }
    public void setBtnMC2C4Listener(ActionListener actionListener){
        this.btnMC2C4.addActionListener(actionListener);
    }
    public void setBtnMC3C1Listener(ActionListener actionListener){
        this.btnMC3C1.addActionListener(actionListener);
    }
    public void setBtnMC3C2Listener(ActionListener actionListener){
        this.btnMC3C2.addActionListener(actionListener);
    }
    public void setBtnMC3C3Listener(ActionListener actionListener){
        this.btnMC3C3.addActionListener(actionListener);
    }
    public void setBtnMC3C4Listener(ActionListener actionListener){
        this.btnMC3C4.addActionListener(actionListener);
    }
    public void setBtnMC3C5Listener(ActionListener actionListener){
        this.btnMC3C5.addActionListener(actionListener);
    }
    public void setBtnMC3C6Listener(ActionListener actionListener){
        this.btnMC3C6.addActionListener(actionListener);
    }
    public void setBtnMC3C7Listener(ActionListener actionListener){
        this.btnMC3C7.addActionListener(actionListener);
    }

    public void openMainC1(){
        this.mainC1.setVisible(true);
    }
    public void openMainC2(){
        this.mainC2.setVisible(true);
    }
    public void openMainC3(){
        this.mainC3.setVisible(true);
    }
    public void openMainC4(){
        this.mainC4.setVisible(true);
    }
    public void openMainC2C1(){
        this.C2C1.setVisible(true);
    }
    public void openMainC2C2(){
        this.C2C2.setVisible(true);
    }
    public void openMainC2C3(){
        this.C2C3.setVisible(true);
    }
    public void openMainC2C4(){
        this.C2C4.setVisible(true);
    }
    public void openMainC3C1(){
        this.C3C1.setVisible(true);
    }
    public void openMainC3C2(){
        this.C3C2.setVisible(true);
    }
    public void openMainC3C3(){
        this.C3C3.setVisible(true);
    }
    public void openMainC3C4(){
        this.C3C4.setVisible(true);
    }
    public void openMainC3C5(){
        this.C3C5.setVisible(true);
    }
    public void closeHotel(){
        this.mainC2.setVisible(false);
        this.mainC3.setVisible(false);
    }
    public void openMainC3C7(){
        this.C3C7.setVisible(true);
    }

    public void setMainLblText(String text){
        this.mainLbl.setText(text);
    }
    public void setFeedbackLblText(String text) {
        this.remarkLblMc1.setText(text);
    }
    public void setMc2c2RemarkLbl(String text){
        this.mc2c2RemarkLbl.setText(text);
    }
    public void setMc2c3RemarkLbl(String text){
        this.mc2c3RemarkLbl.setText(text);
    }
    public void setMc3c1RemarkLbl(String text){
        this.mc3c1RemarkLbl.setText(text);
    }
    public void setMc3c2RemarkLbl(String text){
        this.mc3c2RemarkLbl.setText(text);
    }
    public void setMc3c3RemarkLbl(String text) {
        this.mc3c3RemarkLbl.setText(text);
    }
    public void setMc3c4RemarkLbl(String text) {
        this.mc3c4RemarkLbl.setText(text);
    }
    public void setMc3c5RemarkLbl(String text) {
        this.mc3c5RemarkLbl.setText(text);
    }
    public void setMc3c7RemarkLbl(String text) {
        this.mc3c7RemarkLbl.setText(text);
    }
    public void setMc4RemarkLbl(String text){
        this.mc4RemarkLbl.setText(text);
    }
    public void setTextAreaText(String text) {
        this.TextArea.setText(text);
    }
    public void setHotelListAreaText(String text){
        this.hotelListArea.setText(text);
    }
    public void setRoomInfoAreaText(String text){
        this.mc2c2Area.setText(text);
    }
    public void setResInfoAreaText(String text){
        this.mc2c3Area.setText(text);
    }
    public void setBookedRoomsAreaText(String text){
        this.mc2c4Area.setText(text);
    }
    public void setRoomListAreaAddText(String text){
        this.mc3c2Area.setText(text);
    }
    public void setRoomListAreaRemText(String text){
        this.mc3c3Area.setText(text);
    }
    public void setResRemAreaText(String text){
        this.mc3c5Area.setText(text);
    }
    public void setDayRateAreaText(String text){
        this.mc3c7Area.setText(text);
    }
    public void setReservationBookingAreaText(String text){
        this.reservationArea.setText(text);
    }
    public int getHotelIndexTfText(){
        return Integer.parseInt(this.hotelIndexTf.getText());
    }
    public String getNameTfText() {
        return this.nameTf.getText();
    }
    public String getSizeTfText(){
        return this.sizeTf.getText();
    }
    public String getMc2c2TfText(){
        return this.roomNumTf1.getText();
    }
    public String getMc2c3TfText(){
        return this.resTf.getText();
    }
    public int getCheckInMonth(){
        return Integer.parseInt(this.mc2c4CiMTf.getText());
    }
    public int getCheckInDate(){
        return Integer.parseInt(this.mc2c4CiDTf.getText());
    }
    public int getCheckInYear(){
        return Integer.parseInt(this.mc2c4CiYTf.getText());
    }
    public int getEmployeeConTfText(){
        return Integer.parseInt(this.employeeTf.getText());
    }
    public String getHotelNameTfText(){
        return this.hotelNameTf.getText();
    }
    public int getAddRoomTfText(){
        return Integer.parseInt(this.roomNumTf2.getText());
    }
    public int getRemoveRoomTfText(){
        return Integer.parseInt(this.roomNumTf3.getText());
    }
    public double getPriceTfText(){
        return Double.parseDouble(this.priceTf.getText());
    }
    public int removeResTfText(){
        return  Integer.parseInt(this.resRemoveTf.getText());
    }
    public int getDateTfText(){
        return Integer.parseInt(this.dateTf.getText());
    }
    public int getRateTfText(){
        return Integer.parseInt(this.rateTf.getText());
    }
    public String getRoomTypeTfText(){
        return this.roomTypeTf.getText();
    }
    public int getHotelIndexRB(){
        return Integer.parseInt(this.hotelTf.getText());
    }
    public int getRoomIndexRB(){
        return Integer.parseInt(this.resRoomTf.getText());
    }
    public String getGuestNameTf(){
        return this.resGuestTf.getText();
    }
    public int getCheckInMonthRB(){
        return Integer.parseInt(this.cidMonthTf.getText());
    }
    public int getCheckInDateRB(){
        return Integer.parseInt(this.cidDateTf.getText());
    }
    public int getCheckInYearRB(){
        return Integer.parseInt(this.cidYearTf.getText());
    }
    public int getCheckOutMonthRB(){
        return Integer.parseInt(this.codMonthTf.getText());
    }
    public int getCheckOutDateRB(){
        return Integer.parseInt(this.codDateTf.getText());
    }
    public int getCheckOutYearRB(){
        return Integer.parseInt(this.codYearTf.getText());
    }
    public void clearTextFieldsMC1() {
        this.nameTf.setText("");
        this.sizeTf.setText("");
    }
    public void clearTextFieldsMC2C2(){
        this.roomNumTf1.setText("");
    }
    public void clearTextFieldsMC2C3(){
        this.resTf.setText("");
    }
    public void clearTextFieldsMC2C4(){
        this.mc2c4CiMTf.setText("");
        this.mc2c4CiDTf.setText("");
        this.mc2c4CiYTf.setText("");
    }
    public void clearTextFieldsMC3C1(){
        this.hotelNameTf.setText("");
    }
    public void clearTextFieldsMC3C2(){
        this.roomNumTf2.setText("");
    }
    public void clearTextFieldsMC3C3(){
        this.roomNumTf3.setText("");
    }
    public void clearTextFieldsMC3C4(){
        this.priceTf.setText("");
    }
    public void clearTextFieldsMC3C5(){
        this.resRemoveTf.setText("");
    }
    public void clearTextFieldsMC3C7(){
        this.dateTf.setText("");
        this.rateTf.setText("");
    }
    public void clearTextFieldsMC4(){
        this.hotelTf.setText("");
        this.resRoomTf.setText("");
        this.resGuestTf.setText("");
        this.cidMonthTf.setText("");
        this.cidDateTf.setText("");
        this.cidYearTf.setText("");
        this.codMonthTf.setText("");
        this.codDateTf.setText("");
        this.codYearTf.setText("");
    }
}
