package models;

import interfaces.GameLogicClientIF;
import interfaces.GameLogicServerIF;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class GameLogicServer extends UnicastRemoteObject implements GameLogicServerIF {

    private static final long serialVersionUID = 1L;
    public ArrayList<GameLogicClientIF> clients;
    private FakeDatabase database = new FakeDatabase();

    public GameLogicServer() throws RemoteException {
        clients = new ArrayList<>();
    }

    @Override
    public boolean authenticate(int clientId, String name, String password) throws RemoteException {
        if (! database.authenticate(name, password)) return false;

        GameLogicClientIF found = null;
        for (GameLogicClientIF client : clients) {
            if (client.getId() == clientId) found = client;
        }

        User user = database.findUserByName(name);
        found.assignIdentity(user.id, user.name, user.coordinate);
        System.out.println(name + " has logged into the system.");

        return true;
    }

    @Override
    public void registerClient(GameLogicClientIF client) {
        this.clients.add(client);
    }

    @Override
    public int userMove(int id) {
        User user = database.updateUserCoordinate(id, 1);
        System.out.println(user.name + " has moved to coordinate " + user.coordinate);

        return user.coordinate;
    }

    @Override
    public void exit(GameLogicClientIF client) throws RemoteException {
        System.out.println(client.getUserName() + " has left the system.");
        clients.remove(client);
    }
}
