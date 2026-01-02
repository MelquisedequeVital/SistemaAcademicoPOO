package br.edu.ifpb.poo.Model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Estagio extends ComponenteFormativo{
    
    @Getter @Setter
    private String instituicao;
    @Getter @Setter
    private Double media;

    public Estagio(String cod, String nome, Professor prof, String instituicao){
        super(cod, nome, prof);
        this.instituicao = instituicao;
    }

    
    @Override
    public Double calcularMediaFinal(List<Double> notas){
        return notas.get(0);
    }

}
