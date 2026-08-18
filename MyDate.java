package WEEK1;

public class MyDate
{
    private int year;
    private int month;
    private int day; 

    public MyDate (int year, int month, int day)
    {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear ()
    {
        return year;
    }

    public int getMonth ()
    {
        return month;
    }

    public int getDay ()
    {
        return day;
    }

    public void setYear (int year)
    {
        this.year = year;
    }

    public void setMonth (int month)
    {
        this.month = month;
    }

    public void setDay (int day)
    {
        this.day = day;
    }

    @Override
    public String toString ()
    {
        return month + "/" + day + "/" + year;
    }

    //lab 3

    public Boolean isExpired (MyDate expiryDate)
    {
        if (this.year > expiryDate.getYear())
        {
            return true;
        }
        if (this.year < expiryDate.getYear())
        {
            return false;
        }
        if (this.month > expiryDate.month)
        {
            return true;
        }
        if (this.month < expiryDate.getMonth())
        {
            return false;
        }
        if (this.day > expiryDate.getDay())
        {
            return true;
        }
        if(this.day < expiryDate.getDay())
        {
            return false;
        }
        else
            return false;
    }
}
