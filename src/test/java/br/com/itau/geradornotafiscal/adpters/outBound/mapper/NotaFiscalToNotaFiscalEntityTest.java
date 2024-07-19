package br.com.itau.geradornotafiscal.adpters.outBound.mapper;

import br.com.itau.geradornotafiscal.adpters.entity.NotaFiscalEntity;
import br.com.itau.geradornotafiscal.application.core.domain.*;
import br.com.itau.geradornotafiscal.application.core.enuns.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class NotaFiscalToNotaFiscalEntityTest {

    private NotaFiscalToNotaFiscalEntity notaFiscalToNotaFiscalEntityUnderTest;

    @BeforeEach
    void setUp() {
        notaFiscalToNotaFiscalEntityUnderTest = new NotaFiscalToNotaFiscalEntity();
    }

    @Test
    void testMapper() {
        // Setup
        final NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setIdNotaFiscal("idNotaFiscal");
        notaFiscal.setData(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        notaFiscal.setValorTotalItens(0.0);
        notaFiscal.setValorFrete(0.0);
        notaFiscal.setDestinatario(notaFiscal.getDestinatario());
        final ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal();
        itemNotaFiscal.setIdItem("idItem");
        itemNotaFiscal.setDescricao("descricao");
        itemNotaFiscal.setValorUnitario(0.0);
        itemNotaFiscal.setQuantidade(0);
        itemNotaFiscal.setValorTributoItem(0.0);
        notaFiscal.setItens(List.of(itemNotaFiscal));
        final Destinatario destinatario = new Destinatario();
        destinatario.setNome("nome");
        destinatario.setTipoPessoa(ETipoPessoa.FISICA);
        destinatario.setRegimeTributacao(ERegimeTributacaoPJ.SIMPLES_NACIONAL);
        final Documento documento = new Documento();
        documento.setNumero("numero");
        documento.setTipo(ETipoDocumento.CPF);
        destinatario.setDocumentos(List.of(documento));
        final Endereco endereco = new Endereco();
        endereco.setCep("cep");
        endereco.setLogradouro("logradouro");
        endereco.setNumero("numero");
        endereco.setEstado("estado");
        endereco.setComplemento("complemento");
        endereco.setFinalidade(EFinalidade.COBRANCA_ENTREGA);
        endereco.setRegiao(ERegiao.NORTE);
        destinatario.setEnderecos(List.of(endereco));
        notaFiscal.setDestinatario(destinatario);

        // Run the test
        final NotaFiscalEntity result = notaFiscalToNotaFiscalEntityUnderTest.mapper(notaFiscal);

        // Verify the results
        assertThat(result).isNotNull();

    }


}
