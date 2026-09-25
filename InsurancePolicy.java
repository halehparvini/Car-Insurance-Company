package WEEK1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class InsurancePolicy implements Cloneable, Comparable <InsurancePolicy> 
{
    protected String policyHolderName;
    protected int id;
    protected Car car;
    protected int numberOfClaims;
    protected MyDate expiryDate;

    public InsurancePolicy (String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate) throws PolicyException
    {
        this.policyHolderName = policyHolderName;
        if (id < 3000000 || id > 3999999)
        {
            int generatedID = 3000000 + new Random().nextInt(1000000);
            throw new PolicyException(generatedID);
        }
        this.id = id;
        this.car =car;
        this.numberOfClaims = numberOfClaims;
        this.expiryDate = expiryDate;
    }

    public int getPolicyID()
    {
        return id;
    }
    
    public Car getCar ()
    {
        return car;
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

    public static void printPolicies (HashMap <Integer, InsurancePolicy> policies)
    {
        for (InsurancePolicy policy : policies.values())
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

    public static double calcTotalPayments (HashMap <Integer, InsurancePolicy> policies, double flatRate)
    {
        double totalPayment = 0;
        for (InsurancePolicy policy : policies.values())
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

    public static void carPriceRiseAll (HashMap <Integer, InsurancePolicy> policies, double risePercent)
    {
        for (InsurancePolicy policy : policies.values())
        {
            policy.carPriceRise(risePercent);
        }
    }

    public static ArrayList <InsurancePolicy> filterByCarModel (ArrayList <InsurancePolicy> policies, String carModel)
    {
        ArrayList <InsurancePolicy> filteredPolicies = new ArrayList<>();
        for (InsurancePolicy policy : policies)
        {
            if (policy.car.getModel().contains(carModel))
            {
                filteredPolicies.add(policy);
            }
        }
        return filteredPolicies;
    }

    public static HashMap <Integer, InsurancePolicy> filterByCarModel (HashMap <Integer, InsurancePolicy> policies, String carModel)
    {
        HashMap <Integer, InsurancePolicy> filteredPolicies = new HashMap<>();
        for (InsurancePolicy policy : policies.values())
        {
            if (policy.car.getModel().contains(carModel))
            {
                filteredPolicies.put(policy.getPolicyID(), policy);
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

    // lab 3 

    public static ArrayList <InsurancePolicy> filterByExpiryDate (ArrayList <InsurancePolicy> policies, MyDate date)
    {
        ArrayList <InsurancePolicy> filteredExpiredPolicies = new ArrayList<>();
        for (InsurancePolicy policy : policies)
        {
            if (policy.expiryDate.isExpired(date))
            {
                filteredExpiredPolicies.add(policy);
            }
        }
        return filteredExpiredPolicies;
    }

    public static HashMap <Integer, InsurancePolicy> filterByExpiryDate (HashMap <Integer, InsurancePolicy> policies, MyDate date)
    {
        HashMap <Integer, InsurancePolicy> filteredExpiredPolicies = new HashMap<>();
        for (InsurancePolicy policy : policies.values())
        {
            if (policy.expiryDate.isExpired(date))
            {
                filteredExpiredPolicies.put(policy.getPolicyID(), policy);
            }
        }
        return filteredExpiredPolicies;
    }

    // lab 4
    public InsurancePolicy (InsurancePolicy ip)
    {
        policyHolderName = ip.policyHolderName;
        id = ip.id;
        car = new Car(ip.car);
        numberOfClaims = ip.numberOfClaims;
        expiryDate = new MyDate(ip.expiryDate);
    }

    public InsurancePolicy clone () throws CloneNotSupportedException
    {
        InsurancePolicy policy = (InsurancePolicy)super.clone();
        policy.car = car.clone();
        policy.expiryDate = expiryDate.clone();
        return policy;
    }

    public static ArrayList <InsurancePolicy> shallowCopy (ArrayList <InsurancePolicy> policies)
    {
        ArrayList <InsurancePolicy> shallowCopy = new ArrayList<>();
        for (InsurancePolicy policy : policies)
        {
            shallowCopy.add(policy);
        }
        return shallowCopy;
    }

    public static ArrayList <InsurancePolicy> deepCopy (ArrayList <InsurancePolicy> policies) throws CloneNotSupportedException
    {
        ArrayList <InsurancePolicy> deepCopy = new ArrayList<>();
        for (InsurancePolicy policy : policies)
        {
            deepCopy.add(policy.clone());
        }
        return deepCopy;
    }

    @Override 
    public int compareTo (InsurancePolicy other)
    {
        return expiryDate.compareTo(other.expiryDate);
    }

    // lab 5

    public static ArrayList <InsurancePolicy> shallowCopy (HashMap <Integer, InsurancePolicy> policies)
    {
        ArrayList <InsurancePolicy> shallowCopy = new ArrayList<>();
        for (InsurancePolicy policy : policies.values())
        {
            shallowCopy.add(policy);
        }
        return shallowCopy;
    }

    public static ArrayList <InsurancePolicy> deepCopy (HashMap <Integer, InsurancePolicy> policies) throws CloneNotSupportedException
    {
        ArrayList <InsurancePolicy> deepCopy = new ArrayList<>();
        for (InsurancePolicy policy : policies.values())
        {
            deepCopy.add(policy.clone());
        }
        return deepCopy;
    }

    public static HashMap <Integer, InsurancePolicy> shallowCopyHashMap (HashMap <Integer, InsurancePolicy> policies)
    {
        HashMap <Integer, InsurancePolicy> shallowCopy = new HashMap<>();
        for (InsurancePolicy policy : policies.values())
        {
            shallowCopy.put(policy.getPolicyID(), policy);
        }
        return shallowCopy;
    }

    public static HashMap <Integer, InsurancePolicy> deepCopyHashMap (HashMap <Integer, InsurancePolicy> policies) throws CloneNotSupportedException
    {
        HashMap <Integer, InsurancePolicy> deepCopy = new HashMap<>();
        for (InsurancePolicy policy : policies.values())
        {
            deepCopy.put(policy.getPolicyID(), policy.clone());
        }
        return deepCopy;
    }
}