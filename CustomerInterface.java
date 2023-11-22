import java.rmi.RemoteException;
import java.util.List;

public interface CustomerInterface extends User {
    Cart getCart() throws RemoteException;
    List<Item> browseItems() throws RemoteException;
    boolean addItemToCart(Item item, int qt) throws RemoteException;
    boolean removeItemFromCart(Item item, int qt) throws RemoteException;
    Item getItem(int id) throws RemoteException;
}