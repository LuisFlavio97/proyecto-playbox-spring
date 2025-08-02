package com.cibertec.controller;

import com.cibertec.dto.UsuarioSecurityDto;
import com.cibertec.models.entity.Usuario;
import com.cibertec.models.service.IUsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class LoginController {
    private final IUsuarioService usuarioService;

    // Constructor manual para la inyección de dependencias
    public LoginController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "auth/registrar";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario){
        usuarioService.guardarUsuario(usuario);
        return "redirect:/auth/login";
    }

    @GetMapping("/login-success")
    public String loginSuccess(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsuarioSecurityDto usuarioSecurityDto = (UsuarioSecurityDto) userDetails;
        session.setAttribute("nomusuario",usuarioSecurityDto.getUsername());
        return "redirect:/auth/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "home";
    }

    @GetMapping("/cambioContrasena")
    public String cambioContrasena() {
        return "auth/cambiarContrasena";
    }

    @PostMapping("/cambiarContrasena")
    public String cambiarContrasena(@RequestParam String nombreUsuario, @RequestParam String nuevaContrasena,
                                    @RequestParam String confirmarContrasena, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        // Validación de las contraseñas
        if (!nuevaContrasena.equals(confirmarContrasena)) {
            redirectAttributes.addAttribute("error", "Las contraseñas no coinciden");
            return "redirect:/auth/cambioContrasena";
        }

        // Validar usuario
        Usuario usuario = usuarioService.obtenerUsuario(nombreUsuario);
        if (usuario == null) {
            redirectAttributes.addAttribute("error", "Usuario no encontrado");
            return "redirect:/auth/cambioContrasena";
        }

        // Cambiar la contraseña
        usuario.setPassword(nuevaContrasena);
        usuarioService.actualizarContraseña(usuario);

        // Invalidar la sesión
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        redirectAttributes.addAttribute("success", "Contraseña cambiada exitosamente");
        return "redirect:/auth/login";
    }
}
