package entities;

import java.util.ArrayList;
import java.util.List;

import main.BDPI;

public class Funcionario {
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
	
	public static void registrarFuncionario() {
		Funcionario funcionario = new Funcionario();
		funcionario.id = 1;
		funcionario.nome = "Thiago";
		funcionario.telefone = "telefone";
		funcionario.cep = "cep";
		funcionario.cidade = "Indaiatuba";
		funcionario.rua = "rua";
		funcionario.bairro = "bairro";
		funcionario.usuario = "Thiago";
		funcionario.senha = "Thiago";
		funcionario.nivelDeAcesso = 2;
		
		BDPI.insertValues(null, funcionario, null, null);
	}
	
	public static List<Funcionario> getFuncionarios() {
		List<Funcionario> funcionarios = new ArrayList<>();
		// BDPI.main(null, funcionarios)
		
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
	
	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", cep=" + cep + ", cidade="
				+ cidade + ", rua=" + rua + ", bairro=" + bairro + ", usuario=" + usuario + ", senha=" + senha
				+ ", nivelDeAcesso=" + nivelDeAcesso + "]";
	}
}
