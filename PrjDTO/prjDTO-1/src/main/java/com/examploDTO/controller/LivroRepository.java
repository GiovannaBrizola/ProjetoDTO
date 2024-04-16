package com.examploDTO.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examploDTO.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro,Long>{
}
