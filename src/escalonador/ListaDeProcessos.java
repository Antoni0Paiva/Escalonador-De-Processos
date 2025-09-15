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
    
}
