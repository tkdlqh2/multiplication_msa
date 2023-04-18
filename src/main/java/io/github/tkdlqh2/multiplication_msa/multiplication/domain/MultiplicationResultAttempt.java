package io.github.tkdlqh2.multiplication_msa.multiplication.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;


@NoArgsConstructor
@Getter
@ToString
@Entity
public class MultiplicationResultAttempt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USER_ID")
	private User user;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "MULTIPLICATION_ID")
	private Multiplication multiplication;
	private int resultAttempt;
	private boolean correct;

	public MultiplicationResultAttempt(User user,Multiplication multiplication, int resultAttempt, boolean correct) {
		this.user = user;
		this.multiplication = multiplication;
		this.resultAttempt = resultAttempt;
		this.correct = correct;
	}
}

