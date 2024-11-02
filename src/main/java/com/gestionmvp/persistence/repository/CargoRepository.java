package com.gestionmvp.persistence.repository;

import com.gestionmvp.persistence.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
