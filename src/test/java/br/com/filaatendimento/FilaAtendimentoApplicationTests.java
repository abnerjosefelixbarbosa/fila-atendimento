package br.com.filaatendimento;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class FilaAtendimentoApplicationTests {	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void listarPessoa() throws Exception {
		String url = String.format("/pessoas");	
		MvcResult mvcResult = mockMvc.perform(get(url)).andReturn();
			
		Assertions.assertEquals(mvcResult.getResponse().getStatus(), 200);
	}
	
	

}
