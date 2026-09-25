package WEEK1;

import java.util.ArrayList;
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

    User user1 = new User("Haleh", address1);
    User user2 = new User("Parisa",address2);
    User user3 = new User("Taraneh",address3);
    User user4 = new User("Payam", address4);
    User user5 = new User("Sarina", address5);
    User user6 = new User("Behzad", address6);

    InsuranceCompany insuranceCompany = new InsuranceCompany("TrustInsure", "admin_ti", "admin@1234", 100);

    public InsuranceCompany getInsuranceCompany ()
    {
        return insuranceCompany;
    }
    
    public Test()
    {
        User.resetCount();
        tpp1 = makeThirdPartyPolicy("James", 01, car1, 2, date1, "2 previous claims!");
        cp1 = makeComprehensivePolicy("Robert", 02, car2, 0, date2,  24, 3);
        tpp2 = makeThirdPartyPolicy("Sara", 03, car3, 0, date3, "No previous claims!");
        cp2 = makeComprehensivePolicy("Sue", 04, car4, 5, date4, 56, 3);
        tpp3 = makeThirdPartyPolicy("Taylor", 05, car5, 2, date5, "2 previous claims!");
        cp3 = makeComprehensivePolicy("Matthew", 06, car6, 1, date6, 60, 3);
        tpp4 = makeThirdPartyPolicy("Jack", 7, car7, 0, date1, "No previous claims!");
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
        }
    }

    public void testCore ()
    {
        System.out.println("Welcome to Test Core");
        System.out.println("\u001B[33mExpected result: Successful login!\u001B[0m");
        testValidateAdmin("admin_ti", "admin@1234");
        System.out.println("\u001B[33mExpected rsult: Invalid username or password!\u001B[0m");
        testValidateAdmin("wrong username", "admin@1234");
        System.out.println("\u001B[33mExpected result: 6 users have been added successfully!\u001B[0m");
        testAddUser(user1);
        testAddUser(user2);
        testAddUser(user3);
        testAddUser(user4);
        testAddUser(user5);
        testAddUser(user6);
        System.out.println("\u001B[33mExpected result: User cannot be added as the userID is invalid or duplicate!\u001B[0m");
        testAddUser(user1); // duplicate userID
        System.out.println("\u001B[33mExpected result: User has been added successfully!\u001B[0m");
        testAddUser("Alhan", address1); // create user7
        System.out.println("\u001B[33mExpected rsult: User has been found successfully!\u001B[0m");
        testFindUser(1);
        System.out.println("\u001B[33mExpected result: User cannot be found as the userID is invalid!\u001B[0m");
        testFindUser(8489); // invalid userID
        System.out.println("\u001B[33mExpected result: Policies have been added successfuly!\u001B[0m");
        testAddPolicy(1, tpp1);
        testAddPolicy(1, cp1);
        testAddPolicy(2, tpp1);
        testAddPolicy(2, tpp2);
        testAddPolicy(2, tpp3);
        testAddPolicy(2, tpp4);
        testAddPolicy(3, tpp2);
        testAddPolicy(3, cp1);
        testAddPolicy(3, cp2);
        testAddPolicy(5, cp2);
        testAddPolicy(6, cp1);
        testAddPolicy(6, cp3);
        testAddPolicy(6, tpp4);
        System.out.println("\u001B[33mExpected result: Policies cannot be added as the userID is invalid or policyID is duplicate!\u001B[0m");
        testAddPolicy(1838, cp2); // invalid userID
        testAddPolicy(1, cp1); // duplicate policy
        System.out.println("\u001B[33mExpected result: Policy has been found successfully!\u001B[0m");
        testFindPolicy(1, tpp1.getPolicyID());
        System.out.println("\u001B[33mExpected result: Policy cannot be found as the userID or policyID is invalid!\u001B[0m");
        testFindPolicy(37637, 1); //invalid userID
        testFindPolicy(1, 49787); //invalid policyID
        testFindPolicy(1, 4); // user dose not have a policy with this ID
        System.out.println("\u001B[33mExpected result: User 1 information and policies will be printed\u001B[0m");
        insuranceCompany.printPolicies(1);
        System.out.println("\u001B[33mExpected result: As userID is not valid nothing will be printed\u001B[0m");
        insuranceCompany.printPolicies(783683); // invalid userID
        System.out.println("\u001B[33mExpected result: All the users with their policies will be printed\u001B[0m");
        insuranceCompany.print();
        System.out.println("\u001B[33mExpected result: Third Party Policy had been created successfully!\u001B[0m");
        testCreateThirdPartyPolicy(4, "Jack", 8, car7, 0, date4, "No previous claims!");
        user4.printPolicies(100);
        System.out.println("\u001B[33mExpected result: Third Party Policy cannot be created as the userID is invalid or policyID is duplicate!\u001B[0m");
        testCreateThirdPartyPolicy(63626, "Ell", 9, car1, 1, date1, "One previous claim!"); // invalid userID
        testCreateThirdPartyPolicy(2, "Lee", tpp3.getPolicyID(), car1, 0, date1, "No previous claims!"); //duplicate policyID for user2
        System.out.println("\u001B[33mExpected result: Comprehensive Policy has been created successfully!\u001B[0m");
        testCreateComprehensivePolicy(4, "Lee", 9, car4, 0, date6, 29, 2);
        user4.printPolicies(100);
        System.out.println("\u001B[33mExpected result: Comprehensive Policy cannot be creates as the userID is invalid or policyID is duplicate.\u001B[0m");
        testCreateComprehensivePolicy(37982, "Lee", 10, car1, 0, date1, 55, 1);
        testCreateComprehensivePolicy(5, "Sue",cp2.getPolicyID(), car1, 0, date1, 39, 2);
        System.out.println("\u001B[33mExpected result: Total payment for each user will be pinted \u001B[0m");
        System.out.println("\u001B[33mExpected total payment for user 1: 1680.0\u001B[0m");
        System.out.println("\u001B[32mPASSED\u001B[0m");
        double totalPaymentUser1 = insuranceCompany.calcTotalPayments(1);
        System.out.println("Total Payment for user 1: " + totalPaymentUser1);
        System.out.println("\u001B[33mExpected total payment for user 2: 2139.8\u001B[0m");
        System.out.println("\u001B[32mPASSED\u001B[0m");
        double totalPaymentUser2 = insuranceCompany.calcTotalPayments(2);
        System.out.println("Total Payment for user 2: " + totalPaymentUser2);
        System.out.println("\u001B[33mExpected total payment for user 3: 2569.8\u001B[0m");
        System.out.println("\u001B[32mPASSED\u001B[0m");
        double totalPaymentUser3 = insuranceCompany.calcTotalPayments(3);
        System.out.println("Total Payment for user 3: " + totalPaymentUser3);
        System.out.println("\u001B[33mExpected total payment for user 4: 740.0\u001B[0m");
        System.out.println("\u001B[32mPASSED\u001B[0m");
        double totalPaymentUser4 = insuranceCompany.calcTotalPayments(4);
        System.out.println("Total Payment for user 4: " + totalPaymentUser4);
        System.out.println("\u001B[33mExpected total payment for user 5: 1480.0\u001B[0m");
        System.out.println("\u001B[32mPASSED\u001B[0m");
        double totalPaymentUser5 = insuranceCompany.calcTotalPayments(5);
        System.out.println("Total Payment for user 5: " + totalPaymentUser5);
        System.out.println("\u001B[33mExpected total payment for user 6: 2550.0\u001B[0m");
        System.out.println("\u001B[32mPASSED\u001B[0m");
        double totalPaymentUser6 = insuranceCompany.calcTotalPayments(6);
        System.out.println("Total Payment for user 6: " + totalPaymentUser6);
        System.out.println("\u001B[33mExpected total payment for user 7: 0.0\u001B[0m");
        System.out.println("\u001B[32mPASSED\u001B[0m");
        double totalPaymentUser7 = insuranceCompany.calcTotalPayments(7);
        System.out.println("Total Payment for user 7: " + totalPaymentUser7);
        System.out.println("\u001B[33mExpected result: The sum of all total payments will be printed \u001B[0m");
        System.out.println("\u001B[33mExpected sum of total payment for all users: 11159.6\u001B[0m");
        System.out.println("\u001B[32mPASSED\u001B[0m");
        double sumOfTotalPayments = insuranceCompany.calcTotalPayments();
        System.out.println("Sum of total payments: " + sumOfTotalPayments);
        System.out.println("\u001B[33mExpected result: The price of the cars included in the insurance policies that User 1 has must have increased. (rise percent: 10%) \u001B[0m");
        insuranceCompany.carPriceRise(1, 0.1);
        user1.printPolicies(100);
        System.out.println("\u001B[33mExpected result: The price of all cars included in users' insurance policies must have increased. (rise percent: 10%) \u001B[0m");
        insuranceCompany.carPriceRise(0.1);
        insuranceCompany.print();
        System.out.println("\u001B[33mExpected result: 1 car will be found for user 1 \u001B[0m");
        testFilterByCarModel(1, "Honda CR-V");
        System.out.println("\u001B[33mExpected result: No car will be found as user 1 dose not have that car model. \u001B[0m");
        testFilterByCarModel(1, "Peykan");
        MyDate expiryDate1 = new MyDate(2025, 12, 3);
        System.out.println("\u001B[33mExpected result: Based on the date " + expiryDate1 + ", 3 insurance policies were found for user 2 that expired before that date. \u001B[0m");
        testFilterByExpiryDate(2, expiryDate1);
        System.out.println("\u001B[33mExpected result: All policies with car model Honda CR-V will be printed.\u001B[0m");
        testFilterByCarModel("Honda CR-V");
        System.out.println("\u001B[33mExpected result: No policy should be found with this car model.\u001B[0m");
        testFilterByCarModel("BMW");
        System.out.println("\u001B[33mExpected result: All policies with the expiry date before " + expiryDate1 + " will be printed.\u001B[0m");
        testFilterByExpiryDate(expiryDate1);
        System.out.println("\u001B[33mExpected result: No policy should be found with this expiry date.\u001B[0m");
        testFilterByExpiryDate(new MyDate(2010, 1, 1));
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

    public void testAddUser (User user)
    {
        if (insuranceCompany.addUser(user))
        {
            System.out.println("User has been added succussfully!");
        }
        else
            System.out.println("User cannot be added as the userID is invalid or duplicate!");
    }

    public void testAddUser (String name, Address address)
    {
        if (insuranceCompany.addUser(name, address))
        {
            System.out.println("User has been added succussfully!");
        }
        else
            System.out.println("User cannot be added as the userID is invalid or duplicate!");
    }

    public void testFindUser (int userID)
    {
        User user = insuranceCompany.findUser(userID);
        if (user != null)
        {
            System.out.println("User has been found successfully!");
        }
        else
            System.out.println("User connot be found as the userID is invalid");
    }

    public void testAddPolicy (int userID, InsurancePolicy policy)
    {
        if (insuranceCompany.addPolicy(userID, policy))
        {
            System.out.println("Policy has been added successfully!");
        }
        else
            System.out.println("Policy cannot be added as the userID is invalid or policyID is duplicate!");
    }

    public void testFindPolicy (int userID, int policyID)
    {
        InsurancePolicy policy = insuranceCompany.findPolicy(userID, policyID);
        if (policy != null)
        {
            System.out.println("Policy has been found succussfully!");
        }
        else
            System.out.println("Policy cannot be found as the userID or policyID is invalid!");
    }

    public void testCreateThirdPartyPolicy (int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments)
    {
        try
        {
            if (insuranceCompany.createThirdPartyPolicy(userID, policyHolderName, id, car, numberOfClaims, expiryDate, comments))
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
    }

    public void testCreateComprehensivePolicy (int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level)
    {
        try
        {
            if (insuranceCompany.createComprehensivePolicy(userID, policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level))
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
    }

    public void testFilterByCarModel (int userID, String carModel)
    {
        HashMap <Integer, InsurancePolicy> filteredPoliciesByCarModel = insuranceCompany.filterByCarModel(userID, carModel);
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

    public void testFilterByExpiryDate (int userID, MyDate date)
    {
        HashMap <Integer, InsurancePolicy> filteredPoliciesByExpiryDate = insuranceCompany.filterByExpiryDate(userID, date);
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

    public void testFilterByCarModel (String carModel)
    {
        ArrayList<InsurancePolicy> result = insuranceCompany.filterByCarModel(carModel);
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

    public void testFilterByExpiryDate  (MyDate date)
    {
        ArrayList<InsurancePolicy> result = insuranceCompany.filterByExpiryDate(date);
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
        System.out.println("\u001B[33mExpected result: Melbourne, Miami, Dallas \u001B[0m");
        testPopulateDistinctCityNames();
        System.out.println("\u001B[33mExpected Value for Melbourne: 6139.6$ \u001B[0m");
        testGetTotalPaymentForCity("Melbourne");
        System.out.println("\u001B[33mExpected Value for Miami: 3055.7799999999997$ \u001B[0m");
        testGetTotalPaymentForCity("Miami");
        System.out.println("\u001B[33mExpected Value for Dallas: 2719.58$ \u001B[0m");
        testGetTotalPaymentForCity("Dallas");
        System.out.println("\u001B[33mExpected Value for Isfahan: 0$ (becuase we don't have this city)\u001B[0m");
        testGetTotalPaymentForCity("Isfahan");
        ArrayList <String> cities = insuranceCompany.populateDistinctCityNames();
        System.out.println("\u001B[33mExpected result: Total payment for each city will be printed.\u001B[0m");
        testGetTotalPaymentPerCity(cities);
        System.out.println("\u001B[33mExpected result: Payment report for each city will be displayed.\u001B[0m");
        testReportPaymentPerCity();
        System.out.println("\u001B[33mExpected result: All distinct car models will be displayed. (Honda CR-V, Honda Civic Hatchback, A3 Sportback, A3 Sportback, E-Class)\u001B[0m");
        testPopulateDistinctCarModels();
        System.out.println("\u001B[33mExpected result: Total count of each car model will be displayed.\u001B[0m");
        testGetTotalCountPerCarModel();
        System.out.println("\u001B[33mExpected result: Total payment for each car model will be displayed.\u001B[0m");
        testGetTotalPaymentPerCarModels();
        System.out.println("\u001B[33mExpected result: Payment report for each car model will be displayed.\u001B[0m");
        testReportPaymentPerCarModel();
        System.out.println("\u001B[33mExpected result: User with ID 1 will be removed successfully.\u001B[0m");
        testRemoveUserByAdmin(1);
        System.out.println("\u001B[33mExpected result: User with ID 100 will not be found.\u001B[0m");
        testRemoveUserByAdmin(100);
        System.out.println("\u001B[33mExpected result: Admin password will be changed successfully.\u001B[0m");
        testChangeAdminPassword("newPassword");
        System.out.println("\u001B[33mExpected result: Policy will be removed successfully.\u001B[0m");
        testRemovePolicy(1, 1);
        System.out.println("\u001B[33mExpected result: Policy cannot be removed because the user ID is invalid.\u001B[0m");
        testRemovePolicy(999, 1); // invalid userID
    }

    public void testPopulateDistinctCityNames ()
    {
        ArrayList <String> cities = insuranceCompany.populateDistinctCityNames();
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

    public void testGetTotalPaymentForCity (String city)
    {
        double totalPayment = insuranceCompany.getTotalPaymentForCity(city);
        System.out.println("Total Payment for City " + city + " " + totalPayment);
    }

    public void testGetTotalPaymentPerCity (ArrayList <String> cities)
    {
        ArrayList <Double> payments = insuranceCompany.getTotalPaymentPerCity(cities);
        for (int i = 0; i < cities.size(); i++)
        {
            System.out.println(cities.get(i) + ": " + payments.get(i));
        }
    }

    public void testReportPaymentPerCity ()
    {
        ArrayList <String> cities = insuranceCompany.populateDistinctCityNames();
        ArrayList <Double> payments = insuranceCompany.getTotalPaymentPerCity(cities);
        insuranceCompany.reportPaymentPerCity(cities, payments);
    }

    public void testPopulateDistinctCarModels ()
    {
        ArrayList <String> carModels = insuranceCompany.populateDistinctCarModels();
        for (String model : carModels)
        {
            System.out.println(model);
        }
    }

    public void testGetTotalCountPerCarModel ()
    {
        ArrayList <String> carModels = insuranceCompany.populateDistinctCarModels();
        ArrayList <Integer> totalCounts = insuranceCompany.getTotalCountPerCarModel(carModels);
        for  (int i = 0; i < carModels.size(); i++)
        {
            System.out.println(carModels.get(i) + ": " + totalCounts.get(i));
        }
    }

    public void testGetTotalPaymentPerCarModels ()
    {
        ArrayList <String> carModels = insuranceCompany.populateDistinctCarModels();
        ArrayList <Double> totalPayments = insuranceCompany.getTotalPaymentPerCarModel(carModels);
        for (int i = 0; i < carModels.size(); i++)
        {
            System.out.println(carModels.get(i) + ": " + totalPayments.get(i));
        }
        
    }

    public void testReportPaymentPerCarModel ()
    {
        ArrayList <String> carModels = insuranceCompany.populateDistinctCarModels();
        ArrayList <Integer> counts = insuranceCompany.getTotalCountPerCarModel(carModels);
        ArrayList <Double> payments = insuranceCompany.getTotalPaymentPerCarModel(carModels);
        insuranceCompany.reportPaymentsPerCarModel(carModels, counts, payments);
    }

    public void testRemoveUserByAdmin (int userID)
    {
        boolean result = insuranceCompany.removeUserByAdmin(userID);
        if (result)
        {
            System.out.println("User with ID " + userID + " was removed successfully!");
        }
        else
            System.out.println("User with ID " + userID + " was not found!");

    }

    public void testChangeAdminPassword (String newPass)
    {
        insuranceCompany.changeAdminPassword(newPass);
        if (insuranceCompany.validateAdmin("admin_ti", newPass))
        {
            System.out.println("Admin password changed successfully!");
        }
        else
            System.out.println("Admin password was not changed!");
    }

    public void testRemovePolicy (int userID, int policyID)
    {
        boolean result = insuranceCompany.removePolicy(userID, policyID);
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
        User sina = new User("Sina", new Address(19, "King", "downtown", "London") );
        sina.addPolicy(cp1);
        sina.addPolicy(tpp3);
        ArrayList <InsurancePolicy> deepCopy = sina.deepCopyPolicies();
        ArrayList <InsurancePolicy> shallowCopy = sina.shallowCopyPolicies();

        sina.setCity("New York");

        try
        {
        ThirdPartyPolicy tpp = new ThirdPartyPolicy("Justin", 10, new Car("Mazda 3", CarType.HATCH, 2020, 35000), 0, new MyDate(2027, 1, 1), "No previous claims!");
        sina.addPolicy(tpp);
        }
        catch (PolicyException e)
        {
            System.out.println(e);
        }

        ArrayList <InsurancePolicy> sortedPolicies = sina.sortPoliciesByDate();

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

    public void copyUsers () throws CloneNotSupportedException
    {
        InsuranceCompany company = new InsuranceCompany("TestCompany", "admin", "1234",100);
        company.addUser(user1);
        company.addUser(user2);
        company.addUser(user3);
        company.addUser(user4);
        company.addUser(user5);
        company.addUser(user6);
        ArrayList <User> deepCopyUsers = company.deepCopyUsers();
        ArrayList <User> shallowCopyUsers = company.shallowCopyUsers();

        company.addUser("Hailey", new Address(12, "Garden st", "Downtown", "Boston"));

        ArrayList <User> sortedUsers = company.sortUsers(); // sorted by city

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

    public void cloneInsuranceCompany () throws CloneNotSupportedException
    {
        InsuranceCompany insuranceCompany = new InsuranceCompany("clone copmany", "clone", "1234", 100);
        
        insuranceCompany.addUser(user1);
        insuranceCompany.addUser(user2);
        insuranceCompany.addUser(user3);
        insuranceCompany.addUser(user4);
        insuranceCompany.addUser(user5);
        insuranceCompany.addUser(user6);

        user1.addPolicy(tpp1);
        user2.addPolicy(cp1);
        user3.addPolicy(tpp2);
        user4.addPolicy(cp2);
        user5.addPolicy(tpp3);
        user6.addPolicy(cp3);

        InsuranceCompany clone = insuranceCompany.clone();

        System.out.println("\nOrginal Insurance Company");
        System.out.println(insuranceCompany);
        System.out.println("Clone Insurance Company");
        System.out.println(clone);

        insuranceCompany.addPolicy(1, cp1);
        insuranceCompany.addUser(new User("Diba", new Address(23, "Queen st", "GreenHouse", "London")));
        insuranceCompany.carPriceRise(0.1);
        try
        {
        insuranceCompany.createComprehensivePolicy(6, "Adele", 9, car1, 0, date1, 60, 3);
        }
        catch (PolicyException e)
        {
            System.out.println(e);
        }
        ArrayList <User> sortedUser = insuranceCompany.sortUsers();
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
        HashMap <String, Integer> totalCounts = user.getTotalCountPerCarModel();

        for (String model : totalCounts.keySet())
        {
            System.out.println(model + ": " + totalCounts.get(model));
        }
    }

    public void testGetTotalPremiumPerCarModelUser (User user)
    {
        HashMap <String, Double> totalPremiums = user.getTotalPremiumPerCarModel();

        for (String model : totalPremiums.keySet())
        {
            System.out.println(model + ": " + totalPremiums.get(model));
        }
    }

    public void testReportUser (User user)
    {
        user.report();
    }

    public void testGetTotalPremiumPerCityCompany ()
    {
        HashMap <String, Double> totalPremiums = insuranceCompany.getTotalPremiumPerCity();

        for (String city : totalPremiums.keySet())
        {
            System.out.println(city + ": " + totalPremiums.get(city));
        }
    }

    public void testGetTotalCountPerCarModelCompany ()
    {
        HashMap <String, Integer> totalCounts = insuranceCompany.getTotalCountPerCarModel();

        for (String model : totalCounts.keySet())
        {
            System.out.println(model + ": " + totalCounts.get(model));
        }
    }

    public void testGetTotalPremiumPerCarModelCompany ()
    {
        HashMap <String, Double> totalPremiums = insuranceCompany.getTotalPremiumPerCarModel();

        for (String model : totalPremiums.keySet())
        {
            System.out.println(model + ": " + totalPremiums.get(model));
        }
    }

    public void testReportComapny ()
    {
        insuranceCompany.report();
    }

    public void testReportAcrossAllUsersCompany ()
    {
        insuranceCompany.reportAcrossAllUsers();
    }

    public void setUpAggregation()
    {
        insuranceCompany.addUser(user1);
        insuranceCompany.addUser(user2);
        insuranceCompany.addUser(user3);
        insuranceCompany.addUser(user4);
        insuranceCompany.addUser(user5);
        insuranceCompany.addUser(user6);

        user1.addPolicy(tpp1); //Honda CR-V
        user1.addPolicy(cp2); //E-Class
        user1.addPolicy(cp3); //E-Class
        // Expected: 
        // Honda CR-V: 1
        // E-Class: 2
        // Honda CR-V (tpp1) = 30000/100 + 2*200 + 100 =800
        // cp2 = 19000/50 + 5*200 + 100 = 1480
        // cp3 = 58000/50 + 1*200 + 100 = 1460
        // E-Class (cp2 + cp3) = 2940 

        user2.addPolicy(tpp2);
        user2.addPolicy(cp2);

        user3.addPolicy(tpp3);

        user4.addPolicy(cp3);
    }

    public void testAggregation ()
    {
        setUpAggregation();
        System.out.println();
        System.out.println("USER AGGREGATION");
        testGetTotalCountPerCarModelUser(user1);
        System.out.println();
        testGetTotalPremiumPerCarModelUser(user1);
        System.out.println();
        testReportUser(user1);
        System.out.println();
        System.out.println("COMPANY AGGREGATION");
        testGetTotalPremiumPerCityCompany();
        System.out.println();
        testGetTotalCountPerCarModelCompany();
        System.out.println();
        testGetTotalPremiumPerCarModelCompany();
        System.out.println();
        testReportComapny();
        System.out.println();
        testReportAcrossAllUsersCompany();
        System.out.println();
    }   

}