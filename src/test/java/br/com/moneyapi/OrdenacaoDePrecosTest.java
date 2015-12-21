package br.com.moneyapi;

import static java.util.stream.Collectors.toList;
import static org.javamoney.moneta.function.MonetaryFunctions.sortCurrencyUnit;
import static org.javamoney.moneta.function.MonetaryFunctions.sortNumber;
import static org.junit.Assert.assertEquals;

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
		MonetaryAmount trintaReais = Money.of(30, real);
		MonetaryAmount vinteReais = Money.of(20, real);
		MonetaryAmount cincoReais = Money.of(5, real);
		
		MonetaryAmount vinteDolares = Money.of(20, dolar);
		MonetaryAmount dezDolares = Money.of(10, dolar);
		MonetaryAmount cincoDolares = Money.of(5, dolar);
		
		List<MonetaryAmount> precos = Arrays.asList(trintaReais, vinteReais, cincoDolares, vinteDolares, cincoReais, dezDolares);
		
		List<MonetaryAmount> precosOrdenados = precos.stream().sorted(sortNumber()).sorted(sortCurrencyUnit()).collect(toList());

		assertEquals(cincoReais, precosOrdenados.get(0));
		assertEquals(vinteReais, precosOrdenados.get(1));
		assertEquals(trintaReais, precosOrdenados.get(2));
		
		assertEquals(cincoDolares, precosOrdenados.get(3));
		assertEquals(dezDolares, precosOrdenados.get(4));
		assertEquals(vinteDolares, precosOrdenados.get(5));
	}
}
