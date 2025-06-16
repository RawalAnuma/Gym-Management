/**
 * This is the PremiumMember class and the subclass of the GymMember
 * This class represents a premium member with additional features
 * such as personal trainer, payment status, and discount calculation.
 * @author (Anuma Rawal)
 * @version (09/03/2025 )
 */
//creating class PremiumMember inherited from super class GymMember
public class PremiumMember extends GymMember{
    //declaring variables
    static final double PREMIUMCHARGE = 50000;
    String personalTrainer;
    boolean isFullPayment;
    double paidAmount;
    double discountAmount;

    //creating parameterized constructor
    public PremiumMember(int id, String name, String location, String phone, String email, String gender, String dob, String membershipStartDate, String personalTrainer){
        super(id, name, location, phone, email,gender, dob, membershipStartDate);

        this.isFullPayment = false; // initially set to false
        this.paidAmount = 0; // initially set to 0
        this.discountAmount = 0; // initially set to 0
        this.personalTrainer = personalTrainer;

    }


    //creating accessor method
    public static final double getPremiumCharge(){
        return PREMIUMCHARGE;
    }

    public String getPersonalTrainer(){
        return personalTrainer;
    }

    public boolean getIsFullPayment(){
        return isFullPayment;
    }

    public double getPaidAmount(){
        return paidAmount;
    }

    public double getDiscountAmount(){
        return discountAmount;
    }

    public void markAttendance(){
        this.attendance++;
        this.loyaltyPoints+=5;
    }

    //creating setter method
    /**
     * If the payment is full, a suitable message is displayed and returned,
     * If the payment is more than premium charge, a suitable message is displayed and returned,
     * If the payment is not full, we made a variable named as remainingAmount which will stored the value of the remaining amount to be paid,
     * If the payment is equal to premium charge then isFullPayment is set to true otherwise false.
     */
    public String payDueAmount(double paidAmount){
        double newPaidAmount = this.paidAmount + paidAmount;
        if(this.isFullPayment == true){
            return("The full payment is done");
        }
        else if(newPaidAmount>this.PREMIUMCHARGE){
            return("The paid amount exceeds more than the due amount");
        }
        else{
            this.paidAmount = newPaidAmount;
            double remainingAmount = this.PREMIUMCHARGE - this.paidAmount;
            if(this.paidAmount==PREMIUMCHARGE){
                this.isFullPayment=true;
            }
            else{
                this.isFullPayment=false;
            }
            return("The payment has been successful. The remaining amount is "+ remainingAmount);
        }
    }

    /**
     * Here we created a method named as calculateDiscount which calculates the discount amount based on the payment status,
     * If payment is full the premium member gets 10% discount,
     * After calculating discount, the value of discountAmount attribute is updated and suitable message is displayed.
     */
    public double calculateDiscount(){
        if(this.isFullPayment){
            this.discountAmount = 0.1*this.PREMIUMCHARGE;
            System.out.println("The discount amount you will get is "+ this.discountAmount);
        }
        else{
            System.out.println("No discount available");
        }
        return this.discountAmount;
    }
    //creating revertPremiumMember() method
    public void revertPremiumMember(){
        super.resetMember(); // calls the super class resetMember() method
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    //creating display method to display output
    public String display(){
        String display = super.display(); // get parent class details

        display += "\nPersonal Trainer: " + getPersonalTrainer();
        display += "\nThe paid amount is: " + getPaidAmount();
        display += "\nIs full payment made? " + getIsFullPayment();

        double remainingAmount = this.PREMIUMCHARGE - this.paidAmount;
        display += "\nThe remaining amount to be paid: " + remainingAmount;

        // Append discount only if full payment is done
        if (this.isFullPayment) {
            display += "\nThe discount amount: " + this.discountAmount;
        }

        return display;
    }
}