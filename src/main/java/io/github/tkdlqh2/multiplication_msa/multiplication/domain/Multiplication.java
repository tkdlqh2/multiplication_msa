package io.github.tkdlqh2.multiplication_msa.multiplication.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 애플리케이션에서 곱셈을 나타내는 클래스
 */
@RequiredArgsConstructor
@Getter
@ToString
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Multiplication {

	@Id
	@GeneratedValue
	@Column(name = "MULTIPLICATION_ID")
	private Long id;

	// 인수
	private final int factorA;
	private final int factorB;

	public Multiplication() {
		this.factorA = 0;
		this.factorB = 0;
	}
}