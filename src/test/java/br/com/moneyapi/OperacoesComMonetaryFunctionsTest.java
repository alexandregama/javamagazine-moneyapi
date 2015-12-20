package br.com.moneyapi;

import static org.javamoney.moneta.function.MonetaryFunctions.sum;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.junit.Test;

public class OperacoesComMonetaryFunctionsTest {

	@Test
	public void deveriaSomarOsPrecosDeFormaConvencional() {
		CurrencyUnit real = Monetary.getCurrency("BRL");
		
		MonetaryAmount dezReais = Money.of(10, real);
		MonetaryAmount vinteReais = Money.of(20, real);
		
		List<MonetaryAmount> precos = Arrays.asList(dezReais, vinteReais);
		
		MonetaryAmount soma = Money.of(0, real);
		for (MonetaryAmount preco: precos) {
			soma = soma.add(preco);
		}
		
		assertEquals(30d, soma.getNumber().doubleValue(), 0);
	}
	
	@Test
	public void deveriaSomarOsPrecosUtilizandoMoneyAPI() throws Exception {
		CurrencyUnit real = Monetary.getCurrency("BRL");
		
		MonetaryAmount dezReais = Money.of(10, real);
		MonetaryAmount vinteReais = Money.of(20, real);
		
		List<MonetaryAmount> precos = Arrays.asList(dezReais, vinteReais);
		
		MonetaryAmount soma = precos.stream().reduce(sum()).get();
		
		assertEquals(30d, soma.getNumber().doubleValue(), 0);
	}

}
