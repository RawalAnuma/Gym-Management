/**
 * This is GymGUI class which implements the GUI.
 * This class has an ArrayList of the GymMember class, which
 * will hold both RegularMember and PremiumMember objects.
 * This class contains the GUI components and action listeners for the buttons.
 * @author (Anuma Rawal)
 * @version (09/03/2025 )
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GymGUI implements ActionListener {
    ArrayList<GymMember> memberList = new ArrayList<GymMember>();
    //declaring variables
    JLabel lblId, lblName, lblLocation, lblPhone, lblEmail, lblGender, lblDOB, lblMembershipStartDate, lblReferralSource, lblPaidAmount, lblRemovalReason, lblTrainerName, lblRegularPlanPrice, lblPremiumPlanPrice, lblDiscountAmount, lblPlan;
    //declaring text fields
    JTextField txtId, txtName, txtLocation, txtPhone, txtEmail, txtReferralSource, txtPaidAmount, txtRemovalReason, txtTrainerName, txtRegularPlanPrice, txtPremiumPlanPrice, txtDiscountAmount;
    //declaring radio buttons and combo boxes
    JRadioButton rdMale, rdFemale;
    JComboBox<String> planCombo;
    JComboBox<String> msYearComboBox, msMonthComboBox, msDayComboBox;
    JComboBox<String> dobYearComboBox, dobMonthComboBox, dobDayComboBox;
    //declaring buttons
    JButton btnMarkAttendance, btnActivateMembership, btnAddRegularMember, btnAddPremiumMember, btnUpgradePlan, btnPayDueAmount, btnCalculateDiscount, btnRevertRegularMember, btnRevertPremiumMember, btnDeactivateMembership, btnClear, btnDisplay, btnSaveToFile, btnReadFromFile;
    
    public GymGUI() {
        JFrame frame = new JFrame("Gym Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Font titleFont = new Font("Arial", Font.BOLD, 24);
        JLabel title = new JLabel("Gym Member Details");
        title.setFont(titleFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(10, 10, 20, 20);//external padding for the title
        frame.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 5, 10, 15);//reset external padding for other components
        gbc.anchor = GridBagConstraints.WEST; //aligns the components to the left side

        // Left side fields

        //Id label
        lblId = new JLabel("ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx=1.0;
        frame.add(lblId, gbc);

        //Id text field
        txtId = new JTextField(20);
        gbc.gridx = 1;
        frame.add(txtId, gbc);

        //Location label
        lblLocation = new JLabel("Location:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(lblLocation, gbc);

        //Location text field
        txtLocation = new JTextField(20);
        gbc.gridx = 1;
        frame.add(txtLocation, gbc);

        //gender label
        lblGender = new JLabel("Gender:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(lblGender, gbc);

        //gender radio buttons
        rdMale = new JRadioButton("Male");
        rdFemale = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();//initializing a ButtonGroup object for grouping the radio buttons
        genderGroup.add(rdMale);//adding male radio button to the group
        genderGroup.add(rdFemale);//adding female radio button to the group

        //panel to hold gender radio buttons
        JPanel genderPanel = new JPanel();

        //adding radio buttons to the panel
        genderPanel.add(rdMale);
        genderPanel.add(rdFemale);
        gbc.gridx = 1;
        frame.add(genderPanel, gbc);//adding the gender panel to the frame

        //DOB label
        lblDOB = new JLabel("DOB:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(lblDOB, gbc);

        //DOB combo boxes
        dobDayComboBox = new JComboBox<>(getDays());
        dobMonthComboBox = new JComboBox<>(getMonths());
        dobYearComboBox = new JComboBox<>(getYears());

        //panel to hold the DOB date combo boxes
        JPanel dobPanel = new JPanel();

        dobPanel.add(dobDayComboBox);//adding day combo box to the panel
        dobPanel.add(dobMonthComboBox);//adding month combo box to the panel
        dobPanel.add(dobYearComboBox);//adding year combo box to the panel
        gbc.gridx = 1;
        frame.add(dobPanel, gbc);//add the dob date panel to the frame

        //referral source label
        lblReferralSource = new JLabel("Referral Source for Regular Member");
        gbc.gridx = 0;
        gbc.gridy = 5;
        frame.add(lblReferralSource, gbc);

        //referral source text field
        txtReferralSource = new JTextField(20);
        gbc.gridx = 1;
        frame.add(txtReferralSource, gbc);

        //premium plan price label
        lblPremiumPlanPrice = new JLabel("Premium Plan Charge:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        frame.add(lblPremiumPlanPrice, gbc);

        //premium plan price text field
        txtPremiumPlanPrice = new JTextField(20);
        gbc.gridx = 1;
        frame.add(txtPremiumPlanPrice, gbc);

        // regular plan price label
        lblRegularPlanPrice = new JLabel("Regular Plan Price:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        frame.add(lblRegularPlanPrice, gbc);

        // regular plan price text field
        txtRegularPlanPrice = new JTextField(20);
        gbc.gridx = 1;
        frame.add(txtRegularPlanPrice, gbc);


        //discount amount label
        lblDiscountAmount = new JLabel("Discount Amt:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        frame.add(lblDiscountAmount, gbc);

        //discount amount text field
        txtDiscountAmount = new JTextField(20);
        gbc.gridx = 1;
        frame.add(txtDiscountAmount, gbc);




        // Right side fields

        lblName = new JLabel("Name:");
        gbc.gridx = 2;
        gbc.gridy = 1;
        frame.add(lblName, gbc);

        //name text field
        txtName = new JTextField(20);
        gbc.gridx = 3;
        frame.add(txtName, gbc);

        //phone label
        lblPhone = new JLabel("Phone:");
        gbc.gridx = 2;
        gbc.gridy = 2;
        frame.add(lblPhone, gbc);

        //phone text field
        txtPhone = new JTextField(20);
        gbc.gridx = 3;
        frame.add(txtPhone, gbc);

        // email label
        lblEmail = new JLabel("Email:");
        gbc.gridx = 2;
        gbc.gridy = 3;
        frame.add(lblEmail, gbc);

        // email text field
        txtEmail = new JTextField(20);
        gbc.gridx = 3;
        frame.add(txtEmail, gbc);

        //membership start date label
        lblMembershipStartDate = new JLabel("Membership Start Date:");
        gbc.gridx = 2;
        gbc.gridy = 4;
        frame.add(lblMembershipStartDate, gbc);

        //membership start date combo boxes
        msDayComboBox = new JComboBox<>(getDays());
        msMonthComboBox = new JComboBox<>(getMonths());
        msYearComboBox = new JComboBox<>(getYears());

        //panel to hold the membership start date combo boxes
        JPanel msDatePanel = new JPanel();
        msDatePanel.add(msDayComboBox);//adding day combo box to the panel
        msDatePanel.add(msMonthComboBox);//adding month combo box to the panel
        msDatePanel.add(msYearComboBox);//adding year combo box to the panel
        gbc.gridx = 3;
        frame.add(msDatePanel, gbc);//adding the date panel to the frame

        //paid amount label
        lblPaidAmount = new JLabel("Paid Amt:");
        gbc.gridx = 2;
        gbc.gridy = 5;
        frame.add(lblPaidAmount, gbc);

        //paid amount text field
        txtPaidAmount = new JTextField(20);
        gbc.gridx = 3;
        frame.add(txtPaidAmount, gbc);


        //trainer name label
        lblTrainerName = new JLabel("Trainer's Name for premium member:");
        gbc.gridx = 2;
        gbc.gridy = 6;
        frame.add(lblTrainerName, gbc);

        //trainer name text field
        txtTrainerName = new JTextField(20);
        gbc.gridx = 3;
        frame.add(txtTrainerName, gbc);

        //plan label
        lblPlan = new JLabel("Plan:");
        gbc.gridx = 2;
        gbc.gridy = 7;
        frame.add(lblPlan, gbc);

        //plan combo box
        planCombo = new JComboBox<>(new String[]{"Basic", "Standard","Deluxe"});
        gbc.gridx = 3;
        frame.add(planCombo, gbc);



        // Upgrade Plan Button
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        btnUpgradePlan = new JButton("Upgrade Plan");
        btnUpgradePlan.setPreferredSize(new Dimension(360,40));
        frame.add(btnUpgradePlan, gbc);





        // Membership Buttons
        btnActivateMembership=new JButton("Activate Membership");
        btnAddRegularMember=new JButton("Add Regular Member");
        btnAddPremiumMember=new JButton("Add Premium Member");
        btnMarkAttendance=new JButton("Mark Attendance");
        btnPayDueAmount=new JButton("Pay Due Amount");
        btnCalculateDiscount=new JButton("Calculate Discount");
        btnRevertRegularMember=new JButton("Revert Regular Member");
        btnRevertPremiumMember=new JButton("Revert Premium Member");
        btnDeactivateMembership=new JButton("Deactivate Membership");

        ButtonGroup buttonGroup= new ButtonGroup();
        buttonGroup.add(btnRevertRegularMember);
        buttonGroup.add(btnMarkAttendance);
        buttonGroup.add(btnRevertPremiumMember);
        buttonGroup.add(btnAddRegularMember);
        buttonGroup.add(btnActivateMembership);
        buttonGroup.add(btnAddPremiumMember);
        buttonGroup.add(btnPayDueAmount);
        buttonGroup.add(btnDeactivateMembership);
        buttonGroup.add(btnCalculateDiscount);



        //creating button panel to hold the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3,3,180,20));

        buttonPanel.add(btnRevertRegularMember);
        buttonPanel.add(btnMarkAttendance);
        buttonPanel.add(btnRevertPremiumMember);
        buttonPanel.add(btnAddRegularMember);
        buttonPanel.add(btnActivateMembership);
        buttonPanel.add(btnAddPremiumMember);
        buttonPanel.add(btnPayDueAmount);
        buttonPanel.add(btnDeactivateMembership);
        buttonPanel.add(btnCalculateDiscount);


        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 4;
        frame.add(buttonPanel, gbc);

        // Removal Reason label
        lblRemovalReason = new JLabel("Removal Reason:");
        gbc.gridy = 10;
        gbc.gridx = 0;
        frame.add(lblRemovalReason, gbc);


        // Removal Reason text field
        txtRemovalReason = new JTextField(52);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        frame.add(txtRemovalReason, gbc);



        // Bottom Buttons
        JPanel bottomPanel = new JPanel(new GridLayout(1,4,140,20));
        btnClear = new JButton("Clear");
        btnDisplay = new JButton("Display");
        btnSaveToFile = new JButton("Save to file");
        btnReadFromFile = new JButton("Read from file");

        bottomPanel.add(btnClear);
        bottomPanel.add(btnDisplay);
        bottomPanel.add(btnSaveToFile);
        bottomPanel.add(btnReadFromFile);

        // Adding action listeners to the buttons
        btnClear.addActionListener(this);
        btnMarkAttendance.addActionListener(this);
        btnActivateMembership.addActionListener(this);
        btnDisplay.addActionListener(this);
        btnAddRegularMember.addActionListener(this);
        btnAddPremiumMember.addActionListener(this);
        btnUpgradePlan.addActionListener(this);
        btnPayDueAmount.addActionListener(this);
        btnCalculateDiscount.addActionListener(this);
        btnRevertRegularMember.addActionListener(this);
        btnRevertPremiumMember.addActionListener(this);
        btnDeactivateMembership.addActionListener(this);
        btnSaveToFile.addActionListener(this);
        btnReadFromFile.addActionListener(this);


        gbc.gridy = 11;
        gbc.gridx = 0;
        gbc.gridwidth =4;
        frame.add(bottomPanel, gbc);
        frame.pack();

        nonEditableFields();
        regularPlanPrice();
        premiumPlanPrice();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    // This method sets the text fields to non-editable
    private void nonEditableFields() {
        txtRegularPlanPrice.setEditable(false);
        txtPremiumPlanPrice.setEditable(false);
        txtDiscountAmount.setEditable(false);
    }

    // This method returns the days of the month
    private String[] getDays() {
        return new String[]{"DD","1","2","3","4","5","6","7","8","9","10","11","12",
                "13","14","15","16","17","18","19","20","21","22",
                "23","24","25","26","27","28","29","30", "31"};
    }

    // This method returns the months of the year
    private String[] getMonths() {
        return new String[]{"MM","1","2","3","4","5","6","7","8","9","10","11","12"};
    }

    // This method returns the years
    private String[] getYears() {
        return new String[]{"YYYY", "2025", "2024", "2023", "2022", "2021", "2020", "2019", "2018",
                "2017", "2016", "2015", "2014", "2013", "2012", "2011",
                "2010", "2009", "2008", "2007", "2006", "2005", "2004",
                "2003", "2002", "2001", "2000"};
    }



    @Override
    // This method handles the action events
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClear) {
            txtId.setText("");
            txtName.setText("");
            txtLocation.setText("");
            txtPhone.setText("");
            txtEmail.setText("");
            rdMale.setSelected(false);
            rdFemale.setSelected(false);
            dobDayComboBox.setSelectedIndex(0);
            dobMonthComboBox.setSelectedIndex(0);
            dobYearComboBox.setSelectedIndex(0);
            txtReferralSource.setText("");
            txtPaidAmount.setText("");
            txtTrainerName.setText("");
            planCombo.setSelectedIndex(0);
            msDayComboBox.setSelectedIndex(0);
            msMonthComboBox.setSelectedIndex(0);
            msYearComboBox.setSelectedIndex(0);
            txtRemovalReason.setText("");
            txtRegularPlanPrice.setText("");
            txtPremiumPlanPrice.setText("");
            txtDiscountAmount.setText("");
            JOptionPane.showMessageDialog(null, "Cleared Successfully");
        }

        if (e.getSource() == btnAddRegularMember) {
            if (validateRegularMember() && validateId() && uniqueIdCheck()) {
               String genderGroup = rdMale.isSelected() ? "Male" : "Female";
                addRegularMember(Integer.parseInt(txtId.getText()), txtName.getText(), txtLocation.getText(), txtPhone.getText(), txtEmail.getText(), genderGroup, dobDayComboBox.getSelectedItem().toString() + "/" + dobMonthComboBox.getSelectedItem().toString() + "/" + dobYearComboBox.getSelectedItem().toString(), msDayComboBox.getSelectedItem().toString() + "/" + msMonthComboBox.getSelectedItem().toString() + "/" + msYearComboBox.getSelectedItem().toString(), txtReferralSource.getText());
            }
        }

        if (e.getSource() == btnAddPremiumMember) {
            if (validatePremiumMember() && validateId() && uniqueIdCheck()) {
                String genderGroup = rdMale.isSelected() ? "Male" : "Female";
                addPremiumMember(Integer.parseInt(txtId.getText()), txtName.getText(), txtLocation.getText(), txtPhone.getText(), txtEmail.getText(), genderGroup, dobDayComboBox.getSelectedItem().toString() + "/" + dobMonthComboBox.getSelectedItem().toString() + "/" + dobYearComboBox.getSelectedItem().toString(), msDayComboBox.getSelectedItem().toString() + "/" + msMonthComboBox.getSelectedItem().toString() + "/" + msYearComboBox.getSelectedItem().toString(), txtTrainerName.getText());
            }
        }


        if (e.getSource() == btnActivateMembership) {
            if (validateId()) {
                Boolean found = false;
                if (memberList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No members to activate");
                } else {
                    for (GymMember member : memberList) {
                        if (member.getId() == (Integer.parseInt(txtId.getText()))) {
                            member.activateMembership();
                            JOptionPane.showMessageDialog(null, "Membership activated successfully");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Member not found");
                    }
                }
            }
        }

        if (e.getSource() == btnDeactivateMembership) {
            if (validateId()) {
                Boolean found = false;
                if (memberList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No members to deactivate");
                } else {
                    for (GymMember member : memberList) {
                        if (member.getId() == (Integer.parseInt(txtId.getText()))) {
                            JOptionPane.showMessageDialog(null, member.deactivateMembership());
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Member not found");
                    }
                }
            }
        }


        if (e.getSource() == btnDisplay) {
            if (memberList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No members to display");

            } else {
                String allMembers = "";

                for (GymMember member : memberList) {
                    allMembers += member.display() + "\n\n";
                }
                JTextArea memberDetailsTxtArea = new JTextArea(allMembers);
                memberDetailsTxtArea.setEditable(false);
                memberDetailsTxtArea.setMargin(new Insets(10, 10, 10, 10));
                JScrollPane scrollPane = new JScrollPane(memberDetailsTxtArea);

                JFrame displayFrame = new JFrame("All Member Details");
                displayFrame.setSize(500, 500);
                displayFrame.add(scrollPane);
                displayFrame.setLocationRelativeTo(null);
                displayFrame.setVisible(true);

            }
        }

        if (e.getSource() == btnMarkAttendance) {
            if (validateId()) {
                Boolean found = false;
                if (memberList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No members to mark attendance");
                } else {
                    for (GymMember member : memberList) {
                        if (member.getId() == (Integer.parseInt(txtId.getText()))) {
                            member.markAttendance();
                            JOptionPane.showMessageDialog(null, "Attendance marked successfully");
                            found = true;
                            break;
                        }

                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Member not found");
                    }

                }
            }
        }

        if (e.getSource() == btnRevertRegularMember) {
            if (validateId()) {
                Boolean found = false;
                if (memberList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No members to revert");
                } else {
                    for (GymMember member : memberList) {
                        if (member.getId() == (Integer.parseInt(txtId.getText()))) {
                            if (member instanceof RegularMember) {
                                ((RegularMember) member).revertRegularMember(txtRemovalReason.getText());
                                JOptionPane.showMessageDialog(null, "Regular Member reverted successfully");
                                found = true;
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Member is not a Regular Member");
                                found = true;
                            }
                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Member not found");
                    }
                }
            }
        }

        if (e.getSource() == btnRevertPremiumMember) {
            if (validateId()) {
                Boolean found = false;
                if (memberList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No members to revert");
                } else {
                    for (GymMember member : memberList) {
                        if (member.getId() == (Integer.parseInt(txtId.getText()))) {
                            if (member instanceof PremiumMember) {
                                ((PremiumMember) member).revertPremiumMember();
                                JOptionPane.showMessageDialog(null, "Premium Member reverted successfully");
                                found = true;
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Member is not a Premium Member");
                                found = true;
                            }
                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Member not found");
                    }
                }
            }
        }

        if (e.getSource() == btnUpgradePlan) {
            if (validateId()) {
                Boolean found = false;
                if (memberList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No members to upgrade");
                } else {
                    for (GymMember member : memberList) {
                        if (member.getId() == (Integer.parseInt(txtId.getText()))) {
                            if (member instanceof RegularMember) {

                                JOptionPane.showMessageDialog(null, ((RegularMember) member).upgradePlan(planCombo.getSelectedItem().toString().toLowerCase()));
                                found = true;
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Member is not a Regular Member");
                                found = true;
                            }
                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Member not found");
                    }
                }
            }
        }

        if (e.getSource() == btnPayDueAmount) {
            if (validateId()) {
                Boolean found = false;
                if (memberList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No members to pay due amount");
                } else {
                    for (GymMember member : memberList) {
                        if (member.getId() == (Integer.parseInt(txtId.getText()))) {
                            if (member instanceof PremiumMember){
                                if (txtPaidAmount.getText().isEmpty()){
                                    JOptionPane.showMessageDialog(null, "Please enter the amount paid");
                                    found = true;
                                    break;
                                }
                                else{
                                    try {
                                        double paidAmount = Double.parseDouble(txtPaidAmount.getText());
                                        JOptionPane.showMessageDialog(null, ((PremiumMember) member).payDueAmount(paidAmount));
                                        found = true;
                                        break;
                                    }catch(NumberFormatException exception){
                                        JOptionPane.showMessageDialog(null, "Please enter a valid number of paid amount.");
                                    }catch(Exception exception){
                                        JOptionPane.showMessageDialog(null, "An error occurred: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    }

                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Member is not a Premium Member");
                                found = true;
                            }
                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Member not found");
                    }
                }
            }
        }
        if (e.getSource() == btnCalculateDiscount) {
            if (validateId()) {
                Boolean found = false;
                if (memberList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No members to calculate discount");
                } else {
                    for (GymMember member : memberList) {
                        if (member.getId() == (Integer.parseInt(txtId.getText()))) {
                            if (member instanceof PremiumMember) {
                                double discount = ((PremiumMember) member).calculateDiscount();
                                txtDiscountAmount.setText("" + discount);
                                JOptionPane.showMessageDialog(null, "Discount calculated successfully");
                                found = true;
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Member is not a Premium Member");
                                found = true;
                            }
                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Member not found");
                    }
                }
            }
        }

        if (e.getSource()==btnSaveToFile){
            if (memberList.isEmpty()){
                JOptionPane.showMessageDialog(null, "No members added to save to file");
            }
            else{
                try(FileWriter writer = new FileWriter("GymMember_Details.txt")){
                    writer.write("Regular Members:\n"+ "\n");

                    for (GymMember member : memberList) {
                        if (member instanceof RegularMember) {
                            writer.write(member.display() + "\n"+ "\n");
                        }
                    }
                    writer.write("Premium Members:\n");
                    for (GymMember member : memberList) {
                        if (member instanceof PremiumMember) {
                            writer.write(member.display() + "\n" + "\n");
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Members data saved to file successfully");
                }
                catch (IOException exception){
                    JOptionPane.showMessageDialog(null, "Error saving to file: " + exception.getMessage());
                }
            }
        }

        if(e.getSource()==btnReadFromFile){
            try{
                File file = new File("GymMember_Details.txt");
                Scanner scanner = new Scanner(file);
                StringBuilder fileContent = new StringBuilder();
                while (scanner.hasNextLine()){
                    fileContent.append(scanner.nextLine()).append("\n");
                }
                scanner.close();

                JFrame fileFrame = new JFrame("Saved Gym Members details");
                JTextArea fileTextArea = new JTextArea(fileContent.toString());
                fileTextArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(fileTextArea);
                fileFrame.add(scrollPane);
                fileFrame.setSize(500, 500);
                fileFrame.setLocationRelativeTo(null);
                fileFrame.setVisible(true);
            }catch (FileNotFoundException ex){
                JOptionPane.showMessageDialog(null, "File not found: " + ex.getMessage());
            }
        }
    }

    private void regularPlanPrice() {
        String plan = planCombo.getSelectedItem().toString().toLowerCase();
        txtRegularPlanPrice.setText(""+RegularMember.getPlanPrice(plan));
    }

    private void premiumPlanPrice() {
        txtPremiumPlanPrice.setText(""+PremiumMember.getPremiumCharge());
    }


    // This method validates the input fields
    boolean AddMemberValidateInput(){
        if(txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtLocation.getText().isEmpty() || txtPhone.getText().isEmpty() || txtEmail.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return false;
        }
        if(!rdMale.isSelected() && !rdFemale.isSelected()){
            JOptionPane.showMessageDialog(null,"Please select a gender");
        }

        if(txtPhone.getText().length() != 10){
            JOptionPane.showMessageDialog(null, "Please enter a valid phone number");
            return false;
        }

        if(txtEmail.getText().contains("@") == false){
            JOptionPane.showMessageDialog(null, "Please enter a valid email address");
            return false;
        }

        if(dobDayComboBox.getSelectedIndex()==0 || dobMonthComboBox.getSelectedIndex()==0 || dobYearComboBox.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Please select a valid date of birth");
            return false;
        }

        if(msDayComboBox.getSelectedIndex()==0 || msMonthComboBox.getSelectedIndex()==0 || msYearComboBox.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Please select a valid membership start date");
            return false;
        }

        return true;
    }

    boolean validateRegularMember(){
        if(!AddMemberValidateInput() || txtReferralSource.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Required fields for regular member are not filled up");
            return false;
        }
        return true;
    }

    boolean validatePremiumMember(){
        if(!AddMemberValidateInput() || txtTrainerName.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Required fields for premium member are not filled up");
            return false;
        }
        return true;
    }
    // This method validates the ID field
    boolean validateId(){
        try{
            int id = Integer.parseInt(txtId.getText());
            if(id<0){
                JOptionPane.showMessageDialog(null, "Please enter a valid ID");
                return false;
            }

        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Please enter a valid ID");
            return false;
        }
        return true;

    }
    // This method checks if the ID is unique
    private boolean uniqueIdCheck(){
        int id = Integer.parseInt(txtId.getText());
        for(GymMember member : memberList){
            if(member.getId() == id){
                JOptionPane.showMessageDialog(null, "Please enter a unique ID. This id already exists");
                return false;
            }
        }
        return true;
    }

    // This method adds a regular member to the member list
    private void addRegularMember(int id, String name, String location, String phone, String email, String gender, String DOB, 
                                  String membershipStartDate, String referralSource) {
        RegularMember regularMember = new RegularMember(id, name, location, phone, email, gender, DOB, membershipStartDate,
                                      referralSource);
        memberList.add(regularMember);
        JOptionPane.showMessageDialog(null, "Regular Member has been added successfully");
    }
    // This method adds a premium member to the member list
    private void addPremiumMember(int id, String name, String location, String phone, String email, String gender, String DOB,
                                  String membershipStartDate, String trainerName){
        PremiumMember premiumMember = new PremiumMember(id, name, location, phone, email, gender, DOB, membershipStartDate, 
                                      trainerName);
        memberList.add(premiumMember);
        JOptionPane.showMessageDialog(null, "Premium Member has been added successfully");
    }


    public static void main(String[] args) {
        GymGUI gymGUI= new GymGUI();
    }
}

















