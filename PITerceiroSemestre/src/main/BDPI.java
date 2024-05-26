package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import entities.Aluno;
import entities.Aula;
import entities.Funcionario;
import entities.Pagamento;

public class BDPI {

	public static Connection con = null;
	public static PreparedStatement st = null;
	public static ResultSet rs = null;
	
	private final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private final String DATABASENAME = "DatabasePI";
	private final String URL = "jdbc:sqlserver://localhost:1433;databasename="+DATABASENAME;
	
	private final String LOGIN = "sa";
	private final String SENHA = "thiago";
	
	
	/**
	 * Realiza a conexão com o banco de dados
	 * @return - true em caso de sucesso, ou false caso contrário
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
	 * Encerra a conexão e libera os objetos utilizados
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
	
	public static void insertValues(Aluno aluno, Funcionario funcionario, Aula aula, Pagamento pagamento) {
		BDPI bd = new BDPI();
		bd.getConnection();
		String sql = null;
		try {
			if (funcionario != null) {
					sql = "insert into Funcionario values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					st = con.prepareStatement(sql);
					st.setInt(1, funcionario.getId());
					st.setString(2, funcionario.getNome());
					st.setString(3, funcionario.getTelefone());
					st.setString(4, funcionario.getCep());
					st.setString(5, funcionario.getCidade());
					st.setString(6, funcionario.getRua());
					st.setString(7, funcionario.getBairro());
					st.setString(8, funcionario.getUsuario());
					st.setString(9, funcionario.getSenha());
					st.setInt(10, funcionario.getNivelDeAcesso());
					st.execute();
					System.out.println("Funcionario cadastrado.");
				}
			
			if (aluno != null) {
					sql = "insert into Aluno values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
					st = con.prepareStatement(sql);
					st.setInt(1, aluno.getId());
					st.setString(2, aluno.getNome());
					st.setString(3, aluno.getDataNascimento());
					st.setString(4, aluno.getTelefone());
					st.setString(5, aluno.getCep());
					st.setString(6, aluno.getCidade());
					st.setString(7, aluno.getRua());
					st.setString(8, aluno.getBairro());
					st.setInt(9, aluno.getRegistradoPor());
					st.execute();
					System.out.println("Aluno cadastrado.");
				}
			
			if (aula != null) {
				sql = "insert into Aula values(?, ?, ?, ?, ?, ?, ?)";
				st = con.prepareStatement(sql);
				st.setInt(1, aula.getId());
				st.setString(2, aula.getData());
				st.setString(3, aula.getHoraComeco());
				st.setString(4, aula.getHoraFim());
				st.setInt(5, aula.getQtdeVagasDisponiveis());
				st.setInt(6, aula.getVagasOcupadas());
				st.setInt(7, aula.getSala());
				st.execute();
				System.out.println("Aula cadastrado.");
				}
			
			if (pagamento != null) {
				sql = "insert into Aula values(?, ?, ?, ?, ?, ?)";
				st = con.prepareStatement(sql);
				st.setInt(1, pagamento.getId());
				st.setString(2, pagamento.getData());
				st.setString(3, pagamento.getHorario());
				st.setDouble(4, pagamento.getValorMensalidade());
				st.setInt(5, pagamento.getIdAluno());
				st.setInt(6, pagamento.getIdFuncionario());
				st.execute();
				System.out.println("Pagamento cadastrado.");
				}
			
		} catch (SQLServerException e) {
			System.out.println("ID ja registrado.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		bd.close();
	}
}
