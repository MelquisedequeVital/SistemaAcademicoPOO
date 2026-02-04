package br.edu.ifpb.poo.Persistence;

import br.edu.ifpb.poo.Model.Aluno;
import br.edu.ifpb.poo.Model.ComponenteFormativo;
import br.edu.ifpb.poo.Model.Disciplina;
import br.edu.ifpb.poo.Model.Enums.ModalidadeDisciplina;
import br.edu.ifpb.poo.Model.Estagio;
import br.edu.ifpb.poo.Model.Inscricao;
import br.edu.ifpb.poo.Model.Professor;

public class CargaDados {

    public static void popular(GerenciadorDados db) {
        // --- 1. PROFESSORES ---
        Professor p1 = new Professor(100, "Frederico Guedes");
        Professor p2 = new Professor(101, "Luiz Alberto");
        Professor p3 = new Professor(102, "Maria Clara");
        Professor p4 = new Professor(103, "João Paulo");
        Professor p5 = new Professor(104, "Ana Beatriz");
        
        db.salvarProfessor(p1);
        db.salvarProfessor(p2);
        db.salvarProfessor(p3);
        db.salvarProfessor(p4);
        db.salvarProfessor(p5);

        // --- 2. COMPONENTES (DISCIPLINAS) ---
        Disciplina d1 = new Disciplina("POO", "Programação Orientada a Objetos", p1, ModalidadeDisciplina.PRESENCIAL, 2);
        Disciplina d2 = new Disciplina("LS", "Linguagem de Script", p2, ModalidadeDisciplina.ONLINE, 3);
        Disciplina d3 = new Disciplina("BD1", "Banco de Dados I", p3, ModalidadeDisciplina.HIBRIDO, 2);
        Disciplina d4 = new Disciplina("REDES", "Redes de Computadores", p4, ModalidadeDisciplina.PRESENCIAL, 3);
        Disciplina d5 = new Disciplina("ED", "Estrutura de Dados", p5, ModalidadeDisciplina.PRESENCIAL, 2);
        Disciplina d6 = new Disciplina("IHM", "Interface Homem-Máquina", p3, ModalidadeDisciplina.ONLINE, 2);

        db.salvarComponente(d1);
        db.salvarComponente(d2);
        db.salvarComponente(d3);
        db.salvarComponente(d4);
        db.salvarComponente(d5);
        db.salvarComponente(d6);

        // --- 3. COMPONENTES (ESTÁGIOS) ---
        Estagio e1 = new Estagio("EST-GOO", "Estágio em Engenharia", p1, "Google");
        Estagio e2 = new Estagio("EST-MET", "Estágio em Frontend", p2, "Meta");
        Estagio e3 = new Estagio("EST-CIS", "Estágio em Suporte", p4, "Cisco");
        Estagio e4 = new Estagio("EST-AMZ", "Estágio em Dados", p3, "Amazon");

        db.salvarComponente(e1);
        db.salvarComponente(e2);
        db.salvarComponente(e3);
        db.salvarComponente(e4);

        // Vincular componentes aos professores
        p1.addAtribuicao(d1); p1.addAtribuicao(e1);
        p2.addAtribuicao(d2); p2.addAtribuicao(e2);
        p3.addAtribuicao(d3); p3.addAtribuicao(d6); p3.addAtribuicao(e4);
        p4.addAtribuicao(d4); p4.addAtribuicao(e3);
        p5.addAtribuicao(d5);

        // --- 4. ALUNOS ---
        Aluno[] alunos = {
            new Aluno("Melquisedeque Vital", 2023001),
            new Aluno("Rogerio Andrade", 2023002),
            new Aluno("Mariana Ludmilla", 2023003),
            new Aluno("Victor Belfort", 2023004),
            new Aluno("Ana Costa", 2023005),
            new Aluno("Lucas Almeida", 2023006),
            new Aluno("Carla Souza", 2023007),
            new Aluno("Bruno Lima", 2023008),
            new Aluno("Julia Rocha", 2023009),
            new Aluno("Gabriel Cruz", 2023010)
        };

        for (Aluno a : alunos) db.salvarAluno(a);

        // --- 5. MATRÍCULAS E NOTAS INICIAIS (EXEMPLOS) ---
        // Aluno 1 matriculado em 3 coisas com algumas notas
        registrarMatricula(alunos[0], d1, db); // POO
        registrarMatricula(alunos[0], d2, db); // LS
        registrarMatricula(alunos[0], e1, db); // Estágio Google
        
        // Lançando notas parciais para teste de histórico serrilhado/formatado
        alunos[0].getInscricoes().get(0).addNota(8.0); // Nota 1 de POO
        alunos[0].getInscricoes().get(1).addNota(7.5); // Nota 1 de LS
        alunos[0].getInscricoes().get(1).addNota(9.0); // Nota 2 de LS

        // Outros alunos matriculados aleatoriamente
        registrarMatricula(alunos[1], d1, db);
        registrarMatricula(alunos[2], d3, db);
        registrarMatricula(alunos[3], d4, db);
        registrarMatricula(alunos[4], e2, db);
    }

    private static void registrarMatricula(Aluno a, ComponenteFormativo c, GerenciadorDados db) {
        Inscricao insc = new Inscricao(a, c, 2024);
        a.adicionarInscricao(insc);
        c.inscreverAluno(insc);
    }
}