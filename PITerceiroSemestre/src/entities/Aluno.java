package entities;

import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import main.BD;

public class Aluno {
	private final static String NOME_TABELA = "Aluno";
	private static String sql = null;
	
	private int id;
	private String nome;
	private String dataNascimento;
	private String telefone;
	private String cep;
	private String cidade;
	private String rua;
	private String bairro;
	private int registradoPor;
	
	public static void create() {
		
		Aluno aluno = new Aluno();
		
		aluno.setId(1); // autonumeração
		aluno.setNome("Thiago");
		aluno.setDataNascimento("19/09/2004");
		aluno.setTelefone("(XX)X XXXX-XXXX");
		aluno.setCep("XXXXX/XXX");
		aluno.setCidade("Indaiatuba");
		aluno.setBairro("XXXXXXXXXX");
		aluno.setRua("XXXXXXXXXXXXX");
		aluno.setRegistradoPor(1);
		
		BD bd = new BD();
		if (bd.getConnection()) {
			try {
				sql = "insert into "+NOME_TABELA+" values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, aluno.getId());
				bd.st.setString(2, aluno.getNome());
				bd.st.setString(3, aluno.getDataNascimento());
				bd.st.setString(4, aluno.getTelefone());
				bd.st.setString(5, aluno.getCep());
				bd.st.setString(6, aluno.getCidade());
				bd.st.setString(7, aluno.getRua());
				bd.st.setString(8, aluno.getBairro());
				bd.st.setInt(9, aluno.getRegistradoPor());
				bd.st.execute();
				System.out.println("Aluno cadastrado.");
			} catch (SQLServerException e) {
				System.out.println("ID ja registrado.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			bd.close();
		}
		
		
		// aluno.setRegistradoPor(1); // Associação binária - Associar ao id do funcionario logado
		
	}
	
	public static Aluno read(int id) {
		Aluno aluno = new Aluno();
		
		BD bd = new BD();
		if (bd.getConnection()) {
			try {
				sql = "select * from "+NOME_TABELA+" where id_aluno = ?";
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, id);
				bd.rs = bd.st.executeQuery();
				while(bd.rs.next()) {
					aluno.setId(bd.rs.getInt("id_aluno"));
					aluno.setNome(bd.rs.getString("nome_aluno"));
					aluno.setDataNascimento(bd.rs.getString("dataNascimento_aluno"));
					aluno.setTelefone(bd.rs.getString("telefone_aluno"));
					aluno.setCep(bd.rs.getString("cep_aluno"));
					aluno.setCidade(bd.rs.getString("cidade_aluno"));
					aluno.setRua(bd.rs.getString("rua_aluno"));
					aluno.setBairro(bd.rs.getString("bairro_aluno"));
					aluno.setRegistradoPor(bd.rs.getInt("registradoPor_funcionario"));
				}
				if (aluno.getId() != 0) {
					System.out.println("Aluno lido.");
				}
			} catch (SQLServerException e) {
				System.out.println("ID ja registrado.");
				System.out.println(e);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			bd.close();
			if (aluno.id == 0) {
				System.out.println("ID não encontrado.");
			}
		}
		return aluno;
	}
	
	public static void update(int id) {
		Aluno aluno = new Aluno();
		
		// Recebe os dados atuais, campos em branco não serão alterados.
		aluno = Aluno.read(id);
		
		// Pedir dados ao usuário
		aluno.setNome("Thiago");
		aluno.setDataNascimento("19/09/2004");
		aluno.setTelefone("(XX)X XXXX-XXXX");
		aluno.setCep("XXXXX/XXX");
		aluno.setCidade("Indaiatuba");
		aluno.setBairro("XXXXXXXXXX");
		aluno.setRua("XXXXXXXXXXXXX");
		
		BD bd = new BD();
		if(bd.getConnection()) {
			try {
				sql = "update "+NOME_TABELA+" set nome_aluno = ?, dataNascimento_aluno = ?, telefone_aluno = ?, cep_aluno = ?,"
						+ "cidade_aluno = ?, rua_aluno = ?, bairro_aluno = ? where id_aluno = ?";
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, aluno.getNome());
				bd.st.setString(2, aluno.getDataNascimento());
				bd.st.setString(3, aluno.getTelefone());
				bd.st.setString(4, aluno.getCep());
				bd.st.setString(5, aluno.getCidade());
				bd.st.setString(6, aluno.getRua());
				bd.st.setString(7, aluno.getBairro());
				bd.st.setInt(8, aluno.getId());
				bd.st.execute();
				
				System.out.println("Dados do aluno "+aluno.getNome()+" atualizados.");
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
				sql = "delete from "+NOME_TABELA+" where id_aluno = ?";
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getRegistradoPor() {
		return registradoPor;
	}

	public void setRegistradoPor(int registradoPor) {
		this.registradoPor = registradoPor;
	}
	public static String getNOME_TABELA() {
		return NOME_TABELA;
	}
	public Aluno() {
		
	}
	
	public Aluno(int id, String nome, String dataNascimento, String telefone, String cep, String cidade, String rua,
			String bairro, int registradoPor) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.cep = cep;
		this.cidade = cidade;
		this.rua = rua;
		this.bairro = bairro;
		this.registradoPor = registradoPor;
	}
	
	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", telefone=" + telefone
				+ ", cep=" + cep + ", cidade=" + cidade + ", rua=" + rua + ", bairro=" + bairro + ", registradoPor="
				+ registradoPor + "]";
	}
}
