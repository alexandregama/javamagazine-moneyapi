package br.com.moneyapi;

import static org.junit.Assert.*;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.junit.Test;

public class QueriesDeComparacaoComMonetaryFunctions {

	@Test
	public void deveriaVerificarSeUmPrecoEMaiorQueOutro() throws Exception {
		CurrencyUnit real = Monetary.getCurrency("BRL");
		
		MonetaryAmount dezReais = Money.of(10, real);
		MonetaryAmount vinteReais = Money.of(20, real);
		
		assertTrue(vinteReais.isGreaterThan(dezReais));
	}
	
	@Test
	public void deveriaVerificarSeUmPrecoEMenorQueOutro() throws Exception {
		CurrencyUnit real = Monetary.getCurrency("BRL");
		
		MonetaryAmount dezReais = Money.of(10, real);
		MonetaryAmount vinteReais = Money.of(20, real);
		
		assertTrue(dezReais.isLessThan(vinteReais));
	}
	
	@Test
	public void deveriaVerificarSeUmPrecoEPositivoENegativo() throws Exception {
		CurrencyUnit real = Monetary.getCurrency("BRL");
		
		MonetaryAmount dezReais = Money.of(10, real);
		MonetaryAmount menosDezReais = Money.of(-10, real);
		
		assertTrue(dezReais.isPositive());
		assertTrue(menosDezReais.isNegative());
	}
	
}
