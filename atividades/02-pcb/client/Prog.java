package client;

import java.rmi.Naming;
import java.rmi.RemoteException;

import server.ConsumerProducerImp;

public class Prog {
    public static void main(String[] args) {
    
        try {
            // Busca o objeto remoto pelo URL RMI e faz o casting para a interface ConsumerProducerImp
            ConsumerProducerImp cp = (ConsumerProducerImp) Naming.lookup("rmi://localhost/ConsumerProducerRemote");
            
            // Cria uma thread para o produtor
            Thread producer = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Chama o método remoto produzir com o valor 1
                        cp.produzir(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            // Cria uma thread para o consumidor
            Thread consumer = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Chama o método remoto consumir
                        cp.consumir();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });

            // Inicia as threads do produtor e do consumidor
            producer.start();
            consumer.start();

            try {
                // Aguarda a conclusão das threads do produtor e do consumidor
                producer.join();
                consumer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
