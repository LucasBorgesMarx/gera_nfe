package br.com.itau.geradornotafiscal.application.core.service;

import br.com.itau.geradornotafiscal.application.core.domain.Destinatario;
import br.com.itau.geradornotafiscal.application.core.domain.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.application.core.domain.Pedido;
import br.com.itau.geradornotafiscal.application.core.enuns.ERegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.application.core.enuns.ETipoPessoa;

import java.util.ArrayList;
import java.util.List;

public class VerificaAliquotaTipoPessoa {


    public List<ItemNotaFiscal>  verficaAliquotaTitpoPessoaService(Pedido pedido) {

        Destinatario destinatario = pedido.getDestinatario();
        ETipoPessoa tipoPessoa = destinatario.getTipoPessoa();
        List<ItemNotaFiscal> itemNotaFiscalList = new ArrayList<>();

        CalculadoraAliquotaProduto calculadoraAliquotaProduto = new CalculadoraAliquotaProduto();


        if (tipoPessoa == ETipoPessoa.FISICA) {
            double valorTotalItens = pedido.getValorTotalItens();
            double aliquota;
            var verficaAliquotaTitpoPessoaService = destinatario.getTipoPessoa();
            if (valorTotalItens < 500) {
                aliquota = 0;
            } else if (valorTotalItens <= 2000) {
                aliquota = 0.12;
            } else if (valorTotalItens <= 3500) {
                aliquota = 0.15;
            } else {
                aliquota = 0.17;
            }


            itemNotaFiscalList = calculadoraAliquotaProduto.calcularAliquota(pedido.getItens(), aliquota);
        } else if (tipoPessoa == ETipoPessoa.JURIDICA) {

            ERegimeTributacaoPJ regimeTributacao = destinatario.getRegimeTributacao();

            if (regimeTributacao == ERegimeTributacaoPJ.SIMPLES_NACIONAL) {

                double valorTotalItens = pedido.getValorTotalItens();
                double aliquota;

                if (valorTotalItens < 1000) {
                    aliquota = 0.03;
                } else if (valorTotalItens <= 2000) {
                    aliquota = 0.07;
                } else if (valorTotalItens <= 5000) {
                    aliquota = 0.13;
                } else {
                    aliquota = 0.19;
                }


                //  ------------------------------------------------------
                itemNotaFiscalList = calculadoraAliquotaProduto.calcularAliquota(pedido.getItens(), aliquota);
            } else if (regimeTributacao == ERegimeTributacaoPJ.LUCRO_REAL) {
                double valorTotalItens = pedido.getValorTotalItens();
                double aliquota;

                if (valorTotalItens < 1000) {
                    aliquota = 0.03;
                } else if (valorTotalItens <= 2000) {
                    aliquota = 0.09;
                } else if (valorTotalItens <= 5000) {
                    aliquota = 0.15;
                } else {
                    aliquota = 0.20;
                }

                //  ------------------------------------------------------


                itemNotaFiscalList = calculadoraAliquotaProduto.calcularAliquota(pedido.getItens(), aliquota);

            } else if (regimeTributacao == ERegimeTributacaoPJ.LUCRO_PRESUMIDO) {
                double valorTotalItens = pedido.getValorTotalItens();
                double aliquota;

                if (valorTotalItens < 1000) {
                    aliquota = 0.03;
                } else if (valorTotalItens <= 2000) {
                    aliquota = 0.09;
                } else if (valorTotalItens <= 5000) {
                    aliquota = 0.16;
                } else {
                    aliquota = 0.20;
                }
                itemNotaFiscalList = calculadoraAliquotaProduto.calcularAliquota(pedido.getItens(), aliquota);
            }
        }

        return itemNotaFiscalList;
    }
}
