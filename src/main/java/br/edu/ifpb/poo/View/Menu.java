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
        String borda = "#".repeat(LARGURA);

        // Borda superior em azul (INFO)
        System.out.println(Cores.INFO + borda + Cores.RESET);

        // Titulo com '#' azuis nas pontas
        System.out.print(Cores.INFO + "# " + Cores.RESET); // '#' da esquerda azul
        System.out.print(Cores.INFO + String.format("%-36s", titulo.toUpperCase()) + Cores.RESET); // Título cor padrão
        System.out.println(Cores.INFO + " #" + Cores.RESET); // '#' da direita azul

        System.out.println(Cores.INFO + borda + Cores.RESET);


        for (int i = 0; i < itens.size(); i++) {
            String texto = String.format("%02d > %s", (i + 1), itens.get(i));

            System.out.print(Cores.INFO + "# " + Cores.RESET);

            // Destaque para a opção 1 (Sair) em vermelho, as outras seguem padrão
            if (i == 0) {
                System.out.print(Cores.ERRO + String.format("%-36s", texto) + Cores.RESET);
            } else {
                System.out.print(String.format("%-36s", texto));
            }

            System.out.println(Cores.INFO + " #" + Cores.RESET);
        }

        System.out.println(Cores.INFO + borda + Cores.RESET);
        System.out.print("Escolha uma opção: ");
    }
}
