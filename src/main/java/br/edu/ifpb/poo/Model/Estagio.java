package br.edu.ifpb.poo.Model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Estagio extends ComponenteFormativo {

    @Getter
    @Setter
    private String instituicao;
    @Getter
    @Setter
    private Double media;
    private static final int QTD_AVAL_ESTAGIO = 1;

    public Estagio(String cod, String nome, Professor prof, String instituicao) {

        super(cod, nome, prof, QTD_AVAL_ESTAGIO);
        this.instituicao = instituicao;
    }

    //to-do: Criação de tipo de erro para Notas(entre 0 e 10)
    @Override
    public Double calcularMediaFinal(List<Double> notas) {
        if(notas != null){
            return notas.get(0);
        } else {
            throw new IllegalArgumentException("Quantidade de Notas Insuficiente");
        }
        
    }

}
