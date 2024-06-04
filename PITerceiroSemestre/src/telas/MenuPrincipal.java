package telas;

import entities.Aluno;
import entities.Funcionario;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import customComponents.CustomTable;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static JPanel menuExibicao;
	private static JPanel PanelMenu;

	/**
	 * Create the panel.
	 */
	public MenuPrincipal(Funcionario funcionario) {
		
		menuExibicao = new JPanel();
		menuExibicao.setBackground(Color.LIGHT_GRAY);
		
		PanelMenu = new JPanel();
		PanelMenu.setBackground(SystemColor.activeCaption);
		
		JScrollPane tabelaFuncionarios = new JScrollPane(CustomTable.configTabela(Funcionario.getNOME_TABELA()));
		tabelaFuncionarios.setVisible(false);
		
		JScrollPane tabelaAlunos = new JScrollPane(CustomTable.configTabela(Aluno.getNOME_TABELA()));
		tabelaAlunos.setVisible(false);
		
		JButton btnAgenda = new JButton("Agenda");
		btnAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabelaAlunos.setVisible(false);
				tabelaFuncionarios.setVisible(false);
				revalidate(); //refresh
				repaint();
			}
		});
		btnAgenda.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnAlunos = new JButton("Alunos");
		btnAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabelaAlunos.setVisible(true);
				tabelaFuncionarios.setVisible(false);
				revalidate(); //refresh
				repaint();
			}
		});
		btnAlunos.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnEquipe = new JButton("Equipe");
		btnEquipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabelaAlunos.setVisible(false);
				tabelaFuncionarios.setVisible(true);
				revalidate(); //refresh
				repaint();
			}
		});
		btnEquipe.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnFinanceiro = new JButton("Financeiro");
		btnFinanceiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabelaAlunos.setVisible(false);
				tabelaFuncionarios.setVisible(false);
				revalidate(); //refresh
				repaint();
			}
		});
		btnFinanceiro.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaption);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(SystemColor.activeCaption);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setBackground(SystemColor.activeCaption);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(SystemColor.activeCaption);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBackground(SystemColor.activeCaption);
		GroupLayout gl_PanelMenu = new GroupLayout(PanelMenu);
		gl_PanelMenu.setHorizontalGroup(
			gl_PanelMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelMenu.createSequentialGroup()
					.addComponent(panel_4_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_PanelMenu.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_PanelMenu.createSequentialGroup()
							.addGroup(gl_PanelMenu.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
								.addComponent(btnAgenda, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(gl_PanelMenu.createSequentialGroup()
							.addGroup(gl_PanelMenu.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_2_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
								.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
								.addComponent(panel_3_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
								.addComponent(panel_3_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
								.addComponent(btnAlunos, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_PanelMenu.createSequentialGroup()
									.addComponent(btnEquipe, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
									.addGap(2))
								.addGroup(Alignment.LEADING, gl_PanelMenu.createSequentialGroup()
									.addComponent(btnFinanceiro, GroupLayout.PREFERRED_SIZE, 117, Short.MAX_VALUE)
									.addGap(4)))
							.addGap(12))))
		);
		gl_PanelMenu.setVerticalGroup(
			gl_PanelMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelMenu.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_PanelMenu.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_4_1, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_PanelMenu.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAgenda, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addGap(4)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
							.addGap(4)
							.addComponent(btnAlunos, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3_1, GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEquipe, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3_2, GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFinanceiro, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_2_1, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		JLabel lblNomeUsuario_1 = new JLabel(funcionario.getNome());
		lblNomeUsuario_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeUsuario_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_panel_2_1 = new GroupLayout(panel_2_1);
		gl_panel_2_1.setHorizontalGroup(
			gl_panel_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2_1.createSequentialGroup()
					.addComponent(lblNomeUsuario_1, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2_1.setVerticalGroup(
			gl_panel_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2_1.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addComponent(lblNomeUsuario_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
		);
		panel_2_1.setLayout(gl_panel_2_1);
		PanelMenu.setLayout(gl_PanelMenu);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(PanelMenu, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
					.addGap(6)
					.addComponent(menuExibicao, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(PanelMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(menuExibicao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(11))
		);
		GroupLayout gl_menuExibicao = new GroupLayout(menuExibicao);
		gl_menuExibicao.setHorizontalGroup(
			gl_menuExibicao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuExibicao.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_menuExibicao.createParallelGroup(Alignment.LEADING)
						.addComponent(tabelaAlunos, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
						.addComponent(tabelaFuncionarios, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)))
		);
		gl_menuExibicao.setVerticalGroup(
			gl_menuExibicao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuExibicao.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_menuExibicao.createParallelGroup(Alignment.LEADING)
						.addComponent(tabelaAlunos, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
						.addComponent(tabelaFuncionarios, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)))
		);
		menuExibicao.setLayout(gl_menuExibicao);
		setLayout(groupLayout);

	}
}
