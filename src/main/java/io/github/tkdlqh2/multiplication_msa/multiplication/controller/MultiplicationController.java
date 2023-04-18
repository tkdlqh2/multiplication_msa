package io.github.tkdlqh2.multiplication_msa.multiplication.controller;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.Multiplication;
import io.github.tkdlqh2.multiplication_msa.multiplication.service.MultiplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 곱셈 애플리케이션의 REST API 를 구현한 클래스
 */
@RestController
@RequestMapping("/multiplications")
@RequiredArgsConstructor
public class MultiplicationController {

	private final MultiplicationService multiplicationService;

	@GetMapping("/random")
	Multiplication getRandomMultiplication(){
		return multiplicationService.createRandomMultiplication();
	}
}
