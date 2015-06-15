package br.banco.modelo.relatorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;

import br.banco.modelo.Conta;
import br.banco.modelo.ContaCorrente;
import br.banco.modelo.excessoes.ContaJaCadastradaException;
import br.banco.modelo.excessoes.ContaNaoEncontradaException;
import br.banco.modelo.excessoes.ValorInvalidoException;
import br.banco.persistencia.ConexaoBD;

public class RelatorioConta {
	
	public StringBuffer gerarRelatorio(Conta conta) {
		EscreverDados(conta);
		BufferedReader br = null;
		InputStream is = null;
		InputStreamReader isr = null;
		StringBuffer buffer = new StringBuffer(); 
		try {
			is = new FileInputStream("D:/Dados/workstation/ProjetoJava/relatorios/relatorio.txt");
			isr= new InputStreamReader(is);
			br = new BufferedReader(isr);
			String s = br.readLine();
			
			while (s != null) {
				buffer.append(s);
				buffer.append("\n");
				s = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return buffer;
	}
	
	private void EscreverDados(Conta conta){
		OutputStream os = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			os = new FileOutputStream("D:/Dados/workstation/ProjetoJava/relatorios/relatorio.txt");
			osw = new OutputStreamWriter(os);
		    bw = new BufferedWriter(osw);
		    DecimalFormat formatter = new DecimalFormat("0.00");  
		    bw.write("Relatorio da Conta:\r\nNome do proprietário: "+conta.getNomeCliente()+
		    		"\r\nNumero da Conta: "+conta.getNumero()+"\r\nSaldo: R$ "+formatter.format(conta.getSaldo()));
		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}				
	}
}
