package com.cibertec.models.repository;

import com.cibertec.models.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {
    boolean existsByIdvideojuego(int idvideojuego);
}