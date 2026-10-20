package WEEK1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class InsuranceCompany implements Cloneable, Serializable
{
    private String name;
    //private ArrayList <User> users;
    private HashMap <Integer, User> users;
    private String adminUsername; 
    private String adminPassword; 
    private int flatRate;
    private ArrayList<Car> updatedCars = new ArrayList<>();

    public InsuranceCompany (String name, String adminUsername, String adminPassword, int flatRate)
    {
        this.name = name;
        users = new HashMap<>();
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate; 
    }

    public String getName ()
    {
        return name;
    }

    // public ArrayList <User> getUsers ()
    // {
    //     return users;
    // }

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

    // public boolean addUser (User user)
    // {
        // if (user != null && findUser(user.getUserID()) == null)
        // {
        //     users.add(user);
        //     return true;
        // }
        // else
        //     return false;
    // }

    public Boolean addUser (String username, String password, String name, String userPassword, Address address)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        User user = new User(name, address, userPassword);
        return addUser(username, password, user);
    }

    // public User findUser (int userID)
    // {
        // for (User user : users)
        // {
        //     if (user.getUserID() == userID)
        //         return user;
        // }
        // return null;
    // }

    public boolean addPolicy (String username, String password, int userID, InsurancePolicy policy)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        User user = findUserInternal(userID);
        if (user == null)
        {
            return false;
        }
        if (findPolicy(username, password, userID, policy.getPolicyID()) != null)
        {
            return false;
        }

        return user.addPolicy(user.getName(), user.getUserPassword(), policy);
    }

    public void validateAdminAccess (String username, String password)
    {
        if (!validateAdmin(username, password))
        {
            throw new SecurityException("Invalid username or password!");
        }
    }

    public InsurancePolicy findPolicy (String username, String password, int userID, int policyID)
    {
        validateAdminAccess(username, password);
        User user = findUserInternal(userID);
        if (user != null)
        {
            return user.findPolicyInternal(policyID);
        }
        return null;
    }

    public void printPolicies (String username, String password, int userID)
    {
        validateAdminAccess(username, password);
        User user = findUserInternal(userID);
        if (user != null)
        {
            user.printUserDetailsInternal();
            user.printPoliciesInternal(flatRate);
        }
        else
        {
            System.out.println("User not found!");
        }
    }

    // public void print ()
    // {
        // for (User user : users)
        // {
        //     user.print();
        //     user.printPolicies(flatRate);
        // }
    // }

    // public String toString ()
    // {
        // String result = "";
        // for (User user : users)
        // {
        //     result += user.toString() + "\n";
        // }
        // return result;
    // }

    public boolean createThirdPartyPolicy (String username, String password, int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) throws PolicyException, PolicyHolderNameException
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        User user = findUserInternal(userID);
        if (user != null)
        {
            return user.createThirdPartyPolicy(user.getName(), user.getUserPassword(), policyHolderName, id, car, numberOfClaims, expiryDate, comments);
        }
        return false;
    }

    public boolean createComprehensivePolicy (String username, String password, int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) throws PolicyException, PolicyHolderNameException
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        User user = findUserInternal(userID);
        if (user != null)
        {
            return user.createComprehensivePolicy(user.getName(), user.getUserPassword(), policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level);
        }
        return false; 
    }

    public double calcTotalPayments (String username, String password, int userID)
    {
        validateAdminAccess(username, password);
        User user = findUserInternal(userID);
        if (user != null)
        {
            return user.calcTotalPremiumsInternal(flatRate);
        }
        return 0;
    }

    // public double calcTotalPayments ()
    // {
        // double totalPayment = 0;
        // for (User user : users)
        // {
        //     totalPayment += user.calcTotalPremiums(flatRate);
        // }
        // return totalPayment;
    // }

    // public boolean carPriceRise (int userID, double risePercent)
    // {
        // User user = findUser(userID);
        // if (user != null)
        // {
        //     for (InsurancePolicy policy : user.policies)
        //     {
        //         Car car = policy.car;
        //         if (!updatedCars.contains(car))
        //         {
        //             car.priceRise(risePercent);
        //             updatedCars.add(car);
        //         }
        //     }
        //     return true;
        // }
        // return false;
    // }

    // public void carPriceRise (double risePercent)
    // {
        // ArrayList<Car> cars = new ArrayList<Car>();
        // for (User user : users)
        // {
        //     for (InsurancePolicy policy : user.getPolicies())
        //     {
        //         Car car = policy.getCar();
        //         if (!cars.contains(car))
        //         {
        //             car.priceRise(risePercent);
        //             cars.add(car);
        //         }
        //     }
        // }
    // }

    // public ArrayList <InsurancePolicy> allPolicies ()
    // {
        // ArrayList <InsurancePolicy> policies = new ArrayList<>();
        // for (User user : users)
        // {
        //     for (InsurancePolicy policy : user.policies)
        //     {
        //         policies.add(policy);
        //     }
        // }
        // return policies;
    // 

    // public ArrayList <InsurancePolicy> filterByCarModel (int userID, String carModel)
    // {
    //     User user = findUser(userID);
    //     if (user != null)
    //     {
    //         return user.filterByCarModel(carModel);
    //     }
    //     return new ArrayList<InsurancePolicy>();
    // }

    // public ArrayList <InsurancePolicy> filterByExpiryDate (int userID, MyDate date)
    // {
    //     User user = findUser(userID);
    //     if (user != null)
    //     {
    //         return user.filterByExpiryDate(date);
    //     }
    //     return new ArrayList<InsurancePolicy>();
    // }
    
    // public ArrayList <InsurancePolicy> filterByCarModel (String carModel)
    // {
        // ArrayList <InsurancePolicy> result = new ArrayList<>();
        // for (User user : users)
        // {
        //     ArrayList <InsurancePolicy> filteredPolicies = user.filterByCarModel(carModel);
        //     for (InsurancePolicy policy : filteredPolicies)
        //     {
        //         result.add(policy);
        //     }
        // }
        // return result;
    // }

    // public ArrayList <InsurancePolicy> filterByExpiryDate (MyDate date)
    // {
        // ArrayList <InsurancePolicy> result = new ArrayList<>();
        // for (User user : users)
        // {
        //     ArrayList <InsurancePolicy> filteredPolicies = user.filterByExpiryDate(date);
        //     for (InsurancePolicy policy : filteredPolicies)
        //     {
        //         result.add(policy);
        //     }
        // }
        // return result;
    // }

    // public ArrayList <String> populateDistinctCityNames ()
    // {
        // ArrayList <String> cities = new ArrayList<>();
        // for (User user : users)
        // {
        //     boolean found = false;
        //     for (String city : cities)
        //     {
        //         if (user.getAddress().getCity().equals(city))
        //         {
        //             found = true;
        //             break;
        //         }
        //     }
        //     if (!found)
        //     {
        //         cities.add(user.getAddress().getCity());
        //     }
        // }
        // return cities;
    // }

    // public double getTotalPaymentForCity (String city)
    // {
        // double totalPayment = 0;
        // for (User user : users)
        // {
        //     if (user.getAddress().getCity().equals(city))
        //     {
        //         totalPayment += user.calcTotalPremiums(flatRate);

        //     }
        // }
        // return totalPayment;
    // }

    public ArrayList <Double> getTotalPaymentPerCity (String username, String password, ArrayList <String> cities)
    {
        validateAdminAccess(username, password);
        ArrayList <Double> payments = new ArrayList<>();
        if (cities == null)
        {
            throw new IllegalArgumentException("Cities cannot be null!");
        }
        for (String city : cities)
        {
            payments.add(getTotalPaymentForCity(username, password, city));
        }
        return payments;
    }

    public void reportPaymentPerCity (String username, String password, ArrayList <String> cities, ArrayList <Double> payments)
    {
        validateAdminAccess(username, password);
        if (cities == null || payments == null)
        {
            throw new IllegalArgumentException("Cities and payments cannot be null!");
        }
        if (cities.size() != payments.size())
        {
            throw new IllegalArgumentException("Cities and payments must have the same size!");
        }
        System.out.printf("%-20s %s%n", "City Name", "Total Premium Payment");
        for (int i = 0; i < cities.size(); i++)
        {
            System.out.printf("%-20s %.2f$%n", cities.get(i), payments.get(i));
        }
    }

    // public ArrayList <String> populateDistinctCarModels ()
    // {
        // ArrayList <String> carModels = new ArrayList<>();
        // for (User user : users)
        // {
        //     for (String model : user.populateDistinctCarModels())
        //     {
        //         if (!carModels.contains(model)){
        //             carModels.add(model);
        //         }
        //     }
        // }
        // return carModels;
    // }

    // public ArrayList <Integer> getTotalCountPerCarModel (ArrayList <String> carModels)
    // {
        // ArrayList <Integer> totalCounts = new ArrayList<>();
        // for (String model : carModels)
        // {
        //     int count = 0;
        //     for (User user : users)
        //     {
        //         count += user.getTotalCountForCarModel(model);
        //     }
        //     totalCounts.add(count);
        // }
        // return totalCounts;
    // }

    // public ArrayList <Double> getTotalPaymentPerCarModel (ArrayList <String> carModels)
    // {
        // ArrayList <Double> totalPayments = new ArrayList<>();
        // for (String model : carModels)
        // {
        //     double payment = 0;
        //     for (User user : users)
        //     {
        //         payment += user.getTotalPaymentForCarModel(model, flatRate);
        //     }
        //     totalPayments.add(payment);
        // }
        // return totalPayments;
    // }

    public void reportPaymentsPerCarModel (String username, String password, ArrayList <String> carModels ,ArrayList <Integer> counts, ArrayList <Double> premiumPayments)
    {
        validateAdminAccess(username, password);
        if (carModels == null || counts == null || premiumPayments == null)
        {
            throw new IllegalArgumentException("Input lists cannot be null!");
        }
        if (carModels.size() != counts.size() || carModels.size() != premiumPayments.size())
        {
            throw new IllegalArgumentException("All lists must have the same size!");
        }
        System.out.printf("%-25s %-25s %-25s%n","Car Model", "Total Premium Payment", "Average Premium Payment");
        for (int i = 0; i < carModels.size(); i++)
        {
            String model = carModels.get(i);
            int count = counts.get(i);
            double payment = premiumPayments.get(i);
            double average = (count > 0) ? payment / count : 0.0;
            System.out.printf("%-25s $%-25.2f $%-25.2f%n", model, payment, average);
        }
    }

    public boolean removeUserByAdmin (String username, String password, int userID)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        User user = findUserInternal(userID);
        if (user != null)
        {
            users.remove(userID);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean changeAdminPassword (String username, String password, String newPass)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        if (newPass == null || newPass.trim().isEmpty())
        {
            return false;
        }
        adminPassword = newPass.trim();
        return true;
    }

    public boolean removePolicy (String username, String password, int userID, int policyID)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        User user = findUserInternal(userID);
        if (user != null)
        {
            return user.removePolicyInternal(policyID);
        }
        return false;
    }

    // lab 4
    // public InsuranceCompany (InsuranceCompany insuranceCompany)
    // {
        // name = insuranceCompany.name;
        // users = new ArrayList<>();
        // for (User user : insuranceCompany.users)
        // {
        //     users.add(new User(user));
        // }
        // adminUsername = insuranceCompany.adminUsername;
        // adminPassword = insuranceCompany.adminPassword;
        // flatRate = insuranceCompany.flatRate;
        // updatedCars = new ArrayList<>();
        // for (Car car : insuranceCompany.updatedCars)
        // {
        //     updatedCars.add(new Car(car));
        // }
    // }

    // public InsuranceCompany clone () throws CloneNotSupportedException
    // {
        // InsuranceCompany insuranceCompany = (InsuranceCompany)super.clone();
        // insuranceCompany.users = new ArrayList<>();
        // for (User user : users)
        // {
        //     insuranceCompany.users.add(user.clone());
        // }
        // insuranceCompany.updatedCars = new ArrayList<>();
        // for (Car car : updatedCars)
        // {
        //     insuranceCompany.updatedCars.add(car.clone());
        // }
        // return insuranceCompany;
    // }

    public ArrayList <User> deepCopyUsers (String username, String password) throws CloneNotSupportedException
    {
        validateAdminAccess(username, password);
        return User.deepCopy(users);
    }

    public ArrayList <User> shallowCopyUsers (String username, String password)
    {
        validateAdminAccess(username, password);
        return User.shallowCopy(users);
    }

    // public ArrayList <User> sortUsers ()
    // {
        // ArrayList <User> sorted = User.shallowCopy(users);
        // Collections.sort(sorted);
        // return sorted;
    // }

    // lab 5
    public boolean addUser (String username, String password, User user)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        if (user != null && findUserInternal(user.getUserID()) == null)
        {
            users.put(user.getUserID(), user);
            return true;
        }
        else
            return false;
    }

    public User findUser (String username, String password, int userID)
    {
        validateAdminAccess(username, password);
        return users.get(userID);
    }

    public HashMap <Integer, User> getUsers ()
    {
        return users;
    }

    public void print (String username, String password)
    {
        validateAdminAccess(username, password);
        for (User user : users.values())
        {
            user.printUserDetailsInternal();
            user.printPoliciesInternal(flatRate);
        }
    }

    public String toString ()
    {
        String result = "";
        for (User user : users.values())
        {
            result += user.toString() + "\n";
        }
        return result;
    }

    public double calcTotalPayments (String username, String password)
    {
        validateAdminAccess(username, password);
        double totalPayment = 0;
        for (User user : users.values())
        {
            totalPayment += user.calcTotalPremiumsInternal(flatRate);
        }
        return totalPayment;
    }

    public boolean carPriceRise (String username, String password, int userID, double risePercent)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        User user = findUserInternal(userID);
        if (user != null)
        {
            for (InsurancePolicy policy : user.policies.values())
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

    User findUserInternal (int userID)
    {
        return users.get(userID);
    }

    public void carPriceRise (String username, String password, double risePercent)
    {
        validateAdminAccess(username, password);
        ArrayList<Car> cars = new ArrayList<Car>();
        for (User user : users.values())
        {
            for (InsurancePolicy policy : user.getPolicies().values())
            {
                Car car = policy.getCar();
                if (!cars.contains(car))
                {
                    car.priceRise(risePercent);
                    cars.add(car);
                }
            }
        }
    }

    public ArrayList <InsurancePolicy> allPolicies (String username, String password)
    {
        validateAdminAccess(username, password);
        ArrayList <InsurancePolicy> policies = new ArrayList<>();
        for (User user : users.values())
        {
            for (InsurancePolicy policy : user.getPolicies().values())
            {
                policies.add(policy);
            }
        }
        return policies;
    }

    public HashMap <Integer, InsurancePolicy> allPoliciesHashMap (String username, String password)
    {
        validateAdminAccess(username, password);
        HashMap <Integer, InsurancePolicy> policies = new HashMap<>();
        for (User user : users.values())
        {
            for (InsurancePolicy policy : user.getPolicies().values())
            {
                policies.put(policy.getPolicyID(), policy);
            }
        }
        return policies;
    }

    public HashMap <Integer, InsurancePolicy> filterByCarModel (String username, String password, int userID, String carModel)
    {
        validateAdminAccess(username, password);
        User user = findUserInternal(userID);
        if (user != null)
        {
            return user.filterByCarModelInternal(carModel);
        }
        return new HashMap<>();
    }

    public HashMap <Integer, InsurancePolicy> filterByExpiryDate (String username, String password, int userID, MyDate date)
    {
        validateAdminAccess(username, password);
        User user = findUserInternal(userID);
        if (user != null)
        {
            return user.filterByExpiryDateInternal(date);
        }
        return new HashMap<>();
    }

    public ArrayList <InsurancePolicy> filterByCarModel (String username, String password, String carModel)
    {
        validateAdminAccess(username, password);
        ArrayList <InsurancePolicy> result = new ArrayList<>();
        for (User user : users.values())
        {
            if (user == null)
            {
                continue;
            }
            HashMap <Integer, InsurancePolicy> filteredPolicies = user.filterByCarModelInternal(carModel);
            for (InsurancePolicy policy : filteredPolicies.values())
            {
                result.add(policy);
            }
        }
        return result;
    }

    public ArrayList <InsurancePolicy> filterByExpiryDate (String username, String password, MyDate date)
    {
        validateAdminAccess(username, password);
        ArrayList <InsurancePolicy> result = new ArrayList<>();
        for (User user : users.values())
        {
            if (user == null)
            {
                continue;
            }
            HashMap <Integer, InsurancePolicy> filteredPolicies = user.filterByExpiryDateInternal(date);
            for (InsurancePolicy policy : filteredPolicies.values())
            {
                result.add(policy);
            }
        }
        return result;
    }

    public ArrayList <String> populateDistinctCityNames (String username, String password)
    {
        validateAdminAccess(username, password);
        ArrayList <String> cities = new ArrayList<>();
        for (User user : users.values())
        {
            if (user == null || user.getAddress() == null || user.getCity() == null)
            {
                continue;
            }
            boolean found = false;
            for (String city : cities)
            {
                if (user.getCity().equals(city))
                {
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                cities.add(user.getCity());
            }
        }
        return cities;
    }

    public double getTotalPaymentForCity (String username, String password, String city)
    {
        validateAdminAccess(username, password);
        double totalPayment = 0;
        for (User user : users.values())
        {
            if (user != null && user.getAddress() != null && user.getCity() != null && user.getCity().equals(city))
            {
                totalPayment += user.calcTotalPremiumsInternal(flatRate);

            }
        }
        return totalPayment;
    }

    public ArrayList <String> populateDistinctCarModels (String username, String password)
    {
        validateAdminAccess(username, password);
        ArrayList <String> carModels = new ArrayList<>();
        for (User user : users.values())
        {
            if (user == null)
            {
                continue;
            }
            for (String model : user.populateDistinctCarModelsInternal())
            {
                if (!carModels.contains(model)){
                    carModels.add(model);
                }
            }
        }
        return carModels;
    }

    public ArrayList <Integer> getTotalCountPerCarModel (String username, String password, ArrayList <String> carModels)
    {
        validateAdminAccess(username, password);
        ArrayList <Integer> totalCounts = new ArrayList<>();
        for (String model : carModels)
        {
            int count = 0;
            for (User user : users.values())
            {
                count += user.getTotalCountForCarModelInternal(model);
            }
            totalCounts.add(count);
        }
        return totalCounts;
    }

    public ArrayList <Double> getTotalPaymentPerCarModel (String username, String password, ArrayList <String> carModels)
    {
        validateAdminAccess(username, password);
        ArrayList <Double> totalPayments = new ArrayList<>();
        for (String model : carModels)
        {
            double payment = 0;
            for (User user : users.values())
            {
                payment += user.getTotalPaymentForCarModelInternal(model, flatRate);
            }
            totalPayments.add(payment);
        }
        return totalPayments;
    }

    public InsuranceCompany (InsuranceCompany insuranceCompany)
    {
        if (insuranceCompany == null)
        {
            throw new IllegalArgumentException("InsuranceCompany cannot be null!");
        }
        name = insuranceCompany.name;
        users = new HashMap<>();
        for (User user : insuranceCompany.users.values())
        {
            if (user != null)
            {
                users.put(user.getUserID(), new User(user));
            }
        }
        adminUsername = insuranceCompany.adminUsername;
        adminPassword = insuranceCompany.adminPassword;
        flatRate = insuranceCompany.flatRate;
        updatedCars = new ArrayList<>();
        for (Car car : insuranceCompany.updatedCars)
        {
            updatedCars.add(new Car(car));
        }
    }

    public InsuranceCompany clone () throws CloneNotSupportedException
    {
        InsuranceCompany insuranceCompany = (InsuranceCompany)super.clone();
        insuranceCompany.users = new HashMap<>();
        for (User user : users.values())
        {
            if (user != null)
            {
                insuranceCompany.users.put(user.getUserID(), user.clone());
            }
        }
        insuranceCompany.updatedCars = new ArrayList<>();
        for (Car car : updatedCars)
        {
            if (car != null)
            {
                insuranceCompany.updatedCars.add(car.clone());
            }
        }
        return insuranceCompany;
    }

    public HashMap <Integer, User> deepCopyUsersHashMap (String username, String password) throws CloneNotSupportedException
    {
        validateAdminAccess(username, password);
        return User.deepCopyHashMap(users);
    }

    public HashMap <Integer, User> shallowCopyUsersHashMap (String username, String password)
    {
        validateAdminAccess(username, password);
        return User.shallowCopyHashMap(users);
    }

    public ArrayList <User> sortUsers (String username, String password)
    {
        validateAdminAccess(username, password);
        ArrayList <User> sorted = User.shallowCopy(users);
        Collections.sort(sorted);
        return sorted;
    }

    public HashMap <String, Double> getTotalPremiumPerCity (String username, String password)
    {
        validateAdminAccess(username, password);
        HashMap <String, Double> totalPremiums = new HashMap<>();
        for (User user : users.values())
        {
            if (user == null || user.getAddress() == null || user.getCity() == null)
            {
                continue;
            }
            String city = user.getCity();
            double premium = user.calcTotalPremiumsInternal(flatRate);
            if (totalPremiums.containsKey(city))
            {
                totalPremiums.put(city, totalPremiums.get(city) + premium);
            }
            else
            {
                totalPremiums.put(city, premium);
            }
        } 
        return totalPremiums;
    }

    public HashMap <String, Integer> getTotalCountPerCarModel (String username, String password)
    {
        validateAdminAccess(username, password);
        HashMap <String, Integer> totalCounts = new HashMap<>();
        for (User user : users.values())
        {
            if (user == null)
            {
                continue;
            }
            HashMap <String, Integer> userCounts = user.getTotalCountPerCarModelInternal();
            if (userCounts == null)
            {
                continue;
            }
            for (String model : userCounts.keySet())
            {
                int count = userCounts.get(model);
                if (totalCounts.containsKey(model))
                {
                    totalCounts.put(model, totalCounts.get(model) + count);
                }
                else
                {
                    totalCounts.put(model, count);
                }
            }
        }
        return totalCounts;
    }

    public HashMap <String, Double> getTotalPremiumPerCarModel (String username, String password)
    {
        validateAdminAccess(username, password);
        HashMap <String, Double> totalPremiums = new HashMap<>();
        
        for (User user : users.values())
        {
            HashMap <String, Double> userPremiums = user.getTotalPremiumPerCarModelInternal();
            for (String model : userPremiums.keySet())
            {
                double premium = userPremiums.get(model);
                
                if (totalPremiums.containsKey(model))
                {
                    totalPremiums.put(model, totalPremiums.get(model) + premium);
                }
                else
                {
                    totalPremiums.put(model, premium);
                }
            }
        }
        return totalPremiums;
    }

    public void report (String username, String password)
    {
        HashMap <String, Double> totalPremiums = getTotalPremiumPerCity(username, password);

        System.out.printf("%-20s %-25s%n","City Name", "Total Premium Payments");
        for (String city : totalPremiums.keySet())
        {
            double total = totalPremiums.get(city);

            System.out.printf("%-20s $%,.2f%n", city, total);
        }
    }

    public void reportAcrossAllUsers (String username, String password)
    {
        HashMap <String, Integer> counts = getTotalCountPerCarModel(username, password);
        HashMap <String, Double> totalPremium = getTotalPremiumPerCarModel(username, password);

        System.out.printf("%-30s %-25s %-25s%n","Car Model", "Total Premium Payment", "Avarage Premium Payment");
            for (String model : totalPremium.keySet())
            {
                double total = totalPremium.get(model);
                int count = counts.get(model);
                double average = (count > 0) ? total / count : 0;

                System.out.printf("%-30s $%-24.2f $%-24.2f%n", model, total, average);
            }
    }

    // lab 6
    public InsuranceCompany ()
    {
        users = new HashMap<>();
        updatedCars = new ArrayList<>();
    }

    public void clearUsers (String username, String password)
    {
        validateAdminAccess(username, password);
        users.clear();
    }

    public Boolean load (String username, String password, String fileName)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        try
        {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
            InsuranceCompany company = (InsuranceCompany) inputStream.readObject();

            this.name = company.name;
            this.users = company.users;
            this.adminUsername = company.adminUsername;
            this.adminPassword = company.adminPassword;
            this.flatRate = company.flatRate;
            this.updatedCars = company.updatedCars;

            inputStream.close();

            return true;
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Error while loading the file.");
            return false;
        }
    }

    public Boolean save (String username, String password, String fileName)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        try
        {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(this);
            outputStream.close();
            return true;
        }
        catch (IOException e)
        {
            System.out.println("Error while saving the file.");
            return false;
        }
    }

    public String toDelimitedString ()
    {
        String result = name + "," + adminUsername + "," + adminPassword + "," + flatRate + "," + users.size();

        for (User user : users.values())
        {
            result += "\n" + user.toDelimitedString();
        }
        return result;
    }

    public Boolean loadTextFile (String username, String password, String fileName)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();
            if (line == null)
            {
                bufferedReader.close();
                return false;
            }
            String [] companyFields = line.split(",");

            this.name = companyFields[0];
            this.adminUsername = companyFields[1];
            this.adminPassword = companyFields[2];
            this.flatRate = Integer.parseInt(companyFields[3]);
            int numberOfUsers = Integer.parseInt(companyFields[4]);
            this.users = new HashMap<>();
            for (int i = 0; i < numberOfUsers; i++)
            {
                line = bufferedReader.readLine();
                String[] field = line.split(",");
                String userName = field[0];
                String userPassword = field[1];
                int userID = Integer.parseInt(field[2]);
                int streetNum = Integer.parseInt(field[3]);
                String street = field[4];
                String suburb = field[5];
                String city = field[6];
                Address address = new Address(streetNum, street, suburb, city);
                User user = new User(userName, address, userPassword);
                user.setUserID(userID);
                int numberOfPolicies = Integer.parseInt(field[7]);
                int index = 8;
                for (int j = 0; j < numberOfPolicies; j++)
                {
                    if (field[index].equals("TPP"))
                    {
                        String policyHolderName = field[index+1];
                        int id = Integer.parseInt(field[index + 2]);
                        String model = field[index+3];
                        CarType type = CarType.valueOf(field[index+4]);
                        int manufacturingYear = Integer.parseInt(field[index+5]);
                        double price = Double.parseDouble(field[index+6]);
                        Car car = new Car(model, type, manufacturingYear, price);
                        int numberOfClaims = Integer.parseInt(field[index+7]);
                        int year = Integer.parseInt(field[index+8]);
                        int month = Integer.parseInt(field[index+9]);
                        int day = Integer.parseInt(field[index+10]);
                        MyDate expiryDate = new MyDate(year, month, day);
                        String comments = field[index+11];
                        ThirdPartyPolicy policy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
                        user.addPolicyForLoading(policy);
                        index += 12;
                    }
                    else if (field[index].equals("CP"))
                    {
                        String policyHolderName = field[index+1];
                        int id = Integer.parseInt(field[index+2]);
                        String model = field[index+3];
                        CarType type = CarType.valueOf(field[index+4]);
                        int manufacturingYear = Integer.parseInt(field[index+5]);
                        double price = Double.parseDouble(field[index+6]);
                        Car car = new Car(model, type, manufacturingYear, price);
                        int numberOfClaims = Integer.parseInt(field[index+7]);
                        int year = Integer.parseInt(field[index+8]);
                        int month = Integer.parseInt(field[index+9]);
                        int day = Integer.parseInt(field[index+10]);
                        MyDate expiryDate = new MyDate(year, month, day);
                        int driverAge = Integer.parseInt(field[index+11]);
                        int level = Integer.parseInt(field[index+12]);
                        ComprehensivePolicy policy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level);
                        user.addPolicyForLoading(policy);
                        index += 13;
                    }
                }
                users.put(userID, user);
            }
            bufferedReader.close();
            return true;
        }
        catch (IOException e)
        {
            System.out.println("Error while reading the text file.");
        }
        catch (PolicyException e)
        {
            System.out.println("Error while creating policy");
        }
        catch (PolicyHolderNameException e)
        {
            System.out.println("Invalid policy holder name!");
        }
        return false;
    }

    public Boolean saveTextFile (String username, String password, String fileName)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        try
        {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.write(toDelimitedString());
            bufferedWriter.close();
            return true;
        }
        catch (IOException e)
        {
            System.out.println("Error while writing to the text file.");
            return false;
        }
    }

    // Asignment 2

    public class UserComparator implements Comparator <User>
    {
        public int compare (User user1, User user2)
        {
            double totalPaymentsUser1 = InsurancePolicy.calcTotalPayments(user1.getPolicies(), flatRate);
            double totalPaymentsUser2 = InsurancePolicy.calcTotalPayments(user2.getPolicies(), flatRate);
            return Double.compare(totalPaymentsUser1, totalPaymentsUser2);
        }
    }

    public ArrayList <User> sortUsersByPremium (String username, String password)
    {
        validateAdminAccess(username, password);
        ArrayList <User> userList = new ArrayList<>(users.values());
        UserComparator comparator = new UserComparator();
        Collections.sort(userList, comparator);
        return userList;
    }

    public HashMap <String, ArrayList<User>> getUsersPerCity (String username, String password)
    {
        validateAdminAccess(username, password);
        HashMap <String, ArrayList <User>> usersPerCity = new HashMap<>();
        for (User user : users.values())
        {
            String cityName = user.getCity();
            if (!usersPerCity.containsKey(cityName))
            {
                usersPerCity.put(cityName, new ArrayList<User>());
            }
            usersPerCity.get(cityName).add(user);
        }
        return usersPerCity;
    }

    public HashMap <String, ArrayList<InsurancePolicy>> filterPoliciesByExpiryDate (String username, String password, MyDate expiryDate)
    {
        validateAdminAccess(username, password);
        HashMap <String, ArrayList<InsurancePolicy>> filteredPoliciesByExpiryDate = new HashMap<>();
        if (expiryDate == null)
            return filteredPoliciesByExpiryDate;
        for (User user : users.values())
        {
            if (user == null)
            {
                continue;
            }
            String userName = user.getName();
            for (InsurancePolicy policy : user.getPolicies().values())
            {
                if (policy != null && policy.getExpiryDate() != null && policy.getExpiryDate().isExpired(expiryDate))
                {
                    if (!filteredPoliciesByExpiryDate.containsKey(userName))
                    {
                        filteredPoliciesByExpiryDate.put(userName, new ArrayList<InsurancePolicy>());
                    }
                    filteredPoliciesByExpiryDate.get(userName).add(policy);
                }
            }
        }
        return filteredPoliciesByExpiryDate;
    }

    // these are for UI
    public ArrayList <InsurancePolicy> deepCopyPolicies (String username, String password) throws CloneNotSupportedException
    {
        validateAdminAccess(username, password);
        ArrayList <InsurancePolicy> copiedPolicies = new ArrayList<>();
        for (User user : users.values())
        {
            copiedPolicies.addAll(user.deepCopyPoliciesInternal());
        }
        return copiedPolicies;
    }

    public ArrayList <InsurancePolicy> shallowCopyPolicies (String username, String password)
    {
        validateAdminAccess(username, password);
        ArrayList <InsurancePolicy> copiedPolicies = new ArrayList<>();
        for (User user : users.values())
        {
            copiedPolicies.addAll(user.shallowCopyPoliciesInternal());
        }
        return copiedPolicies;
    }

    public InsuranceCompany shallowCopy (String username, String password)
    {
        validateAdminAccess(username, password);
        InsuranceCompany company = new InsuranceCompany(name, adminUsername, adminPassword, flatRate);
        company.users = new HashMap<>(users);
        company.updatedCars = new ArrayList<>(updatedCars);
        return company;
    }

    // Proxy Design Pattern
    public boolean isValidCarType (String username, String password, String carType)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        return Car.isValidCarType(carType);
    }

    public boolean isValidDate (String username, String password, int year, int month, int day)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        return MyDate.isValiddate(year, month, day);
    }

    public boolean userExists (String username, String password, int userID)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        return findUserInternal(userID) != null;
    }

    public int createUser (String username, String password, String name, String userPassword,  int streetNumber, String street, String suburb, String city)
    {
        validateAdminAccess(username, password);
        User user = User.createUser(name, userPassword, streetNumber, street, suburb, city);
        if (addUser(username, password, user))
        {
            return user.getUserID();
        }
        return -1;
    }

    public boolean updateUserAddress (String username, String password, int userID, int streetNumber, String street, String suburb, String city)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        User user = findUserInternal(userID);
        if (user == null)
        {
            return false;
        }
        user.setAddress(new Address(streetNumber, street, suburb, city));
        return true;
    }

    public String getUserInfo (String username, String password, int userID)
    {
        validateAdminAccess(username, password);
        User user = findUserInternal(userID);
        if (user == null)
        {
            return "";
        }
        return user.toString();
    }

    public boolean showUserPolicies (String username, String password, int userID)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        if (findUserInternal(userID) == null)
        {
            return false;
        }
        printPolicies(username, password, userID);
        return true;
    }

    public boolean printUserReport (String username, String password, int userID)
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        User user = findUserInternal(userID);
        if (user == null)
        {
            return false;
        }
        user.reportInternal();
        return true;
    }

    public boolean createThirdPartyPolicy (String username, String password, int userID, String policyHolderName, int policyID, String carModel, String carType, int manufacturingYear, double price, int numberOfClaims, int expiryYear, int expiryMonth, int expiryDay, String comments) throws PolicyException, PolicyHolderNameException
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        Car car = new Car(carModel, Car.toCarType(carType), manufacturingYear, price);
        MyDate expiryDate = new MyDate(expiryYear, expiryMonth, expiryDay);
        return createThirdPartyPolicy(username, password, userID, policyHolderName, policyID, car, numberOfClaims, expiryDate, comments);
    }

    public boolean createComprehensivePolicy (String username, String password, int userID, String policyHolderName, int policyID, String carModel, String carType, int manufacturingYear, double price, int numberOfClaims, int expiryYear, int expiryMonth, int expiryDay, int driverAge, int level) throws PolicyException, PolicyHolderNameException
    {
        if (!validateAdmin(username, password))
        {
            return false;
        }
        Car car = new Car(carModel, Car.toCarType(carType), manufacturingYear, price);
        MyDate expiryDate = new MyDate(expiryYear, expiryMonth, expiryDay);
        return createComprehensivePolicy(username, password, userID, policyHolderName, policyID, car, numberOfClaims, expiryDate, driverAge, level);
    }

    public double printPoliciesByCarModel (String username, String password, String carModel)
    {
        validateAdminAccess(username, password);
        ArrayList <InsurancePolicy> filtered = filterByCarModel(username, password, carModel);
        if (filtered.isEmpty())
        {
            return -1;
        }
        InsurancePolicy.printPolicies(filtered);
        return InsurancePolicy.calcTotalPayments(filtered, flatRate);
    }

    public double getTotalPaymentByCarModel (String username, String password, String carModel)
    {
        validateAdminAccess(username, password);
        return InsurancePolicy.calcTotalPayments(filterByCarModel(username, password, carModel), flatRate);
    }

    public int printPoliciesBeforeDate (String username, String password, int userID, int year, int month, int day)
    {
        validateAdminAccess(username, password);
        HashMap <Integer, InsurancePolicy> filtered = filterByExpiryDate(username, password, userID, new MyDate(year, month, day));
        if (!filtered.isEmpty())
        {
            InsurancePolicy.printPolicies(filtered);
        }
        return filtered.size();
    }

    public void printPaymentPerCityReport (String username, String password)
    {
        validateAdminAccess(username, password);
        ArrayList <String> cities = populateDistinctCityNames(username, password);
        ArrayList <Double> payments = getTotalPaymentPerCity(username, password, cities);
        reportPaymentPerCity(username, password, cities, payments);
    }

    public void printPaymentPerCarModelReport (String username, String password)
    {
        validateAdminAccess(username, password);
        ArrayList <String> carModels = populateDistinctCarModels(username, password);
        ArrayList <Integer> counts = getTotalCountPerCarModel(username, password, carModels);
        ArrayList <Double> payments = getTotalPaymentPerCarModel(username, password, carModels);
        reportPaymentsPerCarModel(username, password, carModels, counts, payments);
    }

    public void printDeepCopyPolicies (String username, String password) throws CloneNotSupportedException
    {
        validateAdminAccess(username, password);
        ArrayList <InsurancePolicy> policies = deepCopyPolicies(username, password);
        System.out.println("Deep Copy of Policies: ");
        InsurancePolicy.printPolicies(policies);
    }

    public void printShallowCopyPolicies (String username, String password)
    {
        validateAdminAccess(username, password);
        ArrayList <InsurancePolicy> policies = shallowCopyPolicies(username, password);
        System.out.println("Shallow Copy of policies: ");
        InsurancePolicy.printPolicies(policies);
    }

    public void printDeepCopyUsers (String username, String password) throws CloneNotSupportedException
    {
        validateAdminAccess(username, password);
        ArrayList <User> copiedUsers = deepCopyUsers(username, password);
        System.out.println("Deep Copy of Users: ");
        for (User user : copiedUsers)
        {
            System.out.println(user);
        }
    }

    public void printShallowCopyUsers (String username, String password)
    {
        validateAdminAccess(username, password);
        ArrayList <User> copiedUsers = shallowCopyUsers(username, password);
        System.out.println("Shallow Copy of Users: ");
        for (User user : copiedUsers)
        {
            System.out.println(user);
        }
    }

    public void printDeepCopyCompany (String username, String password) throws CloneNotSupportedException
    {
        validateAdminAccess(username, password);
        InsuranceCompany copiedCompany = clone();
        System.out.println("Deep Copy of Insurance Company: ");
        System.out.println(copiedCompany);
    }

    public void printShallowCopyCompany (String username, String password)
    {
        validateAdminAccess(username, password);
        InsuranceCompany copiedCompany = shallowCopy(username, password);
        System.out.println("Shallow Copy of Insurance Company: ");
        System.out.println(copiedCompany);
    }

    public void printUsersSortedByCity (String username, String password)
    {
        validateAdminAccess(username, password);
        System.out.println("Users Sorted by City");
        for (User user : sortUsers(username, password))
        {
            System.out.println(user);
        }
    }

    public void printUsersSortedByPremium (String username, String password)
    {
        validateAdminAccess(username, password);
        System.out.println("Users Sorted by Premium Payments: ");
        for (User user : sortUsersByPremium(username, password))
        {
            System.out.println(user);
            System.out.printf("Total Premium: $%.2f%n", calcTotalPayments(username, password, user.getUserID()));
        }
    }

    public void printPoliciesSortedByExpiryDate (String username, String password)
    {
        validateAdminAccess(username, password);
        System.out.println("Policies Sorted By Expiry Date: ");
        for (User user : users.values())
        {
            System.out.println("User: " + user.getName());
            ArrayList <InsurancePolicy> sortedPolicies = user.sortPoliciesByDateInternal();
            InsurancePolicy.printPolicies(sortedPolicies);
            System.out.println();
        }
    }

    public void runCoreTests ()
    {
        Test test = new Test();
        test.testCore();
    }

    public void runStandardAndAdvancedTests ()
    {
        Test test = new Test();
        test.testStandardAndAdvanced();
    }
}