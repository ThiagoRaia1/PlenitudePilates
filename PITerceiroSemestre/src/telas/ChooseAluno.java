package telas;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import entities.Aluno;
import entities.Funcionario;
import main.GetRowCount;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChooseAluno extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ChooseAluno(Funcionario funcionario, MainFrame frame) {
		setBackground(Color.WHITE);
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, null));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
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
		
		JButton btnNewButton_1 = new JButton("Avançar");
		btnNewButton_1.addActionListener(new ActionListener() {
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
						EditAluno ea = new EditAluno(funcionario, frame, alunoProcurado);
						frame.setContentPane(ea);
						revalidate(); //refresh
						repaint();
					}
				}
				System.out.println("Aluno não encontrado");
			}
		});
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_1_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		JLabel lblNewLabel = new JLabel("Aluno não encontrado.");
		lblNewLabel.setVisible(false);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(60)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
								.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
								.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
							.addGap(66))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addComponent(lblDigiteONomeDoAluno, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 211, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(panel_1_1, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(0)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDigiteONomeDoAluno, GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)
							.addGap(32))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
								.addComponent(panel_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
							.addContainerGap())))
		);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(11))
		);
		setLayout(groupLayout);
	}
}
