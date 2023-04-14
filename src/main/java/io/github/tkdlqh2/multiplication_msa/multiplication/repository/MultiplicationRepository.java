package io.github.tkdlqh2.multiplication_msa.multiplication.repository;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.Multiplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultiplicationRepository extends JpaRepository<Multiplication,Long> {
}
