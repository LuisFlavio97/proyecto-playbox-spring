package com.cibertec.models.service;

import com.cibertec.dto.UsuarioResumenDto;
import com.cibertec.models.entity.Rol;
import com.cibertec.models.entity.Usuario;
import com.cibertec.models.repository.RolRepository;
import com.cibertec.models.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UsuarioService implements IUsuarioService{
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository,RolRepository rolRepository){
        this.usuarioRepository=usuarioRepository;
        this.rolRepository=rolRepository;
    }

    @Override
    public Usuario obtenerUsuario(String nomusuario) {
        System.out.println("Intentando buscar usuario con nombre: '" + nomusuario + "'");
        Usuario usuario = usuarioRepository.findByNomusuario(nomusuario);
        System.out.println("Resultado de la búsqueda: " + (usuario != null ? usuario.toString() : "null"));
        return usuario;
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        usuario.setActivo(true);
        Rol usuarioRol = rolRepository.findByNomrol("USER");
        usuario.setRoles(new HashSet<>(Collections.singleton(usuarioRol)));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByIdUsuario(Integer id) {
        Optional<Usuario> usuario =  usuarioRepository.findById(id);
        return usuario;

    }

    @Override
    public Usuario actualizarContraseña(Usuario usuario) {
        // Encriptar la nueva contraseña
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        // Guardar el usuario con la nueva contraseña, sin afectar los roles ni otros datos
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<UsuarioResumenDto> listarUsuariosActivos() {
        return usuarioRepository.obtenerUsuariosActivos();
    }

    @Override
    public UsuarioResumenDto buscarResumenPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) return null;
        Rol rol = usuario.getRoles().stream().findFirst().orElse(null);
        Integer idrol = rol != null ? rol.getIdrol() : null;
        String nomrol = rol != null ? rol.getNomrol() : "";
        return new UsuarioResumenDto(usuario.getIdusuario(), usuario.getNombres(), usuario.getApellidos(),
                usuario.getEmail(), usuario.getNomusuario(), idrol, nomrol);
    }

    @Override
    public void actualizarDesdeDto(UsuarioResumenDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdusuario()).orElse(null);
        if (usuario != null) {
            usuario.setNombres(dto.getNombres());
            usuario.setApellidos(dto.getApellidos());
            usuario.setEmail(dto.getEmail());
            usuario.setNomusuario(dto.getNomusuario());
            Rol nuevoRol = rolRepository.findById(dto.getIdrol()).orElse(null);
            if (nuevoRol != null) {
                usuario.setRoles(new HashSet<>(Collections.singleton(nuevoRol)));
            }
            usuarioRepository.save(usuario);
        }
    }

    @Override
    @Transactional
    public void anularUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            System.out.println("Antes: " + usuario.getActivo());
            usuario.setActivo(false);
            usuarioRepository.save(usuario);
            System.out.println("Después: " + usuario.getActivo());
        }
    }

}
