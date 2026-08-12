package WEEK1;

import java.util.*;

public class Program
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        final double flatRate = 1.2;
        
        Car car1 = new Car("Honda CR-V", CarType.SUV, 2026, 30921);
        Car car2 = new Car("Honda Civic Hatchback", CarType.HATCH, 2024, 24950);
        Car car3 = new Car("Mercedes-Benz S-Class", CarType.LUX, 2021, 109800);
        Car car4 = new Car("Honda Accord", CarType.SED, 2026, 28395);

        MyDate date1 = new MyDate(2030, 8, 19);
        MyDate date2 = new MyDate(2027, 2, 4);
        MyDate date3 = new MyDate(2026, 11, 25);
        MyDate date4 = new MyDate(2036, 12, 1);

        ThirdPartyPolicy tpp1 = new ThirdPartyPolicy("James", 01, car1, 3, date1, "3 previous claims!");
        ComprehensivePolicy cp1 = new ComprehensivePolicy("Robert", 02, car2, 0, date2,  24, 3);
        ThirdPartyPolicy tpp2 = new ThirdPartyPolicy("Sara", 03, car3, 0, date3, "No previous claims!");
        ComprehensivePolicy cp2 = new ComprehensivePolicy("Sue", 04, car4, 5, date4, 56, 3);

        Address address1 = new Address(42, "Willow Street", "Greenfield", "Melbourne");

        User user1 = new User("Haleh", 100, address1);
        
        user1.addPolicy(tpp1);
        user1.addPolicy(cp1);
        user1.addPolicy(tpp2);
        user1.addPolicy(cp2);

        ArrayList <InsurancePolicy> policies = new ArrayList<>(); // ArrayList of Parent
        policies.add(tpp1); // adding children to the list
        policies.add(cp1);
        policies.add(tpp2);
        policies.add(cp2);

        for (InsurancePolicy ip : policies)
        {
            System.out.println(ip); // print by using toString() method
        }

        InsurancePolicy.printPolicies(policies);
        System.out.println("Total Payment: " + InsurancePolicy.calcTotalPayments(policies, flatRate) + "$");
        
        user1.print();

        System.out.println(user1.toString());

        InsurancePolicy policy = user1.findPolicy(6);
        if (policy == null)
        {
            System.out.println("Policy has not been found!");
        }
        policy = user1.findPolicy(4);

        policy.print();
        policy.carPriceRise(0.1);
        policy.print();

        policy.setPolicyHolderName("Robert");

        policy.setCarModel ("Toyota Camry 2018");
        
        user1.setCity("Wollongong");
        
        System.out.println("Enter Street Number: ");
        int streetNum = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Street Name: ");
        String streetName = scanner.nextLine();
        System.out.println("Enter Suburb: ");
        String suburb = scanner.nextLine();
        System.out.println("Enter City: ");
        String city = scanner.nextLine();
        Address newAddress = new Address(streetNum, streetName, suburb, city);
        user1.setAddress(newAddress);
        user1.print();

        System.out.println("Total Premium Payments: " + user1.calcTotalPremiums(flatRate) + "$");

        user1.carPriceRiseAll(0.1);

        System.out.println("Total Premium Payments: " + user1.calcTotalPremiums(flatRate) + "$");

        System.out.println("Enter a Car Model: ");
        String userCarModel = scanner.nextLine();
        ArrayList <InsurancePolicy> policyList = user1.filterByCarModel(userCarModel);
        System.out.println("Policies matching '" + userCarModel + "' :");
        InsurancePolicy.printPolicies(policyList);
    }
}