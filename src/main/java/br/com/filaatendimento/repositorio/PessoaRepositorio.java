package br.com.filaatendimento.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.filaatendimento.modelo.Pessoa;

@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa, Long> {
	
}
