import java.rmi.Remote;
import java.rmi.RemoteException;

public interface User extends Remote {
    String getUsername() throws RemoteException;
    boolean getAuthed() throws RemoteException;
    boolean authenticate(String password) throws RemoteException;
    boolean logout() throws RemoteException;
}
