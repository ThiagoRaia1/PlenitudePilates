package telas;

import javax.swing.JPanel;

import entities.Funcionario;
import main.GetRowCount;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import customComponents.CustomButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

import customComponents.TextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditFuncionario extends JPanel {

	private static final long serialVersionUID = 1L;
	private TextField textField_Nome;
	private TextField textField_Telefone;
	private TextField textField_CEP;
	private TextField textField_Cidade;
	private TextField textField_Rua;
	private TextField textField_Bairro;
	private TextField textField_Funcao;
	private TextField textField_Usuario;
	private TextField textField_Senha;
	private TextField textField_NivelDeAcesso;

	/**
	 * Cria um painel para o usuario editar as informacoes do funcionario pesquisado.
	 * @param funcionarioLogado - Dados do funcionario logado.
	 * @param frame - Frame principal.
	 * @param funcionarioProcurado - Dados do funcionario buscado no painel "ChooseFuncionario".
	 */
	public EditFuncionario(Funcionario funcionarioLogado, MainFrame frame, Funcionario funcionarioProcurado) {
		setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(236, 236, 236));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, null));
		
		textField_Nome = new TextField();
		textField_Nome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:*");
		
		JLabel lblFuncao = new JLabel("Função:*");
		
		JLabel lblTelefone = new JLabel("Telefone:*");
		
		textField_Telefone = new TextField();
		textField_Telefone.setColumns(10);
		
		JLabel lblCEP = new JLabel("CEP:*");
		
		textField_CEP = new TextField();
		textField_CEP.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:*");
		
		textField_Cidade = new TextField();
		textField_Cidade.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua:*");
		
		textField_Rua = new TextField();
		textField_Rua.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:*");
		
		textField_Bairro = new TextField();
		textField_Bairro.setColumns(10);

		textField_Funcao = new TextField();
		textField_Funcao.setColumns(10);
		
		textField_Usuario = new TextField();
		textField_Usuario.setColumns(10);

		textField_Senha = new TextField();
		textField_Senha.setColumns(10);
		
		textField_NivelDeAcesso = new TextField();
		textField_NivelDeAcesso.setColumns(10);

		// Os campos sao preenchidos com os dados atuais do aluno.
		textField_Nome.setText(funcionarioProcurado.getNome());
		textField_Cidade.setText(funcionarioProcurado.getCidade());
		textField_Funcao.setText(funcionarioProcurado.getFuncao());
		textField_Telefone.setText(funcionarioProcurado.getTelefone());
		textField_CEP.setText(funcionarioProcurado.getCep());
		textField_Rua.setText(funcionarioProcurado.getRua());
		textField_Bairro.setText(funcionarioProcurado.getBairro());
		textField_Usuario.setText(funcionarioProcurado.getUsuario());
		textField_Senha.setText(funcionarioProcurado.getSenha());
		textField_NivelDeAcesso.setText(String.valueOf(funcionarioProcurado.getNivelDeAcesso()));
		// Os campos sao preenchidos com os dados atuais do aluno.
		
		JLabel lblMensagem = new JLabel("Preencha todos os dados obrigatórios.");
		lblMensagem.setVisible(false);
		
		JLabel lblRegistrarFuncionario = new JLabel("Editar funcionário");
		lblRegistrarFuncionario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegistrarFuncionario.setForeground(Color.DARK_GRAY);
		lblRegistrarFuncionario.setBackground(Color.LIGHT_GRAY);
		lblRegistrarFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(236, 236, 236));
		
		lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		
		CustomButton btnVoltar = new CustomButton();
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnVoltar.setText("VOLTAR");
		btnVoltar.setRadius(10);
		btnVoltar.setColorOver(new Color(236, 236, 236));
		btnVoltar.setColorClick(new Color(236, 236, 236));
		btnVoltar.setColor(new Color(255, 255, 255));
		btnVoltar.setBorderColor(new Color(255, 255, 255));
		btnVoltar.setFocusPainted(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.addActionListener(new ActionListener() {
			/**
			 * Retorna para o menu principal.
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal mp = new MenuPrincipal(funcionarioLogado, frame);
				frame.setContentPane(mp);
				frame.revalidate(); //refresh
				frame.repaint();
			}
		});
		
		CustomButton btnRegistrar = new CustomButton();
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnRegistrar.setText("EDITAR");
		btnRegistrar.setRadius(10);
		btnRegistrar.setFocusPainted(false);
		btnRegistrar.setBorderPainted(false);
		btnRegistrar.setColorOver(new Color(236, 236, 236));
		btnRegistrar.setColorClick(new Color(236, 236, 236));
		btnRegistrar.setColor(new Color(255, 255, 255));
		btnRegistrar.setBorderColor(new Color(255, 255, 255));
		btnRegistrar.addActionListener(new ActionListener() {
			/**
			 * Valida se todos os dados foram preenchidos corretamente, se sim, exibe
			 * "Dados do funcionario (nome do funcionario) atualizados.", 
			 * se nao, "Preencha todos os dados obrigatorios." caso algum campo nao tenha sido preenchido, 
			 * "Nome de usuario ja em uso." caso o usuario digitado ja esteja em uso,
			 * "Nivel de acesso invalido." caso o nivel de acesso digitado nao seja 1, 2 ou 3.
			 * Se atualizarmos os dados do funcionario que esta logado, seus dados serao substituidos pelos novos.
			 */
			public void actionPerformed(ActionEvent e) {
				boolean dadosPreenchidos = true;
				String[] dados = new String[10];
				dados[0] = textField_Nome.getText();
				dados[1] = textField_Cidade.getText();
				dados[2] = textField_Funcao.getText();
				dados[3] = textField_Telefone.getText();
				dados[4] = textField_CEP.getText();
				dados[5] = textField_Rua.getText();
				dados[6] = textField_Bairro.getText();
				dados[7] = textField_Usuario.getText();
				dados[8] = textField_Senha.getText();
				dados[9] = textField_NivelDeAcesso.getText();
				// dados.lenght-3 pois usuario, senha e nivelDeAcesso nao sao obrigatorios.
				// alterar pra que caso um dos tres tenha sido preenchido, os outros tambem precisam.
				for (int i = 0; i < dados.length-3; i++) {
					if (dados[i].equals("")) {
						lblMensagem.setText("Preencha todos os dados obrigatórios.");
						lblMensagem.setVisible(true);
						dadosPreenchidos = false;
						break;
					} else {
						dadosPreenchidos = true;
					}
				}
				boolean usuarioNaoEstaEmUso = true;
				if (dadosPreenchidos) {
					int numeroDeLinhas = GetRowCount.getRowCount(Funcionario.getNOME_TABELA());
					
	                // Se o nome de usuario nao for mantido, verifica se o usuario digitado esta disponivel
					if (!dados[7].equals(funcionarioProcurado.getUsuario())) {
						System.out.println("O usuário será alterado.");
						for (int i = 1; i <= numeroDeLinhas; i++) {
							Funcionario buscaUsuarioExistente = new Funcionario();
							buscaUsuarioExistente = Funcionario.read(i);
							if (dados[7].equals(buscaUsuarioExistente.getUsuario())) {
								lblMensagem.setText("Nome de usuario já em uso.");
								lblMensagem.setVisible(true);
								usuarioNaoEstaEmUso = false;
								break;
							} else {
								usuarioNaoEstaEmUso = true;
							}
						}
					} else {
						System.out.println("O usuário não será alterado.");
						usuarioNaoEstaEmUso = true;
					}
					
					if (usuarioNaoEstaEmUso) {
						try {
							Funcionario funcionario = new Funcionario();
							funcionario.setId(funcionarioProcurado.getId());
							funcionario.setNome(dados[0]);
							funcionario.setCidade(dados[1]);
							funcionario.setFuncao(dados[2]);
							funcionario.setTelefone(dados[3]);
							funcionario.setCep(dados[4]);
							funcionario.setRua(dados[5]);
							funcionario.setBairro(dados[6]);
							funcionario.setUsuario(dados[7]);
							funcionario.setSenha(dados[8]);
							funcionario.setNivelDeAcesso(Integer.parseInt(dados[9]));
							if (funcionario.getNivelDeAcesso() < 1 || funcionario.getNivelDeAcesso() > 3) {
								lblMensagem.setText("Nível de acesso inválido.");
								lblMensagem.setVisible(true);
							} else {
								lblMensagem.setText(Funcionario.update(funcionarioProcurado.getId(), funcionario));
								lblMensagem.setVisible(true);
								// Atualiza os dados do funcionario logado para alteracoes na interface.
								if (funcionario.getId() == funcionarioLogado.getId()) {
									funcionarioLogado.setNome(funcionario.getNome());
									funcionarioLogado.setFuncao(funcionario.getFuncao());
									funcionarioLogado.setTelefone(funcionario.getTelefone());
									funcionarioLogado.setCep(funcionario.getCep());
									funcionarioLogado.setCidade(funcionario.getCidade());
									funcionarioLogado.setRua(funcionario.getRua());
									funcionarioLogado.setBairro(funcionario.getBairro());
									funcionarioLogado.setUsuario(funcionario.getUsuario());
									funcionarioLogado.setSenha(funcionario.getSenha());
									funcionarioLogado.setNivelDeAcesso(funcionario.getNivelDeAcesso());
								}
							}
						} catch (NumberFormatException erro) {
							lblMensagem.setText("Nível de acesso inválido.");
							lblMensagem.setVisible(true);
						}
					}
				}
			}
		});
		
		JLabel lblNivelDeAcesso = new JLabel("Nível de acesso: (1;2;3)");
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(236, 236, 236));
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNivelDeAcesso, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
							.addGap(14))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(textField_NivelDeAcesso, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(lblMensagem, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(6)
							.addComponent(btnRegistrar, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVoltar, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNivelDeAcesso)
								.addComponent(lblMensagem, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE, false)
										.addComponent(btnRegistrar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
									.addContainerGap(4, Short.MAX_VALUE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(textField_NivelDeAcesso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())))))
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 370, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 362, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		
		JLabel lblSenha = new JLabel("Senha:");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(236, 236, 236));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(133)
					.addComponent(lblRegistrarFuncionario, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
					.addGap(130))
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addContainerGap()
											.addComponent(textField_Funcao, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
										.addGroup(gl_panel.createSequentialGroup()
											.addContainerGap()
											.addComponent(lblFuncao, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(8)
											.addComponent(textField_Nome, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)))
									.addGap(10))
								.addGroup(gl_panel.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblTelefone, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addContainerGap()
									.addComponent(textField_Telefone, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
									.addGap(10))
								.addGroup(gl_panel.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_Usuario, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_Senha, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNome, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRua, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCEP, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
									.addGap(51))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblBairro, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
									.addGap(39))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(textField_Bairro, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(textField_Rua, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(textField_CEP, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
									.addGap(26)))
							.addGap(9))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField_Cidade, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblCidade, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
							.addGap(32)))
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(4)
					.addComponent(lblRegistrarFuncionario, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(lblCidade))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_Cidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblRua)
									.addGap(2)
									.addComponent(textField_Rua, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblBairro, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_Bairro, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblCEP, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_CEP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblFuncao)
									.addGap(3)
									.addComponent(textField_Funcao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTelefone)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_Telefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblSenha)
										.addComponent(lblUsuario))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_Senha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_Usuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
}
