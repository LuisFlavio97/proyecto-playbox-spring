package com.cibertec.models.repository;

import com.cibertec.dto.UsuarioResumenDto;
import com.cibertec.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Usuario findByNomusuario(String nomusuario);
    @Query("SELECT new com.cibertec.dto.UsuarioResumenDto(u.idusuario, u.nombres, u.apellidos, u.email, u.nomusuario, r.idrol, r.nomrol) " +
            "FROM Usuario u JOIN u.roles r WHERE u.activo = true")
    List<UsuarioResumenDto> obtenerUsuariosActivos();
}
