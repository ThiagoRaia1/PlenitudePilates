package entities;

import java.sql.Date;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import main.BD;

/**
 * Classe para instaciacao de objetos referente a tabela "Aula" no banco de dados.
 * Possui metodos para CRUD.
 */
public class Aula {
	private final static String NOME_TABELA = "Aula";
	private static String sql = null;
	private static String msg = null;
	
	private int id;
	private Date data;
	private String horaComeco;
	private String horaFim;
	private int qtdeVagasDisponiveis;
	private int vagasOcupadas;
	private int sala;
	private int idAluno;
	private int idFuncionario;
	
	/**
	 * Realiza um insert no banco de dados definido na classe "BD" em "entities".
	 * @param aula - Objeto da classe "Aula" que possui os dados a serem inseridos no banco, dados esses
	 * que serao solicitados no painel "AddAula".
	 * @return - Retorna a variavel "msg" contendo o resultado da operacao, seja "Erro ao registrar aula." em caso de erro
	 * e "Aula cadastrada." em caso de sucesso.
	 */
	public static String create(Aula aula) {
		msg = "Erro ao registrar aula.";
		int i = 1;
		BD bd = new BD();
		if (bd.getConnection()) {
			while (true) {
				try {
					aula.setId(i);
					sql = "insert into "+NOME_TABELA+" values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
					bd.st = bd.con.prepareStatement(sql);
					bd.st.setInt(1, aula.getId());
					bd.st.setDate(2, aula.getData());
					bd.st.setString(3, aula.getHoraComeco());
					bd.st.setString(4, aula.getHoraFim());
					bd.st.setInt(5, aula.getQtdeVagasDisponiveis());
					bd.st.setInt(6, aula.getVagasOcupadas());
					bd.st.setInt(7, aula.getSala());
					bd.st.setInt(8, aula.getIdAluno());
					bd.st.setInt(9, aula.getIdFuncionario());
					bd.st.execute();
					msg = "Aula cadastrada";
					System.out.println("Aula cadastrada.");
					bd.close();
					break;
				} catch (SQLServerException e) {
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
	 * Realiza a query "select * from Aula where id_aula = ?" no banco de dados definido na classe "BD" em "entites",
	 * onde "?" e substituido pelo parametro "id". Pode ser usado em um loop para que todos as aulas sejam lidas.
	 * @param id - O id da aula a ser lida.
	 * @return - Retorna um objeto chamado "aula" da classe "Aula" contendo os dados da aula lida.
	 */
	public static Aula read(int id) {
		Aula aula = new Aula();
		
		BD bd = new BD();
		if (bd.getConnection()) {
			try {
				String sql = "select * from "+NOME_TABELA+" where id_aula = ?";
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, id);
				bd.rs = bd.st.executeQuery();
				while(bd.rs.next()) {
					aula.setId(bd.rs.getInt("id_aula"));
					aula.setData(bd.rs.getDate("data_aula"));
					aula.setHoraComeco(bd.rs.getString("horaComeco_aula"));
					aula.setHoraFim(bd.rs.getString("horaFim_aula"));
					aula.setQtdeVagasDisponiveis(bd.rs.getInt("qtdeVagasDisponiveis_aula"));
					aula.setVagasOcupadas(bd.rs.getInt("vagasOcupadas_aula"));
					aula.setSala(bd.rs.getInt("sala_aula"));
					aula.setIdAluno(bd.rs.getInt("id_aluno"));
					aula.setIdFuncionario(bd.rs.getInt("id_funcionario"));
				}
				if (aula.getId() != 0) {
					//System.out.println("Aula lida.");
				}
			} catch (SQLServerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			bd.close();
			if (aula.id == 0) {
				System.out.println("ID não encontrado.");
			}
		}
		return aula;
	}

	/**
	 * Realiza um update na quantidade vagas disponiveis(-1) e nas vagas ocupadas(+1) 
	 * no banco de dados definido na classe "BD" em "entites" em todos os registros que possuem
	 * a mesma data e horario informados no parametro "aulaExistente".
	 * @param aulaExistente - A classe "AddAula" chama este metodo caso um aluno seja registrado em uma aula 
	 * em que ja tenha outros alunos registrados, passando como parametro a data e hora digitado pelo usuario
	 * somente se estes ja existam no banco de dados.
	 */
	public static void update(Aula aulaExistente) {
		BD bd = new BD();
		if (bd.getConnection()) {
			try {
				sql = "update "+NOME_TABELA+" set qtdeVagasDisponiveis_aula = ?, "
						+ "vagasOcupadas_aula = ? where data_aula = ? and horaComeco_aula = ?";
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, aulaExistente.getQtdeVagasDisponiveis()-1);
				bd.st.setInt(2, aulaExistente.getVagasOcupadas()+1);
				bd.st.setDate(3, aulaExistente.getData());
				bd.st.setString(4, aulaExistente.getHoraComeco());
				bd.st.execute();
				System.out.println("Vagas atualizadas.");
			} catch (SQLServerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			bd.close();
		}
	}
	
	/** 
	 * Deleta uma aula por completo, removendo todos os alunos referente a data e horario informados.
	 * @param data - Data informada pelo usuario para ter seus dados apagados.
	 * @param horaComeco - Hora da aula informada pelo usuario para ter seus registros apagados.
	 */
	public static void delete(Date data, String horaComeco) {
		BD bd = new BD();
		if (bd.getConnection()) {
			try {
				sql = "delete from "+NOME_TABELA+" where data_aula = ? and horaComeco_aula = ?";
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setDate(1, data);
				bd.st.setString(2, horaComeco);
				bd.st.execute();
			} catch (SQLServerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			bd.close();
		}
	}
	
	// GETTERS AND SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getHoraComeco() {
		return horaComeco;
	}
	public void setHoraComeco(String horaComeco) {
		this.horaComeco = horaComeco;
	}
	public String getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}
	public int getQtdeVagasDisponiveis() {
		return qtdeVagasDisponiveis;
	}
	public void setQtdeVagasDisponiveis(int qtdeVagasDisponiveis) {
		this.qtdeVagasDisponiveis = qtdeVagasDisponiveis;
	}
	public int getVagasOcupadas() {
		return vagasOcupadas;
	}
	public void setVagasOcupadas(int vagasOcupadas) {
		this.vagasOcupadas = vagasOcupadas;
	}
	public int getSala() {
		return sala;
	}
	public void setSala(int sala) {
		this.sala = sala;
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
	public static String getNOME_TABELA() {
		return NOME_TABELA;
	}
	// GETTERS AND SETTERS
	
	// CONSTRUCTORS
	public Aula() {
		
	}

	public Aula(int id, Date data, String horaComeco, String horaFim, int qtdeVagasDisponiveis, int vagasOcupadas,
			int sala, int idAluno, int idFuncionario) {
		super();
		this.id = id;
		this.data = data;
		this.horaComeco = horaComeco;
		this.horaFim = horaFim;
		this.qtdeVagasDisponiveis = qtdeVagasDisponiveis;
		this.vagasOcupadas = vagasOcupadas;
		this.sala = sala;
		this.idAluno = idAluno;
		this.idFuncionario = idFuncionario;
	}
	// CONSTRUCTORS
	
	@Override
	public String toString() {
		return "Aula [id=" + id + ", data=" + data + ", horaComeco=" + horaComeco + ", horaFim=" + horaFim
				+ ", qtdeVagasDisponiveis=" + qtdeVagasDisponiveis + ", vagasOcupadas=" + vagasOcupadas + ", sala="
				+ sala + ", idAluno=" + idAluno + ", idFuncionario=" + idFuncionario + "]";
	}	
	
}
