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

    // Arquivo: src/main/java/br/edu/ifpb/poo/Model/Estagio.java
    @Override
    public Double calcularMediaFinal(List<Double> notas) {
        if (notas != null && !notas.isEmpty()) {
            return notas.get(0);
        }
        return 0.0; // Retorna 0 em vez de estourar erro se não houver nota
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - Inst: %s", getCodigo(), getNome(), instituicao);
    }

}
