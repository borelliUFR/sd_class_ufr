import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClientManager {
    private Registry registry;

    private String host = "localhost";
    private int port = 1099;

    public RMIClientManager() {
        try {
            registry = LocateRegistry.getRegistry(host, port);
        } catch (Exception e) {
            System.out.println("Erro ao obter o registro RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Registry getRegistry() {
        return registry;
    }
}