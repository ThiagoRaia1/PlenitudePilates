package telas;

import java.awt.Color;

import javax.swing.JPanel;

import entities.Aluno;
import entities.Funcionario;
import main.BD;

import java.sql.SQLException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class AlunosPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static JPanel alunos;
	/**
	 * Create the panel.
	 */

	/**
	 * Altera as configurações da tabela para que se mantenha um padrão
	 * @param tabela - A tabela a ser configurada
	 * @return - A tabela configurada para o padrão definido
	 */
	public static JTable configTabela(JTable tabela) {
		tabela.setRowHeight(40);
		tabela.setEnabled(false);
		tabela.setFont(new Font("Tahoma", Font.PLAIN, 19));
		return tabela;
	}
	
	public AlunosPanel(Funcionario funcionario) {
		alunos = new JPanel();
		alunos.setBounds(0, 0, 900, 550);
		alunos.setBackground(Color.LIGHT_GRAY);
		alunos.setLayout(null);
		
		JPanel panel = new JPanel();
		
		String sql = "select count(*) from Aluno";
		int numeroDeLinhas = 0;
		BD bd = new BD();
		if (bd.getConnection()) {
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.rs = bd.st.executeQuery();
                while (bd.rs.next()) {
                    numeroDeLinhas = bd.rs.getInt(1);
                }
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}

		String [] colunas = {"Nome", "Mensalidade", "Data de nascimento", "CPF", "Contato"};
		Object [][] dados = new Object[numeroDeLinhas][5];
		Aluno aluno = new Aluno();
		for (int i = 1; i <= numeroDeLinhas; i++) {
			aluno = Aluno.read(i);
			dados[i-1][0] = aluno.getNome();
			// dados[0][1] = aluno.getMensalidade();
			dados[i-1][2] = aluno.getDataNascimento();
			//dados[i-1][3] = aluno.getCpf();
			dados[i-1][4] = aluno.getTelefone();
		}
		
		JTable tabela = new JTable(dados, colunas);
		configTabela(tabela);
		
		JPanel panelTabela = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelTabela, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
					.addGap(2))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(panelTabela, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JScrollPane barraRolagem = new JScrollPane(tabela);
		GroupLayout gl_panel_1 = new GroupLayout(panelTabela);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(barraRolagem, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addComponent(barraRolagem, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
		);
		panelTabela.setLayout(gl_panel_1);
		setLayout(groupLayout);
	}
}
