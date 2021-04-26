package com.precourse.baseball;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UserScannerTest {
	private final UserScanner userScanner = new UserScanner();

	@ParameterizedTest
	@CsvSource({"1,1", "5,5", "9,9"})
	@DisplayName("char형 문자 int형으로 변환")
	public void testCharToInt(char input, int output) {
		int number = userScanner.charToInt(input);
		assertThat(number).isEqualTo(output);
	}

	@Test
	@DisplayName("문자열을 리스트로 반환")
	public void testGenerateList() {
		String str = "987";
		List<Integer> numbers = userScanner.generateList(str);
		assertThat(numbers).containsExactly(9, 8, 7);
	}

	@Test
	@DisplayName("1, 2 이외의 값 입력시 에러 발생")
	public void testScanMenuNumberException() {
		assertThatThrownBy(() -> {
			userScanner.checkMenuNum("3");
		}).isInstanceOf(RuntimeException.class)
			.hasMessageContaining("값 범위");
	}

	@Test
	@DisplayName("입력값의 길이가 3이 아닐 경우 에러 발생")
	public void testLengthException() {
		assertThatThrownBy(() -> {
			userScanner.generateList("1234");
		}).isInstanceOf(RuntimeException.class)
			.hasMessageContaining("값 길이");
	}

	@Test
	@DisplayName("입력값에 중복된 값이 있을 경우 에러 발생")
	public void testUniqueException() {
		assertThatThrownBy(() -> {
			userScanner.generateList("778");
		}).isInstanceOf(RuntimeException.class)
			.hasMessageContaining("값 중복");
	}

	@Test
	@DisplayName("입력값에 중복된 값이 있을 경우 에러 발생")
	public void testRangeException() {
		assertThatThrownBy(() -> {
			userScanner.generateList("120");
		}).isInstanceOf(RuntimeException.class)
			.hasMessageContaining("값 범위");
	}
}
