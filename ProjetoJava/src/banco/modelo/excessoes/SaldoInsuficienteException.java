package banco.modelo.excessoes;

public class SaldoInsuficienteException extends Exception {
	
	public SaldoInsuficienteException(String msg){
		super(msg);
	}

}
