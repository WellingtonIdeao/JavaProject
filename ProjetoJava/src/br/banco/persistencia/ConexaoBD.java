package br.banco.persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.banco.modelo.Conta;
import br.banco.modelo.ContaCorrente;

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
		return DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/bdBanco", "postgres",
				"654321");

	}
	
	public List<Conta> listar(){
		Connection connection = null;
		List<Conta> myList = new ArrayList<Conta>();
			try {
				connection  = getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT *FROM Conta");
				ResultSet result = statement.executeQuery();
				while(result.next()){
					Conta c = new ContaCorrente(result.getString(3));
					c.setNumero(result.getInt(1));
					c.setSaldo(result.getFloat(2));
					myList.add(c);
				}	
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		return myList;
	}
	public int inserir(Conta conta){
		Connection connection = null;
		int idObj = 0;
		try {
			connection = getConnection();
			PreparedStatement statement =  connection.prepareStatement("INSERT INTO Conta(Saldo,NomeCliente)"+"Values(?,?)",
										   PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setFloat(1,conta.getSaldo());
			statement.setString(2, conta.getNomeCliente());
			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();
			while(rs.next()){
				idObj = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return idObj;
	}
	
	public void atualizar(Conta conta){
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement =  connection.prepareStatement("UPDATE Conta "
							+ "Set Saldo = ?, "
							+ "NomeCliente = ?"
							+"Where CodC = ?");
			statement.setFloat(1,conta.getSaldo());
			statement.setString(2, conta.getNomeCliente());
			statement.setInt(3, Integer.parseInt(conta.getNumero()));
			statement.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	public void deletar(int id){
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement =  connection.prepareStatement("DELETE FROM Conta where CodC = ?");
			statement.setInt(1, id);
			statement.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
