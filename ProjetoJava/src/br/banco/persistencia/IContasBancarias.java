package br.banco.persistencia;

import java.util.List;

import br.banco.modelo.Conta;

public interface IContasBancarias {
  int inserir(Conta conta);
  void atualizar(Conta conta);
  List<Conta> listar();
  void deletar(int id);
}
