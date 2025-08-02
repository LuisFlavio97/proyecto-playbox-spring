package com.cibertec.models.service;

import com.cibertec.dto.TransacUsuarioJuegoDto;
import com.cibertec.dto.TransaccionDetalleDto;
import com.cibertec.models.entity.Transaccion;
import com.cibertec.models.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionServiceImpl implements ITransaccionService{

    @Autowired
    private TransaccionRepository transaccionRepository;
    @Autowired
    private UsuarioService usuarioServiceImp;
    @Autowired
    private VideojuegoServiceImpl videojuegoService;




    @Override
    public List<TransaccionDetalleDto> findAllTransaccion() {

        List<Transaccion> transaccionList = transaccionRepository.findAll();
        List<TransaccionDetalleDto> tdetalleDtoList = new ArrayList<>();

        for (Transaccion t:
             transaccionList) {
            TransaccionDetalleDto transaccionDetalleDto = new TransaccionDetalleDto();
            transaccionDetalleDto.setUsuarioNombre(usuarioServiceImp.findByIdUsuario(t.getIdusuario()).orElseThrow().getNomusuario());
            transaccionDetalleDto.setVideojuegoNombre(videojuegoService.buscarPorId(t.getIdvideojuego()).getNombre());
            transaccionDetalleDto.setTipoMovimiento(t.getTipomovimiento());
            transaccionDetalleDto.setFecha(t.getFecha());
            transaccionDetalleDto.setCantidad(t.getCantidadmovidad());
            tdetalleDtoList.add(transaccionDetalleDto);
        }

        return tdetalleDtoList;
    }

    @Transactional
    @Override
    public Optional<Transaccion> saveTransaccion( TransacUsuarioJuegoDto transacUsuarioJuegoDto) {
        Transaccion nuevaTransaccion = new Transaccion(
                transacUsuarioJuegoDto.getVidejoJuegoId(),
                transacUsuarioJuegoDto.getUsuarioId(),
                transacUsuarioJuegoDto.getCantMovida(),
                transacUsuarioJuegoDto.getTipoMoviento(),
                LocalDateTime.now()
                );

        Transaccion transaccionGuardada = transaccionRepository.save(nuevaTransaccion);

        return Optional.ofNullable(transaccionGuardada);
    }
}
