package br.com.itau.geradornotafiscal.adpters.imbound.request;

import br.com.itau.geradornotafiscal.adpters.entity.NotaFiscalEntity;
import br.com.itau.geradornotafiscal.adpters.entity.PedidoEntity;

import br.com.itau.geradornotafiscal.adpters.imbound.mapper.NotaFiscalToNotaFiscalEntity;
import br.com.itau.geradornotafiscal.adpters.imbound.mapper.PedidoToPedidoEntity;
import br.com.itau.geradornotafiscal.application.ports.out.GeradorNotaFiscalPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedido")
public class GeradorNFRest {


    private final GeradorNotaFiscalPort notaFiscalService;
    private final NotaFiscalToNotaFiscalEntity notaFiscalToNotaFiscalEntity;
    private final PedidoToPedidoEntity pedidoToPedidoEntity;


    public GeradorNFRest(GeradorNotaFiscalPort notaFiscalService, NotaFiscalToNotaFiscalEntity notaFiscalToNotaFiscalEntity, PedidoToPedidoEntity pedidoToPedidoEntity) {
        this.notaFiscalService = notaFiscalService;
        this.notaFiscalToNotaFiscalEntity = notaFiscalToNotaFiscalEntity;
        this.pedidoToPedidoEntity = pedidoToPedidoEntity;
    }

    @PostMapping("/gerarNotaFiscal")
    public ResponseEntity<NotaFiscalEntity> gerarNotaFiscal(@RequestBody PedidoEntity pedidoEntity) {
        // Lógica de processamento do pedidoEntity
        var pedido = pedidoToPedidoEntity.mapper(pedidoEntity);
        // Aqui você pode realizar as operações desejadas com o objeto PedidoEntity
        var notaFiscal = notaFiscalToNotaFiscalEntity.mapper(notaFiscalService.gerarNotaFiscal(pedido));
        // Exemplo de retorno

        notaFiscal.setMensagem("Nota fiscal gerada com sucesso para o pedidoEntity: " + pedidoEntity.getIdPedido());

        return new ResponseEntity<>(notaFiscal, HttpStatus.OK);
    }

}
