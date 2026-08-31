package WEEK1;

import java.util.ArrayList;

public class InsuranceCompany
{
    private String name;
    private ArrayList <User> users;
    private String adminUsername; 
    private String adminPassword; 
    private int flatRate;

    public InsuranceCompany (String name, String adminUsername, String adminPassword, int flatRate)
    {
        this.name = name;
        users = new ArrayList<>();
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate; 
    }

    public String getName ()
    {
        return name;
    }

    public String getAdminUsername ()
    {
        return adminUsername;
    }

    public String getAdminPassword ()
    {
        return adminPassword;
    }

    public int getFlatRate ()
    {
        return flatRate;
    }

    public void setAdminUsername (String adminUsername)
    {
        this.adminUsername = adminUsername;
    }

    public boolean validateAdmin (String username, String password)
    {
        if (adminUsername != null && adminPassword != null && adminUsername.equals(username) && adminPassword.equals(password))
        {
            return true;
        }
        else
            return false;
    }

    public boolean addUser (User user)
    {
        if (user != null && findUser(user.getUserID()) == null)
        {
            users.add(user);
            return true;
        }
        else
            return false;
    }

    public Boolean addUser (String name, int userID, Address address)
    {
        User user = new User(name, userID, address);
        return addUser(user);
    }

    public User findUser (int userID)
    {
        for (User user : users)
        {
            if (user.getUserID() == userID)
                return user;
        }
        return null;
    }

    public boolean addPolicy (int userID, InsurancePolicy policy)
    {
        User user = findUser(userID);
        if (user == null)
        {
            return false;
        }
        if (findPolicy(userID, policy.getPolicyID()) != null)
        {
            return false;
        }

        return user.addPolicy(policy);
    }

    public InsurancePolicy findPolicy (int userID, int policyID)
    {
        User user = findUser(userID);
        if (user != null)
        {
            return user.findPolicy(policyID);
        }
        return null;
    }

    public void printPolicies (int userID)
    {
        User user = findUser(userID);
        if (user != null)
        {
            user.print();
            user.printPolicies(flatRate);
        }
    }

    public void print ()
    {
        for (User user : users)
        {
            user.print();
            user.printPolicies(flatRate);
        }
    }

    public String toString ()
    {
        String result = "";
        for (User user : users)
        {
            result += user.toString();
        }
        return result;
    }

    public boolean createThirdPartyPolicy (int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments)
    {
        User user = findUser(userID);
        if (user != null)
        {
            return user.createThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
        }
        return false;
    }

    public boolean createComprehensivePolicy (int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level)
    {
        User user = findUser(userID);
        if (user != null)
        {
            user.createComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level);
        }
        return false; 
    }

    public double calcTotalPayments (int userID)
    {
        User user = findUser(userID);
        if (user != null)
        {
            return user.calcTotalPremiums(flatRate);
        }
        return 0;
    }

    public double calcTotalPayments ()
    {
        double totalPayment = 0;
        for (User user : users)
        {
            totalPayment += user.calcTotalPremiums(flatRate);
        }
        return totalPayment;
    }

    public boolean carPriceRise (int userID, double risePercent)
    {
        User user = findUser(userID);
        if (user != null)
        {
            user.carPriceRiseAll(risePercent);
            return true;  
        }
        return false;
    }

    public void carPriceRise (double risePercent)
    {
        for (User user : users)
        {
            user.carPriceRiseAll(risePercent);
        }
    }

    public ArrayList <InsurancePolicy> allPolicies ()
    {
        ArrayList <InsurancePolicy> policies = new ArrayList<>();
        for (User user : users)
        {
            for (InsurancePolicy policy : user.policies)
            {
                policies.add(policy);
            }
        }
        return policies;
    }

    public ArrayList <InsurancePolicy> filterByCarModel (int userID, String carModel)
    {
        User user = findUser(userID);
        if (user != null)
        {
            return user.filterByCarModel(carModel);
        }
        return new ArrayList<InsurancePolicy>();
    }

    public ArrayList <InsurancePolicy> filterByExpiryDate (int userID, MyDate date)
    {
        User user = findUser(userID);
        if (user != null)
        {
            return user.filterByExpiryDate(date);
        }
        return new ArrayList<InsurancePolicy>();
    }

    public ArrayList <InsurancePolicy> filterByCarModel (String carModel)
    {
        ArrayList <InsurancePolicy> result = new ArrayList<>();
        for (User user : users)
        {
            ArrayList <InsurancePolicy> filteredPolicies = user.filterByCarModel(carModel);
            for (InsurancePolicy policy : filteredPolicies)
            {
                result.add(policy);
            }
        }
        return result;
    }

    public ArrayList <InsurancePolicy> filterByExpiryDate (MyDate date)
    {
        ArrayList <InsurancePolicy> result = new ArrayList<>();
        for (User user : users)
        {
            ArrayList <InsurancePolicy> filteredPolicies = user.filterByExpiryDate(date);
            for (InsurancePolicy policy : filteredPolicies)
            {
                result.add(policy);
            }
        }
        return result;
    }
}
