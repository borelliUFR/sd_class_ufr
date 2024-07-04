// Executar no shell do SO
// java TTCPServer Ola localhost

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
	public static void main (String args[]) {
	    Scanner sc = new Scanner(System.in);
        Socket s = null;
        try {
            int serverPort = 7896;

            // Mudar o host para o endereço do Servidor
            s = new Socket("localhost", serverPort);
            
            DataInputStream in = new DataInputStream( s.getInputStream());
            DataOutputStream out = new DataOutputStream( s.getOutputStream());
            
            System.out.println("Digite uma frase");
            String data = sc.nextLine();
            out.writeUTF(data);
            data = in.readUTF();	      
            
            System.out.println("Received: "+ data) ;      
        
        } catch (UnknownHostException e){
            System.out.println("Sock:"+e.getMessage()); 
        } catch (EOFException e) {
            System.out.println("EOF:"+e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:"+e.getMessage());
        } finally {
            if(s!=null) 
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:"+e.getMessage());
                }
            sc.close();
        }
  	}
}
