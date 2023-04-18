package io.github.tkdlqh2.multiplication_msa.multiplication.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class GlobalExceptionHandler {

	@ExceptionHandler(MultiplicationException.class)
	public ResponseEntity<String> handleMultiplicationException(MultiplicationException e){
		return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getErrorCode().getMessage());
	}
}
