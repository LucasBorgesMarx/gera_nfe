package br.com.itau.geradornotafiscal.adpters.imbound.request;

import br.com.itau.geradornotafiscal.adpters.entity.PedidoEntity;

import br.com.itau.geradornotafiscal.adpters.imbound.mapper.PedidoToPedidoEntity;
import br.com.itau.geradornotafiscal.adpters.outBound.response.GeradorNFResponse;
import br.com.itau.geradornotafiscal.application.ports.in.GeradorDeNotaFiscalRequestPort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/pedido")
public class GeradorNFRest {


    private final GeradorDeNotaFiscalRequestPort notaFiscalService;
    private final PedidoToPedidoEntity pedidoToPedidoEntity;
    private final GeradorNFResponse geradorNFResponse;

    public GeradorNFRest(GeradorDeNotaFiscalRequestPort notaFiscalService, PedidoToPedidoEntity pedidoToPedidoEntity, GeradorNFResponse geradorNFResponse) {
        this.notaFiscalService = notaFiscalService;
        this.pedidoToPedidoEntity = pedidoToPedidoEntity;
        this.geradorNFResponse = geradorNFResponse;
    }

    @PostMapping("/gerarNotaFiscal")
    public void gerarNotaFiscal(@RequestBody PedidoEntity pedidoEntity) {
        // Lógica de processamento do pedidoEntity
        var pedido = pedidoToPedidoEntity.mapper(pedidoEntity);
        // Aqui você pode realizar as operações desejadas com o objeto PedidoEntity
       var nota = notaFiscalService.gerarNotaFiscal(pedido);

       geradorNFResponse.gerarNotaFiscal(nota); // Aqui você pode realizar as operações desejadas com o objeto NotaFiscal
    }

}
