package com.cibertec.dto;

import java.time.LocalDateTime;

public class TransaccionDetalleDto {

    private String usuarioNombre;
    private String videojuegoNombre;
    private String tipoMovimiento;
    private Integer cantidad;
    private LocalDateTime fecha;

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getVideojuegoNombre() {
        return videojuegoNombre;
    }

    public void setVideojuegoNombre(String videojuegoNombre) {
        this.videojuegoNombre = videojuegoNombre;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
