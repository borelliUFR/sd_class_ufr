import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            // Cria um gerenciador de cliente RMI para se conectar ao registro RMI
            RMIClientManager clientManager = new RMIClientManager();
            
            // Obtém o registro do gerenciador
            Registry reg = clientManager.getRegistry();

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
