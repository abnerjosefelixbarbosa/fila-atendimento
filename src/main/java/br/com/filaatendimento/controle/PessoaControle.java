package br.com.filaatendimento.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.filaatendimento.modelo.Pessoa;
import br.com.filaatendimento.servico.PessoaServico;

@RestController
public class PessoaControle {
	@Autowired
	private PessoaServico pessoaServico;
	
	@GetMapping("/pessoas")
	public ResponseEntity<List<Pessoa>> listarPessoas() {
		return ResponseEntity.status(200).body(pessoaServico.listarPessoas());
	}
	
	@GetMapping("/pessoas/{id}")
	public ResponseEntity<Pessoa> listarPessoaId(@PathVariable Long id) {
		if (pessoaServico.listarPessoaId(id) == null) {
			return ResponseEntity.status(404).body(pessoaServico.listarPessoaId(id));
		}
		
		return ResponseEntity.status(200).body(pessoaServico.listarPessoaId(id));
	}
	
	@PostMapping("/pessoas")
	public ResponseEntity<Pessoa> criarPessoas(@RequestBody Pessoa pessoa) {
		return ResponseEntity.status(201).body(pessoaServico.criarPessoa(pessoa));
	}
	
	@PutMapping("/pessoas/{id}")
	public ResponseEntity<Pessoa> alterarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		if (pessoaServico.alterarPessoa(id, pessoa) == null) {
			return ResponseEntity.status(404).body(pessoaServico.alterarPessoa(id, pessoa));
		}
		
		return ResponseEntity.status(200).body(pessoaServico.alterarPessoa(id, pessoa));
	}
	
	@DeleteMapping("/pessoas/{id}")
	public ResponseEntity<Pessoa> removerPessoas(@PathVariable Long id) {
		if (pessoaServico.removerPessoa(id) == null) {
			return ResponseEntity.status(404).body(pessoaServico.removerPessoa(id));
		}
		
		return ResponseEntity.status(200).body(pessoaServico.removerPessoa(id));
	}
}
