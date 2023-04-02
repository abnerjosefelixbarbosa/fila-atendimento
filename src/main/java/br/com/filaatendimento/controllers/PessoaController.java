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
	public ResponseEntity<?> encontrarTodasPessoas() {
		try {
			return ResponseEntity.status(200).body(pessoaService.encontrarTodasPessoas());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("erro no servidor");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> encontrarPessoaPeloId(@PathVariable Long id) {
		try {
			Pessoa pessoaEncontrada = pessoaService.encontrarPessoaPeloId(id);
			return ResponseEntity.status(200).body(pessoaEncontrada);
		} catch (Exception e) {
			if (e.getMessage().equals("pessoa não encontrada"))
				return ResponseEntity.status(404).body(e.getMessage());

			return ResponseEntity.status(500).body("erro no servidor");
		}
	}

	@PostMapping
	public ResponseEntity<?> criarPessoa(@RequestBody Pessoa pessoa) {
		try {
			return ResponseEntity.status(201).body(pessoaService.criarPessoa(pessoa));
		} catch (Exception e) {
			if (!e.getMessage().equals("pessoa verificada"))
				return ResponseEntity.status(400).body(e.getMessage());
			if (!e.getMessage().equals("fila verificada"))
				return ResponseEntity.status(400).body(e.getMessage());

			return ResponseEntity.status(500).body("erro no servidor");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		try {
			return ResponseEntity.status(200).body(pessoaService.alterarPessoa(id, pessoa));
		} catch (Exception e) {
			if (!e.getMessage().equals("pessoa verificada"))
				return ResponseEntity.status(400).body(e.getMessage());
			if (e.getMessage().equals("pessoa não encontrada"))
				return ResponseEntity.status(404).body(e.getMessage());

			return ResponseEntity.status(500).body("erro no servidor");
		}
	}

	@PutMapping("/alterar_fila/{id}")
	public ResponseEntity<?> alterarFila(@PathVariable Long id) {
		try {
			return ResponseEntity.status(200).body(pessoaService.alterarFila(id));
		} catch (Exception e) {
			if (e.getMessage().equals("pessoa não encontrada"))
				return ResponseEntity.status(404).body(e.getMessage());
			if (!e.getMessage().equals("fila verificada"))
				return ResponseEntity.status(400).body(e.getMessage());

			return ResponseEntity.status(500).body("erro no servidor");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarPeloId(@PathVariable Long id) {
		try {
			pessoaService.deletarPeloId(id);
			return ResponseEntity.status(200).body("pessoa deletada");
		} catch (Exception e) {
			if (e.getMessage().equals("pessoa não encontrada"))
				return ResponseEntity.status(404).body(e.getMessage());

			return ResponseEntity.status(500).body("erro no servidor");
		}
	}

	@DeleteMapping
	public ResponseEntity<?> deletarTodasPessoas() {
		try {
			pessoaService.deletarTodasPessoas();
			return ResponseEntity.status(200).body("pessoas deletadas");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("erro no servidor");
		}
	}
}
