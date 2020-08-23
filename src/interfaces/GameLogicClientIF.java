package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameLogicClientIF extends Remote {
    int getId() throws RemoteException;
    String getUserName() throws RemoteException;
    void assignIdentity(int id, String name, int coordinate) throws RemoteException;
    void moveForward() throws RemoteException;
}
