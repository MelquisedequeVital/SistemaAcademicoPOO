package br.edu.ifpb.poo.Model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Professor {
    private int matricula;
    private String nome;
    private List<ComponenteFormativo> atribuicoes;

    public Professor(int matricula, String nome){
        this.matricula = matricula;
        this.nome = nome;
        this.atribuicoes = new ArrayList<>();
    }

    public void addAtribuicao(ComponenteFormativo atribuicao){
        this.atribuicoes.add(atribuicao);
        atribuicao.setProfessor(this);
    }
    
    public void removeAtribuicao(String codigo){
        ComponenteFormativo atribuicao = atribuicoes.stream()
                .filter(atr -> atr.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
        
        if(atribuicao != null){
            this.atribuicoes.remove(atribuicao);
            atribuicao.setProfessor(null);
        }
        
    }
    
    @Override
    public String toString(){
        return nome + "(" + matricula + ")";
    }
}
