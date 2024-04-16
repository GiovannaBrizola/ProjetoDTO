package com.examploDTO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examploDTO.dto.LivroDTO;
import com.examploDTO.entities.Livro;
import com.examploDTO.service.LivroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
	public class LivroController {
	private final LivroService livroservice;
	
	@Autowired
	public LivroController (LivroService livroservice) {
	this.livroservice = livroservice;
}
	@PostMapping
	public ResponseEntity<LivroDTO> criar (@RequestBody @Valid Livro livro){
		LivroDTO salvarLivro = livroservice.salvar(livro);
	return ResponseEntity.status(HttpStatus.CREATED).body (salvarLivro);
	}
	
	@PutMapping("/(id)")
	public ResponseEntity<LivroDTO> alteraClienteControl (@PathVariable Long id, @RequestBody @Valid Livro livro) {
	LivroDTO alteraLivroDTO = livroservice.atualizar (id, livro);
		if(alteraLivroDTO != null) {
		return ResponseEntity.ok(alteraLivroDTO);
		}
		else {
		return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Livro> apagaClienteControl (@PathVariable Long id) {
		boolean apagar = livroservice.deletar(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> buscarLivroPorId (@PathVariable Long id) {
		Livro livro = livroservice.buscarPorld(id);
		if (livro != null) {
			return ResponseEntity.ok(livro);
			}
		else {
			return ResponseEntity.notFound().build();
			}
		}
		@GetMapping
		public ResponseEntity<List<Livro>> buscaTodosLivros () {
			List<Livro> Livro = livroservice.buscarTodos();
			return ResponseEntity.ok(Livro);
		}
}