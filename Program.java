package WEEK1;

import java.util.*;

public class Program
{
    public static void main(String[] args)
    {
        final double flatRate = 1.2;

        Car car1 = new Car("Honda CR-V", CarType.SUV, 2026, 30921);
        Car car2 = new Car("Honda Civic Hatchback", CarType.HATCH, 2024, 24950);
        Car car3 = new Car("Mercedes-Benz S-Class", CarType.LUX, 2021, 109800);
        Car car4 = new Car("Honda Accord", CarType.SED, 2026, 28395);

        ThirdPartyPolicy tpp1 = new ThirdPartyPolicy("James", 01, car1, 3, null, "3 previous claims!");
        ComprehensivePolicy cp1 = new ComprehensivePolicy("Robert", 02, car2, 0, null,  24, 3);
        ThirdPartyPolicy tpp2 = new ThirdPartyPolicy("Sara", 03, car3, 0, null, "No previous claims!");
        ComprehensivePolicy cp2 = new ComprehensivePolicy("Sue", 04, car4, 5, null, 56, 3);
        
        
        ArrayList<InsurancePolicy> policies = new ArrayList<>(); // ArrayList of Parent
        policies.add(tpp1); // adding children to the list
        policies.add(cp1);
        policies.add(tpp2);
        policies.add(cp2);

        for (InsurancePolicy ip : policies)
        {
            ip.print(); // print by using print method
        }

        for (InsurancePolicy ip : policies)
        {
            System.out.println(ip); // print by using toString() method
        }

        double totalPayment = 0;
        for (InsurancePolicy ip : policies)
        {
            totalPayment += ip.calcPayment(flatRate);
        }
        System.out.println("Total Payment: " + totalPayment + "$");
    }
}