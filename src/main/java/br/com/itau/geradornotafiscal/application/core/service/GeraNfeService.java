package br.com.itau.geradornotafiscal.application.core.service;

import br.com.itau.geradornotafiscal.adpters.entity.NotaFiscalEntity;
import br.com.itau.geradornotafiscal.application.core.domain.NotaFiscal;
import br.com.itau.geradornotafiscal.application.core.domain.Pedido;
import br.com.itau.geradornotafiscal.application.ports.out.GeradorNotaFiscalResponsePort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


public class GeraNfeService  {

    public static NotaFiscal gerandoNfe(Pedido pedido, double valorFreteComPercentual) {
        var itemNotaFiscalList = new VerificaAliquotaTipoPessoa();
        String idNotaFiscal = UUID.randomUUID().toString();

        NotaFiscal notaFiscal = new NotaFiscal(
                idNotaFiscal,
                LocalDateTime.now(),
                pedido.getValorTotalItens(),
                valorFreteComPercentual,
                itemNotaFiscalList.verficaAliquotaTitpoPessoaService(pedido),
                pedido.getDestinatario()
        );

        return notaFiscal;
    }

}
