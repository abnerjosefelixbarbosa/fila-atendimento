package br.com.filaatendimento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.filaatendimento.models.Pessoa;
import br.com.filaatendimento.services.PessoaService;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listarPessoas() {
		return ResponseEntity.status(200).body(pessoaService.listarPessoas());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> procurarPessoaPeloId(@PathVariable Long id) {
		return ResponseEntity.status(200).body(pessoaService.listarPessoaId(id));
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
		return ResponseEntity.status(201).body(pessoaService.criarPessoa(pessoa));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> alterarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		if (pessoaService.alterarPessoa(id, pessoa) == null) {
			return ResponseEntity.status(404).body(pessoaService.alterarPessoa(id, pessoa));
		}
		
		return ResponseEntity.status(200).body(pessoaService.alterarPessoa(id, pessoa));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pessoa> removerPessoas(@PathVariable Long id) {
		if (pessoaService.removerPessoa(id) == null) {
			return ResponseEntity.status(404).body(pessoaService.removerPessoa(id));
		}
		
		return ResponseEntity.status(200).body(pessoaService.removerPessoa(id));
	}
}
