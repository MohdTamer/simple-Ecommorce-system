import java.util.Date;

interface Shippable
{
    String getName();
    double getWeight();
} 

public class Product implements Shippable
{
    String name;
    double price;
    int quantity;
    double weight;
    boolean isShippable;
    Date expirationDate;

    public Product(String name, double price, int quantity, double weight, boolean isShippable, Date expirationDate) 
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
        this.isShippable = isShippable;
        this.expirationDate = expirationDate;
    }
    
    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public double getWeight() 
    {
        return isShippable ? weight : 0; 
    }

    public void reduceQuantity(int amount) throws Exception
    {
        if (amount > quantity) 
        {
            throw new Exception("Insufficient quantity available");
        }
        else 
        {
            quantity -= amount;
        }
    }

    public boolean isExpired() 
    {
        Date currentDate = new Date();
        return expirationDate.before(currentDate);
    }

    public double getPrice() 
    {
        return price;
    }

    public int getQuantity() 
    {
        return quantity;
    }

    public boolean isShippable() 
    {
        return isShippable;
    }
}
