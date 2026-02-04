package br.edu.ifpb.poo.Persistence;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.poo.Model.Aluno;
import br.edu.ifpb.poo.Model.ComponenteFormativo;
import br.edu.ifpb.poo.Model.Professor;

public class GerenciadorDados {

    private List<Aluno> alunos = new ArrayList<>();
    private List<Professor> professores = new ArrayList<>();
    private List<ComponenteFormativo> componentes = new ArrayList<>();

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public List<ComponenteFormativo> getComponentes() {
        return componentes;
    }

    public void salvarAluno(Aluno a) {
        alunos.add(a);
    }

    public void salvarProfessor(Professor p) {
        professores.add(p);
    }

    public void salvarComponente(ComponenteFormativo c) {
        componentes.add(c);
    }

    public void removerAluno(Aluno a) {
        alunos.remove(a);
    }

    public void removerProfessor(Professor p) {
        professores.remove(p);
    }

    public void removerComponente(ComponenteFormativo c) {
        componentes.remove(c);
    }
}
