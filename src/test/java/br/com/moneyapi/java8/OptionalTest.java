package br.com.moneyapi.java8;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;

public class OptionalTest {

	@Test
	public void deveriaIndicarQueOValorDoObjetoENulo() throws Exception {
		Pessoa pessoa = null;
		
		Optional<Pessoa> optional = Optional.ofNullable(pessoa);
		
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void deveriaIndicarQueOValorDoObjetoNaoENulo() throws Exception {
		Pessoa pessoa = new Pessoa("Alexandre Gama");
		
		Optional<Pessoa> optional = Optional.ofNullable(pessoa);
		
		Pessoa alexandre = null;
		if (optional.isPresent()) {
			alexandre = optional.get();
		}
		
		assertEquals("Alexandre Gama", alexandre.getNome());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void deveriaLancarExecaoAoTentarAcessarUmObjetoNulo() throws Exception {
		Pessoa alexandre = null;
		
		Optional<Pessoa> optional = Optional.ofNullable(alexandre);
		
		optional.get();
	}
	
	@Test
	public void deveriaRetornarUmNovoObjetoPessoaAoTentarAcessarUmObjetoNulo() throws Exception {
		Pessoa alexandre = null;
		
		Optional<Pessoa> optional = Optional.ofNullable(alexandre);
		
		Pessoa pessoa = optional.orElse(new Pessoa("Alexandre"));
		
		assertEquals("Alexandre", pessoa.getNome());
	}
	
}



