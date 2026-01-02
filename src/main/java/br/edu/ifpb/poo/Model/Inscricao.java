package br.edu.ifpb.poo.Model;

import java.util.List;

import br.edu.ifpb.poo.Model.Enum.SituacaoInscricao;
import static br.edu.ifpb.poo.Model.Enum.SituacaoInscricao.EM_CURSO;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class Inscricao {
    private String id;
    private Aluno aluno;
    private ComponenteFormativo componenteFormativo;
    private List<Double> notas;
    private SituacaoInscricao statusAluno;
    private Double media;

    public Inscricao(Aluno aluno, ComponenteFormativo componenteFormativo){
        this.aluno = aluno;
        this.componenteFormativo = componenteFormativo;
        id = aluno.getMatricula() + componenteFormativo.getCodigo();
        statusAluno = EM_CURSO;
    }


    public void addNota(Double nota){
        notas.add(nota);
    }






}
