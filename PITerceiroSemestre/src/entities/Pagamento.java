package entities;

import main.BDPI;

public class Pagamento {
	private final static String NOME_TABELA = "Pagamento";
	
	private int id;
	private String data;
	private String horario;
	private double valorMensalidade;
	private int idAluno;
	private int idFuncionario;
	
	public static void createPagamento() {
		Pagamento pagamento = new Pagamento();
		
		pagamento.setId(1); // autonumeração
		pagamento.setData("30/05/2024");
		pagamento.setHorario("08:00");
		pagamento.setValorMensalidade(200.00);
		pagamento.setIdAluno(1);
		pagamento.setIdFuncionario(1);

		BDPI.create(null, null, null, pagamento);
	}
	
	public static Pagamento readPagamento(int id) {
		Pagamento pagamento = new Pagamento();
		pagamento = (Pagamento) BDPI.read(id, NOME_TABELA);
		System.out.println(pagamento);
		return pagamento;
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
