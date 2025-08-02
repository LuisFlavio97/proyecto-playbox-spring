package com.cibertec.models.service;

import com.cibertec.models.entity.Genero;
import com.cibertec.models.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService implements IGeneroService{
    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public List<Genero> listarGeneros() {
        return generoRepository.findAll();
    }

    @Override
    public Genero findById(Integer id) {
        return generoRepository.findById(id).orElseThrow();
    }
}
