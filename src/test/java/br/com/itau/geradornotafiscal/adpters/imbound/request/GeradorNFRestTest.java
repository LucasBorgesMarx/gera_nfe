package br.com.itau.geradornotafiscal.adpters.imbound.request;

import br.com.itau.geradornotafiscal.adpters.entity.PedidoEntity;
import br.com.itau.geradornotafiscal.adpters.imbound.mapper.PedidoToPedidoEntity;
import br.com.itau.geradornotafiscal.adpters.outBound.response.GeradorNFResponse;
import br.com.itau.geradornotafiscal.application.core.domain.Item;
import br.com.itau.geradornotafiscal.application.core.domain.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.application.core.domain.NotaFiscal;
import br.com.itau.geradornotafiscal.application.core.domain.Pedido;
import br.com.itau.geradornotafiscal.application.ports.in.GeradorDeNotaFiscalRequestPort;
import io.swagger.v3.core.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GeradorNFRest.class)
class GeradorNFRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeradorDeNotaFiscalRequestPort mockNotaFiscalService;
    @MockBean
    private PedidoToPedidoEntity mockPedidoToPedidoEntity;
    @MockBean
    private GeradorNFResponse mockGeradorNFResponse;

    @Test
    void testGerarNotaFiscal() throws Exception {
        // Setup
        // Configure PedidoToPedidoEntity.mapper(...).
        final Pedido pedido = new Pedido();
        pedido.setIdPedido(0);
        pedido.setData(LocalDate.of(2020, 1, 1));
        pedido.setValorTotalItens(0.0);
        pedido.setValorFrete(0.0);
        final Item item = new Item();
        pedido.setItens(List.of(item));
        when(mockPedidoToPedidoEntity.mapper(any(PedidoEntity.class))).thenReturn(pedido);

        // Configure GeradorDeNotaFiscalRequestPort.gerarNotaFiscal(...).
        final NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setIdNotaFiscal("idNotaFiscal");
        notaFiscal.setData(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        notaFiscal.setValorTotalItens(0.0);
        notaFiscal.setValorFrete(0.0);
        final ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal();
        notaFiscal.setItens(List.of(itemNotaFiscal));
        when(mockNotaFiscalService.gerarNotaFiscal(any(Pedido.class))).thenReturn(notaFiscal);


        // Run the test and verify the results
        mockMvc.perform(post("/api/pedido/gerarNotaFiscal")
                        .content(Json.pretty(pedido))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()) // Isso imprime a resposta no console
                .andExpect(status().isOk());

        // Verificar se o mock do GeradorNFResponse foi chamado
        verify(mockGeradorNFResponse).gerarNotaFiscal(any());
    }

}
