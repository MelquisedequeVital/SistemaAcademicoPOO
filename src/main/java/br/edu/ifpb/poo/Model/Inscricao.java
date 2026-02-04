package br.edu.ifpb.poo.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.edu.ifpb.poo.Model.Enums.SituacaoInscricao;
import static br.edu.ifpb.poo.Model.Enums.SituacaoInscricao.EM_CURSO;
import lombok.Getter;


@Getter
public class Inscricao {

    private final Aluno aluno;
    private final ComponenteFormativo componenteFormativo;
    private List<Double> notas;
    private final int periodo;
    private SituacaoInscricao statusAluno;
    private final String id;

    public Inscricao(Aluno aluno, ComponenteFormativo componenteFormativo, int periodo) {
        this.aluno = aluno;
        this.componenteFormativo = componenteFormativo;
        this.periodo = periodo;
        this.id = periodo + aluno.getMatricula() + componenteFormativo.getCodigo();
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

    //sugestão: a Inscricao poderia apenas fornecer os dados e o ComponenteFormativo retornar um objeto de "Resultado" que contém tanto a média quanto o status, reduzindo as chamadas de ida e volta entre as classes.
    public Double obterMediaFinal() {
        return componenteFormativo.calcularMediaFinal(notas);
    }

    public List<Double> getNotas() {
        return new ArrayList<>(this.notas);
    }

    
    public boolean verificaMatriculaPertenceAluno(int matricula) {
        return this.aluno.getMatricula() == matricula;
    }

    public boolean verificaCodigoPertenceComponente(String codigo) {
        return this.componenteFormativo.getCodigo().equals(codigo);
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
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        if (!(obj instanceof Inscricao)) {
            return false;
        }

        Inscricao inscricao = (Inscricao) obj;

        return this.id.equals(inscricao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "[" + this.id + "]" + "Aluno: " + aluno.toString() + " | " + "Componente Formativo: " + componenteFormativo.toString();
    }

}
