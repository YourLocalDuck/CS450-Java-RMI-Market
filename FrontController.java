import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class FrontController {
    private StoreInterface store;

    public FrontController(StoreInterface store) {
        this.store = store;
    }

    public User login(String username, String password) {
        User user;
        try {
            user = store.getUser(username);
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            return null;
        }
        try {
            if (user.authenticate(password)) {
                return user;
            } else {
                
                return null;
            }
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    public boolean register(String username, String password, AbstractUserFactory userFactory) {
        User user;
        try {
            user = userFactory.createUser(username, password, this.store);
            this.store.addUser(user);
            return true;
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            return false;
        }
    }

    public List<Item> adminGetInventory(AdministratorInterface admin) {
        try {
            return admin.getInventory();
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    public void adminAddItem(AdministratorInterface admin, String name, String type, String description, float price, int qt) {
        try {
            admin.addItem(new Item(admin.getNewID(), name, type, description, price, qt));
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void adminUpdateItem(AdministratorInterface admin, int id, String name, float price, String description, int quantity) {
        try {
            admin.updateItem(id, name, price, description, quantity);
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void adminRemoveItem(AdministratorInterface admin, int id) {
        try {
            admin.removeItem(id);
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public List<User> adminGetUsers(AdministratorInterface admin) {
        try {
            return admin.getUsers();
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    public void adminAddCustomer(AdministratorInterface admin, String username, String password) {
        try {
            admin.addCustomer(new Customer(username, password, this.store));
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void adminRemoveCustomer(AdministratorInterface admin, String username) {
        try {
            admin.removeCustomer((Customer) admin.getStore().getUser(username));
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
    public void adminAddAdministrator(AdministratorInterface admin, String username, String password)
    {
        try {
            admin.addAdministrator(new Administrator(username, password, this.store));
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void adminRemoveAdministrator(AdministratorInterface admin, String username)
    {
        try {
            admin.removeAdministrator((Administrator) admin.getStore().getUser(username));
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public List<Item> customerGetInventory(CustomerInterface customer) {
        try {
            return customer.browseItems();
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    public Map<Item, Integer> customerGetCart(CustomerInterface customer) {
        try {
            return customer.getCart().getContents();
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    public void customerAddToCart(CustomerInterface customer, int id, int quantity) {
        try {
            customer.addItemToCart(customer.getItem(id), quantity);
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void customerRemoveFromCart(CustomerInterface customer, int id, int quantity) {
        try {
            customer.removeItemFromCart(customer.getItem(id), quantity);
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void customerEmptyCart(CustomerInterface customer) {
        try {
            customer.getCart().emptyCart();
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void customerCheckout(CustomerInterface customer) {
        try {
            customer.getCart().checkout();
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void logout(User user) {
        try {
            user.logout();
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }  
}
