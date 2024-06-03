package entities;

import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import main.BD;

public class Pagamento {
	private final static String NOME_TABELA = "Pagamento";
	private static String sql = null;
	
	private int id;
	private String data;
	private String horario;
	private double valorMensalidade;
	private int idAluno;
	private int idFuncionario;
	
	public static void create() {
		Pagamento pagamento = new Pagamento();
		
		pagamento.setId(1); // autonumeração
		pagamento.setData("30/05/2024");
		pagamento.setHorario("08:00");
		pagamento.setValorMensalidade(200.00);
		pagamento.setIdAluno(1);
		pagamento.setIdFuncionario(1);

		BD bd = new BD();
		if (bd.getConnection()) {
			try {
				sql = null;
				sql = "insert into "+NOME_TABELA+" values(?, ?, ?, ?, ?, ?)";
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, pagamento.getId());
				bd.st.setString(2, pagamento.getData());
				bd.st.setString(3, pagamento.getHorario());
				bd.st.setDouble(4, pagamento.getValorMensalidade());
				bd.st.setInt(5, pagamento.getIdAluno());
				bd.st.setInt(6, pagamento.getIdFuncionario());
				bd.st.execute();
				System.out.println("Pagamento cadastrado.");
			} catch (SQLServerException e) {
				System.out.println("ID ja registrado.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			bd.close();
		}
	}
	
	public static Pagamento read(int id) {
		Pagamento pagamento = new Pagamento();
		
		BD bd = new BD();
		if (bd.getConnection()) {
			try {
				sql = "select * from "+NOME_TABELA+" where id_pagamento = ?";
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, id);
				bd.rs = bd.st.executeQuery();
				while(bd.rs.next()) {
					pagamento.setId(bd.rs.getInt("id_pagamento"));
					pagamento.setData(bd.rs.getString("data_pagamento"));
					pagamento.setHorario(bd.rs.getString("horario_pagamento"));
					pagamento.setValorMensalidade(bd.rs.getDouble("valorMensalidade_pagamento"));
					pagamento.setIdAluno(bd.rs.getInt("id_aluno"));
					pagamento.setIdFuncionario(bd.rs.getInt("id_funcionario"));
				}
				if (pagamento.getId() != 0) {
					System.out.println("Pagamento lido.");
				}
			} catch (SQLServerException e) {
				System.out.println("ID ja registrado.");
				System.out.println(e);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			bd.close();
			if (pagamento.getId() == 0) {
				System.out.println("ID não encontrado.");
			}
		}
		return pagamento;
	}
	
	public static void update(int id) {
		Pagamento pagamento = new Pagamento();
		
		// Recebe os dados atuais, campos em branco não serão alterados.
		pagamento = Pagamento.read(id);

		// Pedir dados ao usuário
		pagamento.setData("30/05/2024");
		pagamento.setHorario("08:00");
		pagamento.setValorMensalidade(200.00);
		pagamento.setIdAluno(1);
		
		BD bd = new BD();
		if (bd.getConnection()) {
			try {
				sql = "update "+NOME_TABELA+" set data_pagamento = ?, horario_pagamento = ?, valorMensalidade_pagamento = ?,"
						+ "id_aluno = ?, id_pagamento = ? where id_pagamento = ?";
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, pagamento.getData());
				bd.st.setString(2, pagamento.getHorario());
				bd.st.setDouble(3, pagamento.getValorMensalidade());
				bd.st.setInt(4, pagamento.getIdAluno());
				bd.st.setInt(5, pagamento.getIdFuncionario());
				bd.st.setInt(6, pagamento.getId());
				bd.st.execute();
				
				System.out.println("Dados do pagamento "+pagamento.getId()+" atualizados.");
			} catch (SQLServerException e) {
				System.out.println("ID ja registrado.");
				System.out.println(e);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			bd.close();
		}
	}
	
	public static void delete(int id) {
		BD bd = new BD();
		if (bd.getConnection()) {
			try {
				sql = "delete from "+NOME_TABELA+" where id_pagamento = ?";
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, id);
				bd.st.execute();
			} catch (SQLServerException e) {
				System.out.println(e);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			bd.close();
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public double getValorMensalidade() {
		return valorMensalidade;
	}
	public void setValorMensalidade(double valorMensalidade) {
		this.valorMensalidade = valorMensalidade;
	}
	public int getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	public Pagamento() {
		
	}
	
	public Pagamento(int id, String data, String horario, double valorMensalidade, int idAluno, int idFuncionario) {
		super();
		this.id = id;
		this.data = data;
		this.horario = horario;
		this.valorMensalidade = valorMensalidade;
		this.idAluno = idAluno;
		this.idFuncionario = idFuncionario;
	}
	
	@Override
	public String toString() {
		return "Pagamento [id=" + id + ", data=" + data + ", horario=" + horario + ", valorMensalidade="
				+ valorMensalidade + ", idAluno=" + idAluno + ", idFuncionario=" + idFuncionario + "]";
	}
}
