package entities;

import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import main.BDPI;

public class Aula {
	private final static String NOME_TABELA = "Aula";
	
	private int id;
	private String data;
	private String horaComeco;
	private String horaFim;
	private int qtdeVagasDisponiveis;
	private int vagasOcupadas;
	private int sala;
	
	private static String sql = null;
	
	public static void create() {
		Aula aula= new Aula();
		// Pedir dados ao usuário
		aula.setId(1);
		aula.setData("30/05/2024");
		aula.setHoraComeco("08:00");
		aula.setHoraFim("09:00");
		aula.setQtdeVagasDisponiveis(5);
		aula.setVagasOcupadas(0);
		aula.setSala(1);
		
		BDPI bd = new BDPI();
		bd.getConnection();
		sql = null;
		try {
			sql = "insert into Aula values(?, ?, ?, ?, ?, ?, ?)";
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, aula.getId());
			bd.st.setString(2, aula.getData());
			bd.st.setString(3, aula.getHoraComeco());
			bd.st.setString(4, aula.getHoraFim());
			bd.st.setInt(5, aula.getQtdeVagasDisponiveis());
			bd.st.setInt(6, aula.getVagasOcupadas());
			bd.st.setInt(7, aula.getSala());
			bd.st.execute();
			System.out.println("Aula cadastrado.");
		} catch (SQLServerException e) {
			System.out.println("ID ja registrado.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		bd.close();
	}
	
	public static Aula read(int id) {
		Aula aula = new Aula();
		
		BDPI bd = new BDPI();
		bd.getConnection();
		try {
			String sql = "select * from Aula where id_aula = ?";
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, id);
			bd.rs = bd.st.executeQuery();
			while(bd.rs.next()) {
				aula.setId(bd.rs.getInt("id_aula"));
				aula.setData(bd.rs.getString("data_aula"));
				aula.setHoraComeco(bd.rs.getString("horaComeco_aula"));
				aula.setHoraFim(bd.rs.getString("horaFim_aula"));
				aula.setQtdeVagasDisponiveis(bd.rs.getInt("qtdeVagasDisponiveis_aula"));
				aula.setVagasOcupadas(bd.rs.getInt("vagasOcupadas_aula"));
				aula.setSala(bd.rs.getInt("sala_aula"));
			}
			if (aula.getId() != 0) {
				System.out.println("Aula lida.");
			}
		} catch (SQLServerException e) {
			System.out.println("ID ja registrado.");
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		bd.close();
		if (aula.id == 0) {
			System.out.println("ID não encontrado.");
		}
		return aula;
	}
	
	public static void update(int id) {
		Aula aula = new Aula();
		
		// Recebe os dados atuais, campos em branco não serão alterados.
		aula = Aula.read(id);

		// Pedir dados ao usuário
		aula.setId(1);
		aula.setData("30/05/2024");
		aula.setHoraComeco("08:00");
		aula.setHoraFim("09:00");
		aula.setQtdeVagasDisponiveis(5);
		aula.setVagasOcupadas(0);
		aula.setSala(1);

		BDPI bd = new BDPI();
		bd.getConnection();
		try {
			// alterar para update da aula
			sql = "update funcionario set nome_funcionario = ?, telefone_funcionario = ?, "
					+ "cep_funcionario = ?, cidade_funcionario = ?, rua_funcionario = ?, "
					+ "bairro_funcionario = ?, usuario_funcionario = ?, senha_funcionario = ?, "
					+ "nivelDeAcesso_funcionario = ? where id_funcionario = ?";
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, aula.getId());
			bd.st.setString(2, aula.getData());
			bd.st.setString(3, aula.getHoraComeco());
			bd.st.setString(4, aula.getHoraFim());
			bd.st.setInt(5, aula.getQtdeVagasDisponiveis());
			bd.st.setInt(6, aula.getVagasOcupadas());
			bd.st.setInt(6, aula.getSala());
			bd.st.execute();
			
			System.out.println("Dados da aula "+aula.getId()+" atualizados.");
		} catch (SQLServerException e) {
			System.out.println("ID ja registrado.");
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		bd.close();
	}
	
	public static void delete(int id) {
		
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
	
	public Aula() {
		
	}
	
	public Aula(int id, String data, String horaComeco, String horaFim, int qtdeVagasDisponiveis, int vagasOcupadas,
			int sala) {
		super();
		this.id = id;
		this.data = data;
		this.horaComeco = horaComeco;
		this.horaFim = horaFim;
		this.qtdeVagasDisponiveis = qtdeVagasDisponiveis;
		this.vagasOcupadas = vagasOcupadas;
		this.sala = sala;
	}
	
	@Override
	public String toString() {
		return "Aula [id=" + id + ", data=" + data + ", horaComeco=" + horaComeco + ", horaFim=" + horaFim
				+ ", qtdeVagasDisponiveis=" + qtdeVagasDisponiveis + ", vagasOcupadas=" + vagasOcupadas + ", sala="
				+ sala + "]";
	}
	
	
}
