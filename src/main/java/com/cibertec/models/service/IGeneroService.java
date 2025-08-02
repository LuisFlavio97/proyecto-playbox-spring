package com.cibertec.models.service;

import com.cibertec.models.entity.Genero;
import java.util.List;

public interface IGeneroService {
    List<Genero> listarGeneros();
    Genero findById(Integer id);
}
