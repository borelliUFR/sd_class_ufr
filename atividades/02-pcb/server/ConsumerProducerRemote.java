package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Classe que implementa a interface remota ConsumerProducerImp
public class ConsumerProducerRemote extends UnicastRemoteObject implements ConsumerProducerImp {

    // Fila FIFO utilizada para armazenar os elementos
    Fifo fila;

    // Construtor que recebe uma fila FIFO e pode lançar RemoteException
    public ConsumerProducerRemote(Fifo fila) throws RemoteException {
        this.fila = fila;
    }

    // Método para produzir elementos e inseri-los na fila
    public synchronized void produzir(int elemento) throws RemoteException {
        
        int elementoValue = 0; // Valor inicial do elemento
    
        // Loop infinito para produzir elementos continuamente
        while (true) {
    
            synchronized(this) {
                // Enquanto a fila estiver cheia, espera
                while (this.fila.cheia()) {
                    try {
                        wait(); // Libera o lock e espera ser notificado
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Insere o elemento na fila
                this.fila.inserir(elementoValue);
                System.out.println("Elemento inserido na fila: " + elementoValue);
                elementoValue++; // Incrementa o valor do próximo elemento
                // Comando sleep para simular produção mais lenta
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Notifica todas as threads que estão esperando
                notifyAll();
            }
        }
    }

    // Método para consumir elementos da fila
    public synchronized void consumir() throws RemoteException {
        int elementoValue; // Variável para armazenar o elemento removido

        // Loop infinito para consumir elementos continuamente
        while (true) {

            synchronized(this) {
                // Enquanto a fila estiver vazia, espera
                while (this.fila.vazia()) {
                    try {
                        wait(); // Libera o lock e espera ser notificado
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Remove o elemento da fila
                elementoValue = this.fila.remover();
                System.out.println("Elemento removido da fila: " + elementoValue);
                // Comando sleep para simular consumo mais lento
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Notifica todas as threads que estão esperando
                notifyAll();
            }
        }
    }
}
