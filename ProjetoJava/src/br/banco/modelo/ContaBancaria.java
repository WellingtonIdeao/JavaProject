package br.banco.modelo;
import java.util.List;

import br.banco.persistencia.IContasBancarias;

public class ContaBancaria {
	
	IContasBancarias persistencia;
	
	public ContaBancaria(IContasBancarias persistencia){
		this.persistencia = persistencia;
	}
	public int cadastrarConta(Conta conta){
		return this.persistencia.inserir(conta);
	}
	public void modificarConta(Conta conta){
		this.persistencia.atualizar(conta);
	}
	public List<Conta> listarContas(){
		return this.persistencia.listar();
	}
	public void excluirConta(int id){
		this.persistencia.deletar(id);
	}
}
