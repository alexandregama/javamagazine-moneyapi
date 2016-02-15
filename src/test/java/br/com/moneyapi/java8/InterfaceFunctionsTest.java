package br.com.moneyapi.java8;

import static org.junit.Assert.assertEquals;

import java.util.function.Function;

import org.junit.Test;

public class InterfaceFunctionsTest {

	@Test
	public void deveriaConverterUmaStringEmInteiroUtilizandoFunction() throws Exception {
		Function<String, Integer> converterToInteger = Integer::valueOf;
				
		Integer convertido = converterToInteger.apply("10");
		
		assertEquals(10, convertido, 0);
	}
	
	@Test
	public void deveriaConverterUmaStringEmInteiroERetornarOValorNegativoUtilizandoFunction() throws Exception {
		Function<String, Integer> converterParaInteger = Integer::valueOf;
		Function<String, Integer> converterParaNegativo = converterParaInteger.andThen(Math::negateExact);
		
		
		Integer convertido = converterParaNegativo.apply("10");
		
		assertEquals(-10, convertido, 0);
	}
	
}
