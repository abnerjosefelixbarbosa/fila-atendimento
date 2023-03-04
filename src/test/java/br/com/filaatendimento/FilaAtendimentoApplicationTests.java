package br.com.filaatendimento;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.filaatendimento.modelo.Pessoa;

@SpringBootTest
@AutoConfigureMockMvc
class FilaAtendimentoApplicationTests {	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Disabled
	public void listarPessoa() throws Exception {
		String url = String.format("/pessoas");	
		MvcResult mvcResult = mockMvc.perform(get(url)).andReturn();
			
		Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void criarPessoa() throws Exception {
		String url = String.format("/pessoas");	
		Pessoa pessoa = new Pessoa();
		pessoa.setId(2L);
		pessoa.setNome("b");
		pessoa.setIdade(2);
		pessoa.setPosicao(2);
		String json = objectMapper.writeValueAsString(pessoa);		
		MvcResult mvcResult = mockMvc.perform(post(url, json)).andReturn();
			
		Assertions.assertEquals(201, mvcResult.getResponse().getStatus());
	}
	
	

}
