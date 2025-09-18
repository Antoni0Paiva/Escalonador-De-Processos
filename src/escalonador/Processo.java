package escalonador;

/** Representa um processo com seus atributos e métodos.*/
public class Processo {

    private int id;
    private String nome;
    private int prioridade;
    private int ciclos_necessarios;
    private String recurso_necessario;

    /** Construtor para criar um novo processo.*/
    public Processo(int id, String nome, int prioridade, int ciclos_necessarios, String recurso_necessario) {
        this.id = id;
        this.nome = nome;
        if (prioridade != 1 && prioridade != 2 && prioridade != 3) {
            throw new IllegalArgumentException("Prioridade inválida! Selecione uma prioridade permitida.");
        } else {
            this.prioridade = prioridade;
        }
        if (ciclos_necessarios <= 0) {
            throw new IllegalArgumentException("Quantidade de ciclos deve ser maior que 0. Valor fornecido: " + ciclos_necessarios);
        }
        this.ciclos_necessarios = ciclos_necessarios;
        this.recurso_necessario = recurso_necessario;
    }
    // Getters e Setters para os atributos do processo.
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
    public void setCiclos_necessarios(int ciclos){
        this.ciclos_necessarios = ciclos;
    }

    public String getRecurso_necessario() {
        return recurso_necessario;
    }
    public void setRecurso_necessario(String recurso_necessario) {
        this.recurso_necessario = recurso_necessario;
    }

    /** Retorna uma representação em String do objeto Processo.*/
    @Override
    public String toString() {
        return "Processo {" +
                "id=" + id +
                ", nome = '" + nome + '\'' +
                ", prioridade = " + prioridade +
                ", ciclosNecessarios = " + ciclos_necessarios +
                ", recursoNecessario = '" + recurso_necessario + '\'' +
                '}';
    }
}