package com.gestionmvp;

import com.gestionmvp.auth.persistence.entity.Permiso;
import com.gestionmvp.auth.persistence.entity.Rol;
import com.gestionmvp.auth.persistence.entity.RoleEnum;
import com.gestionmvp.auth.persistence.entity.Usuario;
import com.gestionmvp.auth.persistence.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class GestionmvpApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionmvpApplication.class, args);
	}

	/*
	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository) {
		return args -> {


			Permiso createPermission = Permiso.builder()
					.nombre("CREATE")
					.build();

			Permiso readPermission = Permiso.builder()
					.nombre("READ")
					.build();

			Permiso updatePermission = Permiso.builder()
					.nombre("UPDATE")
					.build();

			Permiso deletePermission = Permiso.builder()
					.nombre("DELETE")
					.build();

			Permiso refactorPermission = Permiso.builder()
					.nombre("REFACTOR")
					.build();


			Rol roleAdmin = Rol.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permisosLista(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			Rol roleUser = Rol.builder()
					.roleEnum(RoleEnum.USER)
					.permisosLista(Set.of(createPermission, readPermission))
					.build();

			Rol roleDeveloper = Rol.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permisosLista(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();


			Usuario userId = Usuario.builder()
					.username("id")
					.password("$2a$10$u4TtXj0nC86UWWg8DtUoRO/F2tF56AJAW6YpjLwkr6kynG8s2hhC6") //123
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			Usuario userId2 = Usuario.builder()
					.username("id2")
					.password("$2a$10$u4TtXj0nC86UWWg8DtUoRO/F2tF56AJAW6YpjLwkr6kynG8s2hhC6") //123
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			Usuario userId3 = Usuario.builder()
					.username("id3")
					.password("$2a$10$u4TtXj0nC86UWWg8DtUoRO/F2tF56AJAW6YpjLwkr6kynG8s2hhC6") //123
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			usuarioRepository.saveAll(List.of(userId, userId2, userId3));
		};
	}*/
}
