package com.exampleDTO.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exampleDTO.dto.LivroDTO;
import com.exampleDTO.entities.Livro;
import com.exampleDTO.repository.LivroRepository;

@Service
public class LivroService {
	private final LivroRepository livroRepository;

	@Autowired
	public LivroService(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}

	// servico de busca de todos os registros
	public List<Livro> buscaTodos() {
		return livroRepository.findAll();
	}

	// servico de busca de registro por id
	public Livro buscaPorId(Long id) {
		return livroRepository.findById(id).orElse(null);
	}

	// servico de salvar livro
	public LivroDTO salvar(Livro livro) {
		Livro salvarLivro = livroRepository.save(livro);
	    return new LivroDTO(salvarLivro.getId(),salvarLivro.getTitulo(),salvarLivro.getAutor());
	}

	// servico de atualizar
	public LivroDTO atualizar(Long id, Livro livro) {
		Livro exiteLivro = livroRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Livro " + " não encontrado"));
		exiteLivro.setTitulo(livro.getTitulo());
		exiteLivro.setAutor(livro.getAutor());
		Livro updateLivro = livroRepository.save(exiteLivro);
		return new LivroDTO(updateLivro.getId(), updateLivro.getTitulo(), updateLivro.getAutor());
	}

	// servico de deletar
	public boolean deletar(Long id) {
		Optional<Livro> exiteLivro = livroRepository.findById(id);
		if (exiteLivro.isPresent()) {
			livroRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
