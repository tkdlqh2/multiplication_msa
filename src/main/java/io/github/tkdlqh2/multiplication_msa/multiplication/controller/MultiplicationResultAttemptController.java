package io.github.tkdlqh2.multiplication_msa.multiplication.controller;

import io.github.tkdlqh2.multiplication_msa.multiplication.domain.dto.MultiplicationResultAttemptDto;
import io.github.tkdlqh2.multiplication_msa.multiplication.domain.dto.MultiplicationResultAttemptInput;
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
	ResponseEntity<MultiplicationResultAttemptDto> postResult(@RequestBody MultiplicationResultAttemptInput multiplicationResultAttempt) {
		return ResponseEntity.ok(multiplicationService.checkAttempt(multiplicationResultAttempt));
	}

	@GetMapping
	ResponseEntity<List<MultiplicationResultAttemptDto>> getStatistics(@RequestParam("alias") String alias) {
		return ResponseEntity.ok(
				multiplicationService.getStatsForUser(alias)
		);
	}
}
