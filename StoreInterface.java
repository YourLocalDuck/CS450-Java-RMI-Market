import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface StoreInterface extends Remote {
    List<Item> getInventory() throws RemoteException;
    void addUser(User user) throws RemoteException;
    void removeUser(User user) throws RemoteException;
    public User getUser(String username) throws RemoteException;
    List<User> getUsers() throws RemoteException;
}