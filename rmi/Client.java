import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) {
        try {
            // Obtém o registro RMI na porta 1099
            Registry reg = LocateRegistry.getRegistry("localhost");

            // Busca o objeto remoto "Hello" no registro
            Hello obj = (Hello) reg.lookup("Hello");

            // Invoca o método remoto e imprime a mensagem retornada
            String msg = obj.sayHello();
            System.out.println("Mensagem do servidor RMI: \"" + msg + "\"");
        } catch (Exception e) {
            System.out.println("Exceção do Cliente RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
