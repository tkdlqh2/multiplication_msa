package io.github.tkdlqh2.multiplication_msa.multiplication.repository;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
