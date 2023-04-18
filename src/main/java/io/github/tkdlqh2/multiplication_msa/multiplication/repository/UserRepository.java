package io.github.tkdlqh2.multiplication_msa.multiplication.repository;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User> findByAlias(String alias);
}
