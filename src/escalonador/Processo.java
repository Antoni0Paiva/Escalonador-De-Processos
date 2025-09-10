package escalonador;

public class Processo {
    public static final int ALTA = 1;
    public static final int MÉDIA = 2;
    public static final int BAIXA = 3;

int id;
String nome;
int prioridade;
int ciclos_necessarios;
String recurso_necessario;

public Processo (int id, String nome, int prioridade, int ciclos_necessarios, String recurso_necessario) {
    this.id = id;
    this.nome = nome;
    this.prioridade = prioridade;
    this.ciclos_necessarios = ciclos_necessarios;
    this. recurso_necessario = recurso_necessario;
}
}
