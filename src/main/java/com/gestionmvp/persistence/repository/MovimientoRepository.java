package com.gestionmvp.persistence.repository;

import com.gestionmvp.persistence.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}
