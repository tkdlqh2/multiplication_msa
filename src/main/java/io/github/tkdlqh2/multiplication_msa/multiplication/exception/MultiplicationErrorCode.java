package io.github.tkdlqh2.multiplication_msa.multiplication.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum MultiplicationErrorCode {

	ALREADY_CHECKED("채점한 상태로 보낼 수 없습니다!!",HttpStatus.BAD_REQUEST);

	private final String message;
	private final HttpStatus status;
}
