package com.gestionmvp.persistence.repository;

import com.gestionmvp.persistence.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
