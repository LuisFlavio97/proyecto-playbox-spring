package com.cibertec;

import com.cibertec.controller.VideojuegoController;
import com.cibertec.dto.UsuarioSecurityDto;
import com.cibertec.models.entity.Consola;
import com.cibertec.models.entity.Genero;
import com.cibertec.models.entity.Usuario;
import com.cibertec.models.entity.Videojuego;
import com.cibertec.models.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VideojuegoControllerTest {
    @Mock
    private IVideojuegoService videojuegoService;

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private ITransaccionService transaccionService;

    @Mock
    private IConsolaService consolaService;

    @Mock
    private IGeneroService generoService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private VideojuegoController videojuegoController;

    @BeforeEach
    void setUp() {
        // Limpiar el contexto de seguridad antes de cada prueba
        SecurityContextHolder.clearContext();
    }

    @Test
    void listarVideojuegos_DeberiaRetornarVistaListar() {
        // Arrange
        List<Videojuego> videojuegos = Arrays.asList(new Videojuego(), new Videojuego());
        when(videojuegoService.listarTodos()).thenReturn(videojuegos);

        // Act
        String viewName = videojuegoController.listarVideojuegos(model);

        // Assert
        assertEquals("/views/videojuegos/listar", viewName);
        verify(model).addAttribute("titulo", "Lista de Videojuegos");
        verify(model).addAttribute("videojuegos", videojuegos);
        verify(videojuegoService).listarTodos();
    }

    @Test
    void crear_DeberiaRetornarVistaCrear() {
        // Arrange
        List<Consola> consolas = Arrays.asList(new Consola());
        List<Genero> generos = Arrays.asList(new Genero());
        when(consolaService.listarConsolas()).thenReturn(consolas);
        when(generoService.listarGeneros()).thenReturn(generos);

        // Act
        String viewName = videojuegoController.crear(model);

        // Assert
        assertEquals("/views/videojuegos/frmCrear", viewName);
        verify(model).addAttribute("titulo", "Formulario: Nuevo videojuego");
        verify(model).addAttribute(eq("videojuego"), any(Videojuego.class));
        verify(model).addAttribute("consolas", consolas);
        verify(model).addAttribute("generos", generos);
        verify(consolaService).listarConsolas();
        verify(generoService).listarGeneros();
    }

    @Test
    void guardar_DeberiaGuardarVideojuegoYRedirigir() {
        // Arrange
        Videojuego videojuego = new Videojuego();
        videojuego.setConsola("1");
        videojuego.setGenero("1");

        Consola consola = new Consola();
        consola.setId_consola(1);
        consola.setDescripcion("PlayStation");

        Genero genero = new Genero();
        genero.setId_genero(1);
        genero.setDescripcion("Acción");

        when(consolaService.findById(1)).thenReturn(consola);
        when(generoService.findById(1)).thenReturn(genero);

        // Act
        String redirect = videojuegoController.guardar(videojuego, redirectAttributes);

        // Assert
        assertEquals("redirect:/views/videojuegos/", redirect);
        verify(videojuegoService).guardar(videojuego);
        verify(redirectAttributes).addFlashAttribute("success", "Videojuego guardado con éxito...!!!");
        assertEquals("PlayStation", videojuego.getConsola());
        assertEquals("Acción", videojuego.getGenero());
    }

    @Test
    void editar_ConIdValido_DeberiaRetornarVistaEditar() {
        // Arrange
        Integer id = 1;
        Videojuego videojuego = new Videojuego();
        List<Consola> consolas = Arrays.asList(new Consola());
        List<Genero> generos = Arrays.asList(new Genero());

        when(videojuegoService.buscarPorId(id)).thenReturn(videojuego);
        when(consolaService.listarConsolas()).thenReturn(consolas);
        when(generoService.listarGeneros()).thenReturn(generos);

        // Act
        String viewName = videojuegoController.editar(id, model, redirectAttributes);

        // Assert
        assertEquals("/views/videojuegos/frmCrear", viewName);
        verify(model).addAttribute("titulo", "Formulario: Editar videojuego");
        verify(model).addAttribute("videojuego", videojuego);
        verify(model).addAttribute("consolas", consolas);
        verify(model).addAttribute("generos", generos);
    }

    @Test
    void editar_ConIdInvalido_DeberiaRedirigirConError() {
        // Arrange
        Integer id = 0;

        // Act
        String redirect = videojuegoController.editar(id, model, redirectAttributes);

        // Assert
        assertEquals("redirect:/views/videojuegos/", redirect);
        verify(redirectAttributes).addFlashAttribute("error", "Atención: Error con el ID del Videojuego a EDITAR...!!!");
        verify(videojuegoService, never()).buscarPorId(anyInt());
    }

    @Test
    void editar_ConVideojuegoNoEncontrado_DeberiaRedirigirConError() {
        // Arrange
        Integer id = 1;
        when(videojuegoService.buscarPorId(id)).thenReturn(null);

        // Act
        String redirect = videojuegoController.editar(id, model, redirectAttributes);

        // Assert
        assertEquals("redirect:/views/videojuegos/", redirect);
        verify(redirectAttributes).addFlashAttribute("error", "Atención: El ID del Videojuego a EDITAR no existe...!!!");
        verify(videojuegoService).buscarPorId(id);
    }

    @Test
    void eliminar_ConIdValido_DeberiaEliminarYRedirigir() {
        // Arrange
        Integer id = 1;
        Videojuego videojuego = new Videojuego();
        when(videojuegoService.buscarPorId(id)).thenReturn(videojuego);

        // Act
        String redirect = videojuegoController.eliminar(id, redirectAttributes);

        // Assert
        assertEquals("redirect:/views/videojuegos/", redirect);
        verify(videojuegoService).eliminar(id);
        verify(redirectAttributes).addFlashAttribute("warning", "Registro eliminado con éxito...!!!");
    }

    @Test
    void eliminar_ConIdInvalido_DeberiaRedirigirConError() {
        // Arrange
        Integer id = 0;

        // Act
        String redirect = videojuegoController.eliminar(id, redirectAttributes);

        // Assert
        assertEquals("redirect:/views/videojuegos/", redirect);
        verify(redirectAttributes).addFlashAttribute("error", "Atención: Error con el ID del Videojuego a ELIMINAR...!!!");
        verify(videojuegoService, never()).eliminar(anyInt());
    }

    @Test
    void eliminar_ConVideojuegoNoEncontrado_DeberiaRedirigirConError() {
        // Arrange
        Integer id = 1;
        when(videojuegoService.buscarPorId(id)).thenReturn(null);

        // Act
        String redirect = videojuegoController.eliminar(id, redirectAttributes);

        // Assert
        assertEquals("redirect:/views/videojuegos/", redirect);
        verify(redirectAttributes).addFlashAttribute("error", "Atención: El ID del Videojuego a ELIMINAR no existe...!!!");
        verify(videojuegoService, never()).eliminar(anyInt());
    }
}
