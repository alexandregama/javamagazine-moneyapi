package br.com.moneyapi;

import java.util.Locale;

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
		CurrencyUnit real = Monetary.getCurrency(new Locale("pt", "BR"));
		CurrencyUnit dolar = Monetary.getCurrency(new Locale("en", "US"));
		
		ExchangeRateProvider provider = MonetaryConversions.getExchangeRateProvider("ECB");
		
		MonetaryAmount money = Money.of(1, dolar);
		
		CurrencyConversion conversor = provider.getCurrencyConversion(real);
		MonetaryAmount dolarParaReal = conversor.apply(money);
		
		System.out.println(dolarParaReal);
	}
	
}
