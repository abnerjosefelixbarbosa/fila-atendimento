package br.com.filaatendimento.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.filaatendimento.models.Pessoa;
import br.com.filaatendimento.repositories.PessoaRepository;

@Service
public class PessoaService {
	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> encontrarTodasPessoas() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return pessoas;
	}

	public Pessoa encontrarPessoaPeloId(Long id) throws Exception {
		Pessoa pessoaEncontrada = pessoaRepository.findById(id).orElse(null);
		if (pessoaEncontrada == null)
			throw new Exception("pessoa não encontrada");

		return pessoaEncontrada;
	}

	@Transactional
	public String criarPessoa(Pessoa pessoa) throws Exception {
		String pessoaVerificada = verificarCriarPessoa(pessoa);
		if (!pessoaVerificada.equals("pessoa verificada"))
			throw new Exception(pessoaVerificada);

		Integer tamanhoFila = encontrarTodasPessoas().size();
		String filaVerificada = verificarAdicionarFila(tamanhoFila);
		if (!filaVerificada.equals("fila verificada"))
			throw new Exception(filaVerificada);

		pessoa.setId(tamanhoFila.longValue() + 1);
		pessoa.setPosicao(tamanhoFila + 1);
		pessoaRepository.save(pessoa);
		return "pessoa criada";
	}

	private String verificarCriarPessoa(Pessoa pessoa) {
		if (pessoa.getNome() == null || pessoa.getNome().isEmpty() || pessoa.getNome().length() > 100
				|| pessoaRepository.existsByNome(pessoa.getNome()))
			return "nome invalido";
		if (pessoa.getIdade() == null)
			return "idade invalida";

		return "pessoa verificada";
	}

	private String verificarAdicionarFila(Integer tamanhoFila) {
		if (tamanhoFila >= 1000)
			return "fila esgotada";

		return "fila verificada";
	}

	@Transactional
	public String alterarPessoa(Long id, Pessoa pessoa) throws Exception {
		String pessoaVerificada = verificarAlterarPessoa(pessoa);
		if (!pessoaVerificada.equals("pessoa verificada"))
			throw new Exception(pessoaVerificada);

		Pessoa pessoaEncontrada = encontrarPessoaPeloId(id);
		if (pessoaEncontrada == null)
			throw new Exception("pessoa não encontrada");

		pessoaEncontrada.setNome(pessoa.getNome());
		pessoaEncontrada.setIdade(pessoa.getIdade());
		pessoaRepository.save(pessoaEncontrada);
		return "pessoa alterada";
	}
	
	private String verificarAlterarPessoa(Pessoa pessoa) {
		if (pessoa.getNome() == null || pessoa.getNome().isEmpty() || pessoa.getNome().length() > 100
				|| pessoaRepository.existsByNome(pessoa.getNome()))
			return "nome invalido";
		if (pessoa.getIdade() == null)
			return "idade invalida";

		return "pessoa verificada";
	}

	public String alterarFila(Long id) throws Exception {
		Pessoa pessoaEncontrada = encontrarPessoaPeloId(id);
		if (pessoaEncontrada == null)
			throw new Exception("pessoa não encontrada");

		Integer tamanhoFila = encontrarTodasPessoas().size();
		String pessoaVerificada = verificarAlterarFila(tamanhoFila, pessoaEncontrada.getPosicao());
		if (!pessoaVerificada.equals("fila verificada"))
			throw new Exception(pessoaVerificada);

		deletarPeloId(tamanhoFila.longValue());
		return "fila alterada";
	}
	
	private String verificarAlterarFila(Integer tamanhoFila, Integer posicao) {
		if (tamanhoFila != posicao)
			return "não é o fim da fila";

		return "fila verificada";
	}

	public void deletarPeloId(Long id) throws Exception  {
		Pessoa pessoaEncontrada = encontrarPessoaPeloId(id);
		if (pessoaEncontrada == null)
			throw new Exception("pessoa não encontrada");
		
		if (pessoaRepository.existsById(id))
			pessoaRepository.deleteById(id);
	}

	public void deletarTodasPessoas() {
		pessoaRepository.deleteAllInBatch();
	}
}
