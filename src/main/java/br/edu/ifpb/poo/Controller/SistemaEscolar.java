package br.edu.ifpb.poo.Controller; // "Controller" com C maiúsculo para igualar a pasta

import br.edu.ifpb.poo.Model.Aluno; // "Model" com M maiúsculo e removido o ".Enum"
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaEscolar {
    
    // Essa lista é o seu "Banco de Dados" em memória
    private List<Aluno> alunosCadastrados = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        // Lógica simples de menu para testar o UC
        System.out.println("1 - Cadastrar Aluno");
        System.out.println("2 - Sair");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        if (opcao == 1) {
            executarUC01();
        }
    }

    // AQUI ESTÁ A IMPLEMENTAÇÃO DO SEU UC01
    public void executarUC01() {
        System.out.println("--- UC01: Cadastrar Aluno ---");
        
        // Passo 2: Sistema pede dados
        System.out.print("Nome do Aluno: ");
        String nome = scanner.nextLine();
        
        // Passo 4 (Verificação): Verifica se já existe ANTES de pedir o resto ou salvar
        if (buscarAlunoPorNome(nome) != null) {
            System.out.println("ERRO: Já existe um aluno com o nome " + nome);
            return; // Encerra o cadastro aqui (Pós-condição falhou)
        }

        System.out.print("Matrícula: ");
        int matricula = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        // Passo 4 (Ação): Cadastra
        Aluno novoAluno = new Aluno(nome, matricula);
        alunosCadastrados.add(novoAluno);
        
        System.out.println("Sucesso: Aluno cadastrado!");
    }

    // Método auxiliar para ajudar na verificação
    private Aluno buscarAlunoPorNome(String nome) {
        for (Aluno a : alunosCadastrados) {
            if (a.getNome().equalsIgnoreCase(nome)) {
                return a;
            }
        }
        return null;
    }
}