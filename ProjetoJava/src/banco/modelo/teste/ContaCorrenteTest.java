package banco.modelo.teste;

import junit.framework.TestCase;
import banco.modelo.ContaCorrente;
import banco.modelo.excessoes.SaldoInsuficienteException;
import banco.modelo.excessoes.ValorInvalidoException;

public class ContaCorrenteTest extends TestCase {

	public void testCreditar() {
		ContaCorrente c1 = new ContaCorrente("1234", 100.00);
		assertEquals(100.00, c1.getSaldo()); 			// testando o saldo atual
		try {
			c1.creditar(160.00); 						// testando o creditar
			assertEquals(260.00, c1.getSaldo());
			c1.creditar(-1.00); 						// testando a inserção de valores negativos
		} catch (ValorInvalidoException e) {
			System.out.println(e);
		}
		assertEquals(260.00, c1.getSaldo()); 			// confirmando que não houve alteração após
	}													// a inserção dos valores negativos

	 													
	public void testDebitar() {
		ContaCorrente c1 = new ContaCorrente("1234", 100.00);
		try {
			c1.debitar(57.00); // debitando...
			assertEquals(43.00, c1.getSaldo()); // confirmando o debito
			c1.debitar(44.00); // debitando mais do que existe no saldo
		} catch (SaldoInsuficienteException e) {
			System.out.println(e);
		}
		assertEquals(43.00, c1.getSaldo()); // confirmando que não houve
											// modificação no saldo
	}

}
