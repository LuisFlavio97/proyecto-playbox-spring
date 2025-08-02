package com.cibertec.dto;


public class TransacUsuarioJuegoDto {

    private Integer usuarioId;
    private Integer videjoJuegoId;
    private Integer cantMovida;
    private String tipoMoviento;

    public TransacUsuarioJuegoDto(Integer usuarioId, Integer videjoJuegoId, Integer cantMovida, String tipoMoviento) {
        this.usuarioId = usuarioId;
        this.videjoJuegoId = videjoJuegoId;
        this.cantMovida = cantMovida;
        this.tipoMoviento = tipoMoviento;
    }

    public TransacUsuarioJuegoDto(){

    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getVidejoJuegoId() {
        return videjoJuegoId;
    }

    public void setVidejoJuegoId(Integer videjoJuegoId) {
        this.videjoJuegoId = videjoJuegoId;
    }

    public Integer getCantMovida() {
        return cantMovida;
    }

    public void setCantMovida(Integer cantMovida) {
        this.cantMovida = cantMovida;
    }

    public String getTipoMoviento() {
        return tipoMoviento;
    }

    public void setTipoMoviento(String tipoMoviento) {
        this.tipoMoviento = tipoMoviento;
    }

    @Override
    public String toString() {
        return "TransacUsuarioJuegoDto{" +
                "usuarioId=" + usuarioId +
                ", videjoJuegoId=" + videjoJuegoId +
                ", cantMovida=" + cantMovida +
                ", tipoMoviento='" + tipoMoviento + '\'' +
                '}';
    }
}
