package escalonador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** Lógica principal do escalonador, gerenciando o fluxo de processos.*/
public class Scheduler {
    private ListaDeProcessos lista_alta_prioridade;
    private ListaDeProcessos lista_media_prioridade;
    private ListaDeProcessos lista_baixa_prioridade;
    private ListaDeProcessos lista_bloqueados;
    private int contador_ciclos_alta_prioridade;

    /** Construtor que inicializa as listas de processos. */
    public Scheduler() {
        this.lista_alta_prioridade = new ListaDeProcessos();
        this.lista_media_prioridade = new ListaDeProcessos();
        this.lista_baixa_prioridade = new ListaDeProcessos();
        this.lista_bloqueados = new ListaDeProcessos();
        this.contador_ciclos_alta_prioridade = 0;
    }

    /** Adiciona um processo na lista de prioridade correspondente. */
    public void adicionarProcesso(Processo processo) {
        if (contemId(processo.getId())) {
            throw new IllegalArgumentException("Já existe um processo com o ID " + processo.getId());
        }
        switch (processo.getPrioridade()) {
            case 1 -> lista_alta_prioridade.adicionar(processo);
            case 2 -> lista_media_prioridade.adicionar(processo);
            case 3 -> lista_baixa_prioridade.adicionar(processo);
        }
    }

    /** Verifica se um ID de processo já existe em qualquer lista. */
    private boolean contemId(int id) {
        return lista_alta_prioridade.contemId(id)
                || lista_media_prioridade.contemId(id)
                || lista_baixa_prioridade.contemId(id)
                || lista_bloqueados.contemId(id);
    }

    /** Move o processo mais antigo da lista de bloqueados para sua lista de prioridade original. */
    private void desbloquearProcesso() {
        if (!lista_bloqueados.isEmpty()) {
            Processo desbloqueado = lista_bloqueados.remover();
            System.out.println("Desbloqueando processo: " + desbloqueado.getNome());
            adicionarProcesso(desbloqueado);
        }
    }

    /** Escolhe o próximo processo a ser executado, seguindo as regras de prioridade e anti-inanição. */
    private Processo escolherProcesso() {
        Processo processo = null;
        // Lógica de prevenção de inanição: executa um processo de prioridade menor após 5 de alta prioridade
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
        // Lógica de execução padrão: prioridade alta > média > baixa
        if (!lista_alta_prioridade.isEmpty()) {
            processo = lista_alta_prioridade.remover();
        } else if (!lista_media_prioridade.isEmpty()) {
            processo = lista_media_prioridade.remover();
        } else if (!lista_baixa_prioridade.isEmpty()) {
            processo = lista_baixa_prioridade.remover();
        }
        return processo;
    }

    /** Executa um único ciclo de CPU. */
    public void executarCicloDeCPU() {
        imprimirEstado();
        desbloquearProcesso();
        Processo processo = escolherProcesso();

        if (processo == null) {
            System.out.println("Nenhum processo disponível para executar.");
            return;
        }
        // Se o processo precisa do "DISCO" pela primeira vez, ele é movido para a lista de bloqueados
        if ("DISCO".equalsIgnoreCase(processo.getRecurso_necessario())) {
            processo.setRecurso_necessario("null");
            lista_bloqueados.adicionar(processo);
            System.out.println("Processo " + processo.getNome() + " bloqueado (precisa de DISCO).");
            return;
        }

        // Executa o processo e atualiza seus ciclos necessários
        processo.setCiclos_necessarios(processo.getCiclos_necessarios() - 1);
        System.out.println("Executando: " + processo);
        if (processo.getPrioridade() == 1) {
            contador_ciclos_alta_prioridade++;
        }

        // Reinsere o processo na lista de origem se ele ainda não tiver terminado
        if (processo.getCiclos_necessarios() > 0) {
            switch (processo.getPrioridade()) {
                case 1 -> lista_alta_prioridade.adicionar(processo);
                case 2 -> lista_media_prioridade.adicionar(processo);
                case 3 -> lista_baixa_prioridade.adicionar(processo);
            }
        } else {
            System.out.println("Processo finalizado: " + processo.getNome());
        }
    }

    /** Imprime o estado atual de todas as listas. */
    public void imprimirEstado() {
        System.out.println("===== Estado do Sistema =====");
        lista_alta_prioridade.imprimirLista("Alta prioridade");
        lista_media_prioridade.imprimirLista("Média prioridade");
        lista_baixa_prioridade.imprimirLista("Baixa prioridade");
        lista_bloqueados.imprimirLista("Bloqueados");
        System.out.println("=============================\n");
    }

    /** Retorna true se todas as listas estiverem vazias.*/
    public boolean estaVazio() {
        return lista_alta_prioridade.isEmpty() &&
                lista_media_prioridade.isEmpty() &&
                lista_baixa_prioridade.isEmpty() &&
                lista_bloqueados.isEmpty();
    }

    public void execTodosCiclos() {
        int ciclo = 1;
        if (estaVazio()) {
            System.out.println();
            imprimirEstado();
            System.out.println("Finalizado! Nenhum ciclo a ser concluído!");
            return;
        }
    }
}