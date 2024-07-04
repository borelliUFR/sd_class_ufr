# Aula de Sistemas Distribuídos (2024-1)

Explicação para a execução dos algoritmos Java RMI

Cliente                              Servidor
--------------------------------------------|-------------------------------------
                                            |
                                            |
1. Cria o objeto Server                     |
Server server = new Server();               |
                                            |
2. Inicia o RMI Registry                    |
LocateRegistry.createRegistry(1099);        |
                                            |
3. Obtém o registry                         |
Registry reg = LocateRegistry.getRegistry();|
                                            |
4. Registra o objeto remoto                 |
reg.rebind("Hello", server);                |
                                            |
                                            | 
                                            |
Cliente                                     |
--------------------------------------------|-------------------------------------
                                            |
5. Cria o gerenciador de cliente            |
RMIClientManager clientManager = new        |
    RMIClientManager("localhost", 1099);    |
                                            |
6. Obtém o registry do gerenciador          |
Registry reg = clientManager.getRegistry(); |
                                            |
7. Busca o objeto remoto "Hello"            |
Hello obj = (Hello) reg.lookup("Hello");    |
                                            |
8. Invoca o método remoto                   |
String msg = obj.sayHello();                |
                                            |
9. Executa o método no servidor             |
String sayHello() {                         |
    System.out.println("Invocação do método"|
            + "Hello com sucesso!!!");      |
    return "Hello, world by RMI Server!";   |
}                                           |
                                            |
10. Retorna o resultado                     |
msg = "Hello, world by RMI Server!";        |
                                            |
11. Imprime a mensagem no cliente           |
System.out.println("Mensagem do servidor RMI: \"" + msg + "\"");

