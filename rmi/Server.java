import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements Hello {

    public Server() throws RemoteException {
        super();
    }

    @Override
    public String sayHello() throws RemoteException {
        System.out.println("Invocação do método Hello com sucesso!!!");
        return "Hello, world by RMI Server!";
    }

    public static void main(String[] args) {
        try {
            Server obj = new Server();
            LocateRegistry.createRegistry(1099);
            Registry reg = LocateRegistry.getRegistry();
            reg.rebind("Hello", obj);
            System.out.println("Servidor RMI ready!!!");
        } catch (RemoteException e) {
            System.out.println("HelloServer exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
