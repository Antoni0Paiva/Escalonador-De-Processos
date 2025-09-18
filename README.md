# Projeto: Escalonador com Listas de Prioridade e Prevenção de Inanição

## Informações da Disciplina
- **Disciplina:** Algoritmos e Estrutura de Dados I  
- **Professor:** Dimmy Magalhães  
- **Turma:** Allen  

## Integrante
- Antonio Eduardo Paiva de Almeida – Matrícula **0030628**

## Repositório
[Link do Repositório GitHub](https://github.com/Antoni0Paiva/Escalonador-De-Processos.git)  

---

## Descrição do Projeto
Este projeto implementa um **escalonador de processos** para o sistema fictício **iCEVOS**, simulando o funcionamento de um sistema operacional.  

O objetivo é gerenciar processos em múltiplos níveis de prioridade, garantindo justiça no escalonamento por meio de **prevenção de inanição (starvation)** e controle de processos **bloqueados por recursos**.  

Para isso, foram implementadas estruturas de dados próprias, sem o uso de bibliotecas prontas como `ArrayList` ou `LinkedList`. O gerenciamento é feito a partir de uma **lista encadeada customizada**.

### Funcionalidades principais:
- Três listas de processos: **alta, média e baixa prioridade**.
- Uma lista para **processos bloqueados** que precisam de recurso (`DISCO`).
- **Prevenção de inanição**: após 5 execuções seguidas de processos de alta prioridade, o escalonador garante a execução de processos de prioridade menor.
- **Gerenciamento de bloqueio e desbloqueio** de processos que requerem o recurso `DISCO`.
- Saída no console exibindo:
  - Estado atual das listas a cada ciclo;
  - Processos em execução;
  - Processos bloqueados e desbloqueados;
  - Processos finalizados.

---

## Estrutura do Código
O projeto é composto pelas seguintes classes:
- **Processo:** representa um processo (atributos como `id`, `nome`, `prioridade`, `ciclos_necessarios`, `recurso_necessario`).  
- **No:** nó da lista encadeada que armazena um processo.  
- **ListaDeProcessos:** estrutura de dados personalizada para gerenciar processos em forma de lista encadeada.  
- **Scheduler:** lógica central do escalonador, responsável por escolher e executar processos.  
- **Main:** classe principal que lê os processos de um arquivo `.txt` e inicia a execução.  

---

## Instruções de Compilação e Execução

### 1. Pré-requisitos
- **Java JDK 17+** (ou versão compatível) instalado.  
- Arquivo de entrada `.txt` com os processos na seguinte formatação:
id nome prioridade ciclos recurso
Exemplo (`processos.txt`):

1 P1 1 4 DISCO
2 P2 2 3 null
3 P3 3 5 DISCO
4 P4 1 2 null
  
  ### 2. Compilação
No terminal, dentro da raiz do repositório (pasta que contém o diretório `escalonador`):

```bash
javac escalonador/*.java
```
Isso compilará todas as classes do projeto.

### 3. Execução via terminal
Para rodar o projeto, execute
```bash
java escalonador.Main arquivo.txt
```
Onde arquivo.txt é o nome do seu arquivo de entrada.
O arquivo deve estar na raiz do repositório.

### 4. Execução via IntelliJ IDEA
Caso utilize o IntelliJ IDEA:

1° Vá em Run > Edit Configurations...
2° No campo Program arguments, informe o nome do arquivo de entrada (ex.: processos.txt).
3° Certifique-se de que o Working Directory está apontando para a raiz do projeto (onde está o arquivo txt).
4° Clique em Run para executar.

## Exemplo de Saída
Ao rodar o programa, a saída será semelhante a:

>>> CICLO 1
===== Estado do Sistema =====
Alta prioridade: P1(id=1, ciclos=4)
Média prioridade: P2(id=2, ciclos=3)
Baixa prioridade: P3(id=3, ciclos=5)
Bloqueados: [vazia]
=============================

Processo P1 bloqueado (precisa de DISCO).

>>> CICLO 2
===== Estado do Sistema =====
Alta prioridade: [vazia]
Média prioridade: P2(id=2, ciclos=3)
Baixa prioridade: P3(id=3, ciclos=5)
Bloqueados: P1(id=1, ciclos=4)
=============================

Executando: Processo {id=2, nome='P2', prioridade=2, ciclosNecessarios=2, recursoNecessario='null'}

## Relatório
Um relatório separado (relatorio_analise.pdf) foi elaborado contendo:
- Justificativa das estruturas de dados utilizadas;
- Análise de complexidade (Big-O);
- Descrição da lógica de anti-inanição e bloqueio;
- Discussão sobre pontos fortes e possíveis gargalos.
