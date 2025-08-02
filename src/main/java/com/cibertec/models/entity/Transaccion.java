package com.cibertec.models.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "transaccion")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtransaccion")
    private Integer idtransaccion;

    @Column(name = "idvideojuego", nullable = false)
    private Integer idvideojuego;

    @Column(name = "idusuario", nullable = false)
    private Integer idusuario;

    @Column(name = "cantidadmovidad")
    private Integer cantidadmovidad;

    @Column(name = "tipomovimiento", length = 300)
    private String tipomovimiento;

    @Column(name = "fecha", length = 300)
    private LocalDateTime fecha;

    public Integer getIdtransaccion() {
        return idtransaccion;
    }

    public void setIdtransaccion(Integer idtransaccion) {
        this.idtransaccion = idtransaccion;
    }

    public Integer getIdvideojuego() {
        return idvideojuego;
    }

    public void setIdvideojuego(Integer idvideojuego) {
        this.idvideojuego = idvideojuego;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getCantidadmovidad() {
        return cantidadmovidad;
    }

    public void setCantidadmovidad(Integer cantidadmovidad) {
        this.cantidadmovidad = cantidadmovidad;
    }

    public String getTipomovimiento() {
        return tipomovimiento;
    }

    public void setTipomovimiento(String tipomovimiento) {
        this.tipomovimiento = tipomovimiento;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Transaccion(Integer idvideojuego, Integer idusuario, Integer cantidadmovidad, String tipomovimiento, LocalDateTime fecha) {
        this.idtransaccion = idtransaccion;
        this.idvideojuego = idvideojuego;
        this.idusuario = idusuario;
        this.cantidadmovidad = cantidadmovidad;
        this.tipomovimiento = tipomovimiento;
        this.fecha = fecha;
    }

    public Transaccion(Integer idtransaccion, Integer idvideojuego, Integer idusuario, Integer cantidadmovidad, String tipomovimiento, LocalDateTime fecha) {
        this.idtransaccion = idtransaccion;
        this.idvideojuego = idvideojuego;
        this.idusuario = idusuario;
        this.cantidadmovidad = cantidadmovidad;
        this.tipomovimiento = tipomovimiento;
        this.fecha = fecha;
    }

    public Transaccion(){}

    @Override
    public String toString() {
        return "Transaccion{" +
                "idtransaccion=" + idtransaccion +
                ", idvideojuego=" + idvideojuego +
                ", idusuario=" + idusuario +
                ", cantidadmovidad=" + cantidadmovidad +
                ", tipomovimiento='" + tipomovimiento + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
