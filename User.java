package WEEK1;

import java.util.ArrayList;

public class User
{
    private String name; //the name of the account holder
    private int userID; //the user ID/number
    private Address address;
    ArrayList <InsurancePolicy> policies; //list of all the Insurance Policies this user hold

    public User (String name, int userID, Address address)
    {
        this.name = name;
        this.userID = userID;
        this.address = address;
        policies = new ArrayList<InsurancePolicy>();
    }

    public String getName ()
    {
        return name;
    }

    public int getUserID ()
    {
        return userID;
    }

    public Address getAddress ()
    {
        return address;
    }

    public void setAddress (Address address)
    {
        this.address = address;
    }

    public boolean addPolicy (InsurancePolicy policy)
    {
        if (findPolicy(policy.getPolicyID()) == null)
        {
            policies.add(policy);
            return true;
        }
        else
            return false;
    }

    public InsurancePolicy findPolicy (int policyID)
    {
        for (InsurancePolicy policy : policies)
        {
            if (policy.getPolicyID() == policyID)
                return policy;
        }
        return null;
    }

    public void print ()
    {
        System.out.println("User Name: " + name + " ID: " + userID + " Address: " + address);
        for (InsurancePolicy policy : policies)
        {
            policy.print();
        }
    }

    public String toString ()
    {
        String result =  "User Name: " + name + " ID: " + userID + " Address: " + address;
        
        for (InsurancePolicy policy : policies)
        {
            result += "\n" + policy.toString();
        }
        return result;
    }

    public void printPolicies (int flatRate)
    {
        System.out.println(name +"'s Policies: ");
        for (InsurancePolicy policy : policies)
        {
            policy.print();
            System.out.println("Premium Payment: " + policy.calcPayment(flatRate));
        }
    }

    public double calcTotalPremiums (double flatRate)
    {
        return InsurancePolicy.calcTotalPayments(policies, flatRate);
    }

    public void carPriceRiseAll (double risePercent)
    {
        InsurancePolicy.carPriceRiseAll(policies, risePercent);
    }

    public ArrayList <InsurancePolicy> filterByCarModel (String carModel)
    {
        return InsurancePolicy.filterByCarModel(policies, carModel);
    }

    public void setCity (String city)
    {
        address.setCity(city);
    }

    // lab 3

    public boolean createThirdPartyPolicy (String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments)
    {
        for (InsurancePolicy policy : policies)
            {
                if (policy.getPolicyID() == id)
                    return false;
            }   

            ThirdPartyPolicy thirdPartyPolicy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
            addPolicy(thirdPartyPolicy);
            return true;
    }

    public boolean createComprehensivePolicy (String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level)
    {
        for (InsurancePolicy policy : policies)
        {
            if (policy.getPolicyID() == id)
            {
                return false;
            }
        }
        ComprehensivePolicy comprehensivePolicy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level);
        addPolicy(comprehensivePolicy);
        return true;
    }

    public ArrayList <InsurancePolicy> filterByExpiryDate (MyDate date)
    {
        return InsurancePolicy.filterByExpiryDate(policies, date);
    }
}