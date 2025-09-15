package escalonador;

public class Scheduler {
    private ListaDeProcessos lista_alta_prioridade;
    private ListaDeProcessos lista_media_prioridade;
    private ListaDeProcessos lista_baixa_prioridade;
    private ListaDeProcessos lista_bloqueados;
    private int contador_ciclos_alta_prioridade;

    public Scheduler() {
        this.lista_alta_prioridade = new ListaDeProcessos();
        this.lista_media_prioridade = new ListaDeProcessos();
        this.lista_baixa_prioridade = new ListaDeProcessos();
        this.lista_bloqueados = new ListaDeProcessos();
        this.contador_ciclos_alta_prioridade = 0;
    }
    public void adicionarProcesso(Processo processo) {
        switch (processo.getPrioridade()) {
            case 1 -> lista_alta_prioridade.adicionar(processo);
            case 2 -> lista_media_prioridade.adicionar(processo);
            case 3 -> lista_baixa_prioridade.adicionar(processo);
        }
    }

}
