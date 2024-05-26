package main;


import javax.swing.JOptionPane;

import entities.Aluno;
import entities.Aula;
import entities.Funcionario;
import entities.Pagamento;

public class Main {
	
	public static String opcao() {
			String opcao = JOptionPane.showInputDialog("Graças a Deus pai, receba");
		return opcao;
	}
	
	public static void main(String[] args) {
		
		// Funcionario.createFuncionario();
		// Aluno.createAluno();
		// Aula.createAula();
		// Pagamento.createPagamento();
		
		Funcionario.readFuncionario(1);
		System.out.println();
		
		Aluno.readAluno(1);
		System.out.println();
		
		Aula.readAula(1);
		System.out.println();
		
		Pagamento.readPagamento(1);
		System.out.println();
	}

}
