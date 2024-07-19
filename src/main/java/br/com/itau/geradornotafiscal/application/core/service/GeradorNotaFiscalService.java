package br.com.itau.geradornotafiscal.application.core.service;

import br.com.itau.geradornotafiscal.application.core.domain.*;
import br.com.itau.geradornotafiscal.application.core.enuns.EFinalidade;
import br.com.itau.geradornotafiscal.application.core.enuns.ERegiao;
import br.com.itau.geradornotafiscal.application.ports.in.GeradorDeNotaFiscalRequestPort;
import br.com.itau.geradornotafiscal.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GeradorNotaFiscalService implements GeradorDeNotaFiscalRequestPort {

    @Override
    public NotaFiscal gerarNotaFiscal(Pedido pedido) {

        Destinatario destinatario = pedido.getDestinatario();

        //Regras diferentes para frete

        ERegiao regiao = destinatario.getEnderecos().stream()
                .filter(endereco -> endereco.getFinalidade() == EFinalidade.ENTREGA || endereco.getFinalidade() == EFinalidade.COBRANCA_ENTREGA)
                .map(Endereco::getRegiao)
                .findFirst()
                .orElse(null);


        double valorFreteComPercentual = Config.getValorFreteComPercentual(pedido, regiao);

        // Create the NotaFiscalEntity object

        var notaFiscal = GeraNfeService.gerandoNfe(pedido, valorFreteComPercentual);


        new EstoqueService().enviarNotaFiscalParaBaixaEstoque(notaFiscal);
        new RegistroService().registrarNotaFiscal(notaFiscal);
        new EntregaService().agendarEntrega(notaFiscal);
        new FinanceiroService().enviarNotaFiscalParaContasReceber(notaFiscal);
        new NotaFiscalRetService().gerarNotaFiscalResponse(notaFiscal);

        return notaFiscal;
    }


}