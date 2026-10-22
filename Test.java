package WEEK1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Test
{
    Car car1 = new Car("Honda CR-V", CarType.SUV, 2026, 30000);
    Car car2 = new Car("Honda Civic Hatchback", CarType.HATCH, 2024, 24000);
    Car car3 = new Car("Mercedes-Benz S-Class", CarType.LUX, 2021, 10980);
    Car car4 = new Car("E-Class", CarType.SED, 2026, 19000);
    Car car5 = new Car("A3 Sportback", CarType.HATCH, 2022,42000);
    Car car6 = new Car("E-Class", CarType.SED, 2023, 58000);
    Car car7 = new Car("A3 Sportback", CarType.HATCH, 2015, 11000);
    
    MyDate date1 = new MyDate(2020, 5, 28);
    MyDate date2 = new MyDate(2027, 2, 4);
    MyDate date3 = new MyDate(2026, 11, 25);
    MyDate date4 = new MyDate(2036, 12, 1);
    MyDate date5 = new MyDate(2013, 1, 14);
    MyDate date6 = new MyDate(2020, 5, 29);

    ThirdPartyPolicy tpp1;
    ComprehensivePolicy cp1;
    ThirdPartyPolicy tpp2;
    ComprehensivePolicy cp2;
    ThirdPartyPolicy tpp3;
    ComprehensivePolicy cp3;
    ThirdPartyPolicy tpp4;
    
    Address address1 = new Address(42, "Willow Street", "Greenfield", "Melbourne");
    Address address2 = new Address(9, "Willow Way", "Fairview", "Miami");
    Address address3 = new Address(31, "Aspen Circle", "Orchard Hills", "Dallas");
    Address address4 = new Address(789, "Oak Lane", "Pinecrest", "Miami");
    Address address5 = new Address(14, "Water Fall", "Greenfield", "Melbourne");
    Address address6 = new Address(1, "Heaven", "Greenfield", "Melbourne");

    User user1 = new User("Haleh", address1, "halehPassword");
    User user2 = new User("Parisa",address2, "parisaPassword");
    User user3 = new User("Taraneh",address3, "taranehPassword");
    User user4 = new User("Payam", address4, "payamPassword");
    User user5 = new User("Sarina", address5, "sarinaPassword");
    User user6 = new User("Behzad", address6, "behzadPassword");

    InsuranceCompany insuranceCompany = new InsuranceCompany("TrustInsure", "admin_ti", "admin@1234", 100);

    public InsuranceCompany getInsuranceCompany ()
    {
        return insuranceCompany;
    }
    
    public Test()
    {
        User.resetCount();
        tpp1 = makeThirdPartyPolicy("James Smith", 01, car1, 2, date1, "2 previous claims!");
        cp1 = makeComprehensivePolicy("Robert Johnson", 02, car2, 0, date2,  24, 3);
        tpp2 = makeThirdPartyPolicy("Sara Williams", 03, car3, 0, date3, "No previous claims!");
        cp2 = makeComprehensivePolicy("Sue Brown", 04, car4, 5, date4, 56, 3);
        tpp3 = makeThirdPartyPolicy("Taylor Jones", 05, car5, 2, date5, "2 previous claims!");
        cp3 = makeComprehensivePolicy("Matthew Miller", 06, car6, 1, date6, 60, 3);
        tpp4 = makeThirdPartyPolicy("Jack Davis", 7, car7, 0, date1, "No previous claims!");
    }

    public ThirdPartyPolicy makeThirdPartyPolicy (String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments)
    {
        try
        {
            return new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
        }
        catch (PolicyException e)
        {
            System.out.println(e);
            try
            {
                return new ThirdPartyPolicy(policyHolderName, e.getID(), car, numberOfClaims, expiryDate, comments);
            }
            catch (PolicyException e2)
            {
                System.out.println(e2);
                return null;
            }
            catch (PolicyHolderNameException e3)
            {
                System.out.println("Invalid policy holder name!");
                return null;
            }
        }
        catch (PolicyHolderNameException e)
        {
            System.out.println("Invalid policy holder name!");
            return null;
        }
    }

    public ComprehensivePolicy makeComprehensivePolicy (String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level)
    {
        try
        {
            return new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level);
        }
        catch (PolicyException e)
        {
            System.out.println(e);
            try
            {
                return new ComprehensivePolicy(policyHolderName, e.getID(), car, numberOfClaims, expiryDate, driverAge, level);
            }
            catch (PolicyException e2)
            {
                System.out.println(e2);
                return null;
            }
            catch (PolicyHolderNameException e3)
            {
                System.out.println("Invalid policy holder name");
                return null;
            }
        }
        catch (PolicyHolderNameException e)
        {
            System.out.println("Invalid policy holder name!");
            return null;
        }
    }

    public void testCore ()
    {
        System.out.println("Welcome to Test Core");
        // System.out.println("\u001B[33mExpected result: Successful login!\u001B[0m");
        // testValidateAdmin("admin_ti", "admin@1234");
        // System.out.println("\u001B[33mExpected rsult: Invalid username or password!\u001B[0m");
        // testValidateAdmin("wrong username", "admin@1234");
        // System.out.println("\u001B[33mExpected result: 6 users have been added successfully!\u001B[0m");
        // testAddUser(user1);
        // testAddUser(user2);
        // testAddUser(user3);
        // testAddUser(user4);
        // testAddUser(user5);
        // testAddUser(user6);
        // System.out.println("\u001B[33mExpected result: User cannot be added as the userID is invalid or duplicate!\u001B[0m");
        // testAddUser(user1); // duplicate userID
        // System.out.println("\u001B[33mExpected result: User has been added successfully!\u001B[0m");
        // testAddUser("Alhan", "alhanPassword", address1); // create user7
        // System.out.println("\u001B[33mExpected rsult: User has been found successfully!\u001B[0m");
        // testFindUser(1);
        // System.out.println("\u001B[33mExpected result: User cannot be found as the userID is invalid!\u001B[0m");
        // testFindUser(8489); // invalid userID
        // System.out.println("\u001B[33mExpected result: Policies have been added successfuly!\u001B[0m");
        // testAddPolicy(1, tpp1);
        // testAddPolicy(1, cp1);
        // testAddPolicy(2, tpp1);
        // testAddPolicy(2, tpp2);
        // testAddPolicy(2, tpp3);
        // testAddPolicy(2, tpp4);
        // testAddPolicy(3, tpp2);
        // testAddPolicy(3, cp1);
        // testAddPolicy(3, cp2);
        // testAddPolicy(5, cp2);
        // testAddPolicy(6, cp1);
        // testAddPolicy(6, cp3);
        // testAddPolicy(6, tpp4);
        // System.out.println("\u001B[33mExpected result: Policies cannot be added as the userID is invalid or policyID is duplicate!\u001B[0m");
        // testAddPolicy(1838, cp2); // invalid userID
        // testAddPolicy(1, cp1); // duplicate policy
        // System.out.println("\u001B[33mExpected result: Policy has been found successfully!\u001B[0m");
        // testFindPolicy(1, tpp1.getPolicyID());
        // System.out.println("\u001B[33mExpected result: Policy cannot be found as the userID or policyID is invalid!\u001B[0m");
        // testFindPolicy(37637, 1); //invalid userID
        // testFindPolicy(1, 49787); //invalid policyID
        // testFindPolicy(1, 4); // user dose not have a policy with this ID
        // System.out.println("\u001B[33mExpected result: User 1 information and policies will be printed\u001B[0m");
        // insuranceCompany.printPolicies("admin_ti", "admin@1234", 1);
        // System.out.println("\u001B[33mExpected result: As userID is not valid nothing will be printed\u001B[0m");
        // insuranceCompany.printPolicies("admin_ti", "admin@1234", 783683); // invalid userID
        // System.out.println("\u001B[33mExpected result: All the users with their policies will be printed\u001B[0m");
        // insuranceCompany.print("admin_ti", "admin@1234");
        // System.out.println("\u001B[33mExpected result: Third Party Policy had been created successfully!\u001B[0m");
        // testCreateThirdPartyPolicy(4, "Jack", 8, car7, 0, date4, "No previous claims!");
        // user4.printPolicies(100);
        // System.out.println("\u001B[33mExpected result: Third Party Policy cannot be created as the userID is invalid or policyID is duplicate!\u001B[0m");
        // testCreateThirdPartyPolicy(63626, "Ell", 9, car1, 1, date1, "One previous claim!"); // invalid userID
        // testCreateThirdPartyPolicy(2, "Lee", tpp3.getPolicyID(), car1, 0, date1, "No previous claims!"); //duplicate policyID for user2
        // System.out.println("\u001B[33mExpected result: Comprehensive Policy has been created successfully!\u001B[0m");
        // testCreateComprehensivePolicy(4, "Lee", 9, car4, 0, date6, 29, 2);
        // user4.printPolicies(100);
        // System.out.println("\u001B[33mExpected result: Comprehensive Policy cannot be creates as the userID is invalid or policyID is duplicate.\u001B[0m");
        // testCreateComprehensivePolicy(37982, "Lee", 10, car1, 0, date1, 55, 1);
        // testCreateComprehensivePolicy(5, "Sue",cp2.getPolicyID(), car1, 0, date1, 39, 2);
        // System.out.println("\u001B[33mExpected result: Total payment for each user will be pinted \u001B[0m");
        // System.out.println("\u001B[33mExpected total payment for user 1: 1680.0\u001B[0m");
        // System.out.println("\u001B[32mPASSED\u001B[0m");
        // double totalPaymentUser1 = insuranceCompany.calcTotalPayments("admin_ti", "admin@1234", 1);
        // System.out.println("Total Payment for user 1: " + totalPaymentUser1);
        // System.out.println("\u001B[33mExpected total payment for user 2: 2139.8\u001B[0m");
        // System.out.println("\u001B[32mPASSED\u001B[0m");
        // double totalPaymentUser2 = insuranceCompany.calcTotalPayments("admin_ti", "admin@1234", 2);
        // System.out.println("Total Payment for user 2: " + totalPaymentUser2);
        // System.out.println("\u001B[33mExpected total payment for user 3: 2569.8\u001B[0m");
        // System.out.println("\u001B[32mPASSED\u001B[0m");
        // double totalPaymentUser3 = insuranceCompany.calcTotalPayments("admin_ti", "admin@1234",3);
        // System.out.println("Total Payment for user 3: " + totalPaymentUser3);
        // System.out.println("\u001B[33mExpected total payment for user 4: 740.0\u001B[0m");
        // System.out.println("\u001B[32mPASSED\u001B[0m");
        // double totalPaymentUser4 = insuranceCompany.calcTotalPayments("admin_ti", "admin@1234",4);
        // System.out.println("Total Payment for user 4: " + totalPaymentUser4);
        // System.out.println("\u001B[33mExpected total payment for user 5: 1480.0\u001B[0m");
        // System.out.println("\u001B[32mPASSED\u001B[0m");
        // double totalPaymentUser5 = insuranceCompany.calcTotalPayments("admin_ti", "admin@1234",5);
        // System.out.println("Total Payment for user 5: " + totalPaymentUser5);
        // System.out.println("\u001B[33mExpected total payment for user 6: 2550.0\u001B[0m");
        // System.out.println("\u001B[32mPASSED\u001B[0m");
        // double totalPaymentUser6 = insuranceCompany.calcTotalPayments("admin_ti", "admin@1234",6);
        // System.out.println("Total Payment for user 6: " + totalPaymentUser6);
        // System.out.println("\u001B[33mExpected total payment for user 7: 0.0\u001B[0m");
        // System.out.println("\u001B[32mPASSED\u001B[0m");
        // double totalPaymentUser7 = insuranceCompany.calcTotalPayments("admin_ti", "admin@1234",7);
        // System.out.println("Total Payment for user 7: " + totalPaymentUser7);
        // System.out.println("\u001B[33mExpected result: The sum of all total payments will be printed \u001B[0m");
        // System.out.println("\u001B[33mExpected sum of total payment for all users: 11159.6\u001B[0m");
        // System.out.println("\u001B[32mPASSED\u001B[0m");
        // double sumOfTotalPayments = insuranceCompany.calcTotalPayments("admin_ti", "admin@1234");
        // System.out.println("Sum of total payments: " + sumOfTotalPayments);
        // System.out.println("\u001B[33mExpected result: The price of the cars included in the insurance policies that User 1 has must have increased. (rise percent: 10%) \u001B[0m");
        // insuranceCompany.carPriceRise("admin_ti", "admin@1234",1, 0.1);
        // user1.printPolicies(100);
        // System.out.println("\u001B[33mExpected result: The price of all cars included in users' insurance policies must have increased. (rise percent: 10%) \u001B[0m");
        // insuranceCompany.carPriceRise("admin_ti", "admin@1234",0.1);
        // insuranceCompany.print("admin_ti", "admin@1234");
        // System.out.println("\u001B[33mExpected result: 1 car will be found for user 1 \u001B[0m");
        // testFilterByCarModel(1, "Honda CR-V");
        // System.out.println("\u001B[33mExpected result: No car will be found as user 1 dose not have that car model. \u001B[0m");
        // testFilterByCarModel(1, "Peykan");
        // MyDate expiryDate1 = new MyDate(2025, 12, 3);
        // System.out.println("\u001B[33mExpected result: Based on the date " + expiryDate1 + ", 3 insurance policies were found for user 2 that expired before that date. \u001B[0m");
        // testFilterByExpiryDate(2, expiryDate1);
        // System.out.println("\u001B[33mExpected result: All policies with car model Honda CR-V will be printed.\u001B[0m");
        // testFilterByCarModel("Honda CR-V");
        // System.out.println("\u001B[33mExpected result: No policy should be found with this car model.\u001B[0m");
        // testFilterByCarModel("BMW");
        // System.out.println("\u001B[33mExpected result: All policies with the expiry date before " + expiryDate1 + " will be printed.\u001B[0m");
        // testFilterByExpiryDate(expiryDate1);
        // System.out.println("\u001B[33mExpected result: No policy should be found with this expiry date.\u001B[0m");
        // testFilterByExpiryDate(new MyDate(2010, 1, 1));
    }

    public void testValidateAdmin (String adminUsername, String adminPassword)
    {
        if (insuranceCompany.validateAdmin(adminUsername, adminPassword))
        {
            System.out.println("Seccussful login!");
        }
        else
            System.out.println("Invalid username or password!");
    }

    public void testAddUser (String username, String password, User user)
    {
        if (insuranceCompany.addUser(username, password, user))
        {
            System.out.println("User has been added succussfully!");
        }
        else
            System.out.println("User cannot be added as the userID is invalid or duplicate!");
    }

    public void testAddUser (String username, String password, String name, String userPassword, Address address)
    {
        if (insuranceCompany.addUser(username, password, name, userPassword, address))
        {
            System.out.println("User has been added succussfully!");
        }
        else
            System.out.println("User cannot be added as the userID is invalid or duplicate!");
    }

    public void testFindUser (String username, String password, int userID)
    {
        User user = insuranceCompany.findUser(username, password, userID);
        if (user != null)
        {
            System.out.println("User has been found successfully!");
        }
        else
            System.out.println("User connot be found as the userID is invalid");
    }

    public void testAddPolicy (String username, String password, int userID, InsurancePolicy policy)
    {
        if (insuranceCompany.addPolicy(username, password, userID, policy))
        {
            System.out.println("Policy has been added successfully!");
        }
        else
            System.out.println("Policy cannot be added as the userID is invalid or policyID is duplicate!");
    }

    public void testFindPolicy (String username, String password, int userID, int policyID)
    {
        InsurancePolicy policy = insuranceCompany.findPolicy(username, password, userID, policyID);
        if (policy != null)
        {
            System.out.println("Policy has been found succussfully!");
        }
        else
            System.out.println("Policy cannot be found as the userID or policyID is invalid!");
    }

    public void testCreateThirdPartyPolicy (String username, String password, int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments)
    {
        try
        {
            if (insuranceCompany.createThirdPartyPolicy(username, password, userID, policyHolderName, id, car, numberOfClaims, expiryDate, comments))
            {
                System.out.println("Third Party Policy has been created successfully!");
            }
            else
                System.out.println("Third Party Policy cannot be created ad the userID is invalid or policyID is duplicate!");
        }
        catch (PolicyException e)
        {
            System.out.println(e);
        }
        catch (PolicyHolderNameException e)
        {
            System.out.println("Invalid policy holder name!");
        }
    }

    public void testCreateComprehensivePolicy (String username, String password, int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level)
    {
        try
        {
            if (insuranceCompany.createComprehensivePolicy(username, password, userID, policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level))
            {
                System.out.println("Comprehensive Policy has been created successfully!");
            }
            else
                System.out.println("Comprehensive Policy cannot be created as the userID is invalid or policyID is duplicate!");
        }
        catch (PolicyException e)
        {
            System.out.println(e);
        }    
        catch (PolicyHolderNameException e)
        {
            System.out.println("Invalid policy holder name!");
        }
    }

    public void testFilterByCarModel (String userame, String password, int userID, String carModel)
    {
        HashMap <Integer, InsurancePolicy> filteredPoliciesByCarModel = insuranceCompany.filterByCarModel(userame, password, userID, carModel);
        if (!filteredPoliciesByCarModel.isEmpty())
        {
            for (InsurancePolicy policy : filteredPoliciesByCarModel.values())
            {
                System.out.println(policy);
            }
        }
        else
            System.out.println("No policy was found for this user with this car model.");

    }

    public void testFilterByExpiryDate (String username, String password, int userID, MyDate date)
    {
        HashMap <Integer, InsurancePolicy> filteredPoliciesByExpiryDate = insuranceCompany.filterByExpiryDate(username, password, userID, date);
        if (!filteredPoliciesByExpiryDate.isEmpty())
        {
            for (InsurancePolicy policy : filteredPoliciesByExpiryDate.values())
            {
                System.out.println(policy);
            }
        }
        else
            System.out.println("No policy was found for this user with this expiry date.");
    }

    public void testFilterByCarModel (String username, String password, String carModel)
    {
        ArrayList<InsurancePolicy> result = insuranceCompany.filterByCarModel(username, password, carModel);
        if (!result.isEmpty())
        {
            for (InsurancePolicy policy : result)
            {
                System.out.println(policy);
            }
        }
        else
            System.out.println("No policy was found with this car model.");
    
    }

    public void testFilterByExpiryDate  (String username, String password, MyDate date)
    {
        ArrayList<InsurancePolicy> result = insuranceCompany.filterByExpiryDate(username, password, date);
        if (!result.isEmpty())
        {
            for (InsurancePolicy policy : result)
            {
                System.out.println(policy);
            }
        }
        else
            System.out.println("No policy was found with this expiry date.");
    }

    public void testStandardAndAdvanced ()
    {
        // System.out.println("\u001B[33mExpected result: Melbourne, Miami, Dallas \u001B[0m");
        // testPopulateDistinctCityNames();
        // System.out.println("\u001B[33mExpected Value for Melbourne: 6139.6$ \u001B[0m");
        // testGetTotalPaymentForCity("Melbourne");
        // System.out.println("\u001B[33mExpected Value for Miami: 3055.7799999999997$ \u001B[0m");
        // testGetTotalPaymentForCity("Miami");
        // System.out.println("\u001B[33mExpected Value for Dallas: 2719.58$ \u001B[0m");
        // testGetTotalPaymentForCity("Dallas");
        // System.out.println("\u001B[33mExpected Value for Isfahan: 0$ (becuase we don't have this city)\u001B[0m");
        // testGetTotalPaymentForCity("Isfahan");
        // ArrayList <String> cities = insuranceCompany.populateDistinctCityNames("admin_ti", "admin@1234");
        // System.out.println("\u001B[33mExpected result: Total payment for each city will be printed.\u001B[0m");
        // testGetTotalPaymentPerCity(cities);
        // System.out.println("\u001B[33mExpected result: Payment report for each city will be displayed.\u001B[0m");
        // testReportPaymentPerCity();
        // System.out.println("\u001B[33mExpected result: All distinct car models will be displayed. (Honda CR-V, Honda Civic Hatchback, A3 Sportback, A3 Sportback, E-Class)\u001B[0m");
        // testPopulateDistinctCarModels();
        // System.out.println("\u001B[33mExpected result: Total count of each car model will be displayed.\u001B[0m");
        // testGetTotalCountPerCarModel();
        // System.out.println("\u001B[33mExpected result: Total payment for each car model will be displayed.\u001B[0m");
        // testGetTotalPaymentPerCarModels();
        // System.out.println("\u001B[33mExpected result: Payment report for each car model will be displayed.\u001B[0m");
        // testReportPaymentPerCarModel();
        // System.out.println("\u001B[33mExpected result: User with ID 1 will be removed successfully.\u001B[0m");
        // testRemoveUserByAdmin(1);
        // System.out.println("\u001B[33mExpected result: User with ID 100 will not be found.\u001B[0m");
        // testRemoveUserByAdmin(100);
        // System.out.println("\u001B[33mExpected result: Admin password will be changed successfully.\u001B[0m");
        // testChangeAdminPassword("newPassword");
        // System.out.println("\u001B[33mExpected result: Policy will be removed successfully.\u001B[0m");
        // testRemovePolicy(1, 1);
        // System.out.println("\u001B[33mExpected result: Policy cannot be removed because the user ID is invalid.\u001B[0m");
        // testRemovePolicy(999, 1); // invalid userID
    }

    public void testPopulateDistinctCityNames (String username, String password)
    {
        ArrayList <String> cities = insuranceCompany.populateDistinctCityNames(username, password);
        if (!cities.isEmpty())
        {
            for (String city : cities)
            {
                System.out.println(city);
            }
        }
        else
            System.out.println("No cities found!");
    }

    public void testGetTotalPaymentForCity (String username, String password, String city)
    {
        double totalPayment = insuranceCompany.getTotalPaymentForCity(username, password, city);
        System.out.println("Total Payment for City " + city + " " + totalPayment);
    }

    public void testGetTotalPaymentPerCity (String username, String password, ArrayList <String> cities)
    {
        ArrayList <Double> payments = insuranceCompany.getTotalPaymentPerCity(username, password, cities);
        for (int i = 0; i < cities.size(); i++)
        {
            System.out.println(cities.get(i) + ": " + payments.get(i));
        }
    }

    public void testReportPaymentPerCity (String username, String password)
    {
        ArrayList <String> cities = insuranceCompany.populateDistinctCityNames(username, password);
        ArrayList <Double> payments = insuranceCompany.getTotalPaymentPerCity(username, password, cities);
        insuranceCompany.reportPaymentPerCity(username, password, cities, payments);
    }

    public void testPopulateDistinctCarModels (String username, String password)
    {
        ArrayList <String> carModels = insuranceCompany.populateDistinctCarModels(username, password);
        for (String model : carModels)
        {
            System.out.println(model);
        }
    }

    public void testGetTotalCountPerCarModel (String username, String password)
    {
        ArrayList <String> carModels = insuranceCompany.populateDistinctCarModels(username, password);
        ArrayList <Integer> totalCounts = insuranceCompany.getTotalCountPerCarModel(username, password, carModels);
        for  (int i = 0; i < carModels.size(); i++)
        {
            System.out.println(carModels.get(i) + ": " + totalCounts.get(i));
        }
    }

    public void testGetTotalPaymentPerCarModels (String username, String password)
    {
        ArrayList <String> carModels = insuranceCompany.populateDistinctCarModels(username, password);
        ArrayList <Double> totalPayments = insuranceCompany.getTotalPaymentPerCarModel(username, password, carModels);
        for (int i = 0; i < carModels.size(); i++)
        {
            System.out.println(carModels.get(i) + ": " + totalPayments.get(i));
        }
        
    }

    public void testReportPaymentPerCarModel (String username, String password)
    {
        ArrayList <String> carModels = insuranceCompany.populateDistinctCarModels(username, password);
        ArrayList <Integer> counts = insuranceCompany.getTotalCountPerCarModel(username, password, carModels);
        ArrayList <Double> payments = insuranceCompany.getTotalPaymentPerCarModel(username, password, carModels);
        insuranceCompany.reportPaymentsPerCarModel(username, password, carModels, counts, payments);
    }

    public void testRemoveUserByAdmin (String username, String password, int userID)
    {
        boolean result = insuranceCompany.removeUserByAdmin(username, password, userID);
        if (result)
        {
            System.out.println("User with ID " + userID + " was removed successfully!");
        }
        else
            System.out.println("User with ID " + userID + " was not found!");

    }

    public void testChangeAdminPassword (String username, String password, String newPass)
    {
        insuranceCompany.changeAdminPassword(username, password, newPass);
        if (insuranceCompany.validateAdmin("admin_ti", newPass))
        {
            System.out.println("Admin password changed successfully!");
        }
        else
            System.out.println("Admin password was not changed!");
    }

    public void testRemovePolicy (String username, String password, int userID, int policyID)
    {
        boolean result = insuranceCompany.removePolicy(username, password, userID, policyID);
        if (result)
        {
            System.out.println("Policy with ID " + policyID + " was removed successfully from user " + userID);
        }
        else
            System.out.println("Policy with ID " + policyID + " could not be removed from user " + userID);
    }

    // lab 4
    public void copyPolicies () throws CloneNotSupportedException
    {
        User sina = new User("Sina", new Address(19, "King", "downtown", "London"), "sinaPassword" );
        sina.addPolicy(1, "sinaPassword", cp1);
        sina.addPolicy(1, "sinaPassword", tpp3);
        ArrayList <InsurancePolicy> deepCopy = sina.deepCopyPolicies(1, "sinaPassword");
        ArrayList <InsurancePolicy> shallowCopy = sina.shallowCopyPolicies(1, "sinaPassword");

        sina.setCity("New York");

        try
        {
        ThirdPartyPolicy tpp = new ThirdPartyPolicy("Justin", 10, new Car("Mazda 3", CarType.HATCH, 2020, 35000), 0, new MyDate(2027, 1, 1), "No previous claims!");
        sina.addPolicy(1, "sinaPassword", tpp);
        }
        catch (PolicyException e)
        {
            System.out.println(e);
        }
        catch (PolicyHolderNameException e)
        {
            System.out.println("Invalid policy holder name!");
        }

        ArrayList <InsurancePolicy> sortedPolicies = sina.sortPoliciesByDate(1, "sinaPassword");

        System.out.println("\nShallow Copy");
        for (InsurancePolicy policy : shallowCopy)
        {
            System.out.println(policy);
        }
        System.out.println("\nDeep Copy");
        for (InsurancePolicy policy : deepCopy)
        {
            System.out.println(policy);
        }
        System.out.println("\nUser's Policies (sorted by date and contains one new policy)");
        for (InsurancePolicy policy : sortedPolicies)
        {
            System.out.println(policy);
        }
    }

    public void copyUsers (String username, String password) throws CloneNotSupportedException
    {
        InsuranceCompany company = new InsuranceCompany("TestCompany", "admin", "1234",100);
        company.addUser(username, password, user1);
        company.addUser(username, password, user2);
        company.addUser(username, password, user3);
        company.addUser(username, password, user4);
        company.addUser(username, password, user5);
        company.addUser(username, password, user6);
        ArrayList <User> deepCopyUsers = company.deepCopyUsers(username, password);
        ArrayList <User> shallowCopyUsers = company.shallowCopyUsers(username, password);

        company.addUser(username, password, "Hailey", "hailyPassword", new Address(12, "Garden st", "Downtown", "Boston"));

        ArrayList <User> sortedUsers = company.sortUsers(username, password); // sorted by city

        System.out.println("\nBefore changing the city of user with ID 1");
        System.out.println("\nShallow Copy");
        for (User user : shallowCopyUsers)
        {
            System.out.println(user);
        }
        System.out.println("\nDeep Copy");
        for (User user : deepCopyUsers)
        {
            System.out.println(user);
        }
        System.out.println("\nCompany's Users (sorted by city and contains one new user.)");
        for (User user : sortedUsers)
        {
            System.out.println(user);
        }

        System.out.println("\nAfter changing the city of user with ID 1");
        user1.setCity("New York");
        System.out.println("\nShallow Copy");
        for (User user : shallowCopyUsers)
        {
            System.out.println(user);
        }
        System.out.println("\nDeep Copy");
        for (User user : deepCopyUsers)
        {
            System.out.println(user);
        }
        System.out.println("\nCompany's Users (sorted by city and contains one new user.)");
        for (User user : sortedUsers)
        {
            System.out.println(user);
        }
    }

    public void cloneInsuranceCompany (String username, String password) throws CloneNotSupportedException
    {
        InsuranceCompany insuranceCompany = new InsuranceCompany("clone copmany", "clone", "1234", 100);
        
        insuranceCompany.addUser(username, password, user1);
        insuranceCompany.addUser(username, password, user2);
        insuranceCompany.addUser(username, password, user3);
        insuranceCompany.addUser(username, password, user4);
        insuranceCompany.addUser(username, password, user5);
        insuranceCompany.addUser(username, password, user6);

        user1.addPolicy(user1.getUserID(), user1.getUserPassword(), tpp1);
        user2.addPolicy(user2.getUserID(), user2.getUserPassword(), cp1);
        user3.addPolicy(user3.getUserID(), user3.getUserPassword(), tpp2);
        user4.addPolicy(user4.getUserID(), user4.getUserPassword(), cp2);
        user5.addPolicy(user5.getUserID(), user5.getUserPassword(), tpp3);
        user6.addPolicy(user6.getUserID(), user6.getUserPassword(), cp3);

        InsuranceCompany clone = insuranceCompany.clone();

        System.out.println("\nOrginal Insurance Company");
        System.out.println(insuranceCompany);
        System.out.println("Clone Insurance Company");
        System.out.println(clone);

        insuranceCompany.addPolicy(username, password, 1, cp1);
        insuranceCompany.addUser(username, password, new User("Diba", new Address(23, "Queen st", "GreenHouse", "London"), "dibaPassword"));
        insuranceCompany.carPriceRise(username, password, 0.1);
        try
        {
        insuranceCompany.createComprehensivePolicy(username, password, 6, "Adele", 9, car1, 0, date1, 60, 3);
        }
        catch (PolicyException e)
        {
            System.out.println(e);
        }
        catch (PolicyHolderNameException e)
        {
            System.out.println("Invalid policy holder name!");
        }
        ArrayList <User> sortedUser = insuranceCompany.sortUsers(username, password);
        System.out.println("\nOrginal Insurance Company");
        for (User user : sortedUser)
        {
            System.out.println("\n" + user);
        }
    
        System.out.println("\nClone Insurance Company");
        System.out.println(clone);
    }

    // test data aggregation lab 5
    public void testGetTotalCountPerCarModelUser (User user)
    {
        HashMap <String, Integer> totalCounts = user.getTotalCountPerCarModel(user.getUserID(), user.getUserPassword());

        for (String model : totalCounts.keySet())
        {
            System.out.println(model + ": " + totalCounts.get(model));
        }
    }

    public void testGetTotalPremiumPerCarModelUser (User user)
    {
        HashMap <String, Double> totalPremiums = user.getTotalPremiumPerCarModel(user.getUserID(), user.getUserPassword());

        for (String model : totalPremiums.keySet())
        {
            System.out.println(model + ": " + totalPremiums.get(model));
        }
    }

    public void testReportUser (User user)
    {
        user.report(user.getUserID(), user.getUserPassword());
    }

    public void testGetTotalPremiumPerCityCompany (String username, String password)
    {
        HashMap <String, Double> totalPremiums = insuranceCompany.getTotalPremiumPerCity(username, password);

        for (String city : totalPremiums.keySet())
        {
            System.out.println(city + ": " + totalPremiums.get(city));
        }
    }

    public void testGetTotalCountPerCarModelCompany (String username, String password)
    {
        HashMap <String, Integer> totalCounts = insuranceCompany.getTotalCountPerCarModel(username, password);

        for (String model : totalCounts.keySet())
        {
            System.out.println(model + ": " + totalCounts.get(model));
        }
    }

    public void testGetTotalPremiumPerCarModelCompany (String username, String password)
    {
        HashMap <String, Double> totalPremiums = insuranceCompany.getTotalPremiumPerCarModel(username, password);

        for (String model : totalPremiums.keySet())
        {
            System.out.println(model + ": " + totalPremiums.get(model));
        }
    }

    public void testReportComapny (String username, String password)
    {
        insuranceCompany.report(username, password);
    }

    public void testReportAcrossAllUsersCompany (String username, String password)
    {
        insuranceCompany.reportAcrossAllUsers(username, password);
    }

    public void setUpAggregation(String username, String password)
    {
        insuranceCompany.addUser(username, password, user1);
        insuranceCompany.addUser(username, password, user2);
        insuranceCompany.addUser(username, password, user3);
        insuranceCompany.addUser(username, password, user4);
        insuranceCompany.addUser(username, password, user5);
        insuranceCompany.addUser(username, password, user6);

        user1.addPolicy(user1.getUserID(), user1.getUserPassword(), tpp1); //Honda CR-V
        user1.addPolicy(user1.getUserID(), user1.getUserPassword(), cp2); //E-Class
        user1.addPolicy(user1.getUserID(), user1.getUserPassword(), cp3); //E-Class
        // Expected: 
        // Honda CR-V: 1
        // E-Class: 2
        // Honda CR-V (tpp1) = 30000/100 + 2*200 + 100 =800
        // cp2 = 19000/50 + 5*200 + 100 = 1480
        // cp3 = 58000/50 + 1*200 + 100 = 1460
        // E-Class (cp2 + cp3) = 2940 

        user2.addPolicy(user2.getUserID(), user2.getUserPassword(), tpp2);
        user2.addPolicy(user2.getUserID(), user2.getUserPassword(), cp2);

        user3.addPolicy(user3.getUserID(), user3.getUserPassword(), tpp3);

        user4.addPolicy(user3.getUserID(), user3.getUserPassword(), cp3);
    }

    public void testAggregation (String username, String password)
    {
        setUpAggregation(username, password);
        System.out.println();
        System.out.println("USER AGGREGATION");
        testGetTotalCountPerCarModelUser(user1);
        System.out.println();
        testGetTotalPremiumPerCarModelUser(user1);
        System.out.println();
        testReportUser(user1);
        System.out.println();
        System.out.println("COMPANY AGGREGATION");
        testGetTotalPremiumPerCityCompany(username, password);
        System.out.println();
        testGetTotalCountPerCarModelCompany(username, password);
        System.out.println();
        testGetTotalPremiumPerCarModelCompany(username, password);
        System.out.println();
        testReportComapny(username, password);
        System.out.println();
        testReportAcrossAllUsersCompany(username, password);
        System.out.println();
    }

    // lab 6

    // public void testSaveAndLoad (String username, String password) throws PolicyException
    // {
    //     HashMap <Integer, InsurancePolicy> policies = new HashMap<>();
    //     policies.put(1, tpp1);
    //     policies.put(2, cp1);
    //     policies.put(3, tpp2);
    //     policies.put(4, cp2);
    //     policies.put(5, tpp3);
    //     policies.put(6, cp3);
    //     policies.put(7, tpp4);

    //     InsurancePolicy.save(policies, "policies.ser");
    //     policies.clear();
    //     policies = InsurancePolicy.load("policies.ser");

    //     if (policies != null)
    //     {
    //         InsurancePolicy.printPolicies(policies);
    //         policies.put(1, tpp1);
    //         policies.put(2, cp1);
    //         policies.put(3, tpp2);
    //         policies.put(4, cp2);
    //         policies.put(5, tpp3);
    //         policies.put(6, cp3);
    //         policies.put(7, tpp4);
    //         policies.clear();
    //         policies = InsurancePolicy.load("policies.ser");
    //         if (policies != null)
    //         {
    //             InsurancePolicy.printPolicies(policies);
    //         }
    //     }

    //     HashMap <Integer, User> users = new HashMap<>();
    //     User user = new User("Jack", address1, "jackPassword");
    //     user.addPolicy(username, password, cp1);
    //     users.put(user.getUserID(), user);
    //     User.save(users, "users.ser");
    //     users.clear();
    //     users = User.load("users.ser");
    //     if (users != null)
    //     {
    //         User.printUsers(username, password, users);
    //     }

    //     InsuranceCompany insuranceCompany1 = new InsuranceCompany();
    //     insuranceCompany1.addUser(,username, password, "Mahan", "mahanPassword", address2);
    //     insuranceCompany1.save(username, password, "company.ser");
    //     insuranceCompany1.clearUsers(username, password);
    //     InsuranceCompany insuranceCompany2 = new InsuranceCompany();
    //     insuranceCompany2.load(username, password, "company.ser");
    //     System.out.println(insuranceCompany2);

    //     HashMap <Integer, InsurancePolicy> policies2 = new HashMap<>();
    //     policies2.put(1, tpp1);
    //     policies2.put(2, cp1);
    //     policies2.put(3, tpp2);
    //     policies2.put(4, cp2);
    //     policies2.put(5, tpp3);
    //     policies2.put(6, cp3);
    //     policies2.put(7, tpp4);;
    //     InsurancePolicy.saveTextFile(policies2, "policies.txt");
    //     policies2.clear();
    //     policies2 = InsurancePolicy.loadTextFile("policies.txt");
    //     if (policies2 != null)
    //     {
    //         InsurancePolicy.printPolicies(policies2);
    //     }

    //     HashMap <Integer, User> users2 = new HashMap<>();
    //     User user2 = new User("Ladan", address3, "ladanPassword");
    //     user2.addPolicy(username, password, tpp4);
    //     user2.addPolicy(username, password, cp1);
    //     users2.put(user2.getUserID(), user2);
    //     User.saveTextFile(users2, "users.txt");
    //     if (users2 != null)
    //     {
    //         User.printUsers(username, password, users2);
    //     }

    //     InsuranceCompany insuranceCompany3 = new InsuranceCompany();
    //     insuranceCompany3.addUser(username, password, "Haleh", "halehPassword", address1);
    //     insuranceCompany3.addUser(username, password, "Parisa", "parisaPassword", address2);
    //     insuranceCompany3.addUser(username, password, "Taraneh","taranehPassword", address3);
    //     insuranceCompany3.addUser(username, password, "Payam", "payamPassword", address4);
    //     insuranceCompany3.addUser(username, password, "Sarina", "sarinaPassword", address5);
    //     insuranceCompany3.addUser(username, password, "Behzad", "behzadPassword", address6);


    //     insuranceCompany3.saveTextFile(username, password, "company.txt");
    //     insuranceCompany3.clearUsers(username, password);
    //     InsuranceCompany insuranceCompany4 = new InsuranceCompany();
    //     insuranceCompany4.loadTextFile(username, password, "company.txt");
    //     System.out.println(insuranceCompany4);

    // }

    public void filingTest ()
    {
        InsuranceCompany insuranceCompany1 = new InsuranceCompany("TrustInsure", "admin_ti", "admin@1234",100);

        insuranceCompany1.addUser("admin_ti", "admin@1234", user1);
        insuranceCompany1.addUser("admin_ti", "admin@1234", user2);
        insuranceCompany1.addUser("admin_ti", "admin@1234", user3);
        insuranceCompany1.addUser("admin_ti", "admin@1234", user4);
        insuranceCompany1.addUser("admin_ti", "admin@1234", user5);
        insuranceCompany1.addUser("admin_ti", "admin@1234", user6);

        user1.addPolicy(1, "halehPassword", tpp1);
        user1.addPolicy(1, "halehPassword", cp1);
        user2.addPolicy(2, "parisaPassword", tpp1);
        user2.addPolicy(2, "parisaPassword", tpp2);
        user3.addPolicy(3, "taranehPassword", cp1);
        user3.addPolicy(3, "taranehPassword", cp2);
        user3.addPolicy(3, "taranehPassword", cp3);
        user4.addPolicy(4, "payamPassword", tpp3);
        user4.addPolicy(4, "payamPassword", tpp4);
        user4.addPolicy(4, "payamPassword", cp1);



        // testing the save and load policies in/from BINARY FILE
        try
        {
            InsurancePolicy.save(insuranceCompany1.allPoliciesHashMap("admin_ti", "admin@1234"), "policies.ser");
            HashMap <Integer, InsurancePolicy> policies = InsurancePolicy.load("policies.ser");
            System.out.println("Printing a list of policies loaded from binary file");
            InsurancePolicy.printPolicies(policies);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        // testing the save and load policies in/from TEXT FILE
        try
        {
            InsurancePolicy.saveTextFile(insuranceCompany1.allPoliciesHashMap("admin_ti", "admin@1234"), "policies.txt");
            HashMap <Integer, InsurancePolicy> policies = InsurancePolicy.loadTextFile("policies.txt");
            System.out.println("Printing a list of policies loaded from text file");
            InsurancePolicy.printPolicies(policies);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        // testing the save and load users in/from BINARY FILE
        try
        {
            User.save(insuranceCompany1.getUsers(), "users.ser");
            HashMap <Integer, User> users = User.load("users.ser");
            System.out.println("Printing a list of users loaded from binary file");
            System.out.println(users.values());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        // testing the save and load users in/from TEXT FILE
        try
        {
            User.saveTextFile(insuranceCompany1.getUsers(), "users.txt");
            HashMap <Integer, User> users = User.loadTextFile("users.txt");
            System.out.println("Printing a list of users loaded from text file");
            System.out.println(users.values());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        // testing the save and load InsuranceCompany in/from BINARY FILE
        try
        {
            insuranceCompany1.save("admin_ti", "admin@1234", "company.ser");
            InsuranceCompany insuranceCompany2 = new InsuranceCompany();
            insuranceCompany2.load("admin_ti", "admin@1234", "company.ser");
            System.out.println("Printing the insurance conpany loaded from bunart file");
            System.out.println(insuranceCompany2);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        // testing the save and load InsuranceCompany in/from TEXT FILE
        try
        {
            insuranceCompany1.saveTextFile("admin_ti", "admin@1234", "company.txt");
            InsuranceCompany insuranceCompany2 = new InsuranceCompany();
            insuranceCompany2.loadTextFile("admin_ti", "admin@1234", "company.txt");
            System.out.println("Printing the insurance company loaded from text file");
            System.out.println(insuranceCompany2);          
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    // Core Test
    public void testCoreAssignment2 () throws CloneNotSupportedException
    {
        copyPolicies();
        copyUsers("admin", "1234");
        cloneInsuranceCompany("clone", "1234");
        testAggregation(insuranceCompany.getAdminUsername(), insuranceCompany.getAdminPassword());
        filingTest();
    }

    // Advanced Test
    public void testCaseAdvanced2 () throws PolicyException, PolicyHolderNameException
    {
        Car civic = new Car("Honda Civic", CarType.HATCH, 2022, 20000);
        Car carmy = new Car("Toyota Carmy", CarType.SED, 2021, 30000);
        Car mazda = new Car("Mazda 3", CarType.HATCH, 2023, 25000);
        Car focus = new Car("Ford Focus", CarType.HATCH, 2020, 15000);
        Car cerato = new Car("Kia Cerato", CarType.SED, 2022, 22000);

        User user1 = new User("Sara", new Address(12, "xxx", "xxx", "Wollongong"), "saraPassword");
        User user2 = new User("John", new Address(12, "xxx", "xxx", "Wollongong"), "johnPassword");
        User user3 = new User("Robert", new Address(12, "xxx", "xxx", "Sydney"), "robertPassword");
        User user4 = new User("Alex", new Address(12, "xxx", "xxx", "Melbourne"), "alexPassword");
        
        // user1
        addPolicy(user1, new TestPolicy(20, 3000001, civic));
        addPolicy(user1, new TestPolicy(50, 3000002, civic));
        addPolicy(user1, new TestPolicy(30, 3000003, civic));
        addPolicy(user1, new TestPolicy(100, 3000004, carmy));

        // user2
        addPolicy(user2, new TestPolicy(50, 3000005, carmy));
        addPolicy(user2, new TestPolicy(10, 3000006, civic));
        addPolicy(user2, new TestPolicy(100, 3000007, carmy));
        addPolicy(user2, new TestPolicy(200, 3000008, mazda));

        // user3
        addPolicy(user3, new TestPolicy(15, 3000009, mazda));
        addPolicy(user3, new TestPolicy(100, 3000010, carmy));
        addPolicy(user3, new TestPolicy(500, 3000011, civic));
        addPolicy(user3, new TestPolicy(40, 3000012, focus));

        // user4
        addPolicy(user4, new TestPolicy(50, 3000013, cerato));
        addPolicy(user4, new TestPolicy(100, 3000014, carmy));

        InsuranceCompany company = new InsuranceCompany("trustInsure", "admin_ti", "admin@1234", 100);
        
        String adminUsername = company.getAdminUsername();
        String adminPssword = company.getAdminPassword();

        company.addUser(adminUsername, adminPssword, user1);
        company.addUser(adminUsername, adminPssword, user2);
        company.addUser(adminUsername, adminPssword, user3);
        company.addUser(adminUsername, adminPssword, user4);

        int [] ranges = {20, 50, 100, 1000};

        System.out.println("Testing policy count per user: ");

        for (User user : company.getUsers().values())
        {
            int [] policyCount = user.policyCount(user.getUserID(), user.getUserPassword(), ranges);

            System.out.println(user.getName() + "       " + Arrays.toString(policyCount));
        }

        // Expected: 
        // Sara [0, 2, 1, 1, 0]
        // John [1, 0, 1, 2, 0]
        // Robert [1, 1, 0, 2, 0]
        // Alex [0, 0, 1, 1, 0]

        System.out.println("Testing policy Count in insurance company: ");
        int [] policyCount = company.policyCount(adminUsername, adminPssword, ranges);
        System.out.println(Arrays.toString(policyCount));
        // Expected: [2, 3, 3, 6, 0]

        System.out.println("Testing policy city count: ");
        HashMap <String, Integer []> policyCityCount = company.policyCityCount(adminUsername, adminPssword, ranges);
        for (String city : policyCityCount.keySet())
        {
            System.out.println(city + "     " + Arrays.toString(policyCityCount.get(city)));
        }
        // Expected: 
        // Wollongong [1, 2, 2, 3, 0]
        // Sydney [1, 1, 0, 2, 0]
        // Melbourn [0, 0, 1, 1, 0]

        System.out.println("Testing user count (total premium per user): ");
        int [] userCount = company.userCount(adminUsername, adminPssword, ranges);
        System.out.println(Arrays.toString(userCount));
        // Totals: Sara 200; John 360; Robert 655; Alex 150
        // Expected: [0, 0, 0, 4, 0]

        System.out.println("Tesing user count with different ranges: ");
        int [] ranges2 = {0, 200, 400, 1000};
        System.out.println(Arrays.toString(company.userCount(adminUsername, adminPssword, ranges2)));
        // Expected: [0, 1, 2, 1, 0]

        System.out.println("Testing user car model count: ");
        HashMap <String, Integer []> userCarModelCount = company.userCarModelCount(adminUsername, adminPssword, ranges);
        for (String model : userCarModelCount.keySet())
        {
            System.out.println(model + "        " + Arrays.toString(userCarModelCount.get(model)));
        }
        // Expected: 
        // Honda Civic  [1, 0, 0, 2, 0]
        // Toyota Carmy [0, 0, 0, 4, 0]
        // Mazda 3      [1, 0, 0, 1, 0]
        // Ford Focus   [0, 1, 0, 0, 0]
        // Kia Cerato   [0, 0, 1, 0, 0]

        System.out.println("Testing policy car model count per user: ");
        for (User user : company.getUsers().values())
        {
            HashMap <String, Integer []> policyCarModelCount = user.policyCarModelCount(user.getUserID(), user.getUserPassword(), ranges);
            System.out.println(user.getName() + ":");
            for (String model : policyCarModelCount.keySet())
            {
                System.out.print("  " + model + "       " + Arrays.toString(policyCarModelCount.get(model)) + "\n");
            }
        }
        // Expected: 
        // Sara: Honda Civic [0, 2, 1, 0, 0] Toyota Carmy [0, 0, 0, 1, 0]
        // John: Toyota Carmy [0, 0, 1, 1, 0] Honda Civic [1, 0, 0, 0, 0] Mazda 3 [0, 0, 0, 1, 0]
        // Robert: Mazda 3 [1, 0, 0, 0, 0] Toyota Carmy [0, 0, 0, 1, 0] Honda Civic [0, 0, 0, 1, 0] Ford Focus [0, 1, 0, 0, 0]
        // Alex: Kia Cerato [0, 0, 1, 0, 0] Toyota Carmy [0, 0, 0, 1, 0]

        System.out.println("Testing policy Car model count in insurance company: ");
        HashMap <String, Integer []> companyCarModelCount = company.policyCarModelCount(adminUsername, adminPssword, ranges);
        for (String model : companyCarModelCount.keySet())
        {
            System.out.println(model + "        " + Arrays.toString(companyCarModelCount.get(model)));
        }
        // Expected: 
        // Honda Civic    [1, 2, 1, 1, 0]
        // Toyota Carmy   [0, 0, 1, 4, 0]
        // Mazda 3        [1, 0, 0, 1, 0]
        // For Focus      [0, 1, 0, 0, 0]
        // Kia Cerato     [0, 0, 1, 0, 0]

        System.out.println("Testing wrong credential: ");
        try
        {
            company.policyCount("Wrong", "Wrong", ranges);
            System.out.println("Failed: no exception fo wrong admin credentials");
        }
        catch (SecurityException e)
        {
            System.out.println("Wrong username or password!");
        }
        try
        {
            user1.policyCount(user1.getUserID(), "worng", ranges);
            System.out.println("Failed: no exception for wrong user credetials");
        }
        catch (SecurityException e)
        {
            System.out.println("Wrong username or password!");
        }
    }

    private static void addPolicy (User user, InsurancePolicy policy)
    {
        user.addPolicy(user.getUserID(), user.getUserPassword(), policy);
    }
}

    class TestPolicy extends InsurancePolicy
    {
        private double premium;  
    

        public TestPolicy (double premium, int id, Car car) throws PolicyException, PolicyHolderNameException
        {
            super("James Smith", id, car, 0, new MyDate(2030, 1, 1));
            this.premium = premium;
        }

        @Override 
        public double calcPayment (double flatRate)
        {
            return premium;
        }
    }