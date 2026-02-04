package br.edu.ifpb.poo.Model;

import java.util.List;

import br.edu.ifpb.poo.Model.Enums.ModalidadeDisciplina;
import lombok.Getter;
import lombok.Setter;

public class Disciplina extends ComponenteFormativo {

    @Getter
    @Setter
    private ModalidadeDisciplina modalidade;

    public Disciplina(String cod, String nome, Professor prof, ModalidadeDisciplina mod, int qtdAval) {
        super(cod, nome, prof, qtdAval);
        this.modalidade = mod;
    }

  
    @Override
    public Double calcularMediaFinal(List<Double> notas) {
        if (notas == null || notas.isEmpty()) {
            return 0.0;
        }

        Double somaNotas = 0.0;
        for (Double nota : notas) {
            somaNotas += nota;
        }

        return somaNotas / notas.size();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s)", getCodigo(), getNome(), modalidade);
    }

}
