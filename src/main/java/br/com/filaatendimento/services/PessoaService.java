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
	
	public String validacaoCriar(Pessoa pessoa) {
		if (pessoa.getNome() == null || pessoa.getNome().isEmpty() || pessoa.getNome().length() > 100
				|| pessoaRepository.existsByNome(pessoa.getNome()))
			return "nome invalido";
		if (pessoa.getIdade() == null)
			return "idade invalida";

		return "";
	}

	public void alterar(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
	}
	
	public String validacaoAlterar(Pessoa pessoa) {
		if (pessoa.getNome() == null || pessoa.getNome().isEmpty() || pessoa.getNome().length() > 100
				|| pessoaRepository.existsByNome(pessoa.getNome()))
			return "nome invalido";
		if (pessoa.getIdade() == null)
			return "idade invalida";

		return "";
	}

	public void deletarPeloId(Long id) {
		if (pessoaRepository.existsById(id))
			pessoaRepository.deleteById(id);
	}

	public void deletarTodos() {
		pessoaRepository.deleteAllInBatch();
	}

	public void adicionarFila(Pessoa pessoa) {
		Integer tamanhoFila = encontrarTodos().size();

		pessoa.setId(tamanhoFila.longValue() + 1);
		pessoa.setPosicao(tamanhoFila + 1);
		pessoaRepository.save(pessoa);
	}

	public String validacaoAdicionarFila(Pessoa pessoa) {
		Integer tamanhoFila = encontrarTodos().size();
		if (tamanhoFila >= 1000)
			return "fila esgotada";

		return "";
	}

	public void alterarFila() {
		Integer tamanhoFila = encontrarTodos().size();		
		deletarPeloId(tamanhoFila.longValue());
	}

	public String validacaoAlterarFila(Pessoa pessoa) {
		Integer tamanhoFila = encontrarTodos().size();
		if (tamanhoFila != pessoa.getPosicao())
			return "não é o fim da fila";

		return "";
	}
}
