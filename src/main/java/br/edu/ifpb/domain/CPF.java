package br.edu.ifpb.domain;

public class CPF {

    // 11122233344
    private static final int TAMANHO = 11;
    private String numero;

    public CPF() {
    }

    public CPF(String numero) {
        this.numero = numero;
    }

    public boolean valido() {
        return this.numero.length() == TAMANHO;
    }

    // 111.222.333-44
    public String formatado() {
        return String.format(
            "%s.%s.%s-%s",
            this.numero.substring(0,3), // 111222
            this.numero.substring(3,6),
            this.numero.substring(6,9),
            this.numero.substring(9,11)
        );
    }

    @Override
    public String toString() {
        return formatado();
    }

    public String valor() {
        return this.numero;
    }
}
