package WEEK1;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterfaceForUser {

    User user;
    InsuranceCompany insuranceCompany;
    InsurancePolicy policy;
    Scanner scanner = new Scanner(System.in);
    public UserInterfaceForUser (InsuranceCompany insuranceCompany, InsurancePolicy policy)
    {
        this.insuranceCompany = insuranceCompany;
        this.policy = policy;
    }

    public User loginUser ()
    {
        System.out.print("Enter User ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine();
        for (User user : insuranceCompany.getUsers())
        {
            if (user.getUserID() == userID)
            {
                System.out.println("Successful Login!");
                return user;
            }
        }
        System.out.println("Invalid User ID");
        return null;
    }

    public void userMenu (User user)
    {
        this.user = user;
        String userOption = "";
        while (!userOption.equals("13"))
        {
            displayUserMenu();
            userOption = scanner.nextLine();
            switch (userOption)
            {
                case "1":
                    addPolicyByUser(policy);
                    pause();
                    break;
                case "2":
                    findPolicyByUser();
                    pause();
                    break;
                case "3":
                    printPolicyInformationByUser();
                    pause();
                    break;
                case "4":
                    printAllPoliciesByUser(insuranceCompany.getFlatRate());
                    pause();
                    break;
                case "5":
                    calcTotalPremiumsByUser(insuranceCompany.getFlatRate());
                    pause();
                    break;
                case "6":
                    filterByCarModelByUser();
                    pause();
                    break;
                case "7":
                    filterByExpiryDateByUser();
                    pause();
                    break;
                case "8":
                    changeAddressByUser();
                    pause();
                    break;
                case "9":
                    createThirdPartyPolicyByUser();
                    pause();
                    break;
                case "10":
                    createComprehensivePolicyByUser();
                    pause();
                    break;
                case "11":
                    reportPaymentPerCarModelByUser();
                    pause();
                    break;
                case "12":
                    removePolicyByUser();
                    pause();
                    break;
                case "13":
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    public void displayUserMenu ()
    {
        System.out.println("1. Add a Policy");
        System.out.println("2. Find a Policy");
        System.out.println("3. Print Policy Information");
        System.out.println("4. Print All Policies");
        System.out.println("5. Calculate Total Premiums");
        System.out.println("6. Filter By Car Model");
        System.out.println("7. Filter By Expiry Date");
        System.out.println("8. Change Address");
        System.out.println("9. Create Third Party Policy");
        System.out.println("10. Create Comprehensive Policy");
        System.out.println("11. Payment Report Per Car Model");
        System.out.println("12. Remove Policy");
        System.out.println("13. Log Out");
    }

    public void addPolicyByUser (InsurancePolicy policy)
    {
        if (user.addPolicy(policy))
        {
            System.out.println("The Policy has been added successfuly!");
        }
        else
        System.out.println("The Policy can not be added as the ID already exists!");
    }

    public void findPolicyByUser ()
    {
        System.out.println("Please enter policy ID: ");
        int policyID = scanner.nextInt();
        InsurancePolicy policy = user.findPolicy(policyID);
        if (policy != null)
        {
            System.out.println(policy);
        }
        else
        System.out.println("Policy cannot be found!");
    }

    public void printPolicyInformationByUser ()
    {
        user.toString();
    }

    public void printAllPoliciesByUser (int flatRate)
    {
        user.printPolicies(flatRate);
    }
    
    public void calcTotalPremiumsByUser (int flatRate)
    {
        user.calcTotalPremiums(flatRate);
    }

    public void filterByCarModelByUser ()
    {
        System.out.print("Enter Car Model to filter: ");
        String carModel = scanner.nextLine();
        user.filterByCarModel(carModel);
    }

    public void filterByExpiryDateByUser ()
    {
        System.out.print("- Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("- Month: ");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.println("- Day: ");
        int day = scanner.nextInt();
        scanner.nextLine();
        MyDate expiryDate = new MyDate(year, month, day);
        user.filterByExpiryDate(expiryDate);
    }

    public void changeAddressByUser ()
    {
        System.out.println("- Street Number: ");
        int streetNum = scanner.nextInt();
        scanner.nextLine();
        System.out.println("- Street: ");
        String street = scanner.nextLine();
        System.out.println("- Suburb: ");
        String suburb = scanner.nextLine();
        System.out.println("- City: ");
        String city = scanner.nextLine();
        Address newAddress = new Address(streetNum, street, suburb, city);
        user.setAddress(newAddress);
        user.print();
    }

    public void createThirdPartyPolicyByUser ()
    {
        System.out.println("Create Third Party Policy");
        System.out.println("Enter Policy Information: ");
        System.out.print("- Policy Holder Name: ");
        String policyHolderName = scanner.nextLine();
        System.out.print("- Policy ID: ");
        int policyID = scanner.nextInt();
        scanner.nextLine();
        Car car = enterCar();
        System.out.print("- Number of Claims: ");
        int numberOfClaims = scanner.nextInt();
        scanner.nextLine();
        MyDate expiryDate = enterExpiryDate();
        System.out.println("- Comment: ");
        String comment = scanner.nextLine();
        if (user.createThirdPartyPolicy(policyHolderName, policyID, car, numberOfClaims, expiryDate, comment))
        {
            System.out.println("The Third Party Policy has been added successfully!");
        }
        else
            System.out.println("The Third Party Policy cannot be added as the user ID is invalid or policy ID is duplicate.");
    }
    
    public void createComprehensivePolicyByUser ()
    {
        System.out.println("Create Comprehensive Policy");
        System.out.println("Enter Policy Information: ");
        System.out.print("- Policy Holder Name: ");
        String policyHolderName = scanner.nextLine();
        System.out.print("- Policy ID: ");
        int policyID = scanner.nextInt();
        scanner.nextLine();
        Car car = enterCar();
        System.out.print("- Number of Claims: ");
        int numberOfClaims = scanner.nextInt();
        scanner.nextLine();
        MyDate expiryDate = enterExpiryDate();
        System.out.print("- Driver Age: ");
        int driverAge = scanner.nextInt();
        scanner.nextLine();
        System.out.print("- Level: ");
        int level = scanner.nextInt();
        scanner.nextLine();
        if (user.createComprehensivePolicy(policyHolderName, policyID, car, numberOfClaims, expiryDate, driverAge, level))
        {
            System.out.println("The Comprenhensive Policy has been added successfully!");
        }
        else
            System.out.println("The Comprehensive Policy cannot be added as the user ID is invalid or policy ID is duplicate.");
    }

    public Car enterCar ()
    {
        System.out.println("Enter user car information: ");
        System.out.print("- Car Model: ");
        String carModel = scanner.nextLine();
        CarType carType = getCarType(scanner);
        System.out.print("- Manufacturing Year: ");
        int manufacturingYear = scanner.nextInt();
        scanner.nextLine();
        System.out.print("- Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        Car car = new Car(carModel, carType, manufacturingYear, price);
        return car;
    }

    public CarType getCarType (Scanner scanner)
    {
        CarType carType = null;
        while (carType == null)
        {
            System.out.print("- Car Type (SUV, SED, LUX, HATCH): ");
            String input = scanner.nextLine().toUpperCase();
            try
            {
                carType = CarType.valueOf(input);
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Invalid car type. Please try again.");
            }
        }
        return carType;
    }

    public MyDate enterExpiryDate ()
    {
        System.out.println("Enter Policy Expiry Date: ");
        System.out.print("- Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("- Month: ");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.print("- Day: ");
        int day = scanner.nextInt();
        scanner.nextLine();
        MyDate expiryDate = new MyDate(year, month, day);
        return expiryDate;
    }

    public void reportPaymentPerCarModelByUser ()
    {
        ArrayList <String> carModels = user.populateDistinctCarModels();
        ArrayList <Integer> counts = user.getTotalCountPerCarModel(carModels);
        ArrayList <Double> payments = user.getTotalPaymentPerCarModel(carModels, insuranceCompany.getFlatRate());
        user.reportPaymentsPerCarModel(carModels, counts, payments);
    }

    public void removePolicyByUser ()
    {
        System.out.print("Enter policy ID: ");
        int policyID = scanner.nextInt();
        scanner.nextLine();
        if (user.removePolicy(policyID))
        {
            System.out.println("Policy with ID: " + policyID + " has been removed successfully!");
        }
        else
        {
            System.out.println("Policy ID " + policyID + " cannot be found for this user!");
        }
    }

    public void pause ()
    {
        System.out.println("Please press any key to continue...");
        scanner.nextLine();
    }
}