package io.github.tkdlqh2.multiplication_msa.multiplication.service;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.Multiplication;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.MultiplicationResultAttempt;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.User;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.dto.MultiplicationResultAttemptDto;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.dto.MultiplicationResultAttemptInput;
import io.github.tkdlqh2.multiplication_msa.multiplication.repository.MultiplicationResultAttemptRepository;
import io.github.tkdlqh2.multiplication_msa.multiplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MultiplicationServiceImpl implements MultiplicationService{

	private final RandomGeneratorService randomGeneratorService;
	private final MultiplicationResultAttemptRepository attemptRepository;
	private final UserRepository userRepository;

	@Override
	public Multiplication createRandomMultiplication() {
		int factorA = randomGeneratorService.generateRandomFactor();
		int factorB = randomGeneratorService.generateRandomFactor();

		return new Multiplication(factorA,factorB);
	}

	@Transactional
	@Override
	public MultiplicationResultAttemptDto checkAttempt(final MultiplicationResultAttemptInput attempt) {
		// 해당 닉네임의 사용자가 존재하는지 확인
		Optional<User> user = userRepository.findByAlias(attempt.alias());

		// 답안을 채점
		boolean isCorrect = attempt.resultAttempt() ==
				attempt.multiplication().getFactorA() *
						attempt.multiplication().getFactorB();

		MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
				user.orElse(new User(attempt.alias())),
				attempt.multiplication(),
				attempt.resultAttempt(),
				isCorrect
		);

		// 답안을 저장
		return new MultiplicationResultAttemptDto(attemptRepository.save(checkedAttempt));
	}

	@Override
	public List<MultiplicationResultAttemptDto> getStatsForUser(String userAlias) {
		Optional<User> optionalUser = userRepository.findByAlias(userAlias);

		if(optionalUser.isPresent()){
			User user = optionalUser.get();
			Pageable pageable = PageRequest.of(0,5, Sort.by(Sort.Order.desc("Id")));
			return attemptRepository.findAllByUser(user,pageable).getContent().stream()
					.map(MultiplicationResultAttemptDto::new)
					.toList();
		}

		return Collections.emptyList();
	}
}
