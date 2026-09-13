package WEEK1;

public class Address implements Cloneable
{
    private int streetNum;
    private String street;
    private String suburb;
    private String city;

    public Address (int streetNum, String street, String suburb, String city)
    {
        this.streetNum = streetNum;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
    }
    
    public int getStreetNum ()
    {
        return streetNum;
    }

    public String getStreet ()
    {
        return street;
    }

    public String getSuburb ()
    {
        return suburb;
    }

    public String getCity ()
    {
        return city;
    }

    public void setStreetNum (int streetNum)
    {
        this.streetNum = streetNum;
    }

    public void setStreet (String street)
    {
        this.street = street;
    }

    public void setSuburb (String suburb)
    {
        this.suburb = suburb;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    @Override
    public String toString ()
    {
        return streetNum + " " + street + ", " + suburb + ", " + city;
    }

    // lab 4
    public Address (Address address)
    {
        streetNum = address.streetNum;
        street = address.street;
        suburb = address.suburb;
        city = address.city;
    }

    public Address clone () throws CloneNotSupportedException
    {
        return (Address)super.clone();
    }
}