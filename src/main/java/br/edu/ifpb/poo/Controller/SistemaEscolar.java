package br.edu.ifpb.poo.Controller;

import br.edu.ifpb.poo.Model.Aluno;
import br.edu.ifpb.poo.Model.ComponenteFormativo;
import br.edu.ifpb.poo.Model.Disciplina;
import br.edu.ifpb.poo.Model.Enums.ModalidadeDisciplina;
import br.edu.ifpb.poo.Model.Estagio;
import br.edu.ifpb.poo.Model.Inscricao;
import br.edu.ifpb.poo.Model.Professor;
import br.edu.ifpb.poo.Persistence.CargaDados;
import br.edu.ifpb.poo.Persistence.GerenciadorDados;
import br.edu.ifpb.poo.View.Console;
import br.edu.ifpb.poo.View.SistemaUI;

public class SistemaEscolar {

    private GerenciadorDados db = new GerenciadorDados();
    private Console console = new Console();
    private SistemaUI ui = new SistemaUI(console);

    public void iniciar() {
        CargaDados.popular(this.db);

        int opcao;
        do {
            ui.limparTela();
            opcao = ui.menu();
            processarOpcao(opcao);
            if (opcao != 1) {
                System.out.println("\nPressione ENTER para continuar...");
                console.ler();
            }
        } while (opcao != 1);
    }

    private void processarOpcao(int op) {
        switch (op) {
            case 2 ->
                cadastrarAluno();
            case 3 ->
                apagarAluno();
            case 4 ->
                ui.listarEntidades("Alunos", db.getAlunos());

            case 5 ->
                cadastrarProfessor();
            case 6 ->
                apagarProfessor();
            case 7 ->
                ui.listarEntidades("Professores", db.getProfessores());

            case 8 ->
                cadastrarComponente();
            case 9 ->
                apagarComponente();
            case 10 ->
                ui.listarEntidades("Componentes", db.getComponentes());

            case 11 ->
                matricularAluno();
            case 12 ->
                desmatricularAluno();

            case 13 ->
                registrarNota();
            case 14 ->
                apagarNotas(); // Nova funcionalidade
            case 15 ->
                exibirHistoricoAluno();

            default -> {
                if (op != 1) {
                    console.logErro("Opção Inválida!");
                }
            }
        }
    }

    private void cadastrarAluno() {
        Aluno novo = ui.lerAluno();

        // Só prossegue se o aluno não for nulo (ou seja, se a digitação foi válida)
        if (novo != null) {
            if (db.getAlunos().stream().anyMatch(a -> a.getMatricula() == novo.getMatricula())) {
                console.logErro("Matrícula duplicada!");
            } else {
                db.salvarAluno(novo);
                console.logSucesso("Aluno cadastrado!");
            }
        }
    }

    private void apagarAluno() {
        Aluno aluno = ui.selecionarDaLista("Aluno para Apagar", db.getAlunos());
        if (aluno != null) {
            // Remove o aluno de todos os componentes onde ele estava inscrito
            for (Inscricao insc : aluno.getInscricoes()) {
                insc.getComponenteFormativo().desinscreverAluno((int) aluno.getMatricula());
            }
            db.removerAluno(aluno);
            console.logSucesso("Aluno e todos os seus vínculos foram apagados.");
        }
    }

    private void cadastrarProfessor() {
        Professor p = ui.lerProfessor();

        if (p != null) {
            if (db.getProfessores().stream().anyMatch(prof -> prof.getMatricula() == p.getMatricula())) {
                console.logErro("Professor já cadastrado com esta matrícula!");
            } else {
                db.salvarProfessor(p);
                console.logSucesso("Professor cadastrado!");
            }
        }
    }

    private void apagarProfessor() {
        Professor prof = ui.selecionarDaLista("Professor para Apagar", db.getProfessores());
        if (prof != null) {
            // Antes de apagar, removemos o vínculo com as disciplinas que ele leciona [cite: 165]
            for (ComponenteFormativo comp : db.getComponentes()) {
                if (comp.getProfessor().equals(prof)) {
                    comp.setProfessor(null);
                }
            }
            db.removerProfessor(prof);
            console.logSucesso("Professor removido do sistema.");
        }
    }

    // Arquivo: src/main/java/br/edu/ifpb/poo/Controller/SistemaEscolar.java
    private void cadastrarComponente() {
        int tipo = ui.escolherTipoComponente();

        if (tipo != 1 && tipo != 2) {
            console.logErro("Tipo inválido!");
            return;
        }

        // 1. Dados Comuns (UC03 e UC04)
        Professor prof = ui.selecionarDaLista("Professor Responsável", db.getProfessores());
        if (prof == null) {
            return;
        }

        String codigo = ui.lerTexto("Código");
        String nome = ui.lerTexto("Nome/Descrição");

        // Verificar duplicidade de código
        if (db.getComponentes().stream().anyMatch(c -> c.getCodigo().equalsIgnoreCase(codigo))) {
            console.logErro("Erro: Código já cadastrado!");
            return;
        }

        ComponenteFormativo novoComponente = null;

        if (tipo == 1) { // Lógica para Disciplina
            ModalidadeDisciplina mod = ui.selecionarModalidade();
            int qtdAval = ui.lerInteiro("Quantidade de Avaliações (mín. 2)");
            if (qtdAval < 2) {
                console.logErro("Disciplinas exigem no mínimo 2 avaliações.");
                return;
            }
            novoComponente = new Disciplina(codigo, nome, prof, mod, qtdAval);
        } else { // Lógica para Estágio
            String instituicao = ui.lerTexto("Instituição/Empresa");
            novoComponente = new Estagio(codigo, nome, prof, instituicao);
        }

        // Salvar e vincular
        db.salvarComponente(novoComponente);
        prof.addAtribuicao(novoComponente);
        console.logSucesso("Componente cadastrado com sucesso!");
    }

