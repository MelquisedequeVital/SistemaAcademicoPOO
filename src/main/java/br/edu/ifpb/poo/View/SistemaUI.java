package br.edu.ifpb.poo.View;

import java.util.List;

import br.edu.ifpb.poo.Model.Aluno;

public class SistemaUI {
    private Console console;
    private Menu menuPrincipal;

    public SistemaUI(Console console) {
        this.console = console;
        // Opção 1 agora é Sair para alinhar com o Controller
        List<String> opcoes = List.of("Sair", "Cadastrar Aluno", "Listar Alunos");
        this.menuPrincipal = new Menu("SISTEMA ACADÊMICO IFPB", opcoes);
    }

    public int menu() {
        menuPrincipal.exibir();
        try {
            return Integer.parseInt(console.ler());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Aluno lerAluno() {
        console.logInfo("\n### NOVO CADASTRO DE ALUNO ###");
        console.imprimir("Nome: ");
        String nome = console.ler();
        console.imprimir("Matrícula: ");
        long mat = Long.parseLong(console.ler());
        return new Aluno(nome, mat);
    }

    public void exibirAlunos(List<Aluno> alunos) {
        console.logInfo("\n### ALUNOS MATRICULADOS ###");
        if (alunos.isEmpty()) {
            console.logErro("Nenhum aluno cadastrado.");
        } else {
            // Uso de String.format para organizar a listagem em colunas
            System.out.println(String.format("%-20s | %-10s", "NOME", "MATRÍCULA"));
            System.out.println("-------------------------------------");
            for (Aluno a : alunos) {
                System.out.println(String.format("%-20s | %-10d", a.getNome(), a.getMatricula()));
            }
        }
    }

    public void limparTela() {
        console.limpar();
    }
}