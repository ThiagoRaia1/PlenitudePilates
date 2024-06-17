package entities;

import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import main.BD;

/**
 * Classe para instaciacao de objetos referente a tabela "Funcionario" no banco de dados.
 * Possui metodos para CRUD.
 */
public class Funcionario {
	private final static String NOME_TABELA = "Funcionario";
	private static String sql = null;
	private static String msg = null;
	
	private int id;
	private String nome;
	private String funcao;
	private String telefone;
	private String cep;
	private String cidade;
	private String rua;
	private String bairro;
	private String usuario;
	private String senha;
	private int nivelDeAcesso;
	
	/**
	 * Realiza um insert no banco de dados definido na classe "BD" em "entities".
	 * @param funcionario - Objeto da classe "Funcionario" que possui os dados a serem inseridos no banco, 
	 * dados esses que serao solicitados no painel "AddFuncionario".
	 * @return - Retorna a variavel "msg" contendo o resultado da operacao, seja "Erro ao cadastrar funcionario." 
	 * em caso de erro e "Aula cadastrada." em caso de sucesso.
	 */
	public static String create(Funcionario funcionario) {
		int i = 1;
		msg = "Erro ao cadastrar funcionario.";
		BD bd = new BD();
		if (bd.getConnection()) {
			while (true) {
				try {
					funcionario.setId(i); // autonumeração
					sql = "insert into "+NOME_TABELA+" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					bd.st = bd.con.prepareStatement(sql);
					bd.st.setInt(1, funcionario.getId());
					bd.st.setString(2, funcionario.getNome());
					bd.st.setString(3, funcionario.getFuncao());
					bd.st.setString(4, funcionario.getTelefone());
					bd.st.setString(5, funcionario.getCep());
					bd.st.setString(6, funcionario.getCidade());
					bd.st.setString(7, funcionario.getRua());
					bd.st.setString(8, funcionario.getBairro());
					bd.st.setString(9, funcionario.getUsuario());
					bd.st.setString(10, funcionario.getSenha());
					bd.st.setInt(11, funcionario.getNivelDeAcesso());
					bd.st.execute();
					// System.out.println("Funcionario cadastrado.");
					bd.close();
					msg = "Funcionario cadastrado.";
					break;
				} catch (SQLServerException e) {
					e.printStackTrace();
					// System.out.println("ID ja registrado.");
					i++; // autonumeração
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return msg;
	}

	/**
	 * Realiza a query "select * from Funcionairo where id_funcionario = ?" no banco de dados definido 
	 * na classe "BD" em "entites", onde "?" e substituido pelo parametro "id". 
	 * Pode ser usado em um loop para que todos os funcionarios sejam lidos.
	 * @param id - O id do funcionario a ser lido.
	 * @return - Retorna um objeto chamado "funcionario" da classe "Funcionario" contendo os dados do 
	 * funcionario lido.
	 */
	public static Funcionario read(int id) {
		Funcionario funcionario = new Funcionario();
		BD bd = new BD();
		if (bd.getConnection()) {
			try {
				sql = "select * from "+NOME_TABELA+" where id_funcionario = ?";
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, id);
				bd.rs = bd.st.executeQuery();
				while(bd.rs.next()) {
					funcionario.setId(bd.rs.getInt("id_funcionario"));
					funcionario.setNome(bd.rs.getString("nome_funcionario"));
					funcionario.setFuncao(bd.rs.getString("funcao_funcionario"));
					funcionario.setTelefone(bd.rs.getString("telefone_funcionario"));
					funcionario.setCep(bd.rs.getString("cep_funcionario"));
					funcionario.setCidade(bd.rs.getString("cidade_funcionario"));
					funcionario.setRua(bd.rs.getString("rua_funcionario"));
					funcionario.setBairro(bd.rs.getString("bairro_funcionario"));
					funcionario.setUsuario(bd.rs.getString("usuario_funcionario"));
					funcionario.setSenha(bd.rs.getString("senha_funcionario"));
					funcionario.setNivelDeAcesso(bd.rs.getInt("nivelDeAcesso_funcionario"));
				}
				if (funcionario.getId() != 0) {
					//System.out.println("Funcionario lido.");
				}
			} catch (SQLServerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			bd.close();
			if (funcionario.id == 0) {
				System.out.println("ID nao encontrado.");
			}
		}
		return funcionario;
	}

	/**
	 * Realiza um update no banco de dados definido na classe "BD" em "entites" no id do funcionario 
	 * informado no parametro "funcionario".
	 * @param funcionario - Um objeto da classe "Funcionario" contendo os dados que serao atualizados 
	 * (ou nao, para os dados que nao forem alterados) e onde serao atualizados.
	 * @return - Retorna a variavel "msg" contendo o resultado da operacao, 
	 * seja "Erro ao atualizar dados do funcionario." em caso de erro e 
	 * "Dados do funcionario (nome do funcionario) atualizados." em caso de sucesso.
	 */
	public static String update(Funcionario funcionario) {
		String msg = "Erro ao atualizar dados do funcionario.";
		BD bd = new BD();
		if (bd.getConnection()) {
			try {
					sql = "update "+NOME_TABELA+" set nome_funcionario = ?, funcao_funcionario = ?, telefone_funcionario = ?, "
							+ "cep_funcionario = ?, cidade_funcionario = ?, rua_funcionario = ?, "
							+ "bairro_funcionario = ?, usuario_funcionario = ?, senha_funcionario = ?, "
							+ "nivelDeAcesso_funcionario = ? where id_funcionario = ?";
					bd.st = bd.con.prepareStatement(sql);
					bd.st.setString(1, funcionario.getNome());
					bd.st.setString(2, funcionario.getFuncao());
					bd.st.setString(3, funcionario.getTelefone());
					bd.st.setString(4, funcionario.getCep());
					bd.st.setString(5, funcionario.getCidade());
					bd.st.setString(6, funcionario.getRua());
					bd.st.setString(7, funcionario.getBairro());
					bd.st.setString(8, funcionario.getUsuario());
					bd.st.setString(9, funcionario.getSenha());
					bd.st.setInt(10, funcionario.getNivelDeAcesso());
					bd.st.setInt(11, funcionario.getId());
					bd.st.execute();
					
					msg = "Dados do funcionário "+funcionario.getNome()+" atualizados.";
			} catch (SQLServerException e) {
				System.out.println(e);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		bd.close();
		}
		return msg;
	}
	
	/**
	 * Deleta o registro no id informado.
	 * @param id - ID que tera os dados deletados.
	 * @return - Retorna a variavel "msg" contendo o resultado da operacao, seja "Erro ao deletar dados." 
	 * em caso de erro e "Dados deletados com sucesso." em caso de sucesso.
	 */
	public static String delete(int id) {
		msg = "Erro ao deletar dados.";
		BD bd = new BD();
			if (bd.getConnection()) {
			try {
				sql = "delete from "+NOME_TABELA+" where id_funcionario = ?";
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, id);
				bd.st.execute();
				msg = "Dados deletados com sucesso.";
			} catch (SQLServerException e) {
				e.printStackTrace();
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
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getNivelDeAcesso() {
		return nivelDeAcesso;
	}
	public void setNivelDeAcesso(int nivelDeAcesso) {
		this.nivelDeAcesso = nivelDeAcesso;
	}
	public static String getNOME_TABELA() {
		return NOME_TABELA;
	}
	// GETTERS AND SETTERS

	// CONSTRUCTORS
	public Funcionario() {
		
	}
	
	public Funcionario(int id, String nome, String telefone, String cep, String cidade, String rua, String bairro,
			String usuario, String senha, int nivelDeAcesso) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.cep = cep;
		this.cidade = cidade;
		this.rua = rua;
		this.bairro = bairro;
		this.usuario = usuario;
		this.senha = senha;
		this.nivelDeAcesso = nivelDeAcesso;
	}
	// CONSTRUCTORS

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", cep=" + cep + ", cidade="
				+ cidade + ", rua=" + rua + ", bairro=" + bairro + ", usuario=" + usuario + ", senha=" + senha
				+ ", nivelDeAcesso=" + nivelDeAcesso + "]";
	}
}
