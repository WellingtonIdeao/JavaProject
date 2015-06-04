package br.banco.persistencia;

import java.util.List;

import br.banco.modelo.Conta;
import br.banco.modelo.excessoes.ContaJaCadastradaException;
import br.banco.modelo.excessoes.ContaNaoEncontradaException;

public interface IContasBancarias {
  int inserir(Conta conta) throws ContaJaCadastradaException;
  void atualizar(Conta conta);
  List<Conta> listar();
  void deletar(int id) throws ContaNaoEncontradaException ;
}
