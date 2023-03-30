package br.com.filaatendimento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.filaatendimento.models.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	boolean existsByPosicao(Integer posicao);
}
