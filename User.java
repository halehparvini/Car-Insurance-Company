package WEEK1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class User implements Cloneable, Comparable <User>
{
    private String name; //the name of the account holder
    private int userID; //the user ID/number
    private Address address;
    private static int count;
    //ArrayList <InsurancePolicy> policies; //list of all the Insurance Policies this user hold
    HashMap <Integer, InsurancePolicy> policies;
    
    public User (String name, Address address)
    {
        this.name = name;
        this.userID = ++count;
        this.address = address;
        policies = new HashMap<>();
    }
    
    public static void resetCount()
    {
        count = 0;
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

    public HashMap <Integer, InsurancePolicy> getPolicies ()
    {
        return policies;
    }

    public void setAddress (Address address)
    {
        this.address = address;
    }

    public boolean addPolicy (InsurancePolicy policy)
    {
        // if (findPolicy(policy.getPolicyID()) == null)
        // {
        //     policies.add(policy);
        //     return true;
        // }
        // else
        //     return false;
        
        if (findPolicy(policy.getPolicyID()) == null)
            {
                policies.put(policy.getPolicyID(), policy);
                return true;
            }
        else
            return false;
    }

    public InsurancePolicy findPolicy (int policyID)
    {
        // for (InsurancePolicy policy : policies)
        // {
        //     if (policy.getPolicyID() == policyID)
        //         return policy;
        // }
        // return null;
        return policies.get(policyID);
    }

    public void print ()
    {
        //System.out.println("\u001B[34mUser Name: " + name + " ID: " + userID + " Address: " + address + "\u001B[0m");
        // for (InsurancePolicy policy : policies)
        // {
        //     policy.print();
        // }
        System.out.println("\u001B[34mUser Name: " + name + " ID: " + userID + " Address: " + address + "\u001B[0m");
        InsurancePolicy.printPolicies(policies);
    }

    public String toString ()
    {
        String result =  "User Name: " + name + " ID: " + userID + " Address: " + address;
        
        for (InsurancePolicy policy : policies.values())
        {
            result += "\n" + policy.toString();
        }
        return result;
    }

    public void printPolicies (int flatRate)
    {
        // for (InsurancePolicy policy : policies)
        // {
        //     System.out.println("Premium Payment: " + policy.calcPayment(flatRate));
        // }
        for (InsurancePolicy policy : policies.values())
        {
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

    public HashMap <Integer, InsurancePolicy>  filterByCarModel (String carModel)
    {
        // return InsurancePolicy.filterByCarModel(policies, carModel);
        return InsurancePolicy.filterByCarModel(policies, carModel);
    }

    public void setCity (String city)
    {
        address.setCity(city);
    }

    // lab 3

    public boolean createThirdPartyPolicy (String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) throws PolicyException
    {
        // for (InsurancePolicy policy : policies)
        // {
        //     if (policy.getPolicyID() == id)
        //         return false;
        // }   

        // ThirdPartyPolicy thirdPartyPolicy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
        // addPolicy(thirdPartyPolicy);
        // return true;
        for (InsurancePolicy policy : policies.values())
        {
            if (policy.getPolicyID() == id)
                return false;
        }  
        ThirdPartyPolicy thirdPartyPolicy; 
        try
        {
            thirdPartyPolicy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
        }
        catch (PolicyException e)
        {
            thirdPartyPolicy = new ThirdPartyPolicy(policyHolderName, e.getID(), car, numberOfClaims, expiryDate, comments);
            addPolicy(thirdPartyPolicy);
            throw e;
        }
        addPolicy(thirdPartyPolicy);
        return true;
    }

    public boolean createComprehensivePolicy (String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) throws PolicyException
    {
        // for (InsurancePolicy policy : policies)
        // {
        //     if (policy.getPolicyID() == id)
        //     {
        //         return false;
        //     }
        // }
        // ComprehensivePolicy comprehensivePolicy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level);
        // addPolicy(comprehensivePolicy);
        // return true;
        for (InsurancePolicy policy : policies.values())
        {
            if (policy.getPolicyID() == id)
            {
                return false;
            }
        }
        ComprehensivePolicy comprehensivePolicy;
        try
        {
            comprehensivePolicy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level);
        }
        catch (PolicyException e)
        {
            comprehensivePolicy = new ComprehensivePolicy(policyHolderName, e.getID(), car, numberOfClaims, expiryDate, driverAge, level);
            addPolicy(comprehensivePolicy);
            throw e;
        }
        addPolicy(comprehensivePolicy);
        return true;
    }

    public HashMap <Integer, InsurancePolicy>  filterByExpiryDate (MyDate date)
    {
        // return InsurancePolicy.filterByExpiryDate(policies, date);
        return InsurancePolicy.filterByExpiryDate(policies, date);
    }

    public ArrayList <String> populateDistinctCarModels ()
    {
        // ArrayList <String> models = new ArrayList<>();
        // for (InsurancePolicy policy : policies)
        //     {
        //         String model = policy.getCar().getModel();
        //         if (!models.contains(model))
        //         {
        //             models.add(model);
        //         }
        //     } 
        //     return models;
        ArrayList <String> models = new ArrayList<>();
        for (InsurancePolicy policy : policies.values())
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
        // int count = 0;
        // for (InsurancePolicy policy : policies)
        // {
        //     String model = policy.getCar().getModel();
        //     if (model.equals(carModel))
        //     {
        //     count++;
        //     }
        // }
        // return count;
        int count = 0;
        for (InsurancePolicy policy : policies.values())
        {
            String model = policy.getCar().getModel();
            if (model.equals(carModel))
            {
            count++;
            }
        }
        return count;
        
    }

    public double getTotalPaymentForCarModel (String carModel, double flatRate)
    {
        // double total = 0;
        // for (InsurancePolicy policy : policies)
        // {
        //     String model = policy.getCar().getModel();
        //     if (model.equals(carModel))
        //     {
        //         total += policy.calcPayment(flatRate);
        //     }
        // }
        // return total;
        double total = 0;
        for (InsurancePolicy policy : policies.values())
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

    public ArrayList <Double> getTotalPaymentPerCarModel (ArrayList <String> carModels, double flatRate)
    {
        ArrayList <Double> totalPayments = new ArrayList<>();
        for (String model : carModels)
        {
            totalPayments.add(getTotalPaymentForCarModel(model, flatRate));
        }
        return totalPayments;
    }

    public void reportPaymentsPerCarModel (ArrayList <String> carModels, ArrayList <Integer> counts, ArrayList <Double> premiumPayments)
    {
        System.out.printf("%-20s %-25s %-25s%n","Car Model", "Total Premium Payment", "Average Premium Payment");
        for (int i = 0; i < carModels.size(); i++)
        {
            String model = carModels.get(i);
            int count = counts.get(i);
            double payment = premiumPayments.get(i);
            double average = payment / count;
            System.out.printf("%-20s $%-25.2f $%-25.2f%n", model, payment, average);
        }
    }

    public boolean removePolicy (int policyID)
    {
        InsurancePolicy policy = findPolicy(policyID);
        if (policy != null)
        {
            policies.remove(policyID);
            return true;
        }
        else
        {
            return false;
        }
    }

    // lab 4
    public User (User user)
    {
        name = user.name;
        userID = user.userID;
        address = new Address(user.address);
        //policies = new ArrayList<>();
        policies = new HashMap<>();
        for (InsurancePolicy policy : user.policies.values())
        {
            if (policy instanceof ThirdPartyPolicy)
            {
                policies.put(policy.getPolicyID(), new ThirdPartyPolicy((ThirdPartyPolicy) policy));
            }
            else if (policy instanceof ComprehensivePolicy)
            {
                policies.put(policy.getPolicyID(), new ComprehensivePolicy((ComprehensivePolicy) policy));
            }
        }
    }
    
    public User clone () throws CloneNotSupportedException
    {
        User user = (User)super.clone();
        user.address = address.clone();
        //user.policies = new ArrayList<>();
        user.policies = new HashMap<>();
        for (InsurancePolicy policy : policies.values())
        {
            user.policies.put(policy.getPolicyID(), policy.clone());
        }
        return user;
    }

    // public static ArrayList <User> shallowCopy (ArrayList <User> users)
    // {
    //     ArrayList <User> shallowCopy = new ArrayList<>();
    //     for (User user : users)
    //     {
    //         shallowCopy.add(user);
    //     }
    //     return shallowCopy;
    // }

    public static ArrayList <User> shallowCopy (HashMap <Integer, User> users)
    {
        ArrayList <User> shallowCopy = new ArrayList<>();
        for (User user : users.values())
        {
            shallowCopy.add(user);
        }
        return shallowCopy;
    }

    public static HashMap <Integer, User> shallowCopyHashMap (HashMap <Integer, User> users)
    {
        HashMap <Integer, User> shallowCopy = new HashMap<>();
        for (User user : users.values())
        {
            shallowCopy.put(user.userID, user);
        }
        return shallowCopy;
    }

    // public static ArrayList <User> deepCopy (ArrayList <User> users) throws CloneNotSupportedException
    // {
    //     ArrayList <User> deepCopy = new ArrayList<>();
    //     for (User user : users)
    //     {
    //         deepCopy.add(user.clone());
    //     }
    //     return deepCopy;
    // }

    public static ArrayList <User> deepCopy (HashMap <Integer, User> users) throws CloneNotSupportedException
    {
        ArrayList <User> deepCopy = new ArrayList<>();
        for (User user : users.values())
        {
            deepCopy.add(user.clone());
        }
        return deepCopy;
    }

    public static HashMap <Integer, User> deepCopyHashMap (HashMap <Integer, User> users) throws CloneNotSupportedException
    {
        HashMap <Integer, User> deepCopy = new HashMap<>();
        for (User user : users.values())
        {
            deepCopy.put(user.userID, user.clone());
        }
        return deepCopy;
    }

    public ArrayList <InsurancePolicy> deepCopyPolicies () throws CloneNotSupportedException
    {
        return InsurancePolicy.deepCopy(policies);
    }

    public HashMap <Integer, InsurancePolicy> deepCopyPoliciesHashMap () throws CloneNotSupportedException
    {
        return InsurancePolicy.deepCopyHashMap(policies);
    }

    public ArrayList <InsurancePolicy> shallowCopyPolicies ()
    {
        return InsurancePolicy.shallowCopy(policies);
    }

    public HashMap <Integer, InsurancePolicy> shallowCopyPoliciesHashMap ()
    {
        return InsurancePolicy.shallowCopyHashMap(policies);
    }

    @Override 
    public int compareTo (User other)
    {
        return address.compareTo(other.address);
    }

    public int compareTo1 (User other)
    {
        double total = InsurancePolicy.calcTotalPayments(policies, 100);
        double otherTotal = InsurancePolicy.calcTotalPayments(other.policies, 100);
        return Double.compare(total, otherTotal);
    }

    public ArrayList <InsurancePolicy> sortPoliciesByDate ()
    {
        // ArrayList <InsurancePolicy> sorted = InsurancePolicy.shallowCopy(policies);
        // Collections.sort(sorted);
        // return sorted;
        ArrayList <InsurancePolicy> sorted = InsurancePolicy.shallowCopy(policies);
        Collections.sort(sorted);
        return sorted;
    }

    // lab 5
    public HashMap <String, Integer> getTotalCountPerCarModel ()
    {
        HashMap <String, Integer> totalCount = new HashMap<>();
        for (InsurancePolicy policy : policies.values())
        {
            String model = policy.getCar().getModel();
            if (totalCount.containsKey(model))
            {
                totalCount.put(model, totalCount.get(model) + 1);
            }
            else
            {
                totalCount.put(model, 1);
            }
        }
        return totalCount;
    }

    public HashMap <String, Double> getTotalPremiumPerCarModel ()
    {
        HashMap <String, Double> totalPremiums = new HashMap<>();
        for (InsurancePolicy policy : policies.values())
        {
            String model = policy.getCar().getModel();
            double premium = policy.calcPayment(100);
            if (totalPremiums.containsKey(model))
            {
                totalPremiums.put(model, totalPremiums.get(model) + premium);
            }
            else
            {
                totalPremiums.put(model, premium);
            }
        }
        return totalPremiums;
    }

    public void report ()
    {
        HashMap <String, Integer> counts = getTotalCountPerCarModel();
        HashMap <String, Double> totals = getTotalPremiumPerCarModel();
        
        System.out.printf("%-30s %-25s %-25s%n","Car Model", "Total Premium Payment", "Average Premium Payment");

        for (String model : totals.keySet())
        {
            double total = totals.get(model);
            int count = counts.get(model);
            double average = total / count;

            System.out.printf("%-30s $%-24.2f $%-24.2f%n", model, total, average);
        }

    }
}