package br.com.itau.geradornotafiscal.application.core.domain;


import java.time.LocalDate;
import java.util.List;


public class Pedido {

    public Pedido() {
    }

    public Pedido(int idPedido, LocalDate data, double valorTotalItens, double valorFrete, List<Item> itens, Destinatario destinatario) {
        this.idPedido = idPedido;
        this.data = data;
        this.valorTotalItens = valorTotalItens;
        this.valorFrete = valorFrete;
        this.itens = itens;
        this.destinatario = destinatario;
    }

    private int idPedido;

    private LocalDate data;

    private double valorTotalItens;

    private double valorFrete;

    private List<Item> itens;

    private Destinatario destinatario;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValorTotalItens() {
        return valorTotalItens;
    }

    public void setValorTotalItens(double valorTotalItens) {
        this.valorTotalItens = valorTotalItens;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
    }
}
