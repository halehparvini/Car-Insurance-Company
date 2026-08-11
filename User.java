package WEEK1;

import java.util.ArrayList;

public class User
{
    private String name; //the name of the account holder
    private int userID; //the user ID/number
    private Address address;
    ArrayList <InsurancePolicy> policies; //list of all the Insurance Policies this user hold

    public User (String name, int userID, Address address)
    {
        this.name = name;
        this.userID = userID;
        this.address = address;
        policies = new ArrayList<InsurancePolicy>();
    }

    public String getName ()
    {
        return name;
    }

    public int getUserID ()
    {
        return userID;
    }

    public Address getAddress ()
    {
        return address;
    }

    public void setAddress (Address address)
    {
        this.address = address;
    }
}
