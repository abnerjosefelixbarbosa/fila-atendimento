package br.com.filaatendimento.servico;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.filaatendimento.modelo.Pessoa;
import br.com.filaatendimento.repositorio.PessoaRepositorio;

@Service
public class PessoaServico {
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	public Pessoa criarPessoa(Pessoa pessoa) {
		return pessoaRepositorio.save(pessoa);
	}
	
	public List<Pessoa> listarPessoas() {
		return pessoaRepositorio.findAll();
	}
	
	public Pessoa listarPessoaId(Long id) {
		if (!pessoaRepositorio.existsById(id)) {
			return null;
		}
		
		return pessoaRepositorio.findById(id).get();
	}
	
	public Pessoa alterarPessoa(Long id, Pessoa pessoa) {
		if (!pessoaRepositorio.existsById(id)) {
			return null;
		}
		
		Pessoa pessoaProcurada = pessoaRepositorio.findById(id).get();
		BeanUtils.copyProperties(pessoa, pessoaProcurada);
		return pessoaRepositorio.save(pessoaProcurada);
	}
	
	public Pessoa removerPessoa(Long id) {
		if (!pessoaRepositorio.existsById(id)) {
			return null;
		} 
		
		pessoaRepositorio.deleteById(id);
		return new Pessoa();
	}
}
