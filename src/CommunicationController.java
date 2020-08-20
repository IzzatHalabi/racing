import models.GameLogicServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class CommunicationController {

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        GameLogicServer server = new GameLogicServer();
        Naming.rebind("MultiplayerRacingServer", server);

        System.out.println("[SYSTEM] Server is running...");
    }
}
