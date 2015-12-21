package br.com.moneyapi;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRateProvider;
import javax.money.convert.MonetaryConversions;

import org.javamoney.moneta.Money;
import org.junit.Test;

public class ConversaoDeMoedaTest {

	@Test
	public void deveriaConverterUmDolarEmReal() throws Exception {
		CurrencyUnit dolar = Monetary.getCurrency("USD");
		
		ExchangeRateProvider provider = MonetaryConversions.getExchangeRateProvider("ECB");
		CurrencyConversion realConverter = provider.getCurrencyConversion("BRL");
		
		MonetaryAmount umDolar = Money.of(1, dolar);
		
		MonetaryAmount umDolarEmReal = umDolar.with(realConverter);

		System.out.println(umDolarEmReal);
	}
}
