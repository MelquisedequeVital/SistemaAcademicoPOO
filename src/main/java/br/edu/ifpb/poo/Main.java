package br.edu.ifpb.poo;

import br.edu.ifpb.poo.Model.Aluno;
import br.edu.ifpb.poo.Model.Professor; // <--- Import do Professor
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Listas para simular o banco de dados
        List<Aluno> listaAlunos = new ArrayList<>();
        List<Professor> listaProfessores = new ArrayList<>(); 

        // ----------------------------------------
        // UC01: Cadastrar Aluno
        // ----------------------------------------
        System.out.println("--- UC01: Cadastrar Aluno ---");
        System.out.println("Digite o nome do Aluno:");
        String nomeAluno = scanner.nextLine();

        boolean alunoExiste = false;
        for (Aluno a : listaAlunos) {
            if (a.getNome().equalsIgnoreCase(nomeAluno)) {
                alunoExiste = true;
                break;
            }
        }

        if (alunoExiste) {
            System.out.println("Erro: Aluno já existe!");
        } else {
            System.out.println("Digite a matrícula do Aluno:");
            long matAluno = scanner.nextLong(); // Lê matrícula (long)
            
            Aluno novoAluno = new Aluno(nomeAluno, matAluno);
            listaAlunos.add(novoAluno);
            System.out.println("Sucesso: Aluno cadastrado!");
        }

        // LIMPEZA DE BUFFER (Essencial entre ler número e ler texto)
        scanner.nextLine(); 

        // ----------------------------------------
        // UC02: Cadastrar Professor
        // ----------------------------------------
        System.out.println("\n--- UC02: Cadastrar Professor ---");
        System.out.println("Digite o nome do Professor:");
        String nomeProf = scanner.nextLine();

        boolean profExiste = false;
        for (Professor p : listaProfessores) {
            if (p.getNome().equalsIgnoreCase(nomeProf)) {
                profExiste = true;
                break;
            }
        }

        if (profExiste) {
            System.out.println("Erro: Professor já cadastrado!");
        } else {
            System.out.println("Digite a matrícula do Professor:");
            long matProf = scanner.nextLong(); // Lê matrícula (long)
            
            // ATENÇÃO: No seu código, o Professor recebe (Matricula, Nome)
            // Diferente do Aluno que era (Nome, Matricula)
            Professor novoProf = new Professor(matProf, nomeProf);
            listaProfessores.add(novoProf);
            
            System.out.println("Sucesso: Professor cadastrado!");
        }
        
        scanner.close();
    }
}