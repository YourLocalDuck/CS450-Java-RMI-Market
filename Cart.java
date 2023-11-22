import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {
    protected Map<Item, Integer> contents;
    
    public Cart() {
        this.contents = new HashMap<>();
    }
    
    public Map<Item, Integer> getContents() {
        return contents;
    }
    
    public void addItem(Item item) {
        int quantity = contents.getOrDefault(item, 0);
        contents.put(item, quantity + 1);
    }
    
    public void removeItem(Item item) {
        int quantity = contents.getOrDefault(item, 0);
        if (quantity > 1) {
            contents.put(item, quantity - 1);
        } else {
            contents.remove(item);
        }
    }
    
    public void emptyCart() {
        contents.clear();
    }
    
    public float getTotal() {
        float total = 0;
        for (Map.Entry<Item, Integer> entry : contents.entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();
            total += item.getPrice() * quantity;
        }
        return total;
    }
    
    public void checkout() {
        this.emptyCart();
    }

    public boolean updateQuantity(Item item, int quantity) {
        if (quantity > 0) {
            contents.put(item, quantity);
            return true;
        } else {
            contents.remove(item);
            return true;
        }
    }
}
