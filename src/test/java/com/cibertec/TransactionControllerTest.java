package com.cibertec;

import com.cibertec.controller.TransactionController;
import com.cibertec.dto.TransacUsuarioJuegoDto;
import com.cibertec.dto.TransaccionDetalleDto;
import com.cibertec.models.entity.Transaccion;
import com.cibertec.models.repository.TransaccionRepository;
import com.cibertec.models.service.TransaccionServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransaccionServiceImpl transaccionService;

    @Test
    void testListarMovimientos() throws Exception {
        TransaccionDetalleDto detalleDto = new TransaccionDetalleDto();
        Mockito.when(transaccionService.findAllTransaccion())
                .thenReturn(Collections.singletonList(detalleDto));

        mockMvc.perform(get("/transaccion/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("Movimiento/listar-movimientos"))
                .andExpect(model().attributeExists("movimientos"));
    }

    @Nested
    @ExtendWith(MockitoExtension.class)
    class TransaccionServiceImplTest {
        @InjectMocks
        private TransaccionServiceImpl transaccionService;
        @Mock
        private TransaccionRepository transaccionRepository;
        @Test
        void testGuardarTransaccion() {
            TransacUsuarioJuegoDto dto = new TransacUsuarioJuegoDto();
            dto.setUsuarioId(1);
            dto.setVidejoJuegoId(2);
            Transaccion mockTransaccion = new Transaccion();
            mockTransaccion.setIdtransaccion(1);

            Mockito.when(transaccionRepository.save(Mockito.any()))
                    .thenReturn(mockTransaccion);

            Optional<Transaccion> resultado = transaccionService.saveTransaccion(dto);
            assertTrue(resultado.isPresent());
            assertEquals(1, resultado.get().getIdtransaccion());
        }
    }
}
