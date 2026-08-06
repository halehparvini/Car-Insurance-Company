package WEEK1;

public class ComprehensivePolicy extends InsurancePolicy
{
    protected int driverAge;
    protected int level;

    public ComprehensivePolicy (String policyHolderName, int id, Car car, int numberOfClaims, int driverAge, int level)
    {
        super(policyHolderName, id, car, numberOfClaims);
        this.driverAge = driverAge;
        this.level = level;
    }

    @Override
    public void print ()
    {
        super.print();
        System.out.println(" Driver's Age: " + driverAge + " Level: " + level);
    }

    @Override
    public String toString ()
    {
        return super.toString() + " Driver's Age: " + driverAge + " Level: " + level;
    }
    @Override
    public double calcPayment(double flatRate)
    {
        double payment = car.getPrice()/50 + numberOfClaims * 200 + flatRate;
        if (driverAge < 30)
        {
            payment += (30 - driverAge) * 50;
        }
        return payment;
    }
}