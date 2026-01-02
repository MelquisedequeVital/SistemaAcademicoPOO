package br.edu.ifpb.poo.Model;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.poo.Model.Enum.SituacaoInscricao;
import static br.edu.ifpb.poo.Model.Enum.SituacaoInscricao.APROVADO;
import static br.edu.ifpb.poo.Model.Enum.SituacaoInscricao.FINAL;
import static br.edu.ifpb.poo.Model.Enum.SituacaoInscricao.REPROVADO;
import lombok.Data;

@Data
public abstract class ComponenteFormativo {

    private String codigo;
    private String nome;
    private Professor professor;
    private List<Inscricao> inscricoes;
    protected static final int NOTA_MINIMA_APROVACAO = 7;
    protected static final int NOTA_MINIMA_FINAL = 4;

    public ComponenteFormativo(String codigo, String nome, Professor prof) {
        this.codigo = codigo;
        this.nome = nome;
        this.professor = prof;
        this.inscricoes = new ArrayList<>();
    }

    public void inscreverAluno(Inscricao insc) {
        inscricoes.add(insc);
    }

    public void desinscreverAluno(int matricula) {
        Inscricao inscricaoEncontrada = inscricoes.stream()
                .filter(insc -> insc.getAluno().getMatricula() == matricula)
                .findFirst()
                .orElse(null);

        if (inscricaoEncontrada != null) {
            inscricoes.remove(inscricaoEncontrada);
        }
    }

    public void setProfessor(Professor professor){
        this.professor = professor;
        professor.addAtribuicao(this);
    }
    
    //to-do: Adicionar logica da quantidade de notas
    public SituacaoInscricao verificarSituacao(Double media, int qtdNotas){
        SituacaoInscricao situacaoAtual;

        if(media >= NOTA_MINIMA_APROVACAO){
            situacaoAtual = APROVADO;
        } else if(media >= NOTA_MINIMA_FINAL){
            situacaoAtual = FINAL;
        } else {
            situacaoAtual = REPROVADO;
        }
        
        return situacaoAtual;

    }
    public abstract Double calcularMediaFinal(List<Double> notas);
}
