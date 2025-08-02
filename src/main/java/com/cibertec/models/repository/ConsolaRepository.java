package com.cibertec.models.repository;

import com.cibertec.models.entity.Consola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsolaRepository extends JpaRepository<Consola, Integer> {
}
