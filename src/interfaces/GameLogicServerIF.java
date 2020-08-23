package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameLogicServerIF extends Remote {
    boolean authenticate(int clientId, String name, String password) throws RemoteException;
    void registerClient(GameLogicClientIF client) throws RemoteException;
    int userMove(int id) throws RemoteException;
    void exit(GameLogicClientIF client) throws RemoteException;
}
