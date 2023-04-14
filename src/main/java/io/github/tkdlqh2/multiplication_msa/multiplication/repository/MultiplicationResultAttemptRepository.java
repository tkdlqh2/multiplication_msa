package io.github.tkdlqh2.multiplication_msa.multiplication.repository;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.MultiplicationResultAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultiplicationResultAttemptRepository extends JpaRepository<MultiplicationResultAttempt,Long> {
}
