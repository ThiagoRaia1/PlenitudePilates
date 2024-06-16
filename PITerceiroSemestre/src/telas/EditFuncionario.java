package telas;

import javax.swing.JPanel;

import entities.Funcionario;
import main.GetRowCount;

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
import java.awt.event.ActionEvent;

public class EditFuncionario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField_Nome;
	private JTextField textField_Telefone;
	private JTextField textField_CEP;
	private JTextField textField_Cidade;
	private JTextField textField_Rua;
	private JTextField textField_Bairro;
	private JTextField textField_Funcao;
	private JTextField textField_Usuario;
	private JTextField textField_Senha;
	private JTextField textField_NivelDeAcesso;

	/**
	 * Cria um painel para o usuário editar as informações do funcionário pesquisado.
	 * @param funcionarioLogado - Dados do funcionario logado.
	 * @param frame - Frame principal.
	 * @param funcionarioProcurado - Dados do funcionario buscado no painel "ChooseFuncionario".
	 */
	public EditFuncionario(Funcionario funcionarioLogado, MainFrame frame, Funcionario funcionarioProcurado) {
		setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, null));
		
		textField_Nome = new JTextField();
		textField_Nome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:*");
		
		JLabel lblFuncao = new JLabel("Função:*");
		
		JLabel lblTelefone = new JLabel("Telefone:*");
		
		textField_Telefone = new JTextField();
		textField_Telefone.setColumns(10);
		
		JLabel lblCEP = new JLabel("CEP:*");
		
		textField_CEP = new JTextField();
		textField_CEP.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:*");
		
		textField_Cidade = new JTextField();
		textField_Cidade.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua:*");
		
		textField_Rua = new JTextField();
		textField_Rua.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:*");
		
		textField_Bairro = new JTextField();
		textField_Bairro.setColumns(10);

		textField_Funcao = new JTextField();
		textField_Funcao.setColumns(10);
		
		textField_Usuario = new JTextField();
		textField_Usuario.setColumns(10);

		textField_Senha = new JTextField();
		textField_Senha.setColumns(10);
		
		textField_NivelDeAcesso = new JTextField();
		textField_NivelDeAcesso.setColumns(10);

		// Os campos são preenchidos com os dados atuais do aluno.
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
		// Os campos são preenchidos com os dados atuais do aluno.
		
		JLabel lblMensagem = new JLabel("Preencha todos os dados obrigatórios.");
		lblMensagem.setVisible(false);
		
		JLabel lblRegistrarFuncionario = new JLabel("Editar funcionário");
		lblRegistrarFuncionario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegistrarFuncionario.setForeground(Color.DARK_GRAY);
		lblRegistrarFuncionario.setBackground(Color.LIGHT_GRAY);
		lblRegistrarFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		
		lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnVoltar = new JButton("Voltar");
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
		
		JButton btnRegistrar = new JButton("Editar");
		btnRegistrar.addActionListener(new ActionListener() {
			/**
			 * Valida se todos os dados foram preenchidos corretamente, se sim, exibe
			 * "Dados do funcionario (nome do funcionario) atualizados.", 
			 * se não, "Preencha todos os dados obrigatórios." caso algum campo não tenha sido preenchido, 
			 * "Nome de usuario já em uso." caso o usuario digitado ja esteja em uso,
			 * "Nível de acesso inválido." caso o nível de acesso digitado não seja 1, 2 ou 3.
			 * Se atualizarmos os dados do funcionario que esta logado, seus dados serão substituídos pelos novos.
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
				// dados.lenght-3 pois usuario, senha e nivelDeAcesso não são obrigatórios.
				// alterar pra que caso um dos três tenha sido preenchido, os outros também precisam.
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
					
	                // Se o nome de usuário não for mantido, verifica se o usuário digitado está disponível
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
								// Atualiza os dados do funcionario logado para alterações na interface.
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
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNivelDeAcesso, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
						.addComponent(textField_NivelDeAcesso, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
					.addGap(48)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnRegistrar, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVoltar, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
						.addComponent(lblMensagem, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNivelDeAcesso))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblMensagem, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoltar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnRegistrar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField_NivelDeAcesso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4))
		);
		panel_1.setLayout(gl_panel_1);
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
		
		JLabel lblUsuario = new JLabel("Usuario:");
		
		JLabel lblSenha = new JLabel("Senha:");
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(133)
					.addComponent(lblRegistrarFuncionario, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
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
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField_Funcao, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(lblFuncao, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)))
					.addGap(52)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRua, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
						.addComponent(textField_Rua, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
					.addGap(8))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblUsuario, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Usuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField_Senha, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
					.addGap(50)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_CEP, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
						.addComponent(lblCEP, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(4)
					.addComponent(lblRegistrarFuncionario, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNome)
						.addComponent(lblCidade))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Cidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFuncao)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(lblRua)))
					.addGap(2)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_Rua, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Funcao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTelefone)
						.addComponent(lblBairro))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_Telefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Bairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCEP)
						.addComponent(lblUsuario)
						.addComponent(lblSenha))
					.addGap(2)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_CEP)
						.addComponent(textField_Usuario)
						.addComponent(textField_Senha))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
}
