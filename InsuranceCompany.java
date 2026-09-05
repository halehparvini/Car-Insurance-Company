package WEEK1;

import java.util.ArrayList;

public class InsuranceCompany
{
    private String name;
    private ArrayList <User> users;
    private String adminUsername; 
    private String adminPassword; 
    private int flatRate;
    private ArrayList<Car> updatedCars = new ArrayList<>();

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
            result += user.toString() + "\n";
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
            return user.createComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level);
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

    public boolean carPriceRise(int userID, double risePercent)
    {
        User user = findUser(userID);
        if (user != null)
        {
            for (InsurancePolicy policy : user.policies)
            {
                Car car = policy.car;
                if (!updatedCars.contains(car))
                {
                    car.priceRise(risePercent);
                    updatedCars.add(car);
                }
            }
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

    public ArrayList <String> populateDistinctCityNames ()
    {
        ArrayList <String> cities = new ArrayList<>();
        for (User user : users)
        {
            boolean found = false;
            for (String city : cities)
            {
                if (user.getAddress().getCity().equals(city))
                {
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                cities.add(user.getAddress().getCity());
            }
        }
        return cities;
    }

    public double getTotalPaymentForCity (String city)
    {
        double totalPayment = 0;
        for (User user : users)
        {
            if (user.getAddress().getCity().equals(city))
            {
                totalPayment += user.calcTotalPremiums(flatRate);

            }
        }
        return totalPayment;
    }

    public ArrayList <Double> getTotalPaymentPerCity (ArrayList <String> cities)
    {
        ArrayList <Double> payments = new ArrayList<>();
        for (String city : cities)
        {
            payments.add(getTotalPaymentForCity(city));
        }
        return payments;
    }

    public void reportPaymentPerCity (ArrayList <String> cities, ArrayList <Double> payments)
    {
        System.out.println("City Name \t \t Total Premium Payment");
        for (int i = 0; i < cities.size(); i++)
        {
            System.out.println(cities.get(i) + "\t\t" + payments.get(i) + "$");
        }
    }

    public ArrayList <String> populateDistinctCarModels ()
    {
        ArrayList <String> carModels = new ArrayList<>();
        for (User user : users)
        {
            for (String model : user.populateDistinctCarModels())
            {
                if (!carModels.contains(model)){
                    carModels.add(model);
                }
            }
        }
        return carModels;
    }

    public ArrayList <Integer> getTotalCountPerCarModel (ArrayList <String> carModels)
    {
        ArrayList <Integer> totalCounts = new ArrayList<>();
        for (String model : carModels)
        {
            int count = 0;
            for (User user : users)
            {
                count += user.getTotalCountForCarModel(model);
            }
            totalCounts.add(count);
        }
        return totalCounts;
    }

    public ArrayList <Double> getTotalPaymentPerCarModel (ArrayList <String> carModels)
    {
        ArrayList <Double> totalPayments = new ArrayList<>();
        for (String model : carModels)
        {
            double payment = 0;
            for (User user : users)
            {
                payment += user.getTotalPaymentForCarModel(model);
            }
            totalPayments.add(payment);
        }
        return totalPayments;
    }

    public void reportPaymentsPerCarModel (ArrayList <String> carModels ,ArrayList <Integer> counts, ArrayList <Double> premiumPayments)
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

    public void changeAdminPassword (String newPass)
    {
        adminPassword = newPass;
    }
}