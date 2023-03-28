package br.com.filaatendimento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.filaatendimento.models.Pessoa;
import br.com.filaatendimento.repositories.PessoaRepository;

@Service
public class PessoaService {
	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> encontrarTodos() {
		return pessoaRepository.findAll();
	}

	public Pessoa encontrarPeloId(Long id) {
		return pessoaRepository.findById(id).orElse(null);
	}

	public void criar(Pessoa pessoa) {
		savar(pessoa);
	}

	public String validacaoCriar(Pessoa pessoa) {
		if (pessoa.getId() == null || encontrarPeloId(pessoa.getId()) != null) {
			return "id invalido";
		}
		if (pessoa.getNome() == null || pessoa.getNome().isEmpty() || pessoa.getNome().length() > 100) {
			return "nome invalido";
		}
		if (pessoa.getIdade() == null) {
			return "idade invalida";
		}
		if (pessoa.getPosicao() == null || pessoaRepository.findByPosicao(pessoa.getPosicao()).isPresent()) {
			return "posição invalida";
		}

		return "";
	}

	public void alterar(Pessoa pessoa) {
		savar(pessoa);
	}

	private void savar(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
	}

	public void deletarPeloId(Long id) {
		if (pessoaRepository.existsById(id)) {
			pessoaRepository.deleteById(id);
		}
	}
}
