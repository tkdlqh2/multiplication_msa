package io.github.tkdlqh2.multiplication_msa.multiplication.service;

import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService {
	private final Random rand = SecureRandom.getInstanceStrong();
	static final int MINIMUM_FACTOR = 11;
	static final int MAXIMUM_FACTOR = 99;

	public RandomGeneratorServiceImpl() throws NoSuchAlgorithmException {
		//기본 생성자
	}

	@Override
	public int generateRandomFactor() {
		return this.rand.nextInt((MAXIMUM_FACTOR - MINIMUM_FACTOR) + 1) + MINIMUM_FACTOR;
	}
}
