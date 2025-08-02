package com.cibertec;

import com.cibertec.controller.UsuarioController;
import com.cibertec.dto.UsuarioResumenDto;
import com.cibertec.models.repository.RolRepository;
import com.cibertec.models.repository.UsuarioRepository;
import com.cibertec.models.service.IUsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUsuarioService usuarioService;

    @MockBean
    private RolRepository rolRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    void testListarUsuariosActivos() throws Exception {
        UsuarioResumenDto usuario1 = new UsuarioResumenDto(
                1,
                "Carlos",
                "Manrique",
                "carlos@mail.com",
                "Carlos123",
                2,
                "Administrador"
        );
        Mockito.when(usuarioService.listarUsuariosActivos()).thenReturn(Collections.singletonList(usuario1));

        mockMvc.perform(get("/views/usuarios/activos").with(user("admin").roles("ADMIN"))) // o cualquier rol válido
                .andExpect(status().isOk())
                .andExpect(view().name("views/usuarios/listarUsuario"))
                .andExpect(model().attributeExists("usuarios"))
                .andExpect(model().attribute("usuarios", hasSize(1)));
        System.out.println("✅ testListarUsuariosActivos pasó correctamente.");
    }

    @Test
    void testEditarUsuarioExistente() throws Exception {
        UsuarioResumenDto dto = new UsuarioResumenDto(
                1,
                "Carlos",
                "Manrique",
                "carlos@mail.com",
                "Carlos123",
                2,
                "Administrador"
        );
        Mockito.when(usuarioService.buscarResumenPorId(1)).thenReturn(dto);
        Mockito.when(rolRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/views/usuarios/edit/1").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("views/usuarios/editarUsuario"))
                .andExpect(model().attributeExists("usuario"))
                .andExpect(model().attributeExists("roles"));

        System.out.println("✅ testEditarUsuarioExistente pasó correctamente.");
    }

    @Test
    void testEditarUsuarioNoExistente() throws Exception {
        Mockito.when(usuarioService.buscarResumenPorId(99)).thenReturn(null);

        mockMvc.perform(get("/views/usuarios/edit/99").with(user("admin").roles("ADMIN")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/views/usuarios/activos"));

        System.out.println("✅ testEditarUsuarioNoExistente pasó correctamente.");
    }

    @Test
    void testGuardarUsuario() throws Exception {
        mockMvc.perform(post("/views/usuarios/save").with(csrf())
                        .param("id", "1")
                        .param("nombre", "Carlos")
                        .param("email", "carlos@mail.com")
                        .param("password", "123")
                        .param("activo", "true")
                        .param("idrol", "1")
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/views/usuarios/activos"));
        System.out.println("✅ testGuardarUsuario pasó correctamente.");
    }

    @Test
    void testAnularUsuario() throws Exception {
        mockMvc.perform(post("/views/usuarios/anular/1").with(csrf())
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/views/usuarios/activos"));
        System.out.println("✅ testAnularUsuario pasó correctamente.");
    }
}
