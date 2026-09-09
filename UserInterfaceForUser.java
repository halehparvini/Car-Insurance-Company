package WEEK1;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterfaceForUser {

    private User user;
    private InsuranceCompany insuranceCompany;
    Scanner scanner = new Scanner(System.in);
    public UserInterfaceForUser (InsuranceCompany insuranceCompany)
    {
        this.insuranceCompany = insuranceCompany;
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
                    addPolicyByUser();
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
                    calcTotalPremiumsByUser(insuranceCompany.getFlatRate());
                    pause();
                    break;
                case "5":
                    filterByCarModelByUser();
                    pause();
                    break;
                case "6":
                    filterByExpiryDateByUser();
                    pause();
                    break;
                case "7":
                    changeAddressByUser();
                    pause();
                    break;
                case "8":
                    reportPaymentPerCarModelByUser();
                    pause();
                    break;
                case "9":
                    removePolicyByUser();
                    pause();
                    break;
                case "10":
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
        System.out.println("4. Calculate Total Premiums");
        System.out.println("5. Filter By Car Model");
        System.out.println("6. Filter By Expiry Date");
        System.out.println("7. Change Address");
        System.out.println("8. Payment Report Per Car Model");
        System.out.println("9. Remove Policy");
        System.out.println("10. Log Out");
    }

    public void addPolicyByUser ()
    {
        System.out.println("Choose Policy Type:");
        System.out.println("1. Third Party");
        System.out.println("2. Comprehensive");
        System.out.print("Enter option: ");
        String option = scanner.nextLine();
        switch (option)
        {
            case "1":
                createThirdPartyPolicyByUser();
                break;
            case "2":
                createComprehensivePolicyByUser();
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }
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
        System.out.println(user);
    }
    
    public void calcTotalPremiumsByUser (int flatRate)
    {
        double total = user.calcTotalPremiums(flatRate);
        System.out.println("Total Premiums: " + total);
    }

    public void filterByCarModelByUser ()
    {
        System.out.print("Enter Car Model to filter: ");
        String carModel = scanner.nextLine();
        ArrayList <InsurancePolicy> policies = user.filterByCarModel(carModel);
        if (!policies.isEmpty())
        {
            for (InsurancePolicy policy : policies)
            {
                System.out.println(policy);
            }
        }
        else
            System.out.println("No policy was found");
        
    }

    public void filterByExpiryDateByUser ()
    {
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
        ArrayList <InsurancePolicy> policies = user.filterByExpiryDate(expiryDate);
        if(!policies.isEmpty())
        {
            for (InsurancePolicy policy : policies)
            {
                System.out.println(policy);
            }
        }
        else
            System.out.println("No Policy was found!");
        
    }

    public void changeAddressByUser ()
    {
        System.out.print("- Street Number: ");
        int streetNum = scanner.nextInt();
        scanner.nextLine();
        System.out.print("- Street: ");
        String street = scanner.nextLine();
        System.out.print("- Suburb: ");
        String suburb = scanner.nextLine();
        System.out.print("- City: ");
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
        System.out.print("- Comment: ");
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
        CarType carType = getCarType();
        System.out.print("- Manufacturing Year: ");
        int manufacturingYear = scanner.nextInt();
        scanner.nextLine();
        System.out.print("- Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        Car car = new Car(carModel, carType, manufacturingYear, price);
        return car;
    }

    public CarType getCarType ()
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
            System.out.println("Policy with ID " + policyID + " has been removed successfully!");
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