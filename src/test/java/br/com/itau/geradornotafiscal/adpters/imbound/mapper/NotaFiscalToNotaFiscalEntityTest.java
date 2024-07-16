package br.com.itau.geradornotafiscal.adpters.imbound.mapper;

import br.com.itau.geradornotafiscal.adpters.entity.NotaFiscalEntity;
import br.com.itau.geradornotafiscal.adpters.enuns.TipoDocumento;
import br.com.itau.geradornotafiscal.adpters.enuns.TipoPessoa;
import br.com.itau.geradornotafiscal.application.core.domain.*;
import br.com.itau.geradornotafiscal.application.core.enuns.EFinalidade;
import br.com.itau.geradornotafiscal.application.core.enuns.ERegiao;
import br.com.itau.geradornotafiscal.application.core.enuns.ETipoDocumento;
import br.com.itau.geradornotafiscal.application.core.enuns.ETipoPessoa;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NotaFiscalToNotaFiscalEntityTest {

    private final NotaFiscalToNotaFiscalEntity mapper = new NotaFiscalToNotaFiscalEntity();

    @Test
    public void testMapper() {
        // Arrange
        Destinatario destinatario = new Destinatario();
        destinatario.setNome("Destinatario Teste");
        destinatario.setTipoPessoa(ETipoPessoa.FISICA);
        destinatario.setDocumentos(List.of(new Documento("12345678900" , ETipoDocumento.CPF)));
        destinatario.setEnderecos(List.of(
                new Endereco("12345678",
                        "Rua Teste",
                        "123", "SP", "Apto 1",
                        EFinalidade.ENTREGA, ERegiao.SUL)));

        ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal();
        itemNotaFiscal.setIdItem("1");
        itemNotaFiscal.setDescricao("Item Teste");
        itemNotaFiscal.setValorUnitario(100.0);
        itemNotaFiscal.setQuantidade(1);
        itemNotaFiscal.setValorTributoItem(10.0);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setIdNotaFiscal("1");
        notaFiscal.setData(LocalDateTime.of(2024,07, 16,00,00,00));
        notaFiscal.setValorFrete(50.0);
        notaFiscal.setValorTotalItens(100.0);
        notaFiscal.setDestinatario(destinatario);
        notaFiscal.setItens(List.of(itemNotaFiscal));

        // Act
        NotaFiscalEntity notaFiscalEntity = mapper.mapper(notaFiscal);

        // Assert
        assertNotNull(notaFiscalEntity);
        assertEquals(notaFiscal.getIdNotaFiscal(), notaFiscalEntity.getIdNotaFiscal());
        assertEquals(notaFiscal.getData(), notaFiscalEntity.getData());
        assertEquals(notaFiscal.getValorFrete(), notaFiscalEntity.getValorFrete());
        assertEquals(notaFiscal.getValorTotalItens(), notaFiscalEntity.getValorTotalItens());

        assertNotNull(notaFiscalEntity.getDestinatarioEntity());
        assertEquals(destinatario.getNome(), notaFiscalEntity.getDestinatarioEntity().getNome());
        assertEquals(TipoPessoa.FISICA, notaFiscalEntity.getDestinatarioEntity().getTipoPessoa());
        assertEquals(1, notaFiscalEntity.getDestinatarioEntity().getDocumentoEntities().size());
        assertEquals("12345678900", notaFiscalEntity.getDestinatarioEntity().getDocumentoEntities().get(0).getNumero());
        assertEquals(TipoDocumento.CPF, notaFiscalEntity.getDestinatarioEntity().getDocumentoEntities().get(0).getTipo());

        assertNotNull(notaFiscalEntity.getItens());
        assertEquals(1, notaFiscalEntity.getItens().size());
        assertEquals(itemNotaFiscal.getIdItem(), notaFiscalEntity.getItens().get(0).getIdItem());
        assertEquals(itemNotaFiscal.getDescricao(), notaFiscalEntity.getItens().get(0).getDescricao());
        assertEquals(itemNotaFiscal.getValorUnitario(), notaFiscalEntity.getItens().get(0).getValorUnitario());
        assertEquals(itemNotaFiscal.getQuantidade(), notaFiscalEntity.getItens().get(0).getQuantidade());
        assertEquals(itemNotaFiscal.getValorTributoItem(), notaFiscalEntity.getItens().get(0).getValorTributoItem());
    }
}