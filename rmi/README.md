# Exemplo de RMI (Remote Method Invocation)

Este exemplo demonstra como configurar e executar um sistema RMI simples em Java, separando a lógica de conexão do RMI Registry e a lógica de chamadas de métodos remotos.

## Componentes Principais

1. **RMI Registry**: Serviço que localiza objetos remotos.
2. **RMI Server**: Implementa a interface remota e registra o objeto remoto no RMI Registry.
3. **RMI Client Manager**: Obtém o registro RMI.
4. **RMI Client**: Usa o `RMIClientManager` para localizar o objeto remoto e invocar métodos.

## Fluxo de Execução

### Servidor
1. Inicia o RMI Registry.
2. Cria e registra o objeto remoto `Hello` no RMI Registry.

### Cliente
1. Usa `RMIClientManager` para obter o RMI Registry.
2. Localiza o objeto remoto `Hello` no registro.
3. Invoca o método `sayHello()` no objeto remoto `Hello`.

## Diagrama

Servidor
--------------------------------------------------------------------------
                                     
                                     
1. Cria o objeto Server              
Server server = new Server();        
                                     
2. Inicia o RMI Registry             
LocateRegistry.createRegistry(1099); 
                                     
3. Obtém o registry                  
Registry reg = LocateRegistry.getRegistry();
                                     
4. Registra o objeto remoto          
reg.rebind("Hello", server);         
                                     
                                     
                                     
Cliente                              
--------------------------------------------------------------------------
                                     
5. Cria o gerenciador de cliente     
RMIClientManager clientManager = new RMIClientManager("localhost", 1099);
                                     
6. Obtém o registry do gerenciador   
Registry reg = clientManager.getRegistry();
                                     
7. Busca o objeto remoto "Hello"     
Hello obj = (Hello) reg.lookup("Hello");
                                     
8. Invoca o método remoto            
String msg = obj.sayHello();         
                                     
9. Executa o método no servidor      
String sayHello() {
    System.out.println("Invocação do método Hello com sucesso!!!");
    return "Hello, world by RMI Server!";
}
                                     
10. Retorna o resultado              
msg = "Hello, world by RMI Server!";
                                     
11. Imprime a mensagem no cliente    
System.out.println("Mensagem do servidor RMI: \"" + msg + "\"");
