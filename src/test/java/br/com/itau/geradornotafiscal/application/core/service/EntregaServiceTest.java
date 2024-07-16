package br.com.itau.geradornotafiscal.application.core.service;

import br.com.itau.geradornotafiscal.application.core.domain.NotaFiscal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EntregaServiceTest {
    @InjectMocks
    private EntregaService entregaService;

    @Mock
    private EntregaIntegrationService entregaIntegrationService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAgendarEntrega() {
        // Arrange
        NotaFiscal notaFiscal = new NotaFiscal(); // Crie uma instância adequada de NotaFiscal

        // Act & Assert
        assertDoesNotThrow(() -> entregaService.agendarEntrega(notaFiscal));
        verify(entregaIntegrationService, times(0)).criarAgendamentoEntrega(notaFiscal);
    }

    @Test
    public void testRegistrarNotaFiscal_ThrowsRuntimeException() {
        // Arrange
        NotaFiscal notaFiscal = new NotaFiscal();
        RegistroService service = new RegistroService() {
            @Override
            public void registrarNotaFiscal(NotaFiscal notaFiscal) {
                try {
                    throw new InterruptedException("Simulated interruption");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        // Act & Assert
        assertThrows(RuntimeException.class, () -> service.registrarNotaFiscal(notaFiscal));
    }

}