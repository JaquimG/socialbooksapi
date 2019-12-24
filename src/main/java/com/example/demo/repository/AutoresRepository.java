package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long>{

}
