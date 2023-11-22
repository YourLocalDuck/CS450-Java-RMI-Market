import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Administrator extends UnicastRemoteObject implements AdministratorInterface, User{
    protected String username;
    protected String password;
    protected boolean authed;
    protected StoreInterface store;

    Administrator(String username, String password, StoreInterface store) throws RemoteException
    {
        this.username = username;
        this.password = password;
        this.authed = false;
        this.store = store;
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

    public List<Item> getInventory() throws RemoteException
    {
        return this.store.getInventory();
    }

    public StoreInterface getStore() throws RemoteException
    {
        return (StoreInterface) this.store;
    }
    public void addItem(Item item) throws RemoteException
    {
        this.getInventory().add(item);
    }
    public int getNewID() throws RemoteException
    {
        int id = 0;
        for (Item item : this.getInventory())
        {
            if (item.getId() > id)
            {
                id = item.getId();
            }
        }
        id++;
        return id;
    }
    public void updateItem(int id, String name, float price, String description, int quantity) throws RemoteException
    {
        for (Item item : this.getInventory())
        {
            if (item.getId() == id)
            {
                item.setName(name);
                item.setPrice(price);
                item.setDescription(description);
                item.setQuantity(quantity);
            }
        }
    }
    public void removeItem(int id) throws RemoteException
    {
        Item item = null;
        for (Item i : this.getInventory())
        {
            if (i.getId() == id)
            {
                item = i;
            }
        }
        this.getInventory().remove(item);
    }
    public List<User> getUsers() throws RemoteException
    {
        return this.store.getUsers();
    }

    public void addCustomer(Customer customer) throws RemoteException
    {
        this.store.addUser(customer);
    }
    public void removeCustomer(Customer customer) throws RemoteException
    {
        this.store.removeUser(customer);
    }
    public void addAdministrator(Administrator administrator) throws RemoteException
    {
        this.store.addUser(administrator);
    }
    public void removeAdministrator(Administrator administrator) throws RemoteException
    {
        this.store.removeUser(administrator);
    }
}
