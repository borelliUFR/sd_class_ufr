import java.net.*;
import java.io.*;

public class TCPServer {
    public static void main(String args[]) {
        try {
            int serverPort = 7896; // Porta na qual o servidor vai escutar
            ServerSocket listenSocket = new ServerSocket(serverPort); // Cria um ServerSocket para escutar conexões
            System.out.println("Server running!"); // Mensagem indicando que o servidor está em execução

            while (true) {
                // Aceita uma conexão de cliente
                Socket clientSocket = listenSocket.accept();
                // Cria uma nova instância da classe Connection para gerenciar a comunicação com o cliente
                Connection c = new Connection(clientSocket);
            }

        } catch (IOException e) {
            // Exibe uma mensagem de erro se houver um problema ao iniciar o ServerSocket
            System.out.println("Listen :" + e.getMessage());
        }
    }
}

class Connection extends Thread {
    DataInputStream in; // Fluxo de entrada de dados do cliente
    DataOutputStream out; // Fluxo de saída de dados para o cliente
    Socket clientSocket; // Socket do cliente

    // Construtor da classe Connection que inicializa os fluxos de entrada e saída
    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream()); // Inicializa o fluxo de entrada
            out = new DataOutputStream(clientSocket.getOutputStream()); // Inicializa o fluxo de saída
            this.start(); // Inicia a thread para gerenciar a comunicação
        } catch (IOException e) {
            // Exibe uma mensagem de erro se houver um problema ao criar os fluxos de entrada e saída
            System.out.println("Connection:" + e.getMessage());
        }
    }

    // Método que é executado quando a thread é iniciada
    public void run() {
        try {
            // Lê a string enviada pelo cliente
            String data = in.readUTF();

            // Cria uma instância da classe Sinonimo para consultar sinônimos
            Sinonimo sin = new Sinonimo();
            // Consulta os sinônimos da palavra recebida
            data = sin.consulta(data);

            // Envia os sinônimos encontrados de volta para o cliente
            out.writeUTF(data);
        } catch (EOFException e) {
            // Exibe uma mensagem de erro se o cliente fechar a conexão inesperadamente
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            // Exibe uma mensagem de erro para outros problemas de entrada/saída
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {
                // Fecha o socket do cliente
                clientSocket.close();
            } catch (IOException e) {
                // Exibe uma mensagem de erro se houver um problema ao fechar o socket
                System.out.println("IO:" + e.getMessage());
            }
        }
    }
}
