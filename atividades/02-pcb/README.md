# Produtor-Consumidor com RMI em Java

## Descrição

Este projeto implementa um padrão produtor-consumidor utilizando Java RMI (Remote Method Invocation). Ele inclui uma fila FIFO (First In, First Out) para gerenciar a inserção e remoção de elementos de maneira sincronizada. A comunicação entre o produtor e o consumidor é feita por meio de chamadas remotas.

## Classes

### 1. Fifo

**Descrição:**
Implementa uma fila circular FIFO para gerenciar a inserção e remoção de elementos de maneira sincronizada.

**Atributos:**
- `int cabeca, cauda, qtd_elementos`: Controlam a posição da cabeça, cauda e a quantidade de elementos na fila.
- `int[] fila`: Array que representa a fila.

**Métodos:**
- `Fifo(int tamanho)`: Construtor que inicializa a fila com um tamanho especificado.
- `synchronized void inserir(int elemento)`: Insere um elemento na fila de maneira sincronizada.
- `synchronized int remover()`: Remove um elemento da fila de maneira sincronizada.
- `boolean cheia()`: Verifica se a fila está cheia.
- `boolean vazia()`: Verifica se a fila está vazia.

### 2. ConsumerProducerImp

**Descrição:**
Interface remota que define os métodos para produção e consumo de elementos, utilizada em um ambiente RMI.

**Métodos:**
- `void produzir(int elemento) throws RemoteException`: Método remoto para produzir um elemento.
- `void consumir() throws RemoteException`: Método remoto para consumir um elemento.

### 3. ConsumerProducerRemote

**Descrição:**
Implementa a interface `ConsumerProducerImp` e fornece a lógica para a produção e consumo de elementos em uma fila FIFO, utilizando RMI.

**Atributos:**
- `Fifo fila`: Fila FIFO utilizada para armazenar os elementos.

**Métodos:**
- `ConsumerProducerRemote(Fifo fila) throws RemoteException`: Construtor que inicializa a fila e pode lançar RemoteException.
- `synchronized void produzir(int elemento) throws RemoteException`: Método para produzir elementos e inseri-los na fila de maneira sincronizada.
- `synchronized void consumir() throws RemoteException`: Método para consumir elementos da fila de maneira sincronizada.

### 4. Server

**Descrição:**
Classe que configura e inicia o serviço RMI para a produção e consumo de elementos.

**Métodos:**
- `public static void main(String[] args)`: Método principal que cria o registro RMI, instancia a fila FIFO e o objeto remoto `ConsumerProducerRemote`, e vincula o objeto remoto ao URL RMI.

### 5. Prog

**Descrição:**
Classe cliente que busca o objeto remoto e cria threads para a produção e consumo de elementos utilizando os métodos remotos definidos na interface `ConsumerProducerImp`.

**Métodos:**
- `public static void main(String[] args)`: Método principal que busca o objeto remoto, cria e inicia as threads do produtor e consumidor, e aguarda a conclusão dessas threads.

## Executando o Projeto

1. Compile todas as classes:

    ```sh
    javac server/*.java client/*.java
    ```

2. Inicie o servidor RMI:

    ```sh
    java server.Server
    ```

3. Execute o cliente:

    ```sh
    java client.Prog
    ```

## Contribuição

Sinta-se à vontade para contribuir com melhorias para este projeto. Basta fazer um fork do repositório e enviar um pull request com suas alterações.
