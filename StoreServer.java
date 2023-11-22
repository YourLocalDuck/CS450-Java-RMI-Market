import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StoreServer {
     public static void main(String[] args) {
        try {
            // Create a new store
            Store store = new Store();

            // Create a new customer
            Customer customer = new Customer("customer1", "password1", store);
            store.addUser(customer);

            // Create a new administrator
            Administrator admin = new Administrator("admin1", "password1", store);
            store.addUser(admin);

            // Bind the store, customer, and administrator to the registry
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Store", store);
            registry.bind("Customer", customer);
            registry.bind("Administrator", admin);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
