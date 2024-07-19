package br.com.itau.geradornotafiscal.application.core.service;

import br.com.itau.geradornotafiscal.adpters.entity.NotaFiscalEntity;
import br.com.itau.geradornotafiscal.application.core.domain.NotaFiscal;
import br.com.itau.geradornotafiscal.application.ports.out.GeradorNotaFiscalResponsePort;
import org.springframework.stereotype.Service;

@Service
public class NotaFiscalRetService implements GeradorNotaFiscalResponsePort {


    @Override
    public NotaFiscal gerarNotaFiscalResponse(NotaFiscal notaFiscal) {
        return notaFiscal;
    }
}
