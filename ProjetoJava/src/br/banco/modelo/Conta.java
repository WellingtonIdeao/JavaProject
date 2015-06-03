package br.banco.modelo;
import br.banco.modelo.excessoes.SaldoInsuficienteException;
import br.banco.modelo.excessoes.ValorInvalidoException;

public abstract class Conta {
	protected float saldo;
	protected String numero;
	protected String NomeCliente;

	public Conta(String nome){
		this.NomeCliente = nome;
	}
	public String getNomeCliente() {
		return NomeCliente;
	}
	public void setSaldo(float saldo){
		this.saldo = saldo;
	} 
	public float getSaldo() {
		return saldo;
	}
	public void setNumero(int id){
		this.numero =String.valueOf(id);
	}
	public String getNumero() {
		return numero;
	}
	
	
	public void creditar(float valor) throws ValorInvalidoException {
		if(valor>0)
			this.saldo += valor;
		else
			throw new ValorInvalidoException("Valor invalido, insira um valor maior que zero");
			
	}
	public void debitar(float valor) throws SaldoInsuficienteException {
		if (valor <= this.saldo) 	// impossibilita o cliente retirar o que não possui de saldo.
			this.saldo -= valor;
		else
			throw  new SaldoInsuficienteException("Valor solicitado para debito maior que o saldo atual da conta");
	}

	
	@Override
	public String toString() {
		return "Usuario: "+this.NomeCliente+"\nNumero da Conta: "+this.numero+"\nSaldo: "+this.saldo+"\n\n";
	}
	
}
