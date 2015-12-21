package br.com.moneyapi;

import static java.util.stream.Collectors.toList;
import static org.javamoney.moneta.Money.of;
import static org.javamoney.moneta.function.MonetaryFunctions.isBetween;
import static org.javamoney.moneta.function.MonetaryFunctions.isCurrency;
import static org.javamoney.moneta.function.MonetaryFunctions.isGreaterThan;
import static org.javamoney.moneta.function.MonetaryFunctions.isGreaterThanOrEqualTo;
import static org.javamoney.moneta.function.MonetaryFunctions.max;
import static org.javamoney.moneta.function.MonetaryFunctions.min;
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

public class FiltrosDePrecosTest {

	private CurrencyUnit real;
	private CurrencyUnit dolar;

	@Before
	public void before() {
		real = Monetary.getCurrency("BRL");
		dolar = Monetary.getCurrency("USD");
	}
	
	@Test
	public void deveriaFiltrarPeloPrecoMinimoEMaximoDeUmaListaDePrecos() throws Exception {
		MonetaryAmount vinteReais = Money.of(20, real);
		MonetaryAmount dezReais = Money.of(10, real);
		MonetaryAmount noventaReais = Money.of(90, real);
		MonetaryAmount cincoReais = Money.of(5, real);
		
		List<MonetaryAmount> precos = Arrays.asList(vinteReais, dezReais, noventaReais, cincoReais);

		MonetaryAmount precoMinimo = precos.stream().reduce(min()).get();
		MonetaryAmount precoMaximo = precos.stream().reduce(max()).get();
		
		assertEquals(cincoReais, precoMinimo);
		assertEquals(noventaReais, precoMaximo);
	}
	
	@Test
	public void deveriaFiltrarECriarOutraListaPeloPrecoMaiorQueOPrecoDeterminado() throws Exception {
		MonetaryAmount vinteReais = Money.of(20, real);
		MonetaryAmount dezReais = Money.of(10, real);
		MonetaryAmount noventaReais = Money.of(90, real);
		MonetaryAmount cincoReais = Money.of(5, real);
		
		List<MonetaryAmount> precos = Arrays.asList(vinteReais, dezReais, noventaReais, cincoReais);
		
		List<MonetaryAmount> novosPrecos = precos
				.stream()
				.filter(isGreaterThan(of(30, real)))
				.collect(Collectors.toList());
		
		assertEquals(1, novosPrecos.size());
		assertEquals(noventaReais, novosPrecos.get(0));
	}

	@Test
	public void deveriaFiltrarECriarOutraListaPeloPrecoEntreOsPrecosDeterminados() throws Exception {
		MonetaryAmount vinteReais = Money.of(20, real);
		MonetaryAmount dezReais = Money.of(10, real);
		MonetaryAmount noventaReais = Money.of(90, real);
		MonetaryAmount cincoReais = Money.of(5, real);
		
		List<MonetaryAmount> precos = Arrays.asList(vinteReais, dezReais, noventaReais, cincoReais);
		
		List<MonetaryAmount> novosPrecos = precos
				.stream()
				.filter(isBetween(Money.of(10, real), Money.of(50, real)))
				.collect(Collectors.toList());
		
		assertEquals(2, novosPrecos.size());
		assertEquals(vinteReais, novosPrecos.get(0));
		assertEquals(dezReais, novosPrecos.get(1));
	}

	@Test
	public void deveriaFiltrarPorRealECriarUmaNovaListaAcimaDeDeterminadoPrecoERemovendoPrecosDuplicados() throws Exception {
		MonetaryAmount vinteReais = Money.of(20, real);
		MonetaryAmount dezReais = Money.of(10, real);
		MonetaryAmount dezReaisDuplicado = Money.of(10, real);
		MonetaryAmount noventaReais = Money.of(90, real);
		MonetaryAmount cincoReais = Money.of(5, real);
		MonetaryAmount cincoReaisDuplicado = Money.of(5, real);
		MonetaryAmount sessentaDolares = Money.of(60, dolar);
		MonetaryAmount quinzeDolares = Money.of(15, dolar);
		MonetaryAmount quinzeDolaresDuplicado = Money.of(15, dolar);
		
		List<MonetaryAmount> precos = Arrays.asList(vinteReais, dezReais, noventaReais, 
				cincoReais, cincoReaisDuplicado, dezReaisDuplicado, sessentaDolares, quinzeDolares, quinzeDolaresDuplicado);
		
		List<MonetaryAmount> novosPrecos = precos
				.stream()
				.filter(isCurrency(real))
				.filter(isGreaterThanOrEqualTo(Money.of(5, real)))
				.sorted(sortNumber())
				.distinct()
				.collect(toList());
		
		assertEquals(4, novosPrecos.size());
		assertEquals(cincoReais, novosPrecos.get(0));
		assertEquals(dezReais, novosPrecos.get(1));
		assertEquals(vinteReais, novosPrecos.get(2));
		assertEquals(noventaReais, novosPrecos.get(3));
	}

}
