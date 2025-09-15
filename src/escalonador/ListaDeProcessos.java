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
    
}
