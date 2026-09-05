package WEEK1;

import java.util.ArrayList;

public class User
{
    private String name; //the name of the account holder
    private int userID; //the user ID/number
    private Address address;
    private static int count;
    ArrayList <InsurancePolicy> policies; //list of all the Insurance Policies this user hold

    public User (String name, Address address)
    {
        this.name = name;
        this.userID = ++count;
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
        // for (InsurancePolicy policy : policies)
        // {
        //     policy.print();
        // }
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

    public ArrayList <String> populateDistinctCarModels ()
    {
        ArrayList <String> models = new ArrayList<>();
        for (InsurancePolicy policy : policies)
            {
                String model = policy.getCar().getModel();
                if (!models.contains(model))
                {
                    models.add(model);
                }
            } 
            return models;
    }

    public int getTotalCountForCarModel (String carModel)
    {
        int count = 0;
        for (InsurancePolicy policy : policies)
        {
            String model = policy.getCar().getModel();
            if (model.equals(carModel))
            {
            count++;
            }
        }
        return count;
    }

    public double getTotalPaymentForCarModel (String carModel)
    {
        double total = 0;
        for (InsurancePolicy policy : policies)
        {
            String model = policy.getCar().getModel();
            if (model.equals(carModel))
            {
                total += policy.calcPayment(flatRate);
            }
        }
        return total;
    }

    public ArrayList <Integer> getTotalCountPerCarModel (ArrayList <String> carModels)
    {
        ArrayList <Integer> totalCounts = new ArrayList<>();
        for (String model : carModels)
        {
            totalCounts.add(getTotalCountForCarModel(model));
        }
        return totalCounts;
    }

    public ArrayList <Double> getTotalPaymentPerCarModel (ArrayList <String> carModels)
    {
        ArrayList <Double> totalPayments = new ArrayList<>();
        for (String model : carModels)
        {
            totalPayments.add(getTotalPaymentForCarModel(model));
        }
        return totalPayments;
    }

    public void reportPaymentsPerCarModel (ArrayList <String> carModels, ArrayList <Integer> counts, ArrayList <Double> premiumPayments)
    {
        System.out.println("Car Model \t\t Total Premium Payment \t\t Average Premium Payment");
        for (int i = 0; i < carModels.size(); i++)
        {
            String model = carModels.get(i);
            int count = counts.get(i);
            double payment = premiumPayments.get(i);
            double average = payment / count;
            System.out.println(model + "\t\t" + payment + "$" + "\t\t" + average + "$");
        }
    }
}