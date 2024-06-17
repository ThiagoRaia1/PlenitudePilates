package telas;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import customComponents.TextField;
import customComponents.CustomButton;

import entities.Funcionario;
import main.GetRowCount;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Painel para que o usuario digite o nome completo do funcionario que deseja editar.
 */
public class ChooseFuncionario extends JPanel {
	private static final long serialVersionUID = 1L;
	private CustomButton btnAvancar;

	/**
	 * Cria o painel para que o usuario digite o nome completo do funcionario que deseja editar.
	 * @param funcionario - Dados do funcionario logado.
	 * @param frame - Frame principal.
	 */
	public ChooseFuncionario(Funcionario funcionario, MainFrame frame) {
		setBackground(Color.WHITE);
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, null));
		
		TextField textField_FuncionarioProcurado = new TextField();
		textField_FuncionarioProcurado.setColumns(10);

		JLabel lblFuncionarioNaoEncontrado = new JLabel("Funcionario não encontrado.");
		lblFuncionarioNaoEncontrado.setVisible(false);
		
		CustomButton btnVoltar = new CustomButton();
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnVoltar.setRadius(10);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setColorOver(new Color(236, 236, 236));
		btnVoltar.setColorClick(new Color(236, 236, 236));
		btnVoltar.setColor(new Color(255, 255, 255));
		btnVoltar.setBorderColor(new Color(255, 255, 255));
		btnVoltar.setText("VOLTAR");
		btnVoltar.addActionListener(new ActionListener() {
			/**
			 * Retorna para o menu principal.
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal mp = new MenuPrincipal(funcionario, frame);
				frame.setContentPane(mp);
				frame.revalidate(); //refresh
				frame.repaint();
			}
		});
		
		JLabel lblDigiteONomeDoFuncionario = new JLabel("Digite o nome do funcionario que quer editar:");
		lblDigiteONomeDoFuncionario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDigiteONomeDoFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnAvancar = new CustomButton();
		btnAvancar.setBorderPainted(false);
		btnAvancar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAvancar.setText("AVANÇAR");
		btnAvancar.setRadius(10);
		btnAvancar.setColorOver(new Color(236, 236, 236));
		btnAvancar.setColorClick(new Color(236, 236, 236));
		btnAvancar.setColor(new Color(255, 255, 255));
		btnAvancar.setBorderColor(new Color(255, 255, 255));
		btnAvancar.addActionListener(new ActionListener() {
			/**
			 * Valida se o funcionario procurado existe no banco de dados. Se sim, avanca para a tela 
			 * "EditFuncionario", se nao, exibe em tela a mensagem "Funcionario nao encontrado.".
			 */
			public void actionPerformed(ActionEvent e) {
				String funcionarioProcurado = textField_FuncionarioProcurado.getText();
				boolean naoEncontrado = true;
				int qtdeLinhasTabela = GetRowCount.getRowCount(Funcionario.getNOME_TABELA());
				Funcionario buscaFuncionario = new Funcionario();
				for (int i = 1; i <= qtdeLinhasTabela; i++) {
					buscaFuncionario = Funcionario.read(i);
					if (funcionarioProcurado.equals(buscaFuncionario.getNome())) {
						lblFuncionarioNaoEncontrado.setText("Funcionário encontrado.");
						lblFuncionarioNaoEncontrado.setVisible(true);
						naoEncontrado = false;
						EditFuncionario ea = new EditFuncionario(funcionario, frame, buscaFuncionario);
						frame.setContentPane(ea);
						frame.revalidate(); //refresh
						frame.repaint();
						break;
					}
				}
				if (naoEncontrado) {
					lblFuncionarioNaoEncontrado.setText("Funcionário não encontrado.");
					lblFuncionarioNaoEncontrado.setVisible(true);
				}
			}
		});
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_1_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		lblFuncionarioNaoEncontrado.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_3 = new JPanel();
		
		JPanel panel_3_1 = new JPanel();
		
		JPanel panel_4 = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_FuncionarioProcurado, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
							.addGap(6))
						.addComponent(lblFuncionarioNaoEncontrado, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnVoltar, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
										.addComponent(btnAvancar, GroupLayout.PREFERRED_SIZE, 117, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel_3_1, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)))
							.addGap(4))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblDigiteONomeDoFuncionario, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(panel_1_1, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
					.addGap(17))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDigiteONomeDoFuncionario, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(textField_FuncionarioProcurado, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblFuncionarioNaoEncontrado, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(4)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(btnAvancar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
										.addComponent(panel_3_1, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
					.addGap(9))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
					.addGap(9))
		);
		setLayout(groupLayout);
	}
}
