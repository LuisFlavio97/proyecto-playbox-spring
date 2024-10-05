package com.cibertec.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.models.entity.Videojuego;
import com.cibertec.models.repository.VideojuegoRepository;

@Service
public class VideojuegoServiceImpl implements IVideojuegoService {
	
	@Autowired
	private VideojuegoRepository videojuegoRepository;

	@Override
	public List<Videojuego> listarTodos() {
		// TODO Auto-generated method stub
		return (List<Videojuego>) videojuegoRepository.findAll();
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

}
