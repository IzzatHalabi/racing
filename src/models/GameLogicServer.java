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
    public int participantSize() {
        return clients.size();
    }

    @Override
    public ArrayList<String> participantNames() throws RemoteException {

        ArrayList<String> names = new ArrayList<>();
        User user;

        for (GameLogicClientIF client : clients) {
            user = database.findUser(client.getUserId());
            names.add(user.name);
        }

        return names;
    }

    @Override
    public ArrayList<Integer> participantCoordinate() throws RemoteException {

        ArrayList<Integer> coordinate = new ArrayList<>();
        User user;

        for (GameLogicClientIF client : clients) {
            user = database.findUser(client.getUserId());
            coordinate.add(user.coordinate);
        }

        return coordinate;
    }

    @Override
    public int updateUser(int id, int distance) {
        return database.updateUserCoordinate(id, distance);
    }

    @Override
    public void exit(GameLogicClientIF client) throws RemoteException {
        System.out.println(client.getUserName() + " has left the system.");
        clients.remove(client);
    }
}
