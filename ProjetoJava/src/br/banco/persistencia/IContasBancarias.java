package br.banco.persistencia;

import java.util.List;

import br.banco.modelo.Conta;
import br.banco.modelo.excessoes.ContaJaCadastradaException;
import br.banco.modelo.excessoes.ContaNaoEncontradaException;

public interface IContasBancarias {
	
  void inserir(Conta conta) throws ContaJaCadastradaException;
  void atualizar(Conta conta) throws ContaNaoEncontradaException;
  List<Conta> listar();
  void deletar(String id) throws ContaNaoEncontradaException ;
  Conta buscar(String id) throws ContaNaoEncontradaException ;
}
