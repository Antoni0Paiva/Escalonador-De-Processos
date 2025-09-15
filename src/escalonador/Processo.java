package escalonador;

public class Processo {

    private int id;
    private String nome;
    private int prioridade;
    private int ciclos_necessarios;
    private String recurso_necessario;

    public Processo(int id, String nome, int prioridade, int ciclos_necessarios, String recurso_necessario) {
        this.id = id;
        this.nome = nome;
        if (prioridade != 1 && prioridade != 2 && prioridade != 3) {
            throw new IllegalArgumentException("Prioridade inválida! Selecione uma prioridade permitida.");
        } else {
            this.prioridade = prioridade;
        }
        this.ciclos_necessarios = ciclos_necessarios;
        this.recurso_necessario = recurso_necessario;
    }
    public int getPrioridade() {
        return prioridade;
    }
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCiclos_necessarios() {
        return ciclos_necessarios;
    }

    public String getRecurso_necessario() {
        return recurso_necessario;
    }
    @Override
    public String toString() {
        return "Processo {" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", prioridade='" + prioridade +
                ", ciclosNecessarios=" + ciclos_necessarios +
                ", recursoNecessario='" + recurso_necessario + '\'' +
                '}';
    }
    }