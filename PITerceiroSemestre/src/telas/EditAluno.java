package telas;

import javax.swing.JPanel;

import entities.Aluno;
import entities.Funcionario;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class EditAluno extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextField textField_Nome;
	private JTextField textField_DataNascimento_Dia;
	private JTextField textField_DataNascimento_Mes;
	private JTextField textField_DataNascimento_Ano;
	private JTextField textField_Telefone;
	private JTextField textField_CEP;
	private JTextField textField_Cidade;
	private JTextField textField_Rua;
	private JTextField textField_Bairro;

	/**
	 * Cria um painel para o usuário editar as informações do aluno pesquisado.
	 */
	public EditAluno(Funcionario funcionario, MainFrame frame, Aluno alunoProcurado) {
		setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, null));
		
		textField_Nome = new JTextField();
		textField_Nome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		
		JLabel lblDataNascimento = new JLabel("Data de nascimento: (dd/mm/yyyy)");
		
		JLabel lblTelefone = new JLabel("Telefone:");
		
		textField_Telefone = new JTextField();
		textField_Telefone.setColumns(10);
		
		JLabel lblCEP = new JLabel("CEP:");
		
		textField_CEP = new JTextField();
		textField_CEP.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		
		textField_Cidade = new JTextField();
		textField_Cidade.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua:");
		
		textField_Rua = new JTextField();
		textField_Rua.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		
		textField_Bairro = new JTextField();
		textField_Bairro.setColumns(10);
		
		JLabel lblMensagem = new JLabel("Preencha todos os dados.");
		lblMensagem.setVisible(false);

		textField_DataNascimento_Dia = new JTextField();
		textField_DataNascimento_Dia.setColumns(10);
		
		textField_DataNascimento_Mes = new JTextField();
		textField_DataNascimento_Mes.setColumns(10);

		textField_DataNascimento_Ano = new JTextField();
		textField_DataNascimento_Ano.setColumns(10);
		
		textField_Nome.setText(alunoProcurado.getNome());
		textField_Cidade.setText(alunoProcurado.getCidade());
		textField_DataNascimento_Dia.setText(alunoProcurado.getDataNascimento().toString().substring(8, 10));
		textField_DataNascimento_Mes.setText(alunoProcurado.getDataNascimento().toString().substring(5, 7));
		textField_DataNascimento_Ano.setText(alunoProcurado.getDataNascimento().toString().substring(0, 4));
		textField_Telefone.setText(alunoProcurado.getTelefone());
		textField_CEP.setText(alunoProcurado.getCep());
		textField_Rua.setText(alunoProcurado.getRua());
		textField_Bairro.setText(alunoProcurado.getBairro());
		
		JButton btnRegistrar = new JButton("Editar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean dadosPreenchidos = true;
				String[] dados = new String[9];
				dados[0] = textField_Nome.getText();
				dados[1] = textField_Cidade.getText();
				dados[2] = textField_DataNascimento_Dia.getText();
				dados[3] = textField_DataNascimento_Mes.getText();
				dados[4] = textField_DataNascimento_Ano.getText();
				dados[5] = textField_Telefone.getText();
				dados[6] = textField_CEP.getText();
				dados[7] = textField_Rua.getText();
				dados[8] = textField_Bairro.getText();
				for (int i = 0; i < dados.length; i++) {
					if (dados[i].equals("")) {
						lblMensagem.setText("Preencha todos os dados.");
						lblMensagem.setVisible(true);
						dadosPreenchidos = false;
						break;
					} else {
						dadosPreenchidos = true;
					}
				}
				if (dadosPreenchidos) {
					// Adicionar condicional para ter uma idade mínima e máxima
					try {
						alunoProcurado.setNome(dados[0]);
						alunoProcurado.setCidade(dados[1]);
						alunoProcurado.setDataNascimento(Date.valueOf(dados[4] +"-"+ dados[3] +"-"+ dados[2]));
						alunoProcurado.setTelefone(dados[5]);
						alunoProcurado.setCep(dados[6]);
						alunoProcurado.setRua(dados[7]);
						alunoProcurado.setBairro(dados[8]);
						Aluno.update(alunoProcurado.getId(), alunoProcurado);
						lblMensagem.setText("Dados do aluno "+ alunoProcurado.getNome() +" atualizados.");
						lblMensagem.setVisible(true);
					} catch (NumberFormatException erro) {
						lblMensagem.setText("Data de nascimento inválida.");
						lblMensagem.setVisible(true);
					} catch (IllegalArgumentException erro) {
						lblMensagem.setText("Data de nascimento inválida.");
						lblMensagem.setVisible(true);
					}
				}
			}
		});
		
		JLabel lblEditarAluno = new JLabel("Editar Aluno");
		lblEditarAluno.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEditarAluno.setForeground(Color.DARK_GRAY);
		lblEditarAluno.setBackground(Color.LIGHT_GRAY);
		lblEditarAluno.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		
		lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			/**
			 * Retorna para o menu principal.
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				ChooseAluno ca = new ChooseAluno(funcionario, frame);
				frame.setContentPane(ca);
				frame.revalidate(); //refresh
				frame.repaint();
			}
		});
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_2_1 = new JPanel();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMensagem, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
							.addGap(13)
							.addComponent(btnVoltar, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_2_1, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
							.addGap(8)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addComponent(lblMensagem, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(6)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
							.addGap(6))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnVoltar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(10))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(panel_2_1, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
							.addGap(11)))
					.addGap(0))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_3 = new JPanel();
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(textField_DataNascimento_Dia, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_DataNascimento_Mes, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_DataNascimento_Ano, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_DataNascimento_Dia)
						.addComponent(textField_DataNascimento_Mes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(2)
							.addComponent(textField_DataNascimento_Ano, GroupLayout.PREFERRED_SIZE, 18, Short.MAX_VALUE)))
					.addGap(0))
		);
		panel_3.setLayout(gl_panel_3);
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
					.addGap(11)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(11))
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(133)
					.addComponent(lblEditarAluno, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
					.addGap(130))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(lblNome, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
					.addGap(52)
					.addComponent(lblCidade, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
					.addGap(8))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(textField_Nome, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
					.addGap(52)
					.addComponent(textField_Cidade, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
					.addGap(8))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(lblDataNascimento, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
					.addGap(52)
					.addComponent(lblRua, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
					.addGap(8))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
					.addGap(56)
					.addComponent(textField_Rua, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
					.addGap(8))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(lblTelefone, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
					.addGap(52)
					.addComponent(lblBairro, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
					.addGap(8))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(textField_Telefone, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
					.addGap(52)
					.addComponent(textField_Bairro, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
					.addGap(8))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCEP, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
						.addComponent(textField_CEP, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
					.addGap(52)
					.addComponent(btnRegistrar, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
					.addGap(8))
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(4)
					.addComponent(lblEditarAluno, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNome)
						.addComponent(lblCidade))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_Nome)
						.addComponent(textField_Cidade))
					.addGap(13)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDataNascimento)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(lblRua)))
					.addGap(2)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Rua))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTelefone)
						.addComponent(lblBairro))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_Telefone)
						.addComponent(textField_Bairro))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblCEP)
							.addComponent(textField_CEP)
							.addGap(2))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(13)
							.addComponent(btnRegistrar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(6)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
}
