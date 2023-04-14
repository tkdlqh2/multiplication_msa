package io.github.tkdlqh2.multiplication_msa.multiplication.repository;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.MultiplicationResultAttempt;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultiplicationResultAttemptRepository extends JpaRepository<MultiplicationResultAttempt,Long> {
	Page<MultiplicationResultAttempt> findAllByUser(User user, Pageable pageable);
}
