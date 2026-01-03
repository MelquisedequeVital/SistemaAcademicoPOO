package br.edu.ifpb.poo.Model;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.poo.Model.Enum.SituacaoInscricao;
import static br.edu.ifpb.poo.Model.Enum.SituacaoInscricao.EM_CURSO;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Inscricao {

    private final Aluno aluno;
    private final ComponenteFormativo componenteFormativo;
    @Setter
    private List<Double> notas;
    private SituacaoInscricao statusAluno;
    private String id;

    public Inscricao(Aluno aluno, ComponenteFormativo componenteFormativo) {
        this.aluno = aluno;
        this.componenteFormativo = componenteFormativo;

        //mudar regra para evitar duas inscrições com idS iguais
        this.id = aluno.getMatricula() + componenteFormativo.getCodigo();
        this.notas = new ArrayList<>();
        this.statusAluno = EM_CURSO;
    }

    public void addNota(Double nota) {
        if (nota != null) {
            if (notas.size() < componenteFormativo.getQtdAvaliacoes()) {
                notas.add(nota);
                atualizarStatusAluno();
            }
        }
    }

    public void removeNota(int posicao) {
        if (posicao >= 0 && posicao < notas.size()) {
            notas.remove(posicao);
            atualizarStatusAluno();
        }

    }

    public void atualizarNota(int posicao, Double novaNota) {
        if (posicao >= 0 && posicao < notas.size() && novaNota != null) {
            notas.set(posicao, novaNota);
            atualizarStatusAluno();
        }
    }

    private void atualizarStatusAluno() {
        Double media = obterMediaFinal();
        this.statusAluno = componenteFormativo.verificarSituacao(media, notas.size());
    }

    //To-do: a Inscricao poderia apenas fornecer os dados e o ComponenteFormativo retornar um objeto de "Resultado" que contém tanto a média quanto o status, reduzindo as chamadas de ida e volta entre as classes.
    public Double obterMediaFinal() {
        return componenteFormativo.calcularMediaFinal(notas);
    }

    public List<Double> getNotas() {
        return new ArrayList<>(this.notas);
    }


    public String obterStringAluno() {
        return String.format("Aluno: %s | Média: %.2f | Status: %s",
                aluno.toString(), obterMediaFinal(), statusAluno);
    }

    public String obterStringComponenteFormativo() {
        return String.format("Disciplina: %s | Status: %s",
                componenteFormativo.toString(), statusAluno);
    }

    @Override
    public String toString() {
        return "[" + this.id + "]" + "Aluno: " + aluno.toString() + " | " + "Componente Formativo: " + componenteFormativo.toString();
    }

}
