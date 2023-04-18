package io.github.tkdlqh2.multiplication_msa.multiplication.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

/**
 * 애플리케이션에서 곱셈을 나타내는 클래스
 */
@Getter
@ToString
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Multiplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MULTIPLICATION_ID")
	private Long id;

	// 인수
	private int factorA;
	private int factorB;

	public Multiplication() {
		this.factorA = 0;
		this.factorB = 0;
	}

	public Multiplication(int factorA,int factorB){
		this.factorA = factorA;
		this.factorB = factorB;
	}
}