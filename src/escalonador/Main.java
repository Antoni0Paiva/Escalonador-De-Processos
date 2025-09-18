package escalonador;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** Classe principal para executar o programa a partir de um arquivo de entrada.*/
public class Main {
    /** Ponto de entrada do programa. */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java escalonador.Main <arquivo.txt>");
            return;
        }
        Scheduler scheduler = new Scheduler();

        // Leitura de processos do arquivo de entrada
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] partes = linha.split("\\s+");

                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                int prioridade = Integer.parseInt(partes[2]);
                int ciclos = Integer.parseInt(partes[3]);
                String recurso = (partes.length > 4 && !partes[4].equalsIgnoreCase("null")) ? partes[4] : null;

                Processo p = new Processo(id, nome, prioridade, ciclos, recurso);
                scheduler.adicionarProcesso(p);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
            return;
        }
        // Loop principal de execução do escalonador.
        int ciclo = 1;
        while (!scheduler.estaVazio()) {
            System.out.println(">>> CICLO " + ciclo);
            scheduler.executarCicloDeCPU();
            ciclo++;
        }
        scheduler.execTodosCiclos();
    }
}