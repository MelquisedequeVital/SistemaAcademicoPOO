package br.edu.ifpb.poo.Model;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.poo.Model.Enum.SituacaoInscricao;
import static br.edu.ifpb.poo.Model.Enum.SituacaoInscricao.EM_CURSO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Data
public class Inscricao {
    

    private Aluno aluno;
    private ComponenteFormativo componenteFormativo;
    private List<Double> notas;

    @Setter(AccessLevel.NONE)
    private SituacaoInscricao statusAluno;
    @Setter(AccessLevel.NONE)
    private String id;

    public Inscricao(Aluno aluno, ComponenteFormativo componenteFormativo){
        this.aluno = aluno;
        this.componenteFormativo = componenteFormativo;
        this.id = aluno.getMatricula() + componenteFormativo.getCodigo();
        this.notas = new ArrayList<>();
        this.statusAluno = EM_CURSO;
    }


    public void addNota(Double nota){
        if(nota != null){
            notas.add(nota);
            atualizarStatusAluno();
        }
        
    }

    public void removeNota(int posicao){
        if(posicao >= 0 && posicao < notas.size()){
            notas.remove(posicao);
            atualizarStatusAluno();
        }
        
    }

    public void atualizarNota(int posicao, Double novaNota){
        if(posicao >= 0 && posicao < notas.size() && novaNota != null){
            notas.set(posicao, novaNota);
            atualizarStatusAluno();
        }
    }

    private void atualizarStatusAluno() {
        Double media = obterMediaFinal();
        this.statusAluno = componenteFormativo.verificarSituacao(media, notas.size());
    }


    public Double obterMediaFinal(){
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
    public String toString(){
        return "[" + this.id + "]" + "Aluno: " + aluno.toString() + " | " + "Componente Formativo: " + componenteFormativo.toString(); 
    }



}
