package br.banco.persistencia;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import br.banco.modelo.Conta;
import br.banco.modelo.ContaCorrente;
import br.banco.modelo.excessoes.ContaJaCadastradaException;
import br.banco.modelo.excessoes.ContaNaoEncontradaException;
import br.banco.modelo.excessoes.ValorInvalidoException;

public class ConexaoBD implements IContasBancarias {
	private static ConexaoBD conexao;

	private ConexaoBD() {
	}

	public static ConexaoBD getInstance() {
		if (conexao == null) {
			conexao = new ConexaoBD();
		}
		return conexao;
	}

	// retorna uma conexao
	public Connection getConnection() throws ClassNotFoundException,SQLException {
		Class.forName("org.postgresql.Driver");
		String url = null;
		String login = null;
		String senha = null;
		Properties prop = new Properties();
		try {
			FileInputStream file =  new FileInputStream("D:/Dados/workstation/ProjetoJava/propriedades/database.properties");
			prop.load(file);
			 url = prop.getProperty("url");
			 login = prop.getProperty("login");
			 senha = prop.getProperty("senha");
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(url, login,senha);

	}
	
	public List<Conta> listar(){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Conta> myList = new ArrayList<Conta>();
			try {
				connection  = getConnection();
				statement = connection.prepareStatement("SELECT *FROM Conta");
				result = statement.executeQuery();
				while(result.next()){
					Conta c = new ContaCorrente(result.getString(4),result.getString(2));
					c.setSaldo(result.getFloat(3));
					myList.add(c);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(connection != null){
					try {
						connection.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
				}
				if(statement != null){
					try {
						statement.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
				}
				if(result != null){
					try {
						result.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
				}
			}
			Collections.sort(myList);
		return myList;
	}
	public void inserir(Conta conta) throws ContaJaCadastradaException{ // retorna a chave do objeto inserido
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int i = 0;
		try {
			connection = getConnection();
			statement =  connection.prepareStatement("SELECT *FROM Conta");
			rs = statement.executeQuery();
			while(rs.next()){
				if(rs.getString(2).equalsIgnoreCase(conta.getNumero()))
					throw new ContaJaCadastradaException("Conta já cadastrada no banco de dados");
			}
			statement = null;
			statement = connection.prepareStatement("INSERT INTO Conta(Numero,Saldo,NomeCliente)"+"Values(?,?,?)");
			
			statement.setString(++i,conta.getNumero().toLowerCase());
			statement.setFloat(++i,conta.getSaldo());
			statement.setString(++i, conta.getNomeCliente());
			statement.executeUpdate();
			
				 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(connection != null){
				try {
					connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			if(statement != null){
				try {
					statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			if(rs != null){
				try {
					rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
	}
	
	public void atualizar(Conta conta) throws ContaNaoEncontradaException{
		Connection connection = null;
		PreparedStatement statement = null;
		int c = 0;
		try {
			connection = getConnection();
			statement =  connection.prepareStatement("UPDATE Conta "
							+ "Set Saldo = ? Where Numero = ?");
			statement.setFloat(++c,conta.getSaldo());
			statement.setString(++c,conta.getNumero());
			int i = statement.executeUpdate();
			if(i  == 0)
			   throw new ContaNaoEncontradaException("Conta não cadastrada no banco de dados");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(connection != null){
				try {
					connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			if(statement != null){
				try {
					statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
		
		
	}
	public void deletar(String id) throws ContaNaoEncontradaException {
		Connection connection = null;
		PreparedStatement statement = null;
		int c = 0;
		try {
			connection = getConnection();
		    statement =  connection.prepareStatement("DELETE FROM Conta where Numero = ?");
			statement.setString(++c, id.toLowerCase());
			
			int i  = statement.executeUpdate();
			if(i == 0)
				throw new ContaNaoEncontradaException("Conta não cadastrada no banco de dados");
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(connection != null){
				try {
					connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			if(statement != null){
				try {
					statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			
		}
		
	}
	
	public Conta buscar(String id) throws ContaNaoEncontradaException{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Conta conta = new ContaCorrente("","");
			
				try {
					connection  = getConnection();
					statement = connection.prepareStatement("SELECT *FROM Conta");
					rs = statement.executeQuery();
			
					while(rs.next()){
						int i = 1;
						if(rs.getString(2).equalsIgnoreCase(id)){
					       conta.setNumero(rs.getString(++i));
						   conta.setSaldo(rs.getFloat(++i));
						   conta.setNomeCliente(rs.getString(++i));
						   return conta;
						}   
					}
				throw new ContaNaoEncontradaException("Conta não cadastrada no banco de dados");
				
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					if(connection != null){
						try {
							connection.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
					}
					if(statement != null){
						try {
							statement.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
					}
					if(rs != null){
						try {
							rs.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
					}
				}
				return null;
			
	}

}
