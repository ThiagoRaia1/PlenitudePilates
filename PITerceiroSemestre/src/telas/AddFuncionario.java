package telas;

import javax.swing.JPanel;

import entities.Funcionario;
import main.GetRowCount;
import customComponents.TextField;
import customComponents.CustomButton;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.EtchedBorder;


import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddFuncionario extends JPanel {

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
	private TextField textField_NivelDePermissao;

	/**
	 * Create the panel.
	 */
	public AddFuncionario(Funcionario funcionario, MainFrame frame) {
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
		
		textField_NivelDePermissao = new TextField();
		textField_NivelDePermissao.setColumns(10);
		
		JLabel lblMensagem = new JLabel("Preencha todos os dados obrigatórios.");
		lblMensagem.setVisible(false);
		
		JLabel lblRegistrarFuncionario = new JLabel("Registrar funcionário");
		lblRegistrarFuncionario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegistrarFuncionario.setForeground(Color.DARK_GRAY);
		lblRegistrarFuncionario.setBackground(Color.LIGHT_GRAY);
		lblRegistrarFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		
		lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		
		CustomButton btnVoltar = new CustomButton();
		btnVoltar.setRadius(10);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setBorder(null);
		btnVoltar.setColorOver(new Color(179, 250, 160));
		btnVoltar.setBorderColor(new Color(236, 236, 236));
		btnVoltar.setColor(new Color(255, 255, 255));
		btnVoltar.setColorClick(new Color(236, 236, 236));
		btnVoltar.setText("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
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
		
		CustomButton btnRegistrar = new CustomButton();
		btnRegistrar.setRadius(10);
		btnRegistrar.setBorder(null);
		btnRegistrar.setBorderPainted(false);
		btnRegistrar.setBackground(new Color(236, 236, 236));
		btnRegistrar.setBorderColor(new Color(236, 236, 236));
		btnRegistrar.setColor(new Color(255, 255, 255));
		btnRegistrar.setColorClick(new Color(236, 236, 236));
		btnRegistrar.setColorOver(new Color(236, 236, 236));
		btnRegistrar.setText("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			/**
			 * Valida se todos os dados foram digitados corretamente e realiza o registro no banco de dados.
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
				dados[9] = textField_NivelDePermissao.getText();
				for (int i = 0; i < dados.length; i++) {
					if (dados[i].equals("")) {
						lblMensagem.setText("Preencha todos os dados obrigatórios.");
						lblMensagem.setVisible(true);
						dadosPreenchidos = false;
						break;
					} else {
						dadosPreenchidos = true;
					}
				}
				
				boolean usuarioJaExistente = true;
				if (dadosPreenchidos) {
					int numeroDeLinhas = GetRowCount.getRowCount(Funcionario.getNOME_TABELA());
	                System.out.println("Número de linhas: "+numeroDeLinhas);
					
					for (int i = 1; i <= numeroDeLinhas; i++) {
						Funcionario buscaUsuarioExistente = new Funcionario();
						buscaUsuarioExistente = Funcionario.read(i);
						if (dados[7].equals(buscaUsuarioExistente.getUsuario())) {
							lblMensagem.setText("Nome de usuario já em uso.");
							usuarioJaExistente = false;
							break;
						} else {
							usuarioJaExistente = true;
						}
					}
					if (usuarioJaExistente) {
						try {
							Funcionario funcionario = new Funcionario();
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
							lblMensagem.setText(Funcionario.create(funcionario));
							lblMensagem.setVisible(true);
						} catch (NumberFormatException erro) {
							lblMensagem.setText("Nível de acesso inválido.");
							lblMensagem.setVisible(true);
						}
					}
				}
			}
		});
		
		JLabel lblNivelDePermissao = new JLabel("Nível de permissão: (1;2;3)");
		
		JPanel panel_3 = new JPanel();
		
		JPanel panel_3_1 = new JPanel();
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(lblNivelDePermissao, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(textField_NivelDePermissao, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
					.addGap(26)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMensagem, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnRegistrar, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVoltar, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
						.addComponent(panel_3_1, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblMensagem, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNivelDePermissao)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnVoltar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnRegistrar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField_NivelDePermissao, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(6)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3_1, GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
						.addComponent(panel_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
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
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
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
						.addComponent(textField_CEP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Usuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Senha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
}
