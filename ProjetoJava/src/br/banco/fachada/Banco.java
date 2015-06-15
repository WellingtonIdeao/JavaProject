package br.banco.fachada;
import java.util.List;

import br.banco.modelo.Conta;
import br.banco.modelo.ContaBancaria;
import br.banco.modelo.excessoes.ContaJaCadastradaException;
import br.banco.modelo.excessoes.ContaNaoEncontradaException;

public class Banco {
	ContaBancaria contabancaria;
	
	public Banco (ContaBancaria contaBancaria){
		this.contabancaria = contaBancaria; 
	}
	public void cadastrarConta(Conta conta) throws ContaJaCadastradaException{
		 this.contabancaria.cadastrarConta(conta);
	}
	public void modificarConta(Conta conta) throws ContaNaoEncontradaException{
		this.contabancaria.modificarConta(conta);
	}
	public List<Conta> listarContas(){
		return this.contabancaria.listarContas();
	}
	public void excluirConta(String id) throws ContaNaoEncontradaException {
		this.contabancaria.excluirConta(id);
	}
	public Conta buscarConta(String string) throws ContaNaoEncontradaException{
		return this.contabancaria.buscarConta(string);
	}

}
