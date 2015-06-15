package br.banco.modelo.teste;

import junit.framework.TestCase;
import br.banco.modelo.ContaCorrente;
import br.banco.modelo.excessoes.SaldoInsuficienteException;
import br.banco.modelo.excessoes.ValorInvalidoException;

public class ContaCorrenteTest extends TestCase {

	public void testCreditar() {
		ContaCorrente c1 = new ContaCorrente("joao","12345");
		 			                                    // testando o saldo atual
		try {
			c1.creditar(100.00f);
			assertEquals(100.00f, c1.getSaldo());
			c1.creditar(160.00f); 						// testando o creditar
			assertEquals(260.00f, c1.getSaldo());
			c1.creditar(-1.00f); 						// testando a inserção de valores negativos
		} catch (ValorInvalidoException e) {
			System.out.println(e);
		}
		assertEquals(260.00f, c1.getSaldo()); 			// confirmando que não houve alteração após
	}													// a inserção dos valores negativos

	 													
	public void testDebitar() {
		ContaCorrente c1 = new ContaCorrente("joao","12345");
		try {
			c1.creditar(100.00f);
			c1.debitar(57.00f); // debitando...
			assertEquals(43.00f, c1.getSaldo()); // confirmando o debito
			c1.debitar(44.00f); // debitando mais do que existe no saldo
		} catch (SaldoInsuficienteException e) {
			System.out.println(e);
		} catch (ValorInvalidoException e) {
			System.out.println(e);
		}
		assertEquals(43.00f, c1.getSaldo()); // confirmando que não houve
											// modificação no saldo
	}
}
