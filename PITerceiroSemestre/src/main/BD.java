package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe para realizar operacoes com o banco de dados.
 */
public class BD {

	public Connection con = null;
	public PreparedStatement st = null;
	public ResultSet rs = null;
	
	private final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private final String DATABASENAME = "DatabasePI";
	private final String URL = "jdbc:sqlserver://localhost:1433;databasename="+DATABASENAME;
	
	private final String LOGIN = "usuario";
	private final String SENHA = "fatec";
	
	
	/**
	 * Realiza a conexao com o banco de dados
	 * @return - true em caso de sucesso, ou false caso contrario
	 */
	public boolean getConnection() {
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,LOGIN,SENHA);
			//System.out.println("Conectou!");
			return true;
		}
		catch(SQLException erro) {
			System.out.println("Falha na conexão! " + erro);
			return false;
		}
		catch(ClassNotFoundException erro) {
			System.out.println("Driver não encontrado!");
			return false;
		}
		
	}

	/**
	 * Encerra a conexao e libera os objetos utilizados
	 */
	public void close() {
		try {
			if(rs!=null) rs.close();
		}
		catch(SQLException e) {}
		
		try {
			if(st!=null) st.close();
		}
		catch(SQLException e) {}
		try {
			if(con!=null) {
				con.close();
				//System.out.println("Desconectou!");
			}
		}
		catch(SQLException e) {}

	}
}
