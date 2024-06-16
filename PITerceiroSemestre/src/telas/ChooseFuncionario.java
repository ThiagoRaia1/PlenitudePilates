package telas;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

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

public class ChooseFuncionario extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton btnAvancar;

	/**
	 * Cria o painel para que o usuário digite o nome completo do funcionário que deseja editar.
	 * @param funcionario - Dados do funcionario logado.
	 * @param frame - Frame principal.
	 */
	public ChooseFuncionario(Funcionario funcionario, MainFrame frame) {
		setBackground(Color.WHITE);
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, null));
		
		JTextField textField_FuncionarioProcurado = new JTextField();
		textField_FuncionarioProcurado.setColumns(10);

		JLabel lblFuncionarioNaoEncontrado = new JLabel("Funcionario não encontrado.");
		lblFuncionarioNaoEncontrado.setVisible(false);
		
		JButton btnVoltar = new JButton("Voltar");
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
		
		btnAvancar = new JButton("Avançar");
		btnAvancar.addActionListener(new ActionListener() {
			/**
			 * Valida se o funcionário procurado existe no banco de dados. Se sim, avança para a tela 
			 * "EditFuncionario", se não, exibe em tela a mensagem "Funcionário não encontrado.".
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
								.addComponent(btnAvancar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
								.addComponent(btnVoltar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
								.addComponent(textField_FuncionarioProcurado, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
							.addGap(66))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addComponent(lblDigiteONomeDoFuncionario, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 211, Short.MAX_VALUE)
								.addComponent(lblFuncionarioNaoEncontrado, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
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
							.addComponent(lblDigiteONomeDoFuncionario, GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(textField_FuncionarioProcurado, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblFuncionarioNaoEncontrado, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAvancar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVoltar)
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
