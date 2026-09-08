package WEEK1;

import java.util.ArrayList;

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

    ThirdPartyPolicy tpp1 = new ThirdPartyPolicy("James", 01, car1, 2, date1, "2 previous claims!");
    ComprehensivePolicy cp1 = new ComprehensivePolicy("Robert", 02, car2, 0, date2,  24, 3);
    ThirdPartyPolicy tpp2 = new ThirdPartyPolicy("Sara", 03, car3, 0, date3, "No previous claims!");
    ComprehensivePolicy cp2 = new ComprehensivePolicy("Sue", 04, car4, 5, date4, 56, 3);
    ThirdPartyPolicy tpp3 = new ThirdPartyPolicy("Taylor", 05, car5, 2, date5, "2 previous claims!");
    ComprehensivePolicy cp3 = new ComprehensivePolicy("Matthew", 06, car6, 1, date6, 60, 3);
    ThirdPartyPolicy tpp4 = new ThirdPartyPolicy("Jack", 7, car7, 0, date1, "No previous claims!");
    
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

    public void testCore ()
    {
        System.out.println("Welcome to Test Core");
        System.out.println("\u001B[32mExpected result: Successful login!\u001B[0m");
        testValidateAdmin("admin_ti", "admin@1234");
        System.out.println("\u001B[32mExpected rsult: Invalid username or password!\u001B[0m");
        testValidateAdmin("wrong username", "admin@1234");
        System.out.println("\u001B[32mExpected result: 6 users have been added successfully!\u001B[0m");
        testAddUser(user1);
        testAddUser(user2);
        testAddUser(user3);
        testAddUser(user4);
        testAddUser(user5);
        testAddUser(user6);
        System.out.println("\u001B[32mExpected result: User cannot be added as the userID is invalid or duplicate!\u001B[0m");
        testAddUser(user1); // duplicate userID
        System.out.println("\u001B[32mExpected result: User has been added successfully!\u001B[0m");
        testAddUser("Alhan", 7, address1); // create user7 ????????
        System.out.println("\u001B[32mExpected rsult: User has been found successfully!\u001B[0m");
        testFindUser(1);
        System.out.println("\u001B[32mExpected result: User cannot be found as the userID is invalid!\u001B[0m");
        testFindUser(8489); // invalid userID
        System.out.println("\u001B[32mExpected result: Policies have been added successfuly!\u001B[0m");
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
        System.out.println("\u001B[32mExpected result: Policies cannot be added as the userID is invalid or policyID is duplicate!\u001B[0m");
        testAddPolicy(1838, cp2); // invalid userID
        testAddPolicy(1, cp1); // duplicate policy
        System.out.println("\u001B[32mExpected result: Policy has been found successfully!\u001B[0m");
        testFindPolicy(1, 2);
        System.out.println("\u001B[32mExpected result: Policy cannot be found as the userID or policyID is invalid!\u001B[0m");
        testFindPolicy(37637, 1); //invalid userID
        testFindPolicy(1, 49787); //invalid policyID
        testFindPolicy(1, 1); // user dose not have a policy with this ID
        System.out.println("\u001B[32mExpected result: User 1 information and policies will be printed\u001B[0m");
        insuranceCompany.printPolicies(1);
        System.out.println("\u001B[32mExpected result: As userID is not valid nothing will be printed\u001B[0m");
        insuranceCompany.printPolicies(783683); // invalid userID
        System.out.println("\u001B[32mExpected result: All the users with their policies will be printed\u001B[0m");
        insuranceCompany.print();
        System.out.println("\u001B[32mExpected result: Third Party Policy had been created successfully!\u001B[0m");
        testCreateThirdPartyPolicy(4, "Jack", 8, car7, 0, date4, "No previous claims!");
        user4.printPolicies(100);
        System.out.println("\u001B[32mExpected result: Third Party Policy cannot be created ad the userID is invalid or policyID is duplicate!\u001B[0m");
        testCreateThirdPartyPolicy(63626, "Ell", 9, car1, 1, date1, "One previous claim!"); // invalid userID
        testCreateThirdPartyPolicy(2, "Lee", 5, car1, 0, date1, "No previous claims!"); //duplicate policyID for user2
        System.out.println("\u001B[32mExpected result: Comprehensive Policy has been created successfully!\u001B[0m");
        testCreateComprehensivePolicy(4, "Lee", 9, car4, 0, date6, 29, 2);
        user4.printPolicies(100);
        System.out.println("\u001B[32mExpected result: Comprehensive Policy cannot be creates as the userID is invalid or policyID is duplicate.\u001B[0m");
        testCreateComprehensivePolicy(37982, "Lee", 10, car1, 0, date1, 55, 1);
        testCreateComprehensivePolicy(5, "Sue", 4, car1, 0, date1, 39, 2);
        System.out.println("\u001B[32mExpected result: Total payment for each user will be pinted \u001B[0m");
        double totalPaymentUser1 = insuranceCompany.calcTotalPayments(1);
        System.out.println("Total Payment for user 1: " + totalPaymentUser1);
        double totalPaymentUser2 = insuranceCompany.calcTotalPayments(2);
        System.out.println("Total Payment for user 2: " + totalPaymentUser2);
        double totalPaymentUser3 = insuranceCompany.calcTotalPayments(3);
        System.out.println("Total Payment for user 3: " + totalPaymentUser3);
        double totalPaymentUser4 = insuranceCompany.calcTotalPayments(4);
        System.out.println("Total Payment for user 4: " + totalPaymentUser4);
        double totalPaymentUser5 = insuranceCompany.calcTotalPayments(5);
        System.out.println("Total Payment for user 5: " + totalPaymentUser5);
        double totalPaymentUser6 = insuranceCompany.calcTotalPayments(6);
        System.out.println("Total Payment for user 6: " + totalPaymentUser6);
        double totalPaymentUser7 = insuranceCompany.calcTotalPayments(7);
        System.out.println("Total Payment for user 7: " + totalPaymentUser7);
        System.out.println("\u001B[32mExpected result: The sum of all total payments will be printed \u001B[0m");
        double sumOfTotalPayments = insuranceCompany.calcTotalPayments();
        System.out.println("Sum of total payments: " + sumOfTotalPayments);
        System.out.println("\u001B[32mExpected result: The price of the cars included in the insurance policies that User 1 has must have increased. (rise percent: 10%) \u001B[0m");
        insuranceCompany.carPriceRise(1, 0.1);
        user1.printPolicies(100);
        System.out.println("\u001B[32mExpected result: The price of all cars included in users' insurance policies must have increased. (rise percent: 10%) \u001B[0m");
        insuranceCompany.carPriceRise(0.1);
        insuranceCompany.print();
        System.out.println("\u001B[32mExpected result: 1 car will be found for user 1 \u001B[0m");
        testFilterByCarModel(1, "Honda CR-V");
        System.out.println("\u001B[32mExpected result: No car will be found as user 1 dose not have that car model. \u001B[0m");
        testFilterByCarModel(1, "Peykan");
        MyDate expiryDate1 = new MyDate(2025, 12, 3);
        System.out.println("\u001B[32mExpected result: Based on the date " + expiryDate1 + ", 3 insurance policies were found for user 1 that expired before that date. \u001B[0m");
        testFilterByExpiryDate(2, expiryDate1);
        System.out.println("\u001B[32mExpected result: All policies with car model Honda CR-V will be printed.\u001B[0m");
        testFilterByCarModel("Honda CR-V");
        System.out.println("\u001B[32mExpected result: No policy should be found with this car model.\u001B[0m");
        testFilterByCarModel("BMW");
        System.out.println("\u001B[32mExpected result: All policies with the expiry date before " + expiryDate1 + " will be printed.\u001B[0m");
        testFilterByExpiryDate(expiryDate1);
        System.out.println("\u001B[32mExpected result: No policy should be found with this expiry date.\u001B[0m");
        testFilterByExpiryDate(new MyDate(1, 1, 2050));
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

    public void testAddUser (String name, int userID, Address address)
    {
        if (insuranceCompany.addUser(name, userID, address))
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
        if (insuranceCompany.createThirdPartyPolicy(userID, policyHolderName, id, car, numberOfClaims, expiryDate, comments))
        {
            System.out.println("Third Party Policy has been created successfully!");
        }
        else
            System.out.println("Third Party Policy cannot be created ad the userID is invalid or policyID is duplicate!");
    }

    public void testCreateComprehensivePolicy (int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level)
    {
        if (insuranceCompany.createComprehensivePolicy(userID, policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level))
        {
            System.out.println("Comprehensive Policy has been created successfully!");
        }
        else
            System.out.println("Comprehensive Policy cannot be created as the userID is invalid or policyID is duplicate!");
    }

    public void testFilterByCarModel (int userID, String carModel)
    {
        ArrayList <InsurancePolicy> filteredPoliciesByCarModel = insuranceCompany.filterByCarModel(userID, carModel);
        if (!filteredPoliciesByCarModel.isEmpty())
        {
            for (InsurancePolicy policy : filteredPoliciesByCarModel)
            {
                System.out.println(policy);
            }
        }
        else
            System.out.println("No policy was found for this user with this car model.");
    }

    public void testFilterByExpiryDate (int userID, MyDate date)
    {
        ArrayList <InsurancePolicy> filteredPoliciesByExpiryDate = insuranceCompany.filterByExpiryDate(userID, date);
        if (!filteredPoliciesByExpiryDate.isEmpty())
        {
            for (InsurancePolicy policy : filteredPoliciesByExpiryDate)
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
        System.out.println("\u001B[32mExpected result: Melbourne, Miami, Dallas \u001B[0m");
        testPopulateDistinctCityNames();
        System.out.println("\u001B[32mExpected Value for Melbourne: 6139.6$ \u001B[0m");
        testGetTotalPaymentForCity("Melbourne");
        System.out.println("\u001B[32mExpected Value for Miami: 3055.7799999999997$ \u001B[0m");
        testGetTotalPaymentForCity("Miami");
        System.out.println("\u001B[32mExpected Value for Dallas: 2719.58$ \u001B[0m");
        testGetTotalPaymentForCity("Dallas");
        System.out.println("\u001B[32mExpected Value for Isfahan: 0$ (becuase we don't have this city)\u001B[0m");
        testGetTotalPaymentForCity("Isfahan");
        ArrayList <String> cities = insuranceCompany.populateDistinctCityNames();
        System.out.println("\u001B[32mExpected result: Total payment for each city will be printed.\u001B[0m");
        testGetTotalPaymentPerCity(cities);
        System.out.println("\u001B[32mExpected result: Payment report for each city will be displayed.\u001B[0m");
        testReportPaymentPerCity();
        System.out.println("\u001B[32mExpected result: All distinct car models will be displayed. (Honda CR-V, Honda Civic Hatchback, A3 Sportback, A3 Sportback, E-Class)\u001B[0m");
        testPopulateDistinctCarModels();
        System.out.println("\u001B[32mExpected result: Total count of each car model will be displayed.\u001B[0m");
        testGetTotalCountPerCarModel();
        System.out.println("\u001B[32mExpected result: Total payment for each car model will be displayed.\u001B[0m");
        testGetTotalPaymentPerCarModels();

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
}