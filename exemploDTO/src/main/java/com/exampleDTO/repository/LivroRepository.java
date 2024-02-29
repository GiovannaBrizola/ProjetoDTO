package com.exampleDTO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exampleDTO.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro,Long> {
}
