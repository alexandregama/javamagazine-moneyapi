package br.com.moneyapi;

import static java.util.stream.Collectors.toList;
import static org.javamoney.moneta.function.MonetaryFunctions.sortCurrencyUnit;
import static org.javamoney.moneta.function.MonetaryFunctions.sortNumber;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;

public class OrdenacaoDePrecosTest {

	private CurrencyUnit real;
	
	private CurrencyUnit dolar;
	
	@Before
	public void before() {
		real = Monetary.getCurrency("BRL");
		dolar = Monetary.getCurrency("USD");
	}
	
	@Test
	public void deveriaOrdenarOsPrecosDeFormaAscendente() throws Exception {
		MonetaryAmount trintaReais = Money.of(30, real);
		MonetaryAmount vinteReais = Money.of(20, real);
		MonetaryAmount cincoReais = Money.of(5, real);
		MonetaryAmount dezReais = Money.of(10, real);
		
		List<MonetaryAmount> precos = Arrays.asList(trintaReais, vinteReais, cincoReais, dezReais);
		
		List<MonetaryAmount> precosOrdenados = precos.stream().sorted(sortNumber()).collect(Collectors.toList());
		
		assertEquals(cincoReais, precosOrdenados.get(0));
		assertEquals(dezReais, precosOrdenados.get(1));
		assertEquals(vinteReais, precosOrdenados.get(2));
		assertEquals(trintaReais, precosOrdenados.get(3));
	}
	
	@Test
	public void deveriaOrdenarOsPrecosDeMoedasDiferentesDeFormaAscendente() throws Exception {
		MonetaryAmount quatroReais = Money.of(4, real);
		MonetaryAmount oitoDolares = Money.of(8, dolar);
		MonetaryAmount dezReais = Money.of(10, real);
		MonetaryAmount cincoDolares = Money.of(5, dolar);
		MonetaryAmount quarentaReais = Money.of(40, real);
		MonetaryAmount tresDolares = Money.of(3, dolar);
		
		
		List<MonetaryAmount> precos = Arrays.asList(quatroReais, oitoDolares, dezReais, cincoDolares, quarentaReais, tresDolares);
		
		List<MonetaryAmount> precosOrdenados = precos.stream().sorted(sortNumber()).sorted(sortCurrencyUnit()).collect(toList());
		System.out.println(precosOrdenados);

		assertEquals(quatroReais, precosOrdenados.get(0));
		assertEquals(dezReais, precosOrdenados.get(1));
		assertEquals(quarentaReais, precosOrdenados.get(2));
		
		assertEquals(tresDolares, precosOrdenados.get(3));
		assertEquals(cincoDolares, precosOrdenados.get(4));
		assertEquals(oitoDolares, precosOrdenados.get(5));
	}
	
}
