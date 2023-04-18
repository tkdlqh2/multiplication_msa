package io.github.tkdlqh2.multiplication_msa.multiplication.domain;

import jakarta.persistence.*;
import lombok.*;


/**
 * 사용자 정보를 저장하는 클래스
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "MEMBERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;

	@Column(unique = true)
	private String alias;

	public User(String alias){
		this.alias = alias;
	}
}
