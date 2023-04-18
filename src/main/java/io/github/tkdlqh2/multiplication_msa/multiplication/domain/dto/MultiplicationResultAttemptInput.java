package io.github.tkdlqh2.multiplication_msa.multiplication.domain.dto;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.Multiplication;

public record MultiplicationResultAttemptInput(String alias, Multiplication multiplication, int resultAttempt) {
}