    private void apagarComponente() {
        ComponenteFormativo comp = ui.selecionarDaLista("Componente para Apagar", db.getComponentes());
        if (comp != null) {
            // Remove a referência no professor e limpa as inscrições dos alunos
            if (comp.getProfessor() != null) {
                comp.getProfessor().removeAtribuicao(comp.getCodigo());
            }
            for (Inscricao insc : comp.getInscricoes()) {
                insc.getAluno().removerInscricao(comp.getCodigo());
            }
            db.removerComponente(comp);
            console.logSucesso("Componente acadêmico removido.");
        }
    }

    private void matricularAluno() {
        // UC05/UC06: Seleção por número conforme solicitado
        Aluno aluno = ui.selecionarDaLista("Aluno para matricular", db.getAlunos());
        ComponenteFormativo comp = ui.selecionarDaLista("Componente", db.getComponentes());

        if (aluno != null && comp != null) {
            Inscricao insc = new Inscricao(aluno, comp, 2024);
            aluno.adicionarInscricao(insc);
            comp.inscreverAluno(insc);
            console.logSucesso("Matrícula realizada com sucesso!");
        }
    }

    private void desmatricularAluno() {
        Aluno aluno = ui.selecionarDaLista("Aluno", db.getAlunos());
        if (aluno == null) {
            return;
        }

        // Seleciona a inscrição específica do aluno para remover
        Inscricao insc = ui.selecionarDaLista("Inscrição para Remover", aluno.getInscricoes());
        if (insc != null) {
            // Remove dos dois lados (Aluno e Componente) conforme as regras de negócio [cite: 164]
            aluno.removerInscricao(insc.getComponenteFormativo().getCodigo());
            insc.getComponenteFormativo().desinscreverAluno((int) aluno.getMatricula());
            console.logSucesso("Aluno desmatriculado com sucesso!");
        }
    }

    private void registrarNota() {
        // 1. Seleciona o Aluno
        Aluno aluno = ui.selecionarDaLista("Aluno", db.getAlunos());
        if (aluno == null) {
            return;
        }

        // 2. Seleciona a Inscrição (Aqui o toString do componente será usado)
        // Dica: Se a classe Inscricao não tiver toString, ela usará o do Componente
        Inscricao insc = ui.selecionarDaLista("Matrícula de " + aluno.getNome(), aluno.getInscricoes());

        if (insc != null) {
            int atual = insc.getNotas().size() + 1;
            int total = insc.getComponenteFormativo().getQtdAvaliacoes();

            console.logInfo(String.format("\nLançando nota %d de %d para: %s",
                    atual, total, insc.getComponenteFormativo().getNome()));

            Double nota = ui.lerNota();
            if (nota != null) {
                insc.addNota(nota);
                console.logSucesso("Nota registrada com sucesso!");
            }
        }
    }

    private void apagarNotas() {
        Aluno aluno = ui.selecionarDaLista("Aluno", db.getAlunos());
        if (aluno == null) {
            return;
        }

        Inscricao insc = ui.selecionarDaLista("Componente para limpar notas", aluno.getInscricoes());
        if (insc != null) {
            if (insc.getNotas().isEmpty()) {
                console.logErro("Este aluno não possui notas registradas nesta disciplina.");
                return;
            }

            // Pergunta confirmação (opcional, mas seguro)
            console.logInfo("Tem certeza que deseja apagar TODAS as notas de " + aluno.getNome() + "?");
            System.out.println("01 > Sim | 02 > Não");
            if (ui.lerInteiro("Opção") == 1) {
                insc.getNotas().clear(); // Limpa a lista de notas da inscrição
                console.logSucesso("Notas removidas com sucesso!");
            }
        }
    }

    // Dentro de br.edu.ifpb.poo.Controller.SistemaEscolar
    private void exibirHistoricoAluno() {
        // 1. O usuário escolhe o aluno de uma lista numerada (sua exigência)
        Aluno alunoSelecionado = ui.selecionarDaLista("Selecione o Aluno para consulta", db.getAlunos());

        if (alunoSelecionado != null) {
            // 2. Chama a View para exibir os dados internos do objeto Aluno
            ui.exibirHistoricoAluno(alunoSelecionado);
        } else {
            console.logErro("Operação cancelada ou aluno não encontrado.");
        }
    }
}
