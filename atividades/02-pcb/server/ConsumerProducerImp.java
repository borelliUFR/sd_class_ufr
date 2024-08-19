// package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Interface remota para o servidor ConsumerProducerImp
public interface ConsumerProducerImp extends Remote {

    // Método remoto para produzir um elemento
    // Lança RemoteException em caso de erro na comunicação RMI
    public void produzir(int elemento) throws RemoteException;

    // Método remoto para consumir um elemento
    // Lança RemoteException em caso de erro na comunicação RMI
    public void consumir() throws RemoteException;

}
