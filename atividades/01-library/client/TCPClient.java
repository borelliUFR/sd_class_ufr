// Executar no shell do SO
// java TCPClient [palavra] [localhost]

import java.net.*;
import java.io.*;

public class TCPClient {
    public static void main(String args[]) {
        Socket s = null; // Socket para a conexão com o servidor
        try {
            int serverPort = 7896; // Porta na qual o servidor está escutando
            // Cria um socket para conectar ao servidor na porta especificada
            s = new Socket(args[1], serverPort);    
            
            // Cria fluxos de entrada e saída para comunicação com o servidor
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            
            // Envia a palavra para o servidor
            out.writeUTF(args[0]);
            // Lê a resposta do servidor
            String data = in.readUTF();	      
            
            // Exibe a resposta recebida do servidor
            System.out.println("Received: " + data);      
        
        } catch (UnknownHostException e) {
            // Exibe uma mensagem de erro se o host não for encontrado
            System.out.println("Sock:" + e.getMessage()); 
        } catch (EOFException e) {
            // Exibe uma mensagem de erro se houver um problema de fim de arquivo
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            // Exibe uma mensagem de erro para outros problemas de entrada/saída
            System.out.println("IO:" + e.getMessage());
        } finally {
            // Garante o fechamento do socket
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    // Exibe uma mensagem de erro se houver um problema ao fechar o socket
                    System.out.println("close:" + e.getMessage());
                }
            }
        }
    }
}
