package br.com.moneyapi;

import static junit.framework.Assert.assertEquals;
import static org.javamoney.moneta.function.MonetaryFunctions.groupByCurrencyUnit;
import static org.javamoney.moneta.function.MonetaryFunctions.groupBySummarizingMonetary;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.javamoney.moneta.function.GroupMonetarySummaryStatistics;
import org.javamoney.moneta.function.MonetarySummaryStatistics;
import org.junit.Before;
import org.junit.Test;

public class AgrupamentoEEstatisticaDePrecosTest {

	private CurrencyUnit real;
	private CurrencyUnit dolar;

	@Before
	public void before() {
		real = Monetary.getCurrency("BRL");
		dolar = Monetary.getCurrency("USD");
	}
	
	@Test
	public void deveriaAgruparOsPrecosPorMoeda() throws Exception {
		MonetaryAmount dezReais = Money.of(10, real);
		MonetaryAmount vinteReais = Money.of(20, real);
		MonetaryAmount trintaReais = Money.of(30, real);
		MonetaryAmount cincoDolares = Money.of(5, dolar);
		MonetaryAmount quinzeDolares = Money.of(15, dolar);

		List<MonetaryAmount> precos = Arrays.asList(dezReais, cincoDolares, vinteReais, trintaReais, quinzeDolares);

		Map<CurrencyUnit, List<MonetaryAmount>> precosAgrupados = precos
				.stream()
				.collect(groupByCurrencyUnit());

		assertEquals(3, precosAgrupados.get(real).size());
		assertEquals(2, precosAgrupados.get(dolar).size());
	}

	@Test
	public void deveriaAgruparMostrandoAEstatisticaDaLista() throws Exception {
		CurrencyUnit real = Monetary.getCurrency("BRL");
		CurrencyUnit dolar = Monetary.getCurrency("USD");
		
		MonetaryAmount dezReais = Money.of(10, real);
		MonetaryAmount vinteReais = Money.of(20, real);
		MonetaryAmount trintaReais = Money.of(30, real);
		MonetaryAmount cincoDolares = Money.of(5, dolar);
		MonetaryAmount quinzeDolares = Money.of(15, dolar);
		
		List<MonetaryAmount> precos = Arrays.asList(dezReais, cincoDolares, vinteReais, trintaReais, quinzeDolares);
		
		GroupMonetarySummaryStatistics estatistica = precos
				.stream()
				.collect(groupBySummarizingMonetary());

		Map<CurrencyUnit, MonetarySummaryStatistics> map = estatistica.get();
		
		MonetarySummaryStatistics estatisticasEmReal = map.get(real);
		MonetarySummaryStatistics estatisticasEmDolar = map.get(dolar);
		
		assertEquals(3, estatisticasEmReal.getCount());
		assertEquals(2, estatisticasEmDolar.getCount());
		assertEquals(dezReais, estatisticasEmReal.getMin());
		assertEquals(trintaReais, estatisticasEmReal.getMax());
		
		assertEquals(Money.of(20, real), estatisticasEmReal.getAverage());
		assertEquals(Money.of(60, real), estatisticasEmReal.getSum());
	}

}
