package customComponents;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import entities.Aluno;
import entities.Funcionario;
import main.BD;

public class CustomTable extends JScrollPane {
	private static final long serialVersionUID = 1L;

	public static JTable configTabela(String opcao) {
		String[] colunas = new String[5];
		Object[][] dados = null;
		String sql = null;
		int numeroDeLinhasTabelaAluno = 0, numeroDeLinhasTabelaFuncionario = 0;
		int numeroDeColunas = 5;
		BD bd = new BD();
		if (opcao.equals("Aluno")) { // Da um jeito de criar essa condicional
			if (bd.getConnection()) {
				try {
					sql = "select count(*) from Aluno";
					bd.st = bd.con.prepareStatement(sql);
					bd.rs = bd.st.executeQuery();
	                while (bd.rs.next()) {
	                    numeroDeLinhasTabelaAluno = bd.rs.getInt(1);
	                }
	                sql = "select count(*) from Funcionario";
	                bd.st = bd.con.prepareStatement(sql);
					bd.rs = bd.st.executeQuery();
	                while (bd.rs.next()) {
	                    numeroDeLinhasTabelaFuncionario = bd.rs.getInt(1);
	                }
				} catch (SQLException erro) {
					erro.printStackTrace();
				}
			}
			colunas[0] = "Nome";
			colunas[1] = "Mensalidade";
			colunas[2] = "Data de nascimento";
			colunas[3] = "CPF";
			colunas[4] = "Contato";
			if (numeroDeLinhasTabelaAluno > numeroDeLinhasTabelaFuncionario) {
				dados = new Object[numeroDeLinhasTabelaAluno][numeroDeColunas];
			} else {
				dados = new Object[numeroDeLinhasTabelaFuncionario][numeroDeColunas];
			}
			JTable tabela = new JTable(dados, colunas);
			tabela.setRowHeight(40);
			tabela.setEnabled(false);
			tabela.setFont(new Font("Tahoma", Font.PLAIN, 19));
			Aluno aluno = new Aluno();
			for (int i = 1; i <= numeroDeLinhasTabelaAluno; i++) {
				aluno = Aluno.read(i);
				dados[i-1][0] = aluno.getNome();
				// dados[i-1][1] = aluno.getMensalidade();
				dados[i-1][2] = aluno.getDataNascimento();
				//dados[i-1][3] = aluno.getCpf();
				dados[i-1][4] = aluno.getTelefone();
			}
			bd.close();
		}
		
		if (opcao.equals("Funcionario")) {
			sql = "select count(*) from Funcionario";
			if (bd.getConnection()) {
				try {
					bd.st = bd.con.prepareStatement(sql);
					bd.rs = bd.st.executeQuery();
	                while (bd.rs.next()) {
	                    numeroDeLinhasTabelaAluno = bd.rs.getInt(1);
	                }
				} catch (SQLException erro) {
					erro.printStackTrace();
				}
			}
			colunas[0] = "Nome";
			colunas[1] = "Função";
			colunas[2] = "Data de nascimento";
			colunas[3] = "CPF";
			colunas[4] = "Contato";
			tabela = new JTable(dados, colunas);
			tabela.setRowHeight(40);
			tabela.setEnabled(false);
			tabela.setFont(new Font("Tahoma", Font.PLAIN, 19));
			Funcionario funcionario = new Funcionario();
			for (int i = 1; i <= numeroDeLinhasTabelaAluno; i++) {
				funcionario = Funcionario.read(i);
				dados[i-1][0] = funcionario.getNome();
				// dados[i-1][1] = funcionario.getMensalidade();
				// dados[i-1][2] = funcionario.getDataNascimento();
				// dados[i-1][3] = funcionario.getCpf();
				dados[i-1][4] = funcionario.getTelefone();
			}
			bd.close();
		}
		return tabela;
	}
	
}
