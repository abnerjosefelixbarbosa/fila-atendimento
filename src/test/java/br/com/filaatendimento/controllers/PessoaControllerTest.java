package br.com.filaatendimento.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.filaatendimento.models.Pessoa;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Disabled
	public void listarPessoas() throws Exception {
		final String URL = "/pessoas";
		MvcResult mvcResult = mockMvc.perform(get(URL)).andReturn();

		String json = mvcResult.getResponse().getContentAsString();
		Pessoa[] pessoas = objectMapper.readValue(json, Pessoa[].class);
		System.out.println(pessoas);
	}

	@Test
	@Disabled
	public void criarPessoa() throws Exception {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(1L);
		pessoa.setIdade(23);
		pessoa.setNome("Pessoa 1");
		pessoa.setPosicao(1);
		
		final String URL = "/pessoas";
		String json = objectMapper.writeValueAsString("");
		MvcResult mvcResult = mockMvc.perform(post(URL).contentType("application/json").content(json)).andReturn();
	}
}
