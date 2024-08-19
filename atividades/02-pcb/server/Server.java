// package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            // Cria e inicia o registro RMI na porta 1099
            LocateRegistry.createRegistry(1099);

            // Cria uma fila FIFO com capacidade para 10 elementos
            Fifo fila = new Fifo(10);
            // Cria uma instância do objeto remoto ConsumerProducerRemote com a fila criada
            ConsumerProducerRemote cp = new ConsumerProducerRemote(fila);

            // Vincula o objeto remoto ao URL RMI para que possa ser acessado pelos clientes
            Naming.rebind("rmi://localhost/ConsumerProducerRemote", cp);
            System.out.println("Serviço de fila está funcionando...");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
