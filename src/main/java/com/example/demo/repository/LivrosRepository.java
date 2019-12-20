package com.example.demo.repository;

import com.example.demo.domain.Livro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrosRepository extends JpaRepository<Livro, Long>{

	
	Optional<Livro> findById(Long id);

	
}
