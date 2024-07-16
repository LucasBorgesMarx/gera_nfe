package br.com.itau.geradornotafiscal.application.core.service;

import br.com.itau.geradornotafiscal.application.core.domain.NotaFiscal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class EntregaIntegrationServiceTest {
    @InjectMocks
    EntregaIntegrationService agendamentoService;
    @InjectMocks
    NotaFiscal notaFiscal;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Timeout(300) // Defina um tempo limite para evitar que o teste demore mais do que o esperado
    public void testCriarAgendamentoEntrega() {

        assertDoesNotThrow(() -> agendamentoService.criarAgendamentoEntrega(notaFiscal));

    }
}