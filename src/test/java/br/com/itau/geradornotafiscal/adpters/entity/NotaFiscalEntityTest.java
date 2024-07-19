package br.com.itau.geradornotafiscal.adpters.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@ExtendWith(MockitoExtension.class)
class NotaFiscalEntityTest {

    @Mock
    private List<ItemNotaFiscalEntity> mockItens;
    @Mock
    private DestinatarioEntity mockDestinatarioEntity;

    private NotaFiscalEntity notaFiscalEntityUnderTest;

    @BeforeEach
    void setUp() {
        notaFiscalEntityUnderTest = new NotaFiscalEntity("mensagem", "idNotaFiscal",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0.0, 0.0, mockItens, mockDestinatarioEntity);
    }

    @Test
    void testMensagemGetterAndSetter() {
        final String mensagem = "mensagem";
        notaFiscalEntityUnderTest.setMensagem(mensagem);
        assertThat(notaFiscalEntityUnderTest.getMensagem()).isEqualTo(mensagem);
    }

    @Test
    void testIdNotaFiscalGetterAndSetter() {
        final String idNotaFiscal = "idNotaFiscal";
        notaFiscalEntityUnderTest.setIdNotaFiscal(idNotaFiscal);
        assertThat(notaFiscalEntityUnderTest.getIdNotaFiscal()).isEqualTo(idNotaFiscal);
    }

    @Test
    void testDataGetterAndSetter() {
        final LocalDateTime data = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
        notaFiscalEntityUnderTest.setData(data);
        assertThat(notaFiscalEntityUnderTest.getData()).isEqualTo(data);
    }

    @Test
    void testValorTotalItensGetterAndSetter() {
        final double valorTotalItens = 0.0;
        notaFiscalEntityUnderTest.setValorTotalItens(valorTotalItens);
        assertThat(notaFiscalEntityUnderTest.getValorTotalItens()).isEqualTo(valorTotalItens, within(0.0001));
    }

    @Test
    void testValorFreteGetterAndSetter() {
        final double valorFrete = 0.0;
        notaFiscalEntityUnderTest.setValorFrete(valorFrete);
        assertThat(notaFiscalEntityUnderTest.getValorFrete()).isEqualTo(valorFrete, within(0.0001));
    }

    @Test
    void testItensGetterAndSetter() {
        final List<ItemNotaFiscalEntity> itens = List.of(ItemNotaFiscalEntity.builder().build());
        notaFiscalEntityUnderTest.setItens(itens);
        assertThat(notaFiscalEntityUnderTest.getItens()).isEqualTo(itens);
    }

    @Test
    void testDestinatarioEntityGetterAndSetter() {
        final DestinatarioEntity destinatarioEntity = DestinatarioEntity.builder().build();
        notaFiscalEntityUnderTest.setDestinatarioEntity(destinatarioEntity);
        assertThat(notaFiscalEntityUnderTest.getDestinatarioEntity()).isEqualTo(destinatarioEntity);
    }

    @Test
    void testBuilder() {
        // Setup
        // Run the test
        final NotaFiscalEntity.NotaFiscalEntityBuilder result = NotaFiscalEntity.builder();

        // Verify the results
    }
}
