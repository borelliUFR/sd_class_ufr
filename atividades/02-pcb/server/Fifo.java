package pcb;

public class Fifo {

    // Variáveis para controlar a cabeça, cauda e quantidade de elementos na fila
    private int cabeca, cauda, qtd_elementos;
    // Array que representa a fila
    private int[] fila;

    // Construtor que inicializa a fila com um determinado tamanho
    public Fifo(int tamanho) {
        this.cabeca = 0;
        this.cauda = 0;
        this.qtd_elementos = 0;
        this.fila = new int[tamanho];
    }

    // Método sincronizado para inserir um elemento na fila
    public synchronized void inserir(int elemento) {
        // Enquanto a fila estiver cheia, espera
        while (this.qtd_elementos == this.fila.length) {
            try {
                // Libera o lock e espera ser notificado
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Insere o elemento na posição da cauda
        this.fila[this.cauda] = elemento;
        // Atualiza a posição da cauda de forma circular
        this.cauda = (this.cauda + 1) % this.fila.length;
        // Incrementa a quantidade de elementos
        this.qtd_elementos++;
        // Notifica todas as threads que estão esperando
        notifyAll();
    }

    // Método sincronizado para remover um elemento da fila
    public synchronized int remover() {
        // Enquanto a fila estiver vazia, espera
        while (this.qtd_elementos == 0) {
            try {
                // Libera o lock e espera ser notificado
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Remove o elemento da posição da cabeça
        int elemento = this.fila[this.cabeca];
        // Atualiza a posição da cabeça de forma circular
        this.cabeca = (this.cabeca + 1) % this.fila.length;
        // Decrementa a quantidade de elementos
        this.qtd_elementos--;
        // Notifica todas as threads que estão esperando
        notifyAll();
        // Retorna o elemento removido
        return elemento;
    }

    // Método para verificar se a fila está cheia
    public boolean cheia() {
        return this.qtd_elementos == this.fila.length;
    }

    // Método para verificar se a fila está vazia
    public boolean vazia() {
        return this.qtd_elementos == 0;
    }
}
