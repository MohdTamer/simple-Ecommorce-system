import java.util.ArrayList;
import java.util.List;

public class Checkout 
{
    public void checkout(customer cust, cart Cart) throws Exception
    {
        // check if cart is empty
        if (Cart.isEmpty()) 
        {
            throw new Exception("Cart is empty");
        }

        // check expiration of products
        checkExpiration(Cart);

        // calc total price
        double subtotal = calculateSubtotal(Cart);
        double total = subtotal + 30.0; // 30 is the shipping cost

        // check if customer has enough balance
        verifyBalance(cust, total);

        // process payment
        processPayment(cust, total);

        // print receipt
        printShippingNotice(Cart);
        printReceipt(Cart, subtotal, total);
    }    

    public void checkExpiration(cart Cart) throws Exception
    {
        for (CartItem item : Cart.getItems().values()) 
        {
            if (item.product.isExpired()) 
            {
                throw new Exception("The following product has expired: " + item.product.getName());
            }
        }
    }

    public boolean verifyBalance(customer cust, double totalprice) throws Exception
    {
        if (cust.checkBalance(totalprice)) 
        {
            return true;
        } 
        else 
        {
            throw new Exception("Insufficient balance");
        }
    }

    public double calculateSubtotal(cart Cart) // changed to subtotal instead of total since its needed as well
    {
        double total = 0.0;
        for (CartItem item : Cart.getItems().values()) 
        {
            total += item.product.getPrice() * item.quantity;
        }
        // total += 30.0; // 30 aka shipping cost
        return total;
    }

    public void processPayment(customer cust, double totalprice) throws Exception
    {
        cust.deductBalance(totalprice);
    }

    public void printShippingNotice(cart Cart) 
    {
        System.out.println();
        System.out.println("** Shipment notice **");
        double totalWeight = 0.0;
        
        for (CartItem item : Cart.getItems().values()) 
        {
            if (item.product.isShippable) 
            {
                double itemWeight = item.product.getWeight() * item.quantity;
                totalWeight += itemWeight;
                System.out.println(item.quantity + "x " + item.product.getName() + " " + (itemWeight) + "g");
            }
        }
        
        System.out.println("Total package weight " + totalWeight + "kg");
        System.out.println("");
    }
    
    public void printReceipt(cart Cart, double subtotal, double total) 
    {
        System.out.println("** Checkout receipt **");
        
        for (CartItem item : Cart.getItems().values()) 
        {
            double itemTotal = item.product.getPrice() * item.quantity;
            System.out.println(item.quantity + "x " + item.product.getName() + " " + (int)itemTotal);
        }
        
        System.out.println("----------------------");
        System.out.println("Subtotal " + (int)subtotal);
        System.out.println("Shipping " + 30);
        System.out.println("Amount " + (int)total);
    }

    public void handleShipping(cart Cart) 
    {
        List<Shippable> shippableItems = new ArrayList<>();

        for (CartItem item : Cart.getItems().values()) 
        {
            if (item.product.isShippable) 
            {
                for (int i = 0; i < item.quantity; i++) 
                {
                    shippableItems.add(item.product);
                }
            }
        }
        
        if (!shippableItems.isEmpty()) 
        {
            ShippingService.ship(shippableItems);
        }
    }
}
