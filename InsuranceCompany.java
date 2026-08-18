package WEEK1;

import java.util.ArrayList;

public class InsuranceCompany
{
    private String name;
    private ArrayList <User> users;
    private String adminUsername; 
    private String adminPassword; 
    private int flatRate;

    public InsuranceCompany (String name, String adminUsername, String adminPassword, int flatRate)
    {
        this.name = name;
        users = new ArrayList<>();
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate; 
    }

    public String getName ()
    {
        return name;
    }

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

    public boolean addUser (User user)
    {
        if (user == null)
        {
            return false;
        }
        for (User u : users)
        {
            if (u.getUserID() == user.getUserID())
            {
                return false;
            }
        }
        users.add(user);
        return true;
    }

    public Boolean addUser (String name, int userID, Address address)
    {
        User user = new User(name, userID, address);
        return addUser(user);
    }
}
