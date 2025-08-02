package com.cibertec.models.service;

import com.cibertec.dto.UsuarioSecurityDto;
import com.cibertec.models.entity.Rol;
import com.cibertec.models.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DetalleUsuarioService implements UserDetailsService {
    private final IUsuarioService usuarioService;

    // Constructor requerido para inicializar los campos final
    public DetalleUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.obtenerUsuario(username);
        return obtenerUsuario(usuario,obtenerAuthority(usuario.getRoles()));
    }

    private List<GrantedAuthority> obtenerAuthority(Set<Rol> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Rol role:roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getNomrol()));
        }
        return authorities;
    }

    private UserDetails obtenerUsuario(Usuario usuario,List<GrantedAuthority> authorities) {
        UsuarioSecurityDto usuarioSecurityDto = new UsuarioSecurityDto(
                usuario.getNomusuario(), usuario.getPassword(), usuario.getActivo(),
                true,true,true,
                authorities
        );
        usuarioSecurityDto.setEmail(usuario.getEmail());
        usuarioSecurityDto.setIdusuario(usuario.getIdusuario());
        return usuarioSecurityDto;
    }
}
