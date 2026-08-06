package WEEK1;

public class Car
{
    private String model;
    private CarType type;
    private int manufacturingYear;
    private double price;

    public Car (String model, CarType type, int manufacturingYear, double price)
    {
        this.model = model;
        this.type = type;
        this.manufacturingYear = manufacturingYear;
        this.price = price;
    }

    public double getPrice ()
    {
        return price;
    }

    @Override
    public String toString ()
    {
        return model + " Type: " + type + " Manufacturing Year: " + manufacturingYear + " Price: " + price + "$";
    }
}