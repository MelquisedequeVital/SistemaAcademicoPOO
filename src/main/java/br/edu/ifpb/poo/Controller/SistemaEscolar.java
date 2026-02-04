package br.edu.ifpb.poo.Controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.poo.Model.Aluno;
import br.edu.ifpb.poo.View.Console; // Certifique-se de que o pacote está correto
import br.edu.ifpb.poo.View.SistemaUI;

public class SistemaEscolar {

    private List<Aluno> alunos = new ArrayList<>();
    private Console console = new Console(); // Instancia o console para uso no controller
    private SistemaUI ui = new SistemaUI(console); // Passa o console para a View

    public void iniciar() {
        int opcao;
        do {
            ui.limparTela(); 
            opcao = ui.menu();

            switch (opcao) {
                case 1 -> {
                    console.logErro("Saindo...");
                    // Pequena pausa para o usuário ver a mensagem de saída
                    try { Thread.sleep(500); } catch (InterruptedException e) {}
                }
                case 2 -> {
                    cadastrar();
                    System.out.println("\nPressione ENTER para continuar...");
                    console.ler();
                }
                case 3 -> {
                    ui.exibirAlunos(alunos);
                    System.out.println("\nPressione ENTER para voltar ao menu...");
                    console.ler(); 
                }
                default -> {
                    console.logErro("Opção Inválida!");
                    System.out.println("Pressione ENTER...");
                    console.ler();
                }
            }
        } while (opcao != 1);
    }

    private void cadastrar() {
        try {
            Aluno novo = ui.lerAluno();
            // Verifica duplicidade baseada na matrícula
            if (alunos.stream().anyMatch(a -> a.getMatricula() == novo.getMatricula())) {
                console.logErro("Erro: Matrícula já existe!");
            } else {
                alunos.add(novo);
                console.logSucesso("Aluno cadastrado com sucesso!");
            }
        } catch (Exception e) {
            console.logErro("Erro nos dados digitados: " + e.getMessage());
        }
    }
}