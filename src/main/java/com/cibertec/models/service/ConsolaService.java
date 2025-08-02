package com.cibertec.models.service;

import com.cibertec.models.entity.Consola;
import com.cibertec.models.repository.ConsolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsolaService implements IConsolaService{
    @Autowired
    private ConsolaRepository consolaRepository;

    @Override
    public List<Consola> listarConsolas() {
        return consolaRepository.findAll();
    }

    @Override
    public Consola findById(Integer id) {
        return consolaRepository.findById(id).orElseThrow();
    }
}
