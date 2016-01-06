package br.com.moneyapi.java8;

import org.junit.Test;

public class LambdaExpressionTest {

	@Test
	public void deveriaImprimirUmaMensagemUtilizandoThreadsDeFormaClassica() throws Exception {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Thread de forma clássica");
			}
		};
		
		new Thread(runnable).start();
	}
	
	@Test
	public void deveriaImprimirUmaMensagemUtilizandoThreadsComLambdaExpression() throws Exception {
		Runnable runnable = () -> System.out.println("Thread utilizando Lambda!");
		
		new Thread(runnable).start();
	}
	
}