package escalonador;

/** Estrutura que armazena um processo e a referência para o próximo nó da lista.*/
public class No {
    Processo processo;
    No proximo;

    /** Construtor */
    public No(Processo processo) {
        this.processo = processo;
        this.proximo = null;
    }
}