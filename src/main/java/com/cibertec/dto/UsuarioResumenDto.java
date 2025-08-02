package com.cibertec.dto;

public class UsuarioResumenDto {
    private Integer idusuario;
    private String nombres;
    private String apellidos;
    private String email;
    private String nomusuario;
    private Integer idrol;
    private String nomrol;

    public UsuarioResumenDto(Integer idusuario, String nombres, String apellidos, String email, String nomusuario, Integer idrol, String nomrol) {
        this.idusuario = idusuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.nomusuario = nomusuario;
        this.idrol = idrol;
        this.nomrol = nomrol;
    }

    // Getters y setters

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomusuario() {
        return nomusuario;
    }

    public void setNomusuario(String nomusuario) {
        this.nomusuario = nomusuario;
    }

    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public String getNomrol() {
        return nomrol;
    }

    public void setNomrol(String nomrol) {
        this.nomrol = nomrol;
    }
}
