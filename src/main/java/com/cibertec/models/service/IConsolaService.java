package com.cibertec.models.service;

import com.cibertec.models.entity.Consola;
import java.util.List;

public interface IConsolaService {
    List<Consola> listarConsolas();
    Consola findById(Integer id);
}
