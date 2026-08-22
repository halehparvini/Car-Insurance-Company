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
        Car car5 = new Car("A3 Sportback", CarType.HATCH, 2022,42000);
        Car car6 = new Car("E-Class", CarType.SED, 2023, 58000);

        MyDate date1 = new MyDate(2030, 8, 19);
        MyDate date2 = new MyDate(2027, 2, 4);
        MyDate date3 = new MyDate(2026, 11, 25);
        MyDate date4 = new MyDate(2036, 12, 1);
        MyDate date5 = new MyDate(2013, 1, 14);
        MyDate date6 = new MyDate(2020, 5, 29);

        ThirdPartyPolicy tpp1 = new ThirdPartyPolicy("James", 01, car1, 3, date1, "3 previous claims!");
        ComprehensivePolicy cp1 = new ComprehensivePolicy("Robert", 02, car2, 0, date2,  24, 3);
        ThirdPartyPolicy tpp2 = new ThirdPartyPolicy("Sara", 03, car3, 0, date3, "No previous claims!");
        ComprehensivePolicy cp2 = new ComprehensivePolicy("Sue", 04, car4, 5, date4, 56, 3);
        ThirdPartyPolicy tpp3 = new ThirdPartyPolicy("Taylor Morgan", 05, car5, 2, date5, "2 previous claims!");
        ComprehensivePolicy cp3 = new ComprehensivePolicy("Matthew Wilson", 06, car6, 10, date6, 60, 3);

        Address address1 = new Address(42, "Willow Street", "Greenfield", "Melbourne");
        Address address2 = new Address(9, "Willow Way", "Fairview", "Nashville");
        Address address3 = new Address(31, "Aspen Circle", "Orchard Hills", "Dallas");
        Address address4 = new Address(789, " Oak Lane", "Pinecrest", "Miami");

        User user1 = new User("Haleh", 100, address1);
        User user2 = new User("Parisa", 101, address2);
        User user3 = new User("Taraneh", 102, address3);
        User user4 = new User("Payam", 104, address4);

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

        // lab 2 codes 

        addPolicy(user1, tpp1);
        addPolicy(user1, cp1);
        addPolicy(user1, tpp2);
        addPolicy(user1, cp2);
        
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
    
        public static void addPolicy (User user, InsurancePolicy policy)
        {
            if (user.addPolicy(policy))
            {
                System.out.println("The Policy has been added successfuly!");
            }
            else
                System.out.println("The Policy can not be added as the ID already exists!");
        }
}