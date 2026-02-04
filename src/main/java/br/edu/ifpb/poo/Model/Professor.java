package br.edu.ifpb.poo.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Professor {

    private long matricula; 
    private String nome;
    private List<ComponenteFormativo> atribuicoes;

    // Construtor atualizado
    public Professor(long matricula, String nome) { 
        this.matricula = matricula;
        this.nome = nome;
        this.atribuicoes = new ArrayList<>();
    }

    public void addAtribuicao(ComponenteFormativo atribuicao) {
        this.atribuicoes.add(atribuicao);
    }

    public void removeAtribuicao(String codigo) {
        ComponenteFormativo atribuicao = atribuicoes.stream()
                .filter(atr -> atr.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);

        if (atribuicao != null) {
            this.atribuicoes.remove(atribuicao);
            atribuicao.setProfessor(null);
        }

    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        if (!(obj instanceof Professor)) {
            return false;
        }

        Professor professor = (Professor) obj;

        return this.matricula == professor.matricula;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return nome + "(" + matricula + ")";
    }
}