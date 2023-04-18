package io.github.tkdlqh2.multiplication_msa.multiplication.service;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.Multiplication;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.MultiplicationResultAttempt;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.User;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.dto.MultiplicationResultAttemptDto;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.dto.MultiplicationResultAttemptInput;
import io.github.tkdlqh2.multiplication_msa.multiplication.repository.MultiplicationRepository;
import io.github.tkdlqh2.multiplication_msa.multiplication.repository.MultiplicationResultAttemptRepository;
import io.github.tkdlqh2.multiplication_msa.multiplication.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MultiplicationServiceImplTest {
	@Mock
	private RandomGeneratorService randomGeneratorService;
	@Mock
	private MultiplicationResultAttemptRepository attemptRepository;
	@Mock
	private UserRepository userRepository;
	@Mock
	private MultiplicationRepository multiplicationRepository;
	@InjectMocks
	private MultiplicationServiceImpl multiplicationServiceImpl;

	private static final String USER_ALIAS = "john_doe";

	@Test
	void createRandomMultiplicationTest() {
		// given (randomGeneratorService 가 처음에 50, 나중에 30을 반환하도록 설정)
		given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

		// when
		Multiplication multiplication = multiplicationServiceImpl.createRandomMultiplication();

		// assert
		assertThat(multiplication.getFactorA()).isEqualTo(50);
		assertThat(multiplication.getFactorB()).isEqualTo(30);
	}

	@Test
	void checkCorrectAttemptTest() {
		// given
		Multiplication multiplication = new Multiplication(50, 60);
		MultiplicationResultAttemptInput attempt = new MultiplicationResultAttemptInput(USER_ALIAS, multiplication, 3000);
		given(userRepository.findByAlias(USER_ALIAS))
				.willReturn(Optional.of(new User(1L,USER_ALIAS)));
		given(multiplicationRepository.save(any())).willReturn(multiplication);

		when(attemptRepository.save(any())).thenAnswer(i->i.getArgument(0));

		// when
		MultiplicationResultAttemptDto attemptResult = multiplicationServiceImpl.checkAttempt(attempt);

		// then
		assertTrue(attemptResult.correct());
		verify(attemptRepository,times(1)).save(any());
	}

	@Test
	void checkWrongAttemptTest() {
		// given
		Multiplication multiplication = new Multiplication(50, 60);
		MultiplicationResultAttemptInput attempt = new MultiplicationResultAttemptInput("john_doe", multiplication, 3010);
		given(userRepository.findByAlias(USER_ALIAS))
				.willReturn(Optional.of(new User(1L,USER_ALIAS)));
		when(attemptRepository.save(any())).thenAnswer(i->i.getArgument(0));

		// when
		MultiplicationResultAttemptDto attemptResult = multiplicationServiceImpl.checkAttempt(attempt);

		// then
		assertFalse(attemptResult.correct());
		verify(attemptRepository,times(1)).save(any());
	}

	@Test
	void getStatsForUserTest() {
		// given
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User(1L,"john_doe");
		MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(
				user, multiplication, 3010, false);
		MultiplicationResultAttempt attempt2 = new MultiplicationResultAttempt(
				user, multiplication, 3051, false);
		List<MultiplicationResultAttempt> latestAttempts = List.of(attempt1,attempt2);
		given(userRepository.findByAlias("john_doe")).willReturn(Optional.of(user));
		given(attemptRepository.findAllByUser(user, PageRequest.of(0,5, Sort.by(Sort.Order.desc("Id")))))
				.willReturn(new PageImpl<>(latestAttempts));

		// when
		List<MultiplicationResultAttemptDto> latestAttemptsResult =
				multiplicationServiceImpl.getStatsForUser("john_doe");

		// then
		assertEquals(2,latestAttemptsResult.size());
		assertEquals(1L,latestAttemptsResult.get(0).userId());
		assertEquals(3010,latestAttemptsResult.get(0).resultAttempt());
		assertFalse(latestAttemptsResult.get(0).correct());
		assertEquals(1L,latestAttemptsResult.get(1).userId());
		assertEquals(3051,latestAttemptsResult.get(1).resultAttempt());
		assertFalse(latestAttemptsResult.get(1).correct());
	}
}