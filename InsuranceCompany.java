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
}
