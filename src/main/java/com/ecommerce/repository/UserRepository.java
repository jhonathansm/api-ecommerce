package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.domain.Usuario;


public interface UserRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT u from user u WHERE u.email = ?1 AND u.password = ?2")
	public Optional<Usuario> login(String nome, String password);
	
	@Query("SELECT u from user u WHERE u.email = ?1")
	public Optional<Usuario> findByEmail(String email);
}
