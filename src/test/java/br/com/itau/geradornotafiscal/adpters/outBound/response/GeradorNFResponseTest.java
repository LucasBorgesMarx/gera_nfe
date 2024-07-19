package br.com.itau.geradornotafiscal.adpters.outBound.response;

import br.com.itau.geradornotafiscal.adpters.entity.NotaFiscalEntity;
import br.com.itau.geradornotafiscal.adpters.outBound.mapper.NotaFiscalToNotaFiscalEntity;
import br.com.itau.geradornotafiscal.application.core.domain.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.application.core.domain.NotaFiscal;
import br.com.itau.geradornotafiscal.application.ports.out.GeradorNotaFiscalResponsePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GeradorNFResponseTest {

    @Mock
    private GeradorNotaFiscalResponsePort mockGeradorNotaFiscalResponsePort;
    @Mock
    private NotaFiscalToNotaFiscalEntity mockNotaFiscalEntity;

    private GeradorNFResponse geradorNFResponseUnderTest;

    @BeforeEach
    void setUp() {
        geradorNFResponseUnderTest = new GeradorNFResponse(mockGeradorNotaFiscalResponsePort, mockNotaFiscalEntity);
    }

    @Test
    void testGerarNotaFiscal() {
        // Setup
        final NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setIdNotaFiscal("idNotaFiscal");
        notaFiscal.setData(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        notaFiscal.setValorTotalItens(0.0);
        notaFiscal.setValorFrete(0.0);
        final ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal();
        notaFiscal.setItens(List.of(itemNotaFiscal));

        // Configure GeradorNotaFiscalResponsePort.gerarNotaFiscalResponse(...).
        final NotaFiscal notaFiscal1 = new NotaFiscal();
        notaFiscal1.setIdNotaFiscal("idNotaFiscal");
        notaFiscal1.setData(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        notaFiscal1.setValorTotalItens(0.0);
        notaFiscal1.setValorFrete(0.0);
        final ItemNotaFiscal itemNotaFiscal1 = new ItemNotaFiscal();
        notaFiscal1.setItens(List.of(itemNotaFiscal1));
        when(mockGeradorNotaFiscalResponsePort.gerarNotaFiscalResponse(any(NotaFiscal.class))).thenReturn(notaFiscal1);

        when(mockNotaFiscalEntity.mapper(any(NotaFiscal.class))).thenReturn(NotaFiscalEntity.builder().build());

        // Run the test
        final ResponseEntity<NotaFiscalEntity> result = geradorNFResponseUnderTest.gerarNotaFiscal(notaFiscal);

        // Verify the results
    }
}
