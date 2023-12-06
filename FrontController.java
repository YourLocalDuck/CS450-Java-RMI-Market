import java.rmi.RemoteException;

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
                System.out.println("Invalid username or password");
                return null;
            }
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            return null;
        }
    }
}
