package io.github.tkdlqh2.multiplication_msa.multiplication.domain;

import lombok.Getter;

/**
 * 애플리케이션에서 곱셈을 나타내는 클래스
 */
@Getter
public class Multiplication {

	// 인수
	private final int factorA;
	private final int factorB;

	// A * B 의 결과
	private final int result;

	public Multiplication(int factorA, int factorB) {
		this.factorA = factorA;
		this.factorB = factorB;
		this.result = factorA * factorB;
	}

	@Override
	public String toString() {
		return "Multiplication{" +
				"인수A=" + factorA +
				", 인수B=" + factorB +
				", 결과(A*B)=" + result +
				'}';
	}

}