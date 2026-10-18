package WEEK1;

import java.util.Scanner;

public class UserInterfaceForAdmin
{
    private InsuranceCompany insuranceCompany;
    Scanner scanner = new Scanner(System.in);

    public UserInterfaceForAdmin (InsuranceCompany insuranceCompany)
    {
        this.insuranceCompany = insuranceCompany;
    }

    public boolean loginAdmin ()
    {
        System.out.println("Please enter admin username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter admin password: ");
        String password = scanner.nextLine();
        if (insuranceCompany.validateAdmin(username, password) == false)
        {
            System.out.println("Wrong username or password!");
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
        while (!adminOption.equals("30"))
        {
            displayAdminMenu();
            adminOption = scanner.nextLine();
            switch (adminOption)
            {
                case "1":
                    testMenu();
                    pause();
                    break;
                case "2":
                    createUserByAdmin();
                    pause();
                    break;
                case "3":
                    createThirdPartyPolicyByAdmin();
                    pause();
                    break;
                case "4":
                    createComprehensivePolicyByAdmin();
                    pause();
                    break;
                case "5":
                    printUserPolicies();
                    pause();
                    break;
                case "6":
                    filterByCarModelByAdmin();
                    pause();
                    break;
                case "7":
                    filterByExpiryDateByAdmin();  
                    pause();    
                    break;
                case "8":
                    updateAddressByAdmin();
                    pause();
                    break;
                case "9":
                    insuranceCompany.printPaymentPerCityReport();
                    pause();
                    break;
                case "10":
                    insuranceCompany.printPaymentPerCarModelReport();
                    pause();
                    break;
                case "11":
                    changeAdminPassword();
                    pause();
                    break;
                case "12":
                    removeUserByAdmin();
                    pause();
                    break;
                case "13":
                    removePolicyByAdmin();
                    pause();
                    break;
                case "14":
                    reportOneUser();
                    pause();
                    break;
                case "15":
                    insuranceCompany.report();
                    pause();
                    break;
                case "16":
                    insuranceCompany.reportAcrossAllUsers();
                    pause();
                    break;
                case "17":
                    saveBinaryFile();
                    pause();
                    break;
                case "18":
                    loadBinaryFile();
                    pause();
                    break;
                case "19":
                    saveTextFile();
                    pause();
                    break;
                case "20":
                    loadTextFile();
                    pause();
                    break;
                case "21":
                    deepCopyPolicies();
                    pause();
                    break;
                case "22":
                    insuranceCompany.printShallowCopyPolicies();
                    pause();
                    break;
                case "23":
                    deepCopyUsers();
                    pause();
                    break;
                case "24":
                    insuranceCompany.printShallowCopyUsers();
                    pause();
                    break;
                case "25":
                    deepCopyCompany();
                    pause();
                    break;
                case "26":
                    insuranceCompany.printShallowCopyCompany();
                    pause();
                    break;
                case "27":
                    insuranceCompany.printUsersSortedByCity();
                    pause();
                    break;
                case "28":
                    insuranceCompany.printUsersSortedByPremium();
                    pause();
                    break;
                case "29":
                    insuranceCompany.printPoliciesSortedByExpiryDate();
                    pause();
                    break;
                case "30":
                    System.out.println("Logged Out.");
                    break;
                default:
                    System.out.println("Invalid option! Please choose a number from 1 to 30.");
                    continue;
            }
        }

    }

    public void displayAdminMenu ()
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
        System.out.println("10. Payment Report Per Car Model");
        System.out.println("11. Change Admin Password");
        System.out.println("12. Remove User");
        System.out.println("13. Remove Policy");
        // lab 5
        System.out.println("14. Report for one user");
        System.out.println("15. Report Total Premium Per City");
        System.out.println("16. Report Across All Users");
        // lab 6
        System.out.println("17. Save Company to Binary File");
        System.out.println("18. Load Company from Binary File");
        System.out.println("19. Save Company to Text File");
        System.out.println("20. Load Company to Text File");
        // Assignment 2
        System.out.println("21. Deep Copy Policies");
        System.out.println("22. Shallow Copy Policies");
        System.out.println("23. Deep Copy Users");
        System.out.println("24. Shallow Copy Users");
        System.out.println("25. Deep Copy Company");
        System.out.println("26. Shallow Copy Company");
        System.out.println("27. Sort Users by City Name");
        System.out.println("28. Sort Users by Premium Payments");
        System.out.println("29. Sort Policies by Expiry Date");
        System.out.println("30. Log Out");
    }

