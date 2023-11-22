import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Customer extends UnicastRemoteObject implements CustomerInterface{
    protected String username;
    protected String password;
    protected boolean authed;
    protected StoreInterface store;
    protected Cart cart;

    Customer(String username, String password, StoreInterface store) throws RemoteException {
        this.username = username;
        this.password = password;
        this.authed = false;
        this.store = store;
        this.cart = new Cart();
    }

    public String getUsername() throws RemoteException {
        return this.username;
    }

    public boolean getAuthed() throws RemoteException {
        return this.authed;
    }

    public boolean authenticate(String password) throws RemoteException {
        if (this.password.equals(password)) {
            this.authed = true;
            return true;
        } else {
            this.authed = false;
            return false;
        }
    }

    public boolean logout() throws RemoteException {
        this.authed = false;
        return true;
    }

    public Cart getCart() throws RemoteException {
        return this.cart;
    }

    public List<Item> browseItems() throws RemoteException {
        return this.store.getInventory();
    }

    public boolean addItemToCart(Item item, int qt) throws RemoteException {
        if( item.getQuantity() < qt ) 
        {
            System.out.println("Not enough items in stock");
            return false;
        }
        item.setQuantity(item.getQuantity()-qt);
        return this.cart.updateQuantity(item, qt);
    }

    public boolean removeItemFromCart(Item item, int qt) throws RemoteException {
        item.setQuantity(item.getQuantity()+qt);
        return this.cart.updateQuantity(item, -qt);
    }

    public Item getItem(int id) throws RemoteException {
        for (Item item : this.store.getInventory()) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

}
