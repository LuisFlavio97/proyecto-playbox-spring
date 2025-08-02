package com.cibertec.models.service;

import com.cibertec.dto.UsuarioResumenDto;
import com.cibertec.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    Usuario obtenerUsuario(String nomusuario);
    Usuario guardarUsuario(Usuario usuario);
    Optional<Usuario> findByIdUsuario(Integer id);
    Usuario actualizarContraseña(Usuario usuario);
    List<UsuarioResumenDto> listarUsuariosActivos();
    UsuarioResumenDto buscarResumenPorId(Integer id);
    void actualizarDesdeDto(UsuarioResumenDto dto);
    void anularUsuario(Integer id);
}
