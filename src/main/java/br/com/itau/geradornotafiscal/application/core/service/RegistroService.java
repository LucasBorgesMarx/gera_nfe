package br.com.itau.geradornotafiscal.application.core.service;


import br.com.itau.geradornotafiscal.application.core.domain.NotaFiscal;

public  class RegistroService {
    public void registrarNotaFiscal(NotaFiscal notaFiscal) {

        try {
            //Simula o registro da nota fiscal
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
