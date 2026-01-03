package br.edu.ifpb.poo.Model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Aluno {

    private String nome;
    private int matricula;
    private List<Inscricao> inscricoes;

    public Aluno(String nome, int matricula) {
        this.nome = nome;
        this.matricula = matricula;
        this.inscricoes = new ArrayList<>();
    }

    public void adicionarInscricao(Inscricao insc) {
        inscricoes.add(insc);
    }

    public void removerInscricao(String codigo) {
        Inscricao inscricaoEncontrada = inscricoes.stream()
                .filter(inscricao -> inscricao.getComponenteFormativo().getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);

        if (inscricaoEncontrada != null) {
            inscricoes.remove(inscricaoEncontrada);
        }
    }

    @Override
    public String toString() {
        return nome + "(" + matricula + ")";
    }

}
