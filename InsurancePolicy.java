package WEEK1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class InsurancePolicy implements Cloneable, Comparable <InsurancePolicy>, Serializable
{
    protected String policyHolderName;
    protected int id;
    protected Car car;
    protected int numberOfClaims;
    protected MyDate expiryDate;

    public InsurancePolicy (String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate) throws PolicyException
    {
        this.policyHolderName = policyHolderName;
        if (id < 3000000 || id > 3999999)
        {
            int generatedID = 3000000 + new Random().nextInt(1000000);
            throw new PolicyException(generatedID);
        }
        this.id = id;
        this.car =car;
        this.numberOfClaims = numberOfClaims;
        this.expiryDate = expiryDate;
    }

    public int getPolicyID()
    {
        return id;
    }
    
    public Car getCar ()
    {
        return car;
    }

    public void setPolicyHolderName (String policyHolderName)
    {
        this.policyHolderName = policyHolderName;
    }

    public static void printPolicies (ArrayList <InsurancePolicy> policies) //prints a list of policies
    {
        for (InsurancePolicy policy : policies)
        {
            policy.print();
        }
    }

    public static double calcTotalPayments (ArrayList <InsurancePolicy> policies, double flatRate) //calculates the total premium payments for a list of policies. 
    {
        double totalPayment = 0;
        for (InsurancePolicy policy : policies)
        {
            totalPayment += policy.calcPayment(flatRate);
        }
        return totalPayment;
    }

    public void carPriceRise (double risePercent)
    {
        car.priceRise(risePercent);
    }

    public static void carPriceRiseAll (ArrayList <InsurancePolicy> policies, double risePercent)
    {
        for (InsurancePolicy policy : policies)
        {
            policy.carPriceRise(risePercent);
        }
    }

    public static ArrayList <InsurancePolicy> filterByCarModel (ArrayList <InsurancePolicy> policies, String carModel)
    {
        ArrayList <InsurancePolicy> filteredPolicies = new ArrayList<>();
        for (InsurancePolicy policy : policies)
        {
            if (policy.car.getModel().contains(carModel))
            {
                filteredPolicies.add(policy);
            }
        }
        return filteredPolicies;
    }

    public void setCarModel (String model)
    {
        car.setModel(model);
    }

    public void print ()
    {
        System.out.print("Holder: " + policyHolderName + " ID: " + id + " Car Model: " + car + " Claim(s): " + numberOfClaims + " Expiry Date: " + expiryDate);
    }

    public String toString ()
    {
        return "Holder: " + policyHolderName + " ID: " + id + " Car Model: " + car + " Claim(s): " + numberOfClaims + " Expiry Date: " + expiryDate;
    }

    public abstract double calcPayment (double flatRate);

    // lab 3 

    public static ArrayList <InsurancePolicy> filterByExpiryDate (ArrayList <InsurancePolicy> policies, MyDate date)
    {
        ArrayList <InsurancePolicy> filteredExpiredPolicies = new ArrayList<>();
        for (InsurancePolicy policy : policies)
        {
            if (policy.expiryDate.isExpired(date))
            {
                filteredExpiredPolicies.add(policy);
            }
        }
        return filteredExpiredPolicies;
    }

    // lab 4
    public InsurancePolicy (InsurancePolicy ip)
    {
        policyHolderName = ip.policyHolderName;
        id = ip.id;
        car = new Car(ip.car);
        numberOfClaims = ip.numberOfClaims;
        expiryDate = new MyDate(ip.expiryDate);
    }

    public InsurancePolicy clone () throws CloneNotSupportedException
    {
        InsurancePolicy policy = (InsurancePolicy)super.clone();
        policy.car = car.clone();
        policy.expiryDate = expiryDate.clone();
        return policy;
    }

    public static ArrayList <InsurancePolicy> shallowCopy (ArrayList <InsurancePolicy> policies)
    {
        ArrayList <InsurancePolicy> shallowCopy = new ArrayList<>();
        for (InsurancePolicy policy : policies)
        {
            shallowCopy.add(policy);
        }
        return shallowCopy;
    }

    public static ArrayList <InsurancePolicy> deepCopy (ArrayList <InsurancePolicy> policies) throws CloneNotSupportedException
    {
        ArrayList <InsurancePolicy> deepCopy = new ArrayList<>();
        for (InsurancePolicy policy : policies)
        {
            deepCopy.add(policy.clone());
        }
        return deepCopy;
    }

    @Override 
    public int compareTo (InsurancePolicy other)
    {
        return expiryDate.compareTo(other.expiryDate);
    }

    // lab 5
    public static void printPolicies (HashMap <Integer, InsurancePolicy> policies)
    {
        for (InsurancePolicy policy : policies.values())
        {
            policy.print();
        }
    }

    public static HashMap <Integer, InsurancePolicy> filterByCarModel (HashMap <Integer, InsurancePolicy> policies, String carModel)
    {
        HashMap <Integer, InsurancePolicy> filteredPolicies = new HashMap<>();
        for (InsurancePolicy policy : policies.values())
        {
            if (policy.car.getModel().contains(carModel))
            {
                filteredPolicies.put(policy.getPolicyID(), policy);
            }
        }
        return filteredPolicies;
    }

    public static HashMap <Integer, InsurancePolicy> filterByExpiryDate (HashMap <Integer, InsurancePolicy> policies, MyDate date)
    {
        HashMap <Integer, InsurancePolicy> filteredExpiredPolicies = new HashMap<>();
        for (InsurancePolicy policy : policies.values())
        {
            if (policy.expiryDate.isExpired(date))
            {
                filteredExpiredPolicies.put(policy.getPolicyID(), policy);
            }
        }
        return filteredExpiredPolicies;
    }

    public static double calcTotalPayments (HashMap <Integer, InsurancePolicy> policies, double flatRate)
    {
        double totalPayment = 0;
        for (InsurancePolicy policy : policies.values())
        {
            totalPayment += policy.calcPayment(flatRate);
        }
        return totalPayment;
    }

    public static void carPriceRiseAll (HashMap <Integer, InsurancePolicy> policies, double risePercent)
    {
        for (InsurancePolicy policy : policies.values())
        {
            policy.carPriceRise(risePercent);
        }
    }

    public static ArrayList <InsurancePolicy> shallowCopy (HashMap <Integer, InsurancePolicy> policies)
    {
        ArrayList <InsurancePolicy> shallowCopy = new ArrayList<>();
        for (InsurancePolicy policy : policies.values())
        {
            shallowCopy.add(policy);
        }
        return shallowCopy;
    }

    public static ArrayList <InsurancePolicy> deepCopy (HashMap <Integer, InsurancePolicy> policies) throws CloneNotSupportedException
    {
        ArrayList <InsurancePolicy> deepCopy = new ArrayList<>();
        for (InsurancePolicy policy : policies.values())
        {
            deepCopy.add(policy.clone());
        }
        return deepCopy;
    }

    public static HashMap <Integer, InsurancePolicy> shallowCopyHashMap (HashMap <Integer, InsurancePolicy> policies)
    {
        HashMap <Integer, InsurancePolicy> shallowCopy = new HashMap<>();
        for (InsurancePolicy policy : policies.values())
        {
            shallowCopy.put(policy.getPolicyID(), policy);
        }
        return shallowCopy;
    }

    public static HashMap <Integer, InsurancePolicy> deepCopyHashMap (HashMap <Integer, InsurancePolicy> policies) throws CloneNotSupportedException
    {
        HashMap <Integer, InsurancePolicy> deepCopy = new HashMap<>();
        for (InsurancePolicy policy : policies.values())
        {
            deepCopy.put(policy.getPolicyID(), policy.clone());
        }
        return deepCopy;
    }

    // lab 6
    public static HashMap <Integer, InsurancePolicy> load (String fileName)
    {
        try
        {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream inputStream = new ObjectInputStream(file);
            Object obj = inputStream.readObject();
            HashMap <Integer, InsurancePolicy> policies = (HashMap <Integer, InsurancePolicy>) obj;
            inputStream.close();
            file.close();
            return policies;
        }
        catch (IOException e)
        {
            System.out.println("Error while reading the file");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class not found");
        }
        return null;
    }

    public static Boolean save (HashMap <Integer, InsurancePolicy> policies, String fileName)
    {
        try
        {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(file);
            outputStream.writeObject(policies);
            outputStream.close();
            file.close();
            return true;
        }
        catch (IOException e)
        {
            System.out.println("Error while writing to the file");
        }
        return false;
    }

    public String toDelimitedString ()
    {
        return policyHolderName + "," + id + "," + car.toDelimitedString() + "," + numberOfClaims + "," + expiryDate.toDelimitedString();
    }

    public static HashMap <Integer, InsurancePolicy> loadTextFile (String fileName)
    {
        HashMap <Integer, InsurancePolicy> policies = new HashMap<>();
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();
            while (line != null)
            {
                line = line.trim();
                String [] field = line.split(",");
                
                if (field[0].equals("TPP"))
                {
                    String policyHolderName = field[1];
                    int id = Integer.parseInt(field[2]);
                    String model = field[3];
                    CarType type = CarType.valueOf(field[4]);
                    int manufacturingYear = Integer.parseInt(field[5]);
                    double price = Double.parseDouble(field[6]);
                    Car car = new Car(model, type, manufacturingYear, price);
                    int numberOfClaims = Integer.parseInt(field[7]);
                    int year = Integer.parseInt(field[8]);
                    int month = Integer.parseInt(field[9]);
                    int day = Integer.parseInt(field[10]);
                    MyDate expiryDate = new MyDate(year, month, day);
                    String comments = field[11];
                    ThirdPartyPolicy policy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
                    policies.put(id, policy);
                }
                else if (field[0].equals("CP"))
                {
                    String policyHolderName = field[1];
                    int id = Integer.parseInt(field[2]);
                    String model = field[3];
                    CarType type = CarType.valueOf(field[4]);
                    int manufacturingYear = Integer.parseInt(field[5]);
                    double price = Double.parseDouble(field[6]);
                    Car car = new Car(model, type, manufacturingYear, price);
                    int numberOfClaims = Integer.parseInt(field[7]);
                    int year = Integer.parseInt(field[8]);
                    int month = Integer.parseInt(field[9]);
                    int day = Integer.parseInt(field[10]);
                    MyDate expirDate = new MyDate(year, month, day);
                    int driverAge = Integer.parseInt(field[11]);
                    int level = Integer.parseInt(field[12]);
                    ComprehensivePolicy policy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expirDate, driverAge, level);
                    policies.put(id, policy);
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            return policies;
        }
        catch (IOException e)
        {
            System.out.println("Error while reading the text file.");
        }
        catch (PolicyException e)
        {
            System.out.println("Error while creating policy.");
        }
        return null;
    }

    public static Boolean saveTextFile (HashMap <Integer, InsurancePolicy> policies, String fileName)
    {
        try
        {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            for (InsurancePolicy policy : policies.values())
            {
                bufferedWriter.write(policy.toDelimitedString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            return true;
        }
        catch (IOException e)
        {
            System.out.println("Error while writing to the text file.");
            return false;
        }
    }
}