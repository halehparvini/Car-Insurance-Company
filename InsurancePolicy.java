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

    public int getPolicyID()
    {
        return id;
    }

    public void setPolicyHolderName (String policyHolderName)
    {
        this.policyHolderName = policyHolderName;
    }

    public static void printPolicies (ArrayList <InsurancePolicy> policies) //prints a list of policies
    {
        for (InsurancePolicy policy : policies)
        {
            policy.print();
        }
    }

    public static double calcTotalPayments (ArrayList <InsurancePolicy> policies, double flatRate) //calculates the total premium payments for a list of policies. 
    {
        double totalPayment = 0;
        for (InsurancePolicy policy : policies)
        {
            totalPayment += policy.calcPayment(flatRate);
        }
        return totalPayment;
    }

    public void carPriceRise (double risePercent)
    {
        car.priceRise(risePercent);
    }

    public static void carPriceRiseAll (ArrayList <InsurancePolicy> policies, double risePercent)
    {
        for (InsurancePolicy policy : policies)
        {
            policy.carPriceRise(risePercent);
        }
    }

    public static ArrayList <InsurancePolicy> filterByCarModel (ArrayList <InsurancePolicy> policies, String carModel)
    {
        ArrayList <InsurancePolicy> filteredPolicies = new ArrayList<>();
        for (InsurancePolicy ip : policies)
        {
            if (ip.car.getModel().contains(carModel))
            {
                filteredPolicies.add(ip);
            }
        }
        return filteredPolicies;
    }

    public void setCarModel (String model)
    {
        car.setModel(model);
    }

    public void print ()
    {
        System.out.print("Holder: " + policyHolderName + " ID: " + id + " Car Model: " + car + " Claim(s): " + numberOfClaims + " Expiry Date: " + expiryDate);
    }

    public String toString ()
    {
        return "Holder: " + policyHolderName + " ID: " + id + " Car Model: " + car + " Claim(s): " + numberOfClaims + " Expiry Date: " + expiryDate;
    }

    public abstract double calcPayment (double flatRate);
}