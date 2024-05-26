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
	
	private static String sql = null;
	
	
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
	
	public static void create(Funcionario funcionario, Aluno aluno, Aula aula, Pagamento pagamento) {
		BDPI bd = new BDPI();
		bd.getConnection();
		sql = null;
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
				sql = "insert into Pagamento values(?, ?, ?, ?, ?, ?)";
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

	public static void update(Funcionario funcionario, Aluno aluno, Aula aula, Pagamento pagamento, int id) {
		BDPI bd = new BDPI();
		bd.getConnection();
		try {
			if (funcionario != null) {
				funcionario = Funcionario.readFuncionario(id);
				sql = "update funcionario set nome_funcionario = ?, telefone_funcionario = ?, "
						+ "cep_funcionario = ?, cidade_funcionario = ?, rua_funcionario = ?, "
						+ "bairro_funcionario = ?, usuario_funcionario = ?, senha_funcionario = ?, "
						+ "nivelDeAcesso_funcionario = ? where id_funcionario = ?";
				st = con.prepareStatement(sql);
				st.setString(1, funcionario.getNome());
				st.setString(2, funcionario.getTelefone());
				st.setString(3, funcionario.getCep());
				st.setString(4, funcionario.getCidade());
				st.setString(5, funcionario.getRua());
				st.setString(6, funcionario.getBairro());
				st.setString(7, funcionario.getUsuario());
				st.setString(8, funcionario.getSenha());
				st.setInt(9, funcionario.getNivelDeAcesso());
				st.setInt(10, funcionario.getId());
				st.execute();
				
				System.out.println("Dados do funcionário "+funcionario.getNome()+" atualizados.");
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
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		bd.close();
	}
	
	public static Object read(int id, String tabela) {
		BDPI bd = new BDPI();
		bd.getConnection();
		sql = null;
		Object object = null;
		try {
			if (tabela.equals("Funcionario")) {
				Funcionario funcionario = new Funcionario();
				String sql = "select * from Funcionario where id_funcionario = ?";
				st = con.prepareStatement(sql);
				st.setInt(1, id);
				rs = st.executeQuery();
				while(rs.next()) {
					funcionario.setId(rs.getInt("id_funcionario"));
					funcionario.setNome(rs.getString("nome_funcionario"));
					funcionario.setTelefone(rs.getString("telefone_funcionario"));
					funcionario.setCep(rs.getString("cep_funcionario"));
					funcionario.setCidade(rs.getString("cidade_funcionario"));
					funcionario.setRua(rs.getString("rua_funcionario"));
					funcionario.setBairro(rs.getString("bairro_funcionario"));
					funcionario.setUsuario(rs.getString("usuario_funcionario"));
					funcionario.setSenha(rs.getString("senha_funcionario"));
					funcionario.setNivelDeAcesso(rs.getInt("nivelDeAcesso_funcionario"));
				}
				if (funcionario.getId() != 0) {
					object = funcionario;
					System.out.println("Funcionario lido.");
				}
			}
			
			if (tabela.equals("Aluno")) {
				Aluno aluno = new Aluno();
				String sql = "select * from Aluno where id_aluno = ?";
				st = con.prepareStatement(sql);
				st.setInt(1, id);
				rs = st.executeQuery();
				while(rs.next()) {
					aluno.setId(rs.getInt("id_aluno"));
					aluno.setNome(rs.getString("nome_aluno"));
					aluno.setDataNascimento(rs.getString("dataNascimento_aluno"));
					aluno.setTelefone(rs.getString("telefone_aluno"));
					aluno.setCep(rs.getString("cep_aluno"));
					aluno.setCidade(rs.getString("cidade_aluno"));
					aluno.setRua(rs.getString("rua_aluno"));
					aluno.setBairro(rs.getString("bairro_aluno"));
					aluno.setRegistradoPor(rs.getInt("registradoPor_funcionario"));
				}
				if (aluno.getId() != 0) {
					object = aluno;
					System.out.println("Aluno lido.");
				}
			}
			
			if (tabela.equals("Aula")) {
				Aula aula = new Aula();
				String sql = "select * from Aula where id_aula = ?";
				st = con.prepareStatement(sql);
				st.setInt(1, id);
				rs = st.executeQuery();
				while(rs.next()) {
					aula.setId(rs.getInt("id_aula"));
					aula.setData(rs.getString("data_aula"));
					aula.setHoraComeco(rs.getString("horaComeco_aula"));
					aula.setHoraFim(rs.getString("horaFim_aula"));
					aula.setQtdeVagasDisponiveis(rs.getInt("qtdeVagasDisponiveis_aula"));
					aula.setVagasOcupadas(rs.getInt("vagasOcupadas_aula"));
					aula.setSala(rs.getInt("sala_aula"));
				}
				if (aula.getId() != 0) {
					object = aula;
					System.out.println("Aula lida.");
				}
			}
			
			if (tabela.equals("Pagamento")) {
				Pagamento pagamento = new Pagamento();
				String sql = "select * from Pagamento where id_pagamento = ?";
				st = con.prepareStatement(sql);
				st.setInt(1, id);
				rs = st.executeQuery();
				while(rs.next()) {
					pagamento.setId(rs.getInt("id_pagamento"));
					pagamento.setData(rs.getString("data_pagamento"));
					pagamento.setHorario(rs.getString("horario_pagamento"));
					pagamento.setValorMensalidade(rs.getDouble("valorMensalidade_pagamento"));
					pagamento.setIdAluno(rs.getInt("id_aluno"));
					pagamento.setIdFuncionario(rs.getInt("id_funcionario"));
				}
				if (pagamento.getId() != 0) {
					object = pagamento;
					System.out.println("Pagamento lido.");
				}
			}
			
		} catch (SQLServerException e) {
			System.out.println("ID ja registrado.");
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		bd.close();
		if (object == null) {
			System.out.println("ID não encontrado.");
		}
		return object;
	}
	
	public static void delete(Aluno aluno, Funcionario funcionario, Aula aula, Pagamento pagamento) {
		
	}
}