    public void testMenu ()
    {
        String choice = "";
        while (!choice.equals("3"))
        {
            displayTestMenu();
            choice = scanner.nextLine().trim();
            switch (choice)
            {
                case "1":
                    insuranceCompany.runCoreTests();
                    break;
                case "2":
                    insuranceCompany.runStandardAndAdvancedTests();
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }

    public void displayTestMenu ()
    {
        System.out.println("1. Show Test Core");
        System.out.println("2. Show Test Standard/Advanced");
        System.out.println("3. Return to Admin Menu");
        System.out.println("Enter your choice from 1 to 3: ");
    }

    public void pause ()
    {
        System.out.println("Please press any key to continue...");
        scanner.nextLine();
    }

    public void createUserByAdmin()
    {
        System.out.println("Create User");
        System.out.println("Please enter username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter address: ");
        System.out.print("- Street Number: ");
        int streetNumber = exceptionHandlingInt();
        System.out.print("- Street: ");
        String street = scanner.nextLine();
        System.out.print("- Suburb: ");
        String suburb = scanner.nextLine();
        System.out.print("- City: ");
        String city = scanner.nextLine();
        try
        {
            int newUserID = insuranceCompany.createUser(username, streetNumber, street, suburb, city);
            if (newUserID != -1)
            {
                System.out.println("The user has been added successfully!");
                System.out.println("Created User ID: " + newUserID);
            }
            else
            {
                System.out.println("The user cannot be added!");
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("The user cannot be added: " + e.getMessage());
        }
    }

    public void printUserPolicies ()
    {
        System.out.println("Please enter user ID: ");
        int userID = exceptionHandlingInt();
        if (!insuranceCompany.showUserPolicies(userID))
        {
            System.out.println("User cannot be found!");
        }
    }

    public void updateAddressByAdmin ()
    {
        System.out.println("Please enter user ID: ");
        int userID = exceptionHandlingInt();
        if (!insuranceCompany.userExists(userID))
        {
            System.out.println("User cannot be found!");
            return;
        }
        System.out.println("Enter new user address: ");
        System.out.print("- Street Number: ");
        int streetNumber = exceptionHandlingInt();
        System.out.print("- Street: ");
        String street = scanner.nextLine();
        System.out.print("- Suburb: ");
        String suburb = scanner.nextLine();
        System.out.print("- City: ");
        String city = scanner.nextLine();
        try
        {
            if (insuranceCompany.updateUserAddress(userID, streetNumber, street, suburb, city))
            {
                System.out.println("Address updated succussfully!");
                System.out.println(insuranceCompany.getUserInfo(userID));
            }
            else
            {
                System.out.println("The address cannot be updated.");
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("The Address cannot be updated.");
        }
    }

    public void removeUserByAdmin ()
    {
        System.out.print("Enter user ID to remove: ");
        int userIDToRemove = exceptionHandlingInt();
        if (insuranceCompany.removeUserByAdmin(userIDToRemove))
        {
            System.out.println("User with ID " + userIDToRemove + " has been removed successfully!");
        }
        else
        {
            System.out.println("User ID " + userIDToRemove +  " cannot be found!");
        }
    }

    public void createThirdPartyPolicyByAdmin ()
    {
        System.out.println("Create Third Party Policy");
        System.out.println("Enter Policy Information: ");
        System.out.print("- User ID: ");
        int userID = exceptionHandlingInt();
        System.out.print("- Policy Holder Name: ");
        String policyHolderName = scanner.nextLine();
        System.out.print("- Policy ID: ");
        int policyID = exceptionHandlingInt();
        System.out.println("Enter user car information: ");
        System.out.print("- Car Model: ");
        String carModel = scanner.nextLine();
        String carType = enterCartype();
        System.out.print("- Manufacturing Year: ");
        int manufacturingYear = exceptionHandlingInt();
        System.out.print("- Price: ");
        double price = exceptionHandlingDouble();
        System.out.print("- Number of Claims: ");
        int numberOfClaims = exceptionHandlingInt();
        int [] expiry = enterExpiryDate();
        System.out.println("- Comment: ");
        String comment = scanner.nextLine();
        try
        {
        if (insuranceCompany.createThirdPartyPolicy(userID, policyHolderName, policyID, carModel, carType, manufacturingYear, price, numberOfClaims, expiry[0], expiry[1], expiry[2], comment))
                System.out.println("The Third Party Policy has been added successfully!");
            else
                System.out.println("The Third Party Policy cannot be added as the user ID is invalid or policy ID is duplicate.");
        }
        catch (PolicyException e)
        {
            System.out.println(e);
        }
        catch (PolicyHolderNameException e)
        {
            System.out.println("Invalid policy holder name!");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Invalid input.");
        }
    }

    public void createComprehensivePolicyByAdmin ()
    {
        System.out.println("Create Comprehensive Policy");
        System.out.println("Enter Policy Information: ");
        System.out.print("- User ID: ");
        int userID = exceptionHandlingInt();
        System.out.print("- Policy Holder Name: ");
        String policyHolderName = scanner.nextLine();
        System.out.print("- Policy ID: ");
        int policyID = exceptionHandlingInt();
        System.out.println("Enter user car information: ");
        System.out.print("- Car Model: ");
        String carModel = scanner.nextLine();
        String carType = enterCartype();
        System.out.print("- Manufacturing Year: ");
        int manufacturingYear = exceptionHandlingInt();
        System.out.print("- Price: ");
        double price = exceptionHandlingDouble();
        System.out.print("- Number of Claims: ");
        int numberOfClaims = exceptionHandlingInt();
        int [] expiry = enterExpiryDate();
        System.out.print("- Driver Age: ");
        int driverAge = exceptionHandlingInt();
        System.out.print("- Level: ");
        int level = exceptionHandlingInt();
        try
        {
        if (insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, carModel, carType, manufacturingYear, price, numberOfClaims, expiry[0], expiry[1], expiry[2], driverAge, level))
                System.out.println("The Comprenhensive Policy has been added successfully!");
            else
                System.out.println("The Comprehensive Policy cannot be added as the user ID is invalid or policy ID is duplicate.");
        }
        catch (PolicyException e)
        {
            System.out.println(e);
        }
        catch (PolicyHolderNameException e)
        {
            System.out.println("Invalid policy holder name!");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Invalid input.");
        }
    }

    public String enterCartype ()
    {
        String input = "";
        boolean valid = false;
        while (!valid)
        {
            System.out.print("- Car Type (SUV, SED, LUX, HATCH): ");
            input = scanner.nextLine().trim().toUpperCase();
            valid = insuranceCompany.isValidCarType(input);
            if (!valid)
            {
                System.out.println("Invalid car type. Please try again,");
            }
        }
        return input;
    }

    private int[] enterExpiryDate ()
    {
        int year = 0;
        int month = 0;
        int day = 0;
        boolean valid = false;
        while (!valid)
        {
            System.out.println("Enter Policy Expiry Date: ");
            System.out.print("- Year: ");
            year = exceptionHandlingInt();
            System.out.print("- Month: ");
            month = exceptionHandlingInt();
            System.out.print("- Day: ");
            day = exceptionHandlingInt();
            valid = insuranceCompany.isValidDate(year, month, day);
            if (!valid)
            {
                System.out.println("Invalid date! Please try again.");
            }
        }
        return new int[] {year, month, day};
    }

    public void filterByCarModelByAdmin ()
    {
        System.out.println("Please enter car model to filter: ");
        String carModel = scanner.nextLine().trim();
        double totalPayment = insuranceCompany.printPoliciesByCarModel(carModel);
        if (totalPayment == -1)
        {
            System.out.println("No policy is found for car model " + carModel + "!");
        }
        else
        {
            System.out.println("Total payment for filtered policies " + totalPayment);
        }
    }
    
    public void filterByExpiryDateByAdmin ()
    {
        System.out.println("Please enter user ID: ");
        int userID = exceptionHandlingInt();
        if (!insuranceCompany.userExists(userID))
        {
            System.out.println("User cannot be found!");
            return;
        }
        int [] date = enterExpiryDate();
        String dateText = date[0] + "/" + date[1] + "/" + date[2];
        System.out.println("Filtered policies with the date before " + dateText + ": ");
        int count = insuranceCompany.printPoliciesBeforeDate(userID, date[0], date[1], date[2]);
        if (count == 0)
        {
            System.out.println("No policy is found with the date before " + dateText + "!");
        }
    }

    public void removePolicyByAdmin ()
    {
        System.out.print("Enter userID: ");
        int userID = exceptionHandlingInt();
        System.out.print("Enter policy ID to remove: ");
        int policyID = exceptionHandlingInt();
        if (insuranceCompany.removePolicy(userID, policyID))
        {
            System.out.println("Policy with ID: " + policyID + " has been removed successfully!");
        }
        else
        {
            System.out.println("Policy ID " + policyID + " cannot be found for this user.");
        }
    }

    public void changeAdminPassword ()
    {
        System.out.print("Enter new admin password: ");
        String newPass = scanner.nextLine();
        if (insuranceCompany.changeAdminPassword(newPass))
        {
            System.out.println("Admin password changed successfully!");
        }
        else
        {
            System.out.println("Admin password cannot be changed.");
        }
    }
    
    // lab 5
    public int exceptionHandlingInt()
    {
        while (true)
        {
            try
            {
                return Integer.parseInt(scanner.nextLine().trim());
            }
            catch (NumberFormatException e)
            {
                System.out.print("Invalid input! Please enter and integer: ");
            }
        }
    }

    public Double exceptionHandlingDouble()
    {
        while (true)
        {
            try
            {
                return Double.parseDouble(scanner.nextLine().trim());
            }
            catch (NumberFormatException e)
            {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
        
    }
    
    public void reportOneUser ()
    {
        System.out.print("Enter user ID: ");
        int userID = exceptionHandlingInt();
        if (!insuranceCompany.printUserReport(userID))
        {
            System.out.println("User cannot be found as the user ID is invalid.");
        }
    }

    // lab 6

    public void saveBinaryFile ()
    {
        if (insuranceCompany.save("company.ser"))
        {
            System.out.println("Company saved successfully to binary file");
        }
        else
        {
            System.out.println("Error while saving company.");
        }
    }

    public void loadBinaryFile ()
    {
        if (insuranceCompany.load("company.ser"))
        {
            System.out.println("Company loaded successfully from binary file.");
        }
        else
        {
            System.out.println("Error while loading company.");
        }
    }

    public void saveTextFile ()
    {
        if (insuranceCompany.saveTextFile("company.txt"))
        {
            System.out.println("Company saved successfully to text file.");
        }
        else
        {
            System.out.println("Error while saving company.");
        }
    }

    public void loadTextFile ()
    {
        if (insuranceCompany.loadTextFile("company.txt"))
        {
            System.out.println("Company loaded successfully from text file.");
        }
        else
        {
            System.out.println("Error while loading company.");
        }
    }

    // Assignment 2

    public void deepCopyPolicies ()
    {
        try
        {
            insuranceCompany.printDeepCopyPolicies();
        }
        catch (CloneNotSupportedException e)
        {
            System.out.println("Error while copying policies.");
        }
    }

    public void deepCopyUsers ()
    {
        try
        {
            insuranceCompany.printDeepCopyUsers();
        }
        catch (CloneNotSupportedException e)
        {
            System.out.println("Error while copying users.");
        }
    }

    public void deepCopyCompany ()
    {
        try
        {
            insuranceCompany.printDeepCopyCompany();
        }
        catch (CloneNotSupportedException e)
        {
            System.out.println("Error while copying company.");
        }
    }
}