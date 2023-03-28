package br.com.filaatendimento.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;
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
	public void encontrarTodos() throws Exception {
		mockMvc.perform(get("/pessoas")).andDo(print()).andExpect(status().is(200));
	}
	
	@Test
	@Disabled
	public void encontrarPeloId() throws Exception {
		mockMvc.perform(get("/pessoas/1")).andDo(print()).andExpect(status().is(200));
	}

	@Test
	@Disabled
	public void criar() throws Exception {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(2L);
		pessoa.setNome("Pessoa 2");
		pessoa.setIdade(38);
		pessoa.setPosicao(2);

		mockMvc.perform(
				post("/pessoas").contentType("application/json").content(objectMapper.writeValueAsString(pessoa)))
				.andDo(print()).andExpect(status().is(200));
	}
	
	@Test
	@Disabled
	public void alterarPosicao() {
		
	}
	
	@Test
	@Disabled
	public void deletarPeloId() {
		
	}
	
	@Test
	@Disabled
	public void deletarTodas() {
		
	}
}
