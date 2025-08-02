package com.cibertec.models.service;

import java.util.List;

import com.cibertec.models.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.models.entity.Videojuego;
import com.cibertec.models.repository.VideojuegoRepository;

@Service
public class VideojuegoServiceImpl implements IVideojuegoService {
	
	@Autowired
	private VideojuegoRepository videojuegoRepository;

	@Autowired
	private TransaccionRepository transaccionRepository;

	@Override
	public List<Videojuego> listarTodos() {
		// TODO Auto-generated method stub
		return videojuegoRepository.findByActivoTrue();
	}

	@Override
	public void guardar(Videojuego videojuego) {
		// TODO Auto-generated method 
		videojuegoRepository.save(videojuego);	
	}

	@Override
	public Videojuego buscarPorId(Integer Id) {
		// TODO Auto-generated method stub
		return videojuegoRepository.findById(Id).orElse(null);
	}

	@Override
	public void eliminar(Integer Id) {
		// TODO Auto-generated method stub
		videojuegoRepository.deleteById(Id);
	}

	@Override
	public void anular(int id) {
		Videojuego videojuego = videojuegoRepository.findById(id).orElseThrow();
		videojuego.setActivo(false);
		videojuegoRepository.save(videojuego);
	}

	@Override
	public boolean tieneTransacciones(int idVideojuego) {
		return transaccionRepository.existsByIdvideojuego(idVideojuego);
	}
}
