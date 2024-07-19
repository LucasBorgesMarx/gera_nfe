package br.com.itau.geradornotafiscal.adpters.outBound.response;

import br.com.itau.geradornotafiscal.adpters.entity.NotaFiscalEntity;
import br.com.itau.geradornotafiscal.adpters.entity.PedidoEntity;
import br.com.itau.geradornotafiscal.adpters.outBound.mapper.NotaFiscalToNotaFiscalEntity;
import br.com.itau.geradornotafiscal.application.core.domain.NotaFiscal;
import br.com.itau.geradornotafiscal.application.ports.in.GeradorDeNotaFiscalRequestPort;
import br.com.itau.geradornotafiscal.application.ports.out.GeradorNotaFiscalResponsePort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
@RestController
public class GeradorNFResponse {

    private final GeradorNotaFiscalResponsePort geradorNotaFiscalResponsePort;
    private final NotaFiscalToNotaFiscalEntity notaFiscalEntity;
    public GeradorNFResponse(GeradorNotaFiscalResponsePort geradorNotaFiscalResponsePort, NotaFiscalToNotaFiscalEntity notaFiscalEntity) {
        this.geradorNotaFiscalResponsePort = geradorNotaFiscalResponsePort;
        this.notaFiscalEntity = notaFiscalEntity;
    }


    public ResponseEntity<NotaFiscalEntity> gerarNotaFiscal(NotaFiscal notaFiscal) {

        return ResponseEntity.ok()
                .body(notaFiscalEntity.mapper(geradorNotaFiscalResponsePort.gerarNotaFiscalResponse(notaFiscal)));
    }

}
