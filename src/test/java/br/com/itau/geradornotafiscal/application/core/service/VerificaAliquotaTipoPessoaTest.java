package br.com.itau.geradornotafiscal.application.core.service;

import br.com.itau.geradornotafiscal.application.core.domain.Destinatario;
import br.com.itau.geradornotafiscal.application.core.domain.Item;
import br.com.itau.geradornotafiscal.application.core.domain.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.application.core.domain.Pedido;
import br.com.itau.geradornotafiscal.application.core.enuns.ERegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.application.core.enuns.ETipoPessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VerificaAliquotaTipoPessoaTest {

    @InjectMocks
    private VerificaAliquotaTipoPessoa verificaAliquotaTipoPessoa;

    @Mock
    private CalculadoraAliquotaProduto calculadoraAliquotaProduto;

    @Mock
    private Pedido pedido;

    @Mock
    private Destinatario destinatario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testVerificaAliquotaTipoPessoaFISICA() {
        // Arrange
        when(destinatario.getTipoPessoa()).thenReturn(ETipoPessoa.FISICA);
        when(pedido.getDestinatario()).thenReturn(destinatario);
        when(pedido.getValorTotalItens()).thenReturn(600.0);

        List<Item> itens = new ArrayList<>(); // Lista concreta
        when(pedido.getItens()).thenReturn(itens);

        List<ItemNotaFiscal> resultadoEsperado = new ArrayList<>();
        when(calculadoraAliquotaProduto.calcularAliquota(itens, 0.12)).thenReturn(resultadoEsperado);

        // Act
        List<ItemNotaFiscal> resultado = verificaAliquotaTipoPessoa.verficaAliquotaTitpoPessoaService(pedido);

        // Assert
        assertEquals(resultadoEsperado, resultado);
        verify(calculadoraAliquotaProduto, times(0)).calcularAliquota(itens, 0.12);
    }
    @Test
    void testVerificaAliquotaTipoPessoaFISICAI_MAIOR_TRAZ_MEIO() {
        // Arrange
        when(destinatario.getTipoPessoa()).thenReturn(ETipoPessoa.FISICA);
        when(pedido.getDestinatario()).thenReturn(destinatario);
        when(pedido.getValorTotalItens()).thenReturn(4500.00);

        List<Item> itens = new ArrayList<>(); // Lista concreta
        when(pedido.getItens()).thenReturn(itens);

        List<ItemNotaFiscal> resultadoEsperado = new ArrayList<>();
        when(calculadoraAliquotaProduto.calcularAliquota(itens, 0.17)).thenReturn(resultadoEsperado);

        // Act
        List<ItemNotaFiscal> resultado = verificaAliquotaTipoPessoa.verficaAliquotaTitpoPessoaService(pedido);

        // Assert
        assertEquals(resultadoEsperado, resultado);
        verify(calculadoraAliquotaProduto, times(0)).calcularAliquota(itens, 0.17);
    }
    @Test
    void testVerificaAliquotaTipoPessoaFISICAIqualTrezMeio() {
        // Arrange
        when(destinatario.getTipoPessoa()).thenReturn(ETipoPessoa.FISICA);
        when(pedido.getDestinatario()).thenReturn(destinatario);
        when(pedido.getValorTotalItens()).thenReturn(3500.00);

        List<Item> itens = new ArrayList<>(); // Lista concreta
        when(pedido.getItens()).thenReturn(itens);

        List<ItemNotaFiscal> resultadoEsperado = new ArrayList<>();
        when(calculadoraAliquotaProduto.calcularAliquota(itens, 0.15)).thenReturn(resultadoEsperado);

        // Act
        List<ItemNotaFiscal> resultado = verificaAliquotaTipoPessoa.verficaAliquotaTitpoPessoaService(pedido);

        // Assert
        assertEquals(resultadoEsperado, resultado);
        verify(calculadoraAliquotaProduto, times(0)).calcularAliquota(itens, 0.15);
    }

    @Test
    void testVerificaAliquotaTipoPessoaJURIDICA_SIMPLES_NACIONAL() {
        // Arrange
        when(destinatario.getTipoPessoa()).thenReturn(ETipoPessoa.JURIDICA);
        when(destinatario.getRegimeTributacao()).thenReturn(ERegimeTributacaoPJ.SIMPLES_NACIONAL);
        when(pedido.getDestinatario()).thenReturn(destinatario);
        when(pedido.getValorTotalItens()).thenReturn(1500.0);

        List<Item> itens = new ArrayList<>(); // Lista concreta
        when(pedido.getItens()).thenReturn(itens);

        List<ItemNotaFiscal> resultadoEsperado = new ArrayList<>();
        when(calculadoraAliquotaProduto.calcularAliquota(itens, 0.07)).thenReturn(resultadoEsperado);

        // Act
        List<ItemNotaFiscal> resultado = verificaAliquotaTipoPessoa.verficaAliquotaTitpoPessoaService(pedido);

        // Assert
        assertEquals(resultadoEsperado, resultado);
        verify(calculadoraAliquotaProduto, times(0)).calcularAliquota(itens, 0.07);
    }
    @Test
    void testVerificaAliquotaTipoPessoaJURIDICA_SIMPLES_NACIONALMenorMil() {
        // Arrange
        when(destinatario.getTipoPessoa()).thenReturn(ETipoPessoa.JURIDICA);
        when(destinatario.getRegimeTributacao()).thenReturn(ERegimeTributacaoPJ.SIMPLES_NACIONAL);
        when(pedido.getDestinatario()).thenReturn(destinatario);
        when(pedido.getValorTotalItens()).thenReturn(500.00);

        List<Item> itens = new ArrayList<>(); // Lista concreta
        when(pedido.getItens()).thenReturn(itens);

        List<ItemNotaFiscal> resultadoEsperado = new ArrayList<>();
        when(calculadoraAliquotaProduto.calcularAliquota(itens, 0.03)).thenReturn(resultadoEsperado);

        // Act
        List<ItemNotaFiscal> resultado = verificaAliquotaTipoPessoa.verficaAliquotaTitpoPessoaService(pedido);

        // Assert
        assertEquals(resultadoEsperado, resultado);
        verify(calculadoraAliquotaProduto, times(0)).calcularAliquota(itens, 0.03);
    }

    @Test
    void testVerificaAliquotaTipoPessoaSimplesMaiorDoisMil() {
        // Arrange
        when(destinatario.getTipoPessoa()).thenReturn(ETipoPessoa.JURIDICA);
        when(destinatario.getRegimeTributacao()).thenReturn(ERegimeTributacaoPJ.SIMPLES_NACIONAL);
        when(pedido.getDestinatario()).thenReturn(destinatario);
        when(pedido.getValorTotalItens()).thenReturn(2500.0);

        List<Item> itens = new ArrayList<>(); // Lista concreta
        when(pedido.getItens()).thenReturn(itens);

        List<ItemNotaFiscal> resultadoEsperado = new ArrayList<>();
        when(calculadoraAliquotaProduto.calcularAliquota(itens, 0.13)).thenReturn(resultadoEsperado);

        // Act
        List<ItemNotaFiscal> resultado = verificaAliquotaTipoPessoa.verficaAliquotaTitpoPessoaService(pedido);

        // Assert
        assertEquals(resultadoEsperado, resultado);
        verify(calculadoraAliquotaProduto,  times(0)).calcularAliquota(itens, 0.13);
    }


    @Test
    void testVerificaAliquotaTipoPessoaSimplesMaiorCincoMil() {
        // Arrange
        when(destinatario.getTipoPessoa()).thenReturn(ETipoPessoa.JURIDICA);
        when(destinatario.getRegimeTributacao()).thenReturn(ERegimeTributacaoPJ.SIMPLES_NACIONAL);
        when(pedido.getDestinatario()).thenReturn(destinatario);
        when(pedido.getValorTotalItens()).thenReturn(6000.00);

        List<Item> itens = new ArrayList<>(); // Lista concreta
        when(pedido.getItens()).thenReturn(itens);

        List<ItemNotaFiscal> resultadoEsperado = new ArrayList<>();
        when(calculadoraAliquotaProduto.calcularAliquota(itens, 0.19)).thenReturn(resultadoEsperado);

        // Act
        List<ItemNotaFiscal> resultado = verificaAliquotaTipoPessoa.verficaAliquotaTitpoPessoaService(pedido);

        // Assert
        assertEquals(resultadoEsperado, resultado);
        verify(calculadoraAliquotaProduto,  times(0)).calcularAliquota(itens, 0.19);
    }

    @Test
    void testVerificaAliquotaTipoPessoaJURIDICA_LUCRO_REAL() {
        // Arrange
        when(destinatario.getTipoPessoa()).thenReturn(ETipoPessoa.JURIDICA);
        when(destinatario.getRegimeTributacao()).thenReturn(ERegimeTributacaoPJ.LUCRO_REAL);
        when(pedido.getDestinatario()).thenReturn(destinatario);
        when(pedido.getValorTotalItens()).thenReturn(2500.00);

        List<Item> itens = new ArrayList<>(); // Lista concreta
        when(pedido.getItens()).thenReturn(itens);

        List<ItemNotaFiscal> resultadoEsperado = new ArrayList<>();
        when(calculadoraAliquotaProduto.calcularAliquota(itens, 0.15)).thenReturn(resultadoEsperado);

        // Act
        List<ItemNotaFiscal> resultado = verificaAliquotaTipoPessoa.verficaAliquotaTitpoPessoaService(pedido);

        // Assert
        assertEquals(resultadoEsperado, resultado);
        verify(calculadoraAliquotaProduto,  times(0)).calcularAliquota(itens, 0.15);
    }

    @Test
    void testVerificaAliquotaTipoPessoaJURIDICA_LUCRO_REAL_MENOR_MIL() {
        // Arrange
        when(destinatario.getTipoPessoa()).thenReturn(ETipoPessoa.JURIDICA);
        when(destinatario.getRegimeTributacao()).thenReturn(ERegimeTributacaoPJ.LUCRO_REAL);
        when(pedido.getDestinatario()).thenReturn(destinatario);
        when(pedido.getValorTotalItens()).thenReturn(500.00);

        List<Item> itens = new ArrayList<>(); // Lista concreta
        when(pedido.getItens()).thenReturn(itens);

        List<ItemNotaFiscal> resultadoEsperado = new ArrayList<>();
        when(calculadoraAliquotaProduto.calcularAliquota(itens, 0.03)).thenReturn(resultadoEsperado);

        // Act
        List<ItemNotaFiscal> resultado = verificaAliquotaTipoPessoa.verficaAliquotaTitpoPessoaService(pedido);

        // Assert
        assertEquals(resultadoEsperado, resultado);
        verify(calculadoraAliquotaProduto,  times(0)).calcularAliquota(itens, 0.03);
    }
    @Test
    void testVerificaAliquotaTipoPessoaJURIDICA_LUCRO_REAL_MAIOR_MIL() {
        // Arrange
        when(destinatario.getTipoPessoa()).thenReturn(ETipoPessoa.JURIDICA);
        when(destinatario.getRegimeTributacao()).thenReturn(ERegimeTributacaoPJ.LUCRO_REAL);
        when(pedido.getDestinatario()).thenReturn(destinatario);
        when(pedido.getValorTotalItens()).thenReturn(1500.00);

        List<Item> itens = new ArrayList<>(); // Lista concreta
        when(pedido.getItens()).thenReturn(itens);

        List<ItemNotaFiscal> resultadoEsperado = new ArrayList<>();
        when(calculadoraAliquotaProduto.calcularAliquota(itens, 0.09)).thenReturn(resultadoEsperado);

        // Act
        List<ItemNotaFiscal> resultado = verificaAliquotaTipoPessoa.verficaAliquotaTitpoPessoaService(pedido);

        // Assert
        assertEquals(resultadoEsperado, resultado);
        verify(calculadoraAliquotaProduto,  times(0)).calcularAliquota(itens, 0.09);
    }


    @Test
    void testVerificaAliquotaTipoPessoaJURIDICA_LUCRO_REAL_MAIOR__CINCO_MIL() {
        // Arrange
        when(destinatario.getTipoPessoa()).thenReturn(ETipoPessoa.JURIDICA);
        when(destinatario.getRegimeTributacao()).thenReturn(ERegimeTributacaoPJ.LUCRO_REAL);
        when(pedido.getDestinatario()).thenReturn(destinatario);
        when(pedido.getValorTotalItens()).thenReturn(6000.00);

        List<Item> itens = new ArrayList<>(); // Lista concreta
        when(pedido.getItens()).thenReturn(itens);

        List<ItemNotaFiscal> resultadoEsperado = new ArrayList<>();
        when(calculadoraAliquotaProduto.calcularAliquota(itens, 0.20)).thenReturn(resultadoEsperado);

        // Act
        List<ItemNotaFiscal> resultado = verificaAliquotaTipoPessoa.verficaAliquotaTitpoPessoaService(pedido);

        // Assert
        assertEquals(resultadoEsperado, resultado);
        verify(calculadoraAliquotaProduto,  times(0)).calcularAliquota(itens, 0.20);
    }






    @Test
    void testVerificaAliquotaTipoPessoaJURIDICA_LUCRO_PRESUMIDO() {
        // Arrange
        when(destinatario.getTipoPessoa()).thenReturn(ETipoPessoa.JURIDICA);
        when(destinatario.getRegimeTributacao()).thenReturn(ERegimeTributacaoPJ.LUCRO_PRESUMIDO);
        when(pedido.getDestinatario()).thenReturn(destinatario);
        when(pedido.getValorTotalItens()).thenReturn(4000.0);

        List<Item> itens = new ArrayList<>(); // Lista concreta
        when(pedido.getItens()).thenReturn(itens);

        List<ItemNotaFiscal> resultadoEsperado = new ArrayList<>();
        when(calculadoraAliquotaProduto.calcularAliquota(itens, 0.16)).thenReturn(resultadoEsperado);

        // Act
        List<ItemNotaFiscal> resultado = verificaAliquotaTipoPessoa.verficaAliquotaTitpoPessoaService(pedido);

        // Assert
        assertEquals(resultadoEsperado, resultado);
        verify(calculadoraAliquotaProduto, times(0)).calcularAliquota(itens, 0.16);
    }


    @Test
    void testVerificaAliquotaTipoPessoaJURIDICA_LUCRO_PRESUMIDO_MENOR_MIL() {
        // Arrange
        when(destinatario.getTipoPessoa()).thenReturn(ETipoPessoa.JURIDICA);
        when(destinatario.getRegimeTributacao()).thenReturn(ERegimeTributacaoPJ.LUCRO_PRESUMIDO);
        when(pedido.getDestinatario()).thenReturn(destinatario);
        when(pedido.getValorTotalItens()).thenReturn(500.00);

        List<Item> itens = new ArrayList<>(); // Lista concreta
        when(pedido.getItens()).thenReturn(itens);

        List<ItemNotaFiscal> resultadoEsperado = new ArrayList<>();
        when(calculadoraAliquotaProduto.calcularAliquota(itens, 0.03)).thenReturn(resultadoEsperado);

        // Act
        List<ItemNotaFiscal> resultado = verificaAliquotaTipoPessoa.verficaAliquotaTitpoPessoaService(pedido);

        // Assert
        assertEquals(resultadoEsperado, resultado);
        verify(calculadoraAliquotaProduto, times(0)).calcularAliquota(itens, 0.03);
    }


    @Test
    void testVerificaAliquotaTipoPessoaJURIDICA_LUCRO_PRESUMIDO_MAIOR_MIL() {
        // Arrange
        when(destinatario.getTipoPessoa()).thenReturn(ETipoPessoa.JURIDICA);
        when(destinatario.getRegimeTributacao()).thenReturn(ERegimeTributacaoPJ.LUCRO_PRESUMIDO);
        when(pedido.getDestinatario()).thenReturn(destinatario);
        when(pedido.getValorTotalItens()).thenReturn(1500.00);

        List<Item> itens = new ArrayList<>(); // Lista concreta
        when(pedido.getItens()).thenReturn(itens);

        List<ItemNotaFiscal> resultadoEsperado = new ArrayList<>();
        when(calculadoraAliquotaProduto.calcularAliquota(itens, 0.09)).thenReturn(resultadoEsperado);

        // Act
        List<ItemNotaFiscal> resultado = verificaAliquotaTipoPessoa.verficaAliquotaTitpoPessoaService(pedido);

        // Assert
        assertEquals(resultadoEsperado, resultado);
        verify(calculadoraAliquotaProduto, times(0)).calcularAliquota(itens, 0.09);
    }
}
