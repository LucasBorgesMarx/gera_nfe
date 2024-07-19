package br.com.itau.calculadoratributos;

import br.com.itau.geradornotafiscal.adpters.entity.*;
import br.com.itau.geradornotafiscal.adpters.enuns.*;
import br.com.itau.geradornotafiscal.adpters.imbound.mapper.PedidoToPedidoEntity;
import br.com.itau.geradornotafiscal.application.core.service.CalculadoraAliquotaProduto;
import br.com.itau.geradornotafiscal.application.core.service.GeradorNotaFiscalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeradorNotaFiscalEntityPortImplTest {

    @InjectMocks
    private GeradorNotaFiscalService geradorNotaFiscalService;
    @InjectMocks
    private PedidoToPedidoEntity pedidoToPedidoEntity;

    @Mock
    private CalculadoraAliquotaProduto calculadoraAliquotaProduto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void shouldGenerateNotaFiscalForTipoPessoaFisicaWithValorTotalItensLessThan500() {
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setValorTotalItens(400);
        pedidoEntity.setValorFrete(100);
        DestinatarioEntity destinatarioEntity = new DestinatarioEntity();
        destinatarioEntity.setTipoPessoa(TipoPessoa.FISICA);

        // Create and add EnderecoEntity to the DestinatarioEntity
        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setFinalidade(Finalidade.ENTREGA);
        enderecoEntity.setRegiao(Regiao.SUDESTE);
        destinatarioEntity.setEnderecoEntities(Arrays.asList(enderecoEntity));
        destinatarioEntity.setDocumentoEntities(Arrays.asList(new DocumentoEntity(TipoDocumento.CNPJ.name(), TipoDocumento.CNPJ)));
        pedidoEntity.setDestinatarioEntity(destinatarioEntity);

        // Create and add items to the PedidoEntity
        ItenEntity itemEntity = new ItenEntity();
        itemEntity.setValorUnitario(100);
        itemEntity.setQuantidade(4);
        pedidoEntity.setItens(Arrays.asList(itemEntity));

        var notaFiscalEntity = geradorNotaFiscalService.gerarNotaFiscal(pedidoToPedidoEntity.mapper(pedidoEntity));

        assertEquals(pedidoEntity.getValorTotalItens(), notaFiscalEntity.getValorTotalItens());
        assertEquals(2, notaFiscalEntity.getItens().size());
        assertEquals(200.0, notaFiscalEntity.getItens().get(0).getValorTributoItem());
    }

    @Test
    public void shouldGenerateNotaFiscalForTipoPessoaJuridicaWithRegimeTributacaoLucroPresumidoAndValorTotalItensGreaterThan5000() {
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setValorTotalItens(6000);
        pedidoEntity.setValorFrete(100);
        DestinatarioEntity destinatarioEntity = new DestinatarioEntity();
        destinatarioEntity.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatarioEntity.setRegimeTributacao(RegimeTributacaoPJ.LUCRO_PRESUMIDO);
        destinatarioEntity.setDocumentoEntities(Arrays.asList(new DocumentoEntity(TipoDocumento.CNPJ.name(), TipoDocumento.CNPJ)));
        // Create and add EnderecoEntity to the DestinatarioEntity
        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setFinalidade(Finalidade.ENTREGA);
        enderecoEntity.setRegiao(Regiao.SUDESTE);
        destinatarioEntity.setEnderecoEntities(Arrays.asList(enderecoEntity));

        pedidoEntity.setDestinatarioEntity(destinatarioEntity);

        // Create and add items to the PedidoEntity
        ItenEntity itemEntity = new ItenEntity();
        itemEntity.setValorUnitario(1000);
        itemEntity.setQuantidade(6);
        pedidoEntity.setItens(Arrays.asList(itemEntity));
        pedidoEntity.setDestinatarioEntity(destinatarioEntity);
        var notaFiscalEntity = geradorNotaFiscalService.gerarNotaFiscal(pedidoToPedidoEntity.mapper(pedidoEntity));

        assertEquals(pedidoEntity.getValorTotalItens(), notaFiscalEntity.getValorTotalItens());
        assertEquals(1, notaFiscalEntity.getItens().size());
        assertEquals(0.20 * itemEntity.getValorUnitario(), notaFiscalEntity.getItens().get(0).getValorTributoItem());
    }

}