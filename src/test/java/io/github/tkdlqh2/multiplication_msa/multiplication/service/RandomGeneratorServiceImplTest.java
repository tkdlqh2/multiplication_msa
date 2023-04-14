package io.github.tkdlqh2.multiplication_msa.multiplication.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class RandomGeneratorServiceImplTest {

	@InjectMocks
	private RandomGeneratorServiceImpl randomGeneratorServiceImpl;

	@Test
	void generateRandomFactorIsBetweenExpectedLimits() throws Exception {
		// 무작위 숫자를 생성
		List<Integer> randomFactors = IntStream.range(0, 1000)
				.map(i -> randomGeneratorServiceImpl.generateRandomFactor())
				.boxed().collect(Collectors.toList());

		// 적당히 어려운 계산을 만들기 위해서
		// 생성한 인수가 11~99 범위에 있는지 확인
		assertThat(randomFactors).containsOnlyElementsOf(IntStream.range(11, 100)
				.boxed().collect(Collectors.toList()));
	}
}