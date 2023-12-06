import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Administrator extends UnicastRemoteObject implements AdministratorInterface, User {
    protected String username;
    protected String password;
    protected boolean authed;
    protected StoreInterface store;

    Administrator(String username, String password, StoreInterface store) throws RemoteException {
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

    public List<Item> getInventory() throws RemoteException {
        if (this.authed) {
            return this.store.getInventory();
        } else {
            throw new RemoteException("Not authenticated");
        }
    }

    public StoreInterface getStore() throws RemoteException {
        if (this.authed) {
            return (StoreInterface) this.store;
        } else {
            throw new RemoteException("Not authenticated");
        }
    }

    public void addItem(Item item) throws RemoteException {
        if (this.authed) {
            this.getInventory().add(item);
        } else {
            throw new RemoteException("Not authenticated");
        }
    }

    public int getNewID() throws RemoteException {
        if (this.authed) {
            int id = 0;
            for (Item item : this.getInventory()) {
                if (item.getId() > id) {
                    id = item.getId();
                }
            }
            id++;
            return id;
        } else {
            throw new RemoteException("Not authenticated");
        }
    }

    public void updateItem(int id, String name, float price, String description, int quantity) throws RemoteException {
        if (this.authed) {
            for (Item item : this.getInventory()) {
                if (item.getId() == id) {
                    item.setName(name);
                    item.setPrice(price);
                    item.setDescription(description);
                    item.setQuantity(quantity);
                }
            }
        } else {
            throw new RemoteException("Not authenticated");
        }
    }

    public void removeItem(int id) throws RemoteException {
        if (this.authed) {
            Item item = null;
            for (Item i : this.getInventory()) {
                if (i.getId() == id) {
                    item = i;
                }
            }
            this.getInventory().remove(item);
        } else {
            throw new RemoteException("Not authenticated");
        }
    }

    public List<User> getUsers() throws RemoteException {
        if (this.authed) {
            return this.store.getUsers();
        } else {
            throw new RemoteException("Not authenticated");
        }
    }

    public void addCustomer(Customer customer) throws RemoteException {
        if (this.authed) {
            this.store.addUser(customer);
        } else {
            throw new RemoteException("Not authenticated");
        }
    }

    public void removeCustomer(Customer customer) throws RemoteException {
        if (this.authed) {
            this.store.removeUser(customer);
        } else {
            throw new RemoteException("Not authenticated");
        }
    }

    public void addAdministrator(Administrator administrator) throws RemoteException {
        if (this.authed) {
            this.store.addUser(administrator);
        } else {
            throw new RemoteException("Not authenticated");
        }
    }

    public void removeAdministrator(Administrator administrator) throws RemoteException {
        if (this.authed) {
            this.store.removeUser(administrator);
        } else {
            throw new RemoteException("Not authenticated");
        }
    }
}
