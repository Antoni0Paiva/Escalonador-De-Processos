package escalonador;

public class Processo {
    public static final int ALTA = 1;
    public static final int MÉDIA = 2;
    public static final int BAIXA = 3;

    private int id;
    private String nome;
    private int prioridade;
    private int ciclos_necessarios;
    private String recurso_necessario;

    public Processo(int id, String nome, int prioridade, int ciclos_necessarios, String recurso_necessario) {
        this.id = id;
        this.nome = nome;
        this.prioridade = switch (prioridade){
            case 1 -> "Alta";
            case 2 -> "Média";
            case 3 -> "Baixa";
        };
        this.ciclos_necessarios = ciclos_necessarios;
        this.recurso_necessario = recurso_necessario;
    }
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public int getCiclos_necessarios() {
        return ciclos_necessarios;
    }

    public String getRecurso_necessario() {
        return recurso_necessario;
    }

}