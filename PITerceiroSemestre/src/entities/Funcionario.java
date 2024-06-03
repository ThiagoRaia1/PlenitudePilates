package entities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import main.BD;

public class Funcionario {
	private final static String NOME_TABELA = "Funcionario";
	private static String sql = null;
	
	private int id;
	private String nome;
	private String telefone;
	private String cep;
	private String cidade;
	private String rua;
	private String bairro;
	private String usuario;
	private String senha;
	private int nivelDeAcesso;
	
	public static void create() {
		Funcionario funcionario = new Funcionario();
		// Pedir dados ao usuário
		funcionario.setId(1);
		funcionario.setNome("Thiago");
		funcionario.setTelefone("telefone");
		funcionario.setCep("cep");
		funcionario.setCidade("Indaiatuba");
		funcionario.setRua("rua");
		funcionario.setBairro("bairro");
		funcionario.setUsuario("Thiago");
		funcionario.setSenha("Thiago");
		funcionario.setNivelDeAcesso(2);;
		
		BD bd = new BD();
		bd.getConnection();
		try {
			sql = "insert into "+NOME_TABELA+" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, funcionario.getId());
			bd.st.setString(2, funcionario.getNome());
			bd.st.setString(3, funcionario.getTelefone());
			bd.st.setString(4, funcionario.getCep());
			bd.st.setString(5, funcionario.getCidade());
			bd.st.setString(6, funcionario.getRua());
			bd.st.setString(7, funcionario.getBairro());
			bd.st.setString(8, funcionario.getUsuario());
			bd.st.setString(9, funcionario.getSenha());
			bd.st.setInt(10, funcionario.getNivelDeAcesso());
			bd.st.execute();
			System.out.println("Funcionario cadastrado.");
		} catch (SQLServerException e) {
			System.out.println("ID ja registrado.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		bd.close();
	}
	
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
					System.out.println("Funcionario lido.");
				}
			} catch (SQLServerException e) {
				System.out.println("ID ja registrado.");
				System.out.println(e);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			bd.close();
			if (funcionario.id == 0) {
				System.out.println("ID não encontrado.");
			}
		}
		return funcionario;
	}
	
	public static void update(int id) {
		Funcionario funcionario = new Funcionario();
		
		// Recebe os dados atuais, campos em branco não serão alterados.
		funcionario = Funcionario.read(id);
		
		// Pedir dados ao usuário
		
		// if(JTextLabel != null) {
			funcionario.setNome("Thiago"); // Update
		// }
		
		// if(JTextLabel != null) {
			funcionario.setTelefone("telefone"); // Update
		// }
		
		// if(JTextLabel != null) {
			funcionario.setCep("cep"); // Update
		// }
		
		// if(JTextLabel != null) {
			funcionario.setCidade("Indaiatuba"); // Update
		// }
		
		// if(JTextLabel != null) {
			funcionario.setRua("rua"); // Update
		// }
		
		// if(JTextLabel != null) {
			funcionario.setBairro("bairro"); // Update
		// }
		
		// if(JTextLabel != null) {
			funcionario.setUsuario("Thiago"); // Update
		// }
			
		// if(JTextLabel != null) {
			funcionario.setSenha("Thiago"); // Update
		// }
			
		// if(JTextLabel != null && nivelDeAcesso do login atual == 1) {
			funcionario.setNivelDeAcesso(2); // Update
		// }
			
		BD bd = new BD();
		if (bd.getConnection()) {
			try {
					sql = "update "+NOME_TABELA+" set nome_funcionario = ?, telefone_funcionario = ?, "
							+ "cep_funcionario = ?, cidade_funcionario = ?, rua_funcionario = ?, "
							+ "bairro_funcionario = ?, usuario_funcionario = ?, senha_funcionario = ?, "
							+ "nivelDeAcesso_funcionario = ? where id_funcionario = ?";
					bd.st = bd.con.prepareStatement(sql);
					bd.st.setString(1, funcionario.getNome());
					bd.st.setString(2, funcionario.getTelefone());
					bd.st.setString(3, funcionario.getCep());
					bd.st.setString(4, funcionario.getCidade());
					bd.st.setString(5, funcionario.getRua());
					bd.st.setString(6, funcionario.getBairro());
					bd.st.setString(7, funcionario.getUsuario());
					bd.st.setString(8, funcionario.getSenha());
					bd.st.setInt(9, funcionario.getNivelDeAcesso());
					bd.st.setInt(10, funcionario.getId());
					bd.st.execute();
					
					System.out.println("Dados do funcionário "+funcionario.getNome()+" atualizados.");
			} catch (SQLServerException e) {
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
				sql = "delete from "+NOME_TABELA+" where id_funcionario = ?";
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
	
	public static List<Funcionario> getFuncionarios() {
		List<Funcionario> funcionarios = new ArrayList<>();
		
		return funcionarios;
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
	/*
	public Funcionario(Funcionario funcionario) {
		super();
		this.id = funcionario.id;
		this.nome = funcionario.nome;
		this.telefone = funcionario.telefone;
		this.cep = funcionario.cep;
		this.cidade = funcionario.cidade;
		this.rua = funcionario.rua;
		this.bairro = funcionario.bairro;
		this.usuario = funcionario.usuario;
		this.senha = funcionario.senha;
		this.nivelDeAcesso = funcionario.nivelDeAcesso;
	}
	*/

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", cep=" + cep + ", cidade="
				+ cidade + ", rua=" + rua + ", bairro=" + bairro + ", usuario=" + usuario + ", senha=" + senha
				+ ", nivelDeAcesso=" + nivelDeAcesso + "]";
	}
}
