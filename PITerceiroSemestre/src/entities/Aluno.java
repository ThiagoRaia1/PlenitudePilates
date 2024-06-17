package entities;

import java.sql.Date;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import main.BD;

/**
 * Classe para instaciacao de objetos referente a tabela "Aluno" no banco de dados.
 * Possui metodos para CRUD.
 */
public class Aluno {
	private final static String NOME_TABELA = "Aluno";
	private static String sql = null;
	private static String msg = null;
	
	private int id;
	private String nome;
	private Date dataNascimento;
	private String telefone;
	private String cep;
	private String cidade;
	private String rua;
	private String bairro;
	private int registradoPor;
	
	/**
	 * Realiza um insert no banco de dados definido na classe "BD" em "entities".
	 * @param aluno - Objeto da classe "Aluno" que possui os dados a serem inseridos no banco, dados esses
	 * que serão solicitados no painel "AddAluno".
	 * @return - Retorna a variável "msg" contendo o resultado da operação, seja "Erro ao cadastrar aluno." em caso de erro
	 * e "Aluno cadastrado." em caso de sucesso.
	 */
	public static String create(Aluno aluno) {
		int i = 1;
		msg = "Erro ao cadastrar aluno.";
		BD bd = new BD();
		if (bd.getConnection()) {
			while (true) {
				try {
					aluno.setId(i); // autonumeração
					sql = "insert into "+NOME_TABELA+" values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
					bd.st = bd.con.prepareStatement(sql);
					bd.st.setInt(1, aluno.getId());
					bd.st.setString(2, aluno.getNome());
					bd.st.setDate(3, aluno.getDataNascimento());
					bd.st.setString(4, aluno.getTelefone());
					bd.st.setString(5, aluno.getCep());
					bd.st.setString(6, aluno.getCidade());
					bd.st.setString(7, aluno.getRua());
					bd.st.setString(8, aluno.getBairro());
					bd.st.setInt(9, aluno.getRegistradoPor());
					bd.st.execute();
					msg = "Aluno cadastrado.";
					System.out.println("Aluno cadastrado.");
					bd.close();
					break;
				} catch (SQLServerException e) {
					System.out.println(e);
					System.out.println("ID ja registrado.");
					i++;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return msg;
	}
	
	
	/**
	 * Realiza a query "select * from Aluno where id_aluno = ?" no banco de dados definido na classe "BD" em "entites",
	 * onde "?" é substituído pelo parâmetro "id". Pode ser usado em um loop para que todos os alunos sejam lidos.
	 * @param id - O id do aluno a ser lido.
	 * @return - Retorna um objeto chamado "aluno" da classe "Aluno" contendo os dados do aluno lido.
	 */
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
					aluno.setDataNascimento(bd.rs.getDate("dataNascimento_aluno"));
					aluno.setTelefone(bd.rs.getString("telefone_aluno"));
					aluno.setCep(bd.rs.getString("cep_aluno"));
					aluno.setCidade(bd.rs.getString("cidade_aluno"));
					aluno.setRua(bd.rs.getString("rua_aluno"));
					aluno.setBairro(bd.rs.getString("bairro_aluno"));
					aluno.setRegistradoPor(bd.rs.getInt("registradoPor_funcionario"));
				}
				if (aluno.getId() != 0) {
					//System.out.println("Aluno lido.");
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
	
	/**
	 * Realiza um update no banco de dados definido na classe "BD" em "entites" no id do aluno informado no parâmetro
	 * "aluno".
	 * @param aluno - Um objeto da classe "Aluno" contendo os dados que serão atualizados (ou não, para os dados que
	 * não forem alterados) e onde serão atualizados.
	 * @return - Retorna a variável "msg" contendo o resultado da operação, seja "Erro ao atualizar dados do aluno." 
	 * em caso de erro e "Dados do aluno (nome do aluno) atualizados." em caso de sucesso.
	 */
	public static String update(Aluno aluno) {
		msg = "Erro ao atualizar dados do aluno.";
		BD bd = new BD();
		if(bd.getConnection()) {
			try {
				sql = "update "+NOME_TABELA+" set nome_aluno = ?, dataNascimento_aluno = ?, telefone_aluno = ?, cep_aluno = ?,"
						+ "cidade_aluno = ?, rua_aluno = ?, bairro_aluno = ? where id_aluno = ?";
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, aluno.getNome());
				bd.st.setDate(2, aluno.getDataNascimento());
				bd.st.setString(3, aluno.getTelefone());
				bd.st.setString(4, aluno.getCep());
				bd.st.setString(5, aluno.getCidade());
				bd.st.setString(6, aluno.getRua());
				bd.st.setString(7, aluno.getBairro());
				bd.st.setInt(8, aluno.getId());
				bd.st.execute();
				
				msg = "Dados do aluno "+aluno.getNome()+" atualizados.";
			} catch (SQLServerException e) {
				System.out.println("ID ja registrado.");
				System.out.println(e);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			bd.close();
		}
		return msg;
	}
	
	/**
	 * Deleta o registro no id informado
	 * @param id - ID que terá os dados deletados.
	 * @return - Retorna a variável "msg" contendo o resultado da operação, seja "Erro ao deletar dados." 
	 * em caso de erro e "Dados deletados com sucesso." em caso de sucesso.
	 */
	public static String delete(int id) {
		msg = "Erro ao deletar dados.";
		BD bd = new BD();
		if (bd.getConnection()) {
			try {
				sql = "delete from "+NOME_TABELA+" where id_aluno = ?";
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, id);
				bd.st.execute();
				msg = "Dados deletados com sucesso.";
			} catch (SQLServerException e) {
				System.out.println(e);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			bd.close();
		}
		return msg;
	}
	
	// GETTERS AND SETTERS
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
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
	// GETTERS AND SETTERS
	
	// CONSTRUCTORS
	public Aluno() {
		
	}
	
	public Aluno(int id, String nome, Date dataNascimento, String telefone, String cep, String cidade, String rua,
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
	// CONSTRUCTORS
	
	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", telefone=" + telefone
				+ ", cep=" + cep + ", cidade=" + cidade + ", rua=" + rua + ", bairro=" + bairro + ", registradoPor="
				+ registradoPor + "]";
	}
}
