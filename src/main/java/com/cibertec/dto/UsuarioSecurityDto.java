package com.cibertec.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UsuarioSecurityDto extends User {
    private String email;
    private Integer idusuario;
    public UsuarioSecurityDto(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    // Getter para email
    public String getEmail() {
        return email;
    }

    // Setter para email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter para idusuario
    public Integer getIdusuario() {
        return idusuario;
    }

    // Setter para idusuario
    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }
}
