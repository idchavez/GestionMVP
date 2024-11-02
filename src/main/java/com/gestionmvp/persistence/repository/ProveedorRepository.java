package com.gestionmvp.persistence.repository;

import com.gestionmvp.persistence.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}
