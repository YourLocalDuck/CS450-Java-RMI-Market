import java.rmi.RemoteException;
import java.util.List;

public interface AdministratorInterface extends User {
    List<Item> getInventory() throws RemoteException;
    StoreInterface getStore() throws RemoteException;
    void addItem(Item item) throws RemoteException;
    int getNewID() throws RemoteException;
    void updateItem(int id, String name, float price, String description, int quantity) throws RemoteException;
    void removeItem(int id) throws RemoteException;
    List<User> getUsers() throws RemoteException;
    void addCustomer(Customer customer) throws RemoteException;
    void removeCustomer(Customer customer) throws RemoteException;
    void addAdministrator(Administrator administrator) throws RemoteException;
    void removeAdministrator(Administrator administrator) throws RemoteException;
}