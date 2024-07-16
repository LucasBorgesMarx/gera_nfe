package br.com.itau.geradornotafiscal.application.core.service;

import br.com.itau.geradornotafiscal.application.core.domain.NotaFiscal;

public class EntregaService {
    public void agendarEntrega(NotaFiscal notaFiscal) {

            try {
                //Simula o agendamento da entrega
                Thread.sleep(150);
                new EntregaIntegrationService().criarAgendamentoEntrega(notaFiscal);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

    }
}
