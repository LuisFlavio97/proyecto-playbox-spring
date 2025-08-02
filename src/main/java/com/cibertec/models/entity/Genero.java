package com.cibertec.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_genero")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero")
    private Integer id_genero;

    @Column(name = "descripcion",length = 300)
    private String descripcion;

    // Getters y Setters
    public Integer getId_genero() {
        return id_genero;
    }

    public void setId_genero(Integer id_genero) {
        this.id_genero = id_genero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
