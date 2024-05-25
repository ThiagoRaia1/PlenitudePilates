package Classes;

public class Aluno {
	private int id;
	private String nome;
	private String dataNascimento;
	private String telefone;
	private String cep;
	private String cidade;
	private String rua; //aaaa
	private String bairro;
	private int registradorPor;
	
	public int getId() {
		return id;
	}//adsklaslkfjsd

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

	public int getRegistradorPor() {
		return registradorPor;
	}

	public void setRegistradorPor(int registradorPor) {
		this.registradorPor = registradorPor;
	}

	public Aluno() {
		
	}
	
	public Aluno(int id, String nome, String dataNascimento, String telefone, String cep, String cidade, String rua,
			String bairro, int registradorPor) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.cep = cep;
		this.cidade = cidade;
		this.rua = rua;
		this.bairro = bairro;
		this.registradorPor = registradorPor;
	}
	
	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", telefone=" + telefone
				+ ", cep=" + cep + ", cidade=" + cidade + ", rua=" + rua + ", bairro=" + bairro + ", registradorPor="
				+ registradorPor + "]";
	}
}
