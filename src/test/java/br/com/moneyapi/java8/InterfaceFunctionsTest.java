package br.com.moneyapi.java8;

import static org.junit.Assert.assertEquals;

import java.util.function.Function;

import org.junit.Test;

public class InterfaceFunctionsTest {

	@Test
	public void testName() throws Exception {
		Function<String, Integer> converter = Integer::valueOf;
		
		Integer convertido = converter.apply("10");
		
		assertEquals(10, convertido, 0);
	}
}
