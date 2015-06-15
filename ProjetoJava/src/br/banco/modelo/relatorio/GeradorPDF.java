package br.banco.modelo.relatorio;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;

import br.banco.modelo.Conta;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorPDF {
	
	public boolean criandoDocumento(Conta conta){
		Document doc = new Document(PageSize.A4,72,72,72,72);
		OutputStream os = null;
		RelatorioConta rlt = null;
		try {
			os = new FileOutputStream("D:/Dados/workstation/ProjetoJava/relatorios/relatorio.pdf");
			PdfWriter.getInstance(doc,os);
			doc.open();
			DecimalFormat formatter = new DecimalFormat("0.00");
			Paragraph p = new Paragraph("Relatorio da Conta:\r\nNome do proprietário: "+conta.getNomeCliente()+
		    		"\r\nNumero da Conta: "+conta.getNumero()+"\r\nSaldo: R$ "+formatter.format(conta.getSaldo()));
		    doc.add(p);
		    return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			if(doc!= null){
				doc.close();
			}
			if(os!= null)	
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}		
		}
		return false;	
	}
	
}
