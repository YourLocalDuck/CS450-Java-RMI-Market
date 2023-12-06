import java.rmi.RemoteException;

public class CustomerFactory implements AbstractUserFactory {
    public User createUser(String username, String password, StoreInterface store) {
        try {
            return new Customer(username, password, store);
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            return null;
        }
    }
}
