/**
 * This is the RegularMember class which is also a child class of GymMember.
 * This class represents a regular gym member with specific details
 * such as attendance limit, eligibility for upgrade, removal reason,
 * referral source, plan, and price.
 * @author (Anuma Rawal)
 * @version (09/03/2025 )
 */

public class RegularMember extends GymMember{ //creating RegularMember class inherited from GymMember class
    //declaring variables
    private final int ATTENDANCELIMIT = 30;
    private boolean isEligibleForUpgrade = false;
    private String removalReason = "";
    private String referralSource;
    private String plan = "basic";
    private double price = 6500;

    //creating parameterized constructor
    public RegularMember(int id, String name, String location, String phone, String email, String gender, String dob, String membershipStartDate, String referralSource){
        super(id, name, location, phone, email, gender, dob, membershipStartDate);
        this.referralSource = referralSource;
    }

    //creating accessor method
    public final int getAttendanceLimit(){
        return ATTENDANCELIMIT;
    }

    public boolean getIsEligibleForUpgrade(){
        return isEligibleForUpgrade;
    }

    public String getRemovalReason(){
        return removalReason;
    }

    public String getReferralSource(){
        return referralSource;
    }

    public String getPlan(){
        return plan;
    }

    public double getPrice(){
        return price;
    }

    //creating muttator or setter method
    public void markAttendance(){
        this.attendance++;
        this.loyaltyPoints+=5;
    }

    //creating method named as getPlanPrice() which accepts plan as parameter.
    public static double getPlanPrice(String plan){
        switch(plan){
            case "basic":
                return 6500;
            case "standard":
                return 12500;
            case "deluxe":
                return 18500;
            default:

                return -1;
        }
    }

    //creating method upgradePlan which is used to upgrade the plan subscribed by the member
    /**
     * Here if the member is eligible for upgrading the plan, then the plan and price is updated according to calling
     * getPlanPrice() method.
     * It also displays some appropriate message if the same plan is chosen that the member is currently subscribed to.
     */
    public String upgradePlan(String plan){
        if(getAttendance()>=getAttendanceLimit()){
            isEligibleForUpgrade = true;
        }
        if(isEligibleForUpgrade==true){
            double newPrice = getPlanPrice(plan);
            if (newPrice==-1){
                return("Invalid plan chosen");
            }
            else if(plan.equals(this.plan)){
                return("You have selected same plan that you had subscribed");
            }
            else{
                this.plan = plan;
                this.price = newPrice;
                isEligibleForUpgrade = false;
                return("Plan updated " );
            }
        }
        else{
            return("You are not eligible to upgrade the plan.");
        }
    }

    // creating revertRegularMember() method that accepts removalReason as parameter
    /**
     * Super class method resetMember() is called here,
     * isEligibleForUpgrade is set to false,
     * plan is set to basic,
     * price is set to 6500.
     */
    public void revertRegularMember(String removalReason){
        super.resetMember();
        isEligibleForUpgrade = false;
        plan = "basic";
        price = 6500;

    }

    //creating display method to display output
    public String display(){
        String display = super.display(); // get the string from parent class
        display += "\nPlan: " + plan;
        display += "\nPrice: " + price;

        if (removalReason.length() > 0) {
            display += "\nRemoval Reason: " + removalReason;
        }
        return display;



    }

}


