package br.com.itau.geradornotafiscal.application.ports.in;

import br.com.itau.geradornotafiscal.application.core.domain.NotaFiscal;
import br.com.itau.geradornotafiscal.application.core.domain.Pedido;

public interface GeradorDeNotaFiscalRequestPort {
    public NotaFiscal gerarNotaFiscal(Pedido pedido);
}
