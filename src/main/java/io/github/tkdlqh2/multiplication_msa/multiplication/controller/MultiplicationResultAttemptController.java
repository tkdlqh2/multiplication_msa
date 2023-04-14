package io.github.tkdlqh2.multiplication_msa.multiplication.controller;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.MultiplicationResultAttempt;
import io.github.tkdlqh2.multiplication_msa.multiplication.service.MultiplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 사용자가 POST 로 답안을 전송하도록 REST API 를 제공하는 클래스
 */
@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class MultiplicationResultAttemptController {

	private final MultiplicationService multiplicationService;

	@PostMapping
	ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt multiplicationResultAttempt) {
		boolean isCorrect = multiplicationService.checkAttempt(multiplicationResultAttempt);
		MultiplicationResultAttempt attemptCopy = new MultiplicationResultAttempt(
				multiplicationResultAttempt.getUser(),
				multiplicationResultAttempt.getMultiplication(),
				multiplicationResultAttempt.getResultAttempt(),
				isCorrect
		);
		return ResponseEntity.ok(attemptCopy);
	}

	@GetMapping
	ResponseEntity<List<MultiplicationResultAttempt>> getStatistics(@RequestParam("alias") String alias) {
		return ResponseEntity.ok(
				multiplicationService.getStatsForUser(alias)
		);
	}
}
