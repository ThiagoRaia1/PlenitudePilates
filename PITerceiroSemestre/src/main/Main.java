package main;

import javax.swing.JOptionPane;

import classes.Aluno;
import classes.Funcionario;

public class Main {
	
	public static String Opcao() {
			String opcao = JOptionPane.showInputDialog("Graças a Deus pai, receba");
		return opcao;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Funcionario.registrarFuncionario();
		Aluno.registrarAluno();
	}

}
