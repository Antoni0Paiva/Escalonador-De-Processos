package escalonador;

public class ListaDeProcessos {
    No inicio;
    No fim;

    public ListaDeProcessos() {
        this.inicio = null;
        this.fim = null;
    }
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
    public boolean isEmpty() {
        return inicio == null;
    }
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
