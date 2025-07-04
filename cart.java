import java.util.Map;
import java.util.HashMap;

class CartItem 
{
    Product product;
    int quantity;
    
    public CartItem(Product product, int quantity) 
    {
        this.product = product;
        this.quantity = quantity;
    }
}

public class cart 
{
    private Map<String, CartItem> items;
    
    public cart() 
    {
        this.items = new HashMap<>();
    }
    
    public void addItem(Product product, int quantity) throws Exception 
    {
        if (product.quantity < quantity) 
        {
            throw new Exception("Insufficient quantity available for " + product.getName());
        }
        
        String productName = product.getName();
        
        if (items.containsKey(productName)) 
        {
            CartItem existingItem = items.get(productName);
            existingItem.quantity += quantity;
        } 
        else 
        {
            items.put(productName, new CartItem(product, quantity));
        }
    }
    
    public void removeItem(Product product, int quantity) 
    {
        String productName = product.getName();
        
        if (items.containsKey(productName)) 
        {
            CartItem item = items.get(productName);
            if (item.quantity <= quantity) 
            {
                items.remove(productName);
            } 
            else 
            {
                item.quantity -= quantity;
            }
        }
    }
    
    public boolean isEmpty() 
    {
        return items.isEmpty();
    }
    
    public Map<String, CartItem> getItems() 
    {
        return items;
    }
    
    public int getTotalItems() 
    {
        return items.values().stream().mapToInt(item -> item.quantity).sum();
    }
}