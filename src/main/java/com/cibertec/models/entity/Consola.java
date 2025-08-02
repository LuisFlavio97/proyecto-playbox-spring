package com.cibertec.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_consola")
public class Consola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consola")
    private Integer id_consola;

    @Column(name = "descripcion", length = 300)
    private String descripcion;

    // Getters y Setters
    public Integer getId_consola() {
        return id_consola;
    }

    public void setId_consola(Integer id_consola) {
        this.id_consola = id_consola;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
