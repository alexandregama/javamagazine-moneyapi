package br.com.moneyapi;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class MapTest {

	@Test
	public void deveriaTransformarOsTextosEmUppercase() {
		List<String> nomes = Arrays.asList("Alexandre", "Gama");

		List<String> nomesEmMaiusculo = nomes.stream().map(String::toUpperCase).collect(Collectors.toList());

		assertEquals("ALEXANDRE", nomesEmMaiusculo.get(0));
		assertEquals("GAMA", nomesEmMaiusculo.get(1));
	}

}
