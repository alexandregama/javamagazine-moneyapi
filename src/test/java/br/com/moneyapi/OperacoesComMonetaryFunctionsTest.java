package br.com.moneyapi;

import static org.javamoney.moneta.function.MonetaryFunctions.isCurrency;
import static org.javamoney.moneta.function.MonetaryFunctions.sum;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;

public class OperacoesComMonetaryFunctionsTest {
	
	private CurrencyUnit real;
	private CurrencyUnit dolar;

	@Before
	public void before() {
		real = Monetary.getCurrency("BRL");
		dolar = Monetary.getCurrency("USD");
	}

	@Test
	public void deveriaSomarOsPrecosDeFormaConvencional() {
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
		MonetaryAmount dezReais = Money.of(10, real);
		MonetaryAmount vinteReais = Money.of(20, real);
		
		List<MonetaryAmount> precos = Arrays.asList(dezReais, vinteReais);
		
		MonetaryAmount soma = precos.stream().reduce(sum()).get();
		
		assertEquals(30d, soma.getNumber().doubleValue(), 0);
	}

	@Test
	public void deveriaSomarOsPrecosDeMoedasDiferentesDeFormaConvencional() throws Exception {
		MonetaryAmount dezReais = Money.of(10, real);
		MonetaryAmount vinteReais = Money.of(20, real);
		
		MonetaryAmount cincoDolares = Money.of(5, dolar);
		MonetaryAmount quinzeDolares = Money.of(15, dolar);
		
		List<MonetaryAmount> precos = Arrays.asList(dezReais, vinteReais, cincoDolares, quinzeDolares);
		
		MonetaryAmount somaEmReal = Money.of(0, real);
		MonetaryAmount somaEmDolar = Money.of(0, dolar);
		
		for (MonetaryAmount preco: precos) {
			if (preco.getCurrency().equals(real)) {
				somaEmReal = somaEmReal.add(preco);
			} else {
				somaEmDolar = somaEmDolar.add(preco);
			}
		}
		
		assertEquals(30d, somaEmReal.getNumber().doubleValue(), 0);
		assertEquals(20d, somaEmDolar.getNumber().doubleValue(), 0);
	}
	
	@Test
	public void deveriaSomarOsPrecosDeMoedasDiferentesUtilizandoMoneyAPI() throws Exception {
		CurrencyUnit real = Monetary.getCurrency("BRL");
		CurrencyUnit dolar = Monetary.getCurrency("USD");
		
		MonetaryAmount dezReais = Money.of(10, real);
		MonetaryAmount vinteReais = Money.of(20, real);
		
		MonetaryAmount cincoDolares = Money.of(5, dolar);
		MonetaryAmount quinzeDolares = Money.of(15, dolar);
		
		List<MonetaryAmount> precos = Arrays.asList(dezReais, vinteReais, cincoDolares, quinzeDolares);
		
		MonetaryAmount somaEmReal = precos.stream().filter(isCurrency(real)).reduce(sum()).get();
		MonetaryAmount somaEmDolar = precos.stream().filter(isCurrency(dolar)).reduce(sum()).get();
		
		assertEquals(30d, somaEmReal.getNumber().doubleValue(), 0);
		assertEquals(20d, somaEmDolar.getNumber().doubleValue(), 0);
	}
	
}
