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
	public void cadastrarConta(Conta conta) throws ContaJaCadastradaException{
		this.persistencia.inserir(conta);
	}
	public void modificarConta(Conta conta) throws ContaNaoEncontradaException{
		this.persistencia.atualizar(conta);
	}
	public List<Conta> listarContas(){
		return this.persistencia.listar();
	}
	public void excluirConta(String id) throws ContaNaoEncontradaException {
		this.persistencia.deletar(id);
	}
	public Conta buscarConta(String string) throws ContaNaoEncontradaException{
		return this.persistencia.buscar(string);
	}
}
