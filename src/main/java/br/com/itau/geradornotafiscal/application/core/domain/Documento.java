package br.com.itau.geradornotafiscal.application.core.domain;


import br.com.itau.geradornotafiscal.application.core.enuns.ETipoDocumento;

public class Documento {

    public Documento() {
    }

    public Documento(String numero, ETipoDocumento tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }

    private String numero;

    private ETipoDocumento tipo;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ETipoDocumento getTipo() {
        return tipo;
    }

    public void setTipo(ETipoDocumento tipo) {
        this.tipo = tipo;
    }
}
