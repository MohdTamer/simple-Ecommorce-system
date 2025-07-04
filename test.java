import java.util.Date;

public class test 
{
    public static void main(String[] args) 
    {
        // create products
        Product cheese = new Product("Cheese", 50.0, 2, 250, true, new Date(System.currentTimeMillis() + 100000));
        Product tv = new Product("TV", 5000.0, 1, 10000, true, new Date(System.currentTimeMillis() + 100000));
        Product scratchCard = new Product("Scratch Card", 30.0, 5, 50, false, new Date(System.currentTimeMillis() + 100000));
        Product Biscuits = new Product("Biscuits", 20.0, 10, 200, true, new Date(System.currentTimeMillis() + 100000));

        // create cuatomer
        customer cust = new customer(10000.0);

        // create cart
        cart Cart = new cart();
        try
        {
            Cart.addItem(cheese, 2);
            Cart.addItem(tv, 1);
            Cart.addItem(scratchCard, 3);
            Cart.addItem(Biscuits, 5);
        }
        catch (Exception e) 
        {
            System.out.println("Error adding items to cart: " + e.getMessage());
        }

        Checkout checkout = new Checkout();
        try
        {
            checkout.checkout(cust, Cart);
        }
        catch (Exception e) 
        {
            System.out.println("Error during checkout: " + e.getMessage());
        }

        System.out.println();
        System.out.println("Customer remaining balance: " + cust.getBalance());
        System.out.println();
    }
}
