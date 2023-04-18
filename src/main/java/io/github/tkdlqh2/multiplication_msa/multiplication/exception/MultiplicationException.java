package io.github.tkdlqh2.multiplication_msa.multiplication.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MultiplicationException extends RuntimeException{
	private final MultiplicationErrorCode errorCode;

}
