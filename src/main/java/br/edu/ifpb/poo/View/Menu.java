package br.edu.ifpb.poo.View;

import java.util.List;

public class Menu {
    private String titulo;
    private List<String> itens;
    private Console console;
    private final int LARGURA = 40; // Largura fixa do menu

    public Menu(String titulo, List<String> itens, Console console) {
        this.titulo = titulo;
        this.itens = itens;
        this.console = console;
    }

    public void exibir() {
    
        String bordaHorizontal = "#".repeat(LARGURA);
        
        console.pularLinha(bordaHorizontal);
        
        console.pularLinha(String.format("# %-36s #", titulo.toUpperCase()));
        
        console.pularLinha(bordaHorizontal);
        
        for (int i = 0; i < itens.size(); i++) {
            String textoItem = String.format("%02d > %s", (i + 1), itens.get(i));
            console.pularLinha(String.format("# %-36s #", textoItem));
        }
        
        console.pularLinha(bordaHorizontal);
        console.imprimir("Escolha uma opção: ");
    }
    
    public int lerOpcao() {
        try {
            return Integer.parseInt(console.lerString());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}