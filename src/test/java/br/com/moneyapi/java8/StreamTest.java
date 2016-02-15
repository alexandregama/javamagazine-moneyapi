package br.com.moneyapi.java8;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamTest {

	@Test
	public void deveriaFiltrarOsNomesPelaLetraA() throws Exception {
		List<String> nomes = Arrays.asList("Alexandre", "Bruna", "Fernando", "Augusto", "Sergio", "Paulo");
		
		List<String> nomesComLetraA = nomes
			.stream()
			.filter((s) -> s.startsWith("A"))
			.collect(Collectors.toList());
		
		assertEquals("Alexandre", nomesComLetraA.get(0));
		assertEquals("Augusto", nomesComLetraA.get(1));
	}
	
	@Test
	public void deveriaOrdenarOsNomesEFiltrarPelaPrimeiraLetra() throws Exception {
		List<String> nomes = Arrays.asList("Alexandre", "Adalberto", "Antonio", "Aecio", "Acacio", "Bruna");
		
		List<String> nomesComLetraA = nomes
				.stream()
				.filter((s) -> s.startsWith("A"))
				.sorted()
				.collect(Collectors.toList());
		
		System.out.println(nomesComLetraA);
		assertEquals("Acacio", nomesComLetraA.get(0));
		assertEquals("Adalberto", nomesComLetraA.get(1));
		assertEquals("Aecio", nomesComLetraA.get(2));
		assertEquals("Alexandre", nomesComLetraA.get(3));
		assertEquals("Antonio", nomesComLetraA.get(4));
	}
	
}
