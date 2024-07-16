package br.com.itau.geradornotafiscal.application.core.service;

import br.com.itau.geradornotafiscal.application.core.domain.*;
import br.com.itau.geradornotafiscal.application.core.enuns.EFinalidade;
import br.com.itau.geradornotafiscal.application.core.enuns.ERegiao;
import br.com.itau.geradornotafiscal.application.core.enuns.ERegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.application.core.enuns.ETipoPessoa;
import br.com.itau.geradornotafiscal.application.ports.out.GeradorNotaFiscalPort;
import br.com.itau.geradornotafiscal.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class GeradorNotaFiscalPortImpl implements GeradorNotaFiscalPort {


    private final Config config;

    public GeradorNotaFiscalPortImpl(Config config) {
        this.config = config;
    }

    @Override
    public NotaFiscal gerarNotaFiscal(Pedido pedido) {

        Destinatario destinatario = pedido.getDestinatario();

      var itemNotaFiscalList = new VerificaAliquotaTipoPessoa();

            //Regras diferentes para frete

        ERegiao regiao = destinatario.getEnderecos().stream()
                .filter(endereco -> endereco.getFinalidade() == EFinalidade.ENTREGA || endereco.getFinalidade() == EFinalidade.COBRANCA_ENTREGA)
                .map(Endereco::getRegiao)
                .findFirst()
                .orElse(null);


        double valorFreteComPercentual = Config.getValorFreteComPercentual(pedido, regiao);

        // Create the NotaFiscalEntity object
        String idNotaFiscal = UUID.randomUUID().toString();

        NotaFiscal notaFiscal = new NotaFiscal(
                idNotaFiscal,
                LocalDateTime.now(),
                pedido.getValorTotalItens(),
                valorFreteComPercentual,
                itemNotaFiscalList.verficaAliquotaTitpoPessoaService(pedido),
                pedido.getDestinatario()
        );



        new EstoqueService().enviarNotaFiscalParaBaixaEstoque(notaFiscal);
        new RegistroService().registrarNotaFiscal(notaFiscal);
        new EntregaService().agendarEntrega(notaFiscal);
        new FinanceiroService().enviarNotaFiscalParaContasReceber(notaFiscal);

        return notaFiscal;
    }


}