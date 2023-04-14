package io.github.tkdlqh2.multiplication_msa.multiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.MultiplicationResultAttempt;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.dto.MultiplicationResultAttemptDto;
import io.github.tkdlqh2.multiplication_msa.multiplication.service.MultiplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MultiplicationResultAttemptController.class)
class MultiplicationResultAttemptControllerTest {

	@MockBean
	private MultiplicationService multiplicationService;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void genericParameterizedSuccessTest() throws Exception {
		// given
		given(multiplicationService.checkAttempt(any()))
				.willReturn(new MultiplicationResultAttemptDto(null,null,0,true));
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt();

		// when
		// then
		mvc.perform(post("/results")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(
								attempt
						)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.correct").value(true))
				.andDo(print());
	}

	@Test
	void genericParameterizedFailTest() throws Exception {
		// given
		given(multiplicationService.checkAttempt(any()))
				.willReturn(new MultiplicationResultAttemptDto(null,null,0,false));
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt();

		// when
		// then
		mvc.perform(post("/results")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(
								attempt
						)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.correct").value(false))
				.andDo(print());
	}

	@Test
	void getStatistics() throws Exception {
		//given
		String userAlias = "user1";
		given(multiplicationService.getStatsForUser(userAlias)).willReturn(
				List.of(new MultiplicationResultAttemptDto(null,null,1000,true),
						new MultiplicationResultAttemptDto(null,null,1000,false)));

		//when
		//then
		mvc.perform(get("/results?alias="+ userAlias)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1]").exists())
				.andExpect(jsonPath("$[2]").doesNotExist())
				.andExpect(jsonPath("$[0].resultAttempt").value(1000))
				.andExpect(jsonPath("$[0].correct").value(true))
				.andExpect(jsonPath("$[1].resultAttempt").value(1000))
				.andExpect(jsonPath("$[1].correct").value(false))
				.andDo(print());
	}
}