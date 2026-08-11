package WEEK1;

public abstract class InsurancePolicy
{
    protected String policyHolderName;
    protected int id;
    protected Car car;
    protected int numberOfClaims;
    protected MyDate expiryDate;

    public InsurancePolicy (String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate)
    {
        this.policyHolderName = policyHolderName;
        this.id = id;
        this.car =car;
        this.numberOfClaims = numberOfClaims;
        this.expiryDate = expiryDate;
    }

    public void print ()
    {
        System.out.print("Holder: " + policyHolderName + " ID: " + id + " Car: " + car + " Claim(s): " + numberOfClaims);
    }

    public String toString ()
    {
        return "Holder: " + policyHolderName + " ID: " + id + " Car: " + car + " Claim(s): " + numberOfClaims;
    }

    public abstract double calcPayment (double flatRate);
}