package br.com.itau.geradornotafiscal.application.ports.out;


import br.com.itau.geradornotafiscal.adpters.entity.NotaFiscalEntity;
import br.com.itau.geradornotafiscal.application.core.domain.NotaFiscal;


public interface GeradorNotaFiscalResponsePort {

    public NotaFiscal gerarNotaFiscalResponse(NotaFiscal notaFiscal);

}
