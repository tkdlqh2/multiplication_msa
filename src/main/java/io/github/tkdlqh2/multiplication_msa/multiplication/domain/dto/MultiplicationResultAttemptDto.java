package io.github.tkdlqh2.multiplication_msa.multiplication.domain.dto;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.MultiplicationResultAttempt;

public record MultiplicationResultAttemptDto(Long userId, Long multiplicationId,
											 int resultAttempt, boolean correct) {

	public MultiplicationResultAttemptDto(MultiplicationResultAttempt attempt){
		this(attempt.getUser().getId(),attempt.getMultiplication().getId()
		,attempt.getResultAttempt(),attempt.isCorrect()) ;
	}
}
