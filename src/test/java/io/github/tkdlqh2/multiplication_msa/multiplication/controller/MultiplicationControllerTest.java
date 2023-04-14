package io.github.tkdlqh2.multiplication_msa.multiplication.controller;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.Multiplication;
import io.github.tkdlqh2.multiplication_msa.multiplication.service.MultiplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MultiplicationController.class)
class MultiplicationControllerTest {

	@MockBean
	private MultiplicationService multiplicationService;

	@Autowired
	private MockMvc mvc;

	@Test
	void getRandomMultiplicationTest() throws Exception {
		// given
		given(multiplicationService.createRandomMultiplication())
				.willReturn(new Multiplication(70, 20));

		// when
		// then
		mvc.perform(get("/multiplications/random")
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.factorA").value(70))
				.andExpect(jsonPath("$.factorB").value(20))
				.andExpect(jsonPath("$.result").value(1400))
				.andDo(print());
	}

}