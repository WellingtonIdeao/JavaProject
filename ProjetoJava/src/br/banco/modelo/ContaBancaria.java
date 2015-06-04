package br.banco.modelo;
import java.util.List;

import br.banco.modelo.excessoes.ContaJaCadastradaException;
import br.banco.modelo.excessoes.ContaNaoEncontradaException;
import br.banco.persistencia.IContasBancarias;

public class ContaBancaria {
	
	IContasBancarias persistencia;
	
	public ContaBancaria(IContasBancarias persistencia){
		this.persistencia = persistencia;
	}
	public int cadastrarConta(Conta conta) throws ContaJaCadastradaException{
		return this.persistencia.inserir(conta);
	}
	public void modificarConta(Conta conta){
		this.persistencia.atualizar(conta);
	}
	public List<Conta> listarContas(){
		return this.persistencia.listar();
	}
	public void excluirConta(int id) throws ContaNaoEncontradaException {
		this.persistencia.deletar(id);
	}
}
