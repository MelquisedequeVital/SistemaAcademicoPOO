package br.edu.ifpb.poo.View;

import java.util.Scanner;

public class Console {
    private Scanner scanner = new Scanner(System.in);

    public void imprimir(String texto) {
        System.out.print(texto);
    }

    public void pularLinha(String texto) {
        System.out.println(texto);
    }

    public String lerString() {
        return scanner.nextLine();
    }
}
