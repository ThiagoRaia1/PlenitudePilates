package main;


import javax.swing.JOptionPane;

import entities.Aluno;
import entities.Aula;
import entities.Funcionario;
import entities.Pagamento;
import telas.MainFrame;

public class Main {
	
	public static String opcao() {
			String opcao = JOptionPane.showInputDialog("Opção:");
		return opcao;
	}
	
	public static void main(String[] args) {
		
		/*
		Funcionario.create();
		Aluno.create();
		Aula.create();
		Pagamento.create();
		System.out.println();
		*/
		
		/*
		Funcionario.update(1);
		System.out.println();
		
		Aluno.update(1);
		System.out.println();
		
		Aula.update(1);
		System.out.println();
		
		Pagamento.update(1);
		System.out.println();
		*/
		
		/*
		System.out.println(Funcionario.read(1));
		System.out.println();

		System.out.println(Aluno.read(1));
		System.out.println();

		System.out.println(Aula.read(1));
		System.out.println();

		System.out.println(Pagamento.read(1));
		System.out.println();
		*/
		
		/*
		Aula.delete(1);
		System.out.println("Aula excluída.");
		
		Pagamento.delete(1);
		System.out.println("Pagamento excluído.");
		
		Aluno.delete(1);
		System.out.println("Aluno exclXuído.");
		
		Funcionario.delete(1);
		System.out.println("Funcionário excluído.");
		*/
		
		MainFrame.main();
	}

}
