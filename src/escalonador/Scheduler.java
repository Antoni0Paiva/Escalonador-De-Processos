package escalonador;

public class Scheduler {
    private ListaDeProcessos listaAltaPrioridade;
    private ListaDeProcessos listaMediaPrioridade;
    private ListaDeProcessos listaBaixaPrioridade;
    private ListaDeProcessos listaBloqueados;
    private int contador_ciclos_alta_prioridade;

    public Scheduler() {
        this.listaAltaPrioridade = new ListaDeProcessos();
        this.listaMediaPrioridade = new ListaDeProcessos();
        this.listaBaixaPrioridade = new ListaDeProcessos();
        this.listaBloqueados = new ListaDeProcessos();
        this.contador_ciclos_alta_prioridade = 0;
    }

}
