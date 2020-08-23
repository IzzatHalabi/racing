import models.GameLogicServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class CommunicationController {

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        System.out.println("hello");
        GameLogicServer server = new GameLogicServer();
        System.out.println("test");
        Naming.rebind("MultiplayerRacingServer", server);

        System.out.println("[System] Server is running...");
    }
}
