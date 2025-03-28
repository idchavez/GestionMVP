package com.gestionmvp.persistence.repository;

import com.gestionmvp.persistence.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
