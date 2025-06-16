/**
 * This is a GymMember class which is the parent class of RegularMember and PremiumMember.
 * This is an abstract class and contains one abstract method.
 * @author (Anuma Rawal)
 * @version (09/03/2025 )
 */

public abstract class GymMember{  //creating GymMember class
    //declaring variables
    protected int id;
    protected int attendance;
    protected String name, location, phone, email, gender, dob, membershipStartDate;
    protected double loyaltyPoints;
    protected boolean activeStatus;

    //This is a constructor of GymMember class
    public GymMember(int id, String name,String location, String phone, String email, String gender, String dob, String membershipStartDate){
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.membershipStartDate = membershipStartDate;
        this.attendance = 0;
        this.activeStatus = false;
        this.loyaltyPoints = 0;
    }

    //creating accessor method
    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getLocation(){
        return location;
    }

    public String getPhone(){
        return phone;
    }

    public String getEmail(){
        return email;
    }

    public String getGender(){
        return gender;
    }

    public String getDob(){
        return dob;
    }

    public String getMembershipStartDate(){
        return membershipStartDate;
    }

    public int getAttendance(){
        return attendance;
    }

    public double getLoyaltyPoints(){
        return loyaltyPoints;
    }

    public boolean getActiveStatus(){
        return activeStatus;
    }

    /**
     * An abstract method which don't have a body
     * This abstract method tracks attendance of the gym member.
     */
    public abstract void markAttendance();

    public void activateMembership(){
        activeStatus = true;
    }

    public String deactivateMembership(){
        if(activeStatus){
            activeStatus = false;
            return ("Your account has been deactivated");
        }
        else{
            return ("Your account is already deactivated");
        }
    }

    /*This resetMember method set activeStatus to false,
     * attendance to zero and loyaltyPoints to zero.
     */
    public void resetMember(){
        activeStatus = false;
        attendance = 0;
        loyaltyPoints = 0;
    }

    //Display method displays the output//
    public String display(){
        return "ID: " + getId() + "\n" +
                "Name: " + getName() + "\n" +
                "Location: " + getLocation() + "\n" +
                "Phone: " + getPhone() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Gender: " + getGender() + "\n" +
                "Date of Birth: " + getDob() + "\n" +
                "Membership Start: " + getMembershipStartDate() + "\n" +
                "Attendance: " + getAttendance() + "\n" +
                "Loyalty Points: " + getLoyaltyPoints() + "\n" +
                "Active Status: " + getActiveStatus();
    }
}