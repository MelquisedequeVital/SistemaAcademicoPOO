package br.edu.ifpb.poo.View;

public enum Cores {
    RESET("\u001B[0m"),
    INFO("\u001B[90m"),    
    SUCESSO("\u001B[32m"), 
    ERRO("\u001B[31m"),    
    CLEAR("\u001B[2J\u001B[H");

    private final String codigo;

    Cores(String codigo) {
        this.codigo = codigo;
    }


    @Override
    public String toString() {
        return this.codigo;
    }
}
