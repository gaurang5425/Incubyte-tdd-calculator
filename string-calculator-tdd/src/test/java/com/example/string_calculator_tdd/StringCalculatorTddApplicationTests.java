package com.example.string_calculator_tdd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StringCalculatorTddApplicationTests {

	@Test
	public void should_return_0_for_empty_string() {
		StringCalculator calculator = new StringCalculator();
		assertEquals(0, calculator.add(""));
	}

	@Test
	public void should_return_number_for_single_number() {
		StringCalculator calculator = new StringCalculator();
		assertEquals(1, calculator.add("1"));
		assertEquals(5, calculator.add("5"));
	}
	@Test
	public void should_return_sum_for_two_numbers() {
		StringCalculator calculator = new StringCalculator();
		assertEquals(3, calculator.add("1,2"));
		assertEquals(10, calculator.add("4,6"));
	}

	@Test
	public void should_handle_multiple_numbers() {
		StringCalculator calculator = new StringCalculator();
		assertEquals(15, calculator.add("1,2,3,4,5"));
		assertEquals(21, calculator.add("1,2,3,4,5,6"));
	}
	@Test
	public void should_handle_newline_delimiter() {
		StringCalculator calculator = new StringCalculator();
		assertEquals(6, calculator.add("1\n2,3"));
		assertEquals(10, calculator.add("1\n2\n3\n4"));
	}
}
