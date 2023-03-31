package br.com.filaatendimento.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pessoas")
public class Pessoa {
	@Id
	private Long id;
	@Column(nullable = false, unique = true, length = 100)
	private String nome;
	@Column(nullable = false)
	private Integer idade;
	@Column(nullable = false, unique = true)
	private Integer posicao;
	
	public Pessoa() {
		super();
	}

	public Pessoa(Long id, String nome, Integer idade, Integer posicao) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.posicao = posicao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Integer getPosicao() {
		return posicao;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", idade=" + idade + ", posicao=" + posicao + "]";
	}
}
