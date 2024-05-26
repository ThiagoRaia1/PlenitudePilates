package classes;

import main.BDPI;

public class Aluno {
	private int id;
	private String nome;
	private String dataNascimento;
	private String telefone;
	private String cep;
	private String cidade;
	private String rua;
	private String bairro;
	private int registradoPor;
	
	public static void registrarAluno() { // Como é feito o registro no banco
		Aluno aluno = new Aluno();
		
		aluno.setId(1);
		aluno.setNome("Thiago");
		aluno.setDataNascimento("19/09/2004");
		aluno.setTelefone("(XX)X XXXX-XXXX");
		aluno.setCep("XXXXX/XXX");
		aluno.setCidade("Indaiatuba");
		aluno.setBairro("XXXXXXXXXX");
		aluno.setRua("XXXXXXXXXXXXX");
		aluno.setRegistradoPor(1);

		BDPI.insertValues(aluno, null, null, null);
		
		System.out.println("Aluno cadastrado.");
		
		// aluno.setRegistradoPor(1); // Associação binária - Associar ao id do funcionario logado
		
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
