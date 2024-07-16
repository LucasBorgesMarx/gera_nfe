package br.com.itau.geradornotafiscal.application.core.domain;


import java.time.LocalDateTime;
import java.util.List;


public class NotaFiscal {

    public NotaFiscal() {
    }

    public NotaFiscal(String idNotaFiscal, LocalDateTime data, double valorTotalItens, double valorFrete, List<ItemNotaFiscal> itens, Destinatario destinatario) {
        this.idNotaFiscal = idNotaFiscal;
        this.data = data;
        this.valorTotalItens = valorTotalItens;
        this.valorFrete = valorFrete;
        this.itens = itens;
        this.destinatario = destinatario;
    }

    private String idNotaFiscal;

    private LocalDateTime data;

    private double valorTotalItens;

    private double valorFrete;

    private List<ItemNotaFiscal> itens;

    private Destinatario destinatario;

    public String getIdNotaFiscal() {
        return idNotaFiscal;
    }

    public void setIdNotaFiscal(String idNotaFiscal) {
        this.idNotaFiscal = idNotaFiscal;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
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

    public List<ItemNotaFiscal> getItens() {
        return itens;
    }

    public void setItens(List<ItemNotaFiscal> itens) {
        this.itens = itens;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
    }
}