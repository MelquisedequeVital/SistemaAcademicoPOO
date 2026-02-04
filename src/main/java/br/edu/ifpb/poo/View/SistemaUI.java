package br.edu.ifpb.poo.View;

import java.util.List;

import br.edu.ifpb.poo.Model.Aluno;
import br.edu.ifpb.poo.Model.Enums.ModalidadeDisciplina;
import br.edu.ifpb.poo.Model.Inscricao;
import br.edu.ifpb.poo.Model.Professor;

public class SistemaUI {

    private Console console;
    private Menu menuPrincipal;

    public SistemaUI(Console console) {
        this.console = console;
        // Atualizado com os novos casos de uso do documento de especificação [cite: 28]
        List<String> opcoes = List.of(
                "Sair",
                "Cadastrar Aluno", // 02
                "Apagar Aluno", // 03
                "Listar Alunos", // 04
                "Cadastrar Professor", // 05
                "Apagar Professor", // 06
                "Listar Professores", // 07
                "Cadastrar Componente", // 08
                "Apagar Componente", // 09
                "Listar Componentes", // 10
                "Matricular Aluno", // 11
                "Desmatricular Aluno", // 12
                "Lançar Nota", // 13
                "Apagar Notas", // 14 (Nova)
                "Histórico do Aluno" // 15
        );
        this.menuPrincipal = new Menu("SISTEMA ACADÊMICO IFPB", opcoes);
    }

    public int menu() {
        menuPrincipal.exibir();
        String entrada = console.ler();

        // Verifica se a entrada está vazia ou nula para evitar o erro de conversão
        if (entrada == null || entrada.trim().isEmpty()) {
            return -1; // Retorna uma opção que cairá no 'default' do switch
        }

        try {
            return Integer.parseInt(entrada.trim());
        } catch (NumberFormatException e) {
            return -1; // Retorna -1 se o utilizador digitar letras ou símbolos
        }
    }

    public Aluno lerAluno() {
        console.logInfo("\n### NOVO CADASTRO DE ALUNO ###");
        console.imprimir("Nome: ");
        String nome = console.ler();

        // Validação de nome vazio
        if (nome == null || nome.trim().isEmpty()) {
            console.logErro("Nome não pode ser vazio!");
            return null;
        }

        console.imprimir("Matrícula: ");
        String matStr = console.ler();
        try {
            long mat = Long.parseLong(matStr.trim());
            return new Aluno(nome, mat);
        } catch (NumberFormatException e) {
            console.logErro("Matrícula inválida! Use apenas números.");
            return null;
        }
    }

    public Professor lerProfessor() {
        console.logInfo("\n### NOVO CADASTRO DE PROFESSOR ###");
        console.imprimir("Nome: ");
        String nome = console.ler();

        if (nome == null || nome.trim().isEmpty()) {
            console.logErro("Nome não pode ser vazio!");
            return null;
        }

        console.imprimir("Matrícula: ");
        String matStr = console.ler();
        try {
            long mat = Long.parseLong(matStr.trim());
            return new Professor(mat, nome);
        } catch (NumberFormatException e) {
            console.logErro("Matrícula inválida!");
            return null;
        }
    }

    // Arquivo: src/main/java/br/edu/ifpb/poo/View/SistemaUI.java
    public <T> T selecionarDaLista(String titulo, List<T> lista) {
        if (lista == null || lista.isEmpty()) {
            console.logErro("Lista de " + titulo + " vazia!");
            return null;
        }

        console.logInfo("\n--- SELECIONE " + titulo.toUpperCase() + " ---");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(String.format("%02d > %s", (i + 1), lista.get(i).toString()));
        }

        console.imprimir("Escolha o número da opção: ");
        String entrada = console.ler();

        // Tratamento para entrada vazia
        if (entrada == null || entrada.trim().isEmpty()) {
            console.logErro("Nenhuma opção foi digitada.");
            return null;
        }

        try {
            int escolha = Integer.parseInt(entrada.trim());
            if (escolha > 0 && escolha <= lista.size()) {
                return lista.get(escolha - 1);
            }
        } catch (NumberFormatException e) {
            console.logErro("Entrada inválida. Digite apenas números.");
        }

        console.logErro("Opção inexistente.");
        return null;
    }

    public void exibirAlunos(List<Aluno> alunos) {
        console.logInfo("\n### ALUNOS MATRICULADOS ###");
        if (alunos.isEmpty()) {
            console.logErro("Nenhum aluno cadastrado.");
        } else {
            System.out.println(String.format("%-20s | %-10s", "NOME", "MATRÍCULA"));
            System.out.println("-------------------------------------");
            for (Aluno a : alunos) {
                System.out.println(String.format("%-20s | %-10d", a.getNome(), a.getMatricula()));
            }
        }
    }

    // Arquivo: src/main/java/br/edu/ifpb/poo/View/SistemaUI.java
    public void exibirHistoricoAluno(Aluno aluno) {
        console.logInfo("\n--- STATUS ACADÊMICO: " + aluno.getNome().toUpperCase() + " ---");
        List<Inscricao> inscricoes = aluno.getInscricoes();

        if (inscricoes.isEmpty()) {
            console.logErro("Sem matrículas encontradas.");
        } else {
            // Colunas mais largas para evitar o efeito "serrilhado"
            String formato = "| %-25s | %-7s | %-7s | %-12s |";
            String linhaDivisoria = "+" + "-".repeat(27) + "+" + "-".repeat(9) + "+" + "-".repeat(9) + "+" + "-".repeat(14) + "+";

            System.out.println(linhaDivisoria);
            System.out.println(String.format(formato, "COMPONENTE", "NOTAS", "MÉDIA", "STATUS"));
            System.out.println(linhaDivisoria);

            for (Inscricao ins : inscricoes) {
                int notasLancadas = ins.getNotas().size();
                int totalNecessario = ins.getComponenteFormativo().getQtdAvaliacoes();
                Double mediaAtual = ins.obterMediaFinal();
                String progresso = notasLancadas + "/" + totalNecessario;

                System.out.println(String.format(formato,
                        ins.getComponenteFormativo().getNome(),
                        progresso,
                        String.format("%.2f", mediaAtual),
                        ins.getStatusAluno()
                ));
            }
            System.out.println(linhaDivisoria);
        }
    }

    public <T> void listarEntidades(String titulo, List<T> lista) {
        console.logInfo("\n--- LISTAGEM DE " + titulo.toUpperCase() + " ---");
        if (lista.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
        } else {
            lista.forEach(item -> System.out.println("- " + item.toString()));
        }
    }

    public Double lerNota() {
        console.imprimir("Informe a nota/avaliação: ");
        try {
            return Double.parseDouble(console.ler());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void limparTela() {
        console.limpar();
    }

    public String lerTexto(String campo) {
        console.imprimir(campo + ": ");
        return console.ler();
    }

    public int lerInteiro(String campo) {
        console.imprimir(campo + ": ");
        try {
            return Integer.parseInt(console.ler());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

// Método para selecionar a modalidade (PRESENCIAL, ONLINE, HIBRIDO)
    public ModalidadeDisciplina selecionarModalidade() {
        List<ModalidadeDisciplina> modalidades = List.of(ModalidadeDisciplina.values());
        return selecionarDaLista("Modalidade", modalidades);
    }

    public int escolherTipoComponente() {
        console.logInfo("\n--- TIPO DE COMPONENTE ---");
        System.out.println("01 > Disciplina");
        System.out.println("02 > Estágio");
        console.imprimir("Escolha o tipo: ");
        try {
            return Integer.parseInt(console.ler());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}
