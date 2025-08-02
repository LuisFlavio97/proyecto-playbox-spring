package com.cibertec;

import com.cibertec.controller.LoginController;
import com.cibertec.models.entity.Usuario;
import com.cibertec.models.service.IUsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginControllerTest {
    private IUsuarioService usuarioService;
    private HttpServletRequest requestMock;
    private LoginController controller;

    @BeforeEach
    void setUp() {
        usuarioService = mock(IUsuarioService.class);
        requestMock = mock(HttpServletRequest.class);
        controller = new LoginController(usuarioService);
    }

    @Test
    void testGuardarUsuario_RedireccionCorrecta() {
        Usuario usuario = new Usuario();
        String result = controller.guardarUsuario(usuario);

        assertEquals("redirect:/auth/login", result);
        verify(usuarioService).guardarUsuario(usuario);
    }

    @Test
    void testCambiarContrasena_CuandoNoCoinciden() {
        // Crear un mock de RedirectAttributes
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        String result = controller.cambiarContrasena("usuario", "123", "456", requestMock, redirectAttributes);

        assertEquals("redirect:/auth/cambioContrasena", result);
        // Verificar que se llamó al método addAttribute con los parámetros correctos
        verify(redirectAttributes).addAttribute("error", "Las contraseñas no coinciden");
    }

    @Test
    void testCambiarContrasena_UsuarioNoEncontrado() {
        // Crear un mock de RedirectAttributes
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        when(usuarioService.obtenerUsuario("usuario")).thenReturn(null);

        String result = controller.cambiarContrasena("usuario", "abc", "abc", requestMock, redirectAttributes);

        assertEquals("redirect:/auth/cambioContrasena", result);
        // Verificar que se llamó al método addAttribute con los parámetros correctos
        verify(redirectAttributes).addAttribute("error", "Usuario no encontrado");
    }

    @Test
    void testCambiarContrasena_Exito() {
        // Crear un mock de RedirectAttributes
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        Usuario usuario = new Usuario();
        when(usuarioService.obtenerUsuario("usuario")).thenReturn(usuario);

        HttpSession sessionMock = mock(HttpSession.class);
        when(requestMock.getSession(false)).thenReturn(sessionMock);

        String result = controller.cambiarContrasena("usuario", "abc", "abc", requestMock, redirectAttributes);

        assertEquals("redirect:/auth/login", result);
        // Verificar que se llamó al método addAttribute con los parámetros correctos
        verify(redirectAttributes).addAttribute("success", "Contraseña cambiada exitosamente");
        verify(usuarioService).actualizarContraseña(usuario);
        verify(sessionMock).invalidate();
    }
}
