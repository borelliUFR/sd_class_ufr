# Projeto TCP Cliente-Servidor em Java

Este projeto demonstra uma comunicação básica entre um cliente e um servidor usando sockets TCP em Java. O servidor é responsável por processar as requisições dos clientes e fornecer respostas baseadas em uma consulta de sinônimos.

## Estrutura do Projeto

O projeto é composto pelas seguintes classes:

### 1. `Sinonimo`

**Descrição:**
Classe que fornece um método para consultar sinônimos de uma palavra específica. Utiliza um array de strings para armazenar grupos de sinônimos.

**Atributos:**
- `String dic[]`: Array que contém grupos de sinônimos.

**Métodos:**
- `Sinonimo()`: Construtor que inicializa o array de sinônimos chamando o método `preenche()`.
- `private void preenche()`: Preenche o array com grupos de sinônimos.
- `public String consulta(String palavra)`: Consulta sinônimos para a palavra fornecida e retorna uma string com os sinônimos encontrados. Caso a palavra não seja encontrada, retorna "Palavra nao encontrada".

### 2. `TCPServer`

**Descrição:**
Classe principal que cria um servidor TCP para escutar e aceitar conexões de clientes. Para cada conexão, cria uma nova instância da classe `Connection` para gerenciar a comunicação com o cliente.

**Métodos:**
- `public static void main(String args[])`: Configura o `ServerSocket` na porta 7896 e inicia a escuta por conexões de clientes. Para cada conexão aceita, cria uma nova instância da classe `Connection`.

### 3. `Connection`

**Descrição:**
Classe que estende `Thread` e gerencia a comunicação com um cliente específico. Cada instância é responsável por receber dados do cliente, consultar sinônimos, e enviar a resposta de volta.

**Atributos:**
- `DataInputStream in`: Fluxo de entrada de dados do cliente.
- `DataOutputStream out`: Fluxo de saída de dados para o cliente.
- `Socket clientSocket`: Socket do cliente.

**Métodos:**
- `public Connection(Socket aClientSocket)`: Construtor que inicializa os fluxos de entrada e saída e inicia a thread.
- `public void run()`: Método executado pela thread. Lê a palavra do cliente, consulta os sinônimos usando a classe `Sinonimo`, e envia a resposta de volta ao cliente. Trata exceções e garante o fechamento do socket.

### 4. `TCPClient`

**Descrição:**
Classe cliente que se conecta a um servidor TCP, envia uma palavra para o servidor, e exibe a resposta recebida. A comunicação é feita através de fluxos de entrada e saída de dados.

**Métodos:**
- `public static void main(String args[])`: Método principal que executa o cliente TCP. Cria um socket para se conectar ao servidor na porta especificada, envia uma palavra para o servidor, recebe a resposta e a exibe. Trata exceções para problemas comuns, como não encontrar o host ou erros de entrada/saída. Garante o fechamento do socket ao final da comunicação.

## Como Executar

### No Servidor:

1. Compile as classes:
    ```sh
    javac Sinonimo.java TCPServer.java
    ```

2. Inicie o servidor:
    ```sh
    java TCPServer
    ```

### No Cliente:

1. Compile a classe cliente:
    ```sh
    javac TCPClient.java
    ```

2. Execute o cliente, fornecendo a palavra a ser consultada e o endereço do servidor (por exemplo, `localhost`):
    ```sh
    java TCPClient [palavra] [localhost]
    ```

    - `[palavra]`: Palavra a ser enviada para o servidor.
    - `[localhost]`: Endereço do servidor (pode ser alterado conforme necessário).

O cliente se conectará ao servidor na porta 7896, enviará a palavra fornecida e exibirá a resposta recebida do servidor.

## Contribuição

Sinta-se à vontade para contribuir com melhorias para este projeto. Basta fazer um fork do repositório e enviar um pull request com suas alterações.
