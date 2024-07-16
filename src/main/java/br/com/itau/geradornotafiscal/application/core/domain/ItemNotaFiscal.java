package br.com.itau.geradornotafiscal.application.core.domain;


import java.util.List;

public class ItemNotaFiscal {

    public ItemNotaFiscal() {
    }

    public ItemNotaFiscal(String idItem, String descricao, double valorUnitario, int quantidade, double valorTributoItem) {
        this.idItem = idItem;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.valorTributoItem = valorTributoItem;
    }

    private String idItem;

    private String descricao;

    private double valorUnitario;

    private int quantidade;

    private double valorTributoItem;

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTributoItem() {
        return valorTributoItem;
    }

    public void setValorTributoItem(double valorTributoItem) {
        this.valorTributoItem = valorTributoItem;
    }
}