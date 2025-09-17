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
    private void desbloquearProcesso() {
        if (!lista_bloqueados.isEmpty()) {
            Processo desbloqueado = lista_bloqueados.remover();
            System.out.println("Desbloqueando processo: " + desbloqueado.getNome());
            adicionarProcesso(desbloqueado);
        }
    }
    private Processo escolherProcesso() {
        Processo processo = null;
        if (contador_ciclos_alta_prioridade >= 5) {
            if (!lista_media_prioridade.isEmpty()) {
                processo = lista_media_prioridade.remover();
                contador_ciclos_alta_prioridade = 0;
                return processo;
            } else if (!lista_baixa_prioridade.isEmpty()) {
                processo = lista_baixa_prioridade.remover();
                contador_ciclos_alta_prioridade = 0;
                return processo;
            } else {
                contador_ciclos_alta_prioridade = 0;
            }
        }
        if (!lista_alta_prioridade.isEmpty()) {
            processo = lista_alta_prioridade.remover();
        } else if (!lista_media_prioridade.isEmpty()) {
            processo = lista_media_prioridade.remover();
        } else if (!lista_baixa_prioridade.isEmpty()) {
            processo = lista_baixa_prioridade.remover();
        }

        return processo;
    }
    public void executarCicloDeCPU() {
        imprimirEstado();
        desbloquearProcesso();
        Processo processo = escolherProcesso();

        if (processo == null) {
            System.out.println("Nenhum processo disponível para executar.");
            return;
        }
        if ("DISCO".equalsIgnoreCase(processo.getRecurso_necessario())) {
            processo.setRecurso_necessario("DISCO_JA");
            lista_bloqueados.adicionar(processo);
            System.out.println("Processo " + processo.getNome() + " bloqueado (precisa de DISCO).");
            return;
        }

        processo.setCiclos_necessarios(processo.getCiclos_necessarios() - 1);
            System.out.println("Executando: " + processo);
                if (processo.getPrioridade() == 1) {
                    contador_ciclos_alta_prioridade++;
                }

        if (processo.getCiclos_necessarios() > 0) {
            switch (processo.getPrioridade()) {
                case 1 -> lista_alta_prioridade.adicionar(processo);
                case 2 -> lista_media_prioridade.adicionar(processo);
                case 3 -> lista_baixa_prioridade.adicionar(processo);
            }
        } else {
            System.out.println("Processo finalizado: " + processo.getNome());
        }
        imprimirEstado();
    }
    public void imprimirEstado() {
        System.out.println("===== Estado do Sistema =====");
        lista_alta_prioridade.imprimirLista("Alta prioridade");
        lista_media_prioridade.imprimirLista("Média prioridade");
        lista_baixa_prioridade.imprimirLista("Baixa prioridade");
        lista_bloqueados.imprimirLista("Bloqueados");
        System.out.println("=============================\n");
    }
    public boolean estaVazio() {
        return lista_alta_prioridade.isEmpty() &&
                lista_media_prioridade.isEmpty() &&
                lista_baixa_prioridade.isEmpty() &&
                lista_bloqueados.isEmpty();
    }
}
