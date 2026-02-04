package br.edu.ifpb.poo.View;

import java.util.List;

public class Menu {

    private String titulo;
    private List<String> itens;
    private final int LARGURA = 40;

    public Menu(String titulo, List<String> itens) {
        this.titulo = titulo;
        this.itens = itens;
    }

    public void exibir() {

        int larguraConteudo = titulo.length();
        for (String item : itens) {
            if (item.length() + 10 > larguraConteudo) {
                larguraConteudo = item.length() + 10;
            }
        }

        String borda = "#".repeat(larguraConteudo + 4);
        String formatoItem = "# %-" + (larguraConteudo) + "s #";

        System.out.println(Cores.INFO + borda + Cores.RESET);
        System.out.println(Cores.INFO + "# " + String.format("%-" + larguraConteudo + "s", titulo.toUpperCase()) + " #" + Cores.RESET);
        System.out.println(Cores.INFO + borda + Cores.RESET);

        for (int i = 0; i < itens.size(); i++) {
            String texto = String.format("%02d > %s", (i + 1), itens.get(i));
            System.out.print(Cores.INFO + "# " + Cores.RESET);

            if (i == 0) { // Destaque para SAIR
                System.out.print(Cores.ERRO + String.format("%-" + larguraConteudo + "s", texto) + Cores.RESET);
            } else {
                System.out.print(String.format("%-" + larguraConteudo + "s", texto));
            }
            System.out.println(Cores.INFO + " #" + Cores.RESET);
        }
        System.out.println(Cores.INFO + borda + Cores.RESET);
        System.out.print("Escolha uma opção: ");
    }
}
