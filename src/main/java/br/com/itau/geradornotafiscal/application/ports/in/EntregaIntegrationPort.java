package br.com.itau.geradornotafiscal.application.ports.in;


import br.com.itau.geradornotafiscal.application.core.domain.NotaFiscal;

public interface EntregaIntegrationPort {
    public void criarAgendamentoEntrega(NotaFiscal notaFiscal);
}
