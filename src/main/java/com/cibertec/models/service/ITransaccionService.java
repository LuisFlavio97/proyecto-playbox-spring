package com.cibertec.models.service;


import com.cibertec.dto.TransacUsuarioJuegoDto;
import com.cibertec.dto.TransaccionDetalleDto;
import com.cibertec.models.entity.Transaccion;

import java.util.List;
import java.util.Optional;


public interface ITransaccionService {
    List<TransaccionDetalleDto> findAllTransaccion();
    Optional<Transaccion> saveTransaccion(TransacUsuarioJuegoDto transaccion);
}
