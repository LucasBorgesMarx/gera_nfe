package br.com.itau.geradornotafiscal.adpters.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemNotaFiscalEntityTest {
    @Test
    public void testNoArgsConstructor() {
        ItemNotaFiscalEntity item = new ItemNotaFiscalEntity();
        assertNotNull(item);
    }

    @Test
    public void testAllArgsConstructor() {
        ItemNotaFiscalEntity item = new ItemNotaFiscalEntity(
                "1", "Descrição do Item", 10.0, 2, 1.0
        );

        assertEquals("1", item.getIdItem());
        assertEquals("Descrição do Item", item.getDescricao());
        assertEquals(10.0, item.getValorUnitario());
        assertEquals(2, item.getQuantidade());
        assertEquals(1.0, item.getValorTributoItem());
    }

    @Test
    public void testBuilder() {
        ItemNotaFiscalEntity item = ItemNotaFiscalEntity.builder()
                .idItem("1")
                .descricao("Descrição do Item")
                .valorUnitario(10.0)
                .quantidade(2)
                .valorTributoItem(1.0)
                .build();

        assertEquals("1", item.getIdItem());
        assertEquals("Descrição do Item", item.getDescricao());
        assertEquals(10.0, item.getValorUnitario());
        assertEquals(2, item.getQuantidade());
        assertEquals(1.0, item.getValorTributoItem());
    }

    @Test
    public void testSettersAndGetters() {
        ItemNotaFiscalEntity item = new ItemNotaFiscalEntity();
        item.setIdItem("1");
        item.setDescricao("Descrição do Item");
        item.setValorUnitario(10.0);
        item.setQuantidade(2);
        item.setValorTributoItem(1.0);

        assertEquals("1", item.getIdItem());
        assertEquals("Descrição do Item", item.getDescricao());
        assertEquals(10.0, item.getValorUnitario());
        assertEquals(2, item.getQuantidade());
        assertEquals(1.0, item.getValorTributoItem());
    }
}