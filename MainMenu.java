package WEEK1;

import java.util.Scanner;

public class MainMenu
{
    private UserInterfaceForAdmin adminUI;
    private UserInterfaceForUser userUI;
    private Scanner scanner = new Scanner(System.in);

    public MainMenu (UserInterfaceForAdmin adminUI, UserInterfaceForUser userUI)
    {
        this.adminUI = adminUI;
        this.userUI = userUI;
    }

    public void run () throws PolicyException, PolicyHolderNameException, CloneNotSupportedException
    {
        String option = "";
        while (!option.equals("3"))
        {
            displayMainMenu();
            option = scanner.nextLine().trim();
            switch (option)
            {
                case "1":
                    if (adminUI.loginAdmin())
                    {
                        adminUI.adminMenu();
                    }
                    break;
                case "2":
                    var loggedInUser = userUI.loginUser();
                    if (loggedInUser != null)
                    {
                        userUI.userMenu(loggedInUser);
                    }
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

    public void displayMainMenu ()
    {
        System.out.println("Welcom to the TrustInsure company!");
        System.out.println("1. Admin Login");
        System.out.println("2. User Login");
        System.out.println("3. Exite");
        System.out.println("Please choose an option from 1 to 3: ");
    }
}
