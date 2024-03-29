package br.com.filaatendimento.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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
	//@Disabled
	public void encontrarTodasPessoas() throws Exception {
		mockMvc.perform(get("/pessoas")).andDo(print()).andExpect(status().is(200));
	}

	@Test
	@Disabled
	public void encontrarPessoaPeloId() throws Exception {
		mockMvc.perform(get("/pessoas/2")).andDo(print()).andExpect(status().is(200));
	}

	@Test
	@Disabled
	public void criarPessoa() throws Exception {
		Pessoa pessoa1 = new Pessoa();
		pessoa1.setNome("Pessoa 1");
		pessoa1.setIdade(25);
		Pessoa pessoa2 = new Pessoa();
		pessoa2.setNome("Pessoa 2");
		pessoa2.setIdade(30);

		mockMvc.perform(
				post("/pessoas").contentType("application/json").content(objectMapper.writeValueAsString(pessoa2)))
				.andDo(print()).andExpect(status().is(201));
	}

	@Test
	@Disabled
	public void alterarPessoa() throws Exception {
		Pessoa pessoa1 = new Pessoa();
		pessoa1.setNome("Pessoa 1");
		pessoa1.setIdade(25);
		Pessoa pessoa2 = new Pessoa();
		pessoa2.setNome("Pessoa 2");
		pessoa2.setIdade(30);

		mockMvc.perform(
				put("/pessoas/1").contentType("application/json").content(objectMapper.writeValueAsString(pessoa1)))
				.andDo(print()).andExpect(status().is(200));
	}

	@Test
	@Disabled
	public void alterarFila() throws Exception {
		mockMvc.perform(put("/pessoas/alterar_fila/2")).andDo(print()).andExpect(status().is(200));
	}

	@Test
	@Disabled
	public void deletarPessoaPeloId() throws Exception {
		mockMvc.perform(delete("/pessoas/1")).andDo(print()).andExpect(status().is(200));
	}

	@Test
	@Disabled
	public void deletarTodasPessoas() throws Exception {
		mockMvc.perform(delete("/pessoas")).andDo(print()).andExpect(status().is(200));
	}
}
