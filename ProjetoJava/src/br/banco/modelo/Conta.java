package br.banco.modelo;
import br.banco.modelo.excessoes.SaldoInsuficienteException;
import br.banco.modelo.excessoes.ValorInvalidoException;

public abstract class Conta implements Comparable {
	protected float saldo;
	protected String numero;
	protected String NomeCliente;

	public Conta(String nome, String numero){
		this.NomeCliente = nome;
		this.numero = numero;
	}
	public void setSaldo(float saldo){
		this.saldo = saldo;
	} 
	public float getSaldo() {
		return saldo;
	}
	public void setNumero(String numero){
		this.numero = numero;
	}
	public String getNumero() {
		return this.numero;
	}
	public void setNomeCliente(String nomeCliente) {
		NomeCliente = nomeCliente;
	}
	public String getNomeCliente() {
		return NomeCliente;
	}
	
	public void creditar(float valor) throws ValorInvalidoException {
		if(valor>0)
			this.saldo += valor;
		else
			throw new ValorInvalidoException("Valor inválido, insira um valor maior que zero");
			
	}
	public void debitar(float valor) throws SaldoInsuficienteException {
		if (this.saldo>=valor && valor>0) 	// impossibilita o cliente retirar o que não possui de saldo.
			this.saldo -= valor;
		else
			throw  new SaldoInsuficienteException("Valor invalido, valor negativo ou saldo insuficiente");
	}
	
	@Override
	public int compareTo(Object arg0) {
		 Conta outra = (Conta)arg0;
		 return this.numero.compareTo(outra.getNumero());
	}
	
	@Override
	public String toString() {
		return "Usuario: "+this.NomeCliente+"\nNumero da Conta: "+this.numero+"\nSaldo: "+this.saldo+"\n\n";
	}
	
}
