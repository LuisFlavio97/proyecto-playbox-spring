package com.cibertec.controller;

import com.cibertec.dto.UsuarioResumenDto;
import com.cibertec.models.repository.RolRepository;
import com.cibertec.models.repository.UsuarioRepository;
import com.cibertec.models.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/views/usuarios")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/activos")
    public String listarUsuariosActivos(Model model) {
        List<UsuarioResumenDto> usuarios = usuarioService.listarUsuariosActivos();
        model.addAttribute("usuarios", usuarios);
        return "views/usuarios/listarUsuario";
    }

    @GetMapping("/edit/{id}")
    public String editarUsuario(@PathVariable("id") Integer id, Model model) {
        UsuarioResumenDto usuarioDto = usuarioService.buscarResumenPorId(id);
        if (usuarioDto == null) {
            return "redirect:/views/usuarios/activos";
        }
        model.addAttribute("usuario", usuarioDto);
        model.addAttribute("roles", rolRepository.findAll());
        model.addAttribute("titulo", "Editar Usuario");
        return "views/usuarios/editarUsuario";
    }

    @PostMapping("/save")
    public String guardarUsuario(@ModelAttribute("usuario") UsuarioResumenDto usuarioDto, RedirectAttributes redirectAttributes) {
        usuarioService.actualizarDesdeDto(usuarioDto);
        redirectAttributes.addFlashAttribute("success", "Usuario guardado correctamente");
        return "redirect:/views/usuarios/activos";
    }

    @PostMapping("/anular/{id}")
    public String anularUsuario(@PathVariable("id") Integer id, RedirectAttributes redirect) {
        usuarioService.anularUsuario(id);
        redirect.addFlashAttribute("success", "Usuario anulado correctamente");
        return "redirect:/views/usuarios/activos";
    }
}
