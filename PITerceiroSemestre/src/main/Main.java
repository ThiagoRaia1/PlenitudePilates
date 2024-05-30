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
		
		Funcionario.create();
		Aluno.create();
		Aula.create();
		Pagamento.create();
		System.out.println();
		
		System.out.println(Funcionario.read(1));
		System.out.println();

		System.out.println(Aluno.read(1));
		System.out.println();

		System.out.println(Aula.read(1));
		System.out.println();

		System.out.println(Pagamento.read(1));
		System.out.println();
		
		/*
		Aula.delete(1);
		System.out.println("Aula excluída.");
		
		Pagamento.delete(1);
		System.out.println("Pagamento excluído.");
		
		Aluno.delete(1);
		System.out.println("Aluno excluído.");
		
		Funcionario.delete(1);
		System.out.println("Funcionário excluído.");
		*/
	}

}
