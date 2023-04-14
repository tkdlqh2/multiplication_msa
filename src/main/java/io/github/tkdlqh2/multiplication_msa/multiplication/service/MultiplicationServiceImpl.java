package io.github.tkdlqh2.multiplication_msa.multiplication.service;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.Multiplication;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.MultiplicationResultAttempt;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.User;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.dto.MultiplicationResultAttemptDto;
import io.github.tkdlqh2.multiplication_msa.multiplication.exception.MultiplicationException;
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
import java.util.stream.Collectors;

import static io.github.tkdlqh2.multiplication_msa.multiplication.exception.MultiplicationErrorCode.ALREADY_CHECKED;

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
	public boolean checkAttempt(final MultiplicationResultAttempt attempt) {
		// 해당 닉네임의 사용자가 존재하는지 확인
		Optional<User> user = userRepository.findByAlias(attempt.getUser().getAlias());

		// 조작된 답안을 방지
		if(attempt.isCorrect()){
			throw new MultiplicationException(ALREADY_CHECKED);
		}

		// 답안을 채점
		boolean isCorrect = attempt.getResultAttempt() ==
				attempt.getMultiplication().getFactorA() *
						attempt.getMultiplication().getFactorB();

		MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
				user.orElse(attempt.getUser()),
				attempt.getMultiplication(),
				attempt.getResultAttempt(),
				isCorrect
		);

		// 답안을 저장
		attemptRepository.save(checkedAttempt);
		return isCorrect;
	}

	@Override
	public List<MultiplicationResultAttemptDto> getStatsForUser(String userAlias) {
		Optional<User> optionalUser = userRepository.findByAlias(userAlias);

		if(optionalUser.isPresent()){
			User user = optionalUser.get();
			Pageable pageable = PageRequest.of(0,5, Sort.by(Sort.Order.desc("Id")));
			return attemptRepository.findAllByUser(user,pageable).getContent().stream()
					.map(MultiplicationResultAttemptDto::new)
					.collect(Collectors.toList());
		}

		return Collections.emptyList();
	}
}
