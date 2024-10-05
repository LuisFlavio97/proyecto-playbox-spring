package com.cibertec.models.service;

import java.util.List;

import com.cibertec.models.entity.Videojuego;

public interface IVideojuegoService {
	
	public List<Videojuego> listarTodos();
	
	public void guardar(Videojuego videojuego);

	public Videojuego buscarPorId(Integer Id);
	
	public void eliminar(Integer Id);
}
