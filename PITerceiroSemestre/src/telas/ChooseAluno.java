package telas;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import entities.Aluno;
import entities.Funcionario;
import main.GetRowCount;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import customComponents.TextField;
import customComponents.CustomButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChooseAluno extends JPanel {

	private static final long serialVersionUID = 1L;
	private TextField textField;

	/**
	 * Cria o painel para que o usuario digite o nome completo do aluno que deseja editar.
	 * @param funcionario - Dados do funcionario logado.
	 * @param frame - Frame principal.
	 */
	public ChooseAluno(Funcionario funcionario, MainFrame frame) {
		setBackground(Color.WHITE);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(236, 236, 236));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 64, 64), null));
		
		textField = new TextField();
		textField.setColumns(10);
		
		CustomButton btnNewButton = new CustomButton();
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.setRadius(10);
		btnNewButton.setText("VOLTAR");
		btnNewButton.setColorOver(new Color(236, 236, 236));
		btnNewButton.setColorClick(new Color(236, 236, 236));
		btnNewButton.setColor(new Color(255, 255, 255));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBorderColor(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * Retorna ao menu principal.
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal mp = new MenuPrincipal(funcionario, frame);
				frame.setContentPane(mp);
				frame.revalidate(); //refresh
				frame.repaint();
			}
		});
		
		JLabel lblDigiteONomeDoAluno = new JLabel("Digite o nome do aluno que quer editar:");
		lblDigiteONomeDoAluno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDigiteONomeDoAluno.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblMensagem = new JLabel("Aluno não encontrado.");
		lblMensagem.setVisible(false);
		
		CustomButton btnNewButton_1 = new CustomButton();
		btnNewButton_1.setRadius(10);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setText("AVANÇAR");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setColorOver(new Color(236, 236, 236));
		btnNewButton_1.setColorClick(new Color(236, 236, 236));
		btnNewButton_1.setColor(new Color(255, 255, 255));
		btnNewButton_1.setBorderColor(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			/**
			 * Valida se o nome digitado existe no banco de dados, se sim, direciona para a tela "EditAluno",
			 * se nao, exibe a mensagem "Aluno nao encontrado".
			 */
			public void actionPerformed(ActionEvent e) {
				String alunoProcurado = textField.getText();
				boolean naoEncontrado = true;
				int qtdeLinhasTabela = GetRowCount.getRowCount(Aluno.getNOME_TABELA());
				Aluno aluno = new Aluno();
				for (int i = 1; i <= qtdeLinhasTabela; i++) {
					aluno = Aluno.read(i);
					if (alunoProcurado.equals(aluno.getNome())) {
						System.out.println("Aluno encontrado.");
						naoEncontrado = false;
						EditAluno ea = new EditAluno(funcionario, frame, aluno);
						frame.setContentPane(ea);
						frame.revalidate(); //refresh
						frame.repaint();
						break;
					}
				}
				if (naoEncontrado) {
					lblMensagem.setText("Aluno não encontrado.");
					lblMensagem.setVisible(false);
				}
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(236, 236, 236));
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(236, 236, 236));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(236, 236, 236));
		
		lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(236, 236, 236));
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(new Color(236, 236, 236));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
					.addGap(12))
		);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(236, 236, 236));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
							.addGap(2))
						.addComponent(lblDigiteONomeDoAluno, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
							.addGap(10))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(lblMensagem, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
									.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
									.addGap(2))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
									.addGap(1))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
									.addGap(3)))
							.addComponent(panel_3_1, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1_1, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
					.addGap(3))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
							.addGap(6)
							.addComponent(lblDigiteONomeDoAluno, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(lblMensagem, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE))
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
							.addContainerGap())
						.addComponent(panel_1_1, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)))
		);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 62, Short.MAX_VALUE)
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 70, Short.MAX_VALUE)
		);
		panel_3.setLayout(gl_panel_3);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
	}
}
