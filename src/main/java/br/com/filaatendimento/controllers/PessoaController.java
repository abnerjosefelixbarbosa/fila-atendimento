package br.com.filaatendimento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.filaatendimento.models.Pessoa;
import br.com.filaatendimento.services.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public ResponseEntity<?> encontrarTodos() {
		try {
			return ResponseEntity.status(200).body(pessoaService.encontrarTodos());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("erro em encontrar todas as pessoas");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> encontrarPeloId(@PathVariable Long id) {
		try {
			Pessoa pessoaEncontrada = pessoaService.encontrarPeloId(id);
			if (pessoaEncontrada == null) {
				return ResponseEntity.status(404).body("pessoa não encontrada");
			}
			
			return ResponseEntity.status(200).body(pessoaEncontrada);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("erro em encontrar pessoa pelo id");
		}	
	}
	
	@PostMapping
	public ResponseEntity<?> criar(@RequestBody Pessoa pessoa) {
		try {
			String pessoaValida = pessoaService.validacaoCriar(pessoa);
			if (!pessoaValida.isEmpty()) {
				return ResponseEntity.status(400).body(pessoaValida);
			}
			
			pessoaService.criar(pessoa);
			return ResponseEntity.status(200).body("pessoa criada");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("erro em criar pessoa");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> alterarPosicao(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		try {
			return ResponseEntity.status(200).body("pessoa alterada");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("erro em alterar posição");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarPeloId(@PathVariable Long id) {
		try {
			pessoaService.deletarPeloId(id);
			return ResponseEntity.status(200).body("pessoa deletada");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("erro em deletar pessoa pelo id");
		}
	}
}
