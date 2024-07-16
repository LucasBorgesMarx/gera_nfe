package br.com.itau.geradornotafiscal.application.core.domain;


import br.com.itau.geradornotafiscal.application.core.enuns.EFinalidade;
import br.com.itau.geradornotafiscal.application.core.enuns.ERegiao;

public class Endereco {

    public Endereco() {
    }

    public Endereco(String cep, String logradouro, String numero, String estado, String complemento, EFinalidade EFinalidade, ERegiao ERegiao) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.estado = estado;
        this.complemento = complemento;
        this.EFinalidade = EFinalidade;
        this.ERegiao = ERegiao;
    }

    private String cep;

    private String logradouro;

    private String numero;

    private String estado;

    private String complemento;

    private EFinalidade EFinalidade;

    private ERegiao ERegiao;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public EFinalidade getFinalidade() {
        return EFinalidade;
    }

    public void setFinalidade(EFinalidade EFinalidade) {
        this.EFinalidade = EFinalidade;
    }

    public ERegiao getRegiao() {
        return ERegiao;
    }

    public void setRegiao(ERegiao ERegiao) {
        this.ERegiao = ERegiao;
    }
}