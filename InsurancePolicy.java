package WEEK1;

import java.util.ArrayList;

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

    public static void printPolicies (ArrayList <InsurancePolicy> policies)
    {
        for (InsurancePolicy ip : policies)
        {
            ip.print();
        }
    }

    public static double calcTotalPayments (ArrayList <InsurancePolicy> policies, double flatRate)
    {
        double totalPayment = 0;
        for (InsurancePolicy ip : policies)
        {
            totalPayment += ip.calcPayment(flatRate);
        }
        return totalPayment;
    }

    public void carPriceRise (double risePercent)
    {
        car.priceRise(risePercent);
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