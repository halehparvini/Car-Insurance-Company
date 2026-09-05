package WEEK1;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface
{
    InsuranceCompany insuranceCompany;
    Scanner scanner = new Scanner(System.in);
    public UserInterface (InsuranceCompany insuranceCompany)
    {
        this.insuranceCompany = insuranceCompany;
    }

    public void mainMenu ()
    {
        String option = "";
        while (!option.equals("3"))
        {
            displayMainMenu();
            option = scanner.nextLine();
            switch (option)
            {
                case "1":
                    if (adminLogin())
                    {
                        adminMenu();
                    }
                    break;
                case "2": 
                    //if (userLogin())
                    //{
                    //    userMenu();
                    //}
                    break;
                case "3" :
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
            
        }
    }

    public static void displayMainMenu ()
    {
        System.out.println("Welcom to the TrustInsure company!");
        System.out.println("1. Admin Login");
        System.out.println("2. User Login");
        System.out.println("3. Exite");
        System.out.println("Please choose an option from 1 to 3: ");
    }

    public boolean adminLogin ()
    {
        String username;
        String password;
        System.out.println("Please enter admin username: ");
        username = scanner.nextLine();
        System.out.println("Please enter admin password: ");
        password = scanner.nextLine();
        if (insuranceCompany.validateAdmin(username, password) == false)
        {
            System.out.println("Wrong  username or password!");
            return false;
        }
        else
        {
            System.out.println("Successful login!");
            return true;
        }
    }

    public void adminMenu ()
    {   
        String adminOption = "";
        while (!adminOption.equals("9"))
        {
            displayAdminMenu();
            adminOption = scanner.nextLine();
            switch (adminOption)
            {
                case "1":
                    // testCode(insuranceCompany);
                    break;
                case "2":
                    createUserByAdmin();
                    break;
                case "3":
                    createThirdPartyPolicyByAdmin();
                    break;
                case "4":
                    createComprehensiveByAdmin();
                    break;
                case "5":
                    printUserPolicies(scanner, insuranceCompany);
                    break;
                case "6":
                    filterByCarModelByAdmin();
                    break;
                case "7":
                    filterByExpiryDateByAdmin();      
                    break;
                case "8":
                    updateAddressByAdmin();
                    break;
                case "9":
                    mainMenu();
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }

    }

    public static void displayAdminMenu ()
    {
        System.out.println("Admin Menu");
        System.out.println("1. Test Code");
        System.out.println("2. Create User");
        System.out.println("3. Create Third Party Policy");
        System.out.println("4. Create Comprehensive Policy");
        System.out.println("5. Print User Information");
        System.out.println("6. Filter by Car Model");
        System.out.println("7. Filter by Expiry Date");
        System.out.println("8. Update Address");
        System.out.println("9. Payment Report Per City");
        System.out.println("10. Log Out");
    }  

    public void createUserByAdmin ()
    {
        System.out.println("Create User");
        System.out.println("Please enter username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter user ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine();
        Address address = enterAddress();
        if (insuranceCompany.addUser(username, userID, address))
            {
                System.out.println("The user has been added successfully!");
            }
            else
                System.out.println("The user cannot be added as the ID already exists!");
    }

    public Address enterAddress ()
    {
        System.out.println("Enter user address: ");
        System.out.print("- Street Number: ");
        int streetNumb = scanner.nextInt();
        scanner.nextLine();
        System.out.print("- Street: ");
        String street = scanner.nextLine();
        System.out.print("- Suburb: ");
        String suburb = scanner.nextLine();
        System.out.print("- City: ");
        String city = scanner.nextLine();
        Address address = new Address(streetNumb, street, suburb, city);
        return address;
    }

    public void createThirdPartyPolicyByAdmin ()
    {
        System.out.println("Create Third Party Policy");
        System.out.println("Enter Policy Information: ");
        System.out.print("- User ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine();
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
        if (insuranceCompany.createThirdPartyPolicy(userID, policyHolderName, policyID, car, numberOfClaims, expiryDate, comment))
        {
            System.out.println("The Third Party Policy has been added successfully!");
        }
        else
            System.out.println("The Third Party Policy cannot be added as the user ID is invalid or policy ID is duplicate.");
    }

    public void createComprehensiveByAdmin ()
    {
        System.out.println("Create Comprehensive Policy");
        System.out.println("Enter Policy Information: ");
        System.out.print("- User ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine();
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
        if (insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, car, numberOfClaims, expiryDate, driverAge, level))
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

    public CarType getCarType(Scanner scanner)
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

    public void printUserPolicies (Scanner scaner, InsuranceCompany insuranceCompany)
    {
        System.out.println("Please enter your user ID: ");
        exceptionHandling(scaner);
        int userID = scaner.nextInt();
        User user = insuranceCompany.findUser(userID);
        if (user != null)
        {
            insuranceCompany.printPolicies(userID);
        }
        else
            System.out.println("User cannot be found!");
    }

    public void exceptionHandling(Scanner scanner)
    {
        while (!scanner.hasNextInt())
        {
            System.out.println("Invalid input! please enter an integer:");
            scanner.next();
        }
    }

    public void filterByCarModelByAdmin ()
    {
        System.out.println("Please enter car model to filter: ");
        String carModel = scanner.nextLine();
        ArrayList <InsurancePolicy> filteredPoliciesByCarModel = insuranceCompany.filterByCarModel(carModel);
        InsurancePolicy.printPolicies(filteredPoliciesByCarModel);
        double totalPaymentsForFilteredPolicies = InsurancePolicy.calcTotalPayments(filteredPoliciesByCarModel, insuranceCompany.getFlatRate());
        System.out.println("Total payments for filtered policies: " + totalPaymentsForFilteredPolicies);
    }

    public void filterByExpiryDateByAdmin ()
    {
        System.out.println("Please enter user ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine();
        User user = insuranceCompany.findUser(userID);
        if (user == null)
        {
            System.out.println("User cannot be found!");
            return;
        }
        MyDate date = enterExpiryDate();
        ArrayList <InsurancePolicy> filteredPolicies = insuranceCompany.filterByExpiryDate(userID, date);
        System.out.println("Filtered policies with the date before " + date + ": ");
        if (filteredPolicies.isEmpty())
        {
            System.out.println("No policy is found with the date before " + date + "!");
        }
        InsurancePolicy.printPolicies(filteredPolicies);
    }

    public void updateAddressByAdmin ()
    {
        System.out.println("Please enter user ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine();
        User user = insuranceCompany.findUser(userID);
        if (user == null)
        {
            System.out.println("User cannot be found!");
            return;
        }
        Address address = enterAddress();
        user.setAddress(address);
        System.out.println(user);
    }

    public void pause ()
    {
        System.out.println("Please press any key to continue...");
        scanner.nextLine();
    } 
}