package banco.modelo;
import banco.modelo.excessoes.SaldoInsuficienteException;
import banco.modelo.excessoes.ValorInvalidoException;

public abstract class Conta {
	protected double saldo;
	protected String numero;
	//  colocar o cliente aqui

	public Conta(String numero, double saldo){
		this.numero = numero;
		this.saldo = saldo;
	}
	
	public abstract void atualizaTaxa(double taxa); // método para sobre escrita, de acordo com tipo de conta
	
	public void creditar(double valor) throws ValorInvalidoException {
		if(valor>0)
			this.saldo += valor;
		else
			throw new ValorInvalidoException("Valor invalido, insira um valor maior que zero");
		
			
	}
	public void debitar(double valor) throws SaldoInsuficienteException {
		if (valor <= this.saldo) 	// impossibilita o cliente retirar o que não possui de saldo.
			this.saldo -= valor;
		else
			throw  new SaldoInsuficienteException("Valor solicitado para debito maior que o saldo atual da conta");
	}

	public double getSaldo() {
		return saldo;
	}

//	public void setSaldo(double saldo) {
//		this.saldo = saldo;
//	}

	public String getNumero() {
		return numero;
	}

//	public void setNumero(String numero) {
//		this.numero = numero;
//	}
	

}
