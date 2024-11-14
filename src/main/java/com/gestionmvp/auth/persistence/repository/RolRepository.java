package com.gestionmvp.auth.persistence.repository;

import com.gestionmvp.auth.persistence.entity.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends CrudRepository<Rol, Long> {
    List<Rol> findRolByRoleEnumIn(List<String> roles);
}
