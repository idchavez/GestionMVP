package com.gestionmvp.persistence.repository;


import com.gestionmvp.persistence.entity.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {

}
