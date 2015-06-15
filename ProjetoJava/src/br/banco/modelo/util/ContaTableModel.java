package br.banco.modelo.util;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.banco.modelo.Conta;

public class ContaTableModel extends AbstractTableModel {
	private List<Conta> dados;
	private String[] colunas ={"ID","Nome","SALDO(R$)"};
	
	public ContaTableModel(Conta conta){
        this.dados = new ArrayList<Conta>();
        this.dados.add(conta);
        this.fireTableDataChanged();
    }
	public ContaTableModel(List<Conta> list){
		this.dados = list;
		this.fireTableDataChanged();
	}
     
    public void addRow(Conta p){
        this.dados.add(p);
        this.fireTableDataChanged();
    }
    public void clearTable(){
    	this.dados = new ArrayList<Conta>();
    	this.fireTableDataChanged();
    }
    
    public void setDados(List<Conta> dados) {
		this.dados = dados;
		this.fireTableDataChanged();
	}
	public Conta getConta(int index){
    	return this.dados.get(index);
    }
 
    public String getColumnName(int num){
        return this.colunas[num];
    }
	
	@Override
	public int getColumnCount() {
		return this.colunas.length;
	}

	@Override
	public int getRowCount() {
		 return this.dados.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch(coluna){
		case 0: return this.dados.get(linha).getNumero();
		case 1: return this.dados.get(linha).getNomeCliente();
		case 2: return this.dados.get(linha).getSaldo();
		}
		return null;
	}

}
