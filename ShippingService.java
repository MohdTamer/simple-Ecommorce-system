import java.util.List;

public class ShippingService 
{
    public static void ship(List<Shippable> shippableItems)
    {
        System.out.println("ShippingService: Received " + shippableItems.size() + " items for shipping");
        
        for (Shippable item : shippableItems) 
        {
            System.out.println("- " + item.getName() + " (Weight: " + item.getWeight() + "kg)");
        }
    }    
}
