package com.felipe.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.app.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByUsername(String username);

	boolean existsByUsername(String username);
}
