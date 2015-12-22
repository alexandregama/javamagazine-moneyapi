package br.com.moneyapi.java8;

import static org.junit.Assert.*;

import java.util.function.Predicate;

import org.junit.Test;

public class InterfacePredicateTest {

	@Test
	public void deveriaIndicarQueOTamanhoDoTextoEMaiorQueZero() throws Exception {
		Predicate<String> predicate = (s) -> s.length() > 0;
		
		boolean textoMaiorQueZero = predicate.test("Alexandre Gama");
		
		assertTrue(textoMaiorQueZero);
	}
	
}
