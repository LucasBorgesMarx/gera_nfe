package br.com.itau.geradornotafiscal.config;

import br.com.itau.geradornotafiscal.application.core.domain.Item;
import br.com.itau.geradornotafiscal.application.core.domain.Pedido;
import br.com.itau.geradornotafiscal.application.core.enuns.ERegiao;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class ConfigTest {

    @Test
    void testGetValorFreteComNortePercentual() {
        // Setup
        final Pedido pedido = new Pedido();
        pedido.setIdPedido(0);
        pedido.setData(LocalDate.of(2020, 1, 1));
        pedido.setValorTotalItens(0.0);
        pedido.setValorFrete(1.08);
        final Item item = new Item();
        pedido.setItens(List.of(item));

        // Run the test
        final double result = Config.getValorFreteComPercentual(pedido, ERegiao.NORTE);

        // Verify the results
        assertThat(result).isEqualTo(1.1664, within(0.0001));
    }
    @Test
    void testGetValorFreteComNORDESTEPercentual() {
        // Setup
        final Pedido pedido = new Pedido();
        pedido.setIdPedido(0);
        pedido.setData(LocalDate.of(2020, 1, 1));
        pedido.setValorTotalItens(0.0);
        pedido.setValorFrete(1.085);
        final Item item = new Item();
        pedido.setItens(List.of(item));

        // Run the test
        final double result = Config.getValorFreteComPercentual(pedido, ERegiao.NORDESTE);

        // Verify the results
        assertThat(result).isEqualTo(1.177225, within(0.0001));
    }


    @Test
    void testGetValorFreteComCENTRO_OESTEPercentual() {
        // Setup
        final Pedido pedido = new Pedido();
        pedido.setIdPedido(0);
        pedido.setData(LocalDate.of(2020, 1, 1));
        pedido.setValorTotalItens(0.0);
        pedido.setValorFrete(1.07);
        final Item item = new Item();
        pedido.setItens(List.of(item));

        // Run the test
        final double result = Config.getValorFreteComPercentual(pedido, ERegiao.CENTRO_OESTE);

        // Verify the results
        assertThat(result).isEqualTo(1.1449, within(0.0001));
    }


    @Test
    void testGetValorFreteComSUDESTEPercentual() {
        // Setup
        final Pedido pedido = new Pedido();
        pedido.setIdPedido(0);
        pedido.setData(LocalDate.of(2020, 1, 1));
        pedido.setValorTotalItens(0.0);
        pedido.setValorFrete(1.048);
        final Item item = new Item();
        pedido.setItens(List.of(item));

        // Run the test
        final double result = Config.getValorFreteComPercentual(pedido, ERegiao.SUDESTE);

        // Verify the results
        assertThat(result).isEqualTo(1.0983040000000002, within(0.0001));
    }

    @Test
    void testGetValorFreteComSULPercentual() {
        // Setup
        final Pedido pedido = new Pedido();
        pedido.setIdPedido(0);
        pedido.setData(LocalDate.of(2020, 1, 1));
        pedido.setValorTotalItens(0.0);
        pedido.setValorFrete(1.06);
        final Item item = new Item();
        pedido.setItens(List.of(item));

        // Run the test
        final double result = Config.getValorFreteComPercentual(pedido, ERegiao.SUL);

        // Verify the results
        assertThat(result).isEqualTo(1.1236000000000002, within(0.0001));
    }

}
