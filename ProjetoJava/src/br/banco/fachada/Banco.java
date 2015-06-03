package br.banco.fachada;
import java.util.List;

import br.banco.modelo.Conta;
import br.banco.modelo.ContaBancaria;

public class Banco {
	ContaBancaria contabancaria;
	
	public Banco (ContaBancaria contaBancaria){
		this.contabancaria = contaBancaria; 
	}
	public int cadastrarConta(Conta conta){
		return this.contabancaria.cadastrarConta(conta);
	}
	public void modificarConta(Conta conta){
		this.contabancaria.modificarConta(conta);
	}
	public List<Conta> listarContas(){
		return this.contabancaria.listarContas();
	}
	public void excluirConta(int id){
		this.contabancaria.excluirConta(id);
	}

}
