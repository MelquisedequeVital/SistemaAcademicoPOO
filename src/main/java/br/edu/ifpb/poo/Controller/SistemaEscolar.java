package br.edu.ifpb.poo.Controller; // "Controller" com C maiúsculo para igualar a pasta

import java.util.ArrayList; // "Model" com M maiúsculo e removido o ".Enum"
import java.util.List;

import br.edu.ifpb.poo.Model.Aluno;
import br.edu.ifpb.poo.View.SistemaUI;

public class SistemaEscolar {
    private List<Aluno> alunos = new ArrayList<>();
    private SistemaUI ui = new SistemaUI();

    public void iniciar() {
        int opcao;
        do {
            opcao = ui.menu();
            switch (opcao) {
                case 1 -> ui.mensagem("Saindo do sistema..."); // Opção 1 agora sai
                case 2 -> cadastrar();
                case 3 -> ui.exibirAlunos(alunos); 
                default -> ui.mensagem("Opção inválida!");
            }
        } while (opcao != 1);
    }

    private void cadastrar() {
        try {
            Aluno novo = ui.lerAluno();
            // Regra de negócio: evitar duplicados (ajustado do seu original)
            if (alunos.stream().anyMatch(a -> a.getMatricula() == novo.getMatricula())) {
                ui.mensagem("Erro: Matrícula já existe!");
            } else {
                alunos.add(novo);
                ui.mensagem("Aluno cadastrado com sucesso!");
            }
        } catch (Exception e) {
            ui.mensagem("Erro nos dados digitados.");
        }
    }
}