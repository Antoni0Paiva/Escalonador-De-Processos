package escalonador;

/** Classe da estrutura de dados que foi criada */
public class ListaDeProcessos {
    No inicio;
    No fim;

    /** Construtor para inicializar uma lista vazia. */
    public ListaDeProcessos() {
        this.inicio = null;
        this.fim = null;
    }


    /** Adiciona um processo no final da lista. */
    public void adicionar(Processo processo) {
        No novo = new No(processo);
        if (inicio == null) {
            inicio = novo;
            fim = novo;
        } else {
            fim.proximo = novo;
            fim = novo;
        }
    }
    /** Retorna true se a lista estiver vazia. */
    public boolean isEmpty() {
        return inicio == null;
    }
    /** Remove e retorna o processo do início da lista. */
    public Processo remover() {
        if (isEmpty()) {
            throw new IllegalStateException("Lista vazia");
        }
        Processo processo = inicio.processo;
        inicio = inicio.proximo;
        if (inicio == null) {
            fim = null;
        }
        return processo;
    }
    /** Imprime o conteúdo da lista no console. */
    public void imprimirLista(String nomeLista) {
        System.out.print(nomeLista + ": ");
        No atual = inicio;
        if (atual == null) {
            System.out.println("[vazia]");
            return;
        }
        while (atual != null) {
            System.out.print(atual.processo.getNome() + "(id=" + atual.processo.getId() + ", ciclos=" + atual.processo.getCiclos_necessarios() + ") ");
            atual = atual.proximo;
        }
        System.out.println();
    }
    /** Verifica se um processo com um ID específico existe na lista. */
    public boolean contemId(int id) {
        No atual = inicio;
        while (atual != null) {
            if (atual.processo.getId() == id) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }
}