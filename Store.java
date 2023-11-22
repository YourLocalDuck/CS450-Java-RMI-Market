import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Store extends UnicastRemoteObject implements StoreInterface {

    protected List<Item> inventory;
    protected List<User> users;

    Store() throws RemoteException {
        this.inventory = new ArrayList<Item>();
        this.users = new ArrayList<User>();
    }

    public List<Item> getInventory() throws RemoteException {
        return inventory;
    }

    public void addUser(User user) throws RemoteException {
        this.users.add(user);
    }

    public void removeUser(User user) throws RemoteException {
        this.users.remove(user);
    }

    public User getUser(String username) throws RemoteException {
        for (User user : this.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    public List<User> getUsers() throws RemoteException {
        return this.users;
    }
}