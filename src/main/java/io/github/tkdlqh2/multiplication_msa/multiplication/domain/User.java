package io.github.tkdlqh2.multiplication_msa.multiplication.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 사용자 정보를 저장하는 클래스
 */
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@ToString
@Entity
public final class User {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id;
	private final String alias;

	// JSON (역)직렬화를 위한 빈 생성자
	protected User() {
		alias = null;
	}
}
