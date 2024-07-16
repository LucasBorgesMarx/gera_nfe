package br.com.itau.geradornotafiscal.application.core.service;

import br.com.itau.geradornotafiscal.application.core.domain.NotaFiscal;
import br.com.itau.geradornotafiscal.application.ports.in.EntregaIntegrationPort;

public class EntregaIntegrationService implements EntregaIntegrationPort {

    @Override
    public void criarAgendamentoEntrega(NotaFiscal notaFiscal) {

            try {
                //Simula a criação do agendamento de entrega
               Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}
