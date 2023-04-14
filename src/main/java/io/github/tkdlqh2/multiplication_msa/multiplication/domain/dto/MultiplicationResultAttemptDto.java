package io.github.tkdlqh2.multiplication_msa.multiplication.domain.dto;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.Multiplication;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.MultiplicationResultAttempt;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.User;

public record MultiplicationResultAttemptDto(Long id, User user, Multiplication multiplication,
											 int resultAttempt, boolean correct) {

	public MultiplicationResultAttemptDto(MultiplicationResultAttempt attempt){
		this(attempt.getId(), attempt.getUser(),attempt.getMultiplication()
		,attempt.getResultAttempt(),attempt.isCorrect()) ;
	}
}
