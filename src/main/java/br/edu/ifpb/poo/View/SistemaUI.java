package br.edu.ifpb.poo.View;

import java.util.List;

import br.edu.ifpb.poo.Model.Aluno;

public class SistemaUI {
    private Console console = new Console();
    private Menu menuPrincipal;

    public SistemaUI() {
        List<String> opcoes = List.of("Sair", "Cadastrar Aluno", "Listar Alunos");
        this.menuPrincipal = new Menu("Sistema Acadêmico", opcoes, console);
    }

    public int menu() {
        menuPrincipal.exibir();
        return menuPrincipal.lerOpcao();
    }

    public Aluno lerAluno() {
        console.pularLinha("\n### CADASTRO DE ALUNO ###");
        console.imprimir("Nome: ");
        String nome = console.lerString();
        console.imprimir("Matrícula: ");
        long mat = Long.parseLong(console.lerString());
        return new Aluno(nome, mat);
    }

    public void exibirAlunos(List<Aluno> alunos) {
        console.pularLinha("\n### LISTA DE ALUNOS ###");
        console.pularLinha(String.format("%-20s | %-10s", "NOME", "MATRÍCULA"));
        console.pularLinha("-".repeat(33));
        
        for (Aluno a : alunos) {
            console.pularLinha(String.format("%-20s | %-10d", a.getNome(), a.getMatricula()));
        }
        console.lerString();
    }

    public void mensagem(String texto) {
        console.pularLinha("[!] " + texto);
    }
}