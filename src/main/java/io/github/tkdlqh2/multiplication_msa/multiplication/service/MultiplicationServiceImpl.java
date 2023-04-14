package io.github.tkdlqh2.multiplication_msa.multiplication.service;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.Multiplication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MultiplicationServiceImpl implements MultiplicationService{

	private final RandomGeneratorService randomGeneratorService;

	@Override
	public Multiplication createRandomMultiplication() {
		int factorA = randomGeneratorService.generateRandomFactor();
		int factorB = randomGeneratorService.generateRandomFactor();

		return new Multiplication(factorA,factorB);
	}
}
