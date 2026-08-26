package WEEK1;

import java.util.*;

public class Program
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        final double flatRate = 1.2;
        
        Car car1 = new Car("Honda CR-V", CarType.SUV, 2026, 30921);
        Car car2 = new Car("Honda Civic Hatchback", CarType.HATCH, 2024, 24950);
        Car car3 = new Car("Mercedes-Benz S-Class", CarType.LUX, 2021, 109800);
        Car car4 = new Car("Honda Accord", CarType.SED, 2026, 28395);
        Car car5 = new Car("A3 Sportback", CarType.HATCH, 2022,42000);
        Car car6 = new Car("E-Class", CarType.SED, 2023, 58000);

        MyDate date1 = new MyDate(2030, 8, 19);
        MyDate date2 = new MyDate(2027, 2, 4);
        MyDate date3 = new MyDate(2026, 11, 25);
        MyDate date4 = new MyDate(2036, 12, 1);
        MyDate date5 = new MyDate(2013, 1, 14);
        MyDate date6 = new MyDate(2020, 5, 29);

        ThirdPartyPolicy tpp1 = new ThirdPartyPolicy("James", 01, car1, 3, date1, "3 previous claims!");
        ComprehensivePolicy cp1 = new ComprehensivePolicy("Robert", 02, car2, 0, date2,  24, 3);
        ThirdPartyPolicy tpp2 = new ThirdPartyPolicy("Sara", 03, car3, 0, date3, "No previous claims!");
        ComprehensivePolicy cp2 = new ComprehensivePolicy("Sue", 04, car4, 5, date4, 56, 3);
        ThirdPartyPolicy tpp3 = new ThirdPartyPolicy("Taylor", 05, car5, 2, date5, "2 previous claims!");
        ComprehensivePolicy cp3 = new ComprehensivePolicy("Matthew", 06, car6, 10, date6, 60, 3);

        Address address1 = new Address(42, "Willow Street", "Greenfield", "Melbourne");
        Address address2 = new Address(9, "Willow Way", "Fairview", "Nashville");
        Address address3 = new Address(31, "Aspen Circle", "Orchard Hills", "Dallas");
        Address address4 = new Address(789, " Oak Lane", "Pinecrest", "Miami");

        User user1 = new User("Haleh", 100, address1);
        User user2 = new User("Parisa", 101, address2);
        User user3 = new User("Taraneh", 102, address3);
        User user4 = new User("Payam", 103, address4);

        InsuranceCompany insuranceCompany = new InsuranceCompany("TrustInsure", "admin_ti", "admin@1234", 5);

        // lab 3

        login(scanner, insuranceCompany);

        addUserVersionOne(user1, insuranceCompany);
        addUserVersionOne(user2, insuranceCompany);
        addUserVersionOne(user3, insuranceCompany);
        addUserVersionOne(user4, insuranceCompany);
        addUserVersionOne(user4, insuranceCompany); // duplicate userID

        addUserVersionTwo("Alhan", 105, address1, insuranceCompany);
        addUserVersionTwo("Ali", 101, address2, insuranceCompany); // duplicate userID
        
        addPolicy(100, tpp1, insuranceCompany);
        addPolicy(100, tpp2, insuranceCompany);
        addPolicy(101, cp1, insuranceCompany);
        addPolicy(103, cp3, insuranceCompany);
        addPolicy(103, cp3, insuranceCompany); // duplicate policyID
        addPolicy(8479825, cp2, insuranceCompany); // wrong userID

        createThirdPartyPolicy(100, "Jordan Riley", 07, car6, 0, date6, "No previous claims!", insuranceCompany);
        createComprehensivePolicy(101, "William Martinez", 8 , car3, 0, date3, 35, 2, insuranceCompany);
        createComprehensivePolicy(68667676, "William Martinez", 9, car3, 0, date3, 18, 1, insuranceCompany); // wrong userID
        createThirdPartyPolicy(100, "Jordan Riley", 7, car6, 0, date6, "No previous claims!", insuranceCompany); // duplicate policyID for user 100

        printUserPolicies(scanner, insuranceCompany);

        findAndPrintPolicy(scanner, insuranceCompany);

        System.out.println("------ Printing all users with their policies. ------");
        insuranceCompany.print();
        
        System.out.println("----- Raising the price of cars for all users and policies by 10% -----");
        insuranceCompany.carPriceRise(0.1);
        insuranceCompany.print();

        double totalPremiumPaymentsForOneUser = insuranceCompany.calcTotalPayments(100);
        System.out.println("Total premium payments: " + totalPremiumPaymentsForOneUser);

        double totalPremiumPaymentsForAllUsers = insuranceCompany.calcTotalPayments();
        System.out.println("Total premium payments for all users: " + totalPremiumPaymentsForAllUsers);

        ArrayList <InsurancePolicy> allPolicies = insuranceCompany.allPolicies();
        InsurancePolicy.printPolicies(allPolicies);

        ArrayList <InsurancePolicy> filteredPolicies = insuranceCompany.filterByExpiryDate(100, date1);
        System.out.println("Filtered policies with the date before " + date1 + ": ");
        if (filteredPolicies.isEmpty())
        {
            System.out.println("No policy is found with the date before " + date1 + "!");
        }
        InsurancePolicy.printPolicies(filteredPolicies);

        ArrayList <InsurancePolicy> filteredPolicies2 = insuranceCompany.filterByCarModel("Mercedes-Benz S-Class");
        System.out.println("Filtered Policies by car model:");
        InsurancePolicy.printPolicies(filteredPolicies2);

        System.out.println("Please enter Year: ");
        exceptionHandling(scanner);
        int year = scanner.nextInt();
        System.out.println("Please enter month: ");
        exceptionHandling(scanner);
        int month = scanner.nextInt();
        System.out.println("Please enter day: ");
        exceptionHandling(scanner);
        int day = scanner.nextInt();
        MyDate userDate = new MyDate(year, month, day);
        ArrayList <InsurancePolicy> filteredPoliciesByUserDate = insuranceCompany.filterByExpiryDate(userDate);
        System.out.println("Filtered policied by user's chosen date: ");
        if (filteredPoliciesByUserDate.isEmpty())
        {
            System.out.println("No policy is found with the date before user's chosen date!");
        }
        InsurancePolicy.printPolicies(filteredPoliciesByUserDate);

        User user = insuranceCompany.findUser(100);
        System.out.println("Enter the new street number: ");
        exceptionHandling(scanner);
        int streetnum = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the new street name: ");
        String streetName = scanner.nextLine();
        System.out.println("Enter the new suburb: ");
        String suburb = scanner.nextLine();
        System.out.println("Enter the new city: ");
        String city = scanner.nextLine();
        Address newAddress = new Address(streetnum, streetName, suburb, city);
        user.setAddress(newAddress);
        System.out.println(user);

        System.out.println("End of Lab 3");
        
        // ArrayList <InsurancePolicy> policies = new ArrayList<>(); // ArrayList of Parent
        // policies.add(tpp1); // adding children to the list
        // policies.add(cp1);
        // policies.add(tpp2);
        // policies.add(cp2);

        // for (InsurancePolicy ip : policies)
        // {
        //     System.out.println(ip); // print by using toString() method
        // }

        // InsurancePolicy.printPolicies(policies);
        // System.out.println("Total Payment: " + InsurancePolicy.calcTotalPayments(policies, flatRate) + "$");

        // // lab 2 codes 

        // addPolicy(user1, tpp1);
        // addPolicy(user1, cp1);
        // addPolicy(user1, tpp2);
        // addPolicy(user1, cp2);
        
        // user1.print();

        // System.out.println(user1.toString());

        // InsurancePolicy policy = user1.findPolicy(6);
        // if (policy == null)
        // {
        //     System.out.println("Policy has not been found!");
        // }
        // policy = user1.findPolicy(4);

        // policy.print();
        // policy.carPriceRise(0.1);
        // policy.print();

        // policy.setPolicyHolderName("Robert");

        // policy.setCarModel ("Toyota Camry 2018");
        
        // user1.setCity("Wollongong");
        
        // System.out.println("Enter Street Number: ");
        // int streetNum = scanner.nextInt();
        // scanner.nextLine();
        // System.out.println("Enter Street Name: ");
        // String streetName = scanner.nextLine();
        // System.out.println("Enter Suburb: ");
        // String suburb = scanner.nextLine();
        // System.out.println("Enter City: ");
        // String city = scanner.nextLine();
        // Address newAddress = new Address(streetNum, streetName, suburb, city);
        // user1.setAddress(newAddress);
        // user1.print();

        // System.out.println("Total Premium Payments: " + user1.calcTotalPremiums(flatRate) + "$");

        // user1.carPriceRiseAll(0.1);

        // System.out.println("Total Premium Payments: " + user1.calcTotalPremiums(flatRate) + "$");

        // System.out.println("Enter a Car Model: ");
        // String userCarModel = scanner.nextLine();
        // ArrayList <InsurancePolicy> policyList = user1.filterByCarModel(userCarModel);
        // System.out.println("Policies matching '" + userCarModel + "' :");
        // InsurancePolicy.printPolicies(policyList);

    }

        public static boolean isLoginSuccessful(InsuranceCompany insuranceCompany, String adminUsername, String adminPassword)
        {
            if (insuranceCompany.getAdminUsername().equals(adminUsername) && insuranceCompany.getAdminPassword().equals(adminPassword))
            {
                System.out.println("Successful login!");
                return true;
            }
            System.out.println("Unsuccessful Login. Invalid username or password!");
            return false;
        }

        public static void login (Scanner scanner, InsuranceCompany insuranceCompany)
        {
            boolean successful = false;
            while (!successful)
            {
                System.out.println("Please enter your username: ");
                String username = scanner.nextLine();
                System.out.println("Please enter your password: ");
                String password = scanner.nextLine();
                if (isLoginSuccessful(insuranceCompany, username, password)) {
                successful = true;
                }
            }
        }

        public static void addUserVersionOne (User user, InsuranceCompany insuranceCompany)
        {
            if (insuranceCompany.addUser(user))
            {
                System.out.println("The user has been added successfully!");
            }
            else
                System.out.println("The user cannot be added as the ID already exists!");
        }

        public static void addUserVersionTwo (String name, int userID, Address address, InsuranceCompany insuranceCompany)
        {
            if (insuranceCompany.addUser(name, userID, address))
            {
                System.out.println("The user has been added successfully!");
            }
            else
                System.out.println("The user cannot be added as the ID already exists!");
        }

        public static void addPolicy (int userID, InsurancePolicy policy, InsuranceCompany insuranceCompany)
        {
            if (insuranceCompany.addPolicy(userID, policy))
            {
                System.out.println("The policy has been added successfully!");
            }
            else
                System.out.println("The policy cannot be added as the policy alread exists or the user ID is invalid!");
        }

        public static void createThirdPartyPolicy (int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments, InsuranceCompany insuranceCompany)
        {
            if (insuranceCompany.createThirdPartyPolicy(userID, policyHolderName, id, car, numberOfClaims, expiryDate, comments))
            {
                System.out.println("The Third Party Policy has been added successfully!");
            }
            else
                System.out.println("The Third Party Policy cannot be added as the user ID is invalid or policy ID is duplicate.");
        }

        public static void createComprehensivePolicy (int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level, InsuranceCompany insuranceCompany)
        {
            if (insuranceCompany.createComprehensivePolicy(userID, policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level))
            {
                System.out.println("The Comprenhensive Policy has been added successfully!");
            }
            else
                System.out.println("The Comprehensive Policy cannot be added as the user ID is invalid or policy ID is duplicate.");
        }

        public static void printUserPolicies (Scanner sccaner, InsuranceCompany insuranceCompany)
        {
            System.out.println("Please enter your user ID: ");
    
            exceptionHandling(sccaner);

            int userID = sccaner.nextInt();
            User user = insuranceCompany.findUser(userID);
            if (user != null)
            {
                insuranceCompany.printPolicies(userID);
            }
            else
                System.out.println("User cannot be found!");
        }

        public static void findAndPrintPolicy (Scanner scanner, InsuranceCompany insuranceCompany)
        {
            System.out.println("Please eneter your user ID: ");
            exceptionHandling(scanner);
            int userID = scanner.nextInt();

            System.out.println("Please enter policy ID: ");
            exceptionHandling(scanner);
            int policyID = scanner.nextInt();

            InsurancePolicy policy = insuranceCompany.findPolicy(userID, policyID);
            
            if (policy != null)
            {
                System.out.println(policy);
            }
            else
                System.out.println("Policy cannot be found!");
        }

        public static void exceptionHandling(Scanner scanner)
        {
            while (!scanner.hasNextInt())
            {
                System.out.println("Invalid input! please enter an integer:");
                scanner.next();
            }
        }

        // public static void addPolicy (User user, InsurancePolicy policy)
        // {
        //     if (user.addPolicy(policy))
        //     {
        //         System.out.println("The Policy has been added successfuly!");
        //     }
        //     else
        //         System.out.println("The Policy can not be added as the ID already exists!");
        // }
}