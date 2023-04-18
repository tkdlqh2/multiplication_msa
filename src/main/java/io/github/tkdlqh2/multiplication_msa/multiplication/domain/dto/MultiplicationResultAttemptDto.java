package io.github.tkdlqh2.multiplication_msa.multiplication.domain.dto;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.MultiplicationResultAttempt;

public record MultiplicationResultAttemptDto(Long userId, int resultAttempt, boolean correct) {

	public MultiplicationResultAttemptDto(MultiplicationResultAttempt attempt){
		this(attempt.getUser().getId(),attempt.getResultAttempt(),attempt.isCorrect()) ;
	}
}
