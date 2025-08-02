package com.cibertec.dto;

import java.util.Objects;

public class UsuarioRequestDto {
    private Integer idusuario;
    private String nomusuario;
    private String email;
    private String password;
    private String nombres;
    private String apellidos;
    private Boolean activo;

    // Constructor vacío
    public UsuarioRequestDto() {
    }

    // Constructor completo
    public UsuarioRequestDto(Integer idusuario, String nomusuario, String email, String password, String nombres, String apellidos, Boolean activo) {
        this.idusuario = idusuario;
        this.nomusuario = nomusuario;
        this.email = email;
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.activo = activo;
    }

    // Getters y Setters
    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNomusuario() {
        return nomusuario;
    }

    public void setNomusuario(String nomusuario) {
        this.nomusuario = nomusuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    // toString
    @Override
    public String toString() {
        return "UsuarioRequestDto{" +
                "idusuario=" + idusuario +
                ", nomusuario='" + nomusuario + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", activo=" + activo +
                '}';
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioRequestDto that = (UsuarioRequestDto) o;
        return Objects.equals(idusuario, that.idusuario) &&
                Objects.equals(nomusuario, that.nomusuario) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(nombres, that.nombres) &&
                Objects.equals(apellidos, that.apellidos) &&
                Objects.equals(activo, that.activo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idusuario, nomusuario, email, password, nombres, apellidos, activo);
    }
}
