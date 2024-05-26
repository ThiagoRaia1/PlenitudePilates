package entities;

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
	
	public static void createAula() {
		Aula aula= new Aula();
		// Pedir dados ao usuário
		aula.setId(1);
		aula.setData("30/05/2024");
		aula.setHoraComeco("08:00");
		aula.setHoraFim("09:00");
		aula.setQtdeVagasDisponiveis(5);
		aula.setVagasOcupadas(0);
		aula.setSala(1);
		
		BDPI.create(null, null, aula, null);
	}
	
	public static Aula readAula(int id) {
		Aula aula = new Aula();
		aula = (Aula) BDPI.read(id, NOME_TABELA);
		System.out.println(aula);
		return aula;
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
