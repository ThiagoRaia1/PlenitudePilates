package customComponents;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import entities.Aluno;
import entities.Funcionario;
import main.BD;
import main.CalculaIdade;

public class CustomTable extends JScrollPane {
	private static final long serialVersionUID = 1L;

	/**
	 * @param opcao - A tabela selecionada
	 * @return - a tabela com os dados da tabela selecionada e informações escolhidas
	 */
	public static JTable configTabela(String opcao) {
		int numeroDeColunas = 5;
		String[] colunas = new String[numeroDeColunas];
		Object[][] dados = null;
		String sql = null;
		int numeroDeLinhasTabela = 0;
		
		BD bd = new BD();
		if (opcao.equals(Aluno.getNOME_TABELA())) {
			if (bd.getConnection()) {
				try {
					sql = "select count(*) from "+Aluno.getNOME_TABELA();
					bd.st = bd.con.prepareStatement(sql);
					bd.rs = bd.st.executeQuery();
	                while (bd.rs.next()) {
	                	numeroDeLinhasTabela = bd.rs.getInt(1);
	                }
				} catch (SQLException erro) {
					erro.printStackTrace();
				}
			}
			colunas[0] = "Nome";
			colunas[1] = "Mensalidade";
			colunas[2] = "Idade";
			colunas[3] = "CPF";
			colunas[4] = "Contato";

			Object[][] dadosAlunos = new Object[numeroDeLinhasTabela][numeroDeColunas];
			
			Aluno aluno = new Aluno();
			for (int i = 1; i <= numeroDeLinhasTabela; i++) {
				aluno = Aluno.read(i);
				dadosAlunos[i-1][0] = aluno.getNome();
				// dados[i-1][1] = aluno.getMensalidade();
				dadosAlunos[i-1][2] = CalculaIdade.calculaIdade(aluno.getDataNascimento());
				//dados[i-1][3] = aluno.getCpf();
				dadosAlunos[i-1][4] = aluno.getTelefone();
			}
			dados = dadosAlunos;
			bd.close();
		}
		
		if (opcao.equals(Funcionario.getNOME_TABELA())) {
			sql = "select count(*) from "+Funcionario.getNOME_TABELA();
			if (bd.getConnection()) {
				try {
					bd.st = bd.con.prepareStatement(sql);
					bd.rs = bd.st.executeQuery();
	                while (bd.rs.next()) {
	                	numeroDeLinhasTabela = bd.rs.getInt(1);
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

			Object[][] dadosFuncionarios = new Object[numeroDeLinhasTabela][numeroDeColunas];
			
			Funcionario funcionario = new Funcionario();
			for (int i = 1; i <= numeroDeLinhasTabela; i++) {
				funcionario = Funcionario.read(i);
				dadosFuncionarios[i-1][0] = funcionario.getNome();
				dadosFuncionarios[i-1][1] = funcionario.getFuncao();
				// dadosFuncionarios[i-1][2] = funcionario.getDataNascimento();
				// dadosFuncionarios[i-1][3] = funcionario.getCpf();
				dadosFuncionarios[i-1][4] = funcionario.getTelefone();
			}
			dados = dadosFuncionarios;
			bd.close();
		}
		
		JTable tabela = new JTable(dados, colunas);
		tabela.setRowHeight(40);
		tabela.setEnabled(false);
		tabela.setFont(new Font("Tahoma", Font.PLAIN, 19));
		return tabela;
	}
	
}
