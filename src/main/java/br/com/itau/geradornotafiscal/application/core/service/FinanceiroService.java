package br.com.itau.geradornotafiscal.application.core.service;


import br.com.itau.geradornotafiscal.application.core.domain.NotaFiscal;

public class FinanceiroService {
    public void enviarNotaFiscalParaContasReceber(NotaFiscal notaFiscal) {

        try {
            //Simula o envio da nota fiscal para o contas a receber
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
