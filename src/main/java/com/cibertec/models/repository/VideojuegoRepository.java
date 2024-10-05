package com.cibertec.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.models.entity.Videojuego;

@Repository
public interface VideojuegoRepository extends CrudRepository<Videojuego, Integer>{

}
