package br.com.itau.geradornotafiscal.application.ports.out;


import br.com.itau.geradornotafiscal.application.core.domain.NotaFiscal;
import br.com.itau.geradornotafiscal.application.core.domain.Pedido;

public interface GeradorNotaFiscalPort {

	public NotaFiscal gerarNotaFiscal(Pedido pedido);
	
}
