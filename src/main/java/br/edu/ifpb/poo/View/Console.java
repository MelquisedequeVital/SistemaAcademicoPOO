package br.edu.ifpb.poo.View;

import java.util.Scanner;

public class Console {
    private Scanner scanner = new Scanner(System.in);

    public void limpar() {
        System.out.print(Cores.CLEAR);
        System.out.flush();
    }

   
    public void logSucesso(String texto) {
        System.out.println(Cores.SUCESSO + "[V] " + texto + Cores.RESET);
    }

    
    public void logErro(String texto) {
        System.out.println(Cores.ERRO + "[X] " + texto + Cores.RESET);
    }

    
    public void logInfo(String texto) {
        System.out.println(Cores.INFO + texto + Cores.RESET);
    }

  
    public String ler() {
        return scanner.nextLine();
    }


    public void imprimir(String texto) {
        System.out.print(texto);
    }
}
